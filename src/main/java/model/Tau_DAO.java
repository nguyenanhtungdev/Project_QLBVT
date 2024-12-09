package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connectDB.Database;
import model.Tau.TrangThaiTau;

public class Tau_DAO {

	private static Tau_DAO instance;

	public static Tau_DAO getInstance() {
		return instance == null ? instance = new Tau_DAO() : instance;
	}

	public List<Tau> getAll() {
		String sql = "SELECT * FROM Tau";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public Tau getByMaTau(String maTau) {
		String sql = "SELECT * FROM Tau WHERE maTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maTau);
			resultSet = statement.executeQuery();

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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean add(Tau tau) {
		String sql = "INSERT INTO Tau (maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, tau.getMaTau());
			statement.setString(2, tau.getTenTau());
			statement.setInt(3, tau.getSoToa());
			statement.setDate(4, Date.valueOf(tau.getNamSanXuat()));
			statement.setString(5, tau.getNhaSanXuat());
			statement.setFloat(6, tau.getTocDoTB());
			statement.setFloat(7, tau.getTocDoToiDa());
			statement.setString(8, tau.getTrangThai().name());
			statement.setString(9, tau.getGhiChu());
			int count = statement.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean update(Tau tau) {
		String sql = "UPDATE Tau SET tenTau = ?, soToa = ?, namSanXuat = ?, nhaSanXuat = ?, tocDoTB = ?, tocDoToiDa = ?, trangThai = ?, ghiChu = ? WHERE maTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);

			statement.setString(1, tau.getTenTau());
			statement.setInt(2, tau.getSoToa());
			statement.setDate(3, Date.valueOf(tau.getNamSanXuat()));
			statement.setString(4, tau.getNhaSanXuat());
			statement.setFloat(5, tau.getTocDoTB());
			statement.setFloat(6, tau.getTocDoToiDa());
			statement.setString(7, tau.getTrangThai().name());
			statement.setString(8, tau.getGhiChu());
			statement.setString(9, tau.getMaTau());

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	// Cập nhập trạng thái tàu
	public boolean capNhapTTTau(String maTau, TrangThaiTau trangThaiMoi) {
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
	public Tau layTTTauTheoMa(String maTau) {
		String sql = "SELECT * FROM Tau WHERE maTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maTau);
			resultSet = statement.executeQuery();

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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public List<Tau> layTTTauTheoTrangThai(TrangThaiTau trangThai) {
		String sql = "SELECT * FROM Tau WHERE trangThai = ?";

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, trangThai.getTrangThai());
			resultSet = stmt.executeQuery();

			List<Tau> list = new ArrayList<>();
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

				list.add(new Tau(maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu,
						chuyenTau));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public Tau getTauByMaChuyenTau(String maChuyenTau) {
		String sql = "SELECT * FROM Tau WHERE maChuyenTau = ?";

		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = Database.getInstance().getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maChuyenTau);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String maTau = resultSet.getString("maTau");
				String tenTau = resultSet.getString("tenTau");
				int soToa = resultSet.getInt("soToa");
				LocalDate namSanXuat = resultSet.getDate("namSanXuat").toLocalDate();
				String nhaSanXuat = resultSet.getString("nhaSanXuat");
				float tocDoTB = resultSet.getFloat("tocDoTB");
				float tocDoToiDa = resultSet.getFloat("tocDoToiDa");
				TrangThaiTau trangThai = TrangThaiTau.fromInt(resultSet.getInt("trangThai"));
				String ghiChu = resultSet.getString("ghiChu");

				// Khởi tạo ChuyenTau từ maChuyenTau
				ChuyenTau chuyenTau = new ChuyenTau(maChuyenTau);

				// Tạo và trả về đối tượng Tau
				return new Tau(maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu,
						chuyenTau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public Map<String, Integer> layThongTinGhe(String maTau) {
		String sql = """
				SELECT
				    COUNT(ghe.maGheTau) AS tongSoGhe,
				    SUM(CASE WHEN ghe.trangThai = 'TRONG' THEN 1 ELSE 0 END) AS soGheConLai
				FROM
				    Tau tau
				JOIN
				    ToaTau toa ON tau.maTau = toa.maTau
				JOIN
				    GheTau ghe ON toa.maToaTau = ghe.maToaTau
				WHERE
				    tau.maTau = ?
				""";

		try {
			Map<String, Integer> result = new HashMap<>();
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maTau);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result.put("tongSoGhe", rs.getInt("tongSoGhe"));
				result.put("soGheConLai", rs.getInt("soGheConLai"));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}
}
