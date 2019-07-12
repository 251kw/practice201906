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
 * Servlet implementation class UdeleteServlet2
 */
@WebServlet("/US2")
public class UdeleteServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UdeleteServlet2() {
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
		RequestDispatcher dispatcher = null;

		HttpSession session = request.getSession();
		UserDTO udt = new UserDTO();
		UserDTO loginUser = (UserDTO)session.getAttribute("user");
		String loginUserId = loginUser.getLoginId();

		String[]d_users = (String[])session.getAttribute("d_users");
		boolean STATE = false;


		for(String loginId : d_users) {

			DBManager Sdbm = new DBManager();
			boolean result2 = Sdbm.DeleteShouts(loginId);
			DBManager Udbm = new DBManager();
			boolean result = Udbm.DeleteUser(loginId);

			if(loginUserId.equals(loginId)) {
				STATE = true;
			}
		}

		DBManager dbm = new DBManager();
		ArrayList<ShoutDTO> list = dbm.getShoutList();
		session.setAttribute("shouts", list);

		session.setAttribute("STATE", STATE);


		dispatcher = request.getRequestDispatcher("udComp.jsp");
		dispatcher.forward(request, response);
	}

}
