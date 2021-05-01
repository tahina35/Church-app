using cop_mobile.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;

namespace cop_mobile.ViewModel
{
    public class NewsViewModel
    {
        private ObservableCollection<News> news;

        public NewsViewModel(List<News> news)
        {
            this.news = new ObservableCollection<News>(news);
        }

        public ObservableCollection<News> News { get => news; set => news = value; }
    }
}
