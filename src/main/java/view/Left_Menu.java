package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import constant.ColorConstants;
import controller.ThongKe_Controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Left_Menu extends JFrame {

	private static final long serialVersionUID = 6285786604097345096L;

	private JPanel contentPane;
	private ArrayList<View> danhSachPage;
	private HomeView home;

	public Left_Menu(ArrayList<View> danhSachPage, HomeView home) {
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

		taoMenuTuDanhSach();
	}

	private void taoMenuTuDanhSach() {
		for (View page : danhSachPage) {
			JLabel label = taoLabelChoPage(page);
			contentPane.add(label);

			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (page.getName().equals("Đăng xuất")) {
						int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất không?",
								"Xác nhận", JOptionPane.YES_NO_OPTION);
						if (confirm == JOptionPane.YES_OPTION) {
							home.setVisible(false);
						}
					}
					home.showView(page.getName());

					// Tải lại dữ liệu khi chuyển sang view khác
					if (page.getName().equals("Tổng quan")) {
						ThongKe_Controller.getInstance().refreshData();
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
	private JLabel taoLabelChoPage(View page) {
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
