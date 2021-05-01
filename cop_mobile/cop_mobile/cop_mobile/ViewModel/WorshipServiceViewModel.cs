using cop_mobile.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Text;

namespace cop_mobile.ViewModel
{
    public class WorshipServiceViewModel : INotifyPropertyChanged
    {
        private SundayServiceData sundayServiceData;
        private WednesdayService wednesdayService;
        private YouthService youthService;
        private ObservableCollection<EarlyMorningService> morningServices;

        public WorshipServiceViewModel(SundayServiceData data)
        {
            sundayServiceData = data;               
        }

        public SundayServiceData SundayServiceData
        {
            get => sundayServiceData;
            set
            {
                if (sundayServiceData != value)
                {
                    sundayServiceData = value;
                    OnPropertyChanged("SundayServiceData");
                }
            }
        }

        public YouthService YouthService
        {
            get => youthService;
            set
            {
                if (youthService != value)
                {
                    youthService = value;
                    OnPropertyChanged("YouthService");
                }
            }
        }

        public WednesdayService WednesdayService
        {
            get => wednesdayService;
            set
            {
                if (wednesdayService != value)
                {
                    wednesdayService = value;
                    OnPropertyChanged("WednesdayService");
                }
            }
        }

        public ObservableCollection<EarlyMorningService> MorningServices
        {
            get => morningServices;
            set
            {
               morningServices = value;
               OnPropertyChanged("MorningServices");
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
