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
 * Servlet implementation class UsearchServlet
 */
@WebServlet("/US")
public class UsearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = null;

		//このサーブレットのpostメソッドでセッションに保存しておいたやつを取り出してリクエストに入れる（search.jspで保持するため）
		HttpSession session = request.getSession();
		String LoginId = (String)session.getAttribute("loginId");
		String UserName = (String)session.getAttribute("userName");

		request.setAttribute("loginId", LoginId);
		request.setAttribute("userName", UserName);

		dispatcher = request.getRequestDispatcher("search.jsp");
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

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String ASmessage = null;
		RequestDispatcher dispatcher = null;


		if(loginId.equals("") && userName.equals("")) {
			ASmessage = "検索ワードを入れてください。";
			request.setAttribute("alert_search", ASmessage);
		}




		DBManager dbm = new DBManager();
		ArrayList<UserDTO> Userlist = dbm.getUserlist(loginId,userName);

		HttpSession session = request.getSession();
		session.setAttribute("userlist", Userlist);

		session.setAttribute("loginId", loginId);
		session.setAttribute("userName", userName);

		dispatcher = request.getRequestDispatcher("USresult.jsp");
		dispatcher.forward(request, response);
	}

}
