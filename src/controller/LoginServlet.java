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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String INPUT_DISP = "/register.jsp";
	private final String LOGIN_DISP = "/index.jsp";
	private final String TOP_DISP = "/top.jsp";

	// 直接アクセスがあった場合は index.jsp に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		gotoPage(request, response,LOGIN_DISP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ボタンの判定
		String action = request.getParameter("action");

		HttpSession session = request.getSession();

		//ログインボタンが押されたら
		if ("login".equals(action)) {

			//入力情報取得
			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");

			//IDの保持
			request.setAttribute("ID", loginId);
			String message = null;

			//入力チェック
			if (loginId.equals("") || password.equals("")) {

				// ログインID かパスワードどちらか、もしくは双方未入力なら
				message = "※ログインIDとパスワードは必須入力です";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				// index.jsp に処理を転送
				gotoPage(request, response,LOGIN_DISP);

			} else {

				// ログイン認証を行い、ユーザ情報を取得
				DBManager dbm = new DBManager();
				UserDTO user = dbm.getLoginUser(loginId, password);

				if (user != null) {

					// ユーザ情報を取得できたら、書き込み内容リストを取得
					ArrayList<ShoutDTO> list = dbm.getShoutList();

					// ログインユーザ情報、書き込み内容リストとしてセッションに保存
					session.setAttribute("user", user);
					session.setAttribute("shouts", list);

					// 処理の転送先を top.jsp に指定
					gotoPage(request,response,TOP_DISP);

					// ユーザ情報が取得できない場合
				} else {

					// エラーメッセージをリクエストオブジェクトに保存
					message = "※ログインIDまたはパスワードが違います";
					request.setAttribute("alert", message);

					// 処理の転送先を index.jsp に指定
					gotoPage(request,response,LOGIN_DISP);
				}
			}
		}

		//新規登録ボタンが押されたら
		if("new".equals(action)) {

			//セッション破棄
			session.removeAttribute("user");

			//登録画面に処理を転送
			gotoPage(request,response,INPUT_DISP);
		}
	}

	//転送処理
		private void gotoPage(HttpServletRequest request,
				HttpServletResponse response, String page) throws ServletException, IOException {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
}
