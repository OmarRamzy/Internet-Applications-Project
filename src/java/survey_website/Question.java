package survey_website;


/** *********************************************************************
 * Module:  Question.java
 * Author:  abdal
 * Purpose: Defines the Class Question
 ********************************************************************** */

import java.util.*;

/**
 * @pdOid dd3119a4-5a10-47b6-a500-9a03266c517f
 */
public class Question {

    /**
     * @pdOid 31116038-8919-4cad-949e-e7a1288e92fc
     */
    public int questionID;
    /**
     * @pdOid a3f019b1-09c7-48b5-bef9-993f3d1ce79a
     */
    public java.lang.String questionText;
    /**
     * @pdOid eb9e2d83-ec17-4060-8076-2b87804a4f22
     */
    public java.lang.String questionType;
    /**
     * @pdOid 6bd45c95-19f2-4d6b-b79f-b8581595c0fc
     */
    public int questionNumber;
    /**
     * @pdOid 854d7241-f442-47d4-bcd7-b470439980e3
     */
    public boolean isMandatory;

    /**
     * @pdRoleInfo migr=no name=Choice assc=questionChoice
     * coll=java.util.Collection impl=java.util.HashSet mult=0..*
     */
    public java.util.Collection<Choice> choice;
    /**
     * @pdRoleInfo migr=no name=QuestionAnswer assc=questionQuestionAnswer
     * coll=java.util.Collection impl=java.util.HashSet mult=0..*
     */
    public java.util.Collection<QuestionAnswer> questionAnswer;
    /**
     * @pdRoleInfo migr=no name=Survey assc=surveyQuestion mult=1..1 side=A
     */
    public Survey survey;

    /**
     * @pdGenerated default getter
     */
    public java.util.Collection<Choice> getChoice() {
        if (choice == null) {
            choice = new java.util.HashSet<Choice>();
        }
        return choice;
    }

    /**
     * @pdGenerated default iterator getter
     */
    public java.util.Iterator getIteratorChoice() {
        if (choice == null) {
            choice = new java.util.HashSet<Choice>();
        }
        return choice.iterator();
    }

    /**
     * @pdGenerated default setter
     * @param newChoice
     */
    public void setChoice(java.util.Collection<Choice> newChoice) {
        removeAllChoice();
        for (java.util.Iterator iter = newChoice.iterator(); iter.hasNext();) {
            addChoice((Choice) iter.next());
        }
    }

    /**
     * @pdGenerated default add
     * @param newChoice
     */
    public void addChoice(Choice newChoice) {
        if (newChoice == null) {
            return;
        }
        if (this.choice == null) {
            this.choice = new java.util.HashSet<Choice>();
        }
        if (!this.choice.contains(newChoice)) {
            this.choice.add(newChoice);
            newChoice.setQuestion(this);
        }
    }

    /**
     * @pdGenerated default remove
     * @param oldChoice
     */
    public void removeChoice(Choice oldChoice) {
        if (oldChoice == null) {
            return;
        }
        if (this.choice != null) {
            if (this.choice.contains(oldChoice)) {
                this.choice.remove(oldChoice);
                oldChoice.setQuestion((Question) null);
            }
        }
    }

    /**
     * @pdGenerated default removeAll
     */
    public void removeAllChoice() {
        if (choice != null) {
            Choice oldChoice;
            for (java.util.Iterator iter = getIteratorChoice(); iter.hasNext();) {
                oldChoice = (Choice) iter.next();
                iter.remove();
                oldChoice.setQuestion((Question) null);
            }
        }
    }

    /**
     * @pdGenerated default getter
     */
    public java.util.Collection<QuestionAnswer> getQuestionAnswer() {
        if (questionAnswer == null) {
            questionAnswer = new java.util.HashSet<QuestionAnswer>();
        }
        return questionAnswer;
    }

    /**
     * @pdGenerated default iterator getter
     */
    public java.util.Iterator getIteratorQuestionAnswer() {
        if (questionAnswer == null) {
            questionAnswer = new java.util.HashSet<QuestionAnswer>();
        }
        return questionAnswer.iterator();
    }

    /**
     * @pdGenerated default setter
     * @param newQuestionAnswer
     */
    public void setQuestionAnswer(java.util.Collection<QuestionAnswer> newQuestionAnswer) {
        removeAllQuestionAnswer();
        for (java.util.Iterator iter = newQuestionAnswer.iterator(); iter.hasNext();) {
            addQuestionAnswer((QuestionAnswer) iter.next());
        }
    }

    /**
     * @pdGenerated default add
     * @param newQuestionAnswer
     */
    public void addQuestionAnswer(QuestionAnswer newQuestionAnswer) {
        if (newQuestionAnswer == null) {
            return;
        }
        if (this.questionAnswer == null) {
            this.questionAnswer = new java.util.HashSet<QuestionAnswer>();
        }
        if (!this.questionAnswer.contains(newQuestionAnswer)) {
            this.questionAnswer.add(newQuestionAnswer);
            newQuestionAnswer.setQuestion(this);
        }
    }

    /**
     * @pdGenerated default remove
     * @param oldQuestionAnswer
     */
    public void removeQuestionAnswer(QuestionAnswer oldQuestionAnswer) {
        if (oldQuestionAnswer == null) {
            return;
        }
        if (this.questionAnswer != null) {
            if (this.questionAnswer.contains(oldQuestionAnswer)) {
                this.questionAnswer.remove(oldQuestionAnswer);
                oldQuestionAnswer.setQuestion((Question) null);
            }
        }
    }

    /**
     * @pdGenerated default removeAll
     */
    public void removeAllQuestionAnswer() {
        if (questionAnswer != null) {
            QuestionAnswer oldQuestionAnswer;
            for (java.util.Iterator iter = getIteratorQuestionAnswer(); iter.hasNext();) {
                oldQuestionAnswer = (QuestionAnswer) iter.next();
                iter.remove();
                oldQuestionAnswer.setQuestion((Question) null);
            }
        }
    }

    /**
     * @pdGenerated default parent getter
     */
    public Survey getSurvey() {
        return survey;
    }

    /**
     * @pdGenerated default parent setter
     * @param newSurvey
     */
    public void setSurvey(Survey newSurvey) {
        if (this.survey == null || !this.survey.equals(newSurvey)) {
            if (this.survey != null) {
                Survey oldSurvey = this.survey;
                this.survey = null;
                oldSurvey.removeQuestion(this);
            }
            if (newSurvey != null) {
                this.survey = newSurvey;
                this.survey.addQuestion(this);
            }
        }
    }

}
