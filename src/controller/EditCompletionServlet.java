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
 * Servlet implementation class EditCompletionServlet
 */
@WebServlet("/ec")
public class EditCompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCompletionServlet() {
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
        // 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId =  request.getParameter("loginId");
		String password =  request.getParameter("password");
		String userName =  request.getParameter("userName");
		String icon =  request.getParameter("icon");
		String profile =  request.getParameter("profile");

		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();
		//DBへの書き込みメソッドを呼び出す(DBヘ書き込む)
		dbm.editAccount(loginId, password, userName, icon, profile);

		// アカウント編集後の新しいユーザー情報を取得し、セッションに保存
		UserDTO user = dbm.getLoginUser(loginId, password);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		//doGet(request, response);
		dispatcher = request.getRequestDispatcher("EditCompletion.jsp");
		dispatcher.forward(request, response);
	}

}
