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
 * Servlet implementation class UdeleteServlet
 */
@WebServlet("/UDS")
public class UdeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UdeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String [] DUSs = (String[])session.getAttribute("DuSs");

		request.setAttribute("DUSS", DUSs);

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("USresult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");


		RequestDispatcher dispatcher = null;
		String message1 = "";
		String message2 = "";


		//USresult.jspでチェックされたloginIdを配列で受け取る。
		String [] d_users = request.getParameterValues("d_user");

		//何もチェックしてなかった時のエラー処理。
		if(d_users == null) {
			message1 = "チェックを入れてください。";
			request.setAttribute("alert1", message1);

			dispatcher = request.getRequestDispatcher("USresult.jsp");
			dispatcher.forward(request, response);
		}


		ArrayList<UserDTO> dudt = null; //ユーザー情報を受け取るArrayList
		ArrayList<UserDTO> D_users = new ArrayList<UserDTO>(); //dudtを格納するArrayList



		for(String loginId : d_users) {
			DBManager dbm = new DBManager();
			dudt = dbm.getDuserList(loginId);
			D_users.addAll(dudt);

		}

		HttpSession session = request.getSession();
		//チェックされたユーザーのloginIdが入っている配列をセッションに保存
		session.setAttribute("d_users", d_users);
		//選択された分だけユーザー情報が入っているArrayList<UserDTO>が入っている
		//ArrayListのD_usersをセッションに保存
		session.setAttribute("D_users",D_users);

		String BTN = request.getParameter("btn");

		//USresult.jspでどっちのボタンが押されてもここに飛んできてから分岐させる。
		if(BTN.equals("削除")) {

			dispatcher = request.getRequestDispatcher("udCheck.jsp");

		}else {

			if(d_users.length > 1) {
				message2 = "更新は1件しか選択できません。";
				request.setAttribute("alert2",message2);
				dispatcher = request.getRequestDispatcher("USresult.jsp");
				dispatcher.forward(request, response);

			}
			dispatcher = request.getRequestDispatcher("UDinsert.jsp");
		}
		dispatcher.forward(request, response);


	}

}
