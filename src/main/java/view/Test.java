package view;

import java.sql.SQLException;

import connectDB.ConnectDB;
import controller.BanVe_Controller;

public class Test {
    public static void main(String[] args) {
        // Tạo model, view và controller
        Home view = new Home();
        BanVe_Controller controller = new BanVe_Controller(view); 

        // Hiển thị view
        view.setVisible(true);
    }
}
