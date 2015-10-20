using RestSharp;
using System;
using TakeIoLib.Entities;

namespace TakeIoLib.Clients
{
    public interface IRestOperations<T>
    {
        Response<Collection<T>> Get(RestRequest request);
        Response<Item<T>> Get(RestRequest request, string guid);
        Response<Uri> Post(RestRequest request);
        Response<Item<T>> Put(RestRequest request, string guid);
    }
}