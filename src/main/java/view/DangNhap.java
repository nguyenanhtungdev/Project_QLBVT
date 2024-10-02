package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import orther.ColorConstants;
import orther.RoundButton;
import orther.RoundField;
import orther.RoundPassField;
import orther.TextFont;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class DangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_passWord;
    private RoundButton btn_DangNhap;
    private JTextField txt_userName;
    private JLabel lblMtKhu;
    private JLabel lbl_DangNhap;
    private JLabel image;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			DangNhap dn = new DangNhap();
			dn.setVisible(true);
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
        panel_Right.add(txt_passWord);
        txt_passWord.setColumns(20);

        // Replace JButton with RoundButton
        btn_DangNhap = new RoundButton("Đăng Nhập");
        btn_DangNhap.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btn_DangNhap.setBackground(ColorConstants.SECONDARY_COLOR);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btn_DangNhap.setBackground(ColorConstants.PRIMARY_COLOR);
        	}
        });
        btn_DangNhap.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_DangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn_DangNhap.setBorder(null);
        btn_DangNhap.setBounds(73, 383, 271, 47);
        panel_Right.add(btn_DangNhap);

        // Tạo custom JTextField với icon
        txt_userName =new RoundField(15);
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
        
        lblMtKhu = new JLabel("Mật khẩu");
        lblMtKhu.setFont(TextFont.FONT_2BOLD);
        lblMtKhu.setBounds(73, 274, 119, 27);
        lblMtKhu.setForeground(ColorConstants.PRIMARY_COLOR);
        panel_Right.add(lblMtKhu);
        
        lbl_DangNhap = new JLabel("ĐĂNG NHẬP");
        lbl_DangNhap.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_DangNhap.setFont(TextFont.FONT_1);
        lbl_DangNhap.setBounds(113, 131, 188, 47);
        lbl_DangNhap.setForeground(ColorConstants.PRIMARY_COLOR);
        panel_Right.add(lbl_DangNhap);
        
        JCheckBox checkbox_hienMK = new JCheckBox("Hiện mật khẩu");
        checkbox_hienMK.setHorizontalAlignment(SwingConstants.CENTER);
        checkbox_hienMK.setBorder(null); // Xóa đường viền
        checkbox_hienMK.setBackground(Color.WHITE);
        checkbox_hienMK.setFont(TextFont.FONT_3);
        checkbox_hienMK.setBounds(232, 349, 112, 23);
        checkbox_hienMK.setForeground(ColorConstants.PRIMARY_COLOR);
        panel_Right.add(checkbox_hienMK);
        
		enterJtextField();
    }
    private void enterJtextField() {
    	txt_userName.addActionListener(e -> SwingUtilities.invokeLater(() -> txt_passWord.requestFocus()));
    }
}
