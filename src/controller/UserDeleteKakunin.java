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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String message = null;
		DBManager dbm = new DBManager();
		String[] sltduId=request.getParameterValues("sltduId");
		ArrayList<UserDTO> uList = new ArrayList<UserDTO>();
		RequestDispatcher dispatcher;

		if(!(sltduId==null)) {
		for(String i : sltduId) {
			UserDTO sUser = dbm.getUserDeleteKakunin(i);
			uList.add(sUser);
		}
		request.setAttribute("uList", uList);
		dispatcher = request.getRequestDispatcher("userIchiran2.jsp");
		dispatcher.forward(request, response);
		}else {
			message="削除対象がチェックされていません";
			request.setAttribute("alert", message);
			dispatcher = request.getRequestDispatcher("userIchiran2.jsp");
			dispatcher.forward(request, response);
		}
	}

}
