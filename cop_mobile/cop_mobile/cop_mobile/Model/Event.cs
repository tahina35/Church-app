using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class Event
    {
        public long eventId { get; set; }
        public DateTime startDate { get; set; }
        public DateTime endDate { get; set; }
        public string title { get; set; }
        public string color { get; set; }
        public bool allDay { get; set; }
        public Department department { get; set; }

        public Event(long eventId, DateTime startDate, DateTime endDate, string title, string color, bool allDay, Department department)
        {
            this.eventId = eventId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.color = color;
            this.allDay = allDay;
            this.department = department;
        }
    }
}
