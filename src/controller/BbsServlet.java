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

@WebServlet("/bbs")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String TOP_DISP = "/top.jsp";
	private final String LOGIN_DISP = "/index.jsp";
	private DBManager dbm; // ログインユーザ情報、書き込み内容管理クラス

	// 直接アクセスがあった場合は index.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		gotoPage(request, response, LOGIN_DISP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//どのボタンが押されたか判定
		String action = request.getParameter("action");

		//セッションの用意
		HttpSession session = request.getSession();

		//叫ぶボタンが押されたら
		if ("shout".equals(action)) {

			//内容を取得
			String writing = request.getParameter("shout");

			//スペースを除去
			writing = writing.replaceAll(" ","");
			writing = writing.replaceAll("　","");

			//書き込みがなければ
			if (writing.equals("")) {

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", "入力されていません。");

				// index.jsp に処理を転送
				gotoPage(request, response, TOP_DISP);

				// 書き込み内容があれば、リストに追加
			} else {

				// セッションからログインユーザ情報を取得
				UserDTO user = (UserDTO) session.getAttribute("user");

				// １度だけ DataManager オブジェクトを生成
				if (dbm == null) {
					dbm = new DBManager();
				}

				// ログインユーザ情報と書き込み内容を引数に、リストに追加するメソッドを呼び出し
				dbm.setWriting(user, writing);

				// 書き込み内容追加後のリストを取得
				ArrayList<ShoutDTO> list = dbm.getShoutList();

				// リストをセッションに保存
				session.setAttribute("shouts", list);

				// top.jsp に処理を転送
				gotoPage(request, response, TOP_DISP);
			}
		}

		//削除ボタンが押されたら
		if ("delete".equals(action)) {

			//選択された叫びのshoutsIdを取得
			String shoutsId = request.getParameter("shoutsId");

			dbm = new DBManager();

			//DBから削除するメソッドを呼び出す
			dbm.deleteWriting(shoutsId);

			// 書き込み内容削除後のリストを取得
			ArrayList<ShoutDTO> list = dbm.getShoutList();

			// リストをセッションに保存
			session.setAttribute("shouts", list);
		}
		// top.jsp に処理を転送
		gotoPage(request, response, TOP_DISP);
	}

	//転送処理
	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
