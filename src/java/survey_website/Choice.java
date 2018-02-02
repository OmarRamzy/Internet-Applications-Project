package survey_website;

/***********************************************************************
 * Module:  Choice.java
 * Author:  abdal
 * Purpose: Defines the Class Choice
 ***********************************************************************/

import java.util.*;

/** @pdOid 8619971f-55f0-40eb-a327-ca9fc6554bed */
public class Choice {
   /** @pdOid e58245fa-896f-42f2-bc66-7dc2d2517c32 */
   public int choiceID;
   /** @pdOid babb4ca1-c8dd-4a9d-a02b-d92698fd6bf1 */
   public java.lang.String choiceText;
   /** @pdOid 110a99ba-3ff0-44ec-90fc-2080abed7ed9 */
   public int choiceNumber;
   
   /** @pdRoleInfo migr=no name=QuestionAnswerChoice assc=relationship15 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<QuestionAnswerChoice> questionAnswerChoice;
   /** @pdRoleInfo migr=no name=Question assc=questionChoice mult=1..1 side=A */
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
         newQuestionAnswerChoice.setChoice(this);      
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
            oldQuestionAnswerChoice.setChoice((Choice)null);
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
            oldQuestionAnswerChoice.setChoice((Choice)null);
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
            oldQuestion.removeChoice(this);
         }
         if (newQuestion != null)
         {
            this.question = newQuestion;
            this.question.addChoice(this);
         }
      }
   }

}