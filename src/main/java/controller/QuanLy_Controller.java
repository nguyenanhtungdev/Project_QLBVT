package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.ChiTiet_HoaDon;
import model.ChiTiet_HoaDon_DAO;
import model.ChuyenTau;
import model.ChuyenTau_DAO;
import model.GheTau;
import model.GheTau_DAO;
import model.GiaVe;
import model.GiaVe_DAO;
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
import model.VeTau_DAO;
import other.ColorConstants;
import other.CustomTitleLable;
import other.CustomTrainStatusButton;
import other.SeatButton;
import util.PrinterUtils;
import view.HoaDonChiTiet_View;
import view.QuanLyHoaDon_View;
import view.QuanLyKhuyenMai_View;
import view.QuanLyTau_View;
import view.View;

public class QuanLy_Controller {
	private HoaDon_DAO hoaDon_DAO;
	private VeTau_DAO veTau_DAO;
	private ChuyenTau_DAO chuyenTau_DAO;
	private GiaVe_DAO giaVe_DAO;
	private ChiTiet_HoaDon_DAO ctHD_DAO;
	private Tau_DAO tau_DAO;
	private ToaTau_DAO toaTau_DAO;
	private GheTau_DAO gheTau_DAO;
	private KhuyenMai_DAO kMai_DAO;
	private static final int ITEMS_PER_PAGE = 5;
	private int currentIndex = 0;
	private QuanLyTau_View qLTau_View;
	private QuanLyHoaDon_View qLHoaDon_view;
	private QuanLyKhuyenMai_View qLKhuyenMai_View;
	private HoaDonChiTiet_View qlHoaDonChiTiet;
	private int soTau;
	private int soTrang = 1;
	private ArrayList<Tau> danhSachTau;
	private int sttTT = 1;
	private int sttGT = 1;
	private static QuanLy_Controller instance;

	public static QuanLy_Controller getInstance() {
		if (instance == null)
			try {
				instance = new QuanLy_Controller();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return instance;
	}

	private CustomTrainStatusButton selectedButton;

	private ArrayList<View> pageList = new ArrayList<View>();

	public ArrayList<View> getViewList() {
		return pageList;
	}

	public void setPageList(ArrayList<View> pageList) {
		this.pageList = pageList;
	}

	// QL_Tau
	public QuanLy_Controller() throws SQLException {
		pageList.add(this.qLHoaDon_view = new QuanLyHoaDon_View("Hóa đơn", "/Image/tabler-icon-file-settings.png/"));
		pageList.add(this.qLTau_View = new QuanLyTau_View("Tàu", "/Image/tabler-icon-file-settings.png"));
		pageList.add(
				this.qLKhuyenMai_View = new QuanLyKhuyenMai_View("Khuyến mãi", "/Image/tabler-icon-file-settings.png"));

		this.gheTau_DAO = new GheTau_DAO();
		this.toaTau_DAO = new ToaTau_DAO();
		this.tau_DAO = new Tau_DAO();
		this.danhSachTau = tau_DAO.getAllTau();
		this.soTau = danhSachTau.size();
		initControllerTau();

		this.kMai_DAO = new KhuyenMai_DAO();
		initControllerKM();

		this.qlHoaDonChiTiet = new HoaDonChiTiet_View();
		this.hoaDon_DAO = new HoaDon_DAO();
		this.veTau_DAO = new VeTau_DAO();
		this.chuyenTau_DAO = new ChuyenTau_DAO();
		this.giaVe_DAO = new GiaVe_DAO();
		this.ctHD_DAO = new ChiTiet_HoaDon_DAO();
		initControllerHD();

	}

	private void initControllerTau() throws SQLException {
		themSuKien();
		DocDuLieuVaoTableTau();
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

	// QL_KhuyenMai

	private void initControllerKM() {
		themSuKienKM();
		DocDuLieuVaoTableKhuyenMai();
	}

	private void themSuKienKM() {
		qLKhuyenMai_View.addButtonHuyListener(e -> huyTxtKM());
		qLKhuyenMai_View.addButtonThemListener(e -> ThemKM());
		qLKhuyenMai_View.addButtonTimListener(e -> searchKM());
		qLKhuyenMai_View.addButtonReloadListener(e -> reLoadSearchKM());
	}

	// QL_HoaDon
	private void initControllerHD() {
		themSuKienHD();
		DocDuLieuVaoTableHoaDon();

	}

	private void themSuKienHD() {
		qLHoaDon_view.addButtonReloadListener(e -> reloadHoaDon());
		qLHoaDon_view.addButtonMaHDItem(e -> locTheoMaHD());
		qLHoaDon_view.addButtonDateItem(e -> locTheoDate());
		qLHoaDon_view.addButtonSDTItem(e -> locTheoSDT());
		qLHoaDon_view.addButtonXemHDCT(e -> {
			try {
				xemHDCT();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		qlHoaDonChiTiet.addInActionListener(e -> inHoaDonChiTiet());
		qLHoaDon_view.addButtonInDSHD(e -> inDSHD());
	}

	////// HoaDon_view
	public void inDSHD() {
		PrinterUtils.print(new Printable() {
			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				if (pageIndex > 0) {
					return NO_SUCH_PAGE;
				}

				Graphics2D g2d = (Graphics2D) graphics;
				g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

				double pageWidth = pageFormat.getImageableWidth();
				double pageHeight = pageFormat.getImageableHeight();
				double tableWidth = qLHoaDon_view.getTableHoaDon().getWidth();
				double tableHeight = qLHoaDon_view.getTableHoaDon().getHeight();
				double scaleX = (pageWidth - 40) / tableWidth;
				double scaleY = (pageHeight - 80) / (tableHeight + 50);
				double scale = Math.min(scaleX, scaleY);
				g2d.scale(scale, scale);
				String title = "Danh Sách Hoá Đơn";
				g2d.setFont(new Font("Arial", Font.BOLD, 24));
				FontMetrics metrics = g2d.getFontMetrics();
				int titleX = (int) ((tableWidth - metrics.stringWidth(title)) / 2);
				g2d.drawString(title, titleX, 24);
				g2d.translate(20, 50);
				qLHoaDon_view.getTableHoaDon().getTableHeader().printAll(g2d);

				g2d.translate(0, qLHoaDon_view.getTableHoaDon().getTableHeader().getHeight());
				qLHoaDon_view.getTableHoaDon().printAll(g2d);
				g2d.drawRect(0, -qLHoaDon_view.getTableHoaDon().getTableHeader().getHeight(),
						qLHoaDon_view.getTableHoaDon().getWidth(), qLHoaDon_view.getTableHoaDon().getHeight()
								+ qLHoaDon_view.getTableHoaDon().getTableHeader().getHeight());
				return PAGE_EXISTS;
			}
		}, "In Hoá Đơn");
	}

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

	public void reloadHoaDon() {
		xoaDuLieuTableHoaDon();
		DocDuLieuVaoTableHoaDon();
		qLHoaDon_view.getCombMaHD().setSelectedItem(null);
		qLHoaDon_view.getCombSDT().setSelectedItem(null);
		int selectedRow = qLHoaDon_view.getTableHoaDon().getSelectedRow();
		if (selectedRow != -1) {
			qLHoaDon_view.getModelHD().removeRow(selectedRow);
		}
		qLHoaDon_view.getDateBD().setDate(null);
		JTextField dateField = (JTextField) qLHoaDon_view.getDateBD().getDateEditor().getUiComponent();
		dateField.setText("Tạo từ ngày");
		dateField.setForeground(Color.GRAY);
		qLHoaDon_view.getDateKT().setDate(null);
		JTextField dateField1 = (JTextField) qLHoaDon_view.getDateKT().getDateEditor().getUiComponent();
		dateField1.setText("Đến ngày");
		dateField1.setForeground(Color.GRAY);
	}

	private void themHoaDonVaoBang(HoaDon hd) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String ngayLapHoaDonFormatted = hd.getNgayLapHoaDon().format(formatter);
		String loaiHoaDon = "";
		if ("ThanhToan".equals(hd.getLoaiHoaDon())) {
			loaiHoaDon = "Thanh toán";
		} else if ("GiuCho".equals(hd.getLoaiHoaDon())) {
			loaiHoaDon = "Giữ chỗ";
		}
		double tongTienSauThue = 0.0;

		try {
			Map<String, Double> result = layTongTienHoaDon(hd.getMaHoaDon());
			tongTienSauThue = result.getOrDefault("tongTienSauThue", 0.0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		qLHoaDon_view.getModelHD()
				.addRow(new Object[] { qLHoaDon_view.getModelHD().getRowCount() + 1, hd.getMaHoaDon(), loaiHoaDon,
						hd.getKhachHang().getHoTen(), hd.getKhachHang().getSoDienThoai(), ngayLapHoaDonFormatted,
						hd.getThueVAT() + "%", String.format("%,.0f", tongTienSauThue) + " VNĐ" });
	}

	private void xoaDuLieuTableHoaDon() {
		DefaultTableModel dm = (DefaultTableModel) qLHoaDon_view.getTableHoaDon().getModel();
		dm.getDataVector().removeAllElements();
	}

	public void DocDuLieuVaoTableHoaDon() {
		List<HoaDon> list = hoaDon_DAO.getalltbHDKH();
		for (HoaDon hd : list) {
			themHoaDonVaoBang(hd);
		}
	}

	public Map<String, Double> layTongTienHoaDon(String maHD) throws SQLException {
		HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
		ChiTiet_HoaDon_DAO chiTietHoaDonDAO = ChiTiet_HoaDon_DAO.getInstance();
		VeTau_DAO veTauDAO = new VeTau_DAO();
		ChuyenTau_DAO chuyenTauDAO = new ChuyenTau_DAO();
		GiaVe_DAO giaVeDAO = new GiaVe_DAO();
		KhuyenMai_DAO khuyenMaiDAO = new KhuyenMai_DAO(); // Giả sử bạn có DAO này

		List<ChiTiet_HoaDon> chiTietHoaDons = chiTietHoaDonDAO.getAll().stream()
				.filter(p -> p.getHoaDon().getMaHoaDon().equals(maHD)).collect(Collectors.toList());
		if (chiTietHoaDons.isEmpty()) {
			return Collections.emptyMap();
		}
		double tongTien = 0.0;
		double tongTienKhuyenMai = 0.0;

		for (ChiTiet_HoaDon ctHD : chiTietHoaDons) {
			// Tính tổng tiền từ các vé tàu
			VeTau veTau = veTauDAO.getVeTauByMaVeTau(ctHD.getVeTau().getMaVeTau());
			if (veTau == null) {
				continue;
			}
			ChuyenTau chuyenTau = chuyenTauDAO.getById(veTau.getChuyenTau().getMaChuyenTau());
			if (chuyenTau == null) {
				continue;
			}
			GiaVe giaVe = giaVeDAO.findByMaGiaVe(chuyenTau.getGiaVe().getMaGiaVe());
			if (giaVe == null) {
				continue;
			}
			tongTien += giaVe.getGiaVe();
			String maKhuyenMai = ctHD.getKhuyenMai().getMaKhuyenMai();
			if (maKhuyenMai != null && !maKhuyenMai.isEmpty()) {
				KhuyenMai khuyenMai = khuyenMaiDAO.getById1(maKhuyenMai);
				if (khuyenMai != null) {
					double giamGia = khuyenMai.getGiamGia();
					tongTienKhuyenMai += giamGia;
				}
			}
		}
		HoaDon hoaDon = hoaDonDAO.layTTHoaDonTheoMa(maHD);
		double thueVAT = hoaDon.getThueVAT();
		double soTienThue = tongTien * thueVAT / 100;
		double tongTienSauVAT = tongTien + soTienThue - tongTienKhuyenMai;

		Map<String, Double> result = new HashMap<>();
		result.put("tongTienTruocThue", tongTien);
		result.put("tienThue", soTienThue);
		result.put("tongTienKhuyenMai", tongTienKhuyenMai);
		result.put("tongTienSauThue", tongTienSauVAT);
		return result;
	}

	public String getSelectedMaHD() {
		int selectedRow = qLHoaDon_view.getTableHoaDon().getSelectedRow();
		if (selectedRow != -1) {
			return qLHoaDon_view.getTableHoaDon().getValueAt(selectedRow, 1).toString();
		}
		return null;
	}

	private void xemHDCT() throws SQLException {
		int row = qLHoaDon_view.getTableHoaDon().getSelectedRow();
		int rowCount = qLHoaDon_view.getTableHoaDon().getRowCount();
		if (row != -1 && row < rowCount) {
			String maHD = getSelectedMaHD();
			HoaDon hoaDon = hoaDon_DAO.layTTHoaDonTheoMa(maHD);
			ThongTinTram ttTram = hoaDon_DAO.layThongTinTramTheoMa(hoaDon.getThongTinTram().getMaNhaGa());
			KhachHang kh = hoaDon_DAO.layKhachHangTheoMa(hoaDon.getKhachHang().getMaKhachHang());
			LocalDateTime ngayLapHoaDon = hoaDon.getNgayLapHoaDon();
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("'Ngày' dd 'tháng' MM 'năm' yyyy, HH 'giờ' mm 'phút' ss 'giây'");
			String formattedDate = ngayLapHoaDon.format(formatter);

			qlHoaDonChiTiet.getDateLabel().setText(formattedDate);
			qlHoaDonChiTiet.getMaHoaDonLabel().setText("Mã hóa đơn: " + hoaDon.getMaHoaDon());

			qlHoaDonChiTiet.getDonViBH().setValue(ttTram.getTenNhaGa());
			qlHoaDonChiTiet.getMaSoThue().setValue(ttTram.getMaSoThue());
			qlHoaDonChiTiet.getDiaChi().setValue(ttTram.getDiaChi());
			qlHoaDonChiTiet.getSoTaiKhoan().setValue(ttTram.getSoTaiKhoản());
			qlHoaDonChiTiet.getTenNganHang().setValue(ttTram.getTenNganHang());

			qlHoaDonChiTiet.getHoTenKH().setValue(kh.getHoTen());
			qlHoaDonChiTiet.getSoDienThoai().setValue(kh.getSoDienThoai());
			qlHoaDonChiTiet.getEmail().setValue(kh.getEmail());
			qlHoaDonChiTiet.getLoaiKhachHang().setValue(formatLoaiKhachHang(kh.getLoaiKH()));
			String phuongThucThanhToan = hoaDon.getPhuongThucThanhToan();
			if ("TienMat".equals(phuongThucThanhToan)) {
				phuongThucThanhToan = "Tiền mặt";
			}
			qlHoaDonChiTiet.getHinhThucThanhToan().setValue(phuongThucThanhToan);

			DecimalFormat df = new DecimalFormat("#.##");
			Map<String, Double> tongTienHoaDon = layTongTienHoaDon(maHD);
			double tongTienSauThue = tongTienHoaDon.get("tongTienSauThue");

			List<ChiTiet_HoaDon> ctHD1 = ctHD_DAO.getAll().stream()
					.filter(p -> p.getHoaDon().getMaHoaDon().equals(maHD)).collect(Collectors.toList());
			xoaDuLieuTableHoaDonChiTiet();
			for (int i = 0; i < ctHD1.size(); i++) {
				ChiTiet_HoaDon ctHD = ctHD1.get(i);
				VeTau veTau = veTau_DAO.getVeTauByMaVeTau(ctHD.getVeTau().getMaVeTau());
				if (veTau == null) {
					continue;
				}
				ChuyenTau chuyenTau = chuyenTau_DAO.getById(veTau.getChuyenTau().getMaChuyenTau());
				GiaVe giaVe = giaVe_DAO.findByMaGiaVe(chuyenTau.getGiaVe().getMaGiaVe());
				if (giaVe == null) {
					continue;
				}

				double donGia = giaVe.getGiaVe();
				double thanhTienTruocThue = donGia;
				double giamGia = 0.0;
				if (ctHD.getKhuyenMai() != null) {
					KhuyenMai khuyenMai = kMai_DAO.getById1(ctHD.getKhuyenMai().getMaKhuyenMai());
					if (khuyenMai != null) {
						giamGia = khuyenMai.getGiamGia();
					}
				}

				double thueSuat = hoaDon.getThueVAT();
				double tongTienSauThue1 = (thanhTienTruocThue - giamGia) * (1 + thueSuat / 100);

				String formattedDonGia = String.format("%,.0f", donGia);
				String formattedThanhTienTruocThue = String.format("%,.0f", thanhTienTruocThue);
				String formattedGiamGia = df.format(giamGia);
				String formattedTongTienSauThue1 = String.format("%,.0f", tongTienSauThue1);

				DefaultTableModel model = (DefaultTableModel) qlHoaDonChiTiet.getModelTableHDCT();
				model.addRow(new Object[] { i + 1, veTau.getMaVeTau(), veTau.isLoaiVe() ? "Vé thường" : "Vé Vip",
						ctHD.getSoLuong(), formattedDonGia, formattedThanhTienTruocThue, formattedGiamGia + "%",
						thueSuat + "%", formattedTongTienSauThue1 });
			}
			int currentRowCount = qlHoaDonChiTiet.getModelTableHDCT().getRowCount();

			int remainingRows = 13 - currentRowCount;
			for (int i = 0; i < remainingRows; i++) {
				DefaultTableModel model = (DefaultTableModel) qlHoaDonChiTiet.getModelTableHDCT();
				model.addRow(new Object[] { "", "", "", "", "", "", "", "", "" });
			}
			int rowHeight = qlHoaDonChiTiet.getTableHDCT().getRowHeight();
			int currentRowCount1 = qlHoaDonChiTiet.getModelTableHDCT().getRowCount();
			qlHoaDonChiTiet.getTableHDCT()
					.setPreferredScrollableViewportSize(new Dimension(1000, rowHeight * currentRowCount1));

			qlHoaDonChiTiet.getLblTongTien().setText("Tổng tiền: " + String.format("%,.0f", tongTienSauThue) + " VNĐ");
			qlHoaDonChiTiet.getLblThueVAT().setText("Thuế VAT: " + hoaDon.getThueVAT() + "%");
			double tongTienSauThueRounded = Math.round(tongTienSauThue / 1000.0) * 1000.0;
			qlHoaDonChiTiet.getLblTongTienTT()
					.setText("Tổng tiền thanh toán: " + String.format("%,.0f", tongTienSauThueRounded) + " VNĐ");

			// Sau khi cập nhật dữ liệu xong, hiển thị JFrame
			qlHoaDonChiTiet.getModelTableHDCT().fireTableDataChanged();
			qlHoaDonChiTiet.revalidate();
			qlHoaDonChiTiet.repaint();
			qlHoaDonChiTiet.setLocationRelativeTo(null);
			qlHoaDonChiTiet.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(qLHoaDon_view, "Vui lòng chọn hoá đơn!");
		}
	}

	private void xoaDuLieuTableHoaDonChiTiet() {
		DefaultTableModel dm = (DefaultTableModel) qlHoaDonChiTiet.getTableHDCT().getModel();
		dm.getDataVector().removeAllElements();
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

	private void inHoaDonChiTiet() {
		qlHoaDonChiTiet.print();
	}

	// Khuyen_Mai
	private void ThemKM() {
		if (validDataKMai()) {
			String tenKhuyenMai = qLKhuyenMai_View.getTxtTenkm().getText().trim();
			String noiDungKhuyenMai = qLKhuyenMai_View.getTxtNDKM().getText().trim();
			int soLuongToiDa = Integer.parseInt(qLKhuyenMai_View.getTxtSLKM().getText().trim());
			Date dateHanSuDung = qLKhuyenMai_View.getDateKTKM().getDate();
			LocalDateTime hanSuDung = dateHanSuDung != null
					? LocalDateTime.ofInstant(dateHanSuDung.toInstant(), ZoneId.systemDefault()).withHour(0)
							.withMinute(0).withSecond(0).withNano(0)
					: null;
			double giamGia = Double.parseDouble(qLKhuyenMai_View.getTxtGiamGia().getText().trim()); // Chuyển giamGia
																									// sang double

			String maKhuyenMaiCu;
			try {
				maKhuyenMaiCu = kMai_DAO.getMaxMaKhuyenMai();
				String maKhuyenMai = generateNextMaKhuyenMai(maKhuyenMaiCu);
				KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa,
						hanSuDung, giamGia, TinhTrangKhuyenMai.CON); // Gán giá trị giamGia vào đối tượng KhuyenMai

				boolean result = kMai_DAO.them(khuyenMai);

				if (result) {
					themKhuyenMaiVaoBang(khuyenMai);
					JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Thêm khuyến mãi thành công!");
					huyTxtKM();
				} else {
					JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(),
							"Thêm khuyến mãi không thành công!");
				}
			} catch (SQLException e) {
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

		String giamGia = qLKhuyenMai_View.getTxtGiamGia().getText().trim();

		Date dateHanSuDung = qLKhuyenMai_View.getDateKTKM().getDate();
		LocalDateTime hanSuDung = dateHanSuDung != null
				? LocalDateTime.ofInstant(dateHanSuDung.toInstant(), ZoneId.systemDefault()).withHour(0).withMinute(0)
						.withSecond(0).withNano(0)
				: null;

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
				JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(),
						"Số lượng tối đa không được là số âm!");
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(),
					"Số lượng tối đa phải là số nguyên hợp lệ!");
			return false;
		}
		if (noiDungKhuyenMai.isEmpty()) {
			JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Vui lòng nhập nội dung khuyến mãi!");
			return false;
		}
		if (giamGia.isEmpty()) {
			JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Vui lòng nhập giá trị giảm giá!");
			return false;
		}
		double giamGiaValue;
		try {
			giamGiaValue = Double.parseDouble(giamGia);
			if (giamGiaValue < 0 || giamGiaValue > 100) {
				JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(),
						"Giảm giá phải nằm trong khoảng từ 0 đến 100!");
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(), "Giảm giá phải là một số hợp lệ!");
			return false;
		}

		if (hanSuDung == null || !hanSuDung.isAfter(LocalDateTime.now())) {
			JOptionPane.showMessageDialog(qLKhuyenMai_View.getContentPane(),
					"Hạn sử dụng khuyến mãi phải sau thời gian hiện tại!");
			return false;
		}
		return true;
	}

	private void reLoadSearchKM() {
		xoaDuLieuTableKM();
		List<KhuyenMai> kmList;
		try {
			kmList = kMai_DAO.getAll1();
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
				KhuyenMai km = kMai_DAO.getById1(maKM);
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

	public void DocDuLieuVaoTableKhuyenMai() {
		List<KhuyenMai> list;
		try {
			list = kMai_DAO.getAll1();
			for (KhuyenMai km : list) {
				themKhuyenMaiVaoBang(km);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void themKhuyenMaiVaoBang(KhuyenMai kMai) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String ngayKetThucDoFormatted;
		String tinhTrangKhuyenMai = "";
		switch (kMai.getTinhTrangKhuyenMai()) {
		case CON:
			tinhTrangKhuyenMai = "Còn";
			break;
		case HET_SO_LUONG:
			tinhTrangKhuyenMai = "Hết số lượng";
			break;
		case HET_HAN_SU_DUNG:
			tinhTrangKhuyenMai = "Hết hạn sử dụng";
			break;
		default:
			tinhTrangKhuyenMai = "Không xác định"; // Trường hợp mặc định nếu không khớp
			break;
		}
		if (kMai.getHanSuDungKhuyenMai() != null) {
			ngayKetThucDoFormatted = kMai.getHanSuDungKhuyenMai().format(formatter);
		} else {
			ngayKetThucDoFormatted = "";
		}
		qLKhuyenMai_View.getModelTableKM()
				.addRow(new Object[] { qLKhuyenMai_View.getModelTableKM().getRowCount() + 1, kMai.getMaKhuyenMai(),
						kMai.getTenKhuyenMai(), kMai.getNoiDungKhuyenMai(), kMai.getGiamGia() + "%",
						kMai.getSoLuongToiDa(), ngayKetThucDoFormatted, tinhTrangKhuyenMai, });

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

	////// Tau_view
	private void reloadTau() {
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
		qLTau_View.getModelTau()
				.addRow(new Object[] { qLTau_View.getModelTau().getRowCount() + 1, tau.getMaTau(), tau.getTenTau(),
						tau.getSoToa(), tau.getNamSanXuat(), tau.getNhaSanXuat(), tau.getTocDoTB(), tau.getTocDoToiDa(),
						trangThaiHienThi, tau.getGhiChu() });
	}

	public void DocDuLieuVaoTableTau() throws SQLException {
		qLTau_View.getModelTau().setRowCount(0);
		ArrayList<Tau> dsTau;
		dsTau = tau_DAO.getAllTau();
		for (Tau t : dsTau) {
			themTauVaoBang(t);
		}
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
			javax.swing.JOptionPane.showMessageDialog(null, "Đã đến trang cuối cùng.", "Thông báo",
					javax.swing.JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void prevPage() {
		if (currentIndex >= ITEMS_PER_PAGE) {
			currentIndex -= ITEMS_PER_PAGE;
			soTrang--;
			updateDisplay();
		} else {
			javax.swing.JOptionPane.showMessageDialog(null, "Đã đến trang đầu tiên.", "Thông báo",
					javax.swing.JOptionPane.INFORMATION_MESSAGE);
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
			qLTau_View.getModelTTau().addRow(new Object[] { sttTT++, tt.getMaToaTau(), tt.getTenToaTau(),
					tt.getSoThuTuToa(), loaiToaDescription, tt.getSoLuongGhe(), trangThai });
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
					selectedToaTauButton.setBackground(new Color(230, 230, 230));
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
			JButton gheTauItem = createGheTau(gTau, i + 1, dsGheTau.size());
			int rowIndex = i;
			gheTauItem.addActionListener(e -> {
				if (rowIndex >= 0 && rowIndex < qLTau_View.getTableGheTau().getRowCount()) {
					qLTau_View.getTableGheTau().setRowSelectionInterval(rowIndex, rowIndex);
					qLTau_View.getTableGheTau()
							.scrollRectToVisible(qLTau_View.getTableGheTau().getCellRect(rowIndex, 0, true));
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
		Color buttonColor;

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
		case "DANG_GIU_CHO":
			buttonColor = Color.LIGHT_GRAY;
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
			case "DANG_GIU_CHO":
				trangThaiHienThi = "Đang giữ chỗ";
				break;
			default:
				trangThaiHienThi = "Không xác định";
				break;
			}

			qLTau_View.getModelGheTau().addRow(new Object[] { sttGT++, gt.getMaGheTau(), gt.getTenLoaiGheTau(),
					gt.getsoThuTuGhe(), trangThaiHienThi, });
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
		if (qLTau_View.getPanel_TableTau().isVisible()) {
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
