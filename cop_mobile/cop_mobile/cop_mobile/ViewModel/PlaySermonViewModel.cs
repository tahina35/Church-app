using cop_mobile.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.ViewModel
{
    public class PlaySermonViewModel
    {
        private Sermon sermon;

        public PlaySermonViewModel(Sermon sermon)
        {
            Sermon = sermon;
        }

        public Sermon Sermon { get => sermon; set => sermon = value; }
    }
}
