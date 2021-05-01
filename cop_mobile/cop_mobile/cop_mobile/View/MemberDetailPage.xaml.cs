using cop_mobile.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace cop_mobile.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MemberDetailPage : ContentPage
    {
        private MemberDetailViewModel vm;

        public MemberDetailPage(MemberDetailViewModel viewModel)
        {
            InitializeComponent();
            vm = viewModel;
            this.BindingContext = vm;
        }

        private async void address_btn_Clicked(object sender, EventArgs e)
        {
            try
            {
                String address = HttpUtility.UrlEncode(vm.Member.fullAddress);
                if (Device.RuntimePlatform == Device.iOS)
                {
                    // https://developer.apple.com/library/ios/featuredarticles/iPhoneURLScheme_Reference/MapLinks/MapLinks.html
                    await Launcher.OpenAsync("http://maps.apple.com/?q=" + address);
                }
                else if (Device.RuntimePlatform == Device.Android)
                {
                    // open the maps app directly
                    await Launcher.OpenAsync("geo:0,0?q=" + address);
                }
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }

        }

        private async void phone_btn_Clicked(object sender, EventArgs e)
        {
            try
            {
                PhoneDialer.Open(vm.Member.phoneNumber);
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
            
        }

        private async void email_btn_Clicked(object sender, EventArgs e)
        {
            try
            {
                await Email.ComposeAsync("", "", vm.Member.email);
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
        }
    }
}