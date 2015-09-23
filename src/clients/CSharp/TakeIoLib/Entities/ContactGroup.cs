using System;

namespace TakeIoLib.Entities
{
    public class ContactGroup
    {
        public virtual Guid Id { get; set; }
        public virtual string Name { get; set; }
        public virtual Guid Owner { get; set; }
    }
}