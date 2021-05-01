using cop_mobile.Services;
using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class Notification
    {
        public long notificationId { get; set; }
        public string content { get; set; }
        public DateTime creationDate { get; set; }
        public Department department { get; set; }
        public Boolean isChurchWide => this.department != null;
        public string title;
        public string relativeTime => BaseService.getRelativeTime(creationDate);

        public Notification(long notificationId, string content, DateTime creationDate, Department department)
        {
            this.notificationId = notificationId;
            this.content = content;
            this.creationDate = creationDate;
            this.department = department;
        }

        public string Title
        {
            get
            {
                if (this.department != null)
                {
                    return this.department.name;
                }
                else
                {
                    return "";
                }
            }
        }
    }
}
