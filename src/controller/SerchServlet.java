package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;
/**
 * Servlet implementation class SerchServlet
 */
@WebServlet("/ss")
public class SerchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SerchServlet() {
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
		String uName = request.getParameter("uName");
		String error = null;
		String message_s = null;
		String message_n = null;
		int results_a = 0;
		RequestDispatcher dispatcher;

		error = uName.trim();

		//スペースだけの書き込み内容なら
		if (error.equals("")) {
			message_s = "空白だけの検索は無効です";
			//エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert_s", message_s);
		}else {
		//入力情報が取得されたらリクエストスコープに保存
			DBManager dbm = new DBManager();
			ArrayList<UserDTO> list = dbm.serchUser(uName);

			if (list != null) {
				// ユーザ情報を取得できたら、ユーザー検索情報リストを取得
			request.setAttribute("results", list);
			results_a = list.size();
			request.setAttribute("results_a",results_a);
			}else {
				message_n = "検索結果がありません";
				request.setAttribute("alert_n", message_n);
			}
		}
		dispatcher = request.getRequestDispatcher("Serch.jsp");
		dispatcher.forward(request, response);
		//doGet(request, response);
	}

}
