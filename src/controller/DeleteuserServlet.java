package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteuserServlet")
public class DeleteuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 直接アクセスがあった場合は top.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
		dispatcher.forward(request, response);
	}

	// find.jsp の「削除」ボタンから呼ばれる
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String writing =request.getParameter("writing");
		String shoutsId = request.getParameter("shoutsId");
		String[] deleteuser = request.getParameterValues("deleteuser");
		RequestDispatcher dispatcher;

		HttpSession session = request.getSession();
		session.setAttribute("writing", writing);
		session.setAttribute("shoutsId", shoutsId);
		session.setAttribute("deleteuser", deleteuser);


		// confirm2.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("./confirm3.jsp");
		dispatcher.forward(request, response);
	}
}

