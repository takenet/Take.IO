using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TakeIoLib.Clients;
using System.Net;
using TakeIoLib.Entities.QueryHelpers;
using TakeIoLib.Entities;
using System.Collections.Generic;

namespace TakeIoLibTest.Clients
{
    [TestClass]
    public class MessagesClientTest
    {
        private RequestClient client;

        [TestInitialize]
        public void TestInitialize()
        {
            client = new RequestClient("t3KJh2b5", "fcPJ3TLi",
                "RGbK6mR4rX9YQATm55ug9ytEsps=", "P5GBzPseaxFQycA/1i8Kfk9yqgA=");
        }

        [TestMethod]
        public void ListMessages()
        {
            var parameters = new MessageParameters();
            parameters.AddFilter("folder", "sent");

            var response = client.Messages.ListMessages(parameters);
            Assert.AreEqual(response.Status, HttpStatusCode.OK);
        }

        [TestMethod]
        public void GetMessage()
        {
            var response = client.Messages.GetMessage("f8db41a5-c32f-4e6a-bb83-eb23b73489e6");
            Assert.AreEqual(response.Status, HttpStatusCode.OK);
        }

        [TestMethod]
        public void SendMessageFromObject()
        {
            var message = new Message()
            {
                Sender = "tel:+553199888668",
                Body = "Hello, World!",
                Recipients = new List<Message.RecipientsResource>
                {
                    new Message.RecipientsResource()
                    {
                        Value = "tel:+553199888668"
                    }
                }
            };

            var response = client.Messages.SendMessage(message);
            Assert.AreEqual(response.Status, HttpStatusCode.Created);
        }

        [TestMethod]
        public void SendMessageFromParameters()
        {
            var recipients = new List<Message.RecipientsResource>
            {
                new Message.RecipientsResource()
                {
                    Value = "tel:+553199888668"
                }
            };
            var response = client.Messages.SendMessage(recipients, "tel:+553199888668", "Hello, World!");
            Assert.AreEqual(response.Status, HttpStatusCode.Created);
        }

        [TestMethod]
        public void SendSchedulledMessageFromObject()
        {
            var message = new Message()
            {
                Sender = "tel:+553199888668",
                Body = "Hello, World!",
                Recipients = new List<Message.RecipientsResource>
                {
                    new Message.RecipientsResource()
                    {
                        Value = "tel:+553199888668"
                    }
                }
            };

            var response = client.Messages.SendSchedulledMessage(message, new DateTime(2015, 9, 24, 17, 6, 0));
            Assert.AreEqual(response.Status, HttpStatusCode.Created);
        }

        [TestMethod]
        public void SendSchedulledMessageFromParameters()
        {
            var recipients = new List<Message.RecipientsResource>
            {
                new Message.RecipientsResource()
                {
                    Value = "tel:+553199888668"
                }
            };

            var response = client.Messages.SendSchedulledMessage(recipients, "tel:+553199988668", 
                "Hello, World!", new DateTime(2015, 9, 24, 17, 6, 0));
            Assert.AreEqual(response.Status, HttpStatusCode.Created);
        }

        [TestMethod]
        public void UpdateMessage()
        {
            var message = new Message()
            {
                Urgent = true
            };

            var response = client.Messages.UpdateMessage(message, "f8db41a5-c32f-4e6a-bb83-eb23b73489e6");
            Assert.AreEqual(response.Status, HttpStatusCode.OK);
        }
    }
}
