using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.Services.Interfaces;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

[assembly: Xamarin.Forms.Dependency(typeof(NewsService))]
namespace cop_mobile.Services
{
    public class NewsService : BaseService
    {
        private readonly INewsService INewsService;

        public NewsService() : base()
        {
            INewsService = RestService.For<INewsService>(this._httpClient);
        }

        public async Task<List<News>> GetNews()
        {
            try
            {
                return await INewsService.GetNews();
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
