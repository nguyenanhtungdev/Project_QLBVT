package view;

import java.sql.SQLException;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

import controller.HienThi_Controller;

public class Test {
	public static void main(String[] args) throws SQLException {
		SwingUtilities.invokeLater(() -> {
			FlatLightLaf.setup();

			HienThi_Controller controller = new HienThi_Controller();
			controller.showView();
		});

	}
}
