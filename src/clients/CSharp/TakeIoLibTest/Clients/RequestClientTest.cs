using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TakeIoLib.Clients;
using System.Net;
using TakeIoLib.Entities.QueryHelpers;

namespace TakeIoLibTest.Clients
{
    [TestClass]
    public class RequestClientTest
    {
        [TestMethod]
        public void ListMessages()
        {
            var client = new RequestClient("yaj4kaeR", "Eif4ood7", "WILvEl2PMccErm1PItDkfZUSDZI=", "iZLftDkQItFxPmEjDMCWjQWNqdM=");

            var parameters = new MessageParameters();
            parameters.AddFilter("folder", "sent");

            var response = client.Messages.ListMessages(parameters);
            Assert.AreEqual(response.Status, HttpStatusCode.OK);
        }
    }
}
