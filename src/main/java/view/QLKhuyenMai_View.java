package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import model.HoaDon;
import model.KhuyenMai;
import model.KhuyenMai_DAO;
import other.ColorConstants;
import other.CustomTitleLable;
import other.RoundButton;

import java.awt.FlowLayout;

public class QLKhuyenMai_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDateChooser dateKTKM;
	private RoundButton btnThem;
	private RoundButton btnHuy;
	private DefaultTableModel modelTableKM;
	private JTable tableKM;
	private JPanel panelSearch1;
	private JComboBox<String> comboBoxMaKM;
	private JComboBox comboBoxTrangThai;
	private JButton btnReset;
	private RoundButton btnSearch;
	private static KhuyenMai_DAO km_dao;
	private JTextField txtMakm;
	private ImageIcon iconlich;
	private int STT = 1;
	private JScrollPane tablePanel;
	private JTextField txtTenKM;
	private JTextField txtSLKM;
	private JTextArea txtNDKM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLKhuyenMai_View frame = new QLKhuyenMai_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void addButtonReloadListener(ActionListener listener) {
<<<<<<< Updated upstream
    	btnReset.addActionListener(listener);
    } 
	public void addButtonTimListener(ActionListener listener) {
    	btnSearch.addActionListener(listener);
    } 
	public void addButtonThemListener(ActionListener listener) {
    	btnThem.addActionListener(listener);
    } 
	public void addButtonHuyListener(ActionListener listener) {
    	btnHuy.addActionListener(listener);
    } 
	// Phương thức tạo JComboBox với tính năng gợi ý
	public static JComboBox<String> timKiemMaKhuyenMai() {
	    JComboBox<String> comboBox = new JComboBox<>();
	    EventList<String> maKMList = new BasicEventList<>();
	    List<KhuyenMai> danhSachKM;
		try {
			danhSachKM = km_dao.getAllKhuyenMai1();
		    for (KhuyenMai km : danhSachKM) {
		        String maKM = km.getMaKhuyenMai(); 
		        maKMList.add(maKM);
		        comboBox.addItem(maKM); 
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    AutoCompleteSupport.install(comboBox, maKMList);
	    
	    return comboBox;
	}	
	
=======
		btnReset.addActionListener(listener);
	}

	public void addButtonTimListener(ActionListener listener) {
		btnSearch.addActionListener(listener);
	}

	public void addButtonThemListener(ActionListener listener) {
		btnThem.addActionListener(listener);
	}

	public void addButtonHuyListener(ActionListener listener) {
		btnHuy.addActionListener(listener);
	}

	// Phương thức tạo JComboBox với tính năng gợi ý
	public static JComboBox<String> timKiemMaKhuyenMai() {
		JComboBox<String> comboBox = new JComboBox<>();
		EventList<String> maKMList = new BasicEventList<>();
		List<KhuyenMai> danhSachKM;
		try {
			danhSachKM = km_dao.getAllKhuyenMai1();
			for (KhuyenMai km : danhSachKM) {
				String maKM = km.getMaKhuyenMai();
				maKMList.add(maKM);
				comboBox.addItem(maKM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		AutoCompleteSupport.install(comboBox, maKMList);

		return comboBox;
	}

>>>>>>> Stashed changes
	public QLKhuyenMai_View() {
		FlatLightLaf.setup();
		km_dao = new KhuyenMai_DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< Updated upstream
        setSize(1250, 800); 
        setLocationRelativeTo(null);        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel(new BorderLayout()); 
        headerPanel.setBackground(Color.WHITE);
        
        JPanel panelTitle = new JPanel();
        panelTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        panelTitle.setBackground(Color.WHITE);
        headerPanel.add(panelTitle, BorderLayout.NORTH);
        
        JLabel lblQLKM = new JLabel("Quản Lý Khuyến Mãi");
        lblQLKM.setHorizontalAlignment(SwingConstants.CENTER);
        lblQLKM.setFont(new Font("Arial", Font.BOLD, 20));
        panelTitle.add(lblQLKM);
        contentPane.add(headerPanel, BorderLayout.NORTH);
        
        JPanel pThemKM = new JPanel();
        pThemKM.setBackground(Color.WHITE);
        headerPanel.add(pThemKM, BorderLayout.CENTER);
        pThemKM.setBorder(
            new CompoundBorder(new LineBorder(new Color(192, 192, 192), 1, true), new EmptyBorder(10, 10, 10, 10))
        );
        pThemKM.setLayout(new BorderLayout(0, 0));
        
        JPanel panelNhap = new JPanel(); 
        panelNhap.setBackground(Color.WHITE);
        pThemKM.add(panelNhap, BorderLayout.CENTER);
        panelNhap.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        JPanel panelNhap1 = new JPanel();
        panelNhap1.setBackground(Color.WHITE);
        panelNhap1.setBorder(new EmptyBorder(10, 0, 0, 10));
        panelNhap.add(panelNhap1);
        panelNhap1.setLayout(new BorderLayout(0, 0));
        
        JPanel panelNhap2 = new JPanel();
        panelNhap2.setBackground(Color.WHITE);
        panelNhap2.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelNhap1.add(panelNhap2, BorderLayout.NORTH);
        panelNhap2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JPanel panelTenKM = new JPanel();
        panelTenKM.setBackground(Color.WHITE);
        panelTenKM.setBorder(new EmptyBorder(0, 0, 0, 73));
        panelNhap2.add(panelTenKM);
        
        JLabel lblTenKM = new JLabel("Tên khuyến mãi");
        lblTenKM.setForeground(new Color(70, 130, 180));
        lblTenKM.setFont(new Font("Arial", Font.BOLD, 16));
        panelTenKM.add(lblTenKM);
        
        JPanel panelTxtTenkm = new JPanel();
        panelTxtTenkm.setBackground(Color.WHITE);
        panelTxtTenkm.setBorder(new EmptyBorder(0, 0, 0, 20));
        panelNhap2.add(panelTxtTenkm);
        
        txtTenKM = new JTextField(14);
        panelTxtTenkm.add(txtTenKM);
        txtTenKM.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JPanel panelSL = new JPanel();
        panelSL.setBackground(Color.WHITE);
        panelNhap2.add(panelSL);
        
        JLabel lblSLKM = new JLabel("Số lượng khuyến mãi");
        lblSLKM.setHorizontalAlignment(SwingConstants.CENTER);
        lblSLKM.setForeground(new Color(70, 130, 180));
        lblSLKM.setFont(new Font("Arial", Font.BOLD, 16));
        panelSL.add(lblSLKM);
        
        JPanel panelTxtSL = new JPanel();
        panelTxtSL.setBackground(Color.WHITE);
        panelTxtSL.setBorder(new EmptyBorder(0, 5, 0, 0));
        panelNhap2.add(panelTxtSL);
        panelTxtSL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        txtSLKM = new JTextField(14);
        txtSLKM.setFont(new Font("Arial", Font.PLAIN, 16));
        panelTxtSL.add(txtSLKM);
        
        JPanel panelNhap3 = new JPanel();
        panelNhap3.setBackground(Color.WHITE);
        panelNhap3.setBorder(new EmptyBorder(5, 5, 0, 5));
        panelNhap1.add(panelNhap3);
        panelNhap3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        JPanel panelNDkm = new JPanel();
        panelNDkm.setBackground(Color.WHITE);
        panelNDkm.setBorder(new EmptyBorder(0, 0, 80, 5));
        FlowLayout fl_panelNDkm = (FlowLayout) panelNDkm.getLayout();
        panelNhap3.add(panelNDkm);
        
        JLabel lblNDKM = new JLabel(" Nội dung khuyến mãi");
        lblNDKM.setForeground(new Color(70, 130, 180));
        lblNDKM.setFont(new Font("Arial", Font.BOLD, 16));
        panelNDkm.add(lblNDKM);
        
        JPanel panelTxtNDkm = new JPanel();
        panelTxtNDkm.setBackground(Color.WHITE);
        panelTxtNDkm.setBorder(new EmptyBorder(0, 23, 0, 0));
        panelNhap3.add(panelTxtNDkm);
        
        txtNDKM = new JTextArea();
        txtNDKM.setWrapStyleWord(true);
        txtNDKM.setPreferredSize(new Dimension(195, 100));
        txtNDKM.setMargin(new Insets(5, 5, 5, 5));
        txtNDKM.setLineWrap(true);
        txtNDKM.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNDKM.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
        panelTxtNDkm.add(txtNDKM);
        
        JPanel panelDate = new JPanel();
        panelDate.setBackground(Color.WHITE);
        panelDate.setBorder(new EmptyBorder(0, 0, 70, 0));
        panelNhap3.add(panelDate);
        
        JPanel panelDate1 = new JPanel();
        panelDate1.setBackground(Color.WHITE);
        panelDate.add(panelDate1);
        panelDate1.setLayout(new BoxLayout(panelDate1, BoxLayout.X_AXIS));
        
        JPanel panelDate3 = new JPanel();
        panelDate3.setBackground(Color.WHITE);
        panelDate3.setBorder(new EmptyBorder(0, 20, 0, 10));
        panelDate1.add(panelDate3);
        
        JLabel lblDateKT = new JLabel("Ngày kết thúc");
        lblDateKT.setForeground(new Color(70, 130, 180));
        lblDateKT.setFont(new Font("Arial", Font.BOLD, 16));
        panelDate3.add(lblDateKT);
        dateKTKM = new JDateChooser();
        dateKTKM.setBackground(Color.WHITE);
        dateKTKM.getCalendarButton().setBackground(new Color(235,235,235));
        dateKTKM.getCalendarButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        dateKTKM.setBorder(new EmptyBorder(0, 61, 0, 0));
        dateKTKM.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
        dateKTKM.setFont(new Font("Arial", Font.PLAIN, 16));
        dateKTKM.setDateFormatString("dd-MM-yyyy");
        dateKTKM.setPreferredSize(new Dimension(250, 25));
        iconlich = new ImageIcon(getClass().getResource("/Image/icon_lich.png"));
        dateKTKM.setIcon(new ImageIcon(iconlich.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH)));
        panelDate1.add(dateKTKM);
      
        JPanel panelNut = new JPanel();
        panelNut.setBackground(Color.WHITE);
        pThemKM.add(panelNut, BorderLayout.EAST);
        panelNut.setLayout(new BoxLayout(panelNut, BoxLayout.Y_AXIS)); 

        panelNut.setBorder(
            new CompoundBorder(new LineBorder(new Color(192, 192, 192), 1, true), new EmptyBorder(0, 10, 0, 10))
        );

        btnThem = new RoundButton("Thêm khuyến mãi    ", "/Image/plus.png");
        btnThem.setColor_1(new Color(149, 149, 255)); 
        btnThem.setColor_2(ColorConstants.HOVER_COLOR); 
        btnThem.setHeSoBoGoc(15); 
        btnThem.setFont(new Font("Arial", Font.BOLD, 16));  
        btnThem.setIconSize(32,32);  

        btnHuy = new RoundButton("Huỷ bỏ     ", "/Image/cancel.png");
        btnHuy.setColor_1(new Color(255, 128, 0)); 
        btnHuy.setColor_2(new Color(240, 128, 0)); 
        btnHuy.setHeSoBoGoc(15);  // Độ bo góc
        btnHuy.setFont(new Font("Arial", Font.BOLD, 16));  
        btnHuy.setIconSize(32, 32);  

        panelNut.add(Box.createVerticalGlue());      
        JPanel panelBtnThem = new JPanel();
        panelBtnThem.setBackground(Color.WHITE);
        panelNut.add(panelBtnThem);
        panelBtnThem.setLayout(new BorderLayout(0, 0));
        panelBtnThem.add(btnThem, BorderLayout.SOUTH);
        panelNut.add(Box.createVerticalStrut(20)); 
        
        JPanel panelBtnHuy = new JPanel();
        panelBtnHuy.setBackground(Color.WHITE);
        panelNut.add(panelBtnHuy);
        panelBtnHuy.setLayout(new BorderLayout(0, 0));
        panelBtnHuy.add(btnHuy, BorderLayout.NORTH);
        panelNut.add(Box.createVerticalGlue()); 
        
        String[] header = {"STT","Mã khuyến mãi","Tên khuyến mãi","Nội dung","Số lượng","Ngày kết thúc","Tình trạng"};
        Font headerFont = new Font("Arial", Font.BOLD, 18);
        modelTableKM = new DefaultTableModel(header, 0);
        
        tableKM = new JTable(modelTableKM);
        tableKM.getTableHeader().setFont(headerFont); 
        tableKM.setFont(new Font("Arial", Font.PLAIN, 16));
        tablePanel = new JScrollPane(tableKM);
        tableKM.getColumnModel().getColumn(0).setPreferredWidth(5);
        DocDuLieuVaoTableHoaDon();
        
        JPanel panelSearchAndTable = new JPanel();
        panelSearchAndTable.setBorder(new EmptyBorder(15, 0, 0, 0));
        panelSearchAndTable.setBackground(Color.WHITE);
        contentPane.add(panelSearchAndTable, BorderLayout.CENTER);
        panelSearchAndTable.setLayout(new BorderLayout(0, 0));
        
        JPanel panelSearch = new JPanel();
        panelSearchAndTable.add(panelSearch, BorderLayout.NORTH);
        tableKM.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);      
        tableKM.setRowHeight(30);  
        panelSearchAndTable.add(new JScrollPane(tableKM));
        panelSearch.setLayout(new BoxLayout(panelSearch, BoxLayout.X_AXIS));
        
        panelSearch1 = new JPanel();
        panelSearch1.setBackground(Color.WHITE);
        panelSearch1.setBorder(new EmptyBorder(5, 0, 0, 0));
        panelSearch.add(panelSearch1);
        panelSearch1.setLayout(new BorderLayout(0, 0));
   
        JPanel paelSearch2 = new JPanel();
        paelSearch2.setBackground(Color.WHITE);
        paelSearch2.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelSearch1.add(paelSearch2, BorderLayout.EAST);
        paelSearch2.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2));
        
        JPanel panelMakm = new JPanel();
        panelMakm.setBackground(Color.WHITE);
        panelMakm.setBorder(new EmptyBorder(0, 0, 0, 10));
        paelSearch2.add(panelMakm);
        panelMakm.setLayout(new BoxLayout(panelMakm, BoxLayout.X_AXIS));
        
        JLabel lblMakm = new JLabel("Mã khuyến mãi ");
        lblMakm.setFont(new Font("Arial", Font.BOLD, 16));
        panelMakm.add(lblMakm);
        
        comboBoxMaKM = timKiemMaKhuyenMai();
        comboBoxMaKM.setEditable(true); // Cho phép nhập liệu
        comboBoxMaKM.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBoxMaKM.setPreferredSize(new Dimension(150, 25));
        panelMakm.add(comboBoxMaKM);
        
        JPanel paneTT = new JPanel();
        paneTT.setBackground(Color.WHITE);
        paneTT.setBorder(new EmptyBorder(0, 0, 0, 5));
        paelSearch2.add(paneTT);
        paneTT.setLayout(new BoxLayout(paneTT, BoxLayout.X_AXIS));
        
        JLabel lblTT = new JLabel("Trạng thái ");
        lblTT.setFont(new Font("Arial", Font.BOLD, 16));
        paneTT.add(lblTT);
        String[] items = {"","Còn", "Hết số lượng", "Hết hạn sử dụng"};
        comboBoxTrangThai = new JComboBox<>(items);
        comboBoxTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
        paneTT.add(comboBoxTrangThai);
        
        btnReset = new JButton(); 
        btnReset.setIcon(new ImageIcon(getClass().getResource("/Image/reload.png")));
        btnReset.setBorderPainted(false); 
        btnReset.setFocusPainted(false); 
        btnReset.setVerticalTextPosition(SwingConstants.CENTER);
        btnReset.setHorizontalTextPosition(SwingConstants.CENTER);
        paelSearch2.add(btnReset);
        
        btnSearch = new RoundButton("Tìm kiếm", "/Image/search.png");
        btnSearch.setBorder(new EmptyBorder(4, 10, 4, 10));
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSearch.setHeSoBoGoc(10);
        btnSearch.setKhoangCachIcon(5);
        btnSearch.setIconSize(26, 26);
        btnSearch.setVerticalTextPosition(SwingConstants.CENTER);
        btnSearch.setVerticalAlignment(SwingConstants.TOP);
        btnSearch.setHorizontalTextPosition(SwingConstants.RIGHT);
        paelSearch2.add(btnSearch);

    
        JPanel panelLblDSkm = new JPanel();
        panelLblDSkm.setBackground(Color.WHITE);
        panelLblDSkm.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelLblDSkm.setLayout(new BoxLayout(panelLblDSkm, BoxLayout.X_AXIS));
        CustomTitleLable lblDS = new CustomTitleLable("Danh sách khuyến mãi");
        lblDS.setAlignmentY(0.7f);
        panelLblDSkm.add(lblDS);
        panelSearch1.add(panelLblDSkm, BorderLayout.WEST);     
        
	}
	public JPanel getQLKhuyenMai_View() {
        return contentPane;
    }
	public JTextField getTxtTenkm() {
        return txtTenKM;
    }
	public JTextField getTxtSLKM() {
        return txtSLKM;
    }
	public JTextArea getTxtNDKM() {
        return txtNDKM;
    }
	public JDateChooser getDateKTKM() {
        return dateKTKM;
    }
	public JTable getTableKM() {
        return tableKM;
    }
	public DefaultTableModel getModelTableKM() {
        return modelTableKM;
    }
	public JComboBox<String> getComboBoxMaKM() {
        return comboBoxMaKM;
    }
	public JComboBox<String> getComboBoxTrangThai() {
        return comboBoxTrangThai;
    }
	
	public void DocDuLieuVaoTableHoaDon() {
	    List<KhuyenMai> list;
		try {
			list = km_dao.getAllKhuyenMai1();
			for (KhuyenMai km : list) {
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		        String ngayKetThucDoFormatted;
	            if (km.getHanSuDungKhuyenMai() != null) {
	                ngayKetThucDoFormatted = km.getHanSuDungKhuyenMai().format(formatter);
	            } else {
	                ngayKetThucDoFormatted = "Không xác định"; // Gán giá trị mặc định nếu null
	                // Hoặc bạn có thể để trống như: ngayKetThucDoFormatted = ""; 
	            }
		        modelTableKM.addRow(new Object[] {
		            STT++, 
		            km.getMaKhuyenMai(), 
		            km.getTenKhuyenMai(),
		            km.getNoiDungKhuyenMai(),
		            km.getSoLuongToiDa(),
		            ngayKetThucDoFormatted,
		            km.getTinhTrangKhuyenMai(),
		            
		        });
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
=======
		setSize(1250, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(Color.WHITE);

		JPanel panelTitle = new JPanel();
		panelTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
		panelTitle.setBackground(Color.WHITE);
		headerPanel.add(panelTitle, BorderLayout.NORTH);

		JLabel lblQLKM = new JLabel("Quản Lý Khuyến Mãi");
		lblQLKM.setHorizontalAlignment(SwingConstants.CENTER);
		lblQLKM.setFont(new Font("Arial", Font.BOLD, 20));
		panelTitle.add(lblQLKM);
		contentPane.add(headerPanel, BorderLayout.NORTH);

		JPanel pThemKM = new JPanel();
		pThemKM.setBackground(Color.WHITE);
		headerPanel.add(pThemKM, BorderLayout.CENTER);
		pThemKM.setBorder(
				new CompoundBorder(new LineBorder(new Color(192, 192, 192), 1, true), new EmptyBorder(10, 10, 10, 10)));
		pThemKM.setLayout(new BorderLayout(0, 0));

		JPanel panelNhap = new JPanel();
		panelNhap.setBackground(Color.WHITE);
		pThemKM.add(panelNhap, BorderLayout.CENTER);
		panelNhap.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel panelNhap1 = new JPanel();
		panelNhap1.setBackground(Color.WHITE);
		panelNhap1.setBorder(new EmptyBorder(10, 0, 0, 10));
		panelNhap.add(panelNhap1);
		panelNhap1.setLayout(new BorderLayout(0, 0));

		JPanel panelNhap2 = new JPanel();
		panelNhap2.setBackground(Color.WHITE);
		panelNhap2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNhap1.add(panelNhap2, BorderLayout.NORTH);
		panelNhap2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelTenKM = new JPanel();
		panelTenKM.setBackground(Color.WHITE);
		panelTenKM.setBorder(new EmptyBorder(0, 0, 0, 73));
		panelNhap2.add(panelTenKM);

		JLabel lblTenKM = new JLabel("Tên khuyến mãi");
		lblTenKM.setForeground(new Color(70, 130, 180));
		lblTenKM.setFont(new Font("Arial", Font.BOLD, 16));
		panelTenKM.add(lblTenKM);

		JPanel panelTxtTenkm = new JPanel();
		panelTxtTenkm.setBackground(Color.WHITE);
		panelTxtTenkm.setBorder(new EmptyBorder(0, 0, 0, 20));
		panelNhap2.add(panelTxtTenkm);

		txtTenKM = new JTextField(14);
		panelTxtTenkm.add(txtTenKM);
		txtTenKM.setFont(new Font("Arial", Font.PLAIN, 16));

		JPanel panelSL = new JPanel();
		panelSL.setBackground(Color.WHITE);
		panelNhap2.add(panelSL);

		JLabel lblSLKM = new JLabel("Số lượng khuyến mãi");
		lblSLKM.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLKM.setForeground(new Color(70, 130, 180));
		lblSLKM.setFont(new Font("Arial", Font.BOLD, 16));
		panelSL.add(lblSLKM);

		JPanel panelTxtSL = new JPanel();
		panelTxtSL.setBackground(Color.WHITE);
		panelTxtSL.setBorder(new EmptyBorder(0, 5, 0, 0));
		panelNhap2.add(panelTxtSL);
		panelTxtSL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtSLKM = new JTextField(14);
		txtSLKM.setFont(new Font("Arial", Font.PLAIN, 16));
		panelTxtSL.add(txtSLKM);

		JPanel panelNhap3 = new JPanel();
		panelNhap3.setBackground(Color.WHITE);
		panelNhap3.setBorder(new EmptyBorder(5, 5, 0, 5));
		panelNhap1.add(panelNhap3);
		panelNhap3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel panelNDkm = new JPanel();
		panelNDkm.setBackground(Color.WHITE);
		panelNDkm.setBorder(new EmptyBorder(0, 0, 80, 5));
		FlowLayout fl_panelNDkm = (FlowLayout) panelNDkm.getLayout();
		panelNhap3.add(panelNDkm);

		JLabel lblNDKM = new JLabel(" Nội dung khuyến mãi");
		lblNDKM.setForeground(new Color(70, 130, 180));
		lblNDKM.setFont(new Font("Arial", Font.BOLD, 16));
		panelNDkm.add(lblNDKM);

		JPanel panelTxtNDkm = new JPanel();
		panelTxtNDkm.setBackground(Color.WHITE);
		panelTxtNDkm.setBorder(new EmptyBorder(0, 23, 0, 0));
		panelNhap3.add(panelTxtNDkm);

		txtNDKM = new JTextArea();
		txtNDKM.setWrapStyleWord(true);
		txtNDKM.setPreferredSize(new Dimension(195, 100));
		txtNDKM.setMargin(new Insets(5, 5, 5, 5));
		txtNDKM.setLineWrap(true);
		txtNDKM.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNDKM.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panelTxtNDkm.add(txtNDKM);

		JPanel panelDate = new JPanel();
		panelDate.setBackground(Color.WHITE);
		panelDate.setBorder(new EmptyBorder(0, 0, 70, 0));
		panelNhap3.add(panelDate);

		JPanel panelDate1 = new JPanel();
		panelDate1.setBackground(Color.WHITE);
		panelDate.add(panelDate1);
		panelDate1.setLayout(new BoxLayout(panelDate1, BoxLayout.X_AXIS));

		JPanel panelDate3 = new JPanel();
		panelDate3.setBackground(Color.WHITE);
		panelDate3.setBorder(new EmptyBorder(0, 20, 0, 10));
		panelDate1.add(panelDate3);

		JLabel lblDateKT = new JLabel("Ngày kết thúc");
		lblDateKT.setForeground(new Color(70, 130, 180));
		lblDateKT.setFont(new Font("Arial", Font.BOLD, 16));
		panelDate3.add(lblDateKT);
		dateKTKM = new JDateChooser();
		dateKTKM.setBackground(Color.WHITE);
		dateKTKM.getCalendarButton().setBackground(new Color(235, 235, 235));
		dateKTKM.getCalendarButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		dateKTKM.setBorder(new EmptyBorder(0, 61, 0, 0));
		dateKTKM.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		dateKTKM.setFont(new Font("Arial", Font.PLAIN, 16));
		dateKTKM.setDateFormatString("dd-MM-yyyy");
		dateKTKM.setPreferredSize(new Dimension(250, 25));
		iconlich = new ImageIcon(getClass().getResource("/Image/icon_lich.png"));
		dateKTKM.setIcon(new ImageIcon(iconlich.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH)));
		panelDate1.add(dateKTKM);

		JPanel panelNut = new JPanel();
		panelNut.setBackground(Color.WHITE);
		pThemKM.add(panelNut, BorderLayout.EAST);
		panelNut.setLayout(new BoxLayout(panelNut, BoxLayout.Y_AXIS));

		panelNut.setBorder(
				new CompoundBorder(new LineBorder(new Color(192, 192, 192), 1, true), new EmptyBorder(0, 10, 0, 10)));

		btnThem = new RoundButton("Thêm khuyến mãi    ", "/Image/plus.png");
		btnThem.setColor_1(new Color(149, 149, 255));
		btnThem.setColor_2(ColorConstants.HOVER_COLOR);
		btnThem.setHeSoBoGoc(15);
		btnThem.setFont(new Font("Arial", Font.BOLD, 16));
		btnThem.setIconSize(32, 32);

		btnHuy = new RoundButton("Huỷ bỏ     ", "/Image/cancel.png");
		btnHuy.setColor_1(new Color(255, 128, 0));
		btnHuy.setColor_2(new Color(240, 128, 0));
		btnHuy.setHeSoBoGoc(15); // Độ bo góc
		btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
		btnHuy.setIconSize(32, 32);

		panelNut.add(Box.createVerticalGlue());
		JPanel panelBtnThem = new JPanel();
		panelBtnThem.setBackground(Color.WHITE);
		panelNut.add(panelBtnThem);
		panelBtnThem.setLayout(new BorderLayout(0, 0));
		panelBtnThem.add(btnThem, BorderLayout.SOUTH);
		panelNut.add(Box.createVerticalStrut(20));

		JPanel panelBtnHuy = new JPanel();
		panelBtnHuy.setBackground(Color.WHITE);
		panelNut.add(panelBtnHuy);
		panelBtnHuy.setLayout(new BorderLayout(0, 0));
		panelBtnHuy.add(btnHuy, BorderLayout.NORTH);
		panelNut.add(Box.createVerticalGlue());

		String[] header = { "STT", "Mã khuyến mãi", "Tên khuyến mãi", "Nội dung", "Số lượng", "Ngày kết thúc",
				"Tình trạng" };
		Font headerFont = new Font("Arial", Font.BOLD, 18);
		modelTableKM = new DefaultTableModel(header, 0);

		tableKM = new JTable(modelTableKM);
		tableKM.setShowGrid(true);
		tableKM.setGridColor(new Color(225, 225, 225));
		tableKM.getTableHeader().setFont(headerFont);
		tableKM.setFont(new Font("Arial", Font.PLAIN, 16));
		tablePanel = new JScrollPane(tableKM);
		tableKM.getColumnModel().getColumn(0).setPreferredWidth(5);
		DocDuLieuVaoTableHoaDon();

		JPanel panelSearchAndTable = new JPanel();
		panelSearchAndTable.setBorder(new EmptyBorder(15, 0, 0, 0));
		panelSearchAndTable.setBackground(Color.WHITE);
		contentPane.add(panelSearchAndTable, BorderLayout.CENTER);
		panelSearchAndTable.setLayout(new BorderLayout(0, 0));

		JPanel panelSearch = new JPanel();
		panelSearchAndTable.add(panelSearch, BorderLayout.NORTH);
		tableKM.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableKM.setRowHeight(30);
		panelSearchAndTable.add(new JScrollPane(tableKM));
		panelSearch.setLayout(new BoxLayout(panelSearch, BoxLayout.X_AXIS));

		panelSearch1 = new JPanel();
		panelSearch1.setBackground(Color.WHITE);
		panelSearch1.setBorder(new EmptyBorder(5, 0, 0, 0));
		panelSearch.add(panelSearch1);
		panelSearch1.setLayout(new BorderLayout(0, 0));

		JPanel paelSearch2 = new JPanel();
		paelSearch2.setBackground(Color.WHITE);
		paelSearch2.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelSearch1.add(paelSearch2, BorderLayout.EAST);
		paelSearch2.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2));

		JPanel panelMakm = new JPanel();
		panelMakm.setBackground(Color.WHITE);
		panelMakm.setBorder(new EmptyBorder(0, 0, 0, 10));
		paelSearch2.add(panelMakm);
		panelMakm.setLayout(new BoxLayout(panelMakm, BoxLayout.X_AXIS));

		JLabel lblMakm = new JLabel("Mã khuyến mãi ");
		lblMakm.setFont(new Font("Arial", Font.BOLD, 16));
		panelMakm.add(lblMakm);

		comboBoxMaKM = timKiemMaKhuyenMai();
		comboBoxMaKM.setEditable(true); // Cho phép nhập liệu
		comboBoxMaKM.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxMaKM.setPreferredSize(new Dimension(150, 25));
		panelMakm.add(comboBoxMaKM);

		JPanel paneTT = new JPanel();
		paneTT.setBackground(Color.WHITE);
		paneTT.setBorder(new EmptyBorder(0, 0, 0, 5));
		paelSearch2.add(paneTT);
		paneTT.setLayout(new BoxLayout(paneTT, BoxLayout.X_AXIS));

		JLabel lblTT = new JLabel("Trạng thái ");
		lblTT.setFont(new Font("Arial", Font.BOLD, 16));
		paneTT.add(lblTT);
		String[] items = { "", "Còn", "Hết số lượng", "Hết hạn sử dụng" };
		comboBoxTrangThai = new JComboBox<>(items);
		comboBoxTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
		paneTT.add(comboBoxTrangThai);

		btnReset = new JButton();
		btnReset.setIcon(new ImageIcon(getClass().getResource("/Image/reload.png")));
		btnReset.setBorderPainted(false);
		btnReset.setFocusPainted(false);
		btnReset.setVerticalTextPosition(SwingConstants.CENTER);
		btnReset.setHorizontalTextPosition(SwingConstants.CENTER);
		paelSearch2.add(btnReset);

		btnSearch = new RoundButton("Tìm kiếm", "/Image/search.png");
		btnSearch.setBorder(new EmptyBorder(4, 10, 4, 10));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearch.setHeSoBoGoc(10);
		btnSearch.setKhoangCachIcon(5);
		btnSearch.setIconSize(26, 26);
		btnSearch.setVerticalTextPosition(SwingConstants.CENTER);
		btnSearch.setVerticalAlignment(SwingConstants.TOP);
		btnSearch.setHorizontalTextPosition(SwingConstants.RIGHT);
		paelSearch2.add(btnSearch);

		JPanel panelLblDSkm = new JPanel();
		panelLblDSkm.setBackground(Color.WHITE);
		panelLblDSkm.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelLblDSkm.setLayout(new BoxLayout(panelLblDSkm, BoxLayout.X_AXIS));
		CustomTitleLable lblDS = new CustomTitleLable("Danh sách khuyến mãi");
		lblDS.setAlignmentY(0.7f);
		panelLblDSkm.add(lblDS);
		panelSearch1.add(panelLblDSkm, BorderLayout.WEST);

	}

	public JPanel getQLKhuyenMai_View() {
		return contentPane;
	}

	public JTextField getTxtTenkm() {
		return txtTenKM;
	}

	public JTextField getTxtSLKM() {
		return txtSLKM;
	}

	public JTextArea getTxtNDKM() {
		return txtNDKM;
	}

	public JDateChooser getDateKTKM() {
		return dateKTKM;
	}

	public JTable getTableKM() {
		return tableKM;
	}

	public DefaultTableModel getModelTableKM() {
		return modelTableKM;
	}

	public JComboBox<String> getComboBoxMaKM() {
		return comboBoxMaKM;
	}

	public JComboBox<String> getComboBoxTrangThai() {
		return comboBoxTrangThai;
	}

	public void DocDuLieuVaoTableHoaDon() {
		List<KhuyenMai> list;
		try {
			list = km_dao.getAllKhuyenMai1();
			for (KhuyenMai km : list) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String ngayKetThucDoFormatted;
				if (km.getHanSuDungKhuyenMai() != null) {
					ngayKetThucDoFormatted = km.getHanSuDungKhuyenMai().format(formatter);
				} else {
					ngayKetThucDoFormatted = "Không xác định"; // Gán giá trị mặc định nếu null
					// Hoặc bạn có thể để trống như: ngayKetThucDoFormatted = "";
				}
				modelTableKM.addRow(
						new Object[] { STT++, km.getMaKhuyenMai(), km.getTenKhuyenMai(), km.getNoiDungKhuyenMai(),
								km.getSoLuongToiDa(), ngayKetThucDoFormatted, km.getTinhTrangKhuyenMai(),

						});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
>>>>>>> Stashed changes
	}
}
