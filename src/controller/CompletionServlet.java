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
 * Servlet implementation class CompletionServlet
 */
@WebServlet("/cs")
public class CompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
/*    public CompletionServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("loginId");
		String password = (String)session.getAttribute("password");
		String userName = (String)session.getAttribute("userName");
		String icon = (String)session.getAttribute("icon");
		String profile = (String)session.getAttribute("profile");

		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();
		//DBへの書き込みメソッドを呼び出す(DBヘ書き込む)
		dbm.dbRegistration(loginId,password,userName,icon,profile);

		//新規登録された情報を検索し、アカウント情報をセッションに保存
		UserDTO user = dbm.getLoginUser(loginId, password);
			// ユーザ情報を取得できたら、書き込み内容リストを取得
			ArrayList<ShoutDTO> list = dbm.getShoutList();

			// ログインユーザ情報、書き込み内容リストとしてセッションに保存
			session.setAttribute("user", user);
			session.setAttribute("shouts", list);

		dispatcher = request.getRequestDispatcher("RegiCompletion.jsp");
		dispatcher.forward(request, response);


	}

}
