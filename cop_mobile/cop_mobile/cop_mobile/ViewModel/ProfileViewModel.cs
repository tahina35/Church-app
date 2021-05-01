using cop_mobile.Model;
using cop_mobile.Pages;
using cop_mobile.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;
using System.Windows.Input;
using Xamarin.Forms;

namespace cop_mobile.ViewModel
{
    public class ProfileViewModel : INotifyPropertyChanged
    {

        private Member member;
        private string name;

        public ProfileViewModel(Member member)
        {
            this.member = member;
            this.name = member.fname + ' ' + member.lname;
        }

        public Member Member 
        { 
            get => member;
            set
            {
                if(member != value)
                {
                    member = value;
                    OnPropertyChanged("Member");
                }
            } 
        }

        public string Name
        {
            get => name;
            set
            {
                if (name != value)
                {
                    name = value;
                    OnPropertyChanged("Name");
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
