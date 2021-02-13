using System;
using System.Collections.Generic;
using System.Text;
using Xam.Forms.VideoPlayer;

namespace mobile.Model
{
    public class Sermon
    {
        private string title;
        private string date;
        private string pastor;
        private string url;
        private UriVideoSource uri;
        private string thumbnail;

        public Sermon(string title, string date, string url, string pastor, string thumbnail)
        {
            Title = title;
            Date = date;
            Pastor = pastor;
            URL = url;
            Thumbnail = thumbnail;
            URI = new UriVideoSource()
            {
                Uri = url
            };
        }

        public string Title { get => title; set => title = value; }
        public string Date { get => date; set => date = value; }
        public string Pastor { get => pastor; set => pastor = value; }
        public string URL { get => url; set => url = value; }

        public UriVideoSource URI { get => uri; set => uri = value; }
        public string Thumbnail { get => thumbnail; set => thumbnail = value; }
    }
}
