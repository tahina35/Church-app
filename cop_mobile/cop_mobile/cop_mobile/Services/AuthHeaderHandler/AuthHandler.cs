using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Xamarin.Essentials;

namespace cop_mobile.Services.AuthHeaderHandler
{
    public class AuthHandler : DelegatingHandler
    {
        public AuthHandler(HttpMessageHandler innerHadler) : base(innerHadler)
        {
        }

        public AuthHandler()
        {
        }

        protected override async Task<HttpResponseMessage> SendAsync(HttpRequestMessage request, CancellationToken cancellationToken)
        {
            string token = await SecureStorage.GetAsync("token");

            //potentially refresh token here if it has expired etc.

            if(!String.IsNullOrEmpty(token))
            {
                request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", token);
            }

            return await base.SendAsync(request, cancellationToken).ConfigureAwait(false);
        }
    }
}
