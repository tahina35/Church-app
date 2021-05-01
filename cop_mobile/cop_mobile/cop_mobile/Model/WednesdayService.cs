﻿using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class WednesdayService
    {
        public DateTime date { get; set; }
        public string hymn { get; set; }
        public Sermon sermon { get; set; }
        public string stringDate => date.ToString("d");
        public string preacherFullName => sermon.preacher.fname + ' ' + sermon.preacher.lname;

        public WednesdayService(DateTime date, string hymn, Sermon sermon)
        {
            this.date = date;
            this.hymn = hymn;
            this.sermon = sermon;
        }

        public WednesdayService() { }
    }
}
