package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;

public class ThanhToan_View extends View {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ThanhToan_View(String name, String imagePath) {
		super(name, imagePath);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 830);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JPanel panel_Left = new JPanel();
		panel_Left.setBackground(Color.WHITE);
		contentPane.add(panel_Left);

		JPanel panel_Right = new JPanel();
		panel_Right.setPreferredSize(new Dimension(430, 0)); // Chiều cao cố định
		panel_Right.setMaximumSize(new Dimension(430, Integer.MAX_VALUE)); // Đặt chiều cao tối đa
		panel_Right.setBackground(Color.PINK);
		contentPane.add(panel_Right);
	}

	public JPanel getThanhToan_View() {
		return contentPane;
	}
}
