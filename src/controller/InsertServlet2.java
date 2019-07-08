package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.ShoutDTO;
import dto.UserDTO;

/**
 * Servlet implementation class InsertServlet2
 */
@WebServlet("/IS2")
public class InsertServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet2() {
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
		//doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher dispatcher = null;

		//データベースにINSERTする処理を描く。
//		String LoginId = (String)request.getAttribute("Id");
		/*String Password = (String)request.getAttribute("Pass");
		String Icon = (String)request.getAttribute("Icon");
		String UserName = (String)request.getAttribute("UName");
		String Profile = (String)request.getAttribute("Prof");*/


		HttpSession session = request.getSession();
		String LoginId = (String)session.getAttribute("LoginId");
		String Password = (String)session.getAttribute("Password");
		String Icon = (String)session.getAttribute("Icon");
		String UserName = (String)session.getAttribute("UserName");
		String Profile = (String)session.getAttribute("Profile");

		DBManager dbm = new DBManager();
		UserDTO user = new UserDTO(LoginId,Password,UserName,Icon,Profile);
		ArrayList<ShoutDTO> list = dbm.getShoutList();
		session = request.getSession();
		session.setAttribute("user", user);
		session.setAttribute("shouts", list);

		//DBManagerに新しく作ったInsertNewメソッドを呼ぶ
		/*boolean result = */dbm.InsertNew(user);



		dispatcher = request.getRequestDispatcher("insertComp.jsp");
		dispatcher.forward(request, response);
	}

}
