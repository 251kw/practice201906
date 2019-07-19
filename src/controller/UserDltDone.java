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
		String[] dltUserList=request.getParameterValues("uListLoginId");//削除するユーザーのloginIdを取得
		RequestDispatcher dispatcher;
		DBManager dbm = new DBManager();

		String[] uk = request.getParameterValues("loginId");//Like検索する際に""空文字だとすべてが検索に引っかかるので入力なければＤＢ上該当の無いスペースを代入
		String uk2 = request.getParameter("userName");
		String uk3 = request.getParameter("profile");
		String uI = request.getParameter("icon");
		String uI2 = request.getParameter("icon2");
		String uI3 = request.getParameter("icon3");
		String uI4 = request.getParameter("icon4");
		String uI5 = request.getParameter("icon5");

		HttpSession session = request.getSession();
		UserDTO beanUser = (UserDTO) session.getAttribute("user");//ログイン時に入力されたユーザー情報取得
		String beanUserId=beanUser.getLoginId();//ユーザー情報のログインIDを取得
		String dltMyId="";

		if(!(dltUserList==null)) {
		for(String i : dltUserList) {

			/*boolean sUser =*/ dbm.setWritingDelete(i);
			//System.out.println(sUser);
		}
		for(String i : dltUserList) {
			/*boolean sUser2 =*/ dbm.setUserDelete(i);
			//System.out.println(sUser2);
		}
		for(String i : dltUserList) {//消去するユーザーログインIdとログインしているユーザーのログインIDを比較
			if(i.equals(beanUserId)) {
				dltMyId=i;
				/*dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);//一致するものがあった場合、index.jspに遷移
*/			}
		}
		//request.setAttribute("uList", uList);

		request.setAttribute("beanUserId", beanUserId);
		request.setAttribute("dltMyId", dltMyId);
		request.setAttribute("uk", uk[0]);
		request.setAttribute("uk2", uk2);
		request.setAttribute("uk3", uk3);
		request.setAttribute("uI", uI);
		request.setAttribute("uI2", uI2);
		request.setAttribute("uI3", uI3);
		request.setAttribute("uI4", uI4);
		request.setAttribute("uI5", uI5);

		dispatcher = request.getRequestDispatcher("userDeleteKanryo.jsp");

		}else {
			//message="対象をチェックしてください。";
			//request.setAttribute("alert", message);
			//request.setAttribute("selectedUsers", selectedUsers);//セッションから取得した値（中身はアレーリストをrequestに乗せて返す。
			dispatcher = request.getRequestDispatcher("userIchiran2.jsp");
		}
		dispatcher.forward(request, response);
	}

}
