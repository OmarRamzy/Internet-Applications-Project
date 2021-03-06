package survey_website;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class user_sign_up
 */
@WebServlet("/user_sign_up")
public class user_sign_up extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public user_sign_up() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String data = request.getParameter("data");
        String temp[] = data.split("-");
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        User obj = new User();
        obj.username = temp[0];
        obj.password = temp[1];
        obj.firstName = temp[2];
        obj.lastName = temp[3];
        obj.emailAddress = temp[4];
        try {
            String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(MYSQL_DRIVER);
            Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_db", "root", "");
            Statement Stmt = Con.createStatement();
            ResultSet RS = Stmt.executeQuery("Select Username from user where Username='" + obj.username + "';");
            if (RS.next()) {
                out.print("Username is already exists");
            } else {
                Stmt.executeUpdate("INSERT INTO user VALUES('" + obj.username + "','" + obj.password + "','" + obj.firstName + "','" + obj.lastName + "','" + obj.emailAddress + "',false)");

                //Creation of currentSession
                HttpSession currentSession = request.getSession();
                currentSession.setAttribute("username", obj.username);
                currentSession.setMaxInactiveInterval(5 * 60);

                //Creation of SessionsManager and put the currentSession into it
                Map sessionsManager = SessionManager.get();
                sessionsManager.put(currentSession.getId(), currentSession);

                //Putting the SessionManager into Application Scope
                request.getServletContext().setAttribute("Manager", sessionsManager);

                //Creation of the Cookie and Putting the currentSession into it
                Cookie cookie = new Cookie("UserSession", currentSession.getId());
                cookie.setMaxAge(5 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);

                Con = DriverManager.
                        getConnection("jdbc:mysql://localhost:3306/user", "root", "");
                Stmt = Con.createStatement();
                Stmt.executeUpdate("insert into user values('"
                        + currentSession.getId() + "','" + obj.username + "')");
                request.getServletContext().setAttribute("Mode", "User");
            }
            Stmt.close();
            Con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
