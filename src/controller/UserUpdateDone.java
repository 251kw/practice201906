package controller;

import java.io.IOException;

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
 * Servlet implementation class UserUpdateDone
 */
@WebServlet("/UUD")
public class UserUpdateDone extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateDone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		DBManager dbm = new DBManager();

		String sLoginId = (String) session.getAttribute("beforeUpdateLoginId");//UUKから。アップデートでチェックされたユーザーのID
		UserDTO beanUser = (UserDTO) session.getAttribute("user");//ログイン時に入力されたユーザー情報取得
		String beanUserId=beanUser.getLoginId();//ユーザー情報のログインIDを取得
		String beanUserPw=beanUser.getPassword();

		String userId = (String) session.getAttribute("beforeUpdateUserId");

		//String newerId = request.getParameter("newerId");
		String newerPw = request.getParameter("newerPw");
		String newerName = request.getParameter("newerName");
		String genderIcon = request.getParameter("gender");
		String newerProf = request.getParameter("newerProfile");

		String[] uk = request.getParameterValues("loginId");
		String uk2 = request.getParameter("userName");
		String uk3 = request.getParameter("profile");
		String uI = request.getParameter("icon");
		String uI2 = request.getParameter("icon2");
		String uI3 = request.getParameter("icon3");
		String uI4 = request.getParameter("icon4");
		String uI5 = request.getParameter("icon5");

/*		boolean bln;
		boolean bln2;*/
		/*bln = */dbm.setUserUpdate(newerPw, newerName, genderIcon, newerProf, userId);
		/*bln2 = */dbm.setShoutsUpdate(newerName, genderIcon,sLoginId);
		/*System.out.println(bln);
		System.out.println(bln2);*/

		if(sLoginId.equals(beanUserId)) {
		/*	beanUser.setPassword(newerPw);
		}

		if(bln==true) {*/
			request.setAttribute("updateId", beanUserId);
			request.setAttribute("updatePw", newerPw);

			request.setAttribute("uk", uk[0]);
			request.setAttribute("uk2", uk2);
			request.setAttribute("uk3", uk3);
			request.setAttribute("uI", uI);
			request.setAttribute("uI2", uI2);
			request.setAttribute("uI3", uI3);
			request.setAttribute("uI4", uI4);
			request.setAttribute("uI5", uI5);

			dispatcher = request.getRequestDispatcher("userUpdateKanryo.jsp");
		}else if(!((sLoginId.equals(beanUserId)))) {
			request.setAttribute("updateId", beanUserId);
			request.setAttribute("updatePw", beanUserPw);

			request.setAttribute("uk", uk[0]);
			request.setAttribute("uk2", uk2);
			request.setAttribute("uk3", uk3);
			request.setAttribute("uI", uI);
			request.setAttribute("uI2", uI2);
			request.setAttribute("uI3", uI3);
			request.setAttribute("uI4", uI4);
			request.setAttribute("uI5", uI5);

			dispatcher = request.getRequestDispatcher("userUpdateKanryo.jsp");
		}else {
			String message = "予期せぬエラーが発生しました。";
			request.setAttribute("alert", message);
			request.setAttribute("sUser.password", newerPw);
			request.setAttribute("sUser.userName", newerName);
			request.setAttribute("sUserIcon", genderIcon);
			request.setAttribute("sUser.profile", newerProf);
			dispatcher = request.getRequestDispatcher("userUpdateNyuuryoku.jsp");
		}
		dispatcher.forward(request, response);
	}

}
