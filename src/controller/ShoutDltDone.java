package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class ShoutDltDone
 */
@WebServlet("/SDD")
public class ShoutDltDone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoutDltDone() {
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
		String shouterShoutsIdDelete = request.getParameter("shoutsId");
/*		String shouterNameDelete = request.getParameter("shouterName");
		String shouterIconDelete = request.getParameter("shouterIcon");
		String shouterdateDelete = request.getParameter("shouterDate");
		String writingDelete = request.getParameter("shouterWriting");*/

		//boolean sDelete;


		if(dbm == null){
			dbm = new DBManager();
		}
		/*sDelete =*/ dbm.setWriting2(shouterShoutsIdDelete/*,shouterNameDelete, shouterIconDelete, shouterdateDelete, writingDelete*/);
		//request.setAttribute("shoutDlt",sDelete );
		RequestDispatcher dispatcher = request.getRequestDispatcher("shoutDeleteKanryo.jsp");
		dispatcher.forward(request, response);

	}

}
