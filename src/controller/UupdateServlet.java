package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UupdateServlet
 */
@WebServlet("/UUDS")
public class UupdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UupdateServlet() {
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


		String loginId = request.getParameter("newID");
		String password = request.getParameter("newPW");
		String userName = request.getParameter("newUN");
		String icon = request.getParameter("newIC");
		String profile = request.getParameter("newPF");

		HttpSession session = request.getSession();
		session.setAttribute("loginId", loginId);
		session.setAttribute("password", password);
		session.setAttribute("userName", userName);
		session.setAttribute("icon", icon);
		session.setAttribute("profile", profile);

		dispatcher = request.getRequestDispatcher("uudCheck.jsp");
		dispatcher.forward(request, response);
	}

}
