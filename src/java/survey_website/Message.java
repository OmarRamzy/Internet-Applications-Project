package survey_website;

/***********************************************************************
 * Module:  Message.java
 * Author:  abdal
 * Purpose: Defines the Class Message
 ***********************************************************************/

import java.util.*;

/** @pdOid fc08e9fc-ee57-4b84-a903-48a793d3e9ef */
public class Message {
   /** @pdOid ae72f365-dd63-4611-959c-ed80f4a13818 */
   public int messageID;
   /** @pdOid a450e857-78f5-4311-9231-7c8d96665fbe */
   public java.lang.String messageText;
   /** @pdOid 57e4b779-4df6-49ab-94d4-74098406f455 */
   public java.util.Date sendTime;
   /** @pdOid 37c2da7c-ecec-4187-9e5f-1ecf3031a1d0 */
   public boolean isRead;
   
   /** @pdRoleInfo migr=no name=Admin assc=messageCreator mult=1..1 side=A */
   public Admin admin;
   /** @pdRoleInfo migr=no name=User assc=userMessage mult=1..1 side=A */
   public User user;
   
   
   /** @pdGenerated default parent getter */
   public Admin getAdmin() {
      return admin;
   }
   
   /** @pdGenerated default parent setter
     * @param newAdmin */
   public void setAdmin(Admin newAdmin) {
      if (this.admin == null || !this.admin.equals(newAdmin))
      {
         if (this.admin != null)
         {
            Admin oldAdmin = this.admin;
            this.admin = null;
            oldAdmin.removeMessage(this);
         }
         if (newAdmin != null)
         {
            this.admin = newAdmin;
            this.admin.addMessage(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public User getUser() {
      return user;
   }
   
   /** @pdGenerated default parent setter
     * @param newUser */
   public void setUser(User newUser) {
      if (this.user == null || !this.user.equals(newUser))
      {
         if (this.user != null)
         {
            User oldUser = this.user;
            this.user = null;
            oldUser.removeMessage(this);
         }
         if (newUser != null)
         {
            this.user = newUser;
            this.user.addMessage(this);
         }
      }
   }

}