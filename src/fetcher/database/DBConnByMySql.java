package fetcher.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnByMySql {
	static String dbdriver = "com.mysql.jdbc.Driver";

	public static Connection getConnection() {
		Connection ret = null;
		try {
			Class.forName(dbdriver);

			String url = "jdbc:mysql://" + "localhost" + "/" + "news"
					+ "?useUnicode=true&characterEncoding=UTF-8";
			ret = DriverManager.getConnection(url, "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
