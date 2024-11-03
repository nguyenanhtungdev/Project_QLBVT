package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class QLNV extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEmail;
	private JTextField tfNgaySinh;
	private JTextField tffTen;
	private JTextField tffSdt;
	private JTextField tffCCCD;
	private JTextField tfHoten;
	private JComboBox cbGioiTinh;
	private JComboBox cbLoaikh;
	private JTable customerTable;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private Container pNgaysinh;
	private JTextField tfHsl;
	private JComboBox cbCalam;
	private JTextField tfSdt;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField tfDiaChi;
	private JTextField tfCCCD;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLNV frame = new QLNV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public QLNV() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pthemkh = new JPanel();
		pthemkh.setBounds(10, 11, 1218, 229);
		pthemkh.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)),"Thêm nhân viên", TitledBorder.LEADING, TitledBorder.TOP,new Font("Arial", Font.BOLD, 13), Color.decode("#4682A9")));
		contentPane.add(pthemkh);
		JPanel pr = new JPanel() ; 
		pr.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 255)));
		pr.setBounds(1030,33,178,153);
		pthemkh.setLayout(null);
		pthemkh.add(pr);
		pr.setLayout(null);
		
		JButton btnHuybo = new JButton("Hủy bỏ");
		btnHuybo.setForeground(new Color(255, 255, 255));
		btnHuybo.setBackground(Color.decode("#FF3014"));
		btnHuybo.setFont(new Font("Arial", Font.BOLD, 17));
		btnHuybo.setBounds(27, 86, 124, 45);
		btnHuybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHuyBo();
			}
		});
		pr.add(btnHuybo);
		
		JButton btnXacnhan = new JButton("Xác nhận");
		btnXacnhan.setForeground(new Color(255, 255, 255));
		btnXacnhan.setBackground(Color.decode("#4682A9"));
		btnXacnhan.setFont(new Font("Arial", Font.BOLD, 17));
		btnXacnhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXacNhan();
			}
		});
		btnXacnhan.setBounds(27, 20, 124, 45);
		pr.add(btnXacnhan);
		
		JPanel pl = new JPanel() ;
		pl.setBounds(10, 15, 1012, 209);
		pthemkh.add(pl);
		pl.setLayout(null);
		
		
		pl.setLayout(new GridLayout(1,2));
		pl.setPreferredSize(new java.awt.Dimension(1580,358));
		
		JPanel lthem = new JPanel() ; 
		lthem.setLayout(new BoxLayout(lthem, BoxLayout.Y_AXIS));
		
		JPanel pHoten = new JPanel() ;
		pHoten.setLayout(null);
		tfHoten = new JTextField();
		tfHoten.setBounds(134, 4, 362, 40);
		tfHoten.setPreferredSize(new java.awt.Dimension(300, 43));
		pHoten.add(tfHoten) ; 
		lthem.add(pHoten);
		
		JLabel lblHoten = new JLabel("Họ và tên");
		lblHoten.setBounds(10, 8, 100, 29);
		pHoten.add(lblHoten);
		lblHoten.setFont(new Font("Arial", Font.BOLD, 17));
		lblHoten.setForeground(Color.decode("#625A5A"));
		
		JPanel pNgaysinh = new JPanel();
		pNgaysinh.setLayout(null);
		lthem.add(pNgaysinh);
		
		JLabel lblNgaysinh = new JLabel("Ngày sinh");
		lblNgaysinh.setForeground(Color.decode("#625A5A"));
		lblNgaysinh.setFont(new Font("Arial", Font.BOLD, 17));
		lblNgaysinh.setBounds(10, 8, 100, 29);
		pNgaysinh.add(lblNgaysinh);
		
		tfNgaySinh = new JTextField();
		tfNgaySinh.setPreferredSize(new Dimension(300, 43));
		tfNgaySinh.setBounds(135, 8, 361, 40);
		pNgaysinh.add(tfNgaySinh);
		
		JPanel pSdt = new JPanel() ; 
		pSdt.setLayout(null);
		lthem.add(pSdt);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setForeground(Color.decode("#625A5A"));
		lblSDT.setFont(new Font("Arial", Font.BOLD, 17));
		lblSDT.setBounds(10, 9, 115, 29);
		pSdt.add(lblSDT);
		
		tfSdt = new JTextField();
		tfSdt.setPreferredSize(new Dimension(300, 43));
		tfSdt.setBounds(135, 5, 361, 40);
		pSdt.add(tfSdt);
		
		
		JPanel pEmail = new JPanel() ; 
		pEmail.setLayout(null);
		lthem.add(pEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 9, 114, 29);
		pEmail.add(lblEmail);
		lblEmail.setForeground(Color.decode("#625A5A"));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 17));
		
		tfEmail = new JTextField();
		tfEmail.setPreferredSize(new Dimension(300, 43));
		tfEmail.setBounds(135, 4, 361, 40);
		pEmail.add(tfEmail);
		
		JPanel rthem = new JPanel() ; 
		rthem.setSize(506, 350);
		rthem.setLayout(new BoxLayout(rthem, BoxLayout.Y_AXIS));
		
		pl.add(lthem) ;
		pl.add(rthem) ; 
		
		JPanel pDiachi = new JPanel();
		pDiachi.setLayout(null);
		rthem.add(pDiachi);
		
		JLabel lblDiachi = new JLabel("Địa chỉ");
		lblDiachi.setForeground(Color.decode("#625A5A"));
		lblDiachi.setFont(new Font("Arial", Font.BOLD, 17));
		lblDiachi.setBounds(10, 8, 100, 29);
		pDiachi.add(lblDiachi);
		
		tfDiaChi = new JTextField();
		tfDiaChi.setPreferredSize(new Dimension(300, 43));
		tfDiaChi.setBounds(149, 4, 347, 35);
		pDiachi.add(tfDiaChi);
		
		JPanel pGioitinh = new JPanel();
		pGioitinh.setLayout(null);
		rthem.add(pGioitinh);
		
		JLabel lblGioitinh = new JLabel("Giới tính");
		lblGioitinh.setForeground(Color.decode("#625A5A"));
		lblGioitinh.setFont(new Font("Arial", Font.BOLD, 17));
		lblGioitinh.setBounds(10, 9, 100, 29);
		pGioitinh.add(lblGioitinh);
		
		cbGioiTinh = new JComboBox();
		cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cbGioiTinh.setBounds(149, 4, 347, 35);
		pGioitinh.add(cbGioiTinh);
		
		JPanel pCCCD = new JPanel();
		pCCCD.setLayout(null);
		rthem.add(pCCCD);
		
		JLabel lblCCCD = new JLabel("CCCD");
		lblCCCD.setForeground(Color.decode("#625A5A"));
		lblCCCD.setFont(new Font("Arial", Font.BOLD, 17));
		lblCCCD.setBounds(10, 9, 143, 29);
		pCCCD.add(lblCCCD);
		
		tfCCCD = new JTextField();
		tfCCCD.setPreferredSize(new Dimension(300, 43));
		tfCCCD.setBounds(149, 4, 347, 35);
		pCCCD.add(tfCCCD);
		
		JPanel pHsl = new JPanel();
		pHsl.setLayout(null);
		rthem.add(pHsl);
		
		JLabel lblHsl = new JLabel("Hệ số lương");
		lblHsl.setForeground(Color.decode("#625A5A"));
		lblHsl.setFont(new Font("Arial", Font.BOLD, 17));
		lblHsl.setBounds(10, 8, 132, 29);
		pHsl.add(lblHsl);
		
		tfHsl = new JTextField();
		tfHsl.setPreferredSize(new Dimension(300, 43));
		tfHsl.setBounds(149, 4, 347, 35);
		pHsl.add(tfHsl);
		
		JPanel pCalam = new JPanel();
		pCalam.setLayout(null);
		rthem.add(pCalam);
		//???????????????????????????
		JLabel lblCalam = new JLabel("Ca làm");
		lblCalam.setForeground(Color.decode("#625A5A"));
		lblCalam.setFont(new Font("Arial", Font.BOLD, 17));
		lblCalam.setBounds(10, 9, 143, 29);
		pCalam.add(lblCalam);
		
		cbCalam = new JComboBox();
		cbCalam.setBounds(149, 4, 347, 35);
		pCalam.add(cbCalam);
		cbCalam.setModel(new DefaultComboBoxModel(new String[] {""}));
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 238, 1218, 62);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tffTen = new JTextField();
		tffTen.setBounds(696, 31, 126, 30);
		panel.add(tffTen);
		tffTen.setColumns(10);
		
		JButton btnTimkiem = new JButton("Tìm kiếm");
		btnTimkiem.setFont(new Font("Arial", Font.BOLD, 13));
		btnTimkiem.setForeground(new Color(255, 255, 255));
		btnTimkiem.setBackground(Color.decode("#4682A9"));
		btnTimkiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTimKiem();
			}
		});
		btnTimkiem.setBounds(1113, 11, 95, 50);
		panel.add(btnTimkiem);
		
		tffSdt = new JTextField();
		tffSdt.setColumns(10);
		tffSdt.setBounds(972, 31, 126, 30);
		panel.add(tffSdt);
		
		tffCCCD = new JTextField();
		tffCCCD.setColumns(10);
		tffCCCD.setBounds(835, 31, 126, 30);
		panel.add(tffCCCD);
		
		JLabel lblfCCCD = new JLabel("CCCD");
		lblfCCCD.setFont(new Font("Arial", Font.BOLD, 13));
		lblGioitinh.setForeground(Color.decode("#625A5A"));
		lblfCCCD.setBounds(835, 11, 108, 14);
		panel.add(lblfCCCD);
		
		JLabel lblfTen = new JLabel("Tên nhân viên");
		lblfTen.setFont(new Font("Arial", Font.BOLD, 13));
		lblfTen.setForeground(Color.decode("#625A5A"));
		lblfTen.setBounds(696, 11, 108, 14);
		panel.add(lblfTen);
		
		JLabel lblSdt = new JLabel("SDT");
		lblSdt.setFont(new Font("Arial", Font.BOLD, 13));
		lblSdt.setForeground(Color.decode("#625A5A"));
		lblSdt.setBounds(972, 11, 108, 14);
		panel.add(lblSdt);
		
		JPanel pDanhSach = new JPanel();
		pDanhSach.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Danh sách nhân viên", TitledBorder.LEADING, TitledBorder.TOP,new Font("Arial", Font.BOLD, 13), Color.decode("#4682A9")));
		pDanhSach.setBounds(10, 304, 1218, 450);
		contentPane.add(pDanhSach);
		
		String[] columnNames = {"STT", "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email", "Giới tính", "Trạng thái", "Chi tiết"};
        tableModel = new DefaultTableModel(columnNames, 0);
        pDanhSach.setLayout(new BorderLayout(0, 0));
        customerTable = new JTable(tableModel);
        customerTable.setFont(new Font("Arial",Font.PLAIN, 13));
        customerTable.setRowHeight(30);
        scrollPane = new JScrollPane(customerTable);
        pDanhSach.add(scrollPane);
	}
	public JPanel getQLNV_View() {
		return contentPane;
	}
	private void btnXacNhan() {
    // Lấy thông tin từ các trường đầu vào
    String hoTen = tfHoten.getText().trim();
    String ngaySinh = tfNgaySinh.getText().trim();
    String sdt = tfSdt.getText().trim();
    String email = tfEmail.getText().trim();
    String diaChi = tfDiaChi.getText().trim();
    String cccd = tfCCCD.getText().trim();
    String gioiTinh = cbGioiTinh.getSelectedItem().toString();
    String heSoLuong = tfHsl.getText().trim();
    String caLam = cbCalam.getSelectedItem().toString();
    
    Object[] rowData = {
        tableModel.getRowCount() + 1,
        "?",hoTen,sdt,email,gioiTinh,"Hoạt động","Xem chi tiết"
    };
    tableModel.addRow(rowData);
    
    JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

    tfHoten.setText("");
    tfNgaySinh.setText("");
    tfSdt.setText("");
    tfEmail.setText("");
    tfDiaChi.setText("");
    tfCCCD.setText("");
    tfHsl.setText("");
    cbGioiTinh.setSelectedIndex(0);
    cbCalam.setSelectedIndex(0);
}

	private void btnHuyBo() {
		tfHoten.setText("");
	    tfNgaySinh.setText("");
	    tfSdt.setText("");
	    tfEmail.setText("");
	    tfDiaChi.setText("");
	    tfCCCD.setText("");
	    tfHsl.setText("");
	    cbGioiTinh.setSelectedIndex(0);
	    cbCalam.setSelectedIndex(0);
	}
	private void btnTimKiem() { // LỖI

	}
}

