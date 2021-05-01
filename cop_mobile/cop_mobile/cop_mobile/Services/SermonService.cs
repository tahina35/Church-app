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

[assembly: Xamarin.Forms.Dependency(typeof(SermonService))]
namespace cop_mobile.Services
{
    public class SermonService : BaseService
    {
        private readonly ISermonService ISermonService;

        public SermonService() : base()
        {
            ISermonService = RestService.For<ISermonService>(this._httpClient);
        }

        public async Task<CustomPage<SermonData>> getEnglishSermon(int page)
        {
            try
            {
                return await ISermonService.GetEnglishSermon(page);
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

        public async Task<CustomPage<SermonData>> getKoreanSermon(int page)
        {
            try
            {
                return await ISermonService.GetKoreanSermon(page);
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

        public async Task<CustomPage<SermonData>> getWednesdaySermon(int page)
        {
            try
            {
                return await ISermonService.GetWednesdaySermon(page);
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

        public async Task<CustomPage<SermonData>> getYouthSermon(int page)
        {
            try
            {
                return await ISermonService.GetYouthSermon(page);
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

        public async Task<CustomPage<SermonData>> getMorningSermon(int page)
        {
            try
            {
                return await ISermonService.GetMorningSermon(page);
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
