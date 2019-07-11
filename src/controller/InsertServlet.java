package controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/IS")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//insertCheckからhiddenで送ってきた値を受け取る処理。
		String LId = request.getParameter("Id");
		String PSW = request.getParameter("Pass");
		String UNM = request.getParameter("UName");
		String IC = request.getParameter("Ic");
		String PRF = request.getParameter("Prof");




		//上の処理で受け取った値をindex。ｊｓｐで値をとるためリクエストに保存
		//HttpSession session = request.getSession();
		request.setAttribute("Id", LId);
		request.setAttribute("Pass", PSW);
		request.setAttribute("Un", UNM);
		request.setAttribute("Ic", IC);
		request.setAttribute("Pf", PRF);

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("insert.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String LoginId = request.getParameter("loginId");
		String Password = request.getParameter("password");
		String UserName = request.getParameter("userName");
		String Icon = request.getParameter("icon");
		String Profile = request.getParameter("profile");
		boolean STATE = true;
		String message1 = null;
		String message2 = null;
		String message3 = null;
		//String message4 = null;
		RequestDispatcher dispatcher = null;

		if(LoginId.equals("") || Password.equals("") ||
				UserName.equals("") ) {
			message1 = "全ての項目を入力してください。";
			request.setAttribute("alert1", message1);

			dispatcher = request.getRequestDispatcher("insert.jsp");
			dispatcher.forward(request, response);

		}else {
			//Idに被りがないかチェック
			DBManager dbm = new DBManager();
			boolean result = dbm.serchData(LoginId);
			if(result == false) {
				message2 = "このIDは既に存在します。";
				request.setAttribute("alert2", message2);
				STATE = false;
			}
			//Idがアルファベットかチェック
			Pattern p1 = Pattern.compile("^[0-9a-zA-Z]+$");
			Matcher m1 = p1.matcher(LoginId);
			if(m1.find()==false) {
				message3 = "IDはアルファベットで入力してください。";
				request.setAttribute("alert3", message3);
				STATE = false;
			}
//			//ユーザネームのチェック
//			Pattern p2 = Pattern.compile("^[^<>{}\"/|;:.,~!?@#$%^=&*\\]+$");
//			Matcher m2 = p2.matcher(UserName);
//			if(m2.find()==true) {
//				message4 = "ユーザーネームに記号は使えません。";
//				request.setAttribute("alert4", message4);
//				STATE = false;
//			}
			if(STATE == false) {
				request.setAttribute("Id", LoginId);
				request.setAttribute("Pass", Password);
				request.setAttribute("Un", UserName);
				request.setAttribute("Ic", Icon);
				request.setAttribute("Pf", Profile);

				dispatcher = request.getRequestDispatcher("insert.jsp");
				dispatcher.forward(request, response);
			}



				//insertCheck.jspにつなげるためにリクエストに保存
//				request.setAttribute("LoginId", LoginId);
//				request.setAttribute("Password",Password );
//				request.setAttribute("Icon", Icon);
//				request.setAttribute("UserName", UserName);
//				request.setAttribute("Profile", Profile);

				//servlet2に値を渡すためにセッションに保存
				HttpSession session = request.getSession();
				session.setAttribute("LoginId", LoginId);
				session.setAttribute("Password", Password);
				session.setAttribute("Icon", Icon);
				session.setAttribute("UserName", UserName);
				session.setAttribute("Profile", Profile);

				dispatcher = request.getRequestDispatcher("insertCheck.jsp");
				dispatcher.forward(request, response);

		}




	}

}
