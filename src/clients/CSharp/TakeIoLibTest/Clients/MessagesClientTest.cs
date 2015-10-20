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
            client = new RequestClient("CONSUMER_KEY", "CONSUMER_SECRET",
                "REQUEST_TOKEN", "REQUEST_TOKEN_SECRET");
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
            var response = client.Messages.GetMessage("MESSAGE_GUID");
            Assert.AreEqual(response.Status, HttpStatusCode.OK);
        }

        [TestMethod]
        public void SendMessageFromObject()
        {
            var message = new Message()
            {
                Sender = "tel:+5531XXXXXXXX",
                Body = "Hello, World!",
                Recipients = new List<Message.RecipientsResource>
                {
                    new Message.RecipientsResource()
                    {
                        Value = "tel:+5531XXXXXXXX"
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
                    Value = "tel:+5531XXXXXXXX"
                }
            };
            var response = client.Messages.SendMessage(recipients, "tel:+5531XXXXXXXX", "Hello, World!");
            Assert.AreEqual(response.Status, HttpStatusCode.Created);
        }

        [TestMethod]
        public void SendSchedulledMessageFromObject()
        {
            var message = new Message()
            {
                Sender = "tel:+5531XXXXXXXX",
                Body = "Hello, World!",
                Recipients = new List<Message.RecipientsResource>
                {
                    new Message.RecipientsResource()
                    {
                        Value = "tel:+5531XXXXXXXX"
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
                    Value = "tel:+5531XXXXXXXX"
                }
            };

            var response = client.Messages.SendSchedulledMessage(recipients, "tel:+5531XXXXXXXX", 
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

            var response = client.Messages.UpdateMessage(message, "MESSAGE_GUID");
            Assert.AreEqual(response.Status, HttpStatusCode.OK);
        }
    }
}
