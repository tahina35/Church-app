using cop_mobile.Model;
using Refit;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace cop_mobile.Services.Interfaces
{
    public interface ILoginService
    {
        [Post("/login")]
        Task<ApiResponse<string>> login([Body] Credentials credentials);

        [Post("/api/auth/reset-password")]
        Task<Member> resetPassword([Body] Member member);

        [Get("/api/auth/forgot-password/{username}")]
        Task<Member> forgotPassword(string username);


    }
}
