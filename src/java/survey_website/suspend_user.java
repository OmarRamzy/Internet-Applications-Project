package survey_website;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class suspend_user
 */
@WebServlet("/suspend_user")
public class suspend_user extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public suspend_user() {
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
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            String user_name = request.getParameter("user_name");
            String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(MYSQL_DRIVER);
            Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_db", "root", "");
            Statement Stmt = Con.createStatement();
            Stmt.executeUpdate("UPDATE user SET IsSuspended=1 WHERE Username='" + user_name + "'");
            ResultSet RS = Stmt.executeQuery("Select Username from user where Username='" + user_name + "';");
            if (RS.next()) {
                out.print(user_name + " has been suspended.");
            } else {
                out.print("No Such a username");
            }
            Stmt.close();
            Con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //doGet(request, response);
    }

}
