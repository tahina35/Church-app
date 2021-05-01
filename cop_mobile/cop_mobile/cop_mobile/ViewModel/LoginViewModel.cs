using cop_mobile.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;

namespace cop_mobile.ViewModel
{
    public class LoginViewModel : INotifyPropertyChanged
    {
        private Credentials credentials;

        public LoginViewModel(Credentials credentials)
        {
            this.credentials = credentials;
        }

        public Credentials Credentials
        {
            get => credentials;
            set
            {
                if (credentials != value)
                {
                    credentials = value;
                    OnPropertyChanged("Credentials");
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
