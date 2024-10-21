package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import other.ColorConstants;
import other.RoundButton;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class TimKiem_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private RoundButton btn_DangNhap;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiem_View frame = new TimKiem_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TimKiem_View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Đây là trang tìm kiếm chuyến tàu");
		lblNewLabel.setBounds(435, 10, 280, 22);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
		
		btn_DangNhap = new RoundButton("Đăng Nhập", ColorConstants.PRIMARY_COLOR, ColorConstants.HOVER_COLOR);
		btn_DangNhap.setBounds(605, 126, 132, 37);
		btn_DangNhap.setHeSoBoGoc(10);
		contentPane.add(btn_DangNhap);
	}
	public JPanel getTimKiem_View() {
		return contentPane;
	}
}
