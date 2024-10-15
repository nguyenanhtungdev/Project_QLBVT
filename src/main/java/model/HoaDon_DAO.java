<<<<<<< HEAD
//package model;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//import connectDB.ConnectDB;
//
//public class HoaDon_DAO {
//	public ArrayList<HoaDon> getalltbHD() { 
//        ArrayList<KhachHang> khachHangs = new ArrayList<>();
//        Connection con = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            ConnectDB.getInstance();
//            con = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT * FROM HoaDon";
//            statement = con.createStatement();
//            resultSet = statement.executeQuery(sql);
//            
//            while (resultSet.next()) {
//                String maHoaDon = resultSet.getString(1);
//                Timestamp timestamp = resultSet.getTimestamp(2);
//                LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();
//                String ghiChu = resultSet.getString(3);
//                float thueVAT = resultSet.getFloat(4);
//                String phuongThucThanhToan = resultSet.getString(5);
//                boolean gioiTinh = resultSet.getBoolean(6);
//                String CCCD = resultSet.getString(7);
//                LocalDate ngaySinh = resultSet.getDate(7).toLocalDate();
//                String loaiKHStr = resultSet.getString(8);
//                
//                KhachHang.LoaiKhachHang loaiKH = KhachHang.LoaiKhachHang.valueOf(loaiKHStr);           
//
//                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKH);
//                khachHangs.add(khachHang);
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (resultSet != null) resultSet.close();
//                if (statement != null) statement.close();
//                if (con != null) con.close();  // Đóng kết nối sau khi sử dụng
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return khachHangs;
//    }
//}
=======
<<<<<<< HEAD
//package model;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//import connectDB.ConnectDB;
//
//public class HoaDon_DAO {
//	public ArrayList<HoaDon> getalltbHD() { 
//        ArrayList<KhachHang> khachHangs = new ArrayList<>();
//        Connection con = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            ConnectDB.getInstance();
//            con = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT * FROM HoaDon";
//            statement = con.createStatement();
//            resultSet = statement.executeQuery(sql);
//            
//            while (resultSet.next()) {
//                String maHoaDon = resultSet.getString(1);
//                Timestamp timestamp = resultSet.getTimestamp(2);
//                LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();
//                String ghiChu = resultSet.getString(3);
//                float thueVAT = resultSet.getFloat(4);
//                String phuongThucThanhToan = resultSet.getString(5);
//                boolean gioiTinh = resultSet.getBoolean(6);
//                String CCCD = resultSet.getString(7);
//                LocalDate ngaySinh = resultSet.getDate(7).toLocalDate();
//                String loaiKHStr = resultSet.getString(8);
//                
//                KhachHang.LoaiKhachHang loaiKH = KhachHang.LoaiKhachHang.valueOf(loaiKHStr);           
//
//                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKH);
//                khachHangs.add(khachHang);
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (resultSet != null) resultSet.close();
//                if (statement != null) statement.close();
//                if (con != null) con.close();  // Đóng kết nối sau khi sử dụng
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return khachHangs;
//    }
//}
=======
//package model;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//import connectDB.ConnectDB;
//
//public class HoaDon_DAO {
//	public ArrayList<HoaDon> getalltbHD() { 
//        ArrayList<KhachHang> khachHangs = new ArrayList<>();
//        Connection con = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            ConnectDB.getInstance();
//            con = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT * FROM HoaDon";
//            statement = con.createStatement();
//            resultSet = statement.executeQuery(sql);
//            
//            while (resultSet.next()) {
//                String maHoaDon = resultSet.getString(1);
//                Timestamp timestamp = resultSet.getTimestamp(2);
//                LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();
//                String ghiChu = resultSet.getString(3);
//                float thueVAT = resultSet.getFloat(4);
//                String phuongThucThanhToan = resultSet.getString(5);
//                boolean gioiTinh = resultSet.getBoolean(6);
//                String CCCD = resultSet.getString(7);
//                LocalDate ngaySinh = resultSet.getDate(7).toLocalDate();
//                String loaiKHStr = resultSet.getString(8);
//                
//                KhachHang.LoaiKhachHang loaiKH = KhachHang.LoaiKhachHang.valueOf(loaiKHStr);           
//
//                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKH);
//                khachHangs.add(khachHang);
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (resultSet != null) resultSet.close();
//                if (statement != null) statement.close();
//                if (con != null) con.close();  // Đóng kết nối sau khi sử dụng
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return khachHangs;
//    }
//}
>>>>>>> ef8d2f0 (update HoaDon)
>>>>>>> 507ce983e646719aee9e6fdc8d22dbc7436ca473
