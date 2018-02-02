/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import survey_website.db.DB;

/**
 *
 * @author OmarRamzy
 */
@WebServlet(urlPatterns = {"/MessageSingleUser"})
public class MessageSingleUser extends HttpServlet {

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
        
                HttpSession session = request.getSession(true);
 /*Need Session Here*/ //              String AdminName = (String) session.getAttribute("AdminName");
                String AdminName = "abdo";  
                String Message = request.getParameter("MessageContent");
                String UserName = request.getParameter("UserName");
                Calendar calendar = Calendar.getInstance();
                java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
                ResultSet RS = null ;
                PrintWriter out = response.getWriter();

            try {
                 String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
                 Class.forName(MYSQL_DRIVER);
                 Connection Con = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD);
                 Statement Stmt = Con.createStatement();

                RS = Stmt.executeQuery("select * from user where Username='" + UserName + "';");
            int cnt = 0 ;    
            while (RS.next()) {
                cnt++;
            }
            
            if (cnt == 0)
            {
                out.print("User Not Exist!");
                response.sendRedirect("MessageSingleUser.jsp");
            }
             out.print(cnt);
            Con.close();
        } catch (Exception e) {
            System.out.println("Error");

            System.out.println(e);
        }

              
     
		try{
			String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
			Class.forName(MYSQL_DRIVER);
			Connection Con = DriverManager.getConnection(DB.DB_URL,DB.DB_USERNAME,DB.DB_PASSWORD);
			Statement Stmt = Con.createStatement();
                        
                        
                        
                        Stmt.executeUpdate("INSERT INTO message(AdminUsername, MessageText, Username , IsRead ,SendTime)"
                                + "VALUES('"+AdminName+"','"+Message+"','"+UserName+"' ,false, '"+ourJavaDateObject+"' )");
			Con.close();
                        response.sendRedirect("Home");
			}
			catch(Exception e)
			{
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
