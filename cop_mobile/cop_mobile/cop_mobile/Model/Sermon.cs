using System;
using System.Collections.Generic;
using System.Text;
using System.Web;

namespace cop_mobile.Model
{
    public class Sermon
    {
        public long sermonId { get; set; }                          
        public string mainVerse { get; set; }
        public string url { get; set; }
        public string thumbnail { get; set; }
        public bool isUploaded { get; set; }
        public Member preacher { get; set; }
        public string pastorName => "Pastor " + preacher.fname + ' ' + preacher.lname;

        public Sermon(long sermonId, string mainVerse, string url, string thumbnail, bool isUploaded, Member preacher)
        {
            this.sermonId = sermonId;
            this.mainVerse = mainVerse;
            this.url = url;
            this.thumbnail = this.getThumbnail();
            this.isUploaded = isUploaded;
            this.preacher = preacher;
        }

        public string getThumbnail()
        {
            var uri = new Uri(url);
            var query = HttpUtility.ParseQueryString(uri.Query);
            string videoId = query["v"];
            string res = $"https://img.youtube.com/vi/{videoId}/0.jpg";
            return res;
        }
    }
}
