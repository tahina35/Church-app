using cop_mobile.Model;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace cop_mobile.Services.Interfaces
{
    public interface IFCMTokenService
    {
        [Post("/api/fcmtoken")]
        Task<FCMToken> save([Body] FCMToken token);
    }
}
