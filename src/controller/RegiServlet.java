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
 * Servlet implementation class RegiServlet
 */
@WebServlet("/rs")
public class RegiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

/*    public RegiServlet() {
        super();

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
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName1 = request.getParameter("userName");
		String userName = "";
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		userName = userName1.replaceFirst("^[\\h]+", "").replaceFirst("[\\h]+$", "");

		HttpSession session = request.getSession();
		session.setAttribute("loginId",loginId);
		session.setAttribute("password",password);
		session.setAttribute("userName",userName);
		session.setAttribute("icon",icon);
		session.setAttribute("profile",profile);

		DBManager dbm = new DBManager();

		RequestDispatcher dispatcher = null;
		String messagei = null;
		String message2 = null;
		String messageu = null;
		String messagep = null;

		if (loginId.equals("") ) {
			// 必須入力事項が未入力なら
			messagei = "ログインIDは必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert_i", messagei);
		}

		//パスワードが未入力なら
		if (password.equals("")) {
			messagep = "パスワードは必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert_p", messagep);
		}

		//ユーザーネームが未入力なら
		if (userName.equals("")) {
			messageu = "ユーザー名は必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert_u", messageu);
		}

				// ログインIDが重複している場合
				// エラーメッセージをリクエストオブジェクトに保存

			UserDTO user1 = dbm.checkLoginID(loginId);
		if(user1 != null){
				message2 = "このログインIDは既に使用されています";
				request.setAttribute("alert2", message2);
		}
		//エラーメッセージが一つでもあれば転送
		if(messagei != null || messagep != null || messageu != null || message2 != null)  {
		dispatcher = request.getRequestDispatcher("Registration.jsp");
		}else {
		//入力エラーがなければ確認画面へ
		dispatcher = request.getRequestDispatcher("Confirmation.jsp");
		}
	dispatcher.forward(request, response);
	}

}
