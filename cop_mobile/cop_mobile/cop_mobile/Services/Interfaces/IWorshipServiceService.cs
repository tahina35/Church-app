using cop_mobile.Model;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace cop_mobile.Services.Interfaces
{
    public interface IWorshipServiceService
    {
        [Get("/api/worship/sunday-service/last")]
        Task<SundayServiceData> GetSundayService();

        [Get("/api/worship/wednesday-service/last")]
        Task<WednesdayService> GetWednesdayService();

        [Get("/api/worship/youth-service/last")]
        Task<YouthService> GetYouthService();

        [Get("/api/worship/morning-service/last5")]
        Task<List<EarlyMorningService>> GetEarlyMorningServices();

    }
}
