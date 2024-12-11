package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import constant.ColorConstants;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Date;
import javax.swing.Timer;
import java.text.SimpleDateFormat;

public class HomeView extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private CardLayout cardLayout_Left, cardLayout_Right;
	private JLabel lbl_Icon;
	private ImageIcon icon;
	private JLabel lbl_TenNhanVien;
	private JLabel lbl_MaNhanVien;
	private JLabel lbl_ThoiGian;
	private JLabel lbl_BanVe;
	private JLabel lbl_QuanLy;
	private JLabel lbl_ThongKe;
	private JLabel lbl_TroGiup;
	private JLabel lbl_CaiDat;
	private JLabel lbl_TimKiemChuyenTau;
	private JLabel lbl_VeTau;
	private JPanel content_right;
	private JPanel content_left;
	private Panel demo;

	public JLabel getLbl_BanVe() {
		return lbl_BanVe;
	}

	public JLabel getLbl_TimKiemChuyenTau() {
		return lbl_TimKiemChuyenTau;
	}

	public JLabel getLbl_QuanLy() {
		return lbl_QuanLy;
	}

	public JLabel getLbl_VeTau() {
		return lbl_VeTau;
	}

	public JLabel getLbl_ThongKe() {
		return lbl_ThongKe;
	}

	public JLabel getLbl_TroGiup() {
		return lbl_TroGiup;
	}

	public JLabel getLbl_CaiDat() {
		return lbl_CaiDat;
	}

	// Get time
	private void getCurrentTime() {
		Timer timer = new Timer(1000, e -> {
			String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
			lbl_ThoiGian.setText(currentTime);
		});

		timer.start();
	}

	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Lấy kích thước màn hình
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setSize(screenSize.width, screenSize.height);

		// Mở toàn màn hình
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel header = new JPanel();
		header.setBackground(ColorConstants.PRIMARY_COLOR);
		header.setPreferredSize(new Dimension(0, 80)); // Chiều cao cố định
		header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90)); // Đặt chiều cao tối đa
		contentPane.add(header);
		header.setLayout(new BorderLayout(0, 0));

		JPanel header_Left = new JPanel();
		header_Left.setBackground(ColorConstants.PRIMARY_COLOR);
		header.add(header_Left, BorderLayout.WEST);

		JPanel header_Right = new JPanel();
		header_Right.setBorder(new EmptyBorder(5, 20, 5, 20));
		header_Right.setBackground(ColorConstants.PRIMARY_COLOR);
		header.add(header_Right, BorderLayout.EAST);
		header_Right.setLayout(new BoxLayout(header_Right, BoxLayout.X_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(ColorConstants.PRIMARY_COLOR);
		header_Right.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lbl_Logo_User = new JLabel("");
		lbl_Logo_User.setBorder(new EmptyBorder(0, 10, 0, 10));
		icon = new ImageIcon(getClass().getResource("/Image/user.png"));
		lbl_Logo_User.setIcon(new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		panel_1.add(lbl_Logo_User);

		JPanel panel_2 = new JPanel();
		header_Right.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(ColorConstants.PRIMARY_COLOR);
		panel_2.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 3));

		lbl_TenNhanVien = new JLabel("Nguyễn Anh Tùng");
		lbl_TenNhanVien.setForeground(ColorConstants.TEXT_COLOR);
		lbl_TenNhanVien.setFont(new Font("Arial", Font.BOLD, 17));
		panel_3.add(lbl_TenNhanVien);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(ColorConstants.PRIMARY_COLOR);
		panel_2.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));

		lbl_MaNhanVien = new JLabel("NV00001");
		lbl_MaNhanVien.setForeground(ColorConstants.TEXT_COLOR);
		lbl_MaNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_MaNhanVien.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_4.add(lbl_MaNhanVien);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(ColorConstants.PRIMARY_COLOR);
		panel_2.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));

		lbl_ThoiGian = new JLabel("Loading...");
		lbl_ThoiGian.setForeground(ColorConstants.TEXT_COLOR);
		lbl_ThoiGian.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_5.add(lbl_ThoiGian);

		JPanel header_Center = new JPanel();
		header_Center.setBackground(ColorConstants.PRIMARY_COLOR);
		header_Center.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		header.add(header_Center, BorderLayout.CENTER);

		lbl_Icon = new JLabel("");
		icon = new ImageIcon(getClass().getResource("/Image/logo.png"));
		lbl_Icon.setIcon(new ImageIcon(icon.getImage().getScaledInstance(270, 80, Image.SCALE_SMOOTH)));
		header_Left.add(lbl_Icon);

		lbl_BanVe = new JLabel("Bán vé");
		lbl_BanVe.addMouseListener(this);

		lbl_BanVe.setBackground(ColorConstants.PRIMARY_COLOR);
		lbl_BanVe.setOpaque(true);
		lbl_BanVe.setBorder(new EmptyBorder(5, 10, 5, 10));
		lbl_BanVe.setForeground(Color.WHITE);
		icon = new ImageIcon(getClass().getResource("/Image/tabler-icon-ticket.png"));
		lbl_BanVe.setIcon(new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		lbl_BanVe.setFont(new Font("Arial", Font.BOLD, 22));
		header_Center.add(lbl_BanVe);

		lbl_QuanLy = new JLabel("Quản lý");
		lbl_QuanLy.setBorder(new EmptyBorder(5, 10, 5, 10));
		lbl_QuanLy.setForeground(Color.WHITE);
		lbl_QuanLy.setOpaque(true);
		icon = new ImageIcon(getClass().getResource("/Image/tabler-icon-file-settings.png"));
		lbl_QuanLy.setIcon(new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		lbl_QuanLy.setFont(new Font("Arial", Font.BOLD, 22));
		lbl_QuanLy.setBackground(ColorConstants.PRIMARY_COLOR);
		lbl_QuanLy.addMouseListener(this);
		header_Center.add(lbl_QuanLy);

		lbl_ThongKe = new JLabel("Thống kê");
		lbl_ThongKe.setForeground(Color.WHITE);
		lbl_ThongKe.addMouseListener(this);
		icon = new ImageIcon(getClass().getResource("/Image/tabler-icon-report-analytics.png"));
		lbl_ThongKe.setIcon(new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		lbl_ThongKe.setFont(new Font("Arial", Font.BOLD, 22));
		lbl_ThongKe.setBorder(new EmptyBorder(5, 10, 5, 10));
		lbl_ThongKe.setBackground(ColorConstants.PRIMARY_COLOR);
		lbl_ThongKe.setOpaque(true);
		lbl_ThongKe.addMouseListener(this);
		header_Center.add(lbl_ThongKe);

		lbl_TroGiup = new JLabel("Trợ giúp");
		lbl_TroGiup.setForeground(Color.WHITE);
		icon = new ImageIcon(getClass().getResource("/Image/tabler-icon-help.png"));
		lbl_TroGiup.setIcon(new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		lbl_TroGiup.setFont(new Font("Arial", Font.BOLD, 22));
		lbl_TroGiup.setBorder(new EmptyBorder(5, 10, 5, 10));
		lbl_TroGiup.setBackground(ColorConstants.PRIMARY_COLOR);
		lbl_TroGiup.setOpaque(true);
		lbl_TroGiup.addMouseListener(this);
		header_Center.add(lbl_TroGiup);

		lbl_CaiDat = new JLabel("Cài đặt");
		lbl_CaiDat.setForeground(Color.WHITE);
		icon = new ImageIcon(getClass().getResource("/Image/tabler-icon-settings.png"));
		lbl_CaiDat.setIcon(new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		lbl_CaiDat.setFont(new Font("Arial", Font.BOLD, 22));
		lbl_CaiDat.setBorder(new EmptyBorder(5, 10, 5, 10));
		lbl_CaiDat.setBackground(ColorConstants.PRIMARY_COLOR);
		lbl_CaiDat.setOpaque(true);
		lbl_CaiDat.addMouseListener(this);
		header_Center.add(lbl_CaiDat);

		JPanel content = new JPanel();
		content.setBackground(Color.BLUE);
		contentPane.add(content);
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));

		content_left = new JPanel();
		content_left.setBackground(ColorConstants.PRIMARY_COLOR);
		// Đặt chiều rộng cố định cho content_left
		content_left.setPreferredSize(new Dimension(220, 0));
		content_left.setMaximumSize(new Dimension(220, Integer.MAX_VALUE));
		content_left.setMinimumSize(new Dimension(220, 0));
		content_left.setLayout(new BorderLayout());
		cardLayout_Left = new CardLayout();
		demo = new Panel(cardLayout_Left);
		content_left.add(demo);
		content.add(content_left);

		// Sử dụng CardLayout cho panel nội dung
		cardLayout_Right = new CardLayout();
		content_right = new JPanel(cardLayout_Right);
		content_right.setBackground(Color.YELLOW);
		content_right.setPreferredSize(new Dimension(0, 0));
		content.add(content_right);

		// Goi ham
		getCurrentTime();
	}

	public void addLeft_Menu(String name, JPanel panel) {
		demo.add(panel, name);
	}

	public void showLeft_Menu(String name) {
		cardLayout_Left.show(demo, name);
	}

	public void addView(String name, JPanel panel) {
		content_right.add(panel, name);
	}

	public void showView(String name) {
		cardLayout_Right.show(content_right, name);
	}

	public void addCustomerMenuListener(MouseListener listener) {
		lbl_BanVe.addMouseListener(listener);
		lbl_QuanLy.addMouseListener(listener);
		lbl_ThongKe.addMouseListener(listener);
		lbl_TroGiup.addMouseListener(listener);
		lbl_CaiDat.addMouseListener(listener);
	}

	// Sự kiện Item menu
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JLabel jLabel) {
			jLabel.setBackground(ColorConstants.HOVER_COLOR);
			jLabel.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JLabel jLabel) {
			jLabel.setBackground(ColorConstants.PRIMARY_COLOR);
			jLabel.repaint();
		}
	}

}
