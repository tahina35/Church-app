using System;
using System.Collections.Generic;
using System.Text;

namespace mobile.Model
{
    public class News
    {
        private string title;
        private string content;
        private string group;

        public News(string title, string content, string group)
        {
            Title = title;
            Content = content;
            Group = group;
        }

        public string Title { get => title; set => title = value; }
        public string Content { get => content; set => content = value; }
        public string Group { get => group; set => group = value; }
    }
}


