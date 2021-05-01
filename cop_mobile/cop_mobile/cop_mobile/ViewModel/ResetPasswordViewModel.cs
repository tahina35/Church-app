using cop_mobile.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;

namespace cop_mobile.ViewModel
{

    public class ResetPasswordViewModel : INotifyPropertyChanged
    {
        public Member member;
        public string password = "";
        public string confirmPassword = "";

        public ResetPasswordViewModel(Member m)
        {
            member = m;
        }

        public Member Member
        {
            get => member;
            set
            {
                member = value;
                OnPropertyChanged("Member");
            }
        }

        public string ConfirmPassword
        {
            get => confirmPassword;
            set
            {
                confirmPassword = value;
                OnPropertyChanged("ConfirmPassword");
            }
        }

        public string Password
        {
            get => password;
            set
            {
                password = value;
                OnPropertyChanged("Password");
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
