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
import dto.ShoutDTO;
import dto.UserDTO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBManager dbm = new DBManager();
	private final String ERR_ID_REQUIRE = "※IDは必ず入力してください。";
	private final String ERR_ID_CODE = "※IDは英数字のみ入力してください。";
	private final String ERR_PASS_REQUIRE = "※パスワードは必ず入力してください。";
	private final String ERR_NAME_REQUIRE = "※名前は必ず入力してください。";
	private final String ERR_NAME_ZEN = "※名前は全角で入力してください。";
	private final String ERR_ID_USED = "※既に使われているIDです。";
	private final String INPUT_DISP = "/register.jsp";
	private final String CONF_DISP = "/registerConfirm.jsp";
	private final String DONE_DISP = "/registerComplete.jsp";
	private final String TOP_DISP = "/top.jsp";

	// 直接アクセスがあった場合は register.jsp に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		gotoPage(request, response, INPUT_DISP);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//ボタンの判定
		String action = request.getParameter("action");

		//セッションの用意
		HttpSession session = request.getSession();

		//確認ボタンが押された時
		if ("confirm".equals(action)) {

			//パラメータの取得
			String loginId = trimSpace(request.getParameter("loginId"));
			String password = trimSpace(request.getParameter("password"));
			String name = trimSpace(request.getParameter("name"));
			String icon = trimSpace(request.getParameter("icon"));
			String profile = trimSpace(request.getParameter("profile"));

			//インスタンス化
			UserDTO user = new UserDTO(loginId, password, name, icon, profile);

			//入力値保持のためセッションに保存
			session.setAttribute("user", user);

			//エラーメッセージ用リスト
			HashMap<String, String> errList = new HashMap<>();

			//入力チェック
			errList = checkInput(loginId,password,name);

			//エラーがあれば入力画面へ戻る
			if (errList.size() != 0) {
				request.setAttribute("alert", errList);
				gotoPage(request, response, INPUT_DISP);

				//無ければ確認画面へ
			} else {
				gotoPage(request, response, CONF_DISP);
			}
		}

		//確定ボタンが押されたらDBに登録
		if ("register".equals(action)) {
			UserDTO user = (UserDTO) session.getAttribute("user");
			if (user != null) {
				dbm.setUser(user);
				//登録完了画面に処理を転送
				gotoPage(request, response, DONE_DISP);
			}
		}

		//マイページへボタンが押されたらログインし、トップ画面へ
		if ("top".equals(action)) {

			UserDTO user = (UserDTO) session.getAttribute("user");

			//ログイン処理
			user = dbm.getLoginUser(user.getLoginId(), user.getPassword());

			//叫びの一覧を取得
			if (user != null) {
				ArrayList<ShoutDTO> list = dbm.getShoutList();
				session.setAttribute("shouts", list);
				gotoPage(request, response, TOP_DISP);
			}
		}

		//修正ボタンが押されたら
		if ("fix".equals(action)) {

			//入力画面に戻る
			gotoPage(request, response, INPUT_DISP);
		}

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

	//転送処理
	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
