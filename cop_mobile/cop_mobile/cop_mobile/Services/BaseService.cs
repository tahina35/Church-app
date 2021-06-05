using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using cop_mobile;
using cop_mobile.Model;
using cop_mobile.Pages;
using cop_mobile.Services.AuthHeaderHandler;
using cop_mobile.Services.Interfaces;
using cop_mobile.ViewModel;
using mobile.Pages;
using Newtonsoft.Json;
using Refit;
using Xamarin.Essentials;
using Xamarin.Forms;

namespace cop_mobile.Services
{
    public class BaseService
    {
        public readonly HttpClient _httpClient;
        public readonly string _baseURL = "http://18.222.104.153:8081";
        private readonly ILoginService IloginService;

        public BaseService()
        {
            _httpClient = new HttpClient(new AuthHandler(new HttpClientHandler()))
            {
                BaseAddress = new Uri(_baseURL)
            };

            IloginService = RestService.For<ILoginService>(this._baseURL);
        }

        public async Task<string> HandleApiException(ApiException e)
        {
            string ret = "";
            if(!String.IsNullOrEmpty(e.Content))
            {
                ErrorResponse error = JsonConvert.DeserializeObject<ErrorResponse>(e.Content);
                if (error.code == 500)
                {
                    Credentials credentials = JsonConvert.DeserializeObject<Credentials>(await SecureStorage.GetAsync("credentials"));
                    var response = await IloginService.login(credentials);
                    if (response.IsSuccessStatusCode)
                    {
                        string token = string.Join(",", response.Headers.GetValues("Authorization"));
                        token = token.Split(' ')[1];
                        await SecureStorage.SetAsync("token", token);
                        
                    }
                    else
                    {
                        SecureStorage.Remove("token");
                        SecureStorage.Remove("credentials");
                        SecureStorage.Remove("isLogged");
                        credentials = new Credentials();
                    }

                    ret = "Your session expired! Please log in again";
                    Application.Current.MainPage = new NavigationPage(new LoginPage(new LoginViewModel(credentials)));
                   
                }
                else
                {
                    ret = error.message;
                }
            }
            else
            {
                ret = e.Message;
            }
            return ret;
        }

        public static string getRelativeTime(DateTime date)
        {
            Dictionary<long, string> thresholds = new Dictionary<long, string>();
            int minute = 60;
            int hour = 60 * minute;
            int day = 24 * hour;
            thresholds.Add(60, "{0} seconds ago");
            thresholds.Add(minute * 2, "a minute ago");
            thresholds.Add(45 * minute, "{0} minutes ago");
            thresholds.Add(120 * minute, "an hour ago");
            thresholds.Add(day, "{0} hours ago");
            thresholds.Add(day * 2, "yesterday");
            thresholds.Add(day * 30, "{0} days ago");
            thresholds.Add(day * 365, "{0} months ago");
            thresholds.Add(long.MaxValue, "{0} years ago");
            long since = (DateTime.Now.Ticks - date.Ticks) / 10000000;
            foreach (long threshold in thresholds.Keys)
            {
                if (since < threshold)
                {
                    TimeSpan t = new TimeSpan((DateTime.Now.Ticks - date.Ticks));
                    return string.Format(thresholds[threshold], (t.Days > 365 ? t.Days / 365 : (t.Days > 0 ? t.Days : (t.Hours > 0 ? t.Hours : (t.Minutes > 0 ? t.Minutes : (t.Seconds > 0 ? t.Seconds : 0))))).ToString());
                }
            }
            return "";
        }
    }
}
