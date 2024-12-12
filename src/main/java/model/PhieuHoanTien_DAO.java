package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import connectDB.Database;

public class PhieuHoanTien_DAO {
	private static PhieuHoanTien_DAO instance;
	
	public static PhieuHoanTien_DAO getInstance() {
		return instance == null ? instance = new PhieuHoanTien_DAO() : instance;
	}
	
	public boolean themPhieuHoanTien(PhieuHoanTien phieuHoanTien) {
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "Insert into PhieuHoanTien (maPhieuHoanTien, ngayHoanTien, lyDoHoanTra, ghiChu, tiLeHoanTien, maKH) values (?, ?, ?, ?, ?, ?)";
		try {
			Database.getInstance();
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, phieuHoanTien.getMaPhieuHoanTien());
			statement.setTimestamp(2,Timestamp.valueOf(phieuHoanTien.getNgayHoanTien()));
			statement.setString(3, phieuHoanTien.getLyDoHoanTra());
			statement.setString(4, phieuHoanTien.getGhiChu());
			statement.setFloat(5, phieuHoanTien.getTiLeHoanTien());
			statement.setString(6, phieuHoanTien.getKhachHang().getMaKhachHang());
			
			int x = statement.executeUpdate();
			return x > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public String getMaPhieuHoanTienMax() {
		String maPhieuHoanTienMax = null;
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "select max(maPhieuHoanTien) from PhieuHoanTien";
		ResultSet rs = null;
		try {
            Database.getInstance();
            con = Database.getInstance().getConnection();
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                maPhieuHoanTienMax = rs.getString(1);
            }
        } catch (SQLException e) {
		 e.printStackTrace();
	}
		return maPhieuHoanTienMax;
	}
	


public PhieuHoanTien layPhieuHoanTienBangMa(String maPhieuHoanTien) {
    String sql = "SELECT " +
                 "ph.maPhieuHoanTien, " +
                 "kh.hoTen, " +
                 "ph.ngayHoanTien, " +
                 "ph.lyDoHoanTra, " +
                 "ph.tiLeHoanTien, " +
                 "ph.ghiChu " +
                 "FROM PhieuHoanTien ph " +
                 "JOIN KhachHang kh ON ph.maKH = kh.maKH " +
                 "WHERE ph.maPhieuHoanTien = ?";

    Connection con = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    PhieuHoanTien phieuHoanTien = null;

    try {
        con = Database.getInstance().getConnection();
        statement = con.prepareStatement(sql);
        statement.setString(1, maPhieuHoanTien);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String maPhieu = resultSet.getString("maPhieuHoanTien");
            String hoTen = resultSet.getString("hoTen");
            LocalDateTime ngayHoanTien = resultSet.getTimestamp("ngayHoanTien").toLocalDateTime();
            String lyDoHoanTra = resultSet.getString("lyDoHoanTra");
            String ghiChu = resultSet.getString("ghiChu");
            float tiLeHoanTien = resultSet.getFloat("tiLeHoanTien");
            KhachHang khachHang = new KhachHang();
            khachHang.setHoTen(hoTen);

            phieuHoanTien = new PhieuHoanTien(maPhieu, ngayHoanTien, tiLeHoanTien, lyDoHoanTra, ghiChu, khachHang);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return phieuHoanTien;
}

	public String getMaPhieuHoanTienByMaHoaDon(String maHoaDon) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String maPhieuHoanTien = null;
	
	    try {
	        con = Database.getInstance().getConnection();
	        String sql = "SELECT DISTINCT pht.maPhieuHoanTien "
	                   + "FROM PhieuHoanTien pht "
	                   + "JOIN KhachHang kh ON pht.maKH = kh.maKH "
	                   + "JOIN VeTau vt ON vt.maKH = kh.maKH "
	                   + "JOIN ChiTiet_HoaDon cthd ON cthd.maVeTau = vt.maVeTau "
	                   + "JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon "
	                   + "WHERE hd.maHoaDon = ?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, maHoaDon);
	        rs = ps.executeQuery();
	
	        if (rs.next()) {
	            maPhieuHoanTien = rs.getString("maPhieuHoanTien");
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return maPhieuHoanTien;
	}


public String getMaHoaDonByMaPhieuHoanTien(String maPhieuHoanTien) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String maHoaDon = null;

    try {
        con = Database.getInstance().getConnection();
        String sql = "SELECT DISTINCT hd.maHoaDon "
                   + "FROM PhieuHoanTien pht "
                   + "JOIN KhachHang kh ON pht.maKH = kh.maKH "
                   + "JOIN VeTau vt ON vt.maKH = kh.maKH "
                   + "JOIN ChiTiet_HoaDon cthd ON cthd.maVeTau = vt.maVeTau "
                   + "JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon "
                   + "WHERE pht.maPhieuHoanTien = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, maPhieuHoanTien);
        rs = ps.executeQuery();

        if (rs.next()) {
            maHoaDon = rs.getString("maHoaDon");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return maHoaDon;
}

public String getTenKhachHangByMaPhieuHoanTien(String maPhieuHoanTien) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String tenKhachHang = null;

    try {
        con = Database.getInstance().getConnection();
        String sql = "SELECT kh.hoTen AS tenKhachHang "
                   + "FROM PhieuHoanTien pht "
                   + "JOIN KhachHang kh ON pht.maKH = kh.maKH "
                   + "WHERE pht.maPhieuHoanTien = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, maPhieuHoanTien);
        rs = ps.executeQuery();

        if (rs.next()) {
            tenKhachHang = rs.getString("tenKhachHang");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return tenKhachHang;
}


}
