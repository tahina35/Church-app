using cop_mobile.Model;
using cop_mobile.Services;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace cop_mobile.ViewModel
{
    public class SermonViewModel : INotifyPropertyChanged
    {
        private SermonService sermonService;
        private ObservableCollection<SermonData> english;
        private ObservableCollection<SermonData> korean;
        private ObservableCollection<SermonData> wednesday;
        private ObservableCollection<SermonData> youth;
        private ObservableCollection<SermonData> morning;
        private long englishSermonTotalItems;
        private long koreanSermonTotalItems;
        private long wednesdayTotalItems;
        private long youthSermonTotalItems;
        private long morningSermonTotalItems;
        private int englishSermonPage = 1;
        private int koreanSermonPage = 1;
        private int wednesdaySermonPage = 1;
        private int youthSermonPage = 1;
        private int morningSermonPage = 1;
        private int currentTab = 1;
        public Command<object> LoadMoreItemsCommand { get; set; }

        public SermonViewModel(List<SermonData> sermon)
        {
            sermonService = DependencyService.Get<SermonService>();
            English = new ObservableCollection<SermonData>(sermon);
            LoadMoreItemsCommand = new Command<object>(LoadMoreItems, CanLoadMoreItems);
        }

        private async void LoadMoreItems(object obj)
        {
            try
            {
                var listview = obj as Syncfusion.ListView.XForms.SfListView;
                CustomPage<SermonData> moreSermon;
                switch (currentTab) 
                {
                    case 1:
                        if (English.Count == 0)
                            return;
                        listview.IsBusy = true;
                        await Task.Delay(1000);
                        EnglishSermonPage++;
                        moreSermon = await sermonService.getEnglishSermon(EnglishSermonPage);
                        EnglishSermonTotalItems = moreSermon.TotalItems;
                        AddSermons(English, moreSermon.Content);
                        listview.IsBusy = false;
                        break;
                    case 2:
                        if (Korean.Count == 0)
                            return;
                        listview.IsBusy = true;
                        await Task.Delay(1000);
                        KoreanSermonPage++;
                        moreSermon = await sermonService.getKoreanSermon(KoreanSermonPage);
                        KoreanSermonTotalItems = moreSermon.TotalItems;
                        AddSermons(Korean, moreSermon.Content);
                        listview.IsBusy = false;
                        break;
                    case 3:
                        if (Wednesday.Count == 0)
                            return;
                        listview.IsBusy = true;
                        await Task.Delay(1000);
                        WednesdaySermonPage++;
                        moreSermon = await sermonService.getWednesdaySermon(WednesdaySermonPage);
                        WednesdayTotalItems = moreSermon.TotalItems;
                        AddSermons(Wednesday, moreSermon.Content);
                        listview.IsBusy = false;
                        break;
                    case 4:
                        if (Youth.Count == 0)
                            return;
                        listview.IsBusy = true;
                        await Task.Delay(1000);
                        YouthSermonPage++;
                        moreSermon = await sermonService.getYouthSermon(YouthSermonPage);
                        YouthSermonTotalItems = moreSermon.TotalItems;
                        AddSermons(Youth, moreSermon.Content);
                        listview.IsBusy = false;
                        break;
                    case 5:
                        if (Morning.Count == 0)
                            return;
                        listview.IsBusy = true;
                        await Task.Delay(1000);
                        MorningSermonPage++;
                        moreSermon = await sermonService.getMorningSermon(MorningSermonPage);
                        MorningSermonTotalItems = moreSermon.TotalItems;
                        AddSermons(Morning, moreSermon.Content);
                        listview.IsBusy = false;
                        break;
                }
            }
            catch (Exception ex)
            {
                await Application.Current.MainPage.DisplayAlert("Error", ex.Message, "Ok");
            }

        }

        private bool CanLoadMoreItems(object obj)
        {
            switch (currentTab)
            {
                case 1:
                    if (English.Count == EnglishSermonTotalItems)
                        return false;
                    return true;
                case 2:
                    if (Korean.Count == KoreanSermonTotalItems)
                        return false;
                    return true;
                case 3:
                    if (Wednesday.Count == WednesdayTotalItems)
                        return false;
                    return true;
                case 4:
                    if (Youth.Count == YouthSermonTotalItems)
                        return false;
                    return true;
                case 5:
                    if (Morning.Count == MorningSermonTotalItems)
                        return false;
                    return true;
                default:
                    return false;
            }  
        }

        public void AddSermons(ObservableCollection<SermonData>  sermons, List<SermonData> sermmonsToAdd)
        {
            foreach (SermonData s in sermmonsToAdd)
            {
                sermons.Add(s);
            }
        }

        public ObservableCollection<SermonData> English
        {
            get => english;
            set
            {
                english = value;
                OnPropertyChanged("English");
            }
        }

        public ObservableCollection<SermonData> Korean
        {
            get => korean;
            set
            {
                korean = value;
                OnPropertyChanged("Korean");
            }
        }

        public ObservableCollection<SermonData> Wednesday
        {
            get => wednesday;
            set
            {
                wednesday = value;
                OnPropertyChanged("Wednesday");
            }
        }

        public ObservableCollection<SermonData> Youth
        {
            get => youth;
            set
            {
                youth = value;
                OnPropertyChanged("Youth");
            }
        }

        public ObservableCollection<SermonData> Morning
        {
            get => morning;
            set
            {
                morning = value;
                OnPropertyChanged("Morning");
            }
        }

        public int EnglishSermonPage
        {
            get => englishSermonPage;
            set
            {
                if (englishSermonPage != value)
                {
                    englishSermonPage = value;
                    OnPropertyChanged("EnglishSermonPage");
                }
            }
        }

        public int KoreanSermonPage
        {
            get => koreanSermonPage;
            set
            {
                if (koreanSermonPage != value)
                {
                    koreanSermonPage = value;
                    OnPropertyChanged("KoreanSermonPage");
                }
            }
        }

        public int WednesdaySermonPage
        {
            get => wednesdaySermonPage;
            set
            {
                if (wednesdaySermonPage != value)
                {
                    wednesdaySermonPage = value;
                    OnPropertyChanged("WednesdaySermonPage");
                }
            }
        }

        public int YouthSermonPage
        {
            get => youthSermonPage;
            set
            {
                if (youthSermonPage != value)
                {
                    youthSermonPage = value;
                    OnPropertyChanged("YouthSermonPage");
                }
            }
        }

        public int MorningSermonPage
        {
            get => morningSermonPage;
            set
            {
                if (morningSermonPage != value)
                {
                    morningSermonPage = value;
                    OnPropertyChanged("MorningSermonPage");
                }
            }
        }

        public int CurrentTab
        {
            get => currentTab;
            set
            {
                if (currentTab != value)
                {
                    currentTab = value;
                    OnPropertyChanged("CurrentTab");
                }
            }
        }

        public long EnglishSermonTotalItems
        {
            get => englishSermonTotalItems;
            set
            {
                if (englishSermonTotalItems != value)
                {
                    englishSermonTotalItems = value;
                    OnPropertyChanged("EnglishSermonTotalItems");
                }
            }
        }

        public long KoreanSermonTotalItems
        {
            get => koreanSermonTotalItems;
            set
            {
                if (koreanSermonTotalItems != value)
                {
                    koreanSermonTotalItems = value;
                    OnPropertyChanged("KoreanSermonTotalItems");
                }
            }
        }

        public long WednesdayTotalItems
        {
            get => wednesdayTotalItems;
            set
            {
                if (wednesdayTotalItems != value)
                {
                    wednesdayTotalItems = value;
                    OnPropertyChanged("WednesdayTotalItems");
                }
            }
        }

        public long YouthSermonTotalItems
        {
            get => youthSermonTotalItems;
            set
            {
                if (youthSermonTotalItems != value)
                {
                    youthSermonTotalItems = value;
                    OnPropertyChanged("YouthSermonTotalItems");
                }
            }
        }

        public long MorningSermonTotalItems
        {
            get => morningSermonTotalItems;
            set
            {
                if (morningSermonTotalItems != value)
                {
                    morningSermonTotalItems = value;
                    OnPropertyChanged("MorningSermonTotalItems");
                }
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
