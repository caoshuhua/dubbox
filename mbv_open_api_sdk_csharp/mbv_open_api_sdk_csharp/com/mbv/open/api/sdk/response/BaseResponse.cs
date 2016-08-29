using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace com.mbv.open.api.sdk.response
{
    public class BaseResponse
    {

        public string Url { get; set; }

        [JsonProperty("resp_code")]
        public string RespCode { get; set; }

        [JsonProperty("resp_info")]
        public string RespInfo { get; set; }

    }
}
