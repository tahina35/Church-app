using mobile.Pages;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;
using System.Windows.Input;
using Xamarin.Forms;

namespace mobile.ViewModel
{
    class ProfileViewModel : INotifyPropertyChanged
    {
        bool isOpen;
        public event PropertyChangedEventHandler PropertyChanged;
        void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
        public ICommand PopupAcceptCommand { get; set; }
        public ICommand PopupDeclineCommand { get; set; }
        public ICommand PopupCommand { get; set; }
        public bool PopupOpen
        {
            get { return isOpen; }
            set
            {
                isOpen = value;
                OnPropertyChanged(nameof(PopupOpen));
            }
        }
        public ProfileViewModel()
        {
            PopupAcceptCommand = new Command(PopupAccept); //CanExecute() will be call the PopupAccept method
            PopupDeclineCommand = new Command(PopupDecline); //CanExecute() will be call the PopupDecline method
            PopupCommand = new Command(Popup);
        }

        private void Popup()
        {
            PopupOpen = true;
        }

        private async void PopupAccept()
        {
            await Application.Current.MainPage.Navigation.PushAsync(new Main());
        }

        private void PopupDecline()
        {
            // You can write your set of codes that needs to be executed.
        }
    }
}
