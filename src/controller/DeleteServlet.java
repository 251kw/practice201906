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

/**
 * Servlet implementation class deleteServlet
 */
@WebServlet("/ds")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
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

		String date = request.getParameter("date");

		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();

		//DBから書き込みを消去するメソッドを呼び出す(DBから書き込みを消去)
		dbm.deleteWriting(date);


		// 書き込み内容消去後のリストを取得
		ArrayList<ShoutDTO> list = dbm.getShoutList();

		// リストをセッションに保存
		HttpSession session = request.getSession();
		session.setAttribute("shouts", list);


		dispatcher = request.getRequestDispatcher("DeleteCompletion.jsp");
		dispatcher.forward(request, response);

		//doGet(request, response);
	}

}
