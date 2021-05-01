using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.Services.AuthHeaderHandler;
using cop_mobile.Services.Interfaces;
using Refit;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

[assembly: Xamarin.Forms.Dependency(typeof(MemberService))]
namespace cop_mobile.Services
{
    public class MemberService : BaseService
    {
        private readonly IMemberService IMemberService;

        public MemberService() : base()
        {
            IMemberService = RestService.For<IMemberService>(this._httpClient);
        }

        public async Task<Member> LoadByUsername(string username)
        {
            try
            {
                return await IMemberService.GetMember(username);
            }
            catch(ApiException e)
            {
                throw new Exception(await HandleApiException(e));
            }
            catch(Exception e)
            {
                throw new Exception(e.Message);
            }
        }

        public async Task<CustomPage<Member>> getByDept(long deptId, int page)
        {
            try
            {
                return await IMemberService.GetMemberByDept(deptId, page);
            }
            catch (ApiException e)
            {
                throw new Exception(await HandleApiException(e));
            }
            catch (Exception e)
            {
                throw new Exception(e.Message);
            }
        }
    }
}
