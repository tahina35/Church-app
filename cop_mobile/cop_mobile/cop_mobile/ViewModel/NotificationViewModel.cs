using cop_mobile.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;

namespace cop_mobile.ViewModel
{
    public class NotificationViewModel
    {
        private ObservableCollection<Notification> notifications;

        public NotificationViewModel(List<Notification> data)
        {
            this.notifications = new ObservableCollection<Notification>(data);
        }

        public ObservableCollection<Notification> Notifications { get => notifications; set => notifications = value; }
    }
}
