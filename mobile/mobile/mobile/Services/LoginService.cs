using mobile.Model;
using mobile.Services;
using mobile.Services.Interfaces;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

[assembly: Xamarin.Forms.Dependency(typeof(LoginService))]
namespace mobile.Services
{
    public class LoginService : BaseService
    {
        private readonly ILoginService IloginService;

        public LoginService() : base()
        {
            IloginService = RestService.For<ILoginService>(this._baseUrl);
        }

        public async Task login(Credentials credentials)
        {
            await IloginService.login(credentials).ConfigureAwait(false);
        }
    }
}
