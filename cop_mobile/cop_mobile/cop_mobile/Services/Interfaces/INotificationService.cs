using cop_mobile.Model;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace cop_mobile.Services.Interfaces
{
    public interface INotificationService
    {
        [Get("/api/notification")]
        Task<List<Notification>> GetNotifications();
    }
}
