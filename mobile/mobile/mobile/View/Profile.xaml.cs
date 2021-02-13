using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace mobile.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Profile : ContentPage
    {
        public Profile()
        {
            InitializeComponent();
        }

        private void logout_Clicked(object sender, EventArgs e)
        {
            popupLayout.Show();
        }

        private async void reset_password_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new ResetPassword());
        }

        private async void edit_profile_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new EditProfile());
        }
    }
}