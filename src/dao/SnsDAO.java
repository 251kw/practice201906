package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SnsDAO {
/*	private final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
	private final String USER = "root";
	private final String PASSWORD = "root";
*/
	private final String DSN = "jdbc:h2:tcp://localhost/~/test";
    private final String USER = "sa";
    private final String PASSWORD = "";
    private final String DRIVER = "org.h2.Driver";

	// データベースの接続情報を返す
	public Connection getConnection(){
		Connection conn = null;

		try {
			// JDBC ドライバのロード
            Class.forName(DRIVER);

            // データベースへ接続
            conn = DriverManager.getConnection(DSN, USER, PASSWORD);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

		return conn;
	}

	// Connection 型変数が持つデータベースと JDBC リソースの解放
	public void close(Connection conn) {
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Statement 型変数が持つデータベースと JDBC リソースの解放
	public void close(Statement stmt) {
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ResultSet 型変数が持つデータベースと JDBC リソースの解放
	public void close(ResultSet rset) {
		if(rset != null){
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
