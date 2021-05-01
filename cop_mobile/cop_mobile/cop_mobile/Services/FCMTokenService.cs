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

[assembly: Xamarin.Forms.Dependency(typeof(FCMTokenService))]
namespace cop_mobile.Services
{
    public class FCMTokenService : BaseService
    {
        private readonly IFCMTokenService IFCMTokenService;

        public FCMTokenService() : base()
        {
            IFCMTokenService = RestService.For<IFCMTokenService>(this._baseURL);
        }

        public async Task<FCMToken> save(FCMToken token)
        {
            try
            {
                return await IFCMTokenService.save(token);
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
