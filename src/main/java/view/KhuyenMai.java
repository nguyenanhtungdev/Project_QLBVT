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
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class KhuyenMai extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSoluong;
	private JTextField tfNgayKT;
	private JTextField tffTen;
	private JTextField tffSdt;
	private JTextField tffCCCD;
	private JTextField tfTenKM;
	private JTextField tfNoidung;
	private JTextField tfSDT;
	private JComboBox cbGioiTinh;
	private JComboBox cbLoaikh;
	private JTable customerTable;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private Component lblfCCCD;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhuyenMai frame = new KhuyenMai();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public KhuyenMai() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pthemkh = new JPanel();
		pthemkh.setBounds(10, 11, 1218, 198);
		pthemkh.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)),"Thêm khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP,new Font("Arial", Font.BOLD, 13), Color.decode("#4682A9")));
		contentPane.add(pthemkh);
		JPanel pr = new JPanel() ; 
		pr.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 255)));
		pr.setBounds(1032,15,178,153);
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
		pl.setBounds(10, 15, 1012, 172);
		pthemkh.add(pl);
		pl.setLayout(null);
		
		
		pl.setLayout(new GridLayout(1,2));
		pl.setPreferredSize(new java.awt.Dimension(1580,358));
		
		JPanel lthem = new JPanel() ; 
		lthem.setSize(506, 190);
		lthem.setLayout(new BoxLayout(lthem, BoxLayout.Y_AXIS));
		
		JPanel pTenKM = new JPanel() ;
		pTenKM.setLayout(null);
		tfTenKM = new JTextField();
		tfTenKM.setBounds(145, 4, 351, 40);
		tfTenKM.setPreferredSize(new java.awt.Dimension(300, 43));
		pTenKM.add(tfTenKM) ; 
		lthem.add(pTenKM);
		
		JLabel lblTenKM = new JLabel("Tên khuyến mãi");
		lblTenKM.setBounds(10, 8, 128, 29);
		pTenKM.add(lblTenKM);
		lblTenKM.setFont(new Font("Arial", Font.BOLD, 17));
		lblTenKM.setForeground(Color.decode("#625A5A"));
		
		JPanel pNoidung = new JPanel() ; 
		pNoidung.setLayout(null);
		tfNoidung = new JTextField();
		tfNoidung.setBounds(149, 5, 348, 70);
		tfNoidung.setPreferredSize(new java.awt.Dimension(548, 43));
		pNoidung.add(tfNoidung) ; 
		lthem.add(pNoidung);
		
		JLabel lblNoidung = new JLabel("Nội dung");
		lblNoidung.setForeground(Color.decode("#625A5A"));
		lblNoidung.setFont(new Font("Arial", Font.BOLD, 17));
		lblNoidung.setBounds(10, 9, 100, 29);
		pNoidung.add(lblNoidung);
		
		JPanel rthem = new JPanel() ; 
		rthem.setLayout(new BoxLayout(rthem, BoxLayout.Y_AXIS));
		
		pl.add(lthem) ;
		pl.add(rthem) ; 
		
		JPanel pSoluong = new JPanel();
		pSoluong.setLayout(null);
		rthem.add(pSoluong);
		
		tfSoluong = new JTextField();
		tfSoluong.setPreferredSize(new Dimension(300, 43));
		tfSoluong.setBounds(149, 4, 347, 40);
		pSoluong.add(tfSoluong);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setForeground(Color.decode("#625A5A"));
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 17));
		lblSoLuong.setBounds(10, 8, 100, 29);
		pSoluong.add(lblSoLuong);
		
		JPanel pNgayKT = new JPanel();
		pNgayKT.setLayout(null);
		rthem.add(pNgayKT);
		
		JLabel lblNgayKT = new JLabel("Ngày kết thúc");
		lblNgayKT.setForeground(Color.decode("#625A5A"));
		lblNgayKT.setFont(new Font("Arial", Font.BOLD, 17));
		lblNgayKT.setBounds(10, 8, 129, 29);
		pNgayKT.add(lblNgayKT);
		
		tfNgayKT = new JTextField();
		tfNgayKT.setPreferredSize(new Dimension(300, 43));
		tfNgayKT.setBounds(149, 8, 347, 40);
		pNgayKT.add(tfNgayKT);
		
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
		
		JLabel lblfCCCD = new JLabel("Tên khuyến mãi");
		lblfCCCD.setFont(new Font("Arial", Font.BOLD, 13));
		lblfCCCD.setForeground(Color.decode("#625A5A"));
		lblfCCCD.setBounds(835, 11, 108, 14);
		panel.add(lblfCCCD);
		
		JLabel lblfTen = new JLabel("Mã khuyến mãi");
		lblfTen.setFont(new Font("Arial", Font.BOLD, 13));
		lblfTen.setForeground(Color.decode("#625A5A"));
		lblfTen.setBounds(696, 11, 108, 14);
		panel.add(lblfTen);
		
		JLabel lblSdt = new JLabel("Tình trạng");
		lblSdt.setFont(new Font("Arial", Font.BOLD, 13));
		lblSdt.setForeground(Color.decode("#625A5A"));
		lblSdt.setBounds(972, 11, 108, 14);
		panel.add(lblSdt);
		
		JPanel pDanhSach = new JPanel();
		pDanhSach.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Danh sách khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP,new Font("Arial", Font.BOLD, 13), Color.decode("#4682A9")));
		pDanhSach.setBounds(10, 311, 1218, 410);
		contentPane.add(pDanhSach);
		
		String[] columnNames = {"STT", "Mã khuyến mãi", "Tên khuyến mãi", "Nội dung", "Số lượng", "Ngày kết thúc", "Tình trạng"};
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
		    String tenKhuyenMai = tfTenKM.getText().trim();
		    String noiDung = tfNoidung.getText().trim();
		    String soLuong = tfSoluong.getText().trim();
		    String ngayKetThuc = tfNgayKT.getText().trim();
		  
		    Object[] rowData = {
		        tableModel.getRowCount() + 1,         
		        "?",   
		        tenKhuyenMai,
		        noiDung,
		        soLuong,
		        ngayKetThuc,
		        "Hoạt động" 
		    };
		    tableModel.addRow(rowData);

		    JOptionPane.showMessageDialog(this, "Khuyến mãi đã được thêm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
		    
		    tfTenKM.setText("");
		    tfNoidung.setText("");
		    tfSoluong.setText("");
		    tfNgayKT.setText("");
	
	}
	private void btnHuyBo() {
		tfTenKM.setText("");
	    tfNoidung.setText("");
	    tfSoluong.setText("");
	    tfNgayKT.setText("");
	    tfNgayKT.setText("");
	}
	private void btnTimKiem() { // LỖI
//	    String ten = tffTen.getText().toLowerCase(); // Lấy giá trị từ tffTen
//	    String cccd = tffCCCD.getText().toLowerCase(); // Lấy giá trị từ tffCCCD
//	    String sdt = tffSdt.getText().toLowerCase(); // Lấy giá trị từ tffSdt
//
//	    // Duyệt qua tất cả các hàng trong bảng
//	    DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
//	    for (int i = 0; i < model.getRowCount(); i++) {
//	        boolean matches = false;
//
//	        // Kiểm tra từng ô trong hàng
//	        if (model.getValueAt(i, 2).toString().toLowerCase().contains(ten) || // Tên khách hàng
//	            model.getValueAt(i, 3).toString().toLowerCase().contains(cccd) || // CCCD
//	            model.getValueAt(i, 4).toString().toLowerCase().contains(sdt)) { // Số điện thoại
//	            matches = true;
//	        }
//
//	        // Tô màu hàng nếu tìm thấy
//	        if (matches) {
//	            customerTable.setRowSelectionInterval(i, i); // Chọn hàng
//	            customerTable.setSelectionBackground(Color.YELLOW); // Màu nền khi chọn
//	        }
	    }
	}

