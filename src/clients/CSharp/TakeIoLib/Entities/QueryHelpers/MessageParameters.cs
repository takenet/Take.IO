using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TakeIoLib.Entities.QueryHelpers
{
    public class MessageParameters
    {
        public int? Count { get; set; }
        public int? StartIndex { get; set; }
        public List<string> FilterBy { get; set; }
        public List<string> FilterValue { get; set; }
        public List<string> FilterOp { get; set; }
        public List<string> OrderBy { get; set; }
        public List<string> OrderOp { get; set; }

        public MessageParameters()
        {
            FilterBy = new List<string>();
            FilterValue = new List<string>();
            FilterOp = new List<string>();
            OrderBy = new List<string>();
            OrderOp = new List<string>();
        }

        public void AddFilter(string by, string value, string op = "equal")
        {
            by = by.StartsWith("@") ? by : "@" + by;

            FilterBy.Add(by);
            FilterOp.Add(op);
            FilterValue.Add(value);
        }

        public void AddOrder(string by, string op = "ASC")
        {
            by = by.StartsWith("@") ? by : "@" + by;

            OrderBy.Add(by);
            OrderOp.Add(op);
        }
    }
}
