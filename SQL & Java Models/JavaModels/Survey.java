/***********************************************************************
 * Module:  Survey.java
 * Author:  abdal
 * Purpose: Defines the Class Survey
 ***********************************************************************/

import java.util.*;

/** @pdOid 698819eb-8653-4eb1-b692-bea7a112017a */
public class Survey {
   /** @pdOid 4f38bd2c-3b42-4f3e-be77-4ff5b4d056ae */
   public int surveyID;
   /** @pdOid b26452e9-c384-46af-8530-ffbacbc2e404 */
   public java.lang.String surveyName;
   /** @pdOid 0f27f811-da38-4053-b31e-f52d7671b12b */
   public java.lang.String surveyDescription;
   /** @pdOid ed45a086-18ce-40f3-b8f3-be3dbcb29ac7 */
   public java.util.Date createdDate;
   /** @pdOid 8f7a7f9f-bc5a-4b45-9b9a-a5599cd58e39 */
   public java.util.Date closeTime;
   /** @pdOid 2f87a9d0-d5d3-4513-b699-7888edd0c6c9 */
   public boolean isSuspended;
   /** @pdOid fc74459e-aab5-4e7c-bd9f-97d9c92c88bd */
   public boolean isClosed;
   
   /** @pdRoleInfo migr=no name=Question assc=surveyQuestion coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Question> question;
   /** @pdRoleInfo migr=no name=UserSurveyAnswer assc=surveyUserSurveyAnswer coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<UserSurveyAnswer> userSurveyAnswer;
   /** @pdRoleInfo migr=no name=SurveyReport assc=surveySurveyReport coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<SurveyReport> surveyReport;
   /** @pdRoleInfo migr=no name=User assc=surveyOwner mult=1..1 side=A */
   public User user;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Question> getQuestion() {
      if (question == null)
         question = new java.util.HashSet<Question>();
      return question;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorQuestion() {
      if (question == null)
         question = new java.util.HashSet<Question>();
      return question.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newQuestion */
   public void setQuestion(java.util.Collection<Question> newQuestion) {
      removeAllQuestion();
      for (java.util.Iterator iter = newQuestion.iterator(); iter.hasNext();)
         addQuestion((Question)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newQuestion */
   public void addQuestion(Question newQuestion) {
      if (newQuestion == null)
         return;
      if (this.question == null)
         this.question = new java.util.HashSet<Question>();
      if (!this.question.contains(newQuestion))
      {
         this.question.add(newQuestion);
         newQuestion.setSurvey(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldQuestion */
   public void removeQuestion(Question oldQuestion) {
      if (oldQuestion == null)
         return;
      if (this.question != null)
         if (this.question.contains(oldQuestion))
         {
            this.question.remove(oldQuestion);
            oldQuestion.setSurvey((Survey)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllQuestion() {
      if (question != null)
      {
         Question oldQuestion;
         for (java.util.Iterator iter = getIteratorQuestion(); iter.hasNext();)
         {
            oldQuestion = (Question)iter.next();
            iter.remove();
            oldQuestion.setSurvey((Survey)null);
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
         newUserSurveyAnswer.setSurvey(this);      
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
            oldUserSurveyAnswer.setSurvey((Survey)null);
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
            oldUserSurveyAnswer.setSurvey((Survey)null);
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
         newSurveyReport.setSurvey(this);      
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
            oldSurveyReport.setSurvey((Survey)null);
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
            oldSurveyReport.setSurvey((Survey)null);
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
            oldUser.removeSurvey(this);
         }
         if (newUser != null)
         {
            this.user = newUser;
            this.user.addSurvey(this);
         }
      }
   }

}