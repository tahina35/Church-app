using cop_mobile.Model;
using Syncfusion.SfCalendar.XForms;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Text;

namespace cop_mobile.ViewModel
{
    public class CalendarViewModel
    {
        public CalendarEventCollection CalendarInlineEvents { get; set; } = new CalendarEventCollection();
        private List<Event> events;

        public CalendarViewModel(List<Event> events)
        {
            this.events = events;
            generateCalendarEvents(this.events); 
        }

        private void generateCalendarEvents(List<Event> events)
        {
            foreach(Event e in events)
            {
                CalendarInlineEvent event1 = new CalendarInlineEvent();
                event1.StartTime = e.startDate;
                event1.EndTime = e.endDate;
                event1.Subject = e.title;
                event1.Color = Xamarin.Forms.Color.FromHex(e.color);
                event1.IsAllDay = e.allDay;
                CalendarInlineEvents.Add(event1);
            }    
        }
    }
}
