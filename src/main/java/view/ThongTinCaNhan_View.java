package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import other.RoundedPanel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Insets;

import other.PrimaryButton;
import javax.swing.ImageIcon;

import com.toedter.calendar.JDateChooser;

import constant.ColorConstants;

import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ThongTinCaNhan_View extends View {
	private RoundedPanel mainPanel;
	private ImageIcon iconlich;
	private PrimaryButton btnCapNhat;
	private JTextField txt_MaNhanVien;
	private JTextField txt_NgaySinh;
	private JTextField txt_Email;
	private JTextField txt_CCCD;
	private JTextField txt_HoTen;
	private JTextField txt_SDT;
	private JTextField txt_ChucVu;
	private PrimaryButton btnHoanThanh;
	private JComboBox comboBox_GioiTinh;
	private PrimaryButton btnHuyBo;

	public void addSuKienCD(ActionListener actionListener, FocusListener focusListener, KeyListener keyListener) {
		btnCapNhat.addActionListener(actionListener);
		btnHoanThanh.addActionListener(actionListener);
		btnHuyBo.addActionListener(actionListener);
		txt_NgaySinh.addFocusListener(focusListener);
		txt_NgaySinh.addKeyListener(keyListener);
	}

	public ThongTinCaNhan_View(String name, String imagePath) {
		super(name, imagePath);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);

		mainPanel = new RoundedPanel(15);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(null);
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel head = new JPanel();
		head.setBorder(new EmptyBorder(30, 0, 0, 0));
		head.setBackground(Color.WHITE);
		head.setPreferredSize(new Dimension(getWidth(), 150));
		mainPanel.add(head, BorderLayout.NORTH);
		head.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

		JLabel lblTTCN = new JLabel("THÔNG TIN CÁ NHÂN");
		lblTTCN.setForeground(ColorConstants.PRIMARY_COLOR);
		lblTTCN.setFont(new Font("Arial", Font.BOLD, 51));
		head.add(lblTTCN);

		JPanel body = new JPanel();
		body.setBorder(new EmptyBorder(0, 100, 0, 100));
		body.setBackground(Color.WHITE);
		mainPanel.add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		body.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		Component horizontalStrut = Box.createHorizontalStrut(40);
		panel.add(horizontalStrut);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		Component horizontalStrut1 = Box.createVerticalStrut(10);
		panel_2.add(horizontalStrut1);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_3.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setForeground(ColorConstants.PRIMARY_COLOR);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		panel_11.add(lblNewLabel);

		txt_MaNhanVien = new JTextField();
		txt_MaNhanVien.setEnabled(false);
		txt_MaNhanVien.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_3.add(txt_MaNhanVien);
		txt_MaNhanVien.setColumns(18);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		JPanel panel_11_1 = new JPanel();
		panel_11_1.setBackground(Color.WHITE);
		panel_4.add(panel_11_1);
		panel_11_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("Ngày sinh     ");
		lblNewLabel_1.setForeground(new Color(70, 130, 169));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_11_1.add(lblNewLabel_1);

		txt_NgaySinh = new JTextField();
		txt_NgaySinh.setEnabled(false);
		txt_NgaySinh.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_NgaySinh.setColumns(18);
		panel_4.add(txt_NgaySinh);

		Component horizontalStrut1_1 = Box.createVerticalStrut(10);
		panel_4.add(horizontalStrut1_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		JPanel panel_11_2 = new JPanel();
		panel_11_2.setBackground(Color.WHITE);
		panel_5.add(panel_11_2);
		panel_11_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setForeground(new Color(70, 130, 169));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		panel_11_2.add(lblNewLabel_2);

		txt_Email = new JTextField();
		txt_Email.setEnabled(false);
		txt_Email.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_Email.setColumns(10);
		panel_5.add(txt_Email);

		Component horizontalStrut1_2 = Box.createVerticalStrut(10);
		panel_5.add(horizontalStrut1_2);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_2.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		JPanel panel_11_3 = new JPanel();
		panel_11_3.setBackground(Color.WHITE);
		panel_6.add(panel_11_3);
		panel_11_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_3 = new JLabel("CCCD");
		lblNewLabel_3.setForeground(new Color(70, 130, 169));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 18));
		panel_11_3.add(lblNewLabel_3);

		txt_CCCD = new JTextField();
		txt_CCCD.setEnabled(false);
		txt_CCCD.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_CCCD.setColumns(10);
		panel_6.add(txt_CCCD);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.WHITE);
		panel_1.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));

		JPanel panel_11_4 = new JPanel();
		panel_11_4.setBackground(Color.WHITE);
		panel_10.add(panel_11_4);
		panel_11_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_4 = new JLabel("Họ và tên");
		lblNewLabel_4.setForeground(new Color(70, 130, 169));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 18));
		panel_11_4.add(lblNewLabel_4);

		txt_HoTen = new JTextField();
		txt_HoTen.setEnabled(false);
		txt_HoTen.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_HoTen.setColumns(18);
		panel_10.add(txt_HoTen);

		Component horizontalStrut1_3 = Box.createVerticalStrut(10);
		panel_10.add(horizontalStrut1_3);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_1.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		JPanel panel_11_5 = new JPanel();
		panel_11_5.setBackground(Color.WHITE);
		panel_9.add(panel_11_5);
		panel_11_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_5 = new JLabel("Số điện thoại");
		lblNewLabel_5.setForeground(new Color(70, 130, 169));
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 18));
		panel_11_5.add(lblNewLabel_5);

		txt_SDT = new JTextField();
		txt_SDT.setEnabled(false);
		txt_SDT.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_SDT.setColumns(18);
		panel_9.add(txt_SDT);

		Component horizontalStrut1_4 = Box.createVerticalStrut(10);
		panel_9.add(horizontalStrut1_4);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_1.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));

		JPanel panel_11_6 = new JPanel();
		panel_11_6.setBackground(Color.WHITE);
		panel_8.add(panel_11_6);
		panel_11_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_6 = new JLabel("Giới tính");
		lblNewLabel_6.setForeground(new Color(70, 130, 169));
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 18));
		panel_11_6.add(lblNewLabel_6);

		comboBox_GioiTinh = new JComboBox();
		comboBox_GioiTinh.setEnabled(false);
		comboBox_GioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboBox_GioiTinh.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_8.add(comboBox_GioiTinh);
		comboBox_GioiTinh.setPreferredSize(new Dimension(0, 38));

		Component horizontalStrut1_5 = Box.createVerticalStrut(10);
		panel_8.add(horizontalStrut1_5);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_1.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));

		JPanel panel_11_7 = new JPanel();
		panel_11_7.setBackground(Color.WHITE);
		panel_7.add(panel_11_7);
		panel_11_7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_7 = new JLabel("Chức vụ");
		lblNewLabel_7.setForeground(new Color(70, 130, 169));
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 18));
		panel_11_7.add(lblNewLabel_7);

		txt_ChucVu = new JTextField();
		txt_ChucVu.setEnabled(false);
		txt_ChucVu.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_ChucVu.setColumns(10);
		panel_7.add(txt_ChucVu);

		JDateChooser dateKH = new JDateChooser();
		iconlich = new ImageIcon(getClass().getResource("/Image/icon_lich.png"));

		JPanel footer = new JPanel();
		footer.setBackground(Color.WHITE);
		footer.setPreferredSize(new Dimension(1250, 300));
		mainPanel.add(footer, BorderLayout.SOUTH);
		footer.setLayout(new FlowLayout(FlowLayout.RIGHT, 25, 30));

		btnCapNhat = new PrimaryButton("Cập nhật", "/Image/icon_ChinhSua.png");
		btnCapNhat.setFont(new Font("Arial", Font.BOLD, 18));
		btnCapNhat.setInsets(new Insets(4, 6, 4, 6));
		btnCapNhat.setBorderRadius(10);
		btnCapNhat.setIconTextGap(5);
		btnCapNhat.setIconSize(22, 22);

		btnHuyBo = new PrimaryButton("Hủy bỏ", "/Image/icon_XoaTrang.png");
		btnHuyBo.setPreferredSize(new Dimension(155, 40));
		btnHuyBo.setMaximumSize(new Dimension(2147483647, 50));
		btnHuyBo.setInsets(new Insets(4, 6, 4, 6));
		btnHuyBo.setIconTextGap(5);
		btnHuyBo.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyBo.setBorderRadius(10);
		btnHuyBo.setVisible(false);
		btnHuyBo.setAlignmentX(0.0f);
		footer.add(btnHuyBo);
		btnCapNhat.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnCapNhat.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		btnCapNhat.setPreferredSize(new Dimension(155, 40));
		footer.add(btnCapNhat);

		btnHoanThanh = new PrimaryButton("Hoàn thành", "/Image/done.png");
		btnHoanThanh.setPreferredSize(new Dimension(155, 40));
		btnHoanThanh.setMaximumSize(new Dimension(2147483647, 50));
		btnHoanThanh.setInsets(new Insets(4, 6, 4, 6));
		btnHoanThanh.setIconTextGap(5);
		btnHoanThanh.setFont(new Font("Arial", Font.BOLD, 18));
		btnHoanThanh.setBorderRadius(10);
		btnHoanThanh.setAlignmentX(0.0f);
		btnHoanThanh.setVisible(false);
		footer.add(btnHoanThanh);

	}

	public void addBtnCapNhat(ActionListener listener) {
		btnCapNhat.addActionListener(listener);
	}

	public JTextField getTxt_MaNhanVien() {
		return txt_MaNhanVien;
	}

	public JTextField getTxt_NgaySinh() {
		return txt_NgaySinh;
	}

	public JTextField getTxt_Email() {
		return txt_Email;
	}

	public JTextField getTxt_CCCD() {
		return txt_CCCD;
	}

	public JTextField getTxt_HoTen() {
		return txt_HoTen;
	}

	public JTextField getTxt_SDT() {
		return txt_SDT;
	}

	public JComboBox getComboBox_GioiTinh() {
		return comboBox_GioiTinh;
	}

	public JTextField getTxt_ChucVu() {
		return txt_ChucVu;
	}

	public PrimaryButton getBtnCapNhat() {
		return btnCapNhat;
	}

	public PrimaryButton getBtnHoanThanh() {
		return btnHoanThanh;
	}

	public PrimaryButton getBtnHuyBo() {
		return btnHuyBo;
	}

}
