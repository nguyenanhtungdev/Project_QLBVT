package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;

public class VeTau_DAO {

	private static VeTau_DAO instance;

	public static VeTau_DAO getInstance() {
		return instance == null ? instance = new VeTau_DAO() : instance;
	}

	public List<VeTau> getAll() throws SQLException {
		String sql = "Select * FROM VeTau";

		Connection con = Database.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<VeTau> list = new ArrayList<>();
		while (resultSet.next()) {
			String maVeTau = resultSet.getString("maVeTau");
			boolean loaiVe = resultSet.getBoolean("loaiVe");
			LocalDateTime ngayHetHan = resultSet.getTimestamp("ngayHetHan").toLocalDateTime();
			boolean daHuy = resultSet.getBoolean("daHuy");

			GheTau gheTau = new GheTau(resultSet.getString("maGheTau"));

			list.add(new VeTau(maVeTau, loaiVe, ngayHetHan, daHuy, gheTau));
		}

		return list;
	}

	public String getVeTauMax() {
		String sql = "SELECT MAX(maVeTau) FROM VeTau";
		Connection con;
		String maVeTau = null;
		try {
			con = Database.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				maVeTau = resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maVeTau;
	}

	public VeTau getByMaVeTau(String maVeTau) throws SQLException {
		String sql = "SELECT * FROM VeTau WHERE maVeTau = ?";

		Connection con = Database.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, maVeTau);

		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			boolean loaiVe = resultSet.getBoolean("loaiVe");
			LocalDateTime ngayHetHan = resultSet.getTimestamp("ngayHetHan").toLocalDateTime();
			boolean daHuy = resultSet.getBoolean("daHuy");

			GheTau gheTau = new GheTau(resultSet.getString("maGheTau"));

			return new VeTau(maVeTau, loaiVe, ngayHetHan, daHuy, gheTau);
		}

		return null;
	}

	public boolean add(VeTau veTau) throws SQLException {
		String sql = "INSERT INTO VeTau (maVeTau, loaiVe, ngayHetHan, daHuy, maGheTau) VALUES (?, ?, ?, ?, ?)";

		Connection con = Database.getInstance().getConnection();

		PreparedStatement statement = con.prepareStatement(sql);

		statement.setString(1, veTau.getMaVeTau());
		statement.setBoolean(2, veTau.isLoaiVe());
		statement.setTimestamp(3, Timestamp.valueOf(veTau.getNgayHetHan()));
		statement.setBoolean(4, veTau.isDaHuy());
		statement.setString(5, veTau.getGheTau().getMaGheTau());

		int count = statement.executeUpdate();

		return count == 1;

	}

}
