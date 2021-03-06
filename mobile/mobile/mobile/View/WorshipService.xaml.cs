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
    public partial class WorshipService : ContentPage
    {
        public WorshipService()
        {
            InitializeComponent();
        }

        private async void sunday_services_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new SundayServices());
        }

        private async void wednesday_services_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new WednesdayService());
        }

        private async void morning_services_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new EarlyMorningServices());
        }
    }
}