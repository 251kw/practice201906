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
import dto.UserDTO;

/**
 * Servlet implementation class CheckDeleteAccountServlet
 */
@WebServlet("/cdas")
public class CheckDeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckDeleteAccountServlet() {
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
        // 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String[] users = request.getParameterValues("user");
		DBManager dbm = new DBManager();
		ArrayList<String> listLoginId = new ArrayList<String>();
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String message_n = null;

		if (users == null ){
			message_n = "削除するユーザーをチェックしてください";
			request.setAttribute("alert_n", message_n);
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("Serch.jsp");
		dispatcher.forward(request, response);
		}

		for(String b : users){
			listLoginId.add(b);
		}

		for(String s : users){
			list.add(dbm.checkLoginID(s));
		}


		HttpSession session = request.getSession();
		session.setAttribute("listLoginId",listLoginId);
		request.setAttribute("accounts", list);



		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("Deleting.jsp");
		dispatcher.forward(request, response);
	}

}
