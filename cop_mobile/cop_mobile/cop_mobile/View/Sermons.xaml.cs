using System;
using System.Collections.ObjectModel;
using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.View;
using cop_mobile.ViewModel;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace cop_mobile.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Sermons : ContentPage
    {
        private SermonViewModel vm;
        private SermonService sermonService;

        public Sermons(SermonViewModel viewModel)
        {
            InitializeComponent();
            vm = viewModel;
            this.BindingContext = vm;
            sermonService = DependencyService.Get<SermonService>();
        }

        private async void SfTabView_SelectionChanging(object sender, Syncfusion.XForms.TabView.SelectionChangingEventArgs e)
        {
            try
            {
                int index = e.Index;
                switch (index)
                {
                    case 1:
                        vm.CurrentTab = 3;
                        if (vm.Wednesday == null)
                        {
                            CustomPage<SermonData> wednesdaySermon = await sermonService.getWednesdaySermon(1);
                            vm.Wednesday = new ObservableCollection<SermonData>(wednesdaySermon.Content);
                            vm.WednesdayTotalItems = wednesdaySermon.TotalItems;
                            vm.WednesdaySermonPage = 1;
                        }
                        break;
                    case 2:
                        vm.CurrentTab = 4;
                        if (vm.Youth == null)
                        {
                            CustomPage<SermonData> youthSermon = await sermonService.getYouthSermon(1);
                            vm.Youth = new ObservableCollection<SermonData>(youthSermon.Content);
                            vm.YouthSermonTotalItems = youthSermon.TotalItems;
                            vm.YouthSermonPage = 1;
                        }
                        break;
                    case 3:
                        vm.CurrentTab = 5;
                        if (vm.Morning == null)
                        {
                            CustomPage<SermonData> morningSermon = await sermonService.getMorningSermon(1);
                            vm.Morning = new ObservableCollection<SermonData>(morningSermon.Content);
                            vm.MorningSermonTotalItems = morningSermon.TotalItems;
                            vm.MorningSermonPage = 1;
                        }
                        break;
                }
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }

        }

        private async void SfTabView_SelectionChanging_1(object sender, Syncfusion.XForms.TabView.SelectionChangingEventArgs e)
        {
            try
            {
                int index = e.Index;
                switch (index)
                {
                    case 0:
                        vm.CurrentTab = 1;
                        break;
                    case 1:
                        vm.CurrentTab = 2;
                        if (vm.Korean == null)
                        {
                            CustomPage<SermonData> koreanSermon = await sermonService.getKoreanSermon(1);
                            vm.Korean = new ObservableCollection<SermonData>(koreanSermon.Content);
                            vm.KoreanSermonTotalItems = koreanSermon.TotalItems;
                            vm.KoreanSermonPage = 1;
                        }
                        break; 
                }
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
        }

        private async void listView_ItemTapped(object sender, Syncfusion.ListView.XForms.ItemTappedEventArgs e)
        {
            SermonData sermon = (SermonData)e.ItemData;
            await Navigation.PushModalAsync(new PlaySermon(sermon.sermon));
        }
    }
}