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
 * Servlet implementation class DeleteShoutServlet
 */
@WebServlet("/DSS")
public class DeleteShoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteShoutServlet() {
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

		/*String username = request.getParameter("username");
		String icon = request.getParameter("icon");
		String date = request.getParameter("date");
		String writing = request.getParameter("writing");*/
		String stringkey = request.getParameter("key");
		int key = Integer.parseInt(stringkey);
		DBManager dbm = new DBManager();

		/*HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		String loginid = user.getLoginId();*/

		boolean check = dbm.deleteShout(key);
		if(check == true) {
			dispatcher = request.getRequestDispatcher("FinishDeleteShout.jsp");
		}else {
			dispatcher = request.getRequestDispatcher("ErrorDeleteShout.jsp");
		}
		dispatcher.forward(request, response);


	}

}
