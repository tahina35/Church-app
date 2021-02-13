using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using mobile.Model;
using mobile.View;
using mobile.ViewModel;
using Syncfusion.ListView.XForms;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace mobile.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Sermons : ContentPage
    {
        public Sermons()
        {
            InitializeComponent();
        }

        private async void listView_ItemTapped(object sender, Syncfusion.ListView.XForms.ItemTappedEventArgs e)
        {
            await Navigation.PushModalAsync(new PlaySermon(e.ItemData as Sermon));
        }
    }
}