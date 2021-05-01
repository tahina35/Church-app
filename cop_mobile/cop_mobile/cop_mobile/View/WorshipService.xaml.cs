using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.View;
using cop_mobile.ViewModel;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace cop_mobile.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class WorshipService : ContentPage
    {
        private WorshipServiceViewModel vm;
        private WorshipServiceService worshipServiceService;

        public WorshipService(WorshipServiceViewModel viewModel)
        {
            InitializeComponent();           
            vm = viewModel;
            this.BindingContext = vm;
            worshipServiceService = DependencyService.Get<WorshipServiceService>();
        }

        private async void SfTabView_SelectionChanging(object sender, Syncfusion.XForms.TabView.SelectionChangingEventArgs e)
        {
            try
            {
                int index = e.Index;
                switch (index)
                {
                    case 1:
                        if (vm.WednesdayService == null)
                        {
                            WednesdayService wednesday = await worshipServiceService.GetWedndesdayService();
                            vm.WednesdayService = wednesday;
                        }
                        break;
                    case 2:
                        if (vm.YouthService == null)
                        {
                            YouthService youth = await worshipServiceService.GetYouthService();
                            vm.YouthService = youth;
                        }
                        break;
                    case 3:
                        if (vm.MorningServices == null)
                        {
                            List<EarlyMorningService> morning = await worshipServiceService.GetEarlyMorningServices();
                            vm.MorningServices = new ObservableCollection<EarlyMorningService>(morning);
                        }
                        break;
                }
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
           
        }
    }
}