package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import other.PrimaryButton;

public class ThongTinVe extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	private PrimaryButton hoanVeButton, traVeButton, inPDFButton;
	private JLabel maHoaDonLabel, tenKhachHangLabel, soDienThoaiLabel, soTienLabel, soTienTraLabel;

	public ThongTinVe() {
		setTitle("Thông tin vé tàu");
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);

		// Label hiển thị thông tin vé
		JLabel thongTinVeLabel = new JLabel("Thông tin vé tàu", JLabel.CENTER);
		thongTinVeLabel.setFont(new Font("Arial", Font.BOLD, 24));
		thongTinVeLabel.setForeground(new Color(70, 130, 180)); // Màu tiêu đề
		mainPanel.add(thongTinVeLabel, BorderLayout.NORTH);

		// Panel thông tin khách hàng
		JPanel thongTinPanel = new JPanel();
		thongTinPanel.setLayout(new BorderLayout());
		thongTinPanel.setBackground(Color.WHITE);

		// Thông tin chi tiết với GridLayout để căn chỉnh dễ đọc
		JPanel chiTietPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
		chiTietPanel.setBackground(Color.WHITE);

		maHoaDonLabel = new JLabel("Mã hóa đơn: ");
		tenKhachHangLabel = new JLabel("Tên khách hàng: ");
		soDienThoaiLabel = new JLabel("Số điện thoại: ");
		soTienLabel = new JLabel("Số tiền thanh toán: ");
		soTienTraLabel = new JLabel("Số tiền trả: ");

		// Đặt font chữ và căn chỉnh
		maHoaDonLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		tenKhachHangLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		soDienThoaiLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		soTienLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		soTienTraLabel.setFont(new Font("Arial", Font.PLAIN, 18));

		// Thêm các nhãn vào panel chi tiết
		chiTietPanel.add(maHoaDonLabel);
		chiTietPanel.add(tenKhachHangLabel);
		chiTietPanel.add(soDienThoaiLabel);
		chiTietPanel.add(soTienLabel);
		chiTietPanel.add(soTienTraLabel);

		thongTinPanel.add(chiTietPanel, BorderLayout.CENTER);
		mainPanel.add(thongTinPanel, BorderLayout.CENTER);

		// Phần chứa các nút
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		buttonPanel.setBackground(Color.WHITE);

		// Nút Hoàn vé màu xanh lá cây
		hoanVeButton = new PrimaryButton("Hoàn vé");
		hoanVeButton.setBackground(new Color(34, 139, 34));
		hoanVeButton.setForeground(Color.WHITE);
		hoanVeButton.setFont(new Font("Arial", Font.BOLD, 14));
		hoanVeButton.setPreferredSize(new Dimension(150, 50)); // Tăng kích thước nút

		// Nút Trả vé màu đỏ
		traVeButton = new PrimaryButton("Trả vé");
		traVeButton.setBackground(new Color(220, 20, 60));
		traVeButton.setForeground(Color.WHITE);
		traVeButton.setFont(new Font("Arial", Font.BOLD, 14));
		traVeButton.setPreferredSize(new Dimension(150, 50)); // Tăng kích thước nút

		// Nút In PDF màu xanh dương
		inPDFButton = new PrimaryButton("In PDF");
		inPDFButton.setBackground(new Color(0, 123, 255));
		inPDFButton.setForeground(Color.WHITE);
		inPDFButton.setFont(new Font("Arial", Font.BOLD, 14));
		inPDFButton.setPreferredSize(new Dimension(150, 50)); // Tăng kích thước nút

		// Thêm các nút vào panel nút
		buttonPanel.add(hoanVeButton);
		buttonPanel.add(traVeButton);
		buttonPanel.add(inPDFButton);

		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	public PrimaryButton getHoanVeButton() {
		return hoanVeButton;
	}

	public PrimaryButton getTraVeButton() {
		return traVeButton;
	}

	public PrimaryButton getInPDFButton() {
		return inPDFButton;
	}

	// Các phương thức để đặt thông tin khách hàng lên giao diện
	public void setMaHoaDon(String maHoaDon) {
		maHoaDonLabel.setText("Mã hóa đơn: " + maHoaDon);
	}

	public void setTenKhachHang(String tenKhachHang) {
		tenKhachHangLabel.setText("Tên khách hàng: " + tenKhachHang);
	}

	public void setSoDienThoai(String soDienThoai) {
		soDienThoaiLabel.setText("Số điện thoại: " + soDienThoai);
	}

	public void setSoTien(String soTien) {
		soTienLabel.setText("Số tiền thanh toán: " + soTien);
	}

	public void setSoTienTra(String soTienTra) {
		soTienTraLabel.setText("Số tiền trả: " + soTienTra);
	}
}
