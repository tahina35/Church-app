using cop_mobile.Model;
using cop_mobile.Pages;
using cop_mobile.Services;
using cop_mobile.ViewModel;
using mobile.Pages;
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
    public partial class ResetPassword : ContentPage
    {
        private ResetPasswordViewModel vm;
        private LoginService loginService;

        public ResetPassword(ResetPasswordViewModel viewModel)
        {
            InitializeComponent();
            loginService = DependencyService.Get<LoginService>();
            vm = viewModel;
            this.BindingContext = vm;
        }

        private async void reset_password_Clicked(object sender, EventArgs e)
        {
            try
            {
                if (String.IsNullOrEmpty(vm.Password) || String.IsNullOrEmpty(vm.ConfirmPassword))
                {
                    await DisplayAlert("Error", "Please fill out the required fields", "OK");
                }
                else
                {
                    if (pwdValidator.IsNotValid)
                    {
                        await DisplayAlert("Error", "Password must be eight characters including at least one lowercase letter, at least one uppercase letter, at least one special character and at least one alphanumeric character", "OK");
                    }
                    else if (confirmPwdValidator.IsNotValid)
                    {
                        await DisplayAlert("Error", "Password do not match", "OK");
                    }
                    else
                    {
                        Member member = vm.Member;
                        member.password = vm.Password;
                        await loginService.resetPassword(member);
                        await DisplayAlert("Password changed", "You succesfuly changed your password", "OK");
                        Application.Current.MainPage = new NavigationPage(new LoginPage(new LoginViewModel(new Credentials())));
                    }
                }
                
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
            
        }
    }
}