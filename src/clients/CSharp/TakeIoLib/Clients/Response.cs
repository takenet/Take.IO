using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace TakeIoLib.Clients
{
    public class Response<T>
    {
        public HttpStatusCode Status { get; set; }
        public string Description { get; set; }
        public T Content { get; set; }
    }
}
