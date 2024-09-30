package view;

import java.sql.Connection;
import java.sql.SQLException;

import connectDB.ConnectDB;
import controller.Menu_Controller;

public class DangNhap {
	private static Menu_Controller menu_Cotroller = new Menu_Controller();
	public static void main(String[] args) {
        try {
            // Gọi phương thức kết nối
            ConnectDB.getInstance().connect();
            
            // Lấy kết nối
            Connection conn = ConnectDB.getConnection();
            
            if (conn != null) {
                System.out.println("Kết nối thành công");
                print();
            } else {
                System.out.println("Kết nối thất bại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public static void print() {
		// TODO Auto-generated constructor stub
//		System.out.println(menu_Cotroller.getDataKhachHang());
//		System.out.println(menu_Cotroller.getGiamGia("KH1234567"));
	}
}
