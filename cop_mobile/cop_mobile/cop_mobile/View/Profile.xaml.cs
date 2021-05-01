using cop_mobile.Model;
using cop_mobile.Pages;
using cop_mobile.Services;
using cop_mobile.ViewModel;
using mobile.Pages;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace cop_mobile.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Profile : ContentPage
    {
        private ProfileViewModel profileViewModel;

        public Profile(ProfileViewModel vm)
        {
            InitializeComponent();
            profileViewModel = vm;
            this.BindingContext = profileViewModel;
            
        }

        private async void logout_Clicked(object sender, EventArgs e)
        {
            bool answer = await DisplayAlert("Log out", "Are you sure you want to log out", "Yes", "No");
            if(answer)
            {
                SecureStorage.Remove("token");
                SecureStorage.Remove("credentials");
                SecureStorage.Remove("isLogged");
                Application.Current.MainPage = new NavigationPage(new LoginPage(new LoginViewModel(new Credentials())));

            }
        }

        private async void reset_password_Clicked(object sender, EventArgs e)
        {
            try
            {
                Member member = profileViewModel.Member;
                await Navigation.PushAsync(new ResetPassword(new ResetPasswordViewModel(member)));
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
        }

        private async void edit_profile_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new EditProfile());
        }

    }
}