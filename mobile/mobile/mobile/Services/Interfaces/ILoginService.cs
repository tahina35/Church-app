using mobile.Model;
using Refit;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace mobile.Services.Interfaces
{
    public interface ILoginService
    {
        [Post("/login")]
        Task login([Body] Credentials credentials);
    }
}
