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

	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
		public boolean deleteshouts(String shoutsId) {
			Connection conn = null;            // データベース接続情報
			PreparedStatement pstmt = null;    // SQL 管理情報
			int cnt = 0;
			boolean result = false;

			String sql = "DELETE * FROM shouts WHERE shoutsId=?";

			try {
				// データベース接続情報取得
				conn = getConnection();

				// SELECT 文の登録と実行
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shoutsId);
				cnt = pstmt.executeUpdate();    //cntの時if文

				if(cnt == 1) {
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
			String sql = "INSERT INTO shouts(shoutsId,loginId,userName, icon, date, writing) VALUES(?,?, ?, ?, ?)";
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

public boolean serchUser(String loginId) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;             // 検索結果
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
}
