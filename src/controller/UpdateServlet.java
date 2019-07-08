package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
 * Servlet implementation class deleteServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private final String SEARCH_DISP = "/search.jsp";
	private final String DEL_CONF_DISP = "/deleteConfirm.jsp";
	private final String DEL_COMP_DISP = "/deleteComplete.jsp";
	private final String UPD_INPUT_DISP = "/update.jsp";
	private final String UPD_CONF_DISP = "/updateConfirm.jsp";
	private final String UPD_COMP_DISP = "/updateComplete.jsp";
	private final String CHECK_ERR = "※更新する場合は複数選択できません。";
	private final String ERR_ID_REQUIRE = "※IDは必ず入力してください。";
	private final String ERR_ID_CODE = "※IDは英数字のみ入力してください。";
	private final String ERR_PASS_REQUIRE = "※パスワードは必ず入力してください。";
	private final String ERR_NAME_REQUIRE = "※名前は必ず入力してください。";
	private final String ERR_NAME_ZEN = "※名前は全角で入力してください。";
	private final String ERR_ID_USED = "※既に使われているIDです。";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//セッション用意
		HttpSession session = request.getSession();

		//どのボタンが押されたか判定
		String action = request.getParameter("action");

		DBManager dbm = new DBManager();

		//検索結果画面から削除ボタンが押されたら
		if ("deleteConfirm".equals(action)) {

			//選択されたユーザのID取得
			String[] checkedUser = request.getParameterValues("check");

			//リスト用意
			ArrayList<UserDTO> userList = new ArrayList<>();

			//DBからユーザ情報取得
			for (String loginId : checkedUser) {
				userList.add(dbm.selectUserById(loginId));
			}

			//値保持のためセッションに保存
			session.setAttribute("deleteUsers", userList);

			//削除確認画面に処理を転送
			gotoPage(request, response, DEL_CONF_DISP);

		}

		//削除確認画面からキャンセルが押されたら
		if ("cancel".equals("action")) {

			//セッション破棄
			session.removeAttribute("deleteUsers");

			//検索結果画面に戻る
			gotoPage(request, response, SEARCH_DISP);

		}

		//確認画面から削除ボタンが押されたら
		if ("delete".equals(action)) {

			//セッションからユーザ取得
			ArrayList<UserDTO> list = (ArrayList<UserDTO>) session.getAttribute("deleteUsers");

			//叫びとユーザ削除メソッド呼び出し
			for (UserDTO user : list) {
				dbm.deleteWritings(user.getLoginId());
				dbm.deleteUser(user.getLoginId());
			}

			//削除完了画面に処理を転送
			gotoPage(request, response, DEL_COMP_DISP);
		}

		//削除完了画面からユーザの一覧ボタンが押されたら
		if ("back".equals(action)) {

			//削除したユーザを除く検索結果を取得
			ArrayList<UserDTO> list = (ArrayList<UserDTO>) session.getAttribute("resultUsers"); //前の検索結果
			ArrayList<String> loginIds = new ArrayList<>();
			for (UserDTO user : list) {
				loginIds.add(user.getLoginId()); //IDを取得
			}
			ArrayList<UserDTO> userList = new ArrayList<>();
			for (String loginId : loginIds) {
				userList.addAll(dbm.searchUserById(loginId)); //IDを使って検索しなおす
			}

			//セッションの上書き
			session.setAttribute("resultUsers", userList);

			//検索結果画面に戻る
			gotoPage(request, response, SEARCH_DISP);
		}

		//検索結果画面から更新ボタンが押されたら
		if ("update".equals(action)) {

			//選択されたユーザを取得
			String[] checkedUser = request.getParameterValues("check");

			//複数選択されていたら検索結果画面に戻り、エラー表示
			if (checkedUser.length != 1) {
				request.setAttribute("alert2", CHECK_ERR);
				gotoPage(request, response, SEARCH_DISP);

				//一つだったら
			} else {

				UserDTO user = new UserDTO();

				//ユーザ情報を取得
				user = dbm.selectUserById(checkedUser[0]);

				//セッションに保存
				session.setAttribute("updateUser", user);

				//更新画面に処理を転送
				gotoPage(request, response, UPD_INPUT_DISP);
			}
		}

		//更新画面でキャンセルボタンが押されたら
		if ("noUpdate".equals(action)) {

			//セッション破棄
			session.removeAttribute("updateUser");

			//検索結果画面に戻る
			gotoPage(request, response, SEARCH_DISP);

			//確認ボタンが押されたら
		} else if ("confirm".equals(action)) {

			//パラメータの取得
			String loginId = trimSpace(request.getParameter("loginId"));
			String password = trimSpace(request.getParameter("password"));
			String name = trimSpace(request.getParameter("name"));
			String icon = trimSpace(request.getParameter("icon"));
			String profile = trimSpace(request.getParameter("profile"));

			//インスタンス化
			UserDTO user = new UserDTO(loginId, password, name, icon, profile);

			//セッションの上書き
			session.setAttribute("updateUser", user);

			//エラーメッセージ用リスト
			HashMap<String, String> errList = new HashMap<>();

			//入力チェック
			errList = checkInput(loginId, password, name);

			//エラーがあれば入力画面へ戻る
			if (errList.size() != 0) {
				request.setAttribute("alert", errList);
				gotoPage(request, response, UPD_INPUT_DISP);

				//無ければ確認画面へ
			} else {
				gotoPage(request, response, UPD_CONF_DISP);
			}
		}

		//更新確認画面で修正ボタンが押されたら
		if ("fix".equals(action)) {

		}

	}

	//転送処理
	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	//入力チェック
		private HashMap<String, String> checkInput(String loginId, String password, String name) {
			HashMap<String, String> list = new HashMap<>();
			if ("".equals(loginId)) {     //未入力
				list.put("idErr", ERR_ID_REQUIRE);
			} else if (!isEisuji(loginId)) {     //英数字以外
				list.put("idErr", ERR_ID_CODE);
			} else if (dbm.checkLoginId(loginId)) {     //ID重複
				list.put("idErr", ERR_ID_USED);
			}
			if ("".equals(password)) {     //未入力
				list.put("passErr", ERR_PASS_REQUIRE);
			}
			if ("".equals(name)) {     //未入力
				list.put("nameErr", ERR_NAME_REQUIRE);
			} else if (!isZenkaku(name)) {     //全角以外
				list.put("nameErr", ERR_NAME_ZEN);
			}
			return list;
		}

		//スペース除去
		private String trimSpace(String input) {
			input = input.replaceAll(" ", "");
			input = input.replaceAll("　", "");
			return input;
		}

		//全角チェック
		private boolean isZenkaku(String input) {
			boolean result = true;
			if (input.matches("^[^ -~｡-ﾟ]+$")) {
				result = true;
			} else {
				result = false;
			}
			return result;
		}

		//英数字チェック
		private boolean isEisuji(String input) {
			boolean result = true;
			if (input.matches("[0-9a-zA-Z]+")) {
				result = true;
			} else {
				result = false;
			}
			return result;
		}
}
