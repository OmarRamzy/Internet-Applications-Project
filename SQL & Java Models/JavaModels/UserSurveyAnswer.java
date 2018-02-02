/***********************************************************************
 * Module:  UserSurveyAnswer.java
 * Author:  abdal
 * Purpose: Defines the Class UserSurveyAnswer
 ***********************************************************************/

import java.util.*;

/** @pdOid 5496ee0e-9f10-4b4b-bae0-e0d084de5fd0 */
public class UserSurveyAnswer {
   /** @pdOid 56c081c4-bda9-4658-ae0d-41bed3dd2488 */
   public int answerID;
   /** @pdOid 9a987904-c1a3-4157-a400-acfcc5a89724 */
   public boolean hideUserIdentity;
   
   /** @pdRoleInfo migr=no name=QuestionAnswer assc=userSurveyQuestionAnswer coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<QuestionAnswer> questionAnswer;
   /** @pdRoleInfo migr=no name=Survey assc=surveyUserSurveyAnswer mult=1..1 side=A */
   public Survey survey;
   /** @pdRoleInfo migr=no name=User assc=anwerOwner mult=1..1 side=A */
   public User user;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<QuestionAnswer> getQuestionAnswer() {
      if (questionAnswer == null)
         questionAnswer = new java.util.HashSet<QuestionAnswer>();
      return questionAnswer;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorQuestionAnswer() {
      if (questionAnswer == null)
         questionAnswer = new java.util.HashSet<QuestionAnswer>();
      return questionAnswer.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newQuestionAnswer */
   public void setQuestionAnswer(java.util.Collection<QuestionAnswer> newQuestionAnswer) {
      removeAllQuestionAnswer();
      for (java.util.Iterator iter = newQuestionAnswer.iterator(); iter.hasNext();)
         addQuestionAnswer((QuestionAnswer)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newQuestionAnswer */
   public void addQuestionAnswer(QuestionAnswer newQuestionAnswer) {
      if (newQuestionAnswer == null)
         return;
      if (this.questionAnswer == null)
         this.questionAnswer = new java.util.HashSet<QuestionAnswer>();
      if (!this.questionAnswer.contains(newQuestionAnswer))
      {
         this.questionAnswer.add(newQuestionAnswer);
         newQuestionAnswer.setUserSurveyAnswer(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldQuestionAnswer */
   public void removeQuestionAnswer(QuestionAnswer oldQuestionAnswer) {
      if (oldQuestionAnswer == null)
         return;
      if (this.questionAnswer != null)
         if (this.questionAnswer.contains(oldQuestionAnswer))
         {
            this.questionAnswer.remove(oldQuestionAnswer);
            oldQuestionAnswer.setUserSurveyAnswer((UserSurveyAnswer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllQuestionAnswer() {
      if (questionAnswer != null)
      {
         QuestionAnswer oldQuestionAnswer;
         for (java.util.Iterator iter = getIteratorQuestionAnswer(); iter.hasNext();)
         {
            oldQuestionAnswer = (QuestionAnswer)iter.next();
            iter.remove();
            oldQuestionAnswer.setUserSurveyAnswer((UserSurveyAnswer)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Survey getSurvey() {
      return survey;
   }
   
   /** @pdGenerated default parent setter
     * @param newSurvey */
   public void setSurvey(Survey newSurvey) {
      if (this.survey == null || !this.survey.equals(newSurvey))
      {
         if (this.survey != null)
         {
            Survey oldSurvey = this.survey;
            this.survey = null;
            oldSurvey.removeUserSurveyAnswer(this);
         }
         if (newSurvey != null)
         {
            this.survey = newSurvey;
            this.survey.addUserSurveyAnswer(this);
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
            oldUser.removeUserSurveyAnswer(this);
         }
         if (newUser != null)
         {
            this.user = newUser;
            this.user.addUserSurveyAnswer(this);
         }
      }
   }

}