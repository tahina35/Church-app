using mobile.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;

namespace mobile.ViewModel
{
    public class NewsViewModel
    {
        private ObservableCollection<News> news;

        public NewsViewModel()
        {
            generateNews();
        }

        public void generateNews()
        {
            string content = generateContent();
            news = new ObservableCollection<News>();
            news.Add(new News("My news 1", content, "Eucational community"));
            news.Add(new News("My news 2", content, "Missionary community"));
            news.Add(new News("My news 3", content, "Youth group"));
            news.Add(new News("My news 4", content, "Youth group"));
            news.Add(new News("My news 5", content, "Missionary community"));
        }

        public string generateContent()
        {
            string str = "<b>This text is bold</b>";
                       
            return str;
        }

        public ObservableCollection<News> News { get => news; set => news = value; }
    }
}
