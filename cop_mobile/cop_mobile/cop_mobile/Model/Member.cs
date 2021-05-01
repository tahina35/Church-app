using System;
using System.Collections.Generic;
using System.Text;

namespace cop_mobile.Model
{
    public class Member
    {
        public long memberId { get; set; }
        public string fname { get; set; }
        public string lname { get; set; }
        public string gender { get; set; }
        public string memberDate { get; set; }
        public bool active { get; set; }
        public string streetAddressLine1 { get; set; }
        public string streetAddressLine2 { get; set; }
        public string city { get; set; }
        public string state { get; set; }
        public int zipCode { get; set; }
        public string email { get; set; }
        public string phoneNumber { get; set; }
        public bool admin { get; set; }
        public string password { get; set; }
        public object accessToken { get; set; }
        public bool enabled { get; set; }
        public string username { get; set; }
        public List<object> authorities { get; set; }
        public bool accountNonExpired { get; set; }
        public bool credentialsNonExpired { get; set; }
        public bool accountNonLocked { get; set; }
        public string fullName => fname + ' ' + lname;
        public string phone => this.formatPhoneNumber();
        public string fullAddress => this.getFullAddress();

        public Member(long memberId, string fname, string lname, string gender, string memberDate, bool active, string streetAddressLine1, string streetAddressLine2, string city, string state, int zipCode, string email, string phoneNumber, bool admin, string password, object accessToken, bool enabled, string username, bool credentialsNonExpired, bool accountNonExpired, List<object> authorities, bool accountNonLocked)
        {
            this.memberId = memberId;
            this.fname = fname;
            this.lname = lname;
            this.gender = gender;
            this.memberDate = memberDate;
            this.active = active;
            this.streetAddressLine1 = streetAddressLine1;
            this.streetAddressLine2 = streetAddressLine2;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.admin = admin;
            this.password = password;
            this.accessToken = accessToken;
            this.enabled = enabled;
            this.username = username;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonExpired = accountNonExpired;
            this.authorities = authorities;
            this.accountNonLocked = accountNonLocked;
        }

        public Member()
        {

        }

        public string getFullAddress()
        {
            return streetAddressLine1 + ' ' + streetAddressLine2 + ", " + city + ", " + state + ' ' + zipCode;
        }

        public string formatPhoneNumber()
        {
            char[] charArray = phoneNumber.ToCharArray();
            int index = 0;
            char[] res = new char[14];
            res[0] = '(';
            res[4] = ')';
            res[5] = ' ';
            res[9] = '-';
            for (int i = 0; i<14; i++)
            {
                if(i == 0 || i == 4 || i == 5 || i == 9)
                {
                    continue;
                }

                res[i] = charArray[index];
                index++;
            }

            return new string(res);
        }
    }
}
