using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using com.mbv.open.api.sdk.response;

namespace com.mbv.open.api.sdk.request
{
    public abstract class BaseRequest<T> where T : BaseResponse
    {

        protected string timestamp = System.DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
        protected string version = "2.0";
        protected string method;

        public Dictionary<string, string> GetSysParams()
        {
            Dictionary<string, string> sysParams = new Dictionary<string, string>();
            sysParams.Add("method", this.method);
            sysParams.Add("timestamp", this.timestamp);
            sysParams.Add("v", this.version);

            return sysParams;
        }

        protected string VenderId { get; set; }

        protected string Method { get { return method; } set { method = value; } }

        public string Timestamp
        {
            get
            {
                if (string.IsNullOrEmpty(timestamp))
                    return System.DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                return timestamp;
            }
            set { timestamp = value; }
        }

        protected string Version
        {
            get
            {
                if (string.IsNullOrEmpty(version))
                    return "2.0";
                return this.version;
            }
            set { version = value; }
        }

        protected string Sign { get; set; }

        public abstract string GetApiMethod();

        public abstract string GetAppParams();
    }
}
