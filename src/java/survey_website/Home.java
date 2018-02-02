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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import survey_website.db.DB;

/**
 *
 * @author ahmed
 */
@WebServlet(urlPatterns = {"/Home"})
public class Home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     *
     * @throws IOException if an I/O error occurs
     */
    private boolean logined(HttpServletRequest request) {
        Cookie[] AllCookies = request.getCookies();
        for (Cookie c : AllCookies) {
            if (c.getName().equals("UserSession") || c.getName().equals("AdminSession")) {
                Map sessionsManager = (HashMap) request.getServletContext().getAttribute("Manager");
                if (sessionsManager != null && sessionsManager.get(c.getValue()) != null) {
                    HttpSession user = (HttpSession) sessionsManager.get(c.getValue());
                    String Mode = "";
                    if (c.getName().equals("UserSession")) {
                        Mode = "User";
                        request.setAttribute("username", user.getAttribute("username"));
                    } else {
                        Mode = "Admin";
                    }
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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        if (!logined(request)) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        try (PrintWriter out = response.getWriter()) {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.
                    getConnection(DB.DB_URL,
                            DB.DB_USERNAME, DB.DB_PASSWORD);

            Statement stmt = con.createStatement();
            ResultSet RS = stmt.
                    executeQuery("select SurveyID, Username,SurveyName,IsSuspended,IsClosed from Survey");
            Vector<Map> surveys = new Vector<Map>();
            while (RS.next()) {
                Map survey = new HashMap();
                survey.put("surveyid", RS.getString("SurveyID"));
                survey.put("username", RS.getString("Username"));
                survey.put("surveyname", RS.getString("SurveyName"));
                survey.put("issuspended", RS.getString("IsSuspended"));
                survey.put("isclosed", RS.getString("IsClosed"));
                surveys.add(survey);
            }
            request.setAttribute("surveys", surveys);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
            RS.close();
            stmt.close();
            con.close();
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            String data = request.getParameter("data");
            String temp[] = data.split("-");
            String type = temp[0];
            String survey_name = temp[1];
            String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(MYSQL_DRIVER);
            Connection Con = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD);
            Statement Stmt = Con.createStatement();
            if (type.equals("suspend")) {
                Stmt.executeUpdate("UPDATE Survey set IsSuspended=1 where SurveyName='" + survey_name + "';");
            } else {
                Stmt.executeUpdate("UPDATE Survey set IsClosed=1 where SurveyName='" + survey_name + "';");
            }
            Stmt.close();
            Con.close();
        } catch (Exception e) {
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
