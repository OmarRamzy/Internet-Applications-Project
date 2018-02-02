package survey_website;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import survey_website.db.DB;

/**
 * Servlet implementation class add_text_free_question
 */
@WebServlet("/add_text_free_question")
public class add_text_free_question extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public add_text_free_question() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String question = request.getParameter("question");
		String question_type = "free";
		int surveyID = Integer.parseInt(request.getParameter("surveyID"));
		int question_num = Integer.parseInt(request.getParameter("Q_num"));
		int is_monatory = Integer.parseInt(request.getParameter("is_mondatory"));
		int QID;
		try {
			String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
			Class.forName(MYSQL_DRIVER);
			Connection Con = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD);
			Statement Stmt = Con.createStatement();
			ResultSet rs = Stmt.executeQuery("SELECT MAX(QuestionID) AS QuestionID FROM question;");
			rs.next();
			QID = rs.getInt(1) + 1;
			Stmt.executeUpdate("INSERT INTO question VALUES(default,'" + surveyID + "','" + question + "','" + question_type + "','"+question_num+"','"+is_monatory+"')");
			Stmt.close();
			Con.close();
			question_num++;
			request.setAttribute("surveyID",surveyID);
			request.setAttribute("Q_num",question_num);
			RequestDispatcher dispatcher = request.getRequestDispatcher("add_question.jsp");  
			if (dispatcher != null){  
			dispatcher.forward(request, response);  
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}

		doGet(request, response);
	}

}
