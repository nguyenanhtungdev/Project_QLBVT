	package controller;
	
	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.ChiTiet_HoaDon;
import model.ChiTiet_HoaDon_DAO;
import model.ChuyenTau;
import model.GheTau;
import model.GheTau_DAO;
import model.GiaVe;
import model.HoaDon;
import model.HoaDon_DAO;
import model.KhachHang;
import model.KhachHang.LoaiKhachHang;
import model.KhuyenMai;
import model.KhuyenMai_DAO;
import model.Tau;
import model.Tau.TrangThaiTau;
import model.Tau_DAO;
import model.ThongTinTram;
import model.TinhTrangKhuyenMai;
import model.ToaTau;
import model.ToaTau_DAO;
import model.VeTau;
import other.ColorConstants;
import other.CustomTitleLable;
import other.CustomTrainStatusButton;
import other.RoundedPanel;
import other.SeatButton;
import view.QLHoaDon_view;
import view.QLKhuyenMai_View;
import view.QLTau_View;
	
	public class QuanLy_Controller {		
		private HoaDon_DAO hoaDon_DAO;
		private ChiTiet_HoaDon_DAO ctHD_DAO;
		private Tau_DAO tau_DAO;
		private ToaTau_DAO toaTau_DAO; 
		private GheTau_DAO gheTau_DAO;
		private KhuyenMai_DAO kMai_DAO;
	    private static final int ITEMS_PER_PAGE = 5;
	    private int currentIndex = 0;
	    private QLTau_View qLTau_View;
	    private QLHoaDon_view qLHoaDon_view;
	    private QLKhuyenMai_View qLKhuyenMai_View;
	    private int soTau;
	    private int soTrang = 1;
	    private ArrayList<Tau> danhSachTau;
	    private int sttT = 1;
	    private int sttTT = 1;
	    private int sttGT = 1;
	    private int sttHD = 1;
	    
	    private CustomTrainStatusButton selectedButton;
	    
	    //QL_Tau
	    public QuanLy_Controller(QLTau_View qLTau_View) throws SQLException {
	        this.qLTau_View = qLTau_View;
	        this.gheTau_DAO = new GheTau_DAO();
	        this.toaTau_DAO = new ToaTau_DAO();
	        this.tau_DAO = new Tau_DAO();
	        this.danhSachTau = tau_DAO.getAllTau();
	        this.soTau = danhSachTau.size();
	        initControllerTau();
	    }
	    private void initControllerTau() throws SQLException {
	        themSuKien();
	        updateTrainPanel(qLTau_View.trainContainer);
	        qLTau_View.getLblSoTrang().setText("trang: " + soTrang);
	        qLTau_View.getLblSoTau().setText("Tổng số tàu: " + soTau);
	    }
	    
	    private void themSuKien() {
	    	qLTau_View.addNextButtonListener(e -> nextPage());
	    	qLTau_View.addPrevButtonListener(e -> prevPage());
	    	qLTau_View.addButtonSearchListener(e -> searchTau());
	    	qLTau_View.addButtonReloadListener(e -> reloadTau());	    	
	    }
	    
	    //QL_KhuyenMai
	    public QuanLy_Controller(QLKhuyenMai_View qLKhuyenMai_View) {
	    	this.qLKhuyenMai_View = qLKhuyenMai_View;
	    	this.kMai_DAO = new KhuyenMai_DAO();
	    	initControllerKM();
		}

		private void initControllerKM() {
			themSuKienKM();
			
		}
		private void themSuKienKM() {
			qLKhuyenMai_View.addButtonHuyListener(e -> huyTxtKM());
			qLKhuyenMai_View.addButtonThemListener(e -> ThemKM());
			qLKhuyenMai_View.addButtonTimListener(e -> searchKM());
			qLKhuyenMai_View.addButtonReloadListener(e -> reLoadSearchKM());
		}
	    
		
		//Khuyen_Mai
		private void ThemKM() {
		    if (validDataKMai()) { 
		        String tenKhuyenMai = qLKhuyenMai_View.getTxtTenkm().getText().trim();
		        String noiDungKhuyenMai = qLKhuyenMai_View.getTxtNDKM().getText().trim();
		        int soLuongToiDa = Integer.parseInt(qLKhuyenMai_View.getTxtSLKM().getText().trim());
		        Date dateHanSuDung = qLKhuyenMai_View.getDateKTKM().getDate(); 
		        LocalDateTime hanSuDung = dateHanSuDung != null ? 
		        		LocalDateTime.ofInstant(dateHanSuDung.toInstant(), ZoneId.systemDefault()).withHour(0).withMinute(0).withSecond(0).withNano(0) : null;
		        String maKhuyenMaiCu;
				try {
					maKhuyenMaiCu = kMai_DAO.getMaxMaKhuyenMai();
					String maKhuyenMai = generateNextMaKhuyenMai(maKhuyenMaiCu); 
			        KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDung, TinhTrangKhuyenMai.CON);

			        boolean result = kMai_DAO.themKhuyenMai1(khuyenMai); 

			        if (result) {
			            themKhuyenMaiVaoBang(khuyenMai); 
			            JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Thêm khuyến mãi thành công!");
			            huyTxtKM();
			        } else {
			            JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Thêm khuyến mãi không thành công!");
			        }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		    }
		}
		private String generateNextMaKhuyenMai(String maKhuyenMaiCu) {
		    if (maKhuyenMaiCu == null || maKhuyenMaiCu.isEmpty()) {
		        return "KM0000";
		    }
		    String currentNumberStr = maKhuyenMaiCu.substring(2); 
		    int currentNumber = Integer.parseInt(currentNumberStr); 
		    currentNumber++; 
		    return String.format("KM%04d", currentNumber); 
		}

		private boolean validDataKMai() {
		    String tenKhuyenMai = qLKhuyenMai_View.getTxtTenkm().getText().trim();
		    String soLuongToiDaStr = qLKhuyenMai_View.getTxtSLKM().getText().trim();
		    String noiDungKhuyenMai = qLKhuyenMai_View.getTxtNDKM().getText().trim();
		    Date dateHanSuDung = qLKhuyenMai_View.getDateKTKM().getDate();
		    LocalDateTime hanSuDung = dateHanSuDung != null ? 
		    		LocalDateTime.ofInstant(dateHanSuDung.toInstant(), ZoneId.systemDefault()).withHour(0).withMinute(0).withSecond(0).withNano(0) : null;

		    if (tenKhuyenMai.isEmpty()) {
		        JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Vui lòng nhập tên khuyến mãi!");
		        return false;
		    }
		    if (soLuongToiDaStr.isEmpty()) {
		        JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Vui lòng nhập số lượng tối đa!");
		        return false;
		    }		    
		    int soLuongToiDa;
		    try {
		        soLuongToiDa = Integer.parseInt(soLuongToiDaStr);
		        if (soLuongToiDa < 0) {
		            JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Số lượng tối đa không được là số âm!");
		            return false;
		        }
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Số lượng tối đa phải là số nguyên hợp lệ!");
		        return false;
		    }
		    if (noiDungKhuyenMai.isEmpty()) {
		        JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Vui lòng nhập nội dung khuyến mãi!");
		        return false;
		    }
		    if (hanSuDung == null || !hanSuDung.isAfter(LocalDateTime.now())) {
		        JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Hạn sử dụng khuyến mãi phải sau thời gian hiện tại!");
		        return false;
		    }
		    return true; 
		}


		private void reLoadSearchKM() {
	    	xoaDuLieuTableKM();
	    	List<KhuyenMai> kmList;
			try {
				kmList = kMai_DAO.getAllKhuyenMai1();
				for (KhuyenMai km : kmList) {
		    		themKhuyenMaiVaoBang(km);
		    	}
			} catch (SQLException e) {
				e.printStackTrace();
			}       	
			qLKhuyenMai_View.getComboBoxMaKM().setSelectedItem(null);
			qLKhuyenMai_View.getComboBoxTrangThai().setSelectedItem(null);
		}
		private void searchKM() {
		    String maKM = (String) qLKhuyenMai_View.getComboBoxMaKM().getSelectedItem();
		    String trangThai = (String) qLKhuyenMai_View.getComboBoxTrangThai().getSelectedItem();
		    if ((maKM == null || maKM.isEmpty()) && (trangThai == null || trangThai.isEmpty())) {
		        JOptionPane.showMessageDialog(null, "Vui lòng nhập (chọn) mã khuyến mãi hoặc trạng thái!");
		        return;
		    }

		    try {
		        List<KhuyenMai> danhSachKM = new ArrayList<>();
		        if (maKM != null && !maKM.isEmpty()) {
		            KhuyenMai km = kMai_DAO.getKhuyenMai1(maKM);
		            if (km != null) {
		                danhSachKM.add(km);
		            } else {
		                JOptionPane.showMessageDialog(null, "Không tìm thấy khuyến mãi với mã " + maKM + "!");
		            }
		        }

		        if (trangThai != null && !trangThai.isEmpty()) {
		            TinhTrangKhuyenMai tinhTrangEnum = convertStringToTinhTrang(trangThai);
		            List<KhuyenMai> danhSachKMTheoTrangThai = kMai_DAO.getKhuyenMaiTheoTrangThai(tinhTrangEnum);
		            if (!danhSachKMTheoTrangThai.isEmpty()) {
		                danhSachKM.addAll(danhSachKMTheoTrangThai);
		            } else {
		                JOptionPane.showMessageDialog(null, "Không tìm thấy khuyến mãi với trạng thái " + trangThai + "!");
		            }
		        }

		        danhSachKM = danhSachKM.stream().distinct().collect(Collectors.toList());
		        xoaDuLieuTableKM();
		        for (KhuyenMai km : danhSachKM) {
		            themKhuyenMaiVaoBang(km);
		        }

		    } catch (SQLException e1) {
		        e1.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ!");
		    }
		}

		private TinhTrangKhuyenMai convertStringToTinhTrang(String trangThai) {
		    switch (trangThai) {
		        case "Còn":
		            return TinhTrangKhuyenMai.CON;
		        case "Hết số lượng":
		            return TinhTrangKhuyenMai.HET_SO_LUONG;
		        case "Hết hạn sử dụng":
		            return TinhTrangKhuyenMai.HET_HAN_SU_DUNG;
		        default:
		            throw new IllegalArgumentException("Trạng thái không hợp lệ: " + trangThai);
		    }
		}
		private void themKhuyenMaiVaoBang(KhuyenMai kMai) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String ngayKetThucDoFormatted;
			if (kMai.getHanSuDungKhuyenMai() != null) {
                ngayKetThucDoFormatted = kMai.getHanSuDungKhuyenMai().format(formatter);
            } else {
                ngayKetThucDoFormatted = "";
            }
		    qLKhuyenMai_View.getModelTableKM().addRow(new Object[] {
		    	qLKhuyenMai_View.getModelTableKM().getRowCount() + 1,
		    	kMai.getMaKhuyenMai(), 
	            kMai.getTenKhuyenMai(),
	            kMai.getNoiDungKhuyenMai(),
	            kMai.getSoLuongToiDa(),
	            ngayKetThucDoFormatted,
	            kMai.getTinhTrangKhuyenMai(),	            
		    });
			
		}
		private void xoaDuLieuTableKM() {
			DefaultTableModel dm = (DefaultTableModel) qLKhuyenMai_View.getTableKM().getModel();
			dm.getDataVector().removeAllElements();	
		}
	
		private void huyTxtKM() {
			qLKhuyenMai_View.getTxtTenkm().setText("");
			qLKhuyenMai_View.getTxtSLKM().setText("");
			qLKhuyenMai_View.getTxtNDKM().setText("");
			qLKhuyenMai_View.getDateKTKM().setDate(null);
			qLKhuyenMai_View.getTxtTenkm().requestFocus();
		}
		
		
		//QL_HoaDon
	    public QuanLy_Controller(QLHoaDon_view qlHoaDon_view) {
	    	this.qLHoaDon_view = qlHoaDon_view;
	    	this.hoaDon_DAO = new HoaDon_DAO();
	    	this.ctHD_DAO = new ChiTiet_HoaDon_DAO();
	    	initControllerHD();
		}

		private void initControllerHD() {
			themSuKienHD();
			
		}
		private void themSuKienHD() {
			qLHoaDon_view.addButtonReloadListener(e -> reloadHoaDon());	
			qLHoaDon_view.addButtonMaHDItem(e -> locTheoMaHD());
			qLHoaDon_view.addButtonDateItem(e -> locTheoDate());
			qLHoaDon_view.addButtonSDTItem(e -> locTheoSDT());
			qLHoaDon_view.addButtonXemHDCT(e -> xemHDCT());
		}
		
		////// HoaDon_view
		private void locTheoMaHD() {
			qLHoaDon_view.getCombSDT().setSelectedItem(null);
			qLHoaDon_view.getDateBD().setDate(null); 
		    JTextField dateField = (JTextField) qLHoaDon_view.getDateBD().getDateEditor().getUiComponent();
		    dateField.setText("Tạo từ ngày"); 
		    dateField.setForeground(Color.GRAY);

		    qLHoaDon_view.getDateKT().setDate(null);
		    JTextField dateField1 = (JTextField) qLHoaDon_view.getDateKT().getDateEditor().getUiComponent();
		    dateField1.setText("Đến ngày"); 
		    dateField1.setForeground(Color.GRAY); 
			String maHD = (String) qLHoaDon_view.getCombMaHD().getSelectedItem();
			
		    if (maHD == null || maHD.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Vui lòng chọn mã hóa đơn!");
		        return;
		    }

		    boolean found = false;
			HoaDon hoaDon = hoaDon_DAO.layTTHoaDonTheoMa(maHD);
			if (hoaDon != null) {
			    xoaDuLieuTableHoaDon(); 
			    themHoaDonVaoBang(hoaDon); 
			    found = true;
			} else {
			    JOptionPane.showMessageDialog(null, "Không tìm thấy Hóa đơn với mã " + maHD + "!");
			}

		    if (!found) {
		        JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn nào!");
		    }
		}
		
	    private void locTheoSDT() {
	    	qLHoaDon_view.getCombMaHD().setSelectedItem(null);
	    	qLHoaDon_view.getDateBD().setDate(null); 
		    JTextField dateField = (JTextField) qLHoaDon_view.getDateBD().getDateEditor().getUiComponent();
		    dateField.setText("Tạo từ ngày"); 
		    dateField.setForeground(Color.GRAY);

		    qLHoaDon_view.getDateKT().setDate(null);
		    JTextField dateField1 = (JTextField) qLHoaDon_view.getDateKT().getDateEditor().getUiComponent();
		    dateField1.setText("Đến ngày"); 
		    dateField1.setForeground(Color.GRAY); 
	    	String soDT = (String) qLHoaDon_view.getCombSDT().getSelectedItem(); 

	        if (soDT == null || soDT.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn số điện thoại để lọc!");
	            return;
	        }

	        List<HoaDon> hoaDons = hoaDon_DAO.layTTHoaDonTheoSDT(soDT); 
			xoaDuLieuTableHoaDon(); 
			for (HoaDon hoaDon : hoaDons) {
			    themHoaDonVaoBang(hoaDon); 
			}
			if (hoaDons.isEmpty()) {
			    JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn nào với số điện thoại " + soDT + "!");
			}
		}

	    private void locTheoDate() {
	    	qLHoaDon_view.getCombMaHD().setSelectedItem(null);
	        qLHoaDon_view.getCombSDT().setSelectedItem(null);
	        Date startDate = qLHoaDon_view.getDateBD().getDate(); 
	        Date endDate = qLHoaDon_view.getDateKT().getDate(); 
	        if (startDate == null || endDate == null) {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn khoảng thời gian để lọc!");
	            return;
	        }
	        if (startDate.after(endDate)) {
	            JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể lớn hơn ngày kết thúc!");
	            return;
	        }
	        try {
	        	LocalDateTime sqlStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	            LocalDateTime sqlEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	            List<HoaDon> hoaDons = hoaDon_DAO.layTTHoaDonTheoDate(sqlStartDate, sqlEndDate); 
	            xoaDuLieuTableHoaDon(); 
	            for (HoaDon hoaDon : hoaDons) {
	                themHoaDonVaoBang(hoaDon); 
	            }
	            if (hoaDons.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn nào trong khoảng thời gian đã chọn!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ!");
	        }
	    }
	    
		private void themHoaDonVaoBang(HoaDon hd) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    	String ngayLapHoaDoFormatted = hd.getNgayLapHoaDon().format(formatter);
		    qLHoaDon_view.getModelHD().addRow(new Object[] {    	
		    	qLHoaDon_view.getModelHD().getRowCount() + 1, // STT
	            hd.getMaHoaDon(), 
	            hd.getLoaiHoaDon(),
	            hd.getKhachHang().getHoTen(),
	            hd.getKhachHang().getSoDienThoai(),
	            ngayLapHoaDoFormatted,
	            hd.getThueVAT(),
		    });
		}

	    public void reloadHoaDon() {
	    	xoaDuLieuTableHoaDon();
	        sttHD = 1;
	        DocDuLieuVaoTableHoaDon();
	        qLHoaDon_view.getCombMaHD().setSelectedItem(null);
	        qLHoaDon_view.getCombSDT().setSelectedItem(null);

	        qLHoaDon_view.getDateBD().setDate(null); 
	        JTextField dateField = (JTextField) qLHoaDon_view.getDateBD().getDateEditor().getUiComponent();
	        dateField.setText("Tạo từ ngày"); 
	        dateField.setForeground(Color.GRAY);

	        qLHoaDon_view.getDateKT().setDate(null);
	        JTextField dateField1 = (JTextField) qLHoaDon_view.getDateKT().getDateEditor().getUiComponent();
	        dateField1.setText("Đến ngày"); // Đặt lại placeholder
	        dateField1.setForeground(Color.GRAY); // Đặt màu chữ thành xám
	    }
	    
		private void xoaDuLieuTableHoaDon() {
			DefaultTableModel dm = (DefaultTableModel) qLHoaDon_view.getTableHoaDon().getModel();
			dm.getDataVector().removeAllElements();	
		}

		
	    public void DocDuLieuVaoTableHoaDon() {
		    List<HoaDon> list = hoaDon_DAO.getalltbHDKH();
		    for (HoaDon hd : list) {
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		        String ngayLapHoaDoFormatted = hd.getNgayLapHoaDon().format(formatter);
		       
		        qLHoaDon_view.getModelHD().addRow(new Object[] {
		            sttHD++, 
		            hd.getMaHoaDon(), 
		            hd.getLoaiHoaDon(),
		            hd.getKhachHang().getHoTen(),
		            hd.getKhachHang().getSoDienThoai(),
		            ngayLapHoaDoFormatted,
		            hd.getThueVAT(),
		            
		        });
		    }
		}	    
	    private boolean isButtonClicked = false;
	    private void xemHDCT() {
	    	 qLHoaDon_view.getTableHoaDon().addMouseListener(new MouseAdapter() {
	    		 private JPanel panelLogo;

			        @Override
			        public void mouseClicked(MouseEvent e) {
			            int row = qLHoaDon_view.getTableHoaDon().getSelectedRow();
			            if (row != -1) { 
			            	if (!isButtonClicked) {
			            		isButtonClicked = true;
			                    String maHD = (String) qLHoaDon_view.getModelHD().getValueAt(row, 1);
//			                	private JPanel panelLogo;
			                    JLabel lbl_Icon;
			                    ImageIcon iconLogo;
			                    JPanel panelTrong1;
			                    JPanel panelChiTiet;
			                    DefaultTableModel modelTableHDCT;
			                    JTable tableHDCT;
			                    JPanel panelTableAndTotal;
			                    JPanel panelTableHDCT;
			                    JPanel panelTotal;
			                    JPanel panelTongtien;
			                    JPanel panelTongTien;
			                    JLabel lblTongTien;
			                    JPanel panelThueVAT;
			                    JLabel lblThueVAT;
			                    JPanel panelTongTienTT;
			                    JLabel lblTongTienTT;
							    HoaDon hoaDon = hoaDon_DAO.layTTHoaDonTheoMa(maHD); 
							    ThongTinTram ttTram = hoaDon_DAO.layThongTinTramTheoMa(maHD);
							    KhachHang kh = hoaDon_DAO.layKhachHangTheoMa(hoaDon.getKhachHang().getMaKhachHang());
							    ArrayList<ChiTiet_HoaDon> ctHD = ctHD_DAO.layChiTietHDTheoMaHD(maHD);

							    LocalDateTime ngayLapHoaDon = hoaDon.getNgayLapHoaDon();
							    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Ngày dd tháng MM năm yyyy, HH giờ mm phút ss giây");
							    String formattedDate = ngayLapHoaDon.format(formatter);
							
								panelChiTiet = new JPanel();
						        panelChiTiet.setBackground(Color.WHITE);
					        
						        panelChiTiet.setLayout(new BorderLayout());
						        panelChiTiet.setBackground(Color.WHITE);
						        panelChiTiet.setBorder(new EmptyBorder(20, 20, 20, 20));
								
						    	JPanel headerPanelCT = new JPanel(new BorderLayout());
						        headerPanelCT.setBorder(new EmptyBorder(0, 0, 25, 0));
						        headerPanelCT.setBackground(Color.WHITE);
						        
						        panelLogo = new JPanel();
						        panelLogo.setBackground(Color.WHITE);
						        
						        lbl_Icon = new JLabel("");
						        iconLogo = new ImageIcon(getClass().getResource("/Image/logo1.png"));
						        lbl_Icon.setPreferredSize(new Dimension(250, 70)); 
						        lbl_Icon.setMaximumSize(new Dimension(250, 70));
						        lbl_Icon.setIcon(new ImageIcon(iconLogo.getImage().getScaledInstance(130, 70, Image.SCALE_SMOOTH))); 
						       
						        RoundedPanel panelChuaLogo = new RoundedPanel(20);
						        panelChuaLogo.setPreferredSize(new Dimension(265, 65)); 
						        panelChuaLogo.setBackground(new Color(70, 130, 169));
						        panelChuaLogo.setBorder(new EmptyBorder(5, 5, 5, 5)); 
						        panelChuaLogo.setLayout(new BoxLayout(panelChuaLogo, BoxLayout.X_AXIS));

						        lbl_Icon.setIcon(new ImageIcon(iconLogo.getImage().getScaledInstance(270, 80, Image.SCALE_SMOOTH)));
						        panelChuaLogo.add(lbl_Icon);

						        panelLogo.add(panelChuaLogo);
						        headerPanelCT.add(panelLogo, BorderLayout.WEST);

						        JLabel titleLabel = new JLabel("HÓA ĐƠN GIÁ TRỊ GIA TĂNG", JLabel.CENTER);
						        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
						        titleLabel.setForeground(Color.BLUE);

						        JLabel dateLabel = new JLabel(formattedDate, JLabel.CENTER);
						        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));

						        JPanel titlePanel = new JPanel(new BorderLayout());
						        titlePanel.setBackground(Color.WHITE);
						        titlePanel.add(titleLabel, BorderLayout.CENTER);
						        titlePanel.add(dateLabel, BorderLayout.SOUTH);
						        headerPanelCT.add(titlePanel, BorderLayout.CENTER);

						        JPanel codePanel = new JPanel();
						        codePanel.setBackground(Color.WHITE);
						        JLabel maHoaDonLabel = new JLabel("Mã hóa đơn: " + hoaDon.getMaHoaDon());
						        maHoaDonLabel.setFont(new Font("Arial", Font.PLAIN, 16));
						        maHoaDonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						        codePanel.setLayout(new BoxLayout(codePanel, BoxLayout.Y_AXIS));			        
						        panelTrong1 = new JPanel();
						        panelTrong1.setBackground(Color.WHITE);
						        codePanel.add(panelTrong1);
						        codePanel.add(maHoaDonLabel);
						        
						        headerPanelCT.add(codePanel, BorderLayout.EAST);			        
						        panelChiTiet.add(headerPanelCT, BorderLayout.NORTH);
						        
						     // Content Panel
						        JPanel contentPanel = new JPanel();
						        contentPanel.setBorder(new EmptyBorder(0, 0, 25, 0));
						        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
						        contentPanel.setBackground(Color.WHITE);

						        contentPanel.add(createDetailRow("Đơn vị bán hàng: ", ttTram.getTenNhaGa()));
						        contentPanel.add(Box.createVerticalStrut(10));
						        contentPanel.add(createDetailRow("Mã số thuế: ", ttTram.getMaSoThue()));
						        contentPanel.add(Box.createVerticalStrut(10)); 
						        contentPanel.add(createDetailRow("Địa chỉ: ", ttTram.getDiaChi()));
						        contentPanel.add(Box.createVerticalStrut(10)); 
						        contentPanel.add(createDetailRow("Số tài khoản: ", ttTram.getSoTaiKhoản()));
						        contentPanel.add(Box.createVerticalStrut(10));
						        contentPanel.add(createDetailRow("Tên ngân hàng: ", ttTram.getTenNganHang()));
						        contentPanel.add(Box.createVerticalStrut(30)); 

						        contentPanel.add(createDetailRow("Họ và tên khách hàng: ", kh.getHoTen()));
						        contentPanel.add(Box.createVerticalStrut(10)); 
						        contentPanel.add(createDetailRow("Số điện thoại: ", kh.getSoDienThoai()));
						        contentPanel.add(Box.createVerticalStrut(10));
						        contentPanel.add(createDetailRow("Email: ", kh.getEmail()));
						        contentPanel.add(Box.createVerticalStrut(10));
						        contentPanel.add(createDetailRow("Loại khách hàng: ", formatLoaiKhachHang(kh.getLoaiKH())));
						        contentPanel.add(Box.createVerticalStrut(10)); 
						        contentPanel.add(createDetailRow("Hình thức thanh toán: ", hoaDon.getPhuongThucThanhToan()));
						        contentPanel.add(Box.createVerticalStrut(10)); 
						        contentPanel.add(createDetailRow("Số tài khoản: ", ""));
						        contentPanel.add(Box.createVerticalStrut(10)); 
						        contentPanel.add(createDetailRow("Tên ngân hàng: ", ""));
						        contentPanel.add(Box.createVerticalStrut(10));
						        contentPanel.add(createDetailRow("Tên ngân hàng viết tắt: ", ""));

						        // Thêm contentPanel vào panelChiTiet
						        panelChiTiet.add(contentPanel, BorderLayout.CENTER);
						        
						        String[] headerCT = {"STT","Mã vé","Loại vé","Số lượng", "Đơn giá", "Thành tiền trước thuế","Giảm giá","Thuế suất","Tổng tiền sau thuế"};
						        String[] headerCT1 = {"","a","b","1", "2", "3=1x2","4","5","6=(3-4)+5"};
						        Font headerFontCT = new Font("Arial", Font.BOLD, 16);
						        modelTableHDCT = new DefaultTableModel(new Object[][]{headerCT1}, headerCT);
//						     // Thêm 9 dòng trống vào bảng
//						        for (int i = 1; i <= 9; i++) {
//						            modelTableHDCT.addRow(new Object[]{i, "", "", "", "", "", "", "", ""});
//						        }
						        if (hoaDon == null || ttTram == null || kh == null) {
						            JOptionPane.showMessageDialog(null, "Error retrieving invoice data.");
						            return;
						        }
						       
						        // Cập nhật bảng
						        tableHDCT = new JTable(modelTableHDCT);
						        for (int i = 0; i < ctHD.size(); i++) {
						            ChiTiet_HoaDon chiTiet = ctHD.get(i);
						            
						            // Lấy thông tin vé tàu cho chi tiết hóa đơn
						            VeTau vt = ctHD_DAO.layTTveTauTheoMaVeTau(chiTiet.getVeTau().getMaVeTau());
						            ChuyenTau ct = ctHD_DAO.layChuyenTauTheoMaChuyenTau(vt.getChuyenTau().getMaChuyenTau());
						            GiaVe gv = ctHD_DAO.layGiaVeTheoMaGiaVe(ct.getGiaVe().getMaGiaVe());
						            
						            // Tính toán thành tiền trước thuế, giảm giá, thuế suất, và tổng tiền sau thuế
						            double donGia = gv.getGiaVe();
						            int soLuong = chiTiet.getSoLuong();
						            double thanhTienTruocThue = donGia * soLuong;
						            double giamGia = thanhTienTruocThue * (kh.getLoaiKH().getDiscount());
						            double thueSuat = 0.1; // Ví dụ thuế suất 10%
						            double tongTienSauThue = thanhTienTruocThue - giamGia + (thanhTienTruocThue * thueSuat);
						            
						            // Thêm dòng vào bảng
						            modelTableHDCT.addRow(new Object[]{
						                i + 1,  // STT
						                vt.getMaVeTau(),
						                vt.isLoaiVe() ? "Loại 1" : "Loại 2", 
						                soLuong, 
						                donGia, 
						                thanhTienTruocThue, 
						                thueSuat, 
						                tongTienSauThue 
						            });
						        }
						        
						        tableHDCT = new JTable(modelTableHDCT);
						        tableHDCT.getTableHeader().setFont(headerFontCT); 
						        tableHDCT.setFont(new Font("Arial", Font.PLAIN, 16));
						        tableHDCT.setShowGrid(true);
						        tableHDCT.setGridColor(new Color(225,225,225)); 
						        tableHDCT.setIntercellSpacing(new Dimension(0, 1));
						        tableHDCT.getColumnModel().getColumn(0).setPreferredWidth(5);
						        tableHDCT.getColumnModel().getColumn(5).setPreferredWidth(150);
						        tableHDCT.getColumnModel().getColumn(8).setPreferredWidth(150);        
						        tableHDCT.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);      
						        tableHDCT.setRowHeight(30);  
						        int rowHeight = tableHDCT.getRowHeight();
						        int rowCount = modelTableHDCT.getRowCount();
						        
						        panelTableAndTotal = new JPanel();
						        panelTableAndTotal.setBackground(Color.WHITE);
						        panelChiTiet.add(panelTableAndTotal, BorderLayout.SOUTH);
						        panelTableAndTotal.setLayout(new BorderLayout(0, 0));
						        
						        panelTableHDCT = new JPanel();
						        panelTableAndTotal.add(panelTableHDCT, BorderLayout.CENTER);
						        panelTableHDCT.setLayout(new BorderLayout(0, 0));
						        
						        tableHDCT.setPreferredScrollableViewportSize(new Dimension(1000, rowHeight * rowCount));
						        panelTableHDCT.add(new JScrollPane(tableHDCT));
						        
						        panelTotal = new JPanel();
						        FlowLayout flowLayout_1 = (FlowLayout) panelTotal.getLayout();
						        flowLayout_1.setAlignment(FlowLayout.RIGHT);
						        panelTotal.setBackground(Color.WHITE);
						        panelTableAndTotal.add(panelTotal, BorderLayout.SOUTH);
						        
						        panelTongtien = new JPanel();
						        panelTongtien.setBackground(Color.WHITE);
						        panelTongtien.setBorder(new LineBorder(new Color(53, 154, 255), 1, true));
						        panelTongtien.setPreferredSize(new Dimension(350, 120));
						        panelTotal.add(panelTongtien);
						        panelTongtien.setLayout(new BoxLayout(panelTongtien, BoxLayout.Y_AXIS));
						        
						        panelTongTien = new JPanel();
						        panelTongTien.setBackground(Color.WHITE);
						        panelTongtien.add(panelTongTien);
						        panelTongTien.setLayout(new BorderLayout(0, 0));
						        
						        lblTongTien = new JLabel("Tổng tiền:");
						        lblTongTien.setForeground(new Color(70, 130, 169));
						        lblTongTien.setFont(new Font("Arial", Font.BOLD, 18));
						        panelTongTien.add(lblTongTien);
						        
						        panelThueVAT = new JPanel();
						        panelThueVAT.setBackground(Color.WHITE);
						        panelTongtien.add(panelThueVAT);
						        panelThueVAT.setLayout(new BorderLayout(0, 0));
						        
						        lblThueVAT = new JLabel("Thuế VAT: " + hoaDon.getThueVAT() +"%");
						        lblThueVAT.setForeground(new Color(70, 130, 169));
						        lblThueVAT.setFont(new Font("Arial", Font.BOLD, 18));
						        panelThueVAT.add(lblThueVAT);
						        
						        panelTongTienTT = new JPanel();
						        panelTongTienTT.setBackground(Color.WHITE);
						        panelTongtien.add(panelTongTienTT);
						        panelTongTienTT.setLayout(new BorderLayout(0, 0));
						        
						        lblTongTienTT = new JLabel("Tổng tiền thanh toán:");
						        lblTongTienTT.setForeground(new Color(70, 130, 169));
						        lblTongTienTT.setFont(new Font("Arial", Font.BOLD, 18));
						        panelTongTienTT.add(lblTongTienTT, BorderLayout.CENTER);
						        
						        
						        JScrollPane scrollPane = new JScrollPane(panelChiTiet);
						        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						        
						        showDetailPanel(scrollPane);
						        
						        
						        isButtonClicked = false; 
					            qLHoaDon_view.getQLHoaDon_View().revalidate();
						        qLHoaDon_view.getQLHoaDon_View().repaint();
			            	} else {
			                    // Nếu nút đã được bấm, có thể hiển thị thông báo hoặc không làm gì
			                    JOptionPane.showMessageDialog(null, "Nút đã được bấm!");
			            	}
			            }
			        }
					private void showDetailPanel(JScrollPane scrollPane) {
						 JOptionPane.showMessageDialog(null, scrollPane, "Chi tiết hóa đơn", JOptionPane.PLAIN_MESSAGE);
						JDialog dialog = new JDialog();
			            dialog.setTitle("Chi tiết hoá đơn");
			            dialog.setSize(1100, 700); // Kích thước của dialog
			            dialog.setLocationRelativeTo(null); // Căn giữa màn hình
			            dialog.setModal(true); // Chặn các thao tác khác khi dialog mở
			            dialog.add(scrollPane);
			            dialog.setVisible(true);
						
					}    
	    	 });
	    }

		private JPanel createDetailRow(String label, String value) {
	        JPanel row = new JPanel(new BorderLayout());
	        row.setBackground(Color.WHITE);

	        JLabel labelLabel = new JLabel(label);
	        labelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

	        JLabel valueLabel = new JLabel(value);
	        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

	        row.add(labelLabel, BorderLayout.WEST);
	        row.add(valueLabel, BorderLayout.CENTER);

	        return row;
	    }
		public static String formatLoaiKhachHang(LoaiKhachHang loaiKH) {
		    switch (loaiKH) {
		        case TRE_EM:
		            return "Trẻ em";
		        case SINH_VIEN:
		            return "Sinh viên";
		        case HOC_SINH:
		            return "Học sinh";
		        case NGUOI_GIA:
		            return "Người già";
		        case NGUOI_KHUYET_TAT:
		            return "Người khuyết tật";
		        case KHACH_THUONG:
		            return "Khách thường";
		        default:
		            return "Không xác định"; 
		    }
		}
		
	    ////// Tau_view
	    private void reloadTau() {
	    	sttT = 1;
	    	xoaDuLieuTableTau();
	    	List<Tau> tauList;
			try {
				tauList = tau_DAO.getAllTau();
				for (Tau tau : tauList) {
		    		themTauVaoBang(tau);
		    	}
			} catch (SQLException e) {
				e.printStackTrace();
			}       	
        	qLTau_View.getComBmaTau().setSelectedItem(null);
        	qLTau_View.getComBTThai().setSelectedItem(null);
		}
		

	    private void searchTau() {
	        String maTau = (String) qLTau_View.getComBmaTau().getSelectedItem();
	        String trangThai = (String) qLTau_View.getComBTThai().getSelectedItem();

	        if ((maTau == null || maTau.isEmpty()) && (trangThai == null || trangThai.isEmpty())) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập (chọn) mã tàu hoặc trạng thái!");
	            return;
	        }
	        try {
	            List<Tau> danhSachTau = new ArrayList<>();

	            if (maTau != null && !maTau.isEmpty()) {
	                Tau tau = tau_DAO.layTTTauTheoMa(maTau);
	                if (tau != null) {
	                    danhSachTau.add(tau);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Không tìm thấy Tàu với mã " + maTau + "!");
	                }
	            }
	            if (trangThai != null && !trangThai.isEmpty()) {
	                TrangThaiTau trangThaiEnum = chuyenDoiTrangThai(trangThai);
	                if (trangThaiEnum != null) {
	                    List<Tau> tauList = tau_DAO.layTTTauTheoTrangThai(trangThaiEnum);
	                    if (!tauList.isEmpty()) {
	                        danhSachTau.addAll(tauList); 
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Không có tàu nào với trạng thái " + trangThai + "!");
	                    }
	                }
	            }
	            danhSachTau = danhSachTau.stream().distinct().collect(Collectors.toList());
	            xoaDuLieuTableTau();
	            for (Tau tau : danhSachTau) {
	                themTauVaoBang(tau);
	            }

	        } catch (SQLException e1) {
	            e1.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ!");
	        }
	    }
	    
	    // Phương thức để chuyển đổi từ trạng thái tiếng Việt sang enum
		private TrangThaiTau chuyenDoiTrangThai(String trangThai) {
		    switch (trangThai) {
		        case "Hoạt động":
		            return TrangThaiTau.HOAT_DONG;
		        case "Bảo trì":
		            return TrangThaiTau.BAO_TRI;
		        case "Dừng hoạt động":
		            return TrangThaiTau.DUNG_HOAT_DONG;
		        default:
		            return null;
		    }
		}
	
		// Phương thức để thêm tàu vào bảng
		private void themTauVaoBang(Tau tau) {
		    String trangThaiHienThi;
		    switch (tau.getTrangThai()) {
		        case HOAT_DONG:
		            trangThaiHienThi = "Hoạt động";
		            break;
		        case BAO_TRI:
		            trangThaiHienThi = "Bảo trì";
		            break;
		        case DUNG_HOAT_DONG:
		            trangThaiHienThi = "Dừng hoạt động";
		            break;
		        default:
		            trangThaiHienThi = "Không xác định";
		            break;
		    }
		    qLTau_View.getModelTau().addRow(new Object[] {
		    	qLTau_View.getModelTau().getRowCount() + 1,
		        tau.getMaTau(),
		        tau.getTenTau(),
		        tau.getSoToa(),
		        tau.getNamSanXuat(),
		        tau.getNhaSanXuat(),
		        tau.getTocDoTB(),
		        tau.getTocDoToiDa(),
		        trangThaiHienThi,
		        tau.getGhiChu()
		    });
		}

		private void xoaDuLieuTableTau() {
	    	DefaultTableModel dm = (DefaultTableModel) qLTau_View.getTableTau().getModel();
			dm.getDataVector().removeAllElements();			
		}

		private void nextPage() {
	        if (currentIndex + ITEMS_PER_PAGE < danhSachTau.size()) {
	            currentIndex += ITEMS_PER_PAGE;
	            soTrang++;
	            updateDisplay();
	        } else {
	            javax.swing.JOptionPane.showMessageDialog(qLTau_View, "Đã đến trang cuối cùng.", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	    
	    private void prevPage() {
	        if (currentIndex >= ITEMS_PER_PAGE) {
	            currentIndex -= ITEMS_PER_PAGE;
	            soTrang--;
	            updateDisplay();
	        } else {
	            javax.swing.JOptionPane.showMessageDialog(qLTau_View, "Đã đến trang đầu tiên.", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	
	    private void updateDisplay() {
	        updateTrainPanel(qLTau_View.trainContainer);
	        qLTau_View.getLblSoTrang().setText("trang: " + soTrang);
	        qLTau_View.getLblSoTau().setText("Tổng số tàu: " + soTau);
	    }	    

	    private void updateTrainPanel(JPanel trainContainer) {
	        trainContainer.removeAll();

	        if (currentIndex < 0 || currentIndex >= danhSachTau.size()) {
	            return;
	        }

	        int end = Math.min(currentIndex + ITEMS_PER_PAGE, danhSachTau.size());
	        for (int i = currentIndex; i < end; i++) {
	            Tau tau = danhSachTau.get(i);
	            CustomTrainStatusButton panel = new CustomTrainStatusButton(tau.getMaTau(), tau.getTrangThai());
	            panel.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                	togglePanelToaTau(tau);
	                    if (selectedButton != null) {
	                        selectedButton.deselectButton();
	                    }
	                    selectedButton = panel; 
	                    panel.selectButton();
	                    DocDuLieuVaoTableToaTau(tau.getMaTau());
	                    sttTT = 1;
	                }
	            });

	            trainContainer.add(panel);
	        }
	        trainContainer.revalidate(); 
	        trainContainer.repaint();  
	    }
	    public void DocDuLieuVaoTableToaTau(String maTau) {
	        qLTau_View.getModelTTau().setRowCount(0);
	        
	        ArrayList<ToaTau> dsTTau = toaTau_DAO.getToaTauTheoMaTau(maTau);
	        for (ToaTau tt : dsTTau) {
	            String trangThai = tt.isTrangThai() ? "Còn ghế" : "Đầy ghế"; 
	            String loaiToaDescription;
	            switch (tt.getLoaiToa()) {
	                case "V":
	                    loaiToaDescription = "Toa VIP";
	                    break;
	                case "T":
	                    loaiToaDescription = "Toa thường";
	                    break;
	                default:
	                    loaiToaDescription = "Không xác định"; 
	            }
	            qLTau_View.getModelTTau().addRow(new Object[] {
	                sttTT++, 
	                tt.getMaToaTau(), 
	                tt.getTenToaTau(),
	                tt.getSoThuTuToa(),
	                loaiToaDescription, 
	                tt.getSoLuongGhe(),
	                trangThai            
	            });
	        }	    
	    }
    
	    private void updateTenTau(JPanel tenTauPanel, String maTau) {
	        tenTauPanel.removeAll();	        
	        JPanel panel_lblTenTau = new JPanel();
	        panel_lblTenTau.setBackground(Color.WHITE);
	        panel_lblTenTau.setLayout(new BoxLayout(panel_lblTenTau, BoxLayout.X_AXIS));	        
	        CustomTitleLable lblTau = new CustomTitleLable("Tàu " + maTau);
	        panel_lblTenTau.add(lblTau);
	        
	        tenTauPanel.add(panel_lblTenTau);	        
	        tenTauPanel.revalidate();
	        tenTauPanel.repaint();
	    }
	    
	    private JButton selectedToaTauButton;

	    private void updateToaTau(JPanel toaTauPanel, String maTau) {
	        ArrayList<ToaTau> dsToaTau = toaTau_DAO.getToaTauTheoMaTau(maTau);
	        
	        toaTauPanel.removeAll();
	        for (int i = dsToaTau.size() - 1; i >= 0; i--) {
	            ToaTau tTau = dsToaTau.get(i);
	            JButton toaTauItem = createToaTau(tTau, i, dsToaTau.size());
	            toaTauItem.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    togglePanelGhe(tTau);
	                    DocDuLieuVaoTableGheTau(tTau.getMaToaTau());
	                    sttGT = 1;
	                    if (selectedToaTauButton != null) {
	                        selectedToaTauButton.setBackground(Color.WHITE);
	                        selectedToaTauButton.setForeground(new Color(70, 130, 180));
	                    }	                   
	                    selectedToaTauButton = toaTauItem;
	                    selectedToaTauButton.setBackground(new Color(230,230,230));
	                    selectedToaTauButton.setForeground(Color.RED);
	                }
	            });
	            toaTauPanel.add(toaTauItem);
	        }
	        
	        JPanel panelTrain = new JPanel();
	        panelTrain.setLayout(new BorderLayout());
	        panelTrain.setPreferredSize(new Dimension(50, 60));
	        panelTrain.setBackground(Color.WHITE); 
	        
	        ImageIcon iconDT = new ImageIcon("src/main/resources/Image/tabler-icon-train1.png");
	        JLabel iconLabel = new JLabel(iconDT, JLabel.CENTER);
	        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        panelTrain.add(iconLabel, BorderLayout.CENTER);
	        JLabel labelMaTau = new JLabel(dsToaTau.get(0).getTau().getMaTau(), JLabel.CENTER);
	        labelMaTau.setFont(new Font("Arial", Font.BOLD, 12));
	        labelMaTau.setForeground(new Color(70, 130, 180));
	        labelMaTau.setHorizontalAlignment(SwingConstants.CENTER);
	        panelTrain.add(labelMaTau, BorderLayout.SOUTH);

	        panelTrain.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                panelTrain.setBackground(new Color(230, 230, 250));  
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                panelTrain.setBackground(Color.WHITE); 
	            }

	            @Override
	            public void mouseClicked(MouseEvent e) {
	                panelTrain.setBackground(new Color(173, 216, 230));  
	                togglePanelTau(selectedButton);
	                
	                if (selectedToaTauButton != null) {
	                    selectedToaTauButton.setBackground(Color.WHITE);
	                    selectedToaTauButton.setForeground(new Color(70, 130, 180));
	                    selectedToaTauButton = null;
	                }
	            }
	        });

	        toaTauPanel.add(panelTrain);
	        toaTauPanel.revalidate();
	        toaTauPanel.repaint();
	    }

	    private static JButton createToaTau(ToaTau tTau, int index, int totalToa) {
	        ImageIcon iconTT = new ImageIcon("src/main/resources/Image/tank-train-svgrepo-com.png");
	        JButton buttonCarriage = new JButton(tTau.getTenToaTau(), iconTT);
	        buttonCarriage.setFont(new Font("Arial", Font.PLAIN, 13));    
	        buttonCarriage.setPreferredSize(new Dimension(40, 30));
	        buttonCarriage.setVerticalTextPosition(SwingConstants.BOTTOM);
	        buttonCarriage.setHorizontalTextPosition(SwingConstants.CENTER);
	        buttonCarriage.setForeground(new Color(70, 130, 180));
	        buttonCarriage.setBackground(Color.WHITE);
	        buttonCarriage.setBorderPainted(false);
	        return buttonCarriage;
	    }


	    private void updateGheTau(JPanel gheTauPanel, String maToaTau) {
	    	ArrayList<GheTau> dsGheTau = gheTau_DAO.getGheTauTheoMaToaTau(maToaTau);
	    	
	    	gheTauPanel.removeAll();
	    	
	    	int numberOfSeats = dsGheTau.size();
	    	int columns = 14;
	    	int rows = (numberOfSeats + columns - 1) / columns;
	    	
	    	gheTauPanel.setLayout(new GridLayout(rows, columns, 10, 10)); 
	    	
	    	for (int i = 0; i < dsGheTau.size(); i++) {
	            GheTau gTau = dsGheTau.get(i);
	            JButton gheTauItem = createGheTau(gTau, i+1, dsGheTau.size());
	            int rowIndex = i; 
	            gheTauItem.addActionListener(e -> {
	                // Kiểm tra chỉ số dòng có hợp lệ không trước khi chọn dòng
	                if (rowIndex >= 0 && rowIndex < qLTau_View.getTableGheTau().getRowCount()) {
	                	qLTau_View.getTableGheTau().setRowSelectionInterval(rowIndex, rowIndex);  // Chọn dòng trong JTable
	                	qLTau_View.getTableGheTau().scrollRectToVisible(qLTau_View.getTableGheTau().getCellRect(rowIndex, 0, true)); // Cuộn đến dòng đã chọn
	                } else {
	                    JOptionPane.showMessageDialog(null, "Không tìm thấy dòng để chọn.");
	                }
	            });
	            gheTauPanel.add(gheTauItem);
	        }
	    	
	    	
	    	gheTauPanel.revalidate();
	    	gheTauPanel.repaint();
	    }
	    
	    private JButton createGheTau(GheTau gTau, int i, int size) {
	        Color buttonColor; // Khai báo biến để lưu màu

	        switch (gTau.getTrangThai()) {
	            case "TRONG":
	                buttonColor = ColorConstants.PRIMARY_COLOR; 
	                break;
	            case "DA_THANH_TOAN":
	                buttonColor = Color.LIGHT_GRAY; 
	                break;
	            case "DANG_BAO_TRI":
	                buttonColor = Color.RED;
	                break;
	            default:
	                buttonColor = ColorConstants.PRIMARY_COLOR;
	                break;
	        }
	        SeatButton seatPanel = new SeatButton(i, buttonColor, ColorConstants.TEXT_COLOR);
	        return seatPanel;
	    }
    
	    public void DocDuLieuVaoTableGheTau(String maToaTau) {
	        qLTau_View.getModelGheTau().setRowCount(0);
	        
	        ArrayList<GheTau> dsGTau = gheTau_DAO.getGheTauTheoMaToaTau(maToaTau);
	        for (GheTau gt : dsGTau) {
	            String trangThaiHienThi;
	            switch (gt.getTrangThai()) {
	                case "TRONG":
	                    trangThaiHienThi = "Trống";
	                    break;
	                case "DA_THANH_TOAN":
	                    trangThaiHienThi = "Đã thanh toán";
	                    break;
	                case "DANG_BAO_TRI":
	                    trangThaiHienThi = "Đang bảo trì";
	                    break;
	                default:
	                    trangThaiHienThi = "Không xác định"; 
	                    break;
	            }

	            qLTau_View.getModelGheTau().addRow(new Object[] {
	                sttGT++, 
	                gt.getMaGheTau(), 
	                gt.getTenLoaiGheTau(),
	                gt.getSoThuTuToa(),
	                trangThaiHienThi,
	            });
	        }    
	    }

	    private void togglePanelTau(CustomTrainStatusButton selectedButton) {
	        if (qLTau_View.getPanel_GheTau().isVisible() || qLTau_View.getPanel_dsToaTau().isVisible() 
	                || qLTau_View.getPanel_toaTauIcon().isVisible()) {
	            qLTau_View.getQLTau_View().remove(qLTau_View.getPanel_GheTau());
	            qLTau_View.getQLTau_View().remove(qLTau_View.getPanel_dsToaTau());
	            qLTau_View.getPanel_headerPanel().remove(qLTau_View.getPanel_toaTauIcon());
	            qLTau_View.getPanel_Page().remove(qLTau_View.getPanel_TenTau());

	            qLTau_View.getQLTau_View().add(qLTau_View.getPanel_TableTau(), BorderLayout.CENTER);
	            qLTau_View.getPanel_TableTau().setVisible(true);
	            
	            if (selectedButton != null) {
	                selectedButton.deselectButton(); 
	            }

	            qLTau_View.getQLTau_View().revalidate();
	            qLTau_View.getQLTau_View().repaint();
	        } else {
	        	
	        }
	    }


		private void togglePanelToaTau(Tau tau) {
	        if (qLTau_View.getPanel_TableTau().isVisible() ) {
	            qLTau_View.getQLTau_View().remove(qLTau_View.getPanel_TableTau());
	            qLTau_View.getQLTau_View().remove(qLTau_View.getPanel_GheTau());

	            if (!qLTau_View.getPanel_Page().isAncestorOf(qLTau_View.getPanel_TenTau())) {
	                qLTau_View.getPanel_Page().add(qLTau_View.getPanel_TenTau(), BorderLayout.WEST);
	            }
	            updateTenTau(qLTau_View.getPanel_TenTau(), tau.getMaTau());
	            updateToaTau(qLTau_View.getPanel_toaTauIcon(), tau.getMaTau());
	            qLTau_View.getPanel_headerPanel().add(qLTau_View.getPanel_toaTauIcon(), BorderLayout.SOUTH);

	            qLTau_View.getQLTau_View().add(qLTau_View.getPanel_dsToaTau(), BorderLayout.CENTER);
	            qLTau_View.getPanel_TenTau().setVisible(true);
	            qLTau_View.getPanel_toaTauIcon().setVisible(true);
	            qLTau_View.getPanel_dsToaTau().setVisible(true);
	        } else {
	            qLTau_View.getQLTau_View().remove(qLTau_View.getPanel_toaTauIcon());
	            qLTau_View.getQLTau_View().add(qLTau_View.getPanel_TableTau(), BorderLayout.CENTER);
	            qLTau_View.getPanel_TableTau().setVisible(true);
	        }

	        qLTau_View.getQLTau_View().revalidate();
	        qLTau_View.getQLTau_View().repaint();
	    }

		private void togglePanelGhe(ToaTau tTau) {
		    if (qLTau_View.getPanel_dsToaTau().isVisible()) {
		        qLTau_View.getQLTau_View().remove(qLTau_View.getPanel_dsToaTau());
		        
		        updateGheTau(qLTau_View.getPanel_DSGhe(), tTau.getMaToaTau());
		        qLTau_View.getPanel_DSGheAndTable().add(qLTau_View.getPanel_DSGhe(), BorderLayout.NORTH);

		        int soThuTu = tTau.getSoThuTuToa();
		        String loaiToa;
		        if (soThuTu == 1 || soThuTu == 2 || soThuTu == 3) {
		            loaiToa = "Toa tàu VIP";
		        } else {
		            loaiToa = "Toa tàu thường";
		        }

		        qLTau_View.getlblTenToaTau().setText("Toa tàu số " + soThuTu + ": " + loaiToa);
		        qLTau_View.getQLTau_View().add(qLTau_View.getPanel_GheTau(), BorderLayout.CENTER);		        
		        qLTau_View.getPanel_GheTau().setVisible(true);
		        qLTau_View.getQLTau_View().revalidate();
		        qLTau_View.getQLTau_View().repaint();
		    } else {

		    }

		    qLTau_View.getQLTau_View().revalidate();
		    qLTau_View.getQLTau_View().repaint();
		}

	
	    
		@Override
		public String toString() {
			return "QuanLy_Controller [danhSachTau=" + danhSachTau + "]";
		}
	    
	}
