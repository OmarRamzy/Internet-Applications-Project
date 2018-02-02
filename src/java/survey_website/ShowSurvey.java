package survey_website;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import survey_website.db.DB;

/**
 *
 * @author ahmed
 */
@WebServlet(urlPatterns = {"/ShowSurvey"})
public class ShowSurvey extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int surveyID = Integer.parseInt(request.getParameter("surveyID"));
        
        HttpSession session = request.getSession(true);
        
        if (session.getAttribute(Integer.toString(surveyID)) == null ) {
            session.setAttribute(Integer.toString(surveyID), 0);
        }
        
        
        Integer currentQuestion = (Integer) session.getAttribute(Integer.toString(surveyID));
        int totalNumOfQuestions = DB.getNumOfQuestionsInSurvey(surveyID);
        if (currentQuestion < totalNumOfQuestions || totalNumOfQuestions == 0) {
            request.getRequestDispatcher("ShowSurvey.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("survey-finished.jsp");
            session.removeAttribute(Integer.toString(surveyID));
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int surveyID = Integer.parseInt(request.getParameter("surveyID"));
        int questionID = Integer.parseInt(request.getParameter("questionID"));
        String [] questionAnswers = request.getParameterValues("questionAnswer");
        
        // TODO: get username from session and send it
        String username = "abdallah";
        DB.addQuestionAnswer(username, surveyID, questionID, questionAnswers, false);
        
        HttpSession session = request.getSession(true);
        if (session.getAttribute(Integer.toString(surveyID)) != null ) {
            Integer currentQuestion = (Integer) session.getAttribute(Integer.toString(surveyID));
            session.setAttribute(Integer.toString(surveyID), currentQuestion + 1);
        }
        
        
        // display another question
        response.sendRedirect("ShowSurvey?surveyID=" + surveyID);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
