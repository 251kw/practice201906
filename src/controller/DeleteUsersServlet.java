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
 * Servlet implementation class DeleteUsersServlet
 */
@WebServlet("/DUS")
public class DeleteUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUsersServlet() {
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

		String[] check = request.getParameterValues("check");
		RequestDispatcher dispatcher = null;
		String message = null;
		if (check == null) {
			message = "選択してください";
			request.setAttribute("alert", message);
			dispatcher = request.getRequestDispatcher("ResultUsers.jsp");
		}else {
			ArrayList<UserDTO> userlist = new ArrayList<UserDTO>();
			ArrayList<UserDTO> user = new ArrayList<UserDTO>();
			DBManager dbm = new DBManager();
			for(String s : check) {
				user = dbm.oldserchUsers(s,"", "", "");
				userlist.addAll(user);
			}
			HttpSession session = request.getSession();
			session.setAttribute("checklist", userlist);
			dispatcher =  request.getRequestDispatcher("UsersDeleteChecked.jsp");
		}
		dispatcher.forward(request, response);
	}

}
