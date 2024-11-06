package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import controller.HienThi_Controller;
import controller.DangNhap_Controller;
import other.ColorConstants;
import other.DangerPrimaryButton;
import other.PrimaryButton;
import other.RoundedButton;
import other.SecondaryButton;
import other.RoundField;
import other.RoundPassField;
import other.Shortcut;
import other.TextFont;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import other.ColorConstants;
import other.RoundedButton;
import other.RoundField;
import other.RoundPassField;
import other.TextFont;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

public class DangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RoundPassField txt_passWord;
	private JTextField txt_userName;
	private RoundedButton btn_DangNhap;
	private JLabel image;
	private JCheckBox checkbox_hienMK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			DangNhap_Controller controller = new DangNhap_Controller();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public DangNhap() {
		setBounds(0, 0, 837, 594);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		JPanel panel_Left = new JPanel();
		panel_Left.setBorder(null);
		contentPane.add(panel_Left);
		panel_Left.setBackground(ColorConstants.PRIMARY_COLOR);
		panel_Left.setLayout(null);

		image = new JLabel("");
//        image.setIcon(new ImageIcon(DangNhap.class.getResource("/view/bg-login.png")));
		image.setIcon(new ImageIcon(getClass().getResource("/Image/bg-login.png")));
		image.setBounds(0, 0, 412, 570);
		panel_Left.add(image);

		JPanel panel_Right = new JPanel();
		panel_Right.setBorder(null);
		panel_Right.setBackground(Color.WHITE);
		panel_Right.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.add(panel_Right);
		panel_Right.setLayout(null);

		txt_passWord = new RoundPassField(15);
		txt_passWord.setBorder(new EmptyBorder(0, 10, 0, 0));
		txt_passWord.setFont(TextFont.FONT_2);
		txt_passWord.setBounds(73, 301, 271, 37);
		txt_passWord.setColumns(20);
		panel_Right.add(txt_passWord);

		// Replace JButton with RoundButton
		btn_DangNhap = new PrimaryButton("Đăng Nhập");
		btn_DangNhap.setBorderRadius(20);
		btn_DangNhap.setBounds(73, 383, 271, 47);

		panel_Right.add(btn_DangNhap);

		// Tạo custom JTextField với icon
		txt_userName = new RoundField(15);

		txt_userName.setBorder(new EmptyBorder(0, 10, 0, 0));
		txt_userName.setFont(TextFont.FONT_2);
		txt_userName.setColumns(20);
		txt_userName.setBounds(73, 226, 271, 37);
		panel_Right.add(txt_userName);

		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		lblNewLabel.setFont(TextFont.FONT_2BOLD);
		lblNewLabel.setBounds(73, 200, 119, 27);
		lblNewLabel.setForeground(ColorConstants.PRIMARY_COLOR);
		panel_Right.add(lblNewLabel);

		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(TextFont.FONT_2BOLD);
		lblMtKhu.setBounds(73, 274, 119, 27);
		lblMtKhu.setForeground(ColorConstants.PRIMARY_COLOR);
		panel_Right.add(lblMtKhu);

		JLabel lbl_DangNhap = new JLabel("ĐĂNG NHẬP");
		lbl_DangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_DangNhap.setFont(TextFont.FONT_1);
		lbl_DangNhap.setBounds(113, 131, 188, 47);
		lbl_DangNhap.setForeground(ColorConstants.PRIMARY_COLOR);
		panel_Right.add(lbl_DangNhap);

		checkbox_hienMK = new JCheckBox("Hiện mật khẩu");
		checkbox_hienMK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Nếu checkbox được chọn, hiển thị mật khẩu dưới dạng văn bản
				if (checkbox_hienMK.isSelected()) {
					txt_passWord.setEchoChar((char) 0);
				} else {
					txt_passWord.setEchoChar('*');
				}
			}
		});

		checkbox_hienMK.setHorizontalAlignment(SwingConstants.CENTER);
		checkbox_hienMK.setBorder(null);
		checkbox_hienMK.setBackground(Color.WHITE);
		checkbox_hienMK.setFont(TextFont.FONT_3);
		checkbox_hienMK.setBounds(232, 349, 112, 23);
		checkbox_hienMK.setForeground(ColorConstants.PRIMARY_COLOR);
		panel_Right.add(checkbox_hienMK);

		enterJtextField();
		// Gọi phương thức để thêm phím tắt
		Shortcut.addShowPasswordShortcut(txt_passWord, checkbox_hienMK, "showPassword");
	}

	private void enterJtextField() {
		txt_userName.addActionListener(e -> SwingUtilities.invokeLater(() -> txt_passWord.requestFocus()));
	}

	public RoundPassField getTxt_passWord() {
		return txt_passWord;
	}

	public JTextField getTxt_userName() {
		return txt_userName;
	}

	public RoundedButton getBtn_DangNhap() {
		return btn_DangNhap;
	}

}
