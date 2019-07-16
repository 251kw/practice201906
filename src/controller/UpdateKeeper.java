package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateKeeper
 */
@WebServlet("/UK")
public class UpdateKeeper extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateKeeper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher dispatcher = null;

		String sltduId = request.getParameter("sltduId");
		String newerPw = request.getParameter("newerPw");
		String newerName = request.getParameter("newerName");
		String genderIcon = request.getParameter("gender");
		String newerProf = request.getParameter("newerProfile");

		request.setAttribute("sltduId", sltduId);
		request.setAttribute("sUser.password", newerPw);
		request.setAttribute("sUserIcon", genderIcon);
		request.setAttribute("sUser.userName", newerName);
		request.setAttribute("sUser.profile", newerProf);

		dispatcher = request.getRequestDispatcher("userUpdateNyuuryoku.jsp");
		dispatcher.forward(request, response);
	}

}
