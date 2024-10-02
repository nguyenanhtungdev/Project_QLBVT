package view;

import java.sql.SQLException;

import connectDB.ConnectDB;
import controller.Menu_Controller;

public class Demo {
	private static Menu_Controller menu_Controller = new Menu_Controller();
	
	public static void main(String[] args) {
		//Khởi tạo kết nối đến sql
				try {
					System.out.println("Password: " + System.getenv("MSSQL_PASSWORD"));
					ConnectDB.getInstance().getConnection();
					System.out.println("Connected!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		System.out.println(menu_Controller.getDataKhachHang());
	}
}
