using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;

namespace cop_mobile.Model
{
    public class Credentials : INotifyPropertyChanged
    {

        private string username;
        private string password;

        public Credentials()
        {
        }

        public Credentials(string username, string password)
        {
            this.username = username;
            this.password = password;
        }

        public string Username
        {
            get => username;
            set
            {
                if (username != value)
                {
                    username = value;
                    OnPropertyChanged("Username");
                }
            }
        }

        public string Password
        {
            get => password;
            set
            {
                if (password != value)
                {
                    password = value;
                    OnPropertyChanged("Password");
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
