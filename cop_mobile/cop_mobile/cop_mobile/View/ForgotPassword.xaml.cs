using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace cop_mobile.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ForgotPassword : ContentPage
    {
        private LoginService loginService;

        public ForgotPassword()
        {
            InitializeComponent();
            loginService = DependencyService.Get<LoginService>();
        }

        private async void Button_Clicked(object sender, EventArgs e)
        {
            try
            {   
                if(validator.IsValid)
                {
                    Member member = await loginService.forgotPassword(userEmail.Text);
                    if (member != null)
                    {
                        Application.Current.MainPage = new NavigationPage(new ResetPassword(new ResetPasswordViewModel(member)));
                    }
                }
                else
                {
                    await DisplayAlert("Error", "The email you entered is invalid! Please try again.", "Ok");
                }

            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
            
        }
    }
}