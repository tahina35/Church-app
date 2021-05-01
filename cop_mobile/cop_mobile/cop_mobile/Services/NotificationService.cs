using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.Services.Interfaces;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

[assembly: Xamarin.Forms.Dependency(typeof(NotificationService))]
namespace cop_mobile.Services
{
    public class NotificationService : BaseService
    {
        private readonly INotificationService INotificationService;

        public NotificationService() : base()
        {
            INotificationService = RestService.For<INotificationService>(this._httpClient);
        }

        public async Task<List<Notification>> GetNotifications()
        {
            try
            {
                return await INotificationService.GetNotifications();
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
