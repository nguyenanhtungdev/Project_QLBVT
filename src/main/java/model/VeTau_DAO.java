package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class VeTau_DAO {

	private static VeTau_DAO instance;

	public static VeTau_DAO getInstance() {
		if (instance == null)
			instance = new VeTau_DAO();
		return instance;
	}

	public ArrayList<VeTau> getalltbVT() {
		ArrayList<VeTau> veTaus = new ArrayList<>();
		String sql = "Select * FROM VeTau";
		Connection con;

		try {
			con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String maVeTau = resultSet.getString(1);
				String maVach = resultSet.getString(2);
				boolean loaiVe = resultSet.getBoolean(3);
				TrangThaiVeTau trangThai = TrangThaiVeTau.values()[resultSet.getInt(4)];
				ChuyenTau chuyenTau = new ChuyenTau(resultSet.getString(5));

				VeTau vetau = new VeTau(maVeTau, maVach, loaiVe, trangThai, chuyenTau);
				veTaus.add(vetau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return veTaus;
	}

	public String getVeTauMax() {
		ArrayList<VeTau> veTaus = new ArrayList<>();
		String sql = "SELECT MAX(maVeTau) FROM VeTau";
		Connection con;
		String maVeTau = null;
		try {
			con = ConnectDB.getInstance().getConnection();
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

	public VeTau getVeTauByMaVeTau(String maVeTau) {
		VeTau veTau = null;
		String sql = "SELECT * FROM VeTau WHERE maVeTau = ?";
		Connection con;

		try {
			con = ConnectDB.getInstance().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maVeTau);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String maVach = resultSet.getString(2);
				boolean loaiVe = resultSet.getBoolean(3);
				TrangThaiVeTau trangThai = TrangThaiVeTau.values()[resultSet.getInt(4)];
				ChuyenTau chuyenTau = new ChuyenTau(resultSet.getString(5));
				veTau = new VeTau(maVeTau, maVach, loaiVe, trangThai, chuyenTau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return veTau;
	}
	
	public boolean addVeTau(VeTau veTau) {
	    Connection con = null;
	    PreparedStatement preparedStatement = null;

	    try {

	        ConnectDB.getInstance();
	        con = ConnectDB.getInstance().getConnection();

	        String sql = "INSERT INTO VeTau (maVeTau, maVach, loaiVe, trangThai, maChuyenTau) "
	                   + "VALUES (?, ?, ?, ?, ?)";

	        preparedStatement = con.prepareStatement(sql);

	        preparedStatement.setString(1, veTau.getMaVeTau());
	        preparedStatement.setString(2, veTau.getMaVach());
	        preparedStatement.setBoolean(3, veTau.isLoaiVe());
	        preparedStatement.setInt(4, veTau.getTrangThai().ordinal()); // Chuyển trạng thái sang giá trị int
	        preparedStatement.setString(5, veTau.getChuyenTau().getMaChuyenTau());
	        
	        int rowsInserted = preparedStatement.executeUpdate();

	        return rowsInserted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
