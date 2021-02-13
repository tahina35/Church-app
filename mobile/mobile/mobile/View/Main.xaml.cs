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
    public partial class Main : ContentPage
    {
        public Main()
        {
            InitializeComponent();
        }

        private async void Login_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new LoginPage());
        }

        private async void calendar_btn_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new Calendar());
        }

        private async void sermon_btn_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new Sermons());
        }

        private async void news_btn_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new News());
        }

        private async void worship_service_btn_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new WorshipService());
        }

        private async void materials_btn_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new Materials());
        }

        private async void notifications_btn_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new Notifications());
        }

        private async void Profile_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new Profile());
        }
    }
}