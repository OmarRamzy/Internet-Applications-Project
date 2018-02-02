package survey_website;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import survey_website.db.DB;

/**
 * Servlet implementation class add_survey
 */
@WebServlet("/add_survey")
public class add_survey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_survey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String survey_name=request.getParameter("survey_name");
		String user_name="a"; // take from session
		String survey_description=request.getParameter("description");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date(0);
		String s=dateFormat.format(date);
		String date1 = request.getParameter("date");
		String time = request.getParameter("time");
		System.out.println(date1+ " " + time);
		String close_time=date1+" "+time;
		try{
			String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
			Class.forName(MYSQL_DRIVER);
			Connection Con = DriverManager.getConnection(DB.DB_URL,DB.DB_USERNAME,DB.DB_PASSWORD);
			Statement Stmt = Con.createStatement();
			ResultSet rs = Stmt.executeQuery("SELECT MAX(SurveyID) AS SurveyID FROM survey;");
			rs.next();	
			int surveyID=rs.getInt(1)+1;
			//System.out.println(surveyID);
			Stmt.executeUpdate("INSERT INTO survey VALUES(default,'"+user_name+"','"+survey_name+"','"+survey_description+"','"+s+"','"+close_time+"',0,0)");
			
			Stmt.close();
			Con.close(); 
			request.setAttribute("surveyID",surveyID);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Question_type.jsp");  
			if (dispatcher != null){  
			dispatcher.forward(request, response);  
			}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		doGet(request, response);
	}

}
