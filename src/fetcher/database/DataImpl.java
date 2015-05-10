package fetcher.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fetcher.database.pojo.Content;
import fetcher.pojo.News;

public class DataImpl {
	public void saveData(News n) {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();

		try {

			stmt = conn
					.prepareStatement("INSERT INTO news (`keyword`,`title`,`url`,`time`,`md5`) VALUES (?,?,?,?,?)");
			stmt.setString(1, n.getKeyword());
			stmt.setString(2, n.getTitle());
			stmt.setString(3, n.getUrl());
			stmt.setString(4, n.getTime());
			stmt.setString(5, n.getMd5());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveData(Content content) {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();

		try {

			stmt = conn
					.prepareStatement("INSERT INTO news (`keyword`,`title`,`text`,`url`,`time`) VALUES (?,?,?,?,?)");
			stmt.setString(1, content.getKeyword());
			stmt.setString(2, content.getTitle());
			stmt.setString(3, content.getText());
			stmt.setString(4, content.getUrl());
			stmt.setString(5, content.getTime());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveTitleUrl(String url) {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();

		try {

			stmt = conn.prepareStatement("INSERT INTO url (`url`) VALUES (?)");
			stmt.setString(1, url);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveTextUrl(String md5, String url) {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();

		try {

			stmt = conn
					.prepareStatement("INSERT INTO url1 (`md5`,`url`) VALUES (?,?)");
			stmt.setString(1, md5);
			stmt.setString(2, url);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
