package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class UserDltDone
 */
@WebServlet("/UDD")
public class UserDltDone extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDltDone() {
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
		String[] dltUserList=request.getParameterValues("uListId");
		RequestDispatcher dispatcher;

		if(!(dltUserList==null)) {
		for(String i : dltUserList) {
			DBManager dbm = new DBManager();
			boolean sUser = dbm.setWritingDelete(i);
			System.out.println(sUser);
		}
		for(String i : dltUserList) {
			DBManager dbm = new DBManager();
			boolean sUser = dbm.setUserDelete(i);
			System.out.println(sUser);
		}
		//request.setAttribute("uList", uList);
		dispatcher = request.getRequestDispatcher("userDeleteKakunin.jsp");
		dispatcher.forward(request, response);
		}else {
			//message="対象をチェックしてください。";
			//request.setAttribute("alert", message);
			//request.setAttribute("selectedUsers", selectedUsers);//セッションから取得した値（中身はアレーリストをrequestに乗せて返す。
			dispatcher = request.getRequestDispatcher("userIchiran2.jsp");
			dispatcher.forward(request, response);
		}
	}

}
