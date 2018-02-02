package survey_website;

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

/**
 *
 * @author ahmed
 */
@WebServlet(urlPatterns = {"/SendMessage"})
public class SendMessage extends HttpServlet {

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
    HttpSession user = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String data = request.getParameter("data");
        String temp[] = data.split("-");
        String username = temp[0];
        String message = temp[1];
        try {
            Cookie[] AllCookies = request.getCookies();
            for (Cookie c : AllCookies) {
                if (c.getName().equals("AdminSession")) {
                    Map sessionsManager = (HashMap) request.getServletContext().getAttribute("Manager");
                    user = (HttpSession) sessionsManager.get(c.getValue());
                }
            }
            String adminname = (String) user.getAttribute("username"); //Should be getten from session
            Calendar calendar = Calendar.getInstance();
            java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());

            String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(MYSQL_DRIVER);
            Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_db", "root", "");
            Statement Stmt = Con.createStatement();
            ResultSet RS = Stmt.executeQuery("select Username from user where Username='" + username + "';");
            if (RS.next()) {
                Stmt.executeUpdate("INSERT INTO message(AdminUsername, MessageText, Username , IsRead ,SendTime)"
                        + "VALUES('" + adminname + "','" + message + "','" + username + "' ,false, '" + ourJavaDateObject + "' )");
                out.print("The Message has been sent Successfully");
            } else {
                out.print("No Such a Username!");
            }
            Stmt.close();
            Con.close();
        } catch (Exception e) {
            out.print(e);
        }
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
