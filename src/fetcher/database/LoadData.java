package fetcher.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadData {

	public void updateData(String url, String time) {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();

		try {

			stmt = conn
					.prepareStatement("update  news set  time= ?  where url =  ?");
			stmt.setString(1, time);

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

	public ArrayList<String> LoadUrl() {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select * from news where time = '' ";

		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				list.add(rs.getString("url"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public ArrayList<String> LoadAllNews() {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select time from news  ";

		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				list.add(rs.getString("time"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
