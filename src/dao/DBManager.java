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
				user.setLoginId(rset.getString(2));//2列目はログインID
				user.setPassword(rset.getString(3));//3列目はPW
				user.setUserName(rset.getString(4));//userName(表示名
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
				shout.setShoutsId(rset.getString(1));
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

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する ここでユーザー情報の入ったUserDTO型の変数userを取得している
	public boolean setWriting(UserDTO user, String writing) {//引数にloginIdを追加
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行　insert文にloginIdを追加（カラムでは2番目）して書き込み内容のデータにloginIdを持たせる
			String sql = "INSERT INTO shouts(loginId,userName, icon, date, writing) VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getLoginId());//valuesの1番目の?にuserのget.loginIdを設定
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

	public UserDTO getShinkiUser(String loginId) { //ログインID重複確認用
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));//userに登録済みのログインIDがあれば代入している
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
		return user;//代入した中身を返す
	}

	/*	public UserDTO getShinkiUserName(String userName) { //表示名重複確認用
			Connection conn = null;            // データベース接続情報
			PreparedStatement pstmt = null;    // SQL 管理情報
			ResultSet rset = null;             // 検索結果

			String sql = "SELECT * FROM users WHERE userName=?";
			UserDTO user = null;    // 登録ユーザ情報

			try {
				// データベース接続情報取得
				conn = getConnection();

				// SELECT 文の登録と実行
				pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
				pstmt.setString(1, userName);
				rset = pstmt.executeQuery();

				// 検索結果があれば
				if (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setLoginId(rset.getString(4));//userに登録済みのログインIDがあれば代入している
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// データベース切断処理
				close(rset);
				close(pstmt);
				close(conn);
			}
			return user;//代入した中身を返す
		}
	*/
	public boolean setShinkiUser(UserDTO newerUser) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO users(loginId,password,userName, icon, profile) VALUES(?, ?, ?, ?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newerUser.getLoginId());
			pstmt.setString(2, newerUser.getPassword());
			pstmt.setString(3, newerUser.getUserName());
			pstmt.setString(4, newerUser.getIcon());
			pstmt.setString(5, newerUser.getProfile());
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

	public ShoutDTO getShoutDlt(String shoutsId/*,String userName,String icon,String date,String writing*/) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM shouts WHERE shoutsId=?";// AND userName=? AND icon=? AND date=? AND writing=?";
		ShoutDTO shoutDlt = null;

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shoutsId);
			/*		pstmt.setString(2, userName);
					pstmt.setString(3, icon);
					pstmt.setString(4, date);
					pstmt.setString(5, writing);*/
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {

				shoutDlt = new ShoutDTO();
				shoutDlt.setShoutsId(rset.getString(1)); //書き込み番号から消せればコード量削減に使えるかも？
				shoutDlt.setLoginId(rset.getString(2));
				shoutDlt.setUserName(rset.getString(3));
				shoutDlt.setIcon(rset.getString(4));
				shoutDlt.setDate(rset.getString(5));
				shoutDlt.setWriting(rset.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return shoutDlt;

	}

	public boolean setWriting2(String shoutsId/*,String userName,String icon,String date,String writing*/) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// delete 文の登録と実行
			String sql = "DELETE FROM shouts WHERE shoutsId=?";// AND userName=? AND icon=? AND date=? AND writing=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shoutsId);
			/*		pstmt.setString(2, userName);
					pstmt.setString(3, icon);
					pstmt.setString(4, date);
					pstmt.setString(5, writing);*/

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

	public ArrayList<UserDTO> getUserList(String str, String uk2, String uk3, String uI, String uI2, String uI3,
			String uI4, String uI5) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// SELECT 文の実行

		String sql = "SELECT * FROM users WHERE loginId LIKE ? OR username LIKE ? "
				+ "OR profile LIKE ? OR icon=? OR icon=? OR icon=? OR icon=? OR icon=?";
		ArrayList<UserDTO> list = new ArrayList<>();//ダイヤモンド演算子？
		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + str + "%");
			pstmt.setString(2, "%" + uk2 + "%");
			pstmt.setString(3, "%" + uk3 + "%");
			pstmt.setString(4, uI);
			pstmt.setString(5, uI2);
			pstmt.setString(6, uI3);
			pstmt.setString(7, uI4);
			pstmt.setString(8, uI5);
			rset = pstmt.executeQuery();

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				UserDTO sUser = new UserDTO();
				sUser.setUserId(rset.getString(1));
				sUser.setLoginId(rset.getString(2));
				sUser.setUserName(rset.getString(4));
				sUser.setIcon(rset.getString(5));
				sUser.setProfile(rset.getString(6));
				// 取り出したユーザー内容をリストに追加
				list.add(sUser);
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

	public UserDTO getUserDeleteKakunin(String userId) { //ユーザーＩＤ（主キー）からユーザーを検索するメソッド
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE userId=?";
		//ArrayList<UserDTO> list = new ArrayList<>();
		UserDTO user = new UserDTO();
		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user.setUserId(rset.getString(1));
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

	public boolean setWritingDelete(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// delete 文の登録と実行
			String sql = "DELETE FROM shouts WHERE loginId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);

			int cnt = pstmt.executeUpdate();
			if (cnt >= 1/*== 1*/) {

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

	public boolean setUserDelete(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// delete 文の登録と実行
			String sql = "DELETE FROM users WHERE loginId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);

			int cnt = pstmt.executeUpdate();
			if (cnt >= 1/*== 1*/) {

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

	public boolean setUserUpdate(String newerPw, String newerName, String genderIcon, String newerProf, String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			String sql = "UPDATE users SET password=?,userName=?,icon=?,profile=? WHERE userID=?";/*(loginId,password,userName, icon, profile) VALUES(?, ?, ?, ?,?)";*/
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newerPw);
			pstmt.setString(2, newerName);
			pstmt.setString(3, genderIcon);
			pstmt.setString(4, newerProf);
			pstmt.setString(5, userId);
			int cnt = pstmt.executeUpdate();
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

	public boolean setShoutsUpdate(String newerName, String genderIcon, String sLoginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			String sql = "UPDATE shouts SET userName=?,icon=? WHERE loginId=?";/*(loginId,password,userName, icon, profile) VALUES(?, ?, ?, ?,?)";*/
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newerName);
			pstmt.setString(2, genderIcon);
			pstmt.setString(3, sLoginId);
			int cnt = pstmt.executeUpdate();
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

	public ArrayList<UserDTO> getUserList2(String uk, String uk2, String uk3, String[] icon) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String iconSearch = "(";//検索を絞り込むため、（icon=~~ OR icon=~~)ANDとなるように括弧を付ける。ANDがOR条件にかかる。
		String i ="or ";
		int n = 1;
		int m =icon.length;
		for(String s:icon) {
			if(n == m) {//受け取った配列が１つしか無ければ　ex. icon='icon-user' andになる。
				i = ")and ";//icon配列の数だけfor文が回った後、"or "だったiが"and "に変わる。andの前に)を入れておくことで、後ろのAND条件がかかる。
			}//""で囲まれた文章は文字列扱いになってしまうため、SQLを動かすためには変数を''で囲む
			iconSearch +=(" icon='" + s +"' " + i);
			n++;
		}
		//アイコンの入力が無ければ空白で上書き
		if("".equals(icon[0])) {
			iconSearch=" ";
		}

		// SELECT 文の実行
		//String sql = "SELECT * FROM users WHERE loginId LIKE ? OR username LIKE ? "
				//+ "OR profile LIKE ? OR icon=? OR icon=? OR icon=? OR icon=? OR icon=?";
		String sql = "SELECT * FROM users WHERE" + iconSearch + "loginId LIKE ? AND userName LIKE ? AND profile LIKE ?";


		ArrayList<UserDTO> list = new ArrayList<>();//ダイヤモンド演算子？
		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + uk + "%");
			pstmt.setString(2, "%" + uk2 + "%");
			pstmt.setString(3, "%" + uk3 + "%");
			/*pstmt.setString(4, uI);
			pstmt.setString(5, uI2);
			pstmt.setString(6, uI3);
			pstmt.setString(7, uI4);
			pstmt.setString(8, uI5);*/
			rset = pstmt.executeQuery();

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				UserDTO sUser = new UserDTO();
				sUser.setUserId(rset.getString(1));
				sUser.setLoginId(rset.getString(2));
				sUser.setUserName(rset.getString(4));
				sUser.setIcon(rset.getString(5));
				sUser.setProfile(rset.getString(6));
				// 取り出したユーザー内容をリストに追加
				list.add(sUser);
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
