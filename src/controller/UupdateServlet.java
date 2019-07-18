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

import dto.UserDTO;

/**
 * Servlet implementation class UupdateServlet
 */
@WebServlet("/UUDS")
public class UupdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UupdateServlet() {
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
		UserDTO updateUser = (UserDTO)session.getAttribute("UpdateUser");
		request.setAttribute("updateUser", updateUser);

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("UDinsert.jsp");
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
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<UserDTO> D_users = (ArrayList<UserDTO>)session.getAttribute("D_users");
//		String alert1 = "";
//		boolean STATE = true;


//		String loginId = request.getParameter("newID");
		String password = request.getParameter("newPW");
		String userName = request.getParameter("newUN");
		String icon = request.getParameter("newIC");
		String profile = request.getParameter("newPF");

		//更新画面で何も入力されなければもともとの情報が登録される。
		if(password.equals("")){
			for(UserDTO user : D_users) {
				password = user.getPassword();
			}
		}

		if(userName.equals("")){
			for(UserDTO user : D_users) {
				userName = user.getUserName();
			}
		}

		if(profile.equals("")){
			for(UserDTO user : D_users) {
				profile = user.getProfile();
			}
		}


//		if(STATE == false) {
//
//			UserDTO UPdateUser = new UserDTO(password,userName,icon,profile);
//			HttpSession session = request.getSession();
//			session.setAttribute("UpdateUser", UPdateUser);
//			dispatcher = request.getRequestDispatcher("UDinsert.jsp");
//			dispatcher.forward(request, response);
//		}

		UserDTO UPdateUser = new UserDTO(password,userName,icon,profile);

		session = request.getSession();
		session.setAttribute("UpdateUser", UPdateUser);


//		session.setAttribute("loginId", loginId);
		session.setAttribute("password", password);
		session.setAttribute("userName", userName);
		session.setAttribute("icon", icon);
		session.setAttribute("profile", profile);

		dispatcher = request.getRequestDispatcher("uudCheck.jsp");
		dispatcher.forward(request, response);
	}

}
