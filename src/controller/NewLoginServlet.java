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
 * Servlet implementation class NewLoginServlet
 */
@WebServlet("/NLS")
public class NewLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 直接入力されたらログイン画面に飛ばす
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		String id = request.getParameter("hiddenId");
		String message = null;
		DBManager dbm = new DBManager();

		boolean checked = dbm.setNewUser(loginId, password, username, icon, profile);
		if(checked == true) {
			dispatcher = request.getRequestDispatcher("FinishNewAccount.jsp");
		}else {
			message = "登録できませんでした。最初からやり直してください。";
			request.setAttribute("alert", message);
			request.setAttribute("hiddenId", id);
			dispatcher = request.getRequestDispatcher("NewAccount.jsp");
		}
		dispatcher.forward(request, response);

	}

}
