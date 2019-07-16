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
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
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

	// 入力情報を受け取り、登録ユーザ一覧にその語を含むものがあるか検索
	public ArrayList<UserDTO> serchUser(String uName) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		String sql = "SELECT * FROM users WHERE userName LIKE ? OR profile LIKE ? OR icon LIKE ? OR loginId LIKE ?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, "%" + uName + "%");
			pstmt.setString(2, "%" + uName + "%");
			pstmt.setString(3, "%" + uName + "%");
			pstmt.setString(4, "%" + uName + "%");
			rset = pstmt.executeQuery();

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));

				// 書き込み内容をリストに追加
				list.add(user);
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

	//ログインIDを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO checkLoginID(String loginId) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果

		String sql1 = "SELECT * FROM users WHERE loginId=? ";
		UserDTO user1 = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql1);	// SELECT 構文登録
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user1 = new UserDTO();
				user1.setLoginId(rset.getString(2));
				user1.setUserName(rset.getString(4));
				user1.setIcon(rset.getString(5));
				user1.setProfile(rset.getString(6));			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return user1;
	}

	//新規ユーザー登録情報を受け取り、データベースに登録
	public void dbRegistration(String loginId,String password,String userName,String icon,String profile) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果

		String sql2 = "INSERT INTO users(loginId,password,userName,icon,profile) VALUES(?,?,?,?,?)";


		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql2);	// SELECT 構文登録
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			pstmt.setString(3, userName);
			pstmt.setString(4, icon);
			pstmt.setString(5, profile);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
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
				shout.setloginId(rset.getString(2));
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

	//日付を受け取り、一致する書き込みを検索
	public ShoutDTO getWriting(String date) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果

		String sql1 = "SELECT * FROM shouts WHERE date=? ";
		ShoutDTO shout1 = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql1);	// SELECT 構文登録
			pstmt.setString(1, date);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				shout1 = new ShoutDTO();
				shout1.setDate(rset.getString(5));			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return shout1;
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

	//書き込みの日付情報を受け取り、一致する書き込みを削除する
	public void deleteWriting(String date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "DELETE FROM shouts WHERE DATE=? ";

		try {
			conn = getConnection();

			// DELETE 文の登録と実行

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
	}

		//ユーザー編集情報を受け取り、DBの値を更新する
	public void editAccount(String loginId,String password,String userName,String icon,String profile) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			ResultSet rset = null;

			String sql = "UPDATE USERS SET loginId=?,password=?,userName=?,icon=?,profile=? WHERE loginId=? ";
			String sql2 = "UPDATE SHOUTS SET USERNAME =? , ICON =? WHERE LOGINID =? ";

			try {
				conn = getConnection();

				// DELETE 文の登録と実行

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);
				pstmt.setString(2, password);
				pstmt.setString(3, userName);
				pstmt.setString(4, icon);
				pstmt.setString(5, profile);
				pstmt.setString(6, loginId);

				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, userName);
				pstmt2.setString(2, icon);
				pstmt2.setString(3, loginId);


				pstmt.executeUpdate();
				pstmt2.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// データベース切断処理
				close(rset);
				close(pstmt);
				close(conn);
			}

	}

	//ログインIDを受け取り、DBからアカウントを削除する
	public void deleteAccount(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;

		String sql = "DELETE FROM SHOUTS WHERE loginId=? ";
		String sql2 = "DELETE FROM USERS WHERE loginId=? ";

		try {
			conn = getConnection();

			// DELETE 文の登録と実行

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, loginId);

			pstmt.executeUpdate();
			pstmt2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

	}
}
