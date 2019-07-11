package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;

@WebServlet("/DeleteRegServlet")
public class DeleteRegServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String shout = (String) session.getAttribute("shout");


		DBManager des = new DBManager();
		des.deleteshouts(shout);

		 RequestDispatcher dispatcher =
	                getServletContext().getRequestDispatcher("/Delete.jsp");
		 dispatcher.include(request, response);


	}
}
