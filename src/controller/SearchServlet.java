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
import dto.UserDTO;
/**
 * Servlet implementation class SerchServlet
 */
@WebServlet("/ss")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		String message_s = null;
		String message_rn = null;
		String error = null;

		int results_a = 0;
		RequestDispatcher dispatcher;

		error = uName.replaceFirst("^[\\h]+", "").replaceFirst("[\\h]+$", "");

		//スペースだけの書き込み内容なら
		if (error.equals("")) {
			message_s = "空白だけの検索は無効です";
			//エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert_s", message_s);
		}else {
		//入力情報が取得されたらリクエストスコープに保存
			DBManager dbm = new DBManager();
			ArrayList<UserDTO> list = dbm.serchUser(uName);
			HttpSession session = request.getSession();

			if (list.size() != 0) {
				session.setAttribute("results", list);
			results_a = list.size();
			request.setAttribute("results_a",results_a);
			}else {
				message_rn = "検索結果が0件です";
				request.setAttribute("alert_rn",message_rn);
				list = null;
				session.setAttribute("results",list);
			}
		}
		dispatcher = request.getRequestDispatcher("Search.jsp");
		dispatcher.forward(request, response);
		//doGet(request, response);
	}

}
