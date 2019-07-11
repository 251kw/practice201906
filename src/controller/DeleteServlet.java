package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ShoutDTO;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 直接アクセスがあった場合は index.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
		dispatcher.forward(request, response);
	}

	// top.jsp の「削除」ボタンから呼ばれる
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String shout =request.getParameter("shout");
		RequestDispatcher dispatcher;

		ShoutDTO shouter = new ShoutDTO();
		shouter.setWriting(shout);

		HttpSession session = request.getSession();
		session.setAttribute("shout", shout);


		// confirm2.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("./confirm2.jsp");
		dispatcher.forward(request, response);
	}
}

