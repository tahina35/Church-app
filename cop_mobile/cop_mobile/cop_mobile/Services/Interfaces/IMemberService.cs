using cop_mobile.Model;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace cop_mobile.Services.Interfaces
{
    public interface IMemberService
    {
        [Get("/api/member/username/{username}")]
        Task<Member> GetMember(string username);

        [Get("/api/member/department/{id}/page/{pageNo}")]
        Task<CustomPage<Member>> GetMemberByDept(long id, int pageNo);
    }
}
