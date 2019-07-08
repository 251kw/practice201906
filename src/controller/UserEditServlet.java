package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UES")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		/*String counter = request.getParameter("counter");
		int n = Integer.parseInt(counter);
		*/
		String[] check = request.getParameterValues("check");
		RequestDispatcher dispatcher = null;
		String message = null;
		if (check == null) {
			message = "選択してください";
			request.setAttribute("alert", message);
			dispatcher = request.getRequestDispatcher("ResultUsers.jsp");
		} else {
			int n = check.length;

			if (n > 1) {
				message = "編集する場合は一つだけ選択してください";
				request.setAttribute("alert", message);
				dispatcher = request.getRequestDispatcher("ResultUsers.jsp");
			} else {
				String loginId = null;
				for(String s : check) {
					loginId = s;
				}
				DBManager dbm = new DBManager();
				UserDTO user = dbm.getCheckUser(loginId);
				request.setAttribute("id", loginId);
				request.setAttribute("name", user.getUserName());
				request.setAttribute("icon", user.getIcon());
				request.setAttribute("pro", user.getProfile());

				dispatcher = request.getRequestDispatcher("UserEditer.jsp");
			}
		}
		dispatcher.forward(request, response);
	}

}
