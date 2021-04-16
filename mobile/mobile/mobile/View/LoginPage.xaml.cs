using mobile.Model;
using mobile.Services;
using mobile.View;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace mobile.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoginPage : ContentPage
    {

        private LoginService loginService;
        public LoginPage()
        {
            InitializeComponent();
            loginService = DependencyService.Get<LoginService>();
        }

        private async void register_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new RegistrationPage());

        }

        private async void login_Clicked(object sender, EventArgs e)
        {
            if (String.IsNullOrEmpty(email.Text) || String.IsNullOrEmpty(password.Text))
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
                    Credentials credentials = new Credentials(email.Text, password.Text);
                    //loginService.login();
                    await Navigation.PushAsync(new Main());
                }
            }
        }

        private async void ForgotPassword_Tapped(object sender, EventArgs e)
        {
            await Navigation.PushModalAsync(new ForgotPassword());
        }
    }
}