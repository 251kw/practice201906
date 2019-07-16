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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SEARCH_DISP = "/search.jsp";
	private final String TOP_DISP = "/top.jsp";
	private final String ERR_NULL = "※検索結果がありません。";
	private final String ERR_NO_INPUT = "※検索条件が入力されていません。";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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

		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//セッション用意
		HttpSession session = request.getSession();

		//どのボタンが押されたか判定
		String action = request.getParameter("action");

		DBManager dbm = new DBManager();

		//ユーザ検索ボタンが押されたら
		if ("usersearch".equals(action)) {

			//セッション破棄
			session.removeAttribute("searchUser");
			session.removeAttribute("resultUsers");

			//ユーザ検索画面に処理を転送
			gotoPage(request, response, SEARCH_DISP);
		}

		//検索ボタンが押されたら
		if ("search".equals(action)) {

			//セッション破棄
			session.removeAttribute("resultUsers");

			//パラメータ取得
			String loginId = trimSpace(request.getParameter("loginId"));
			String name = trimSpace(request.getParameter("name"));
			String icon = request.getParameter("icon");
			String profile = trimSpace(request.getParameter("profile"));

			//インスタンス化
			UserDTO user = new UserDTO();
			user.setLoginId(loginId);
			user.setUserName(name);
			user.setIcon(icon);
			user.setProfile(profile);

			//セッションに保存
			session.setAttribute("searchUser", user);

			//何も入力されていなければ検索画面に戻ってエラー表示
			if ("".equals(loginId) && "".equals(name) && "--".equals(icon) && "".equals(profile)) {
				request.setAttribute("alert2", ERR_NO_INPUT);
			} else {

				//DBMのユーザ検索メソッド呼び出してリストに入れる
				ArrayList<UserDTO> userList = dbm.searchUser(loginId, name, icon, profile);

				//検索結果がなければメッセージ表示
				if (userList.size() == 0) {
					request.setAttribute("alert", ERR_NULL);

					//あれば検索結果をセッションに登録
				} else {
					session.setAttribute("resultUsers", userList);
				}
			}

			//処理をsearch.jspに転送
			gotoPage(request, response, SEARCH_DISP);
		}

		//全ユーザ検索ボタンが押されたら
		if ("searchall".equals(action)) {

			//全ユーザ検索メソッド呼び出し
			ArrayList<UserDTO> userList = dbm.searchAllUser();

			//セッションに保存
			session.setAttribute("resultUsers", userList);

			//処理をsearch.jspに転送
			gotoPage(request, response, SEARCH_DISP);
		}

		//search.jspの戻るボタンが押されたらtop.jspに処理を転送
		if ("top".equals(action)) {
			gotoPage(request, response, TOP_DISP);
		}

	}

	//転送処理
	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	//スペース除去
	private String trimSpace(String input) {
		input = input.replaceAll(" ", "");
		input = input.replaceAll("　", "");

		return input;
	}

}
