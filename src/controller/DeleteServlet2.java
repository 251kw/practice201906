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
 * Servlet implementation class DeleteServlet2
 */
@WebServlet("/DS2")
public class DeleteServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet2() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//DeleteServlet.javaでセッションにセットしたものから取り出す。
		HttpSession session = request.getSession();
		String LoginId = (String)session.getAttribute("dC_loginId");
		String Date = (String)session.getAttribute("dC_date");

		//DBMの削除メソッドを呼び出す。
		DBManager dbm = new DBManager();
		/*boolean result = */dbm.DeleteNew(LoginId, Date);

//		余裕があればエラー処理も細かくやってみる。
//		if (result == false) {
//			session.setAttribute("errormessage", "削除に失敗しました。");
//			RequestDispatcher dispatcher = null;
//			dispatcher = request.getRequestDispatcher("error.jsp");
//			dispatcher.forward(request, response);
//		}
//		else {

		//DBMでデータベースから削除した後に最新の状態を画面に反映させるために
		//セッションに上書き
		ArrayList<ShoutDTO> list = dbm.getShoutList();
		session.setAttribute("shouts", list);

		RequestDispatcher dispatcher = null;

		dispatcher = request.getRequestDispatcher("deleteComp.jsp");
		dispatcher.forward(request, response);
	}

}
