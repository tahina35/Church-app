using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class Department
    {
        public long deptId { get; set; }
        public Department parentDept { get; set; }
        public string name { get; set; }
        public string creationDate { get; set; }

        public Department(long deptId, Department parentDept, string name, string creationDate)
        {
            this.deptId = deptId;
            this.parentDept = parentDept;
            this.name = name;
            this.creationDate = creationDate;
        }
    }
}
