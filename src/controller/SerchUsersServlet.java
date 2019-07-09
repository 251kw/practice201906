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
 * Servlet implementation class SerchUsersServlet
 */
@WebServlet("/SUS")
public class SerchUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SerchUsersServlet() {
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
		//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;

		// 値を取得
		String loginId = request.getParameter("loginId");
		String username = request.getParameter("username");
		String[] icon = request.getParameterValues("icon");
		String profile = request.getParameter("profile");
		if (icon == null) {
			icon = new String[1];
			icon[0] = "";
		}
		String back = request.getParameter("back");
		if ("true".equals(back)) {
			String bid = request.getParameter("backloginid");
			String bun = request.getParameter("backusername");
			String bpr = request.getParameter("backprofile");
			String[] bic = request.getParameterValues("backicon");

			request.setAttribute("bid", bid);
			request.setAttribute("bun", bun);
			request.setAttribute("bpr", bpr);
			request.setAttribute("bic", bic);
			dispatcher = request.getRequestDispatcher("SerchUsers.jsp");
		} else {
			// DBで検索
			DBManager dbm = new DBManager();
			ArrayList<UserDTO> list = dbm.serchUsers(loginId, username, icon, profile);
			if (list == null || list.size() == 0) {
				String message = "ユーザーが見つかりませんでした";
				request.setAttribute("noUser", message);
				request.setAttribute("bid", loginId);
				request.setAttribute("bun", username);
				request.setAttribute("bic", icon);
				request.setAttribute("bpr", profile);
				dispatcher = request.getRequestDispatcher("SerchUsers.jsp");
			} else {
				HttpSession session = request.getSession();

				// sessionスコープでリストを保存
				session.setAttribute("List", list);

				request.setAttribute("SaveLoginId", loginId);
				request.setAttribute("SaveUserName", username);
				request.setAttribute("SaveIcon", icon);
				request.setAttribute("SaveProfile", profile);

				dispatcher = request.getRequestDispatcher("ResultUsers.jsp");
			}
		}
		dispatcher.forward(request, response);
	}

}
