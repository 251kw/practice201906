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

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//直接アクセスがあった場合は index.jsp に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	// index.jsp の新規登録から呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		String message1 = null;
		String message2 = null;
		String message3 = null;
		String message4 = null;
		String message5 = null;
		String message6 = null;

		RequestDispatcher dispatcher = null;
		HttpSession session2 = request.getSession();

		UserDTO user = new UserDTO();
		user.setLoginId(loginId);
		user.setPassword(password);
		user.setUserName(userName);
		user.setIcon(icon);
		user.setProfile(profile);

		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		boolean flag = false;


		if ((("").equals(loginId) || ("").equals(password))){
			// ログインID かパスワードどちらか、もしくは双方未入力なら
			message1 = "ログインIDとパスワードは必須入力です";
			flag = true;

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert1", message1);

			session2.setAttribute(loginId,loginId);
			session2.setAttribute(password,password);

		}


		if ((32 < loginId.length() || 32 < password.length())) {
			//loginIdとpasswordが32文字以内でなければ
			message6 = "loginIdまたはpasswordは32文字以内でおねがいします。";
			flag = true;

			request.setAttribute("alert6", message6);
			session2.setAttribute(loginId,loginId);
			session2.setAttribute(password,password);
		}

		if (!((password).matches("^[0-9a-zA-Z]+$"))) {
			//パスワードが半角英数字じゃなければ
			message4 = "パスワードは半角英数字でお願いします。";
			flag = true;

			request.setAttribute("alert4", message4);
			session2.setAttribute(password,password);
		}


		DBManager ndb = new DBManager();
		ndb.loginsearchUser(loginId);

		if (ndb.loginsearchUser(loginId)) {
			message2 = "既にこのloginIdは使われています。";
			flag = true;

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert2", message2);
			session2.setAttribute(loginId,loginId);

		}

		if ((64 < userName.length())) {
			//ユーザーネームが64文字以内でなければ
			message3 = "ユーザーネームは64文字以内でおねがいします。";
			flag = true;

			request.setAttribute("alert3", message3);
			session2.setAttribute(userName, userName);

		}

		if ((icon).equals("who")) {
			//アイコンを選択していなければ
			message5 = "アイコンを選択してください。";
			flag = true;

			request.setAttribute("alert5", message5);
			session2.setAttribute(icon, icon);

		}

		if(flag) {
			dispatcher = request.getRequestDispatcher("/create.jsp");
			dispatcher.forward(request, response);
		}


		dispatcher = request.getRequestDispatcher("/confirm.jsp");
		dispatcher.forward(request, response);
	}
}
