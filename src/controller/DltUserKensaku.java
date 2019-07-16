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
		String[] uk = request.getParameterValues("loginId");//Like検索する際に""空文字だとすべてが検索に引っかかるので入力なければＤＢ上該当の無いスペースを代入
		String uk2 = request.getParameter("userName");
		if (uk2.equals("")/*||uk==null*/) {
			uk2 = null;
		}
		String uk3 = request.getParameter("profile");
		if (uk3.equals("")/*||uk==null*/) {
			uk3 = null;
		}

		String uI = request.getParameter("icon");
		if("".equals(uI)) {
			uI=null;
		}
		String uI2 = request.getParameter("icon2");
		if("".equals(uI2)) {
			uI2=null;
		}
		String uI3 = request.getParameter("icon3");
		if("".equals(uI3)) {
			uI3=null;
		}
		String uI4 = request.getParameter("icon4");
		if("".equals(uI4)) {
			uI4=null;
		}
		String uI5 = request.getParameter("icon5");
		if("".equals(uI5)) {
			uI5=null;
		}

		ArrayList<UserDTO> selectedUsers = new ArrayList<UserDTO>();

		if ("".equals(uk[0]) && uk2==null && uk3 == null && uI == null && uI2 == null && uI3 == null && uI4 == null
				&& uI5 == null) {
			message = "検索ワードが入力されていません";
			request.setAttribute("alert", message);
			dispatcher = request.getRequestDispatcher("userKensaku.jsp");
			dispatcher.forward(request, response);
		} else {
			for(String str :uk) {
				if(str.equals("")) {
					str=null;
				}
				selectedUsers = dbm.getUserList(str, uk2, uk3, uI, uI2, uI3, uI4, uI5);
			}
			if (!(selectedUsers.size() == 0)) {//sizeメソッド　リスト内の要素の数を調べるためのメソッド
				HttpSession session = request.getSession();
				session.setAttribute("selectedUsers2", selectedUsers);
				if("".equals(uk[0])) {
					uk[0]="";
				}

				//request.setAttribute("alert", message);
				request.setAttribute("uk", uk[0]);
				request.setAttribute("uk2", uk2);
				request.setAttribute("uk3", uk3);
				request.setAttribute("uI", uI);
				request.setAttribute("uI2", uI2);
				request.setAttribute("uI3", uI3);
				request.setAttribute("uI4", uI4);
				request.setAttribute("uI5", uI5);

				request.setAttribute("selectedUsers", selectedUsers);
				dispatcher = request.getRequestDispatcher("userIchiran2.jsp");
			} else {
				message = "検索結果は０件でした";
				//値を保持する
				if("".equals(uk[0])) {
					uk[0]="";
				}

				if (!(uI == null)) {
					uI = "checked";
				}
				if (!(uI2 == null)) {
					uI2 = "checked";
				}
				if (!(uI3 == null)) {
					uI3 = "checked";
				}
				if (!(uI4 == null)) {
					uI4 = "checked";
				}
				if (!(uI5 == null)) {
					uI5 = "checked";
				}

				request.setAttribute("alert", message);
				request.setAttribute("uk", uk[0]);
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
