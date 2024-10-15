package view;

import java.sql.SQLException;

import connectDB.ConnectDB;
import controller.Menu_Controller;

public class Demo {
<<<<<<< HEAD
    public static void main(String[] args) {
        // Tạo model, view và controller
        Home view = new Home();
        Menu_Controller controller = new Menu_Controller(view); 

        // Hiển thị view
        view.setVisible(true);
    }
}
=======
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
>>>>>>> 507ce983e646719aee9e6fdc8d22dbc7436ca473
