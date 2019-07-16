package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/es")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		request.setAttribute("loginId",loginId);
		request.setAttribute("password",password);
		request.setAttribute("userName",userName);
		request.setAttribute("icon",icon);
		request.setAttribute("profile",profile);

		//DBManager dbm = new DBManager();

		RequestDispatcher dispatcher = null;
		String messageI = null;
		String messageU = null;
		String messageP = null;

		if (loginId.equals("") ) {
			// 必須入力事項が未入力なら
			messageI = "ログインIDは必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert_i", messageI);
		}

		//パスワードが未入力なら
		if (password.equals("")) {
			messageP = "パスワードは必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert_p", messageP);
		}

		//ユーザーネームが未入力なら
		if (userName.equals("")) {
			messageU = "ユーザー名は必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert_u", messageU);
		}
		//エラーメッセージが一つでもあれば転送
		if(messageI != null || messageP != null || messageU != null)  {
		dispatcher = request.getRequestDispatcher("Editing.jsp");
		}else {
		//入力エラーがなければ確認画面へ
		dispatcher = request.getRequestDispatcher("EditConfirmation.jsp");
		}
		dispatcher.forward(request, response);
	}

}
