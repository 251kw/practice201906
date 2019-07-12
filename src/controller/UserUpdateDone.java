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

/**
 * Servlet implementation class UserUpdateDone
 */
@WebServlet("/UUD")
public class UserUpdateDone extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateDone() {
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
		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		DBManager dbm = new DBManager();

		//String sLoginId = (String) session.getAttribute("beforeUpdateLoginId");
		String userId = (String) session.getAttribute("beforeUpdateUserId");
		//String newerUser2 = (String) session.getAttribute("beforeUpdateUserId");
		String newerId = request.getParameter("newerId");
		String newerPw = request.getParameter("newerPw");
		String newerName = request.getParameter("newerName");
		String genderIcon = request.getParameter("gender");
		String newerProf = request.getParameter("newerProfile");
		boolean bln;
		bln = dbm.setUserUpdate(newerPw, newerName, genderIcon, newerProf, userId);

		if(bln==true) {
			request.setAttribute("updateId", newerId);
			request.setAttribute("updatePw", newerPw);
			dispatcher = request.getRequestDispatcher("userUpdateKanryo.jsp");
		}else {
			String message = "予期せぬエラーが発生しました。";
			request.setAttribute("alert", message);
			request.setAttribute("sUser.password", newerPw);
			request.setAttribute("sUser.userName", newerName);
			request.setAttribute("sUserIcon", genderIcon);
			request.setAttribute("sUser.profile", newerProf);
			dispatcher = request.getRequestDispatcher("userUpdateNyuuryoku.jsp");
		}
		dispatcher.forward(request, response);
	}

}
