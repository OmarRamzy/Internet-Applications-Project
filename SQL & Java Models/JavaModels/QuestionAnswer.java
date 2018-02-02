/***********************************************************************
 * Module:  QuestionAnswer.java
 * Author:  abdal
 * Purpose: Defines the Class QuestionAnswer
 ***********************************************************************/

import java.util.*;

/** @pdOid ad870258-cbfb-46fe-baf8-097bfb743663 */
public class QuestionAnswer {
   /** @pdOid 8024290b-3893-4d25-aa3f-f45321646cf0 */
   public int questionAnswerID;
   /** @pdOid 6a6850e0-1482-4215-9709-2f7136c2967a */
   public java.lang.String freeAnswer;
   
   /** @pdRoleInfo migr=no name=QuestionAnswerChoice assc=relationship14 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<QuestionAnswerChoice> questionAnswerChoice;
   /** @pdRoleInfo migr=no name=UserSurveyAnswer assc=userSurveyQuestionAnswer mult=0..1 side=A */
   public UserSurveyAnswer userSurveyAnswer;
   /** @pdRoleInfo migr=no name=Question assc=questionQuestionAnswer mult=1..1 side=A */
   public Question question;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<QuestionAnswerChoice> getQuestionAnswerChoice() {
      if (questionAnswerChoice == null)
         questionAnswerChoice = new java.util.HashSet<QuestionAnswerChoice>();
      return questionAnswerChoice;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorQuestionAnswerChoice() {
      if (questionAnswerChoice == null)
         questionAnswerChoice = new java.util.HashSet<QuestionAnswerChoice>();
      return questionAnswerChoice.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newQuestionAnswerChoice */
   public void setQuestionAnswerChoice(java.util.Collection<QuestionAnswerChoice> newQuestionAnswerChoice) {
      removeAllQuestionAnswerChoice();
      for (java.util.Iterator iter = newQuestionAnswerChoice.iterator(); iter.hasNext();)
         addQuestionAnswerChoice((QuestionAnswerChoice)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newQuestionAnswerChoice */
   public void addQuestionAnswerChoice(QuestionAnswerChoice newQuestionAnswerChoice) {
      if (newQuestionAnswerChoice == null)
         return;
      if (this.questionAnswerChoice == null)
         this.questionAnswerChoice = new java.util.HashSet<QuestionAnswerChoice>();
      if (!this.questionAnswerChoice.contains(newQuestionAnswerChoice))
      {
         this.questionAnswerChoice.add(newQuestionAnswerChoice);
         newQuestionAnswerChoice.setQuestionAnswer(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldQuestionAnswerChoice */
   public void removeQuestionAnswerChoice(QuestionAnswerChoice oldQuestionAnswerChoice) {
      if (oldQuestionAnswerChoice == null)
         return;
      if (this.questionAnswerChoice != null)
         if (this.questionAnswerChoice.contains(oldQuestionAnswerChoice))
         {
            this.questionAnswerChoice.remove(oldQuestionAnswerChoice);
            oldQuestionAnswerChoice.setQuestionAnswer((QuestionAnswer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllQuestionAnswerChoice() {
      if (questionAnswerChoice != null)
      {
         QuestionAnswerChoice oldQuestionAnswerChoice;
         for (java.util.Iterator iter = getIteratorQuestionAnswerChoice(); iter.hasNext();)
         {
            oldQuestionAnswerChoice = (QuestionAnswerChoice)iter.next();
            iter.remove();
            oldQuestionAnswerChoice.setQuestionAnswer((QuestionAnswer)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public UserSurveyAnswer getUserSurveyAnswer() {
      return userSurveyAnswer;
   }
   
   /** @pdGenerated default parent setter
     * @param newUserSurveyAnswer */
   public void setUserSurveyAnswer(UserSurveyAnswer newUserSurveyAnswer) {
      if (this.userSurveyAnswer == null || !this.userSurveyAnswer.equals(newUserSurveyAnswer))
      {
         if (this.userSurveyAnswer != null)
         {
            UserSurveyAnswer oldUserSurveyAnswer = this.userSurveyAnswer;
            this.userSurveyAnswer = null;
            oldUserSurveyAnswer.removeQuestionAnswer(this);
         }
         if (newUserSurveyAnswer != null)
         {
            this.userSurveyAnswer = newUserSurveyAnswer;
            this.userSurveyAnswer.addQuestionAnswer(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Question getQuestion() {
      return question;
   }
   
   /** @pdGenerated default parent setter
     * @param newQuestion */
   public void setQuestion(Question newQuestion) {
      if (this.question == null || !this.question.equals(newQuestion))
      {
         if (this.question != null)
         {
            Question oldQuestion = this.question;
            this.question = null;
            oldQuestion.removeQuestionAnswer(this);
         }
         if (newQuestion != null)
         {
            this.question = newQuestion;
            this.question.addQuestionAnswer(this);
         }
      }
   }

}