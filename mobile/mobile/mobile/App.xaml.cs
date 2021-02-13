using mobile.Pages;
using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace mobile
{
    public partial class App : Application
    {
        public App()
        {
            Device.SetFlags(new String[] { "MediaElement_Experimental" });
            Syncfusion.Licensing.SyncfusionLicenseProvider.RegisterLicense("MzkzODM4QDMxMzgyZTM0MmUzMFVVbWI2OGxtR3dMVm8yeEd1UVg5NDdSZU9BV2EzR3pzMGovOTZsRVpiOE09");
            InitializeComponent();

            MainPage = new NavigationPage(new Main())
            {
                BarBackgroundColor = Color.FromHex("FD6474"),
                BarTextColor = Color.White  
            };
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
