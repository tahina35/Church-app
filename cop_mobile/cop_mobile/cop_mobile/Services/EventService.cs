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

[assembly: Xamarin.Forms.Dependency(typeof(EventService))]
namespace cop_mobile.Services
{
    public class EventService : BaseService
    {
        private readonly IEventService IEventService;

        public EventService() : base()
        {
            IEventService = RestService.For<IEventService>(this._httpClient);
        }

        public async Task<List<Event>> GetEvents()
        {
            try
            {
                return await IEventService.GetEvents();
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
