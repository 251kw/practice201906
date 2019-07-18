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

	//入力チェックでログインIdの被りがあるか検索する。
	public boolean serchData(String loginId) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=?";
		boolean result = true; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				result = false;
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

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO shouts(loginId, userName, icon, date, writing) VALUES(?, ?, ?, ?, ?)";
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

	public boolean InsertNew(UserDTO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			String sql = "INSERT INTO users(loginId, password, userName, icon, profile) VALUES(?, ?, ?, ?, ?)";
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
			// エラー処理はどこまでやるか？
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}
		return result;
	}

	public boolean DeleteNew(String loginId, String date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		String sql = "DELETE FROM shouts WHERE loginId=? AND date=?";
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);
			pstmt.setString(2, date);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT 文の実行結果が１なら登録成功
				result = true;
			}

		} catch (SQLException e) {
			// エラー処理はどこまでやるか？
			e.printStackTrace();

		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}
		return result;
	}


	//ライク検索
	public ArrayList<UserDTO> getUserlist(String loginId,String userName,String icon,String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<UserDTO> Userlist = new ArrayList<UserDTO>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			// SELECT 文の実行
			String sql = "SELECT * FROM users WHERE (loginId LIKE ? AND userName LIKE ? AND icon LIKE ? AND profile LIKE ?)";
			pstmt = conn.prepareStatement(sql);


			if(loginId=="") {
				pstmt.setString(1, loginId +"%");
			}else {
				pstmt.setString(1, "%" + loginId +"%");
			}
			if(userName=="") {
				pstmt.setString(2, userName +"%");
			}else {
				pstmt.setString(2, "%" + userName +"%");
			}

			pstmt.setString(3, "%"+icon);


			pstmt.setString(4, "%" + profile +"%");



			rset = pstmt.executeQuery();

			while (rset.next()) {

				UserDTO user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));

				// ユーザー情報に検索分追加
				Userlist.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
		return Userlist;
	}



	// 選択したログインIdのユーザー情報をリストに入れて返す。
	public ArrayList<UserDTO> getDuserList(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null;

		ArrayList<UserDTO> DUlist = new ArrayList<UserDTO>();

		try {

			// SELECT 文の実行
			String sql = "SELECT * FROM users WHERE loginId=?";

			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				UserDTO Duser = new UserDTO();
				Duser.setLoginId(rset.getString(2));
				Duser.setPassword(rset.getString(3));
				Duser.setUserName(rset.getString(4));
				Duser.setIcon(rset.getString(5));
				Duser.setProfile(rset.getString(6));

				// 書き込み内容をリストに追加
				DUlist.add(Duser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return DUlist;
	}

	public boolean DeleteUser(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		String sql = "DELETE FROM users WHERE loginId=?";
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT 文の実行結果が１なら登録成功
				result = true;
			}

		} catch (SQLException e) {
			// エラー処理はどこまでやるか？
			e.printStackTrace();

		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}
		return result;
	}

	public boolean DeleteShouts(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		String sql = "DELETE FROM shouts WHERE loginId=?";
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);

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

	public UserDTO getUDUser(String loginId) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=?";
		UserDTO updateUser = null; // 登録ユーザ情報

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
				updateUser = new UserDTO();
				updateUser.setLoginId(rset.getString(2));
				updateUser.setPassword(rset.getString(3));
				updateUser.setUserName(rset.getString(4));
				updateUser.setIcon(rset.getString(5));
				updateUser.setProfile(rset.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return updateUser;
	}

	public boolean UpdateUser(UserDTO Updateuser , String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			String sql = "UPDATE users SET password=?, userName=?, icon=?, profile=? WHERE loginId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Updateuser.getPassword());
			pstmt.setString(2, Updateuser.getUserName());
			pstmt.setString(3, Updateuser.getIcon());
			pstmt.setString(4, Updateuser.getProfile());
			pstmt.setString(5, loginId);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT 文の実行結果が１なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			// エラー処理はどこまでやるか？
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}
		return result;
	}

	public boolean UpdateShouts(UserDTO updateUser, String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "UPDATE shouts SET userName=?, icon=? WHERE loginId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateUser.getUserName());
			pstmt.setString(2, updateUser.getIcon());
			pstmt.setString(3, loginId);


			int cnt = pstmt.executeUpdate();
			if (cnt != 0) {
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

}
