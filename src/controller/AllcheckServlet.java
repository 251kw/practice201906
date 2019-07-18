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
 * Servlet implementation class AllcheckServlet
 */
@WebServlet("/ACS")
public class AllcheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllcheckServlet() {
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
		@SuppressWarnings("unchecked")
		//曖昧検索で引っかかったuserlistをとってくる
		ArrayList<UserDTO> userlist = (ArrayList<UserDTO>)session.getAttribute("userlist");
		ArrayList<String>user = new ArrayList<String>();
		String [] c_usersId = new String[user.size()];

		//userlistの中のユーザー情報のログインIdを取り出してArrayList<String>userに入れていく。
		for(UserDTO udt : userlist) {
			user.add(udt.getLoginId());
		}

		//上のfor文でloginIdを格納したArrayListのuserを検索結果画面での表示条件に引っかかるように、
		//リクエストにCusersという同じキーで入れておく。
		c_usersId = user.toArray(new String[user.size()]);
		request.setAttribute("Cusers", c_usersId);

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("USresult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
