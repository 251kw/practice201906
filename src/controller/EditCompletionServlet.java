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
import dto.ShoutDTO;
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
		HttpSession session = request.getSession();

		String loginId =  request.getParameter("loginId");
		String password =  request.getParameter("password");
		String userName =  request.getParameter("userName");
		String icon =  request.getParameter("icon");
		String profile =  request.getParameter("profile");
		String loginId1 = "";

		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();
		//DBへの書き込みメソッドを呼び出す(DBヘ書き込む)
		dbm.editAccount(loginId, password, userName, icon, profile);

		// ログインユーザー本人が自身のアカウントを編集した場合、編集後の新しいユーザー情報を取得し、セッションに保存
		UserDTO user = (UserDTO)session.getAttribute("user");
		loginId1 = user.getLoginId();
		if(loginId1.equals(loginId)) {
		UserDTO user1 = dbm.getLoginUser(loginId, password);
		session.setAttribute("user", user1);
		}
		// アカウント編集後の書き込みリストを取得
		ArrayList<ShoutDTO> list = dbm.getShoutList();

		// リストをセッションに保存
		session.setAttribute("shouts", list);
		//doGet(request, response);
		dispatcher = request.getRequestDispatcher("EditCompletion.jsp");
		dispatcher.forward(request, response);
	}

}
