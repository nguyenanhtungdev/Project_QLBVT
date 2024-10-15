package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class QLHoaDon_view extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLHoaDon_view frame = new QLHoaDon_view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public QLHoaDon_view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		
		lblNewLabel = new JLabel("Đây là trang quản lý hóa đơn");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
	}
	public JPanel getQLHoaDon_View() {
		return contentPane;
	}
}
