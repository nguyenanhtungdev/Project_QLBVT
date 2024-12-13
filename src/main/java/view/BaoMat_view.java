package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import other.RoundedPanel;
import other.PrimaryButton;
import other.RoundPassField;
import java.awt.BorderLayout;

public class BaoMat_view extends JFrame {
	private static final long serialVersionUID = 1L;
	private RoundedPanel contentPane;
	private RoundPassField currentPasswordField;
	private RoundPassField newPasswordField;
	private RoundPassField confirmPasswordField;
	private PrimaryButton btnUpdatePassword;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			try {
				BaoMat_view frame = new BaoMat_view();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BaoMat_view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700); // Tăng kích thước khung
		setLocationRelativeTo(null);

		// Sử dụng BorderLayout cho contentPane để giữ tiêu đề phía trên và phần nhập
		// liệu ở giữa
		contentPane = new RoundedPanel(10);
		contentPane.setLayout(new BorderLayout(20, 20));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		// Tiêu đề
		JPanel panelTitle = new JPanel(new GridLayout(3, 1));
		panelTitle.setOpaque(false); // Làm trong suốt để khớp với RoundedPanel
		JLabel lblTitle = new JLabel("Tài khoản và bảo mật", JLabel.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		panelTitle.add(lblTitle);

		JLabel lblSubTitle = new JLabel("Thay đổi mật khẩu", JLabel.CENTER);
		lblSubTitle.setFont(new Font("Arial", Font.PLAIN, 26));
		panelTitle.add(lblSubTitle);

		JLabel lblNote = new JLabel(
				"<html>** Lưu ý: Mật khẩu gồm các chữ cái kèm theo số hoặc ký tự đặc biệt, tối thiểu 8 ký tự và không quá 32 ký tự.</html>",
				JLabel.CENTER);
		lblNote.setFont(new Font("Arial", Font.ITALIC, 18));
		panelTitle.add(lblNote);

		contentPane.add(panelTitle, BorderLayout.NORTH);

		// Các trường nhập mật khẩu ở giữa (CENTER) với BoxLayout theo chiều dọc
		JPanel panelFields = new JPanel();
		panelFields.setLayout(new BoxLayout(panelFields, BoxLayout.Y_AXIS));
		panelFields.setBorder(new EmptyBorder(20, 0, 20, 0));
		panelFields.setOpaque(false); // Làm trong suốt để khớp với RoundedPanel

		// Tạo ô nhập cho mật khẩu hiện tại
		panelFields.add(createPasswordFieldPanel("Mật khẩu hiện tại:", currentPasswordField = new RoundPassField(10)));
		panelFields.add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách giữa các ô nhập

		// Tạo ô nhập cho mật khẩu mới
		panelFields.add(createPasswordFieldPanel("Mật khẩu mới:", newPasswordField = new RoundPassField(10)));
		panelFields.add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách giữa các ô nhập

		// Tạo ô nhập cho xác nhận mật khẩu mới
		panelFields
				.add(createPasswordFieldPanel("Xác nhận mật khẩu mới:", confirmPasswordField = new RoundPassField(10)));

		// Thêm panelFields vào contentPane và căn giữa
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false); // Làm trong suốt để khớp với RoundedPanel
		centerPanel.add(panelFields);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		// Nút cập nhật phía dưới
		JPanel panelButton = new JPanel();
		btnUpdatePassword = new PrimaryButton("Cập nhật");
		btnUpdatePassword.setNormalColor(new Color(60, 130, 180));
		btnUpdatePassword.setFont(new Font("Arial", Font.BOLD, 20));
		btnUpdatePassword.setPreferredSize(new Dimension(220, 60)); // Tăng kích thước của nút
		btnUpdatePassword.setFocusPainted(false);
		panelButton.setOpaque(false); // Làm trong suốt để khớp với RoundedPanel
		panelButton.add(btnUpdatePassword);

		contentPane.add(panelButton, BorderLayout.SOUTH);
	}

	private JPanel createPasswordFieldPanel(String labelText, RoundPassField passwordField) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setOpaque(false); // Làm trong suốt để khớp với RoundedPanel

		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Arial", Font.PLAIN, 22)); // Tăng cỡ chữ cho nhãn
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);

		passwordField.setPreferredSize(new Dimension(700, 40)); // Tăng chiều rộng của ô nhập
		passwordField.setMaximumSize(new Dimension(1000, 40));
		passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(passwordField);

		return panel;
	}

	public JPanel getBaoMat_view() {
		return contentPane;
	}

	public String getCurrentPassword() {
		return new String(currentPasswordField.getPassword());
	}

	public String getNewPassword() {
		return new String(newPasswordField.getPassword());
	}

	public String getConfirmPassword() {
		return new String(confirmPasswordField.getPassword());
	}

	public JButton getBtnUpdatePassword() {
		return btnUpdatePassword;
	}
}
