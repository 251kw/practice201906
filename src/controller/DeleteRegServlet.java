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

@WebServlet("/DeleteRegServlet")
public class DeleteRegServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
//		String writing = (String) session.getAttribute("writing");
		String shoutsId = (String) session.getAttribute("shoutsId");
		boolean check;


		DBManager des = new DBManager();
		check = des.deleteshouts(shoutsId);
		if(check == true) {	// SQL文が成功したとき
			RequestDispatcher dispatcher =
	                getServletContext().getRequestDispatcher("/Delete.jsp");
		 dispatcher.forward(request, response);			// 遷移先のURLを書く(/Delete.jsp)
		} else {	// SQL文が失敗したとき
						// 遷移先のURLを書く(削除されませんでした.jsp)
		 RequestDispatcher dispatcher =
	                getServletContext().getRequestDispatcher("/index.jsp");
		 dispatcher.forward(request, response);
		}

	}
}
