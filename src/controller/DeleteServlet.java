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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DS")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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

		String userName = request.getParameter("userName");
		String loginId = request.getParameter("loginId");
		String icon = request.getParameter("icon");
		String date = request.getParameter("date");
		String writing = request.getParameter("writing");

		//deleteCheck.jspに送るために保存
		//DeleteServlet2.javaでも使えるように。
		HttpSession session = request.getSession();
		session.setAttribute("dC_userName", userName);
		session.setAttribute("dC_loginId", loginId);
		session.setAttribute("dC_icon", icon);
		session.setAttribute("dC_date", date);
		session.setAttribute("dC_writing", writing);


		dispatcher = request.getRequestDispatcher("deleteCheck.jsp");
		dispatcher.forward(request, response);


	}

}
