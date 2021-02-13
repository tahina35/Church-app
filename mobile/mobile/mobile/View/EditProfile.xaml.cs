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
    public partial class EditProfile : ContentPage
    {
        public EditProfile()
        {
            InitializeComponent();
        }

        private async void update_profile_Clicked(object sender, EventArgs e)
        {
            await Navigation.PopAsync();
        }

        private async void close_Clicked(object sender, EventArgs e)
        {
            await Navigation.PopAsync();
        }
    }
}