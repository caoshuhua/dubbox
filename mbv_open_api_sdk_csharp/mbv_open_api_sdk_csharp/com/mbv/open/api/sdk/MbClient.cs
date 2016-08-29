using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using com.mbv.open.api.sdk.request;
using com.mbv.open.api.sdk.response;

namespace com.mbv.open.api.sdk
{
    public interface MbClient
    {
        T Execute<T>(BaseRequest<T> request) where T : BaseResponse;
    }
}
