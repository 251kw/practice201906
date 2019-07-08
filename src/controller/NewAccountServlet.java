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
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/NAS")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 直接入力された場合index.jspに戻る
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String link = request.getParameter("link");
		String id = request.getParameter("hiddenId");
		//		String link = (String)request.getAttribute("LINK");
		RequestDispatcher dispatcher = null;
		if (link == null) { //linkの値が取れなかったらindex.jspへ
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} else if ("1".equals(link)) { //NewAccountCheckedからlinkの値が正確に来た場合のみNewAccount.jspへ
			String loginId = request.getParameter("loginId");
			String username = request.getParameter("username");
			String icon = request.getParameter("icon");
			String profile = request.getParameter("profile");
			String password = ""; // passwordは渡さないので空白
			String oldId = request.getParameter("oldId");
			if(oldId != null) {
				request.setAttribute("id", loginId);
				request.setAttribute("pass", password);
				request.setAttribute("name", username);
				request.setAttribute("icon", icon);
				request.setAttribute("pro", profile);
				request.setAttribute("oldid", oldId);
				dispatcher = request.getRequestDispatcher("UserEditer.jsp");
				dispatcher.forward(request, response);
			}
			request.setAttribute("ID", loginId);
			request.setAttribute("PASS", password);
			request.setAttribute("NAME", username);
			request.setAttribute("ICON", icon);
			request.setAttribute("PRO", profile);
			request.setAttribute("hiddenId", id);
			dispatcher = request.getRequestDispatcher("NewAccount.jsp");
			dispatcher.forward(request, response);
		} else { //それ以外はindex.jspへ
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
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
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		username = username.replaceFirst("^[\\h]+", "").replaceFirst("[\\h]+$", "");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		String id = request.getParameter("hiddenId");
		String editer = request.getParameter("Editer");
		String oldId = request.getParameter("oldId");
		RequestDispatcher dispatcher = null;
		String message = null;
		int errorCounter = 0;
		boolean noid = false;
		boolean nopass = false;
		if (loginId.equals("")) {
			message = "ログインIDは必須入力です";
			request.setAttribute("idErr1", message);
			errorCounter++;
			noid = true;
		}
		if (password.equals("")) {
			message = "パスワードは必須入力です";
			request.setAttribute("passErr1", message);
			errorCounter++;
			nopass = true;
		}
		if (username.equals("")) {
			message = "名前は必須入力です※空白文字のみNG";
			request.setAttribute("nameErr", message);
			errorCounter++;
		}
		DBManager dbm = new DBManager();
		UserDTO user = dbm.getCheckUser(loginId);
		if ("FromUserEditer".equals(editer)) {
			UserDTO olduser = dbm.getCheckUser(oldId);
			if (user != null) {
				if (!(loginId.equals(olduser.getLoginId()))) {
					message = "そのIDは他の人が使用しています";
					request.setAttribute("idErr2", message);
					errorCounter++;
				}
			}
		} else {
			//UserDTO user = dbm.getCheckUser(loginId);
			if (user != null) {
				message = "そのIDは他の人が使用しています";
				request.setAttribute("idErr2", message);
				errorCounter++;
			}
		}
		if (noid == false) {
			if (!(loginId.matches("^[0-9a-zA-Z]+$"))) {
				message = "半角英数字で入力してください";
				request.setAttribute("idErr3", message);
				errorCounter++;
			}
		}
		if (nopass == false) {
			if (!(password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$"))) {
				message = "半角英数字記号で入力してください";
				request.setAttribute("passErr2", message);
				errorCounter++;
			}
		}
		/*if (!(Charset.forName("MS932").newEncoder().canEncode(loginId)
				&& Charset.forName("MS932").newEncoder().canEncode(password)
				&& Charset.forName("MS932").newEncoder().canEncode(username)
				&& Charset.forName("MS932").newEncoder().canEncode(profile))) {
			// 変換不可
			message ="文字化けを起こさない文字で書いてください";
			request.setAttribute("alert1", message);
			errorCounter++;
		}*/if (loginId.length() > 32 || password.length() > 32 || username.length() > 64 || profile.length() > 128) {

			// ログインIDかパスワードどちらか、もしくは双方入力限界値を超えているなら
			message = "指定文字数以内にしてください";
			request.setAttribute("alert2", message);
			errorCounter++;
		}
		if ("FromUserEditer".equals(editer)) {
			request.setAttribute("id", loginId);
			request.setAttribute("pass", password);
			request.setAttribute("name", username);
			request.setAttribute("icon", icon);
			request.setAttribute("pro", profile);
			request.setAttribute("oldid", oldId);
			if (errorCounter == 0) {
				dispatcher = request.getRequestDispatcher("UserEditerChecked.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("UserEditer.jsp");
			}
		} else {

			request.setAttribute("ID", loginId);
			request.setAttribute("PASS", password);
			request.setAttribute("NAME", username);
			request.setAttribute("ICON", icon);
			request.setAttribute("PRO", profile);
			request.setAttribute("hiddenId", id);

			if (errorCounter == 0) {
				dispatcher = request.getRequestDispatcher("NewAccountCheck.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("NewAccount.jsp");
			}
		}
		dispatcher.forward(request, response);
	}
}
