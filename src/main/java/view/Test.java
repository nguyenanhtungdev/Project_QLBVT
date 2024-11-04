package view;

import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import connectDB.ConnectDB;
import controller.HienThi_Controller;

public class Test {
	public static void main(String[] args) throws SQLException {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(new FlatLightLaf());
				Home view = new Home();
				HienThi_Controller controller = new HienThi_Controller(view);
				// Hiển thị view
				view.setVisible(true);
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
}
