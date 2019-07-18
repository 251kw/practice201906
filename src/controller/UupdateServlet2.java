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
 * Servlet implementation class UupdateServlet2
 */
@WebServlet("/UUDS2")
public class UupdateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UupdateServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");


		HttpSession session = request.getSession();
		//入力された更新情報をUupdateServlet.javaでnewしてセッションに保存しておいたもの。
		UserDTO UpdateUser = (UserDTO)session.getAttribute("UpdateUser");
		//チェックされたユーザーのloginIdをUdeleteServlet.javaでセッションに保存しておいたもの。
		String uduId = (String)session.getAttribute("UDU_Id");
		UserDTO nowUser = (UserDTO)session.getAttribute("user");
		String nowId = nowUser.getLoginId();


		DBManager dbm = new DBManager();
		/*boolean result = */dbm.UpdateUser(UpdateUser, uduId);
		/*boolean result1 = */dbm.UpdateShouts(UpdateUser, uduId);

		ArrayList<ShoutDTO> list = dbm.getShoutList();
		session.setAttribute("shouts", list);

		if(uduId.equals(nowId)) {
			UserDTO UDU = dbm.getUDUser(uduId);
			session.setAttribute("user", UDU);
		}




		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("updateComp.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);


	}

}
