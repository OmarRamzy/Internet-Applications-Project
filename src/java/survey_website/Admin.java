package survey_website;

/***********************************************************************
 * Module:  Admin.java
 * Author:  abdal
 * Purpose: Defines the Class Admin
 ***********************************************************************/

import java.util.*;

/** @pdOid c2169ab3-51ea-4e7c-b10c-0c65b00b8df8 */
public class Admin {
   /** @pdOid 795ef4b1-9b4e-46a9-9a70-7e14a1261c3a */
   public java.lang.String adminUsername;
   /** @pdOid 8d91c801-af54-47ea-a278-f8069e31c66d */
   public java.lang.String password;
   
   /** @pdRoleInfo migr=no name=Message assc=messageCreator coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Message> message;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Message> getMessage() {
      if (message == null)
         message = new java.util.HashSet<Message>();
      return message;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMessage() {
      if (message == null)
         message = new java.util.HashSet<Message>();
      return message.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMessage */
   public void setMessage(java.util.Collection<Message> newMessage) {
      removeAllMessage();
      for (java.util.Iterator iter = newMessage.iterator(); iter.hasNext();)
         addMessage((Message)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMessage */
   public void addMessage(Message newMessage) {
      if (newMessage == null)
         return;
      if (this.message == null)
         this.message = new java.util.HashSet<Message>();
      if (!this.message.contains(newMessage))
      {
         this.message.add(newMessage);
         newMessage.setAdmin(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldMessage */
   public void removeMessage(Message oldMessage) {
      if (oldMessage == null)
         return;
      if (this.message != null)
         if (this.message.contains(oldMessage))
         {
            this.message.remove(oldMessage);
            oldMessage.setAdmin((Admin)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMessage() {
      if (message != null)
      {
         Message oldMessage;
         for (java.util.Iterator iter = getIteratorMessage(); iter.hasNext();)
         {
            oldMessage = (Message)iter.next();
            iter.remove();
            oldMessage.setAdmin((Admin)null);
         }
      }
   }

}