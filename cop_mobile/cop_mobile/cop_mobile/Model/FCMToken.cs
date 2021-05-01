using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class FCMToken
    {
        public long fcmTokenId { get; set; }
        public Member member { get; set; }
        public string token { get; set; }
        public string deviceId { get; set; }

        public FCMToken(long fcmTokenId, Member member, string deviceId, string token)
        {
            this.fcmTokenId = fcmTokenId;
            this.member = member;
            this.deviceId = deviceId;
            this.token = token;
        }

        public FCMToken(Member member, string deviceId, string token)
        {
            this.member = member;
            this.deviceId = deviceId;
            this.token = token;
        }

        public FCMToken()
        {
        }
    }
}
