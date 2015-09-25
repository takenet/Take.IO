using RestSharp;
using RestSharp.Authenticators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TakeIoLib.Clients
{
    public class RequestClient
    {
        private RestClient _httpClient;
        public MessagesClient Messages { get; set; }

        public RequestClient(string consumerKey, string consumerSecret, string requestToken, string requestTokenSecret)
        {    
            _httpClient = new RestClient("http://api.take.io/rest/1.0");
            _httpClient.Authenticator = OAuth1Authenticator.ForAccessToken(consumerKey, consumerSecret, requestToken, requestTokenSecret);

            Messages = new MessagesClient(_httpClient);
        }
    }
}

