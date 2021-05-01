using cop_mobile.Model;
using cop_mobile.Services;
using cop_mobile.Services.AuthHeaderHandler;
using cop_mobile.Services.Interfaces;
using Refit;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

[assembly: Xamarin.Forms.Dependency(typeof(DepartmentService))]
namespace cop_mobile.Services
{
    public class DepartmentService : BaseService
    {
        private readonly IDepartmentService IDepartmentService;

        public DepartmentService() : base()
        {
            IDepartmentService = RestService.For<IDepartmentService>(this._httpClient);
        }

        public async Task<DepartmentData> GetDepartmentData(long id)
        {
            try
            {
                return await IDepartmentService.GetDepartmentData(id);
            }
            catch (ApiException e)
            {
                throw new Exception( await HandleApiException(e));
            }
            catch (Exception e)
            {
                throw new Exception(e.Message);
            }
        }
    }
}
