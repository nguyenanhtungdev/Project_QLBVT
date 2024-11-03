package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import other.ColorConstants;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Left_Menu extends JFrame {
	private JPanel contentPane;
	private ArrayList<Page> danhSachPage;
	private Home home;

	public Left_Menu(ArrayList<Page> danhSachPage, Home home) {
		this.danhSachPage = danhSachPage;
		this.home = home;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 210, 843);
		contentPane = new JPanel();
		contentPane.setBackground(ColorConstants.PRIMARY_COLOR);
		contentPane.setBorder(new EmptyBorder(0, 5, 0, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setLocationRelativeTo(null);
		setContentPane(contentPane);

		taoMenuTuDanhSach(); // Tạo các item menu từ danh sách Page
	}

	// Phương thức tạo menu từ danh sách Page
	private void taoMenuTuDanhSach() {
		for (Page page : danhSachPage) {
			JLabel label = taoLabelChoPage(page);
			contentPane.add(label);

			// Thêm sự kiện cho label
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (page.getName().equals("Tìm kiếm")) {
						home.showPanel("Tìm kiếm");
					} else if (page.getName().equals("Vé tàu")) {
						home.showPanel("Vé tàu");
					} else if (page.getName().equals("Hóa đơn")) {
						home.showPanel("Quản lý hóa đơn");
					} else if (page.getName().equals("Tàu")) {
						home.showPanel("Quản lý Tàu");
					} else if (page.getName().equals("Khuyến mãi")) {
						home.showPanel("Quản lý Khuyến Mãi");
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					label.setBackground(ColorConstants.HOVER_COLOR);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					label.setBackground(ColorConstants.PRIMARY_COLOR);
				}
			});
		}
	}

	// Phương thức tạo JLabel cho từng Page
	private JLabel taoLabelChoPage(Page page) {
		JLabel label = new JLabel(page.getName());
		label.setForeground(Color.WHITE);
		ImageIcon icon = new ImageIcon(getClass().getResource(page.getImagePath()));
		label.setIcon(new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		label.setFont(new Font("Arial", Font.BOLD, 22));
		label.setBorder(new EmptyBorder(5, 15, 5, 15));
		label.setBackground(ColorConstants.PRIMARY_COLOR);
		label.setOpaque(true);
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		label.setIconTextGap(10);
		return label;
	}

	public JPanel getLeft_Menu() {
		return contentPane;
	}
}
