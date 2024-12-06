package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;
import model.Tau.TrangThaiTau;

public class Tau_DAO {

	private static Tau_DAO instance;

	public static Tau_DAO getInstance() {
		return instance == null ? instance = new Tau_DAO() : instance;
	}

	public List<Tau> getAll() throws SQLException {
		String sql = "SELECT * FROM Tau";

		Connection con = Database.getInstance().getConnection();
		Statement stmt = con.createStatement();
		ResultSet resultSet = stmt.executeQuery(sql);

		List<Tau> list = new ArrayList<>();
		while (resultSet.next()) {
			String maTau = resultSet.getString("maTau");
			String tenTau = resultSet.getString("tenTau");
			int soToa = resultSet.getInt("soToa");
			LocalDate namSanXuat = resultSet.getDate("namSanXuat").toLocalDate();
			String nhaSanXuat = resultSet.getString("nhaSanXuat");
			float tocDoTB = resultSet.getFloat("tocDoTB");
			float tocDoToiDa = resultSet.getFloat("tocDoToiDa");
			TrangThaiTau trangThai = TrangThaiTau.fromInt(resultSet.getInt("trangThai"));
			String ghiChu = resultSet.getString("ghiChu");

			ChuyenTau chuyenTau = new ChuyenTau(resultSet.getString("maChuyenTau"));

			list.add(new Tau(maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu,
					chuyenTau));
		}
		return list;
	}

	public Tau getByMaTau(String maTau) throws SQLException {
		String sql = "SELECT * FROM Tau WHERE maTau = ?";

		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maTau);
		ResultSet resultSet = stmt.executeQuery();

		if (resultSet.next()) {
			String tenTau = resultSet.getString("tenTau");
			int soToa = resultSet.getInt("soToa");
			LocalDate namSanXuat = resultSet.getDate("namSanXuat").toLocalDate();
			String nhaSanXuat = resultSet.getString("nhaSanXuat");
			float tocDoTB = resultSet.getFloat("tocDoTB");
			float tocDoToiDa = resultSet.getFloat("tocDoToiDa");
			TrangThaiTau trangThai = TrangThaiTau.fromInt(resultSet.getInt("trangThai"));
			String ghiChu = resultSet.getString("ghiChu");

			ChuyenTau chuyenTau = new ChuyenTau(resultSet.getString("maChuyenTau"));

			return new Tau(maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu,
					chuyenTau);
		}

		return null;
	}

	public boolean add(Tau tau) throws SQLException {
		String sql = "INSERT INTO Tau (maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con;
		try {
			con = Database.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, tau.getMaTau());
			stmt.setString(2, tau.getTenTau());
			stmt.setInt(3, tau.getSoToa());
			stmt.setDate(4, Date.valueOf(tau.getNamSanXuat()));
			stmt.setString(5, tau.getNhaSanXuat());
			stmt.setFloat(6, tau.getTocDoTB());
			stmt.setFloat(7, tau.getTocDoToiDa());
			stmt.setString(8, tau.getTrangThai().name());
			stmt.setString(9, tau.getGhiChu());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Tau tau) throws SQLException {
		String sql = "UPDATE Tau SET tenTau = ?, soToa = ?, namSanXuat = ?, nhaSanXuat = ?, tocDoTB = ?, tocDoToiDa = ?, trangThai = ?, ghiChu = ? WHERE maTau = ?";
		Connection con;

		try {
			con = Database.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, tau.getTenTau());
			stmt.setInt(2, tau.getSoToa());
			stmt.setDate(3, Date.valueOf(tau.getNamSanXuat()));
			stmt.setString(4, tau.getNhaSanXuat());
			stmt.setFloat(5, tau.getTocDoTB());
			stmt.setFloat(6, tau.getTocDoToiDa());
			stmt.setString(7, tau.getTrangThai().name());
			stmt.setString(8, tau.getGhiChu());
			stmt.setString(9, tau.getMaTau());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Cập nhập trạng thái tàu
	public boolean capNhapTTTau(String maTau, TrangThaiTau trangThaiMoi) throws SQLException {
		String sql = "UPDATE Tau SET trangThai = ? WHERE maTau = ?";
		Connection con;

		try {
			con = Database.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, trangThaiMoi.getTrangThai());
			stmt.setString(2, maTau);

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Lấy thông tin tàu theo mã tàu
	public Tau layTTTauTheoMa(String maTau) throws SQLException {
		String sql = "SELECT * FROM Tau WHERE maTau = ?";

		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, maTau);
		ResultSet resultSet = stmt.executeQuery();

		if (resultSet.next()) {
			String tenTau = resultSet.getString("tenTau");
			int soToa = resultSet.getInt("soToa");
			LocalDate namSanXuat = resultSet.getDate("namSanXuat").toLocalDate();
			String nhaSanXuat = resultSet.getString("nhaSanXuat");
			float tocDoTB = resultSet.getFloat("tocDoTB");
			float tocDoToiDa = resultSet.getFloat("tocDoToiDa");
			TrangThaiTau trangThai = TrangThaiTau.fromInt(resultSet.getInt("trangThai"));
			String ghiChu = resultSet.getString("ghiChu");

			ChuyenTau chuyenTau = new ChuyenTau(resultSet.getString("maChuyenTau"));

			return new Tau(maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu,
					chuyenTau);
		}
		return null;
	}

	public List<Tau> layTTTauTheoTrangThai(TrangThaiTau trangThai) throws SQLException {
		String sql = "SELECT * FROM Tau WHERE trangThai = ?";
		List<Tau> tauList = new ArrayList<>();

		try (Connection con = Database.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, trangThai.getTrangThai());
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				String maTau = resultSet.getString("maTau");
				String tenTau = resultSet.getString("tenTau");
				int soToa = resultSet.getInt("soToa");
				LocalDate namSanXuat = resultSet.getDate("namSanXuat").toLocalDate();
				String nhaSanXuat = resultSet.getString("nhaSanXuat");
				float tocDoTB = resultSet.getFloat("tocDoTB");
				float tocDoToiDa = resultSet.getFloat("tocDoToiDa");
				String ghiChu = resultSet.getString("ghiChu");

				ChuyenTau chuyenTau = new ChuyenTau(resultSet.getString("maChuyenTau"));

				tauList.add(new Tau(maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai,
						ghiChu, chuyenTau));
			}
		}

		return tauList;
	}

}
