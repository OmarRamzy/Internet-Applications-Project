/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package survey_website.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import survey_website.Choice;
import survey_website.Question;
import survey_website.Survey;
import survey_website.User;
/**
 *
 * @author abdallah
 */
public abstract class DB {
    
    public static final String DB_URL = "jdbc:mysql://localhost:3306/survey_db";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";
    
    private static Connection con =null;
    
    static {
        try {
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static Survey fetchSurvey(int surveyID) {
        
        Survey selectedSurvey = new Survey();
        
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
                stmt = con.createStatement();
                resultSet = stmt.executeQuery(
                "SELECT * FROM Survey where SurveyID=" + "\'" + surveyID + "\'" +";");
                resultSet.next(); 
                selectedSurvey.surveyID = surveyID;
                selectedSurvey.surveyName = resultSet.getString("SurveyName");
                selectedSurvey.surveyDescription = resultSet.getString("SurveyDescription");
                selectedSurvey.user = fetchUser(resultSet.getString("Username"));
                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
                selectedSurvey.createdDate = dateFormatter1.parse(resultSet.getString("CreatedDate"));
                SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                selectedSurvey.closeTime = dateFormatter2.parse(resultSet.getString("CloseTime"));
                selectedSurvey.isSuspended = resultSet.getString("IsSuspended").equals("1");
                selectedSurvey.isClosed = resultSet.getString("IsClosed").equals("1");
                
                selectedSurvey.question = fetchSurveyQuestions(surveyID);
                
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        } 
        
        return selectedSurvey;
        
    }
    
    /**
     * returns the questions that belong to a certain survey.
     * @param surveyID
     * @return 
     */
    public static List<Question> fetchSurveyQuestions(int surveyID) {
        List<Question> selectedQuestions = new ArrayList<>();
        
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
                stmt = con.createStatement();
                resultSet = stmt.executeQuery(
                "SELECT * FROM Question where SurveyID=" + "\'" + surveyID + "\'" +" order by QuestionNumber;");
                while (resultSet.next()) {
                    Question q = new Question();
                    
                    q.questionID = Integer.parseInt(resultSet.getString("QuestionID"));
                    
                    Survey s = new Survey();
                    s.surveyID = surveyID;
                    q.survey = s;
                    
                    q.questionText = resultSet.getString("QuestionText");
                    q.questionType = resultSet.getString("QuestionType");
                    q.questionNumber = Integer.parseInt(resultSet.getString("QuestionNumber"));
                    q.isMandatory = resultSet.getString("IsMandatory").equals("1");
                    
                    
                    // read the choices
                    q.choice = fetchQuestionChoices(q.questionID);
               
                
                
                    selectedQuestions.add(q);
                    
                } 
                
                
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        } 
        
        return selectedQuestions;
    }
    
    
    public static List<Choice> fetchQuestionChoices(int questionID) {
        List <Choice> selectedChoices = new ArrayList<>();
        
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
                stmt = con.createStatement();
                resultSet = stmt.executeQuery(
                "SELECT * FROM choice where QuestionID=" + "\'" + questionID + "\'" +" order by ChoiceNumber;");
                while (resultSet.next()) {
                    Choice c = new Choice();
                    
                    c.choiceID = Integer.parseInt(resultSet.getString("ChoiceID"));
                    
                    Question q = new Question();
                    q.questionID = questionID;
                    c.question = q;
                    
                   c.choiceText = resultSet.getString("ChoiceText");
                   c.choiceNumber = Integer.parseInt(resultSet.getString("ChoiceNumber"));
                    
                
                    selectedChoices.add(c);
                    
                } 
                
                
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        } 
        
        
        return selectedChoices;
    }
    
    public static User fetchUser(String username) {
        User selectedUser = new User();
        
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
                stmt = con.createStatement();
                resultSet = stmt.executeQuery(
                "SELECT * FROM user where Username=" + "\'" + username + "\'" +";");
                resultSet.next();
                
                selectedUser.username = username;
                selectedUser.password = resultSet.getString("Password");
                selectedUser.firstName = resultSet.getString("FirstName");
                selectedUser.lastName = resultSet.getString("LastName");
                selectedUser.emailAddress = resultSet.getString("EmailAddress");
                selectedUser.isSuspended = resultSet.getString("IsSuspended").equals("1");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        } 
        
        
        return selectedUser;
    }
    
    
    /**
     * Add answer for a specific question in a specific survey.
     * @param surveyID
     * @param questionID
     * @param questionAnswers 
     */
    public static boolean addQuestionAnswer(
            String username, int surveyID, int questionID, String [] questionAnswers, boolean hideUserIdentity) {
        
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
                stmt = con.createStatement();
                
                resultSet = stmt.executeQuery(
                    "select count(1) as alreadyAnswered from usersurveyanswer where (username='" + username +"' and surveyID='" + surveyID + "');"               
                );
                resultSet.next();
                boolean alreadyExists = resultSet.getBoolean("alreadyAnswered");
                
                if (!alreadyExists) {
                    
                    resultSet = stmt.executeQuery(
                        "SELECT MAX(AnswerID) as MaxAnswerID FROM usersurveyanswer;");
                    resultSet.next();
                
                    int maxAnswerID = resultSet.getInt("MaxAnswerID");
                    int answerID = maxAnswerID + 1;
                
                    int rows_affected = stmt.executeUpdate("INSERT INTO usersurveyanswer VALUES (" 
                        + "\'" + answerID + "\'" + ", " 
                        + "\'" + username + "\'" + ", " 
                        + "\'" + surveyID + "\'" + ", "
                        + "\'" + (hideUserIdentity? 1 : 0) + "\'" + ");");
                    
                }
                // -------------------------------------------------------
                // TODO: check existence 
                
                resultSet = stmt.executeQuery(
                    "select AnswerID from usersurveyanswer where (username='" + username +"' and surveyID='" + surveyID + "');"               
                );
                resultSet.next();
                
                    int answerID = resultSet.getInt("AnswerID");
                    
                    resultSet = stmt.executeQuery(
                        "select QuestionType from question where QuestionID=" + questionID + ";"               
                    );
                    resultSet.next();
                    String questionType = resultSet.getString("QuestionType");
                    
                    if (questionType.equals("MCQ")) {
                        resultSet = stmt.executeQuery(
                            "SELECT MAX(QuestionAnswerID) as MaxQuestionAnswerID FROM QuestionAnswer;");
                        resultSet.next();
                
                        int maxQuestionAnswerID = resultSet.getInt("MaxQuestionAnswerID");
                        int questionAnswerID = maxQuestionAnswerID + 1;
                
                        int rows_affected = stmt.executeUpdate("INSERT INTO questionanswer VALUES (" 
                            + "\'" + questionAnswerID + "\'" + ", " 
                            + "\'" + questionID + "\'" + ", " 
                            + "\'" + answerID + "\'" + ", " 
                            + "\'" + null + "\'" + ");");  // TODO: insert null properly
                        
                        // ------------------------------------------------------------------------------
                    
                        resultSet = stmt.executeQuery(
                            "SELECT MAX(QuestionAnswerChoiceID) as MaxQuestionAnswerChoiceID FROM relationship10;");
                        resultSet.next();
                
                        int maxQuestionAnswerChoiceID = resultSet.getInt("MaxQuestionAnswerChoiceID");
                        int questionAnswerChoiceID = maxQuestionAnswerChoiceID + 1;
                        
                        
                
                        rows_affected = stmt.executeUpdate("INSERT INTO relationship10 VALUES (" 
                            + "\'" + questionAnswerChoiceID + "\'" + ", " 
                            + "\'" + questionAnswerID + "\'" + ", " 
                            + "\'" + questionAnswers[0] + "\'" + ");");
                    
                    }
                    else if (questionType.equals("check")) {
                        resultSet = stmt.executeQuery(
                            "SELECT MAX(QuestionAnswerID) as MaxQuestionAnswerID FROM QuestionAnswer;");
                        resultSet.next();
                
                        int maxQuestionAnswerID = resultSet.getInt("MaxQuestionAnswerID");
                        int questionAnswerID = maxQuestionAnswerID + 1;
                
                        int rows_affected = stmt.executeUpdate("INSERT INTO questionanswer VALUES (" 
                            + "\'" + questionAnswerID + "\'" + ", " 
                            + "\'" + questionID + "\'" + ", " 
                            + "\'" + answerID + "\'" + ", " 
                            + "\'" + null + "\'" + ");");  // TODO: insert null properly
                        
                        // ------------------------------------------------------------
                        resultSet = stmt.executeQuery(
                            "SELECT MAX(QuestionAnswerChoiceID) as MaxQuestionAnswerChoiceID FROM relationship10;");
                        resultSet.next();
                
                        int maxQuestionAnswerChoiceID = resultSet.getInt("MaxQuestionAnswerChoiceID");
                        int questionAnswerChoiceID = maxQuestionAnswerChoiceID + 1;
                        
                
                        for (String choiceID: questionAnswers) {
                            rows_affected = stmt.executeUpdate("INSERT INTO relationship10 VALUES (" 
                            + "\'" + questionAnswerChoiceID++ + "\'" + ", " 
                            + "\'" + questionAnswerID + "\'" + ", " 
                            + "\'" + choiceID + "\'" + ");");
                        }
                        
                    }
                    else if (questionType.equals("free")) {
                        resultSet = stmt.executeQuery(
                            "SELECT MAX(QuestionAnswerID) as MaxQuestionAnswerID FROM QuestionAnswer;");
                        resultSet.next();
                
                        int maxQuestionAnswerID = resultSet.getInt("MaxQuestionAnswerID");
                        int questionAnswerID = maxQuestionAnswerID + 1;
                
                        int rows_affected = stmt.executeUpdate("INSERT INTO questionanswer VALUES (" 
                            + "\'" + questionAnswerID + "\'" + ", " 
                            + "\'" + questionID + "\'" + ", " 
                            + "\'" + answerID + "\'" + ", " 
                            + "\'" + questionAnswers[0] + "\'" + ");");  // TODO: insert null properly
                        
                    }
                    
                    return true;
                
                
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        } 
        
        return false;
        
    }
    
    public static int getNumOfQuestionsInSurvey(int surveyID) {
        Survey s = fetchSurvey(surveyID);
        return s.question.size();
    }
    
    public static void reportSurvey(int surveyID, String reporterUserName, String reportText) {
        
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
                stmt = con.createStatement();
                
                resultSet = stmt.executeQuery(
                        "SELECT MAX(ReportID) as MaxReportID FROM notification;");
                resultSet.next();
                
                int maxReportID = resultSet.getInt("MaxReportID");
                int reportID = maxReportID + 1;

                int rows_affected = stmt.executeUpdate("INSERT INTO notification VALUES (" 
                        + "\'" + reportID + "\'" + ", " 
                        + "\'" + reporterUserName + "\'" + ", " 
                        + "\'" + surveyID + "\'" + ", "
                        + "\'" + reportText + "\'" + ", now());");      
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        } 

    }
    
    
    
}
