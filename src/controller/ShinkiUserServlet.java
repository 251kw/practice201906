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
 * Servlet implementation class ShinkiUserServlet
 */
@WebServlet("/SUS")
public class ShinkiUserServlet extends  HttpServlet{//サーブレットから書き換えた
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShinkiUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		String newerId = request.getParameter("newerId");
		String newerPw = request.getParameter("newerPw");
		String newerName = request.getParameter("newerName");
		String genderIcon = request.getParameter("gender");
		String newerProf = request.getParameter("newerProfile");

		UserDTO newerUser = new UserDTO();

		String message = null; //エラーメッセージ登録用
		String message2 = null;
		String message3 = null;
		String message4 = null;

		if (newerId.equals("") || newerPw.equals("") || newerName.equals("")|| genderIcon.equals("")) {
				if(newerId.equals("")) {
					message2 = "ログインIDが入力されていません。";
				}
				if(!(newerId.matches("^[0-9a-zA-Z]+$"))) {//文字列に半角英数字以外の文字がないかチェック（matchesメソッド
					message2 = "ログインIDは半角英数字のみご利用いただけます。";
				}
				if(newerPw.equals("")) {
					message3 = "パスワードが入力されていません。";
				}
				if(!(newerPw.matches("^[0-9a-zA-Z]+$"))) {
					message3 = "パスワードは半角英数字のみご利用いただけます。";
				}
				if(newerName.equals("")) {
					message4 = "表示名が入力されていません。";
				}
			request.setAttribute("nName", newerName);
			request.setAttribute("nProf", newerProf);
			request.setAttribute("nIcon", genderIcon);
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			request.setAttribute("alert2", message2);
			request.setAttribute("alert3", message3);
			request.setAttribute("alert4", message4);

			// shinkiUser.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("shinkiUser.jsp");
			dispatcher.forward(request, response);

		}else {//必須項目が埋まっていたら
			DBManager dbm = new DBManager();
			newerUser = dbm.getShinkiUser(newerId);//DBManagerに追加したメソッドで入力されたIDが存在しているかチェック　未登録ならnull代入
			if((!(newerId.matches("^[0-9a-zA-Z]+$"))) || (!(newerPw.matches("^[0-9a-zA-Z]+$"))) || (newerUser != null)) {
			if(!(newerId.matches("^[0-9a-zA-Z]+$"))) {//文字列に半角英数字以外の文字がないかチェック（matchesメソッド
				message = "ログインIDは半角英数字のみご利用いただけます。";
			}
			if(!(newerPw.matches("^[0-9a-zA-Z]+$"))) {
				message3 = "パスワードは半角英数字のみご利用だけます。";
			}
			if(newerUser != null) {
				message2 = "このログインIDはすでに使われています。";
			}

				request.setAttribute("nId", newerId);
				request.setAttribute("nName", newerName);
				request.setAttribute("nProf", newerProf);
				request.setAttribute("nIcon", genderIcon);
				request.setAttribute("alert", message);
				request.setAttribute("alert2", message2);
				request.setAttribute("alert3", message3);
				dispatcher = request.getRequestDispatcher("shinkiUser.jsp");
			}else {
				HttpSession session = request.getSession();//sessionにUserDTO型のnewerUserを保存
				newerUser = new UserDTO(newerId,newerPw,newerName,genderIcon,newerProf);
				session.setAttribute("newerUser",newerUser);//key設定して取り出せるようにする。
				request.setAttribute("nPw", newerPw);

				dispatcher = request.getRequestDispatcher("shinkiKakunin.jsp");
			}
			dispatcher.forward(request, response);
		}

	}

}
