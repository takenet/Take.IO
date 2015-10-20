using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TakeIoLib.Entities
{
    public class Collection<T>
    {
        public class ResultType<Inner>
        {
            public virtual int? TotalResults { get; set; }
            public virtual int? StartIndex { get; set; }
            public virtual int? ItemsPerPage { get; set; }
            public virtual IList<Inner> Entry { get; set; }
        }

        public virtual ResultType<T> Result { get; set; }
    }
}
