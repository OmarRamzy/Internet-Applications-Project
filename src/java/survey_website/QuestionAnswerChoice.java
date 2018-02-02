package survey_website;

/***********************************************************************
 * Module:  QuestionAnswerChoice.java
 * Author:  abdal
 * Purpose: Defines the Class QuestionAnswerChoice
 ***********************************************************************/

import java.util.*;

/** @pdOid 7646be8c-9f92-4f60-bb4a-a049ff29bc8a */
public class QuestionAnswerChoice {
   /** @pdOid 8de6495d-be8d-44d6-82ef-55ac777b137b */
   public int questionAnswerChoiceID;
   
   /** @pdRoleInfo migr=no name=QuestionAnswer assc=relationship14 mult=1..1 side=A */
   public QuestionAnswer questionAnswer;
   /** @pdRoleInfo migr=no name=Choice assc=relationship15 mult=1..1 side=A */
   public Choice choice;
   
   
   /** @pdGenerated default parent getter */
   public QuestionAnswer getQuestionAnswer() {
      return questionAnswer;
   }
   
   /** @pdGenerated default parent setter
     * @param newQuestionAnswer */
   public void setQuestionAnswer(QuestionAnswer newQuestionAnswer) {
      if (this.questionAnswer == null || !this.questionAnswer.equals(newQuestionAnswer))
      {
         if (this.questionAnswer != null)
         {
            QuestionAnswer oldQuestionAnswer = this.questionAnswer;
            this.questionAnswer = null;
            oldQuestionAnswer.removeQuestionAnswerChoice(this);
         }
         if (newQuestionAnswer != null)
         {
            this.questionAnswer = newQuestionAnswer;
            this.questionAnswer.addQuestionAnswerChoice(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Choice getChoice() {
      return choice;
   }
   
   /** @pdGenerated default parent setter
     * @param newChoice */
   public void setChoice(Choice newChoice) {
      if (this.choice == null || !this.choice.equals(newChoice))
      {
         if (this.choice != null)
         {
            Choice oldChoice = this.choice;
            this.choice = null;
            oldChoice.removeQuestionAnswerChoice(this);
         }
         if (newChoice != null)
         {
            this.choice = newChoice;
            this.choice.addQuestionAnswerChoice(this);
         }
      }
   }

}