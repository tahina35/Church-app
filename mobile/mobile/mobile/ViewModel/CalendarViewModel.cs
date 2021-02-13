using Syncfusion.SfCalendar.XForms;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Text;

namespace mobile.ViewModel
{
    public class CalendarViewModel
    {
        public CalendarEventCollection CalendarInlineEvents { get; set; } = new CalendarEventCollection();
        public CalendarViewModel()
        {
            CalendarInlineEvent event1 = new CalendarInlineEvent();
            event1.StartTime = new DateTime(2021, 2, 15, 5, 0, 0);
            event1.EndTime = new DateTime(2021, 2, 15, 7, 0, 0);
            event1.Subject = "Go to Meeting";
            event1.Color = Color.Fuchsia;

            CalendarInlineEvent event2 = new CalendarInlineEvent();
            event2.StartTime = new DateTime(2021, 2, 20, 10, 0, 0);
            event2.EndTime = new DateTime(2021, 2, 20, 12, 0, 0);
            event2.Subject = "Planning";
            event2.Color = Color.Green;

            CalendarInlineEvents.Add(event1);
            CalendarInlineEvents.Add(event2);
        }
    }
}
