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
	private DBManager dbm;	// ログインユーザ情報、書き込み内容管理クラス

	// 直接アクセスがあった場合は index.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	// top.jsp の「叫ぶ」ボタンから呼ばれる
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writing = request.getParameter("shout");

		//正規表現を用いて、変数writingで受け取ったshoutの内容から全角スペースを空文字に置換する。
		//"^[\\h]+"は"^"=行の先頭の、[]内の文字、"\\h"は空白を"+"あるだけ""空文字に置き換える。
		//"[\\h]+$"$は文末という意味で文末からあるだけ空白を空文字に置き換えている。
		writing=writing.replaceFirst("^[\\h]+", "").replaceFirst("[\\h]+$", "");
		//writing = writing.trim();

		//String loginId = request.getParameter("userid");//叫ぶボタンにuser.loginIdを乗せて取得
		RequestDispatcher dispatcher;
		String message ="叫び内容を入力してください。";
		// 書き込み内容があれば、リストに追加
		if (!writing.equals("")) {
			HttpSession session = request.getSession();
			// セッションからログインユーザ情報を取得
			UserDTO user = (UserDTO) session.getAttribute("user");

			// １度だけ DataManager オブジェクトを生成
			if(dbm == null){
				dbm = new DBManager();
			}

			// ログインユーザ情報と書き込み内容を引数に、リストに追加するメソッドを呼び出し
			dbm.setWriting(user, writing);

			// 書き込み内容追加後のリストを取得
			ArrayList<ShoutDTO> list = dbm.getShoutList();

			// リストをセッションに保存
			session.setAttribute("shouts", list);
			// top.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("top.jsp");
		}else {
			request.setAttribute("alert", message);
			dispatcher = request.getRequestDispatcher("top.jsp");
		}

		// top.jsp に処理を転送

		dispatcher.forward(request, response);
	}
}
