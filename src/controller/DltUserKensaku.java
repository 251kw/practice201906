package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class DltUserKensaku
 */
@WebServlet("/DUK")
public class DltUserKensaku extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DltUserKensaku() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher;

		String message = null;
		DBManager dbm = new DBManager();
		String uk = request.getParameter("loginId");//Like検索する際に""空文字だとすべてが検索に引っかかるので入力なければＤＢ上該当の無いスペースを代入
		if(uk.equals("")) {
			uk=" ";
		}
		String uk2 = request.getParameter("userName");
		if(uk2.equals("")) {
			uk2=" ";
		}
		String uk3 = request.getParameter("profile");
		if(uk3.equals("")) {
			uk3=" ";
		}
		String uI = request.getParameter("icon");
		String uI2 = request.getParameter("icon2");
		String uI3 = request.getParameter("icon3");
		String uI4 = request.getParameter("icon4");
		String uI5 = request.getParameter("icon5");

		if(uI==null) {
			uI=" ";
		}
		if(uI2==null) {
			uI2=" ";
		}
		if(uI3==null) {
			uI3=" ";
		}
		if(uI4==null) {
			uI4=" ";
		}
		if(uI5==null) {
			uI5=" ";
		}
		if(uk.equals(" ") && uk2.equals(" ") && uk3.equals(" ") && uI.equals(" ") && uI2.equals(" ") && uI3.equals(" ") && uI4.equals(" ") && uI5.equals(" ")) {
			message = "検索ワードが入力されていません";
			request.setAttribute("alert", message);
			dispatcher = request.getRequestDispatcher("userKensaku.jsp");
			dispatcher.forward(request, response);
		}else {
		ArrayList<UserDTO> selectedUsers = dbm.getUserList(uk,uk2,uk3,uI,uI2,uI3,uI4,uI5);

		if (!(selectedUsers.size()==0)) {//sizeメソッド　リスト内の要素の数を調べるためのメソッド
			request.setAttribute("selectedUsers", selectedUsers);
			dispatcher = request.getRequestDispatcher("userIchiran2.jsp");
		}else {
			message = "検索結果は０件でした";
			if(" ".equals(uk)) {
				uk="";
			}
			if(" ".equals(uk2)) {
				uk2="";
			}
			if(" ".equals(uk3)) {
				uk3="";
			}
			if(!(" ".equals(uI))) {
				uI="checked";
			}
			if(!(" ".equals(uI2))) {
				uI2="checked";
			}
			if(!(" ".equals(uI3))) {
				uI3="checked";
			}
			if(!(" ".equals(uI4))) {
				uI4="checked";
			}
			if(!(" ".equals(uI5))) {
				uI5="checked";
			}
			request.setAttribute("alert", message);
			request.setAttribute("uk", uk);
			request.setAttribute("uk2", uk2);
			request.setAttribute("uk3", uk3);
			request.setAttribute("uI", uI);
			request.setAttribute("uI2", uI2);
			request.setAttribute("uI3", uI3);
			request.setAttribute("uI4", uI4);
			request.setAttribute("uI5", uI5);

			dispatcher = request.getRequestDispatcher("userKensaku.jsp");
		}
		dispatcher.forward(request, response);
	}
	}
}
