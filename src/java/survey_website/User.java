package survey_website;

/***********************************************************************
 * Module:  User.java
 * Author:  abdal
 * Purpose: Defines the Class User
 ***********************************************************************/

import java.util.*;

/** @pdOid 5557b77f-6f30-48ad-add3-448253d55f91 */
public class User {
   /** @pdOid ccf5eb0f-e4bf-4a13-90f1-0ed9fbf031b1 */
   public java.lang.String username;
   /** @pdOid 282d4050-807d-4d8c-8fc9-a41243bb48a4 */
   public java.lang.String password;
   /** @pdOid 8e48fd4f-31fc-4109-b216-ecb76ba5c2b7 */
   public java.lang.String firstName;
   /** @pdOid 1b981ceb-f657-4db9-bead-6be1e704b1d2 */
   public java.lang.String lastName;
   /** @pdOid c3b589fd-b5fa-49dc-97cd-b95e4a77b0ed */
   public java.lang.String emailAddress;
   /** @pdOid 222b6ccb-04d9-41ee-85cc-5127de8a07d5 */
   public boolean isSuspended;
   
   /** @pdRoleInfo migr=no name=Survey assc=surveyOwner coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Survey> survey;
   /** @pdRoleInfo migr=no name=UserSurveyAnswer assc=anwerOwner coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<UserSurveyAnswer> userSurveyAnswer;
   /** @pdRoleInfo migr=no name=SurveyReport assc=reportCreator coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<SurveyReport> surveyReport;
   /** @pdRoleInfo migr=no name=Message assc=userMessage coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Message> message;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Survey> getSurvey() {
      if (survey == null)
         survey = new java.util.HashSet<Survey>();
      return survey;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSurvey() {
      if (survey == null)
         survey = new java.util.HashSet<Survey>();
      return survey.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSurvey */
   public void setSurvey(java.util.Collection<Survey> newSurvey) {
      removeAllSurvey();
      for (java.util.Iterator iter = newSurvey.iterator(); iter.hasNext();)
         addSurvey((Survey)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newSurvey */
   public void addSurvey(Survey newSurvey) {
      if (newSurvey == null)
         return;
      if (this.survey == null)
         this.survey = new java.util.HashSet<Survey>();
      if (!this.survey.contains(newSurvey))
      {
         this.survey.add(newSurvey);
         newSurvey.setUser(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldSurvey */
   public void removeSurvey(Survey oldSurvey) {
      if (oldSurvey == null)
         return;
      if (this.survey != null)
         if (this.survey.contains(oldSurvey))
         {
            this.survey.remove(oldSurvey);
            oldSurvey.setUser((User)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSurvey() {
      if (survey != null)
      {
         Survey oldSurvey;
         for (java.util.Iterator iter = getIteratorSurvey(); iter.hasNext();)
         {
            oldSurvey = (Survey)iter.next();
            iter.remove();
            oldSurvey.setUser((User)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<UserSurveyAnswer> getUserSurveyAnswer() {
      if (userSurveyAnswer == null)
         userSurveyAnswer = new java.util.HashSet<UserSurveyAnswer>();
      return userSurveyAnswer;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorUserSurveyAnswer() {
      if (userSurveyAnswer == null)
         userSurveyAnswer = new java.util.HashSet<UserSurveyAnswer>();
      return userSurveyAnswer.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newUserSurveyAnswer */
   public void setUserSurveyAnswer(java.util.Collection<UserSurveyAnswer> newUserSurveyAnswer) {
      removeAllUserSurveyAnswer();
      for (java.util.Iterator iter = newUserSurveyAnswer.iterator(); iter.hasNext();)
         addUserSurveyAnswer((UserSurveyAnswer)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newUserSurveyAnswer */
   public void addUserSurveyAnswer(UserSurveyAnswer newUserSurveyAnswer) {
      if (newUserSurveyAnswer == null)
         return;
      if (this.userSurveyAnswer == null)
         this.userSurveyAnswer = new java.util.HashSet<UserSurveyAnswer>();
      if (!this.userSurveyAnswer.contains(newUserSurveyAnswer))
      {
         this.userSurveyAnswer.add(newUserSurveyAnswer);
         newUserSurveyAnswer.setUser(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldUserSurveyAnswer */
   public void removeUserSurveyAnswer(UserSurveyAnswer oldUserSurveyAnswer) {
      if (oldUserSurveyAnswer == null)
         return;
      if (this.userSurveyAnswer != null)
         if (this.userSurveyAnswer.contains(oldUserSurveyAnswer))
         {
            this.userSurveyAnswer.remove(oldUserSurveyAnswer);
            oldUserSurveyAnswer.setUser((User)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllUserSurveyAnswer() {
      if (userSurveyAnswer != null)
      {
         UserSurveyAnswer oldUserSurveyAnswer;
         for (java.util.Iterator iter = getIteratorUserSurveyAnswer(); iter.hasNext();)
         {
            oldUserSurveyAnswer = (UserSurveyAnswer)iter.next();
            iter.remove();
            oldUserSurveyAnswer.setUser((User)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<SurveyReport> getSurveyReport() {
      if (surveyReport == null)
         surveyReport = new java.util.HashSet<SurveyReport>();
      return surveyReport;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSurveyReport() {
      if (surveyReport == null)
         surveyReport = new java.util.HashSet<SurveyReport>();
      return surveyReport.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSurveyReport */
   public void setSurveyReport(java.util.Collection<SurveyReport> newSurveyReport) {
      removeAllSurveyReport();
      for (java.util.Iterator iter = newSurveyReport.iterator(); iter.hasNext();)
         addSurveyReport((SurveyReport)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newSurveyReport */
   public void addSurveyReport(SurveyReport newSurveyReport) {
      if (newSurveyReport == null)
         return;
      if (this.surveyReport == null)
         this.surveyReport = new java.util.HashSet<SurveyReport>();
      if (!this.surveyReport.contains(newSurveyReport))
      {
         this.surveyReport.add(newSurveyReport);
         newSurveyReport.setUser(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldSurveyReport */
   public void removeSurveyReport(SurveyReport oldSurveyReport) {
      if (oldSurveyReport == null)
         return;
      if (this.surveyReport != null)
         if (this.surveyReport.contains(oldSurveyReport))
         {
            this.surveyReport.remove(oldSurveyReport);
            oldSurveyReport.setUser((User)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSurveyReport() {
      if (surveyReport != null)
      {
         SurveyReport oldSurveyReport;
         for (java.util.Iterator iter = getIteratorSurveyReport(); iter.hasNext();)
         {
            oldSurveyReport = (SurveyReport)iter.next();
            iter.remove();
            oldSurveyReport.setUser((User)null);
         }
      }
   }
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
         newMessage.setUser(this);      
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
            oldMessage.setUser((User)null);
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
            oldMessage.setUser((User)null);
         }
      }
   }

}