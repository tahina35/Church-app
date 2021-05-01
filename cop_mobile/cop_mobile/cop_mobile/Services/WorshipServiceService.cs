using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.Services.Interfaces;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

[assembly: Xamarin.Forms.Dependency(typeof(WorshipServiceService))]
namespace cop_mobile.Services
{
    public class WorshipServiceService : BaseService
    {
        private readonly IWorshipServiceService IWorshipServiceService;

        public WorshipServiceService() : base()
        {
            IWorshipServiceService = RestService.For<IWorshipServiceService>(this._httpClient);
        }

        public async Task<SundayServiceData> GetSundayService()
        {
            try
            {
                return await IWorshipServiceService.GetSundayService();
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

        public async Task<WednesdayService> GetWedndesdayService()
        {
            try
            {
                return await IWorshipServiceService.GetWednesdayService();
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

        public async Task<YouthService> GetYouthService()
        {
            try
            {
                return await IWorshipServiceService.GetYouthService();
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

        public async Task<List<EarlyMorningService>> GetEarlyMorningServices()
        {
            try
            {
                return await IWorshipServiceService.GetEarlyMorningServices();
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
