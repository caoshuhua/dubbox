using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using com.mbv.open.api.sdk.model.order;
using com.mbv.open.api.sdk.request.order;
using com.mbv.open.api.sdk.response.order;
using com.mbv.open.api.sdk;

namespace open_api_test
{
    public class DegTest
    {
        public static GetBatchOrderResponse BatchGet(MbClient client)
        {
            GetBatchOrderRequest request = new GetBatchOrderRequest();

            return client.Execute<GetBatchOrderResponse>(request);
        }
    }
}
