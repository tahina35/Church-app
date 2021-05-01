using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.Services.Interfaces;
using Newtonsoft.Json;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Essentials;

[assembly: Xamarin.Forms.Dependency(typeof(LoginService))]
namespace cop_mobile.Services
{
    public class LoginService : BaseService
    {
        private readonly ILoginService IloginService;

        public LoginService() : base()
        {
            IloginService = RestService.For<ILoginService>(this._baseURL);
        }

        public async Task login(Credentials credentials)
        {
            var response = await IloginService.login(credentials);
            if (response.IsSuccessStatusCode)
            {
                string token = string.Join(",", response.Headers.GetValues("Authorization"));
                token = token.Split(' ')[1];
                await SecureStorage.SetAsync("isLogged", "1");
                await SecureStorage.SetAsync("token", token);
                await SecureStorage.SetAsync("credentials", JsonConvert.SerializeObject(credentials));
            }
            else
            {
                ErrorResponse error = JsonConvert.DeserializeObject<ErrorResponse>(response.Error.Content);
                throw new Exception(error.message);
            }
        }

        public async Task<Member> resetPassword(Member member)
        {
            try
            {
                return await IloginService.resetPassword(member);
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

        public async Task<Member> forgotPassword(string username)
        {
            try
            {
                return await IloginService.forgotPassword(username);
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
