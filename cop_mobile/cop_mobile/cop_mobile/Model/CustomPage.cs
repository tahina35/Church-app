using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class CustomPage<T>
    {
        private int pageNo;
        private int totalPages;
        private long totalItems;
        private List<T> content;

        public CustomPage()
        {

        }

        public CustomPage(int pageNo, int totalPages, long totalItems, List<T> content)
        {
            this.pageNo = pageNo;
            this.totalPages = totalPages;
            this.totalItems = totalItems;
            this.content = content;
        }

        public int PageNo { get => pageNo; set => pageNo = value; }
        public int TotalPages { get => totalPages; set => totalPages = value; }
        public long TotalItems { get => totalItems; set => totalItems = value; }
        public List<T> Content { get => content; set => content = value; }
    }
}
