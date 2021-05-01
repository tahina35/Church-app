using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class EarlyMorningService
    {
        public DateTime date { get; set; }
        public Sermon sermon { get; set; }
        public string stringDate => date.ToString("dddd") + " (" + date.ToString("MM/dd") + ")";
        public string preacherFullName => sermon.preacher.fname + ' ' + sermon.preacher.lname;

        public EarlyMorningService(DateTime date, Sermon sermon)
        {
            this.date = date;
            this.sermon = sermon;
        }
    }
}
