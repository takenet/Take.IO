using System;
using RestSharp;
using System.Net;
using TakeIoLib.Clients;
using TakeIoLib.Entities;
using System.Collections.Generic;
using Newtonsoft.Json;
using TakeIoLib.Entities.QueryHelpers;

namespace TakeIoLib.Clients
{
    public class MessagesClient
    {
        public const string RESOURCE = "messages";
        private RestClient _httpClient;

        public MessagesClient(RestClient _httpClient)
        {
            this._httpClient = _httpClient;
        }

        public Response<Collection<Message>> ListMessages(MessageParameters parameters = null)
        {
            var request = new RestRequest(RESOURCE, Method.GET);

            HandleQueryParameters(parameters, request);

            IRestResponse response = _httpClient.Execute(request);

            var collection = JsonConvert.DeserializeObject<Collection<Message>>(response.Content);

            return new Response<Collection<Message>>()
            {
                Status = response.StatusCode,
                Description = response.StatusDescription,
                Content = collection
            };
        }

        public Response<Item<Message>> GetMessage(MessageParameters parameters = null)
        {
            throw new NotImplementedException();
        }

        public Response<Item<Uri>> SendMessage(Message message)
        {
            throw new NotImplementedException();
        }

        public Response<Item<Uri>> SendMessage(List<Message.RecipientsResource> recipients, string sender, string body)
        {
            throw new NotImplementedException();
        }
        public Response<Item<Uri>> SendMessage(Message message, DateTime datetime)
        {
            throw new NotImplementedException();
        }

        public Response<Item<Uri>> SendMessageSchedulled(List<Message.RecipientsResource> recipients, string sender, string body, DateTime datetime)
        {
            throw new NotImplementedException();
        }

        private static void HandleQueryParameters(MessageParameters parameters, RestRequest request)
        {
            if (parameters != null)
            {
                var params_list = new List<string>();

                if (parameters.Count != null)
                {
                    params_list.Add($"count={parameters.Count}");
                }

                if (parameters.StartIndex != null)
                {
                    params_list.Add($"startIndex={parameters.StartIndex}");
                }

                if (parameters.FilterBy.Count != 0)
                {
                    var filterBy = $"[{string.Join(",", parameters.FilterBy.ToArray())}]";
                    params_list.Add($"filterBy={filterBy}");
                }

                if (parameters.FilterValue.Count != 0)
                {
                    var filterValue = $"[{string.Join(",", parameters.FilterValue.ToArray())}]";
                    params_list.Add($"filterValue={filterValue}");
                }

                if (parameters.FilterOp.Count != 0)
                {
                    var filterOp = $"[{string.Join(",", parameters.FilterOp.ToArray())}]";
                    params_list.Add($"filterOp={filterOp}");
                }

                if (parameters.OrderBy.Count != 0)
                {
                    var orderBy = $"[{string.Join(",", parameters.OrderBy.ToArray())}]";
                    params_list.Add($"orderBy={orderBy}");
                }

                if (parameters.OrderOp.Count != 0)
                {
                    var orderOp = $"[{string.Join(",", parameters.OrderOp.ToArray())}]";
                    params_list.Add($"orderOp={orderOp}");                    
                }

                request.Resource += $"?{string.Join("&", params_list.ToArray())}";
            }
        }

        //void IRestOperations.Post(RestRequest request)
        //{
        //    request.Resource = RESOURCE;
        //    request.Method = Method.POST;
        //    throw new NotImplementedException();
        //}        
    }
}