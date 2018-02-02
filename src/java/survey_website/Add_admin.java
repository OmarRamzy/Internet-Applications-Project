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
 * Servlet implementation class Add_admin
 */
@WebServlet("/Add_admin")
public class Add_admin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_admin() {
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
        String data = (String) request.getParameter("data");
        String temp[] = data.split("-");
        String userName = temp[0];
        String password = temp[1];
        try {
            String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(MYSQL_DRIVER);
            Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_db", "root", "");
            Statement Stmt = Con.createStatement();
            ResultSet RS = Stmt.executeQuery("Select AdminUsername from admin where AdminUsername='" + userName + "';");
            if (RS.next()) {
                out.print("Admin username is Already Exist.");
            } else {
                Stmt.executeUpdate("INSERT INTO admin VALUES('" + userName + "','" + password + "')");
                Stmt.close();
                Con.close();
                out.print(userName + " has been added Successfully.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
