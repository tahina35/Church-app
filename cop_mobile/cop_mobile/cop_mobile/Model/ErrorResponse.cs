using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class ErrorResponse
    {
        public int code { get; set; }
        public string type { get; set; }
        public string message { get; set; }

        public ErrorResponse(int code, string type, string message)
        {
            this.code = code;
            this.type = type;
            this.message = message;
        }
    }
}
