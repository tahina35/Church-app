using cop_mobile.Model;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace cop_mobile.Services.Interfaces
{
    public interface ISermonService
    {
        [Get("/api/sermon/english/page/{pageNo}")]
        Task<CustomPage<SermonData>> GetEnglishSermon(int pageNo);

        [Get("/api/sermon/korean/page/{pageNo}")]
        Task<CustomPage<SermonData>> GetKoreanSermon(int pageNo);

        [Get("/api/sermon/wednesday/page/{pageNo}")]
        Task<CustomPage<SermonData>> GetWednesdaySermon(int pageNo);

        [Get("/api/sermon/youth/page/{pageNo}")]
        Task<CustomPage<SermonData>> GetYouthSermon(int pageNo);

        [Get("/api/sermon/morning/page/{pageNo}")]
        Task<CustomPage<SermonData>> GetMorningSermon(int pageNo);

    }
}
