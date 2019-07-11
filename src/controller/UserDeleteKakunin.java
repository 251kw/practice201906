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
 * Servlet implementation class UserDeleteKakunin
 */
@WebServlet("/UDK")
public class UserDeleteKakunin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteKakunin() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String message = null;
		DBManager dbm = new DBManager();
		HttpSession session = request.getSession();
		//DltUserKensaku.javaでセッションに保存したアレーリストを取得。キャストで警告が出るがアノテーションで処理。
		ArrayList<UserDTO> selectedUsers2 = (ArrayList<UserDTO>)session.getAttribute("selectedUsers2");
		//検索されたユーザーIDを取得
		String[] sltduId=request.getParameterValues("sltduId");
		//ArrayList<UserDTO> selectedUsers = new ArrayList<UserDTO>();
		ArrayList<UserDTO> uList = new ArrayList<UserDTO>();
		RequestDispatcher dispatcher;

		//配列に入れられた個数だけDBManのメソッドを実行、アレーリストに取得したオブジェクトを代入
		if(!(sltduId==null)) {
		for(String i : sltduId) {
			UserDTO sUser = dbm.getUserDeleteKakunin(i);
			uList.add(sUser);
		}
		request.setAttribute("uList", uList);
		dispatcher = request.getRequestDispatcher("userDeleteKakunin.jsp");
		dispatcher.forward(request, response);
		}else {
			message="対象をチェックしてください。";
			request.setAttribute("alert", message);
			request.setAttribute("selectedUsers", selectedUsers2);//セッションから取得した値（中身はアレーリストをrequestに乗せて返す。
			dispatcher = request.getRequestDispatcher("userIchiran2.jsp");
			dispatcher.forward(request, response);
		}
	}

}
