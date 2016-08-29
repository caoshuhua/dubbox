using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using com.mbv.open.api.sdk;
using com.mbv.open.api.sdk.response.workorder;
using com.mbv.open.api.sdk.request.workorder;
using com.mbv.open.api.sdk.model.workorder;

namespace open_api_test
{
    public class WorkOrderTest
    {

        public static WorkOrderBatchQueryResponse BatchGet(MbClient client)
        {
            WorkOrderBatchQueryRequest request = new WorkOrderBatchQueryRequest();

            //request.PageNo = "1";
            request.PageSize = "5";

            return client.Execute<WorkOrderBatchQueryResponse>(request);
        }

        public static WorkOrderAddResponse Add(MbClient client)
        {
            WorkOrderAddRequest request = new WorkOrderAddRequest();

            //request.WorkorderType = "0";
            //request.MbOrderId = "140507009032";
            //request.OrderId = "000032282";
            //request.Remark = "C# SDK 测试";

            //000032298","mb_order_id":"140507009045

            request.WorkorderType = "0";
            request.MbOrderId = "140506998996";
            request.OrderId = "000032275";
            request.Remark = "C# SDK 测试";

            List<OosItem> items = new List<OosItem>();

            items.Add(new OosItem
            {
                SkuId = "50967790148",
                Quantity = "1"
            });

            request.OosItems = items;

            return client.Execute<WorkOrderAddResponse>(request);
        }

        public static WorkOrderSingleQueryResponse SingleGet(MbClient client)
        {
            WorkOrderSingleQueryRequest request = new WorkOrderSingleQueryRequest();

            request.WorkorderId = "00000366";

            return client.Execute<WorkOrderSingleQueryResponse>(request);
        }

        public static WorkOrderConfirmResponse Confirm(MbClient client)
        {
            WorkOrderConfirmRequest request = new WorkOrderConfirmRequest();

            request.WorkorderType = "0";
            request.MbOrderId = "140506998996";
            request.OrderId = "000032275";
            request.Remark = "C# SDK 测试";
            request.WorkorderId = "00000367";

            List<OosItem> items = new List<OosItem>();

            items.Add(new OosItem
            {
                SkuId = "50967790148",
                Quantity = "1"
            });

            request.OosItems = items;

            return client.Execute<WorkOrderConfirmResponse>(request);
        }

        public static WorkOrderCancelResponse Cancel(MbClient client)
        {
            WorkOrderCancelRequest request = new WorkOrderCancelRequest();

            request.WorkorderId = "00000366";

            return client.Execute<WorkOrderCancelResponse>(request);
        }

    }
}
