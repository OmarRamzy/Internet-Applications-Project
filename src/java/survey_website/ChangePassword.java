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
@WebServlet(urlPatterns = {"/ChangePassword"})
public class ChangePassword extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            response.setContentType("text/html;charset=ISO-8859-1");
            try {
                String data = request.getParameter("data");
                String temp[] = data.split("-");
                String Mode = temp[0];//detect User or Admin Who submit the form
                String first_input = temp[1];
                String new_password = temp[2];

                String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
                Class.forName(MYSQL_DRIVER);
                Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_db", "root", "");
                Statement Stmt = Con.createStatement();
                if (Mode.equals("0")) { //Admin
                    ResultSet RS = Stmt.executeQuery("Select Username from user where Username ='" + first_input + "';");
                    if (RS.next()) {
                        Stmt.executeUpdate("UPDATE user SET Password='" + new_password
                                + "' WHERE Username='" + first_input + "'");
                        out.print("Password has been updated");
                    } else {
                        out.print("No such a username");
                    }
                } else { //User
                    Cookie[] AllCookies = request.getCookies();
                    for (Cookie c : AllCookies) {
                        if (c.getName().equals("UserSession")) {
                            Map sessionsManager = (HashMap) request.getServletContext().getAttribute("Manager");
                            user = (HttpSession) sessionsManager.get(c.getValue());
                        }
                    }
                    String username = (String) user.getAttribute("username");
                    ResultSet RS = Stmt.executeQuery("Select password from user where Username ='" + username + "';");
                    RS.next();
                    String password = RS.getString(1);
                    if (first_input.equals(password)) {
                        Stmt.executeUpdate("UPDATE user SET Password='" + new_password
                                + "' WHERE username='" + username + "'");
                        out.print("Password has been updated");
                    } else {
                        out.print("Current Password is incorrect");
                    }
                }
                Stmt.close();
                Con.close();
            } catch (Exception e) {
                out.println(e);
            }
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
