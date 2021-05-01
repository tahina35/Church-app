using cop_mobile.Model;
using mobile.Pages;
using cop_mobile.Services;
using cop_mobile.ViewModel;
using Plugin.FirebasePushNotification;
using System;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using cop_mobile.Pages;
using Newtonsoft.Json;

namespace cop_mobile
{
    public partial class App : Application    
    {
        private FCMTokenService tokenService;
        private MemberService memberService;

        public App()
        {
            Syncfusion.Licensing.SyncfusionLicenseProvider.RegisterLicense("NDI4MjI2QDMxMzkyZTMxMmUzMGxBYTJZU1BmZFVidFQ3VWRCMUVIL2FoSG9NdXhrNmJzK1RSOEhCUDFPK2M9");
            InitializeComponent();

            tokenService = DependencyService.Get<FCMTokenService>();
            memberService = DependencyService.Get<MemberService>();

            var isLoogged = SecureStorage.GetAsync("isLogged").Result;

            if (String.IsNullOrEmpty(isLoogged) || isLoogged != "1")
            {
                MainPage = new NavigationPage(new LoginPage(new LoginViewModel(new Credentials())));
            }
            else
            {
                MainPage = new NavigationPage(new Main());
            }
        }

        protected override void OnStart()
        {
            CrossFirebasePushNotification.Current.Subscribe("general");
            CrossFirebasePushNotification.Current.OnTokenRefresh += async (s, p) =>
            {
                try
                {
                    System.Diagnostics.Debug.WriteLine($"TOKEN : {p.Token}");
                    tokenService = DependencyService.Get<FCMTokenService>();
                    string deviceId = await SecureStorage.GetAsync("deviceId");
                    await SecureStorage.SetAsync("fcmtoken", p.Token);
                    if (String.IsNullOrEmpty(deviceId))
                    {
                        Guid g = Guid.NewGuid();
                        deviceId = g.ToString();
                        await SecureStorage.SetAsync("deviceId", deviceId);
                    }

                    string currentUser = await SecureStorage.GetAsync("credentials");
                    if(!String.IsNullOrEmpty(currentUser))
                    {
                        Credentials credentials = JsonConvert.DeserializeObject<Credentials>(currentUser);
                        Member member = await memberService.LoadByUsername(credentials.Username);
                        FCMToken fcmToken = new FCMToken(member, deviceId, p.Token);
                        FCMToken newToken = await tokenService.save(fcmToken);
                    }
                }
                catch (Exception ex)
                {
                    await App.Current.MainPage.DisplayAlert("Error", ex.Message, "Ok");
                }

            };

            CrossFirebasePushNotification.Current.OnNotificationReceived += (s, p) =>
            {

                System.Diagnostics.Debug.WriteLine("Received");

            };

            CrossFirebasePushNotification.Current.OnNotificationOpened += (s, p) =>
            {
                System.Diagnostics.Debug.WriteLine("Opened");
                foreach (var data in p.Data)
                {
                    System.Diagnostics.Debug.WriteLine($"{data.Key} : {data.Value}");
                }


            };

            CrossFirebasePushNotification.Current.OnNotificationAction += (s, p) =>
            {
                System.Diagnostics.Debug.WriteLine("Action");

                if (!string.IsNullOrEmpty(p.Identifier))
                {
                    System.Diagnostics.Debug.WriteLine($"ActionId: {p.Identifier}");
                    foreach (var data in p.Data)
                    {
                        System.Diagnostics.Debug.WriteLine($"{data.Key} : {data.Value}");
                    }

                }

            };

            CrossFirebasePushNotification.Current.OnNotificationDeleted += (s, p) =>
            {
                System.Diagnostics.Debug.WriteLine("Deleted");

            };
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
