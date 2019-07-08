package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.ShoutDTO;

/**
 * Servlet implementation class ShoutDeleteKakunin
 */
@WebServlet("/SDK")
public class ShoutDeleteKakunin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoutDeleteKakunin() {
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
		String shouterShoutsIdDelete = request.getParameter("shouterId");
	/*	String shouterNameDelete = request.getParameter("shouterName");
		String shouterIconDelete = request.getParameter("shouterIcon");
		String shouterdateDelete = request.getParameter("shouterDate");
		String writingDelete = request.getParameter("shouterWriting");*/

		ShoutDTO sDelete=null;


		if(dbm == null){
			dbm = new DBManager();
		}
		sDelete = dbm.getShoutDlt(shouterShoutsIdDelete/*,shouterNameDelete, shouterIconDelete, shouterdateDelete, writingDelete*/);
		request.setAttribute("shoutDlt",sDelete );
		RequestDispatcher dispatcher = request.getRequestDispatcher("shoutDeleteKakunin.jsp");
		dispatcher.forward(request, response);
	}

}
