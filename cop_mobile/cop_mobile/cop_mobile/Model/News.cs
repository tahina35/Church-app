using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class News
    {
        public long newsId { get; set; }
        public string content { get; set; }
        public string startDate { get; set; }
        public string endDate { get; set; }
        public Department department { get; set; }
        public Boolean isChurchWide => this.department != null;
        public string title;

        public News(long newsId, string content, string startDate, string endDate, Department department)
        {
            this.newsId = newsId;
            this.content = content;
            this.startDate = startDate;
            this.endDate = endDate;
            this.department = department;
        }

        public string Title
        {
            get
            {
                 if(this.department != null)
                 {
                    return this.department.name;
                 }
                 else
                 {
                    return "";
                 }
            }
        }

    }
}


