using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TakeIoLib.Entities
{
    using System;
    using System.Collections.Generic;

    public class Message
    {
        public virtual Guid Id { get; set; }
        public virtual Guid? Schedule { get; set; }
        public virtual Guid? Owner { get; set; }
        public virtual string Sender { get; set; }
        public virtual IList<RecipientsResource> Recipients { get; set; }
        public virtual IList<ContactGroup> ContactGroups { get; set; }
        public virtual string Body { get; set; }
        public virtual string Subject { get; set; }
        public virtual DateTime? Time { get; set; }
        public virtual Guid? Wallet { get; set; }
        public virtual bool? Urgent { get; set; }
        public virtual string AckUri { get; set; }
        public virtual string ReplyUri { get; set; }
        public virtual string Ref { get; set; }
        public virtual string Folder { get; set; }
        public virtual string Status { get; set; }
        public virtual string Category { get; set; }
        public virtual string Type { get; set; }
        public virtual int? Validity { get; set; }
        public virtual DateTime? Created { get; set; }
        public virtual DateTime? Updated { get; set; }
        public virtual string AudioBase64 { get; set; }
        public virtual bool? Async { get; set; }
        public virtual string SpecificId { get; set; }
        public virtual Guid IdDomain { get; set; }
        public virtual string LargeAccount { get; set; }
        public virtual bool? Read { get; set; }

        public Message()
        {
            Recipients = new List<RecipientsResource>();
            ContactGroups = new List<ContactGroup>();
        }

        public Message(string content)
        {

        }

        public struct RecipientsResource
        {
            public string Value { get; set; }
            public string Status { get; set; }
            public DateTime? Updated { get; set; }
            public string Carrier { get; set; }
            public int? Duration { get; set; }
            public float? Price { get; set; }
            public int Attempts { get; set; }
            public DateTime? Time { get; set; }
        }

        public Message Clone()
        {
            return new Message
            {
                Id = Id,
                Schedule = Schedule,
                Owner = Owner,
                Sender = Sender,
                Recipients = new List<RecipientsResource>(Recipients),
                ContactGroups = new List<ContactGroup>(ContactGroups),
                Body = Body,
                Subject = Subject,
                Time = Time,
                Wallet = Wallet,
                Urgent = Urgent,
                AckUri = AckUri,
                ReplyUri = ReplyUri,
                Ref = Ref,
                Folder = Folder,
                Status = Status,
                Type = Type,
                Category = Category,
                Created = Created,
                Updated = Updated,
                Async = Async,
                SpecificId = SpecificId,
                IdDomain = IdDomain,
                AudioBase64 = AudioBase64,
                LargeAccount = LargeAccount,
                Read = Read
            };
        }
    }

}
