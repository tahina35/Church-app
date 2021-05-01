using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class DepartmentData
    {
        private List<Department> depts;
        private CustomPage<Member> members;

        public DepartmentData(List<Department> depts, CustomPage<Member> members)
        {
            Depts = depts;
            Members = members;
        }

        public DepartmentData()
        {

        }

        public List<Department> Depts { get => depts; set => depts = value; }
        public CustomPage<Member> Members { get => members; set => members = value; }
    }
}
