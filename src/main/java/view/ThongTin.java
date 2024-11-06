package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import other.RoundedPanel;
import other.PrimaryButton;

public class ThongTin extends JFrame {
	private static final long serialVersionUID = 1L;
	private RoundedPanel mainPanel;
	private JTextField hoTenField, gioiTinhField, ngaySinhField, emailField, dienThoaiField, diaChiField, cccdField;
	private PrimaryButton capNhatButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ThongTin frame = new ThongTin();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ThongTin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 750);
		setLocationRelativeTo(null);

		mainPanel = new RoundedPanel(5); // Sử dụng constructor mặc định
		mainPanel.setLayout(new BorderLayout(30, 30)); // Đặt layout sau khi khởi tạo

		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		setContentPane(mainPanel);

		// Tiêu đề ở phần NORTH
		JLabel titleLabel = new JLabel("THÔNG TIN CÁ NHÂN");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Panel chứa thông tin cá nhân ở phần CENTER
		JPanel infoPanel = new JPanel(new GridBagLayout());
		infoPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Họ và tên
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel hoTenLabel = new JLabel("Họ và tên:");
		hoTenLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		infoPanel.add(hoTenLabel, gbc);
		gbc.gridx = 1;
		hoTenField = createTextField();
		infoPanel.add(hoTenField, gbc);

		// Giới tính
		gbc.gridx = 2;
		JLabel gioiTinhLabel = new JLabel("Giới tính:");
		gioiTinhLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		infoPanel.add(gioiTinhLabel, gbc);
		gbc.gridx = 3;
		gioiTinhField = createTextField();
		infoPanel.add(gioiTinhField, gbc);

		// Ngày sinh
		gbc.gridx = 0;
		gbc.gridy = 1;
		JLabel ngaySinhLabel = new JLabel("Ngày sinh:");
		ngaySinhLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		infoPanel.add(ngaySinhLabel, gbc);
		gbc.gridx = 1;
		ngaySinhField = createTextField();
		infoPanel.add(ngaySinhField, gbc);

		// Email
		gbc.gridx = 2;
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		infoPanel.add(emailLabel, gbc);
		gbc.gridx = 3;
		emailField = createTextField();
		infoPanel.add(emailField, gbc);

		// Điện thoại
		gbc.gridx = 0;
		gbc.gridy = 2;
		JLabel dienThoaiLabel = new JLabel("Điện thoại:");
		dienThoaiLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		infoPanel.add(dienThoaiLabel, gbc);
		gbc.gridx = 1;
		dienThoaiField = createTextField();
		infoPanel.add(dienThoaiField, gbc);

		// Địa chỉ
		gbc.gridx = 2;
		JLabel diaChiLabel = new JLabel("Địa chỉ:");
		diaChiLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		infoPanel.add(diaChiLabel, gbc);
		gbc.gridx = 3;
		diaChiField = createTextField();
		infoPanel.add(diaChiField, gbc);

		// CCCD
		gbc.gridx = 0;
		gbc.gridy = 3;
		JLabel cccdLabel = new JLabel("CCCD:");
		cccdLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		infoPanel.add(cccdLabel, gbc);
		gbc.gridx = 1;
		cccdField = createTextField();
		infoPanel.add(cccdField, gbc);

		// Chức vụ
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		JLabel chucVuLabel = new JLabel("Chức vụ: Nhân viên");
		chucVuLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		infoPanel.add(chucVuLabel, gbc);

		mainPanel.add(infoPanel, BorderLayout.CENTER);

		// Nút cập nhật thông tin ở phần SOUTH
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// Tăng kích thước của nút Cập nhật thông tin
		capNhatButton = new PrimaryButton("Cập nhật thông tin");
		capNhatButton.setPreferredSize(new Dimension(250, 50)); // Thiết lập kích thước ưa thích cho nút
		capNhatButton.setFont(new Font("Arial", Font.BOLD, 20));
		capNhatButton.setBackground(new Color(70, 130, 180));
		capNhatButton.setForeground(Color.WHITE);
		capNhatButton.setFocusPainted(false);
		capNhatButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Thêm khoảng trống bên trong

		buttonPanel.add(capNhatButton);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	private JTextField createTextField() {
		JTextField textField = new JTextField(20);
		textField.setPreferredSize(new Dimension(300, 40));
		textField.setEditable(false);
		textField.setEnabled(false);
		return textField;
	}

	public JPanel getThongTin_view() {
		return mainPanel;
	}

	public void addThongTinViewListener(ActionListener listener) {
		capNhatButton.addActionListener(listener);
	}

	public JButton getCapNhatButton() {
		return capNhatButton;
	}

	public void setHoTen(String hoTen) {
		hoTenField.setText(hoTen);
	}

	public void setGioiTinh(String gioiTinh) {
		gioiTinhField.setText(gioiTinh);
	}

	public void setNgaySinh(String ngaySinh) {
		ngaySinhField.setText(ngaySinh);
	}

	public void setEmail(String email) {
		emailField.setText(email);
	}

	public void setDienThoai(String dienThoai) {
		dienThoaiField.setText(dienThoai);
	}

	public void setDiaChi(String diaChi) {
		diaChiField.setText(diaChi);
	}

	public void setCCCD(String cccd) {
		cccdField.setText(cccd);
	}
}
