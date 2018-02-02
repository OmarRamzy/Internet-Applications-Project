/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import survey_website.ReportInformation;
import survey_website.db.DB;

/**
 *
 * @author OmarRamzy
 */
@WebServlet(urlPatterns = {"/ShowReports"})
public class ShowReports extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        ResultSet RS = null;
   /* Need Session Here*/     String AdminName = "abdo"/*(String) session.getAttribute("AdminName")*/;
        
        try {
            String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(MYSQL_DRIVER);
            Connection Con = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD);
            Statement Stmt = Con.createStatement();
            RS = Stmt.executeQuery("select *  from notification");
            
            ArrayList < ReportInformation > Reports = new ArrayList<ReportInformation>();
            
            request.setAttribute("AdminName", AdminName );
            

           
            while (RS.next()) {
                String First = RS.getString("SurveyID");
                String Last = RS.getString("ReportText") ; 
                ReportInformation R = new ReportInformation (First , Last);
                Reports.add( R );
            }
            request.setAttribute("Reports", Reports);
            
           request.getRequestDispatcher("ShowReports.jsp").forward(request,response);

            Con.close();
        } catch (Exception e) {
            System.out.println("Error");

            System.out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
