using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class SundayService
    {
        public long serviceType { get; set; }
        public DateTime date { get; set; }
        public string worshipAndPraise { get; set; }
        public string confessionOfFaith { get; set; }
        public string churchNews { get; set; }
        public string offering { get; set; }
        public string hymn { get; set; }
        public Member prayer { get; set; }
        public Sermon sermon { get; set; }
        public string stringDate => date.ToString("d");
        public string prayerFullName => prayer.fname + ' ' + prayer.lname;
        public string preacherFullName => sermon.preacher.fname + ' ' + sermon.preacher.lname;

        public SundayService(long serviceType, DateTime date, string worshipAndPraise, string confessionOfFaith, string churchNews, string offering, string hymn, Member prayer, Sermon sermon   )
        {
            this.serviceType = serviceType;
            this.date = date;
            this.worshipAndPraise = worshipAndPraise;
            this.confessionOfFaith = confessionOfFaith;
            this.churchNews = churchNews;
            this.offering = offering;
            this.hymn = hymn;
            this.prayer = prayer;
            this.sermon = sermon;
        }
    }
}
