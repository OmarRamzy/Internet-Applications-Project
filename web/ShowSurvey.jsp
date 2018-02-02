<%-- 
    Document   : ShowSurvey
    Created on : Dec 13, 2017, 10:36:47 PM
    Author     : ahmed
--%>

<%@page import="survey_website.Choice"%>
<%@page import="survey_website.Question"%>
<%@page import="survey_website.db.DB"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="survey_website.Survey"%>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%  
        // --------------------------------------------
        int surveyID = Integer.parseInt(request.getParameter("surveyID"));
        
        Survey currentSurvey = DB.fetchSurvey(surveyID);
       
        // --------------------------------------------
        
        
    %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= "Survey - " + currentSurvey.surveyName %></title>
        <link rel="stylesheet" href="css/s.css">
        <link rel="stylesheet" href="css/survey.css">
        <script src="jquery-3.2.1.min.js"></script>
    </head>
    
    <%
        if (currentSurvey.isClosed) {
            out.println("<h1>This survey has been closed.</h1><br>");
        }
        else {  
            // continue
    %>
    
    <body>
        <table>
        <tr>
          <td><h1><%=currentSurvey.surveyName%></h1></td>
        </tr>
        <tr>
            <td><u>Survey Description:</u></td>
          <td><%= currentSurvey.surveyDescription %></td>
        </tr>
        <tr>
            <td><u>Created by:</u></td>
          <td><%= currentSurvey.user.username %></td>
        </tr>
        <tr>
            <td><u>Created on:</u></td>
          <td><%= currentSurvey.createdDate %></td>
        </tr>
        <tr>
            <td><u>Closes on:</u></td>
          <td><%= currentSurvey.closeTime %></td>
        </tr>
        <tr>
            <td><a href= <%out.print("Report?surveyID=" + surveyID);%> >Report this survey to Admin</a></td>
        </tr>
        </table>
        
        <br><br>
        
        <div class="container">
        <form method="POST" action="ShowSurvey">

            <%
                if (currentSurvey.question.size() > 0){

                    out.println("<label><b>Please answer the following questions: </b></label>" + "<br><br>");
                    
                    out.println("<div class=\"question\">");
                    out.println("<table>");
                    Question q = currentSurvey.question.get((Integer) session.getAttribute(Integer.toString(surveyID)));
                    
                    out.println("<input type=\"hidden\" name=\"surveyID\" value="+ surveyID +">");
                    out.println("<input type=\"hidden\" name=\"questionID\" value=" + q.questionID + ">");
                    
                    out.println("<tr>");
                    out.println("<td>" + q.questionNumber + ".  "  + q.questionText + "</td>");
                    out.println("</tr>");
                        
                    if (q.questionType.equals("MCQ")) {   // MCQ Questions
                        for (Choice c: q.choice) {
                            out.println("<tr>");
                            out.print("<td><input type=\"radio\" name=\"questionAnswer\" value=" + c.choiceID + "> " + c.choiceText + "</td>");
                            out.println("</tr>");
                        }
                    }
                        
                    if (q.questionType.equals("check")) {   // Check-boxes Questions
                        for (Choice c: q.choice) {
                            out.println("<tr>");
                            out.print("<td><input type=\"checkbox\" name=\"questionAnswer\" value=" + c.choiceID + "> " + c.choiceText + "</td>");
                            out.println("</tr>");
                        }
                    }
                        
                    if (q.questionType.equals("free")) {   // Free-answer Questions
                        out.println("<tr>");
                        out.print("<td><input type=\"text\" name=\"questionAnswer\" required autocomplete=\"off\"></td>");
                        out.println("</tr>");
                    }
                    
                    out.println("</table>");
                    out.println("</div");
                        
                    
                    out.println("<br>Question " + ((Integer)session.getAttribute(Integer.toString(surveyID)) + 1) + " of " + currentSurvey.question.size());
                    
                    if (currentSurvey.isSuspended) {
                        out.println("<br><u>This Survey is Suspended.</u><br>No answers can be accpted.<br>");
                    }
                    else {  // show the submit button
                        out.println("<br><input type=\"submit\" class=\"submit\" value=\"Next\">" + "<br>");
                    }

                }
            } // closing if condition define befor the <body> section
            %>

        </form></div>
    </body>
</html>
