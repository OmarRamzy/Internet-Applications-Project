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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import survey_website.db.DB;

/**
 *
 * @author OmarRamzy
 */
@WebServlet(urlPatterns = {"/ShowMessages"})
public class ShowMessages extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private HttpSession user = null;

    private boolean logined(HttpServletRequest request) {
        Cookie[] AllCookies = request.getCookies();
        for (Cookie c : AllCookies) {
            if (c.getName().equals("UserSession")) {
                Map sessionsManager = (HashMap) request.getServletContext().getAttribute("Manager");
                if (sessionsManager != null && sessionsManager.get(c.getValue()) != null) {
                    user = (HttpSession) sessionsManager.get(c.getValue());
                    String Mode = "User";
                    request.getServletContext().setAttribute("Mode", Mode);
                    return true;
                } else {
                    c.setMaxAge(0);
                    return false;
                }
            }
        }
        return false;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!logined(request)) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        /* Neeeeeeeeed Session Here */

        // String UserName = (String) session.getAttribute("UserName");
        String UserName = (String) user.getAttribute("username"); // Todo: replace with user name from session

        ResultSet RS = null;

        ArrayList<String> Messages = new ArrayList<String>();

        try {
            String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(MYSQL_DRIVER);
            Connection Con = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD);
            Statement Stmt = Con.createStatement();
            RS = Stmt.executeQuery("select MessageText  from message where((message.Username='" + UserName + "'"
                    + "or message.Username ='All') and IsRead = false) ;");
            while (RS.next()) {
                Messages.add(RS.getString(1));
            }

            request.setAttribute("UserName", UserName);
            request.setAttribute("Messages", Messages);

            String query = "update message set IsRead = true where message.Username = '" + UserName + "'  ";
            PreparedStatement preparedStmt = Con.prepareStatement(query);
            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            Con.close();
        } catch (Exception e) {
            System.out.println("Error");

            System.out.println(e);
        }

        request.getRequestDispatcher("ShowMessages.jsp").forward(request, response);

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
