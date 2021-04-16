using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;

namespace mobile.Services
{
    public class BaseService
    {
        public readonly HttpClient _httpClient;
        public string _baseUrl { get; set; }

        public BaseService()
        {
            _httpClient = new HttpClient();
            _baseUrl = "http://localhost:8080";
        }
    }
}
