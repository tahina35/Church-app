using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.Services.Interfaces;
using cop_mobile.View;
using cop_mobile.ViewModel;
using Newtonsoft.Json;
using Refit;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace cop_mobile.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Main : ContentPage
    {
        private MemberService memberService;
        private EventService eventService;
        private NewsService newsService;
        private WorshipServiceService worshipServiceService;
        private NotificationService notificationService;
        private DepartmentService departmentService;
        private SermonService sermonService;
        private FCMTokenService tokenService;

        public Main()
        {
            memberService = DependencyService.Get<MemberService>();
            eventService = DependencyService.Get<EventService>();
            newsService = DependencyService.Get<NewsService>();
            worshipServiceService = DependencyService.Get<WorshipServiceService>();
            notificationService = DependencyService.Get<NotificationService>();
            departmentService = DependencyService.Get<DepartmentService>();
            sermonService = DependencyService.Get<SermonService>();
            InitializeComponent();
        }

        private async void calendar_btn_Clicked(object sender, EventArgs e)
        {
            try
            {
                List<Event> events = await eventService.GetEvents();
                CalendarViewModel vm = new CalendarViewModel(events);
                await Navigation.PushAsync(new Calendar(vm));
            }
            catch(Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
        }

        private async void sermon_btn_Clicked(object sender, EventArgs e)
        {
            try
            {
                CustomPage<SermonData> englishSermon = await sermonService.getEnglishSermon(1);
                SermonViewModel vm = new SermonViewModel(englishSermon.Content);
                vm.EnglishSermonTotalItems = englishSermon.TotalItems;
                await Navigation.PushAsync(new Sermons(vm));
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            } 
        }

        private async void news_btn_Clicked(object sender, EventArgs e)
        {
            try
            {
                List<News> news = await newsService.GetNews();
                NewsViewModel vm = new NewsViewModel(news);
                await Navigation.PushAsync(new NewsPage(vm));
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
        }

        private async void notifications_btn_Clicked(object sender, EventArgs e)
        {
            try
            {
                List<Notification> notifications = await notificationService.GetNotifications();
                NotificationViewModel vm = new NotificationViewModel(notifications);
                await Navigation.PushAsync(new NotificationPage(vm));
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }

        }

        private async void worship_service_btn_Clicked(object sender, EventArgs e)
        {
            try
            {
                SundayServiceData sundayServiceData = await worshipServiceService.GetSundayService();
                WorshipServiceViewModel vm = new WorshipServiceViewModel(sundayServiceData);
                await Navigation.PushAsync(new WorshipService(vm));
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
            
        }

        private async void Profile_Clicked(object sender, EventArgs e)
        {
            try
            {
                Credentials credentials = JsonConvert.DeserializeObject<Credentials>(await SecureStorage.GetAsync("credentials"));
                Member member = await memberService.LoadByUsername(credentials.Username);
                ProfileViewModel vm = new ProfileViewModel(member);
                await Navigation.PushAsync(new Profile(vm));
            }
            catch(Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
        }

        private async void dept_btn_Clicked(object sender, EventArgs e)
        {
            try
            {
                Credentials credentials = JsonConvert.DeserializeObject<Credentials>(await SecureStorage.GetAsync("credentials"));
                Member member = await memberService.LoadByUsername(credentials.Username);
                DepartmentData departmentData = await departmentService.GetDepartmentData(member.memberId);
                DepartmentViewModel vm = new DepartmentViewModel(departmentData);
                await Navigation.PushAsync(new DepartmentPage(vm));
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
            
        }
    }
}