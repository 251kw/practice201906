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
 * Servlet implementation class UserUpdateKensaku
 */
@WebServlet("/UUK")
public class UserUpdateKensaku extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateKensaku() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String message = null;
		DBManager dbm = new DBManager();
		HttpSession session = request.getSession();
		//DltUserKensaku.javaでセッションに保存したアレーリストを取得。キャストで警告が出るがアノテーションで処理。
		ArrayList<UserDTO> selectedUsers2 = (ArrayList<UserDTO>)session.getAttribute("selectedUsers2");
		//検索されたユーザーIDを取得
		String[] sltduId=request.getParameterValues("sltduId");

		String[] uk = request.getParameterValues("loginId");
		String uk2 = request.getParameter("userName");
		String uk3 = request.getParameter("profile");
		String uI = request.getParameter("icon");
		String uI2 = request.getParameter("icon2");
		String uI3 = request.getParameter("icon3");
		String uI4 = request.getParameter("icon4");
		String uI5 = request.getParameter("icon5");

		RequestDispatcher dispatcher;

		//配列に入れられた個数だけDBManのメソッドを実行、アレーリストに取得したオブジェクトを代入
		if(sltduId==null ){
			message="対象をチェックしてください。";
			request.setAttribute("alert", message);
			request.setAttribute("selectedUsers", selectedUsers2);//セッションから取得した値（中身はアレーリストをrequestに乗せて返す。
			dispatcher = request.getRequestDispatcher("userIchiran2.jsp");

		}else if(1==sltduId.length ) {

			UserDTO sUser = dbm.getUserDeleteKakunin(sltduId[0]);

		session.setAttribute("beforeUpdateUserId", sUser.getUserId());
		session.setAttribute("beforeUpdateLoginId", sUser.getLoginId());
		//アイコンの値を送る
		request.setAttribute("sUserIcon", sUser.getIcon());
		request.setAttribute("sUser", sUser);

		request.setAttribute("sltduId", sltduId[0]);
		request.setAttribute("uk", uk[0]);
		request.setAttribute("uk2", uk2);
		request.setAttribute("uk3", uk3);
		request.setAttribute("uI", uI);
		request.setAttribute("uI2", uI2);
		request.setAttribute("uI3", uI3);
		request.setAttribute("uI4", uI4);
		request.setAttribute("uI5", uI5);

		dispatcher = request.getRequestDispatcher("userUpdateNyuuryoku.jsp");
		/*dispatcher.forward(request, response);*/
		}else {
			message="登録内容の編集は1度に1件のみ可能です。";
			request.setAttribute("alert", message);
			request.setAttribute("selectedUsers", selectedUsers2);//セッションから取得した値（中身はアレーリストをrequestに乗せて返す。
			dispatcher = request.getRequestDispatcher("userIchiran2.jsp");

		}
		dispatcher.forward(request, response);
	}

}
