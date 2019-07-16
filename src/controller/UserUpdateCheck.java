package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDTO;

/**
 * Servlet implementation class UserUpdateCheck
 */
@WebServlet("/UUC")
public class UserUpdateCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();

		String sLoginId = (String) session.getAttribute("beforeUpdateLoginId");
		String sltduId = request.getParameter("sltduId");
		//String newerId = request.getParameter("newerId");
		String newerPw = request.getParameter("newerPw");
		String newerName = request.getParameter("newerName");
		String genderIcon = request.getParameter("gender");
		String newerProf = request.getParameter("newerProfile");

		UserDTO newerUser = new UserDTO();
		newerUser = null;

		String message = null; //エラーメッセージ登録用
		String message2 = null;
		String message3 = null;
		String message4 = null;

		String[] uk = request.getParameterValues("loginId");//Like検索する際に""空文字だとすべてが検索に引っかかるので入力なければＤＢ上該当の無いスペースを代入
		String uk2 = request.getParameter("userName");
		String uk3 = request.getParameter("profile");
		String uI = request.getParameter("icon");
		String uI2 = request.getParameter("icon2");
		String uI3 = request.getParameter("icon3");
		String uI4 = request.getParameter("icon4");
		String uI5 = request.getParameter("icon5");

		if (/*newerId.equals("") ||*/ newerPw.equals("") || newerName.equals("") || genderIcon.equals("")
				/*|| (!(newerId.matches("^[0-9a-zA-Z]+$")))*/||(!(newerPw.matches("^[0-9a-zA-Z]+$")))) {
			/*if (newerId.equals("")) {
				message2 = "ログインIDが入力されていません。";
			}*/
			/*if (!(newerId.matches("^[0-9a-zA-Z]+$"))) {//文字列に半角英数字以外の文字がないかチェック（matchesメソッド
				message2 = "ログインIDは半角英数字のみご利用いただけます。";
			}*/
			if (newerPw.equals("")) {
				message2 = "パスワードが入力されていません。";
			}
			if (!(newerPw.matches("^[0-9a-zA-Z]+$"))) {
				message3 = "パスワードは半角英数字のみご利用いただけます。";
			}
			if (newerName.equals("")) {
				message4 = "表示名が入力されていません。";
			}
			request.setAttribute("sUser.userName", newerName);
			request.setAttribute("sUser.profile", newerProf);
			request.setAttribute("sUserIcon", genderIcon);
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			request.setAttribute("alert2", message2);
			request.setAttribute("alert3", message3);
			request.setAttribute("alert4", message4);

			dispatcher = request.getRequestDispatcher("userUpdateNyuuryoku.jsp");
			//dispatcher.forward(request, response);

		} else {
				//requestUserDTO型のnewerUserを保存
				newerUser = new UserDTO(sLoginId, newerPw, newerName, genderIcon, newerProf);
				request.setAttribute("newerUser2", newerUser);//key設定して取り出せるようにする。
				request.setAttribute("nPw", newerPw);

				request.setAttribute("sltduId", sltduId);
				request.setAttribute("uk", uk[0]);
				request.setAttribute("uk2", uk2);
				request.setAttribute("uk3", uk3);
				request.setAttribute("uI", uI);
				request.setAttribute("uI2", uI2);
				request.setAttribute("uI3", uI3);
				request.setAttribute("uI4", uI4);
				request.setAttribute("uI5", uI5);

				dispatcher = request.getRequestDispatcher("userUpdateKakunin.jsp");
			}
			dispatcher.forward(request, response);
		}
	}

