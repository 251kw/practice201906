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
 * Servlet implementation class AccountDeleteServlet
 */
@WebServlet("/ads")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String loginId1 = "";

		@SuppressWarnings("unchecked")
		ArrayList<String> list1 = (ArrayList<String>)session.getAttribute("listLoginId");

		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();

		//DBからアカウントを消去するメソッドを呼び出す
		for(String s : list1) {
			dbm.deleteAccount(s);
		}

		// ログインユーザー本人が自身のアカウントを削除した場合、セッションを破棄する
		UserDTO user = (UserDTO)session.getAttribute("user");
		loginId1 = user.getLoginId();
		if(list1.contains(loginId1)) {
			session.invalidate();
			dispatcher = request.getRequestDispatcher("OwnAccountDeleted.jsp");
		}else {

		// 書き込み内容消去後のリストを取得
		ArrayList<ShoutDTO> list = dbm.getShoutList();

		// リストをセッションに保存
		session.setAttribute("shouts", list);
		dispatcher = request.getRequestDispatcher("AccountDeleteCompletion.jsp");
		}
		dispatcher.forward(request, response);

		//doGet(request, response);
	}

}
