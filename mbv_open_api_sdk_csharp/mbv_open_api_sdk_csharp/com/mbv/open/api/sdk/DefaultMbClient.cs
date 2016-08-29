using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using com.mbv.open.api.sdk.request;
using com.mbv.open.api.sdk.response;
using com.mbv.open.api.sdk.util;

namespace com.mbv.open.api.sdk
{
    public class DefaultMbClient : MbClient
    {
        public const string CHARSET_UTF8 = "UTF-8";

        private String serverUrl;
        private String token;
        private int connectTimeout = 0;
        private int readTimeout = 0;
        private String appKey;

        public DefaultMbClient(String serverUrl, String token, String appKey)
        {
            this.serverUrl = serverUrl;
            this.token = token;
            this.appKey = appKey;
        }

        public DefaultMbClient(String serverUrl, String token, String appKey,
                int connectTimeout, int readTimeout)
        {
            this.serverUrl = serverUrl;
            this.token = token;
            this.appKey = appKey;
            this.connectTimeout = connectTimeout;
            this.readTimeout = readTimeout;
        }

        public T Execute<T>(BaseRequest<T> request) where T : BaseResponse
        {
            try
            {
                string url = buildUrl<T>(request);

                string jsonResult = HttpHelper.SendPost(url, "");

                T resp = Newtonsoft.Json.JsonConvert.DeserializeObject<T>(jsonResult);

                return resp;
            }
            catch (Exception e)
            {
                throw new Exception("server error!");
            }
        }

        private string buildUrl<T>(BaseRequest<T> request) where T : BaseResponse
        {
            Dictionary<string, string> sysParams = request.GetSysParams();

            sysParams.Add("token", this.token);
            sysParams.Add("appkey", this.appKey);
            string strAppParams = UTF8Encoding.UTF8.GetString(UTF8Encoding.UTF8.GetBytes(request.GetAppParams()));
            sysParams.Add("params", strAppParams);

            StringBuilder sb = new StringBuilder(this.serverUrl);

            sb.Append("/rest/").Append(request.GetApiMethod());

            sb.Append("?");
            sb.Append(HttpHelper.BuildQuery(sysParams));
            return sb.ToString();
        }

    }
}
