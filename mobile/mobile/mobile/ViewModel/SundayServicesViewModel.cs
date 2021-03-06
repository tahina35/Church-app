using System;
using System.Collections.Generic;
using System.Text;

namespace mobile.ViewModel
{
    public class SundayServicesViewModel
    {
        private string str;

        public SundayServicesViewModel()
        {
            Str = "aaaaaaaaaa \n" +
                  "bbbbbbbbbb \n" +
                  "cccccccccc";
        }

        public string Str { get => str; set => str = value; }

    }
}
