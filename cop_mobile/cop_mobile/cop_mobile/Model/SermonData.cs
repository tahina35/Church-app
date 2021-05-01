using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class SermonData
    {
        public Sermon sermon { get; set; }
        public DateTime date { get; set; }
        public string stringDate => date.ToString("MM.dd.yyyy");

        public SermonData(Sermon sermon, DateTime date)
        {
            this.sermon = sermon;
            this.date = date;
        }
    }
}
