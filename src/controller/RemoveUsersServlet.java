package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class RemoveUsersServlet
 */
@WebServlet("/RUS")
public class RemoveUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveUsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<UserDTO> user = (ArrayList<UserDTO>) session.getAttribute("checklist");

		List<String> shoutsOnlyId = new ArrayList<String>();
		UserDTO original = (UserDTO)session.getAttribute("user");
		String originId = original.getLoginId();
		String ID = "";
		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();
		boolean errcnt = false;		// 選択したユーザーがshoutsに情報があるかないかの判定
		boolean checkId = false;
		int count = 0;
		int check = 0;
		int shoutscount = 0;
		for (UserDTO u : user) {
			ID = u.getLoginId();
			if(ID.equals(originId)) {	// もし削除するユーザーが自分自身ならtopじゃなくindexに戻るようにするためのリクエスト
				checkId = true;
			}
			shoutscount = dbm.checkShouts(ID);	// 削除するユーザーの叫びの個数を数える
			if (shoutscount != 0) {		// もし叫びがあったら叫びがあるユーザーIDをリストに追加
				shoutsOnlyId.add(ID);
				errcnt = true;
			}
		}

		if(errcnt == true) {	// 叫びがあったユーザーの叫びを削除する
			for(String s : shoutsOnlyId) {
				dbm.deleteShout2(s);
			}
		}
		// 選択したユーザーすべてが削除可能(shoutsにデータがない)時にのみ削除文を実行
		for(UserDTO u : user) {
			ID = u.getLoginId();
			check += dbm.deleteUser(ID);	// 実行できたら1、できなかったら0を返す
			count++;
		}


		if (count != check) {					// countとcheckの値が等しくない場合、実行できていないものがあるのでエラー
			String message = "削除中にエラーが出ました";
			request.setAttribute("alert", message);
			dispatcher = request.getRequestDispatcher("ResultUsers.jsp");
		} else {
			if(checkId == true) {		// 自分自身を削除した場合、logoutに値を入れる
				request.setAttribute("logout", "logout");
			} else {
				request.setAttribute("logout", "");
			}
			dispatcher = request.getRequestDispatcher("FinishDeleteUsers.jsp");
		}
		dispatcher.forward(request,response);

	}

}
