using cop_mobile.Model;
using cop_mobile.Pages;
using cop_mobile.Services;
using cop_mobile.View;
using cop_mobile.ViewModel;
using System;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace mobile.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoginPage : ContentPage
    {
        private LoginService loginService;
        private MemberService memberService;
        private FCMTokenService tokenService;
        private LoginViewModel vm;
        public LoginPage(LoginViewModel viewModel)
        {
            vm = viewModel;
            loginService = DependencyService.Get<LoginService>();
            memberService = DependencyService.Get<MemberService>();
            tokenService = DependencyService.Get<FCMTokenService>();
            this.BindingContext = vm;
            InitializeComponent();
        }

        private async void login_Clicked(object sender, EventArgs e)
        {
            if (String.IsNullOrEmpty(vm.Credentials.Username) || String.IsNullOrEmpty(vm.Credentials.Password))
            {
                await DisplayAlert("Error", "Please enter your email and password", "OK");
            }
            else
            {
                if (!emailValidator.IsValid)
                {
                    await DisplayAlert("Invalid email", "The email you entered is invalid! Please try again.", "OK");
                }
                else
                {
                    Credentials credentials = vm.Credentials;
                    try
                    {
                        await loginService.login(credentials);
                        Member member = await memberService.LoadByUsername(credentials.Username);
                        string deviceID = await SecureStorage.GetAsync("deviceId");
                        string token = await SecureStorage.GetAsync("fcmtoken");
                        FCMToken fcmToken = new FCMToken(member, deviceID, token);
                        FCMToken newToken = await tokenService.save(fcmToken);
                        await Navigation.PushAsync(new Main());
                    }
                    catch(Exception ex)
                    {
                        await DisplayAlert("Login error", ex.Message, "OK");
                    }
                    
                }
            }
        }

        private async void ForgotPassword_Tapped(object sender, EventArgs e)
        {
            await Navigation.PushModalAsync(new ForgotPassword());
        }
    }
}