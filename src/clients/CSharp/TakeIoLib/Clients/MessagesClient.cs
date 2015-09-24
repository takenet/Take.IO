using System;
using RestSharp;
using System.Net;
using TakeIoLib.Clients;
using TakeIoLib.Entities;
using System.Collections.Generic;
using Newtonsoft.Json;
using TakeIoLib.Entities.QueryHelpers;
using Newtonsoft.Json.Serialization;
using Newtonsoft.Json.Linq;

namespace TakeIoLib.Clients
{
    public class MessagesClient : IRestOperations<Message>
    {
        public const string RESOURCE = "messages";
        private RestClient _httpClient;

        public MessagesClient(RestClient _httpClient)
        {
            this._httpClient = _httpClient;
        }

        public Response<Collection<Message>> ListMessages(MessageParameters parameters = null)
        {
            var request = new RestRequest();

            HandleQueryParameters(parameters, request);

            return Get(request);
        }

        public Response<Item<Message>> GetMessage(string guid)
        {
            var request = new RestRequest();

            return Get(request, guid);
        }

        public Response<Uri> SendMessage(Message message)
        {
            var request = new RestRequest();

            message.Type = "sms";

            var item = new Item<Message>();
            item.Entry = message;
            
            var json = JsonConvert.SerializeObject(item,
                Formatting.None,
                new JsonSerializerSettings {
                    NullValueHandling = NullValueHandling.Ignore,
                    ContractResolver = new CamelCasePropertyNamesContractResolver()
                }
            );

            request.AddParameter("text/json", json, ParameterType.RequestBody);

            return Post(request);
        }

        public Response<Uri> SendMessage(List<Message.RecipientsResource> recipients, string sender, string body)
        {
            var message = new Message()
            {
                Sender = sender,
                Body = body,
                Recipients = recipients
            };

            return SendMessage(message);
        }
        public Response<Uri> SendSchedulledMessage(Message message, DateTime datetime)
        {
            message.Time = datetime;
            return SendMessage(message);
        }

        public Response<Uri> SendSchedulledMessage(List<Message.RecipientsResource> recipients, string sender, string body, DateTime datetime)
        {
            var message = new Message()
            {
                Sender = sender,
                Body = body,
                Recipients = recipients,
                Time = datetime
            };

            return SendMessage(message);
        } 

        public Response<Item<Message>> UpdateMessage(Message message, string guid)
        {
            var request = new RestRequest();

            var item = new Item<Message>();
            item.Entry = message;

            var json = JsonConvert.SerializeObject(item,
                Formatting.None,
                new JsonSerializerSettings
                {
                    NullValueHandling = NullValueHandling.Ignore,
                    ContractResolver = new CamelCasePropertyNamesContractResolver()
                }
            );

            request.AddParameter("text/json", json, ParameterType.RequestBody);

            return Put(request, guid);
        }

        public Response<Collection<Message>> Get(RestRequest request)
        {
            request.Resource = RESOURCE;
            request.Method = Method.GET;

            IRestResponse response = _httpClient.Execute(request);

            var collection = JsonConvert.DeserializeObject<Collection<Message>>(response.Content);

            return new Response<Collection<Message>>()
            {
                Status = response.StatusCode,
                Description = response.StatusDescription,
                Content = collection
            };
        }

        public Response<Item<Message>> Get(RestRequest request, string guid)
        {
            request.Resource = $"{RESOURCE}/{guid}";
            request.Method = Method.GET;

            IRestResponse response = _httpClient.Execute(request);

            var item = JsonConvert.DeserializeObject<Item<Message>>(response.Content);

            return new Response<Item<Message>>()
            {
                Status = response.StatusCode,
                Description = response.StatusDescription,
                Content = item
            };
        }

        public Response<Uri> Post(RestRequest request)
        {
            request.Resource = RESOURCE;
            request.Method = Method.POST;

            IRestResponse response = _httpClient.Execute(request);

            var parsed_response = JObject.Parse(response.Content);
            var location = (string)parsed_response.SelectToken("location");
            var uri = new Uri(location);

            return new Response<Uri>()
            {
                Status = response.StatusCode,
                Description = response.StatusDescription,
                Content = uri
            };
        }

        public Response<Item<Message>> Put(RestRequest request, string guid)
        {
            request.Resource = $"{RESOURCE}/{guid}";
            request.Method = Method.PUT;

            IRestResponse response = _httpClient.Execute(request);

            var item = JsonConvert.DeserializeObject<Item<Message>>(response.Content);

            return new Response<Item<Message>>()
            {
                Status = response.StatusCode,
                Description = response.StatusDescription,
                Content = item
            };
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
    }
}