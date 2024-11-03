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
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class QLKH_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEmail;
	private JTextField tfNgaySinh;
	private JTextField tffTen;
	private JTextField tffSdt;
	private JTextField tffCCCD;
	private JTextField tfHoten;
	private JTextField tfCCCD;
	private JTextField tfSDT;
	private JComboBox cbGioiTinh;
	private JComboBox cbLoaikh;
	private JTable customerTable;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLKH_View frame = new QLKH_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public QLKH_View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pthemkh = new JPanel();
		pthemkh.setBounds(10, 11, 1218, 228);
		pthemkh.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)),"Thêm khách hàng", TitledBorder.LEADING, TitledBorder.TOP,new Font("Arial", Font.BOLD, 13), Color.decode("#4682A9")));
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
		
		JPanel pCCCD = new JPanel() ; 
		pCCCD.setLayout(null);
		tfCCCD = new JTextField();
		tfCCCD.setBounds(135, 5, 362, 40);
		tfCCCD.setPreferredSize(new java.awt.Dimension(548, 43));
		pCCCD.add(tfCCCD) ; 
		lthem.add(pCCCD);
		
		JLabel lblCCCD = new JLabel("CCCD");
		lblCCCD.setForeground(Color.decode("#625A5A"));
		lblCCCD.setFont(new Font("Arial", Font.BOLD, 17));
		lblCCCD.setBounds(10, 9, 100, 29);
		pCCCD.add(lblCCCD);
		
		
		JPanel pSDT = new JPanel() ; 
		pSDT.setLayout(null);
		tfSDT = new JTextField();
		tfSDT.setBounds(134, 5, 362, 40);
		tfSDT.setPreferredSize(new java.awt.Dimension(548, 43));
		pSDT.add(tfSDT) ; 
		lthem.add(pSDT);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setBounds(10, 9, 114, 29);
		pSDT.add(lblSDT);
		lblSDT.setForeground(Color.decode("#625A5A"));
		lblSDT.setFont(new Font("Arial", Font.BOLD, 17));
		
		JPanel rthem = new JPanel() ; 
		rthem.setLayout(new BoxLayout(rthem, BoxLayout.Y_AXIS));
		
		pl.add(lthem) ;
		pl.add(rthem) ; 
		
		JPanel pEmail = new JPanel();
		pEmail.setLayout(null);
		rthem.add(pEmail);
		
		tfEmail = new JTextField();
		tfEmail.setPreferredSize(new Dimension(300, 43));
		tfEmail.setBounds(149, 4, 347, 40);
		pEmail.add(tfEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.decode("#625A5A"));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 17));
		lblEmail.setBounds(10, 8, 100, 29);
		pEmail.add(lblEmail);
		
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
		cbGioiTinh.setBounds(149, 4, 347, 43);
		pGioitinh.add(cbGioiTinh);
		
		JPanel pLoaikh = new JPanel();
		pLoaikh.setLayout(null);
		rthem.add(pLoaikh);
		
		JLabel lblLoaikh = new JLabel("Loại khách hàng");
		lblLoaikh.setForeground(Color.decode("#625A5A"));
		lblLoaikh.setFont(new Font("Arial", Font.BOLD, 17));
		lblLoaikh.setBounds(10, 9, 143, 29);
		pLoaikh.add(lblLoaikh);
		
		cbLoaikh = new JComboBox();
		cbLoaikh.setModel(new DefaultComboBoxModel(new String[] {"Trẻ em ", "Sinh viên ", "Học sinh ", "Người già", "Người khuyết tật ", "Khách thường"}));
		cbLoaikh.setBounds(150, 4, 347, 43);
		pLoaikh.add(cbLoaikh);
		
		JPanel pNgaysinh = new JPanel();
		pNgaysinh.setLayout(null);
		rthem.add(pNgaysinh);
		
		JLabel lblNgaysinh = new JLabel("Ngày sinh");
		lblNgaysinh.setForeground(Color.decode("#625A5A"));
		lblNgaysinh.setFont(new Font("Arial", Font.BOLD, 17));
		lblNgaysinh.setBounds(10, 8, 100, 29);
		pNgaysinh.add(lblNgaysinh);
		
		tfNgaySinh = new JTextField();
		tfNgaySinh.setPreferredSize(new Dimension(300, 43));
		tfNgaySinh.setBounds(149, 8, 347, 40);
		pNgaysinh.add(tfNgaySinh);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 239, 1218, 72);
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
		lblfCCCD.setForeground(Color.decode("#625A5A"));
		lblfCCCD.setBounds(835, 11, 108, 14);
		panel.add(lblfCCCD);
		
		JLabel lblfTen = new JLabel("Tên khách hàng");
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
		pDanhSach.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP,new Font("Arial", Font.BOLD, 13), Color.decode("#4682A9")));
		pDanhSach.setBounds(10, 311, 1218, 410);
		contentPane.add(pDanhSach);
		
		String[] columnNames = {"STT", "Mã khách hàng", "Tên khách hàng", "CCCD", "Số điện thoại", "Email", "Giới tính", "Ngày sinh", "Loại khách hàng"};
        tableModel = new DefaultTableModel(columnNames, 0);
        pDanhSach.setLayout(new BorderLayout(0, 0));
        customerTable = new JTable(tableModel);
        customerTable.setFont(new Font("Arial",Font.PLAIN, 13));
        customerTable.setRowHeight(30);
        scrollPane = new JScrollPane(customerTable);
        pDanhSach.add(scrollPane);
	}
	public JPanel getQLKH_View() {
		return contentPane;
	}
	private void btnXacNhan() {
	    String hoTen = tfHoten.getText();
	    String cccd = tfCCCD.getText();
	    String sdt = tfSDT.getText();
	    String email = tfEmail.getText();
	    String ngaySinh = tfNgaySinh.getText();
	    String gioiTinh = (String) cbGioiTinh.getSelectedItem();
	    String loaiKhachHang = (String) cbLoaikh.getSelectedItem();

	    DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
	    int stt = model.getRowCount() + 1;
	    model.addRow(new Object[]{stt, "?", hoTen, cccd, sdt, email, gioiTinh, ngaySinh, loaiKhachHang});

	    JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

	    tfHoten.setText("");
	    tfCCCD.setText("");
	    tfSDT.setText("");
	    tfEmail.setText("");
	    tfNgaySinh.setText("");
	}
	private void btnHuyBo() {
		tfHoten.setText("");
	    tfCCCD.setText("");
	    tfSDT.setText("");
	    tfEmail.setText("");
	    cbGioiTinh.setSelectedIndex(0);
	    cbLoaikh.setSelectedIndex(0);
	    tfNgaySinh.setText("");
	}
	private void btnTimKiem() { // LỖI
	    String ten = tffTen.getText().toLowerCase(); // Lấy giá trị từ tffTen
	    String cccd = tffCCCD.getText().toLowerCase(); // Lấy giá trị từ tffCCCD
	    String sdt = tffSdt.getText().toLowerCase(); // Lấy giá trị từ tffSdt

	    // Duyệt qua tất cả các hàng trong bảng
	    DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
	    for (int i = 0; i < model.getRowCount(); i++) {
	        boolean matches = false;

	        // Kiểm tra từng ô trong hàng
	        if (model.getValueAt(i, 2).toString().toLowerCase().contains(ten) || // Tên khách hàng
	            model.getValueAt(i, 3).toString().toLowerCase().contains(cccd) || // CCCD
	            model.getValueAt(i, 4).toString().toLowerCase().contains(sdt)) { // Số điện thoại
	            matches = true;
	        }

	        // Tô màu hàng nếu tìm thấy
	        if (matches) {
	            customerTable.setRowSelectionInterval(i, i); // Chọn hàng
	            customerTable.setSelectionBackground(Color.YELLOW); // Màu nền khi chọn
	        }
	    }
	}

}

