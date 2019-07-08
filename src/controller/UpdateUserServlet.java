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

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UUS")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
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

		String loginId = request.getParameter("loginId");	// 変更したいユーザーのloginId
		String password = request.getParameter("password");	// pass
		String username = request.getParameter("username");	// username
		String icon = request.getParameter("icon");			// icon
		String profile = request.getParameter("profile");	// profile
		String oldId = request.getParameter("oldId");		// 変更したいユーザーの前のloginId
		int kosuu = 0;	// shoutの数を数えるため
		boolean which = false;
		boolean check = false;

		DBManager dbm = new DBManager();
		RequestDispatcher dispatcher = null;
		which = dbm.updateUser(loginId, password, username, icon, profile, oldId); // ユーザー情報の更新
		kosuu = dbm.checkShouts(loginId);	// 更新したユーザーのつぶやきの数を数える
		check = dbm.changeShouts(loginId, username, icon, kosuu);	// 更新したユーザーのつぶやきのiconとユーザー名とIDの更新

		// もし更新するユーザーが自分自身ならUserDTOを更新する
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");
		if(oldId.equals(user.getLoginId())) {
			user = dbm.getLoginUser(loginId, password);
			session.setAttribute("user", user);
		}
		request.setAttribute("id",loginId);
		request.setAttribute("pass",password);
		if(which == true) {
			if(check == true) {
			dispatcher = request.getRequestDispatcher("FinishUpdateUser.jsp");
			}else {
				request.setAttribute("name",username);
				request.setAttribute("icon",icon);
				request.setAttribute("pro",profile);
				request.setAttribute("oldid",oldId);
				request.setAttribute("alert1", "shoutsの更新に失敗しました");
				dispatcher = request.getRequestDispatcher("UserEditer.jsp");
			}
		}else {
			request.setAttribute("name",username);
			request.setAttribute("icon",icon);
			request.setAttribute("pro",profile);
			request.setAttribute("oldid",oldId);
			request.setAttribute("alert1", "更新に失敗しました");
			dispatcher = request.getRequestDispatcher("UserEditer.jsp");
		}

		dispatcher.forward(request, response);

	}

}
