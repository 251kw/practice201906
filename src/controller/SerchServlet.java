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
		String uName = request.getParameter("uName");
		String error = null;
		String message_s = null;
		RequestDispatcher dispatcher;

		error = uName.trim();

		//スペースだけの書き込み内容なら
		if (error.equals("")) {
			message_s = "空白だけの検索は無効です";
			//エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert_s", message_s);
		}
		//入力情報が取得されたらリクエストスコープに保存
		DBManager dbm = new DBManager();
		UserDTO user = dbm.serchUser(uName);

		if (user != null) {
			// ユーザ情報を取得できたら、書き込み内容リストを取得
			ArrayList<userDTO> list = dbm.getShoutList();


		request.setAttribute("result", user);

		dispatcher = request.getRequestDispatcher("Serch.jsp");
		dispatcher.forward(request, response);
		//doGet(request, response);
	}

}
