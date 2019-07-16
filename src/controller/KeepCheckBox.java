package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AfterUpdateUser
 */
@WebServlet("/KCB")
public class KeepCheckBox extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KeepCheckBox() {
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

		RequestDispatcher dispatcher;

/*		String message = null;
		DBManager dbm = new DBManager();
		String[] uk = request.getParameterValues("userId");
		UserDTO user = new UserDTO();
		ArrayList<UserDTO> uList = new ArrayList<UserDTO>();*/

		String[] uk = request.getParameterValues("loginId");//Like検索する際に""空文字だとすべてが検索に引っかかるので入力なければＤＢ上該当の無いスペースを代入
		String uk2 = request.getParameter("userName");
		String uk3 = request.getParameter("profile");
		String uI = request.getParameter("icon");
		String uI2 = request.getParameter("icon2");
		String uI3 = request.getParameter("icon3");
		String uI4 = request.getParameter("icon4");
		String uI5 = request.getParameter("icon5");

		if("".equals(uI)) {
			uI = null;
		}
		if("".equals(uI2)) {
			uI2 = null;
		}
		if("".equals(uI3)) {
			uI3 = null;
		}
		if("".equals(uI4)) {
			uI4 = null;
		}
		if("".equals(uI5)) {
			uI5 = null;
		}


			if("".equals(uk[0])) {
				uk[0]="";
			}

			if ((!(uI == null))){
				uI = "checked";
			}
			if ((!(uI2 == null))){
				uI2 = "checked";
			}
			if ((!(uI3 == null))){
				uI3 = "checked";
			}
			if ((!(uI4 == null))) {
				uI4 = "checked";
			}
			if ((!(uI5 == null))) {
				uI5 = "checked";
			}

			request.setAttribute("uk", uk[0]);
			request.setAttribute("uk2", uk2);
			request.setAttribute("uk3", uk3);
			request.setAttribute("uI", uI);
			request.setAttribute("uI2", uI2);
			request.setAttribute("uI3", uI3);
			request.setAttribute("uI4", uI4);
			request.setAttribute("uI5", uI5);

			dispatcher = request.getRequestDispatcher("userKensaku.jsp");
		dispatcher.forward(request, response);
	}
}

