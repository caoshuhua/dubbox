using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using com.mbv.open.api.sdk;

namespace open_api_test
{
    class Program
    {
        static void Main(string[] args)
        {
            MbClient client = new DefaultMbClient("https://open.metersbonwe.com/sandbox", "abcd", "1234");

            //var result = WorkOrderTest.BatchGet(client);

            //var singleResult = WorkOrderTest.SingleGet(client);

            //var confirmResult = WorkOrderTest.Confirm(client);

            //var cancelResult = WorkOrderTest.Cancel(client);

            //var addResult = WorkOrderTest.Add(client);

            var orderBatchResult = DegTest.BatchGet(client);

        }
    }
}
