using RestSharp;
using RestSharp.Authenticators;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TakeIoLib.Clients
{
    public class RequestClient
    {
        public MessagesClient Messages { get; set; }

        private NameValueCollection _appSettings;
        private RestClient _httpClient;

        public RequestClient(string consumerKey, string consumerSecret, string requestToken, string requestTokenSecret)
        {
            this._appSettings = ConfigurationManager.AppSettings;

            var version = _appSettings["TakeIoApiVersion"] ?? "1.0";
            var host = _appSettings["TakeIoApiHost"] ?? "api.take.io";
            var protocol = _appSettings["TakeIoApiProtocol"] ?? "https";

            var uri = $"{protocol}://{host}/rest/{version}";

            _httpClient = new RestClient(uri);
            _httpClient.Authenticator = OAuth1Authenticator.ForAccessToken(consumerKey, consumerSecret, requestToken, requestTokenSecret);

            Messages = new MessagesClient(_httpClient);
        }
    }
}

