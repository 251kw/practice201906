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
import dto.ShoutDTO;
import dto.UserDTO;

@WebServlet("/NewLoginServlet")
public class NewLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//register.jspからpost送信される。
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");
		RequestDispatcher dispatcher = null;

			String loginId = user.getLoginId();
			String password = user.getPassword();
			DBManager nlog = new DBManager();
			nlog.getnewLoginUser(loginId,password);

			ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();
			ShoutDTO shout = new ShoutDTO();
			shout.getUserName();
			shout.getIcon();
			shout.getDate();
			shout.getWriting();

			list.add(shout);

			session.setAttribute("shouts",list);

			// 処理の転送先を top.jsp に指定
			dispatcher = request.getRequestDispatcher("top.jsp");
			dispatcher.forward(request, response);

}
}




