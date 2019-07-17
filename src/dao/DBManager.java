package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShoutDTO;
import dto.UserDTO;

public class DBManager extends SnsDAO {

	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return user;
	}

	// 書き込み内容リストの getter
	public ArrayList<ShoutDTO> getShoutList() {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();
			pstmt = conn.createStatement();

			// SELECT 文の実行
			String sql = "SELECT * FROM shouts ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				ShoutDTO shout = new ShoutDTO();
				shout.setshoutsId(rset.getString(1));
				shout.setLoginId(rset.getString(2));
				shout.setUserName(rset.getString(3));
				shout.setIcon(rset.getString(4));
				shout.setDate(rset.getString(5));
				shout.setWriting(rset.getString(6));

				// 書き込み内容をリストに追加
				list.add(shout);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return list;
	}

	// 書き込み内容リストの getter
		public ArrayList<UserDTO> getUserList() {
			Connection conn = null;
			Statement pstmt = null;
			ResultSet rset = null;

			ArrayList<UserDTO> list = new ArrayList<UserDTO>();

			try {
				// SnsDAO クラスのメソッド呼び出し
				conn = getConnection();
				pstmt = conn.createStatement();

				// SELECT 文の実行
				String sql = "SELECT * FROM users WHERE LIKE?";
				rset = pstmt.executeQuery(sql);

				// 検索結果の数だけ繰り返す
				while (rset.next()) {
					// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
					UserDTO searchuser = new UserDTO();
					searchuser.setLoginId(rset.getString(2));
					searchuser.setUserName(rset.getString(4));
					searchuser.setIcon(rset.getString(5));
					searchuser.setProfile(rset.getString(6));

					// 書き込み内容をリストに追加
					list.add(searchuser);
				}
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				// データベース切断処理
				close(rset);
				close(pstmt);
				close(conn);
			}

			return list;
		}


	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public boolean deleteshouts(String shoutsId) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		int cnt = 0;
		boolean result = false;

		String sql ="DELETE FROM shouts WHERE shoutsId=?";

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shoutsId);
			cnt = pstmt.executeUpdate(); //cntの時if文

			if (cnt == 1) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public boolean setnewUser(UserDTO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO users(loginId, password,userName,icon,profile) VALUES(?, ?, ?, ?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getIcon());
			pstmt.setString(5, user.getProfile());

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT 文の実行結果が１なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO shouts(loginId,userName, icon, date, writing) VALUES(?,?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getIcon());
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(4, sdf.format(calender.getTime()));
			pstmt.setString(5, writing);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT 文の実行結果が１なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	public UserDTO getnewLoginUser(String loginId, String password) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return user;
	}

	public boolean loginsearchUser(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null; // 検索結果
		boolean result = false;

		try {
			conn = getConnection();

			// SELECT 文の登録と実行
			String sql = "SELECT * FROM users WHERE loginId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				// SELECT 文の実行結果がtrueなら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return result;
	}

	public ArrayList<UserDTO> searchUser(String loginId,String userName,String icon,String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null; // 検索結果
		UserDTO searchuser = null;
		// sql文に入れるための文字列を宣言
		String logsql = null;
		String unamesql = null;
		String iconsql = null;
		String prosql = null;
		// 各項目に対して入っていない場合、カウントするためのint型の変数
		int cnt = 0;

		ArrayList<UserDTO> list = new ArrayList<>();

		try {
			conn = getConnection();
			String sql;

			// SELECT 文の登録と実行
//			String sql = "SELECT * FROM users WHERE (concat(loginId,userName,profile) like ?) and(concat(loginId,userName,profile) like ?)and (concat(loginId,userName,profile) like ?)" ;
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1,"%"+loginId+"%");
//			pstmt.setString(2,"%"+userName+"%");
//			pstmt.setString(3, icon);
//			pstmt.setString(3,"%"+profile+"%");
//			rset = pstmt.executeQuery();



//			String sql = "SELECT * FROM users WHERE (concat(loginId,userName,icon,profile) like ?) and(concat(loginId,userName,icon,profile) like ?)and (concat(loginId,userName,icon,profile) like ?)"
//					+ "and (concat(loginId,userName,icon,profile) like ?)"
//					/*+ "and where icon=?"*/;

			// loginIdが未入力ならSELECT文に追加しない、あるなら追加
			if("".equals(loginId)) {
				logsql = "";	// 未入力なので何もなし
				cnt++;	// 未入力なのでカウント+1
			} else {
				logsql = "loginId like '%" + loginId + "%'"; // loginId like '%~~%'
			}
			if("".equals(userName)) {// userNameが未入力ならSELECT文に追加しない
				unamesql = "";	// 未入力なので何もなし
				cnt++;	// 未入力なのでカウント+1
			} else {
				if(cnt == 1) {	// 初めてsql文に登録する場合、最初にandやorはつけない
				unamesql = "userName like '%" + userName + "%'";
				} else {	// 前の文章に続けて書くのでandやorをつける
					unamesql = " and userName like '%" + userName + "%'";
				}
			}
			if("who".equals(icon)) {// iconが未入力ならSELECT文に追加しない、あるならSQL文にLIKE検索じゃなく=で入れる
				iconsql = "";	// 未入力なので何もなし
				cnt++;	// 未入力なのでカウント+1
			} else {
				if(cnt == 2) {	// 初めてsql文に登録する場合、最初にandやorはつけない
					iconsql = "icon='" + icon + "'";
				}else {	// 前の文章に続けて書くのでandやorをつける
					iconsql = " and icon='" + icon + "'";
				}
			}
			if("".equals(profile)) {// profileが未入力ならSELECT文に追加しない
				prosql = "";	// 未入力なので何もなし
				cnt++;	// 未入力なのでカウント+1
			} else {
				if(cnt == 3) {	// 初めてsql文に登録する場合、最初にandやorはつけない
					prosql = "profile like '%" + profile + "%'";
				}else {	// 前に続けて書くのでandやorをつける
					prosql = " and profile like '%" + profile + "%'";
				}
			}
			// loginId～profileがすべて未入力なら全検索(例)、サーブレット側で先に弾いてもよし。
			if(cnt == 4) {	// すべての項目が未入力の場合、全検索をする
				sql = "SELECT * FROM users";
			} else {	// それ以外の場合は以下の文章を実行する。
				sql = "SELECT * FROM users WHERE " + logsql + unamesql + iconsql + prosql;
			}


//			String sql = "SELECT * FROM users WHERE loginId like ? and userName like ? and icon=? and profile like ?";

			pstmt = conn.prepareStatement(sql);
			/*pstmt.setString(1,"%"+loginId+"%");
			pstmt.setString(2,"%"+userName+"%");
			pstmt.setString(3, "%"+icon+"%" );
			pstmt.setString(4,"%"+profile+"%");*/
			rset = pstmt.executeQuery();


			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				searchuser = new UserDTO();
				searchuser.setLoginId(rset.getString(2));
				searchuser.setUserName(rset.getString(4));
				searchuser.setIcon(rset.getString(5));
				searchuser.setProfile(rset.getString(6));

				// 書き込み内容をリストに追加
				list.add(searchuser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);

		}
		return list;

	}
}
