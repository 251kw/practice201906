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
import dto.UserDTO;

@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");

		DBManager ndb = new DBManager();
		ndb.setnewUser(user);

		 RequestDispatcher dispatcher =
	                getServletContext().getRequestDispatcher("/register.jsp");
		 dispatcher.include(request, response);


	}
}
