using cop_mobile.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.ViewModel
{
    public class MemberDetailViewModel
    {
        private Member member;

        public MemberDetailViewModel(Member m)
        {
            Member = m;
        }

        public Member Member { get => member; set => member = value; }
    }
}
