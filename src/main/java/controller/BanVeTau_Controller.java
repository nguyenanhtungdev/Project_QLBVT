package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;

import java.sql.SQLException;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import constant.ColorConstants;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
import model.KhachHang_DAO;
import model.NhanVien;
import model.PhieuHoanTien;
import model.PhieuHoanTien_DAO;
import model.Tau;
import model.Tau_DAO;
import model.ThongTinTram;
import model.ThongTinTram_DAO;
import model.TinhThanh;
import model.ToaTau;
import model.ToaTau_DAO;
import model.VeTau;
import model.VeTau_DAO;
import other.RoundedPanel;
import other.ShortcutManager;
import view.BaoMat_view;
import view.DoiTraVe_View;
import view.ThanhToan_View;
import view.ThongTinVe;
import view.ChonGhe_View;
import view.VeTau_View;
import view.View;

public class BanVeTau_Controller implements ActionListener, MouseListener, FocusListener, KeyListener {

	private static BanVeTau_Controller instance;

	public static BanVeTau_Controller getInstance() {
		if (instance == null)
			try {
				instance = new BanVeTau_Controller();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return instance;
	}

	private static int ITEMS_PER_PAGE = 5;
	private int currentIndex = 0, soChuyenTau, soTrang = 1;
	private ChonGhe_View chonGhe_View;
	private VeTau_View veTau_Page;
	private ThanhToan_View thanhToan_Page;
	private List<ChuyenTau> danhSachChuyenTau;
	private JPanel selectedPanel_ChuyenTau = null, selectedPanel_ChuyenTau_1 = null;
	private JLabel selectedLabel_ToaTau = null, selectedLabel_IconToaTau = null;
	private JButton selectedButon_GheTau = null;
	private TinhThanh selectedTinhThanhDi, selectedTinhThanhDen;
	private String ngayDi, ngayVe, gaDi, gaDen;
	private ArrayList<ChuyenTau> chuyenTauList;
	private ImageIcon icon;
	private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
	private boolean isBtnClicked = false;
	private ChuyenTau chuyenTauChon;
	private ToaTau toaTauChon;
	private GheTau gheTauChon;
	private String maGheTauDuocChon = null;
	private DateTimeFormatter formatterNgayGio = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private DateTimeFormatter formatterNgay = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private int sttVeTau = 0;
	private int sttKH = 0;
	private String maVeTau = null;
	private String maKhachHang = null;
	private int tongSoVeTamThoi = 0;
	private double tongTienVeTauTamThoi = 0;
	private NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private int tongSoVeChieuDi = 0;
	private int tongSoVeChieuVe = 0;
	private boolean isBtnTiepTheoClick = false;
	// hoàn tiền view
	private DoiTraVe_View hoanTien_view;
	private ArrayList<View> pageList = new ArrayList<>();
	private Timer countdownTimer;
	private boolean isThemVeTau = false;
	private int[] timeLeft;
	private DefaultTableModel model;
	private JLabel soLuongGheTrongChuyen;
	private ThongTinVe thongTinVe = new ThongTinVe();

	// ThanhToan
	private ArrayList<Double> dsTienChiTietVe;
	private double tienKhachDua = 0;
	private double tongTienThanhToan = 0;
	private String ghiChu = "";
	private String phuongThucThanhToan = "TienMat";
	private String maHoaDon = null;

	public ArrayList<View> getViewList() {
		return pageList;
	}

	// Hàm khởi tạo
	public BanVeTau_Controller() throws SQLException {
		this.danhSachChuyenTau = ChuyenTau_DAO.getInstance().getAll();
		this.soChuyenTau = danhSachChuyenTau.size();

		pageList.add(chonGhe_View = new ChonGhe_View("Chọn ghế", "/Image/armchair.png"));
		pageList.add(veTau_Page = new VeTau_View("Vé tàu", "/Image/tabler-icon-ticket.png"));
		pageList.add(hoanTien_view = new DoiTraVe_View("Đổi trả vé", "/Image/icon_ThanhToan.png"));
		thanhToan_Page = new ThanhToan_View("Thanh toán", "/Image/armchair.png");

		hienThiThongTinVe();
		initController();
	}

	public void hienThiThongTinVe() {
		hoanTien_view.getDanhSachVeTable().getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = hoanTien_view.getDanhSachVeTable().getSelectedRow();
				if (selectedRow != -1) {
					hoanTien_view.getXacNhanButton().setEnabled(true);
				} else {
					hoanTien_view.getXacNhanButton().setEnabled(false);
				}
			}
		});
	}

	private void initController() throws SQLException {
		themSuKien();
		updateTrainPanel(chonGhe_View.panel_dsChuyenTau, danhSachChuyenTau);
		chonGhe_View.getLblSoTrang().setText("trang: " + soTrang);
		chonGhe_View.getLblSoChuyenTau().setText("Tổng số chuyến tàu: " + soChuyenTau);

	}

	private void themSuKien() {
		chonGhe_View.addNextButtonListener(e -> nextPage());
		chonGhe_View.addPrevButtonListener(e -> prevPage());
		chonGhe_View.addSuKien(this);
		thongTinVe.addSuKien(this);
		hoanTien_view.addSuKien(this, this);
		veTau_Page.addSuKien(this, this, this); // Gọi hàm thêm sự kiện cho lớp Vé tàu tạm thời
		veTau_Page.addSuKienTable(this);
		veTau_Page.getTxt_NgaySinh().addKeyListener(this);

		thanhToan_Page.addSuKienThanhToan(this, this);
		thanhToan_Page.addSuKienTableThanhToan(this);

	}

	private void nextPage() {
		if (currentIndex + ITEMS_PER_PAGE < danhSachChuyenTau.size()) {
			currentIndex += ITEMS_PER_PAGE;
			soTrang++;
			updateDisplay(danhSachChuyenTau); // sử dụng để cập nhật hiển thị của giao diện người dùng khi có thay đổi
												// dữ liệu
		} else {
			javax.swing.JOptionPane.showMessageDialog(null, "Đã đến trang cuối cùng.", "Thông báo",
					javax.swing.JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void prevPage() {
		if (currentIndex > 0) {
			currentIndex = Math.max(currentIndex - ITEMS_PER_PAGE, 0);
			soTrang--;
			updateDisplay(danhSachChuyenTau);
		} else {
			javax.swing.JOptionPane.showMessageDialog(null, "Đã đến trang đầu tiên.", "Thông báo",
					javax.swing.JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void updateDisplay(List<ChuyenTau> dsChuyenTaus) {
		try {
			updateTrainPanel(chonGhe_View.getPanel_dsChuyenTau(), dsChuyenTaus);
			chonGhe_View.getLblSoTrang().setText("trang: " + soTrang);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void updateTrainPanel(JPanel trainPanel, List<ChuyenTau> dsChuyenTaus) throws SQLException {
		trainPanel.removeAll();
		for (int i = currentIndex; i < Math.min(currentIndex + ITEMS_PER_PAGE, dsChuyenTaus.size()); i++) {
			ChuyenTau chuyenTau = dsChuyenTaus.get(i);
			trainPanel.add(createTau(chuyenTau));
		}
		trainPanel.revalidate();
		trainPanel.repaint();
	}

	private void updateSoLuongGheTrongChuyen(Tau tau) {
		Map<String, Integer> thongTinGhe = Tau_DAO.getInstance().layThongTinGhe(tau.getMaTau());
		soLuongGheTrongChuyen.setText(thongTinGhe.get("soGheConLai") + "/" + thongTinGhe.get("tongSoGhe") + "");
		soLuongGheTrongChuyen.revalidate();
		soLuongGheTrongChuyen.repaint();
	}

	// Tạo phần tử tàu
	private JPanel createTau(ChuyenTau chuyenTau) {
		Tau tau = Tau_DAO.getInstance().getTauByMaChuyenTau(chuyenTau.getMaChuyenTau());
		RoundedPanel panel_chyentau = new RoundedPanel(20);
		panel_chyentau.setBorder(new EmptyBorder(0, 20, 15, 20));
		panel_chyentau.setBackground(ColorConstants.PRIMARY_COLOR);
		panel_chyentau.setPreferredSize(new Dimension(210, 160));
		panel_chyentau.setMaximumSize(new Dimension(210, 170));
		panel_chyentau.setLayout(new BoxLayout(panel_chyentau, BoxLayout.Y_AXIS));

		JPanel panel_maTau = new JPanel();
		panel_maTau.setBackground(ColorConstants.PRIMARY_COLOR);
		panel_maTau.setPreferredSize(new Dimension(0, 30));
		panel_maTau.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		panel_chyentau.add(panel_maTau);
		panel_maTau.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_9 = new JLabel(chuyenTau.getGaKhoiHanh() + " - " + chuyenTau.getGaDen());
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 18));
		panel_maTau.add(lblNewLabel_9);

		JPanel panel_23 = new JPanel();
		panel_23.setBorder(new EmptyBorder(5, 5, 0, 5));
		panel_chyentau.add(panel_23);
		panel_23.setLayout(new BoxLayout(panel_23, BoxLayout.Y_AXIS));

		JPanel panel_24 = new JPanel();
		panel_23.add(panel_24);
		panel_24.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_10 = new JLabel("Thời gian đi:");
		lblNewLabel_10.setFont(new Font("Arial", Font.BOLD, 16));
		panel_24.add(lblNewLabel_10, BorderLayout.NORTH);

		// Hiển thị trong JLabel
		JLabel lblNewLabel_11 = new JLabel(convertToVietnamTime(chuyenTau.getThoiGianKhoiHanh()));
		lblNewLabel_11.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_24.add(lblNewLabel_11, BorderLayout.CENTER);

		JPanel panel_25 = new JPanel();
		panel_23.add(panel_25);
		panel_25.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_12 = new JLabel("Thời gian đến:");
		lblNewLabel_12.setFont(new Font("Arial", Font.BOLD, 16));
		panel_25.add(lblNewLabel_12, BorderLayout.NORTH);

		JLabel lblNewLabel_13 = new JLabel(convertToVietnamTime(chuyenTau.getThoiGianDuKien()));
		lblNewLabel_13.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_25.add(lblNewLabel_13, BorderLayout.CENTER);

		JPanel panel_26 = new JPanel();
		panel_23.add(panel_26);
		panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		JLabel maTau_JLabel = new JLabel(chuyenTau.getMaChuyenTau() + " - ");
		maTau_JLabel.setForeground(ColorConstants.PRIMARY_COLOR);
		maTau_JLabel.setFont(new Font("Arial", Font.BOLD, 20));
		panel_26.add(maTau_JLabel);

		soLuongGheTrongChuyen = new JLabel("0/0");
		updateSoLuongGheTrongChuyen(tau);
		soLuongGheTrongChuyen.setForeground(ColorConstants.PRIMARY_COLOR);
		soLuongGheTrongChuyen.setFont(new Font("Arial", Font.BOLD, 20));
		panel_26.add(soLuongGheTrongChuyen);

		panel_chyentau.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// Đặt lại màu nền của tàu trước đó nếu có
				if (selectedPanel_ChuyenTau != null && selectedPanel_ChuyenTau_1 != null) {
					selectedPanel_ChuyenTau.setBackground(ColorConstants.PRIMARY_COLOR);
					selectedPanel_ChuyenTau_1.setBackground(ColorConstants.PRIMARY_COLOR);
				}

				panel_chyentau.setBackground(ColorConstants.SELECT_COLOR);
				panel_maTau.setBackground(ColorConstants.SELECT_COLOR);

				selectedPanel_ChuyenTau = panel_chyentau;
				selectedPanel_ChuyenTau_1 = panel_maTau;

				// Ghi nhận chuyến tàu được chọn
				chuyenTauChon = chuyenTau;

				ArrayList<ToaTau> dsToaTau = ToaTau_DAO.getInstance().getDsToaTau(tau.getMaTau());

				if (dsToaTau != null && !dsToaTau.isEmpty()) {
					JPanel panelToaTau = themDsToaTau(dsToaTau);
					toaTauChon = dsToaTau.get(0);
					// Cập nhật giao diện
					chonGhe_View.panel_DsToaTau.removeAll();
					chonGhe_View.panel_DsToaTau.add(panelToaTau);
					chonGhe_View.panel_DsToaTau.revalidate();
					chonGhe_View.panel_DsToaTau.repaint();
				} else {
					JOptionPane.showMessageDialog(null, "Không có toa tàu nào!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				if (panel_chyentau != selectedPanel_ChuyenTau && panel_maTau != selectedPanel_ChuyenTau_1) {
					panel_chyentau.setBackground(ColorConstants.SELECT_COLOR);
					panel_maTau.setBackground(ColorConstants.SELECT_COLOR);
				}
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				if (panel_chyentau != selectedPanel_ChuyenTau && panel_maTau != selectedPanel_ChuyenTau_1) {
					panel_chyentau.setBackground(ColorConstants.PRIMARY_COLOR);
					panel_maTau.setBackground(ColorConstants.PRIMARY_COLOR);
				}
			}
		});
		return panel_chyentau;
	}

	public static String convertToVietnamTime(LocalDateTime thoiGianKhoiHanh) {
		ZonedDateTime zonedDateTimeVN = thoiGianKhoiHanh.atZone(ZoneId.of("Asia/Ho_Chi_Minh"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return zonedDateTimeVN.format(formatter);
	}

	// Thêm danh sách toa tàu
	public JPanel themDsToaTau(ArrayList<ToaTau> dsToaTau) {
		int count = 0;

		JPanel panel = new RoundedPanel(10);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
		panel.setBackground(Color.WHITE);

		for (ToaTau toaTau : dsToaTau) {
			count++;
			panel.add(createTrainCarPanel(toaTau, count));
		}
		// Mặc định là lấy thằng toa tàu đầu tiên
		if (!dsToaTau.isEmpty()) {
			ToaTau toaTauDauTien = dsToaTau.get(0);
			ArrayList<GheTau> dsGheTau = GheTau_DAO.getInstance().getDsGheTau(toaTauDauTien.getMaToaTau());
			JPanel panelGheTau = themDsGheTau(dsGheTau);

			// Cập nhật giao diện cho `panel_DsGheTau`
			chonGhe_View.panel_DsGheTau.removeAll();
			chonGhe_View.panel_DsGheTau.add(panelGheTau);
			chonGhe_View.panel_DsGheTau.revalidate();
			chonGhe_View.panel_DsGheTau.repaint();

			chonGhe_View.getLbl_TenToaTau().setText("Toa tàu số 1: Toa tàu VIP");
			chonGhe_View.getLbl_SoGheTau()
					.setText(tinhSoGheTauConLai(toaTauDauTien.getMaToaTau()) + "/" + dsGheTau.size());
		}
		return panel;
	}

	// Phương thức tính số tàu còn lại
	private int tinhSoGheTauConLai(String maToaTau) {
		int sl = 0;
		for (GheTau gheTau : GheTau_DAO.getInstance().getDsGheTau(maToaTau)) {
			if (gheTau.getTrangThai().equals("TRONG")) {
				sl++;
			}
		}
		return sl;
	}

	// Phương thức tạo panel cho từng toa tàu 1
	private JPanel createTrainCarPanel(ToaTau toaTau, int count) {
		JPanel panel_toaTau = new JPanel();
		panel_toaTau.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_toaTau.setBackground(Color.WHITE);
		panel_toaTau.setLayout(new BoxLayout(panel_toaTau, BoxLayout.Y_AXIS));

		JPanel panel_IconToaTau = new JPanel();
		panel_IconToaTau.setBackground(Color.WHITE);
		panel_toaTau.add(panel_IconToaTau);

		JLabel lbl_IconToaTau = new JLabel();
		icon = new ImageIcon(getClass().getResource("/Image/toa_tau.png"));
		lbl_IconToaTau.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		panel_IconToaTau.add(lbl_IconToaTau);

		JPanel panel_TenToaTau = new JPanel();
		panel_TenToaTau.setBackground(Color.WHITE);
		panel_TenToaTau.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_toaTau.add(panel_TenToaTau);

		JLabel lbl_ThongTinSTTToa = new JLabel("TT" + toaTau.getSoThuTuToa());
		lbl_ThongTinSTTToa.setForeground(Color.BLACK);
		lbl_ThongTinSTTToa.setFont(new Font("Arial", Font.BOLD, 18));
		panel_TenToaTau.add(lbl_ThongTinSTTToa);

		panel_toaTau.setPreferredSize(new Dimension(60, 100));

		// Them su kien
		panel_toaTau.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (selectedLabel_ToaTau != null && selectedLabel_IconToaTau != null) {
					selectedLabel_ToaTau.setForeground(Color.BLACK);
					icon = new ImageIcon(getClass().getResource("/Image/toa_tau.png"));
					selectedLabel_IconToaTau
							.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				}
				selectedLabel_ToaTau = lbl_ThongTinSTTToa;
				selectedLabel_IconToaTau = lbl_IconToaTau;
				icon = new ImageIcon(getClass().getResource("/Image/toa_tau_select.png"));
				lbl_IconToaTau.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				lbl_ThongTinSTTToa.setForeground(ColorConstants.PRIMARY_COLOR);

				// Ghi nhận chuyến tàu được chọn
				toaTauChon = toaTau;

				ArrayList<GheTau> dsGheTau = GheTau_DAO.getInstance().getDsGheTau(toaTau.getMaToaTau());
				if (dsGheTau != null && !dsGheTau.isEmpty()) {
					JPanel panelGheTau = themDsGheTau(dsGheTau);
					// Cập nhật giao diện
					chonGhe_View.panel_DsGheTau.removeAll();
					chonGhe_View.panel_DsGheTau.add(panelGheTau);
					chonGhe_View.panel_DsGheTau.revalidate();
					chonGhe_View.panel_DsGheTau.repaint();
					chonGhe_View.getLbl_SoGheTau()
							.setText(tinhSoGheTauConLai(toaTauChon.getMaToaTau()) + "/" + dsGheTau.size());

					if (count < 4) {
						chonGhe_View.getLbl_TenToaTau().setText("Toa tàu số " + count + ":" + " Toa tàu VIP");
					} else {
						chonGhe_View.getLbl_TenToaTau().setText("Toa tàu số " + count + ":" + " Toa tàu thường");
					}

				} else {
					JOptionPane.showMessageDialog(panel_toaTau, "Không có toa tàu nào!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				if (lbl_ThongTinSTTToa != selectedLabel_ToaTau && lbl_IconToaTau != selectedLabel_IconToaTau) {
					lbl_ThongTinSTTToa.setForeground(ColorConstants.PRIMARY_COLOR);
					icon = new ImageIcon(getClass().getResource("/Image/toa_tau_select.png"));
					lbl_IconToaTau
							.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				}
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				if (lbl_ThongTinSTTToa != selectedLabel_ToaTau && lbl_IconToaTau != selectedLabel_IconToaTau) {
					lbl_ThongTinSTTToa.setForeground(Color.BLACK);
					icon = new ImageIcon(getClass().getResource("/Image/toa_tau.png"));
					lbl_IconToaTau
							.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				}
			}
		});

		return panel_toaTau;
	}

	// Thêm danh sách ghế tàu theo toa tàu
	public JPanel themDsGheTau(ArrayList<GheTau> gheTaus) {
		JPanel panel = new RoundedPanel(10);
		panel.setLayout(new GridLayout(2, 14, 10, 10));
		EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);
		LineBorder lineBorder = new LineBorder(ColorConstants.PRIMARY_COLOR);
		panel.setBorder(new CompoundBorder(lineBorder, emptyBorder));

		for (GheTau gheTau : gheTaus) {
			panel.add(createGheTau(gheTau.getsoThuTuGhe(), gheTau));
		}
		return panel;
	}

	// Ghế tàu
	public JButton createGheTau(int soTTGhe, GheTau gheTau) {
		JButton btn_GheTau = new JButton(String.valueOf(soTTGhe + ""));
		btn_GheTau.setFont(new Font("Arial", Font.BOLD, 16));

		// Đặt màu ban đầu theo trạng thái
		setButtonColorByStatus(btn_GheTau, gheTau.getTrangThai());

		btn_GheTau.setForeground(Color.WHITE);
		btn_GheTau.setBorder(null);
		btn_GheTau.setPreferredSize(new Dimension(73, 30));

		btn_GheTau.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// Đặt lại màu của ghế đã chọn trước đó
				if (selectedButon_GheTau != null) {
					// Khôi phục màu gốc theo trạng thái của ghế trước đó
					setButtonColorByStatus(selectedButon_GheTau, gheTauChon.getTrangThai());
					selectedButon_GheTau = null;
				}

				// Cập nhật ghế được chọn và màu của nó
				btn_GheTau.setBackground(ColorConstants.SECONDARY_COLOR);
				selectedButon_GheTau = btn_GheTau;
				maGheTauDuocChon = gheTau.getMaGheTau();
				gheTauChon = gheTau;
			}
		});

		return btn_GheTau;
	}

	// Phương thức đặt màu của ghế dựa trên trạng thái
	private void setButtonColorByStatus(JButton button, String trangThai) {
		switch (trangThai) {
		case "TRONG":
			button.setBackground(ColorConstants.PRIMARY_COLOR);
			break;
		case "DA_THANH_TOAN":
			button.setBackground(ColorConstants.SELECT_COLOR);
			break;
		case "DANG_BAO_TRI":
			button.setBackground(Color.RED);
			break;
		case "DANG_GIU_CHO":
			button.setBackground(ColorConstants.SELECT_COLOR);
			break;
		default:
			button.setBackground(Color.GRAY); // Màu mặc định nếu có trạng thái lạ
			break;
		}
	}

	// Phương thức tìm kiếm chuyến tàu
	public void timKiemChuyenTau(String ngay, boolean isChiChieuDi) {
		try {
			// Tìm kiếm chuyến tàu
			String gaDiTemp = isChiChieuDi ? gaDi : gaDen;
			String gaDenTemp = isChiChieuDi ? gaDen : gaDi;
			chuyenTauList = ChuyenTau_DAO.getInstance().timKiemChuyenTau(gaDiTemp, gaDenTemp, ngay);
			soChuyenTau = chuyenTauList.size();
			Date date = inputFormat.parse(ngay);

			if (soChuyenTau > 0) {
				chonGhe_View.getLblSoChuyenTau().setText("Tổng số chuyến tàu: " + soChuyenTau);

				String labelText = (isChiChieuDi ? "Chiều đi: " : "Chiều về: ") + "ngày " + outputFormat.format(date)
						+ " từ " + gaDi + " đến " + gaDen;
				chonGhe_View.getLblTTChuyenTauTimKiem().setText(labelText);
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy chuyến tàu!");
				return;
			}

			// Reset lại chỉ số trang và hiển thị giao diện
			danhSachChuyenTau = chuyenTauList;
			this.currentIndex = 0;
			this.soTrang = 1;
			this.soChuyenTau = danhSachChuyenTau.size();
			updateDisplay(danhSachChuyenTau);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void timKiemChuyenTauDi(String ngayDi) {
		timKiemChuyenTau(ngayDi, true);
	}

	public void timKiemChuyenTauDiVe(String ngayVe) {
		timKiemChuyenTau(ngayVe, false);
	}

	// Lọc chuyến tàu
	private void locChuyenTau(List<ChuyenTau> listChuyenTaus) {
		ArrayList<ChuyenTau> dsTimThay = new ArrayList<ChuyenTau>();
		String khungGio = (String) chonGhe_View.getCombobox_KhungGio().getSelectedItem();

		for (ChuyenTau chuyenTau : listChuyenTaus) {
			boolean thoaManKhungGio = false;
			int gioKhoiHanh = chuyenTau.getThoiGianKhoiHanh().getHour();

			// Kiểm tra khung giờ
			switch (khungGio) {
			case "00:00 - 6:00":
				thoaManKhungGio = (gioKhoiHanh >= 0 && gioKhoiHanh < 6);
				break;
			case "6:00 - 12:00":
				thoaManKhungGio = (gioKhoiHanh >= 6 && gioKhoiHanh < 12);
				break;
			case "12:00 - 18:00":
				thoaManKhungGio = (gioKhoiHanh >= 12 && gioKhoiHanh < 18);
				break;
			case "18:00 - 00:00":
				thoaManKhungGio = (gioKhoiHanh >= 18 && gioKhoiHanh < 24);
				break;
			default: // "Tất cả"
				thoaManKhungGio = true;
				break;
			}

			// Kiểm tra theo còn trống với đã đầy

			if (thoaManKhungGio) {
				dsTimThay.add(chuyenTau);
			}
		}

		if (dsTimThay.size() > 0) {
			// Reset lại chỉ số trang và hiển thị giao diện
			this.currentIndex = 0;
			this.soTrang = 1;
			this.soChuyenTau = dsTimThay.size();
			chonGhe_View.getLblSoChuyenTau().setText("Tổng số chuyến tàu: " + soChuyenTau);
			danhSachChuyenTau = dsTimThay;
			updateDisplay(danhSachChuyenTau);
		} else {
			JOptionPane.showMessageDialog(null, "Không tìm thấy chuyến tàu!");
		}
	}

	// Phương thức kiểm tra tỉnh thành
	private boolean kiemTraTinhThanh() {

		if (selectedTinhThanhDi == null || selectedTinhThanhDen == null || selectedTinhThanhDi.toString().equals("")
				|| selectedTinhThanhDen.toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ga đi và ga đến!");
			return false;
		}

		this.gaDi = selectedTinhThanhDi.toString();
		this.gaDen = selectedTinhThanhDen.toString();
		if (gaDi.equals("TP Hồ Chí Minh"))
			gaDi = "TP.HCM";
		if (gaDen.equals("TP Hồ Chí Minh"))
			gaDen = "TP.HCM";

		return true;
	}

	// Phương thức kiểm tra ngày đi và ngày về
	private boolean kiemTraNgay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date ngayDiDate = chonGhe_View.getDateChooser_NgayDi().getDate();
		Date ngayVeDate = chonGhe_View.getDateChooser_NgayVe().getDate();

		if (chonGhe_View.getRdbtn_MotChieu().isSelected() && ngayDiDate == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đi cho vé một chiều!");
			return false;
		}

		if (chonGhe_View.getRdbtn_KhuHoi().isSelected()) {
			if (ngayDiDate == null || ngayVeDate == null) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn cả ngày đi và ngày về cho vé khứ hồi!");
				return false;
			}
			// Kiểm tra nếu ngày về trước ngày đi
			if (ngayVeDate.before(ngayDiDate)) {
				JOptionPane.showMessageDialog(null, "Ngày về phải sau ngày đi!");
				return false;
			}
		}

		if (ngayDiDate != null)
			ngayDi = dateFormat.format(ngayDiDate);
		if (ngayVeDate != null)
			ngayVe = dateFormat.format(ngayVeDate);

		return true;
	}

	private void lamMoi_ChuyenTau_Tau_ToaTau_GheTau() {
		chuyenTauChon = null;
		toaTauChon = null;
		gheTauChon = null;
	}

	// Làm mới chuyến tàu lại như ban đầu và ô nhập dữ liệu
	private void lamMoi() {
		lamMoiInput();
		chonGhe_View.getCombobox_KhungGio().setSelectedIndex(0);
		isBtnClicked = false;
		lamMoi_ChuyenTau_Tau_ToaTau_GheTau();

		// Làm mới giao diện ghế tàu
		lamMoiToaTau_GheTau();

		// Ẩn buton
		chonGhe_View.getBtn_QuayLai().setVisible(false);
		chonGhe_View.getBtn_TiepTheo().setVisible(false);
		chonGhe_View.getLbl_VeChieuDi().setVisible(false);
		chonGhe_View.getLbl_SoVeChieuVe().setVisible(false);
		chonGhe_View.getLbl_VeChieuVe().setVisible(false);
		chonGhe_View.getLbl_SoVeChieuDi().setVisible(false);
		chonGhe_View.getLbl_TongSoVeTamThoi().setVisible(true);

		danhSachChuyenTau = ChuyenTau_DAO.getInstance().getAll();
		soChuyenTau = danhSachChuyenTau.size();
		chonGhe_View.getLblSoChuyenTau().setText("Tổng số chuyến tàu: " + soChuyenTau);
		// Reset lại chỉ số trang và hiển thị giao diện
		this.currentIndex = 0;
		this.soTrang = 1;
		updateDisplay(danhSachChuyenTau);
	}

	private void lamMoiToaTau_GheTau() {
		chonGhe_View.panel_DsGheTau.removeAll();
		chonGhe_View.panel_DsGheTau.revalidate();
		chonGhe_View.panel_DsGheTau.repaint();
		chonGhe_View.getLbl_TenToaTau().setText("");
		chonGhe_View.getLbl_SoGheTau().setText("");
		// Làm mới giao diện toa tàu
		chonGhe_View.panel_DsToaTau.removeAll();
		chonGhe_View.panel_DsToaTau.revalidate();
		chonGhe_View.panel_DsToaTau.repaint();
	}

	private void lamMoiInput() {
		chonGhe_View.getJcombobox_gadi().setSelectedIndex(-1);
		chonGhe_View.getJcombobox_gaden().setSelectedIndex(-1);
		chonGhe_View.getDateChooser_NgayDi().setDate(null);
		chonGhe_View.getDateChooser_NgayVe().setDate(null);
		chonGhe_View.getRdbtn_MotChieu().setSelected(true);
		chonGhe_View.getLblTTChuyenTauTimKiem().setText("Danh sách chuyến tàu đề xuất");
	}

	private void capNhatGiaoDienGheTau(String maToaTau) {
		JPanel panelGheTau = themDsGheTau(GheTau_DAO.getInstance().getDsGheTau(maToaTau));

		chonGhe_View.panel_DsGheTau.removeAll();
		chonGhe_View.panel_DsGheTau.add(panelGheTau);
		chonGhe_View.panel_DsGheTau.revalidate();
		chonGhe_View.panel_DsGheTau.repaint();

	}

	private boolean checkSoLuongVeChieuDi() {
		if (Integer.parseInt(chonGhe_View.getLbl_SoVeChieuDi().getText()) < 1) {
			return false;
		}
		return true;
	}

	private boolean checkSoLuongVeChieuVe() {
		if (Integer.parseInt(chonGhe_View.getLbl_SoVeChieuVe().getText()) < 1) {
			return false;
		}
		return true;
	}

	private boolean chonNhanhGheTauNgauNhien() {
		String input = JOptionPane.showInputDialog(null, "Nhập số lượng ghế muốn chọn:", "Chọn nhanh ghế tàu",
				JOptionPane.QUESTION_MESSAGE);

		if (input == null) {
			return false;
		}
		if (input.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng ghế!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		int soLuongGhe;

		try {
			soLuongGhe = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Số lượng ghế không hợp lệ! Vui lòng nhập số nguyên.", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		ArrayList<GheTau> danhSachGheTrong = new ArrayList<>();
		for (GheTau ghe : GheTau_DAO.getInstance().getDsGheTau(toaTauChon.getMaToaTau())) {
			if (ghe.getTrangThai().equals("TRONG")) {
				danhSachGheTrong.add(ghe);
			}
		}

		if (soLuongGhe > danhSachGheTrong.size()) {
			JOptionPane.showMessageDialog(null,
					"Không đủ ghế trống! Chỉ còn " + danhSachGheTrong.size() + " ghế trống.", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		danhSachGheTrong.sort((g1, g2) -> Integer.compare(g1.getsoThuTuGhe(), g2.getsoThuTuGhe()));

		ArrayList<GheTau> danhSachGheDuocChon = new ArrayList<>();
		for (int i = 0; i < danhSachGheTrong.size() && danhSachGheDuocChon.size() < soLuongGhe; i++) {
			GheTau gheHienTai = danhSachGheTrong.get(i);
			if (danhSachGheDuocChon.isEmpty()
					|| isLienKe(danhSachGheDuocChon.get(danhSachGheDuocChon.size() - 1), gheHienTai)) {
				danhSachGheDuocChon.add(gheHienTai);
			}
		}

		if (danhSachGheDuocChon.size() < soLuongGhe) {
			ArrayList<GheTau> gheConLai = new ArrayList<>(danhSachGheTrong);
			gheConLai.removeAll(danhSachGheDuocChon);
			while (danhSachGheDuocChon.size() < soLuongGhe) {
				GheTau gheNgauNhien = gheConLai.remove(new Random().nextInt(gheConLai.size()));
				danhSachGheDuocChon.add(gheNgauNhien);
			}
		}

		if (!isBtnTiepTheoClick) {
			for (GheTau ghe : danhSachGheDuocChon) {
				themVeTauChonNhanh(ghe);
				ghe.setTrangThai("DANG_GIU_CHO");
				GheTau_DAO.getInstance().updateTrangThaiGheTau(ghe.getMaGheTau(), "DANG_GIU_CHO");
			}
		} else {
			JTable jTable = veTau_Page.getDanhSachVeTau();
			int i = 0;
			for (GheTau ghe : danhSachGheDuocChon) {
				String chiTiet = (String) jTable.getValueAt(i, 12);
				System.out.println(chiTiet);
//				jTable.setValueAt(danhSachGheDuocChon, i, 12);
				i++;
			}
			capNhatVeTauKhuHoi(danhSachGheDuocChon);
		}

		capNhatGiaoDienGheTau(toaTauChon.getMaToaTau());

		if (chonGhe_View.getRdbtn_MotChieu().isSelected()) {
			tongSoVeTamThoi += soLuongGhe;
		} else {
			if (isBtnTiepTheoClick) {
				tongSoVeChieuVe += soLuongGhe;
			} else {
				tongSoVeChieuDi += soLuongGhe;
			}
		}

		updateTrangThaiThemVeTau();
		JOptionPane.showMessageDialog(null, "Đã chọn nhanh " + soLuongGhe + " ghế thành công!", "Thông báo",
				JOptionPane.INFORMATION_MESSAGE);
		return true;
	}

	private void capNhatVeTauKhuHoi(ArrayList<GheTau> danhSachGheDuocChon) {
		JTable jTable = veTau_Page.getDanhSachVeTau();
		int i = 0;
		for (GheTau gheTau : danhSachGheDuocChon) {
			double giaVeHienTai = Double.parseDouble(((String) jTable.getValueAt(i, 13)).replace(",", ""));
			double giaVe = GiaVe_DAO.getInstance()
					.getGiaVeTheoChuyenTau(gheTau.getMaGheTau(), chuyenTauChon.getMaChuyenTau())
					.getGiaVeHienTai((gheTau.getTenLoaiGheTau().equals("Giường nằm")) ? "VIP" : "Thường");

			jTable.setValueAt(String.format("%,.0f", giaVeHienTai + giaVe), i, 13);

			i++;
		}
		veTau_Page.getLblTienTamTinh().setText(String.format("%,.0f VND", roundMoney(tinhGiaVeTamTinh())));
	}

	private boolean isLienKe(GheTau ghe1, GheTau ghe2) {
		return Math.abs(ghe1.getsoThuTuGhe() - ghe2.getsoThuTuGhe()) == 1;
	}

	private void themVeTauChonNhanh(GheTau gheTau) {
		JTable jTable = veTau_Page.getDanhSachVeTau();
		DefaultTableModel model = (DefaultTableModel) jTable.getModel();

		String chiTietChuyenTau = chuyenTauChon.getGaKhoiHanh() + " - " + chuyenTauChon.getGaDen() + " "
				+ formatterNgayGio.format(chuyenTauChon.getThoiGianKhoiHanh());

		if (sttVeTau == 0) {
			maVeTau = taoMaVeTau(VeTau_DAO.getInstance().getVeTauMax());
		} else {
			maVeTau = taoMaVeTau(maVeTau);
		}
		String loaiVeString = (gheTau.getTenLoaiGheTau().equals("Giường nằm")) ? "VIP" : "Thường";
		double giaVe = GiaVe_DAO.getInstance()
				.getGiaVeTheoChuyenTau(gheTau.getMaGheTau(), chuyenTauChon.getMaChuyenTau())
				.getGiaVeHienTai(loaiVeString);

		// Thêm dữ liệu vào model
		model.addRow(new Object[] { ++sttVeTau, maVeTau, loaiVeString, gheTau.getMaGheTau(), "{trống}", "{trống}",
				"{trống}", "{trống}", "{trống}", "{trống}", "dd/mm/yyyy", "{trống}", chiTietChuyenTau,
				String.format("%,.0f", giaVe) });
	}

	public static double convertStringToDouble(String formattedString) {
		String cleanedString = formattedString.replaceAll("[^\\d]", "");
		double giaVe = Double.parseDouble(cleanedString);
		return giaVe;
	}

	// Xử lý lớp vé tàu tạm thời
	// Them ve tau
	public boolean themVeTau() {
		if (gheTauChon == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ghế tàu trước khi thêm vé!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!gheTauChon.getTrangThai().equals("TRONG")) {
			if (gheTauChon.getTrangThai().equals("DANG_BAO_TRI")) {
				JOptionPane.showMessageDialog(null, "Ghế tàu đang bảo trì! Vui lòng chọn ghế khác.", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Ghế tàu đã được đặt hoặc giữ chỗ! Vui lòng chọn ghế khác.", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}

		JTable jTable = veTau_Page.getDanhSachVeTau();
		model = (DefaultTableModel) jTable.getModel();
		for (int i = 0; i < model.getRowCount(); i++) {
			String maGheTrongBang = (String) model.getValueAt(i, 3);
			if (maGheTrongBang.equals(gheTauChon.getMaGheTau())) {
				JOptionPane.showMessageDialog(null, "Ghế tàu này đã được thêm vào danh sách!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		themDataTableVeTau();
		GheTau_DAO.getInstance().updateTrangThaiGheTau(maGheTauDuocChon, "DANG_GIU_CHO");

		++tongSoVeTamThoi;
		updateTrangThaiThemVeTau();
		veTau_Page.getLbl_TongSoVe().setText(String.valueOf(model.getRowCount()));
		JOptionPane.showMessageDialog(null, "Thêm vé tàu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		return true;
	}

	public void updateTrangThaiThemVeTau() {
		int soVeTau = veTau_Page.getDanhSachVeTauModel().getRowCount();
		veTau_Page.getLbl_TongSoVe().setText(soVeTau + "");

		if (chonGhe_View.getRdbtn_MotChieu().isSelected()) {
			chonGhe_View.getLbl_TongSoVeTamThoi().setText("Số vé tạm thời: " + tongSoVeTamThoi);
			veTau_Page.getLblTienTamTinh().setText(String.format("%,.0f VND", roundMoney(tinhGiaVeTamTinh())));
		} else {
			if (isBtnTiepTheoClick) {
				chonGhe_View.getLbl_SoVeChieuVe().setText(tongSoVeChieuVe + "");
			} else {
				chonGhe_View.getLbl_SoVeChieuDi().setText(tongSoVeChieuDi + "");
				veTau_Page.getLblTienTamTinh().setText(String.format("%,.0f VND", roundMoney(tinhGiaVeTamTinh())));
			}
		}
	}

	// Tạo mã vé tàu
	public String taoMaVeTau(String maVeTauMax) {
		String currentMaxCode = maVeTauMax;
		String prefix = "VT";
		String year = String.valueOf(Year.now().getValue()).substring(2);

		int newNumber = 1;
		if (currentMaxCode != null) {
			String currentNumberStr = currentMaxCode.substring(4);
			newNumber = Integer.parseInt(currentNumberStr) + 1;
		}

		String ticketCode = prefix + year + String.format("%04d", newNumber);
		return ticketCode;
	}

	public String taoMaVeTauGiam(String maVeTauMax) {
		String currentMaxCode = maVeTauMax;
		String prefix = "VT";
		String year = String.valueOf(Year.now().getValue()).substring(2);

		int newNumber = 1;
		if (currentMaxCode != null) {
			String currentNumberStr = currentMaxCode.substring(4);
			newNumber = Integer.parseInt(currentNumberStr) - 1;
		}

		String ticketCode = prefix + year + String.format("%04d", newNumber);
		return ticketCode;
	}

	// Tạo mã khách hàng tự động
	public String taoMaKhachHang(String maKhachHangMax) {
		String prefix = "KH";
		int newNumber = 1;

		if (maKhachHangMax != null && maKhachHangMax.startsWith(prefix)) {
			try {

				String currentNumberStr = maKhachHangMax.substring(2);
				newNumber = Integer.parseInt(currentNumberStr) + 1;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Mã khách hàng không hợp lệ: " + maKhachHangMax);
			}
		}
		return prefix + String.format("%07d", newNumber);
	}

	// Xử lý vé tàu tạm thời
	public void themDataTableVeTau() {
		boolean loaiVe;
		String chiTietChuyenTau = chuyenTauChon.getGaKhoiHanh() + " - " + chuyenTauChon.getGaDen() + " "
				+ formatterNgayGio.format(chuyenTauChon.getThoiGianKhoiHanh());

		if (sttVeTau == 0) {
			maVeTau = taoMaVeTau(VeTau_DAO.getInstance().getVeTauMax());
		} else {
			maVeTau = taoMaVeTau(maVeTau);
		}

		if (toaTauChon.getSoThuTuToa() <= 3) {
			loaiVe = true;
		} else {
			loaiVe = false;
		}

		String loaiVeString = loaiVe ? "VIP" : "Thường";
		double giaVe = GiaVe_DAO.getInstance()
				.getGiaVeTheoChuyenTau(gheTauChon.getMaGheTau(), chuyenTauChon.getMaChuyenTau())
				.getGiaVeHienTai(loaiVeString);
		// Them data vao table
		veTau_Page.getDanhSachVeTauModel()
				.addRow(new Object[] { ++sttVeTau, maVeTau, loaiVeString, gheTauChon.getMaGheTau(), "{trống}",
						"{trống}", "{trống}", "{trống}", "{trống}", "{trống}", "dd/mm/yyyy", "{trống}",
						chiTietChuyenTau, String.format("%,.0f VND", roundMoney(giaVe)) });
		veTau_Page.getLbl_TongSoVe().setText(sttVeTau + "");
	}

	// Thêm thông tin khách hàng vào ô input
	public void themThongTinInputTable(int i) {
		JTable jTable = veTau_Page.getDanhSachVeTau();
		String maVT = (String) jTable.getValueAt(i, 1);
		String maKH = (String) jTable.getValueAt(i, 4);
		String hoTen = (String) jTable.getValueAt(i, 5);
		String sdt = (String) jTable.getValueAt(i, 6);
		String email = (String) jTable.getValueAt(i, 7);
		String gioiTinh = (String) jTable.getValueAt(i, 8);
		String cccd = (String) jTable.getValueAt(i, 9);
		String ngaySinh = (String) jTable.getValueAt(i, 10);
		String hangKH = (String) jTable.getValueAt(i, 11);

		veTau_Page.getTxt_maVT().setText(maVT);
		veTau_Page.getTxt_MaKH().setText(maKH);
		veTau_Page.getTxt_SDT().setText(sdt);
		veTau_Page.getTxt_CCCD().setText(cccd);
		veTau_Page.getTxt_HoTen().setText(hoTen);
		veTau_Page.getTxt_Email().setText(email);

		if (gioiTinh.equals("{trống}")) {
			veTau_Page.getComboBox_GioiTinh().setSelectedIndex(0);
		} else {
			veTau_Page.getComboBox_GioiTinh().setSelectedItem((String) gioiTinh);
		}

		if (hangKH.equals("{trống}")) {
			veTau_Page.getComboBox_LoaiKH().setSelectedIndex(0);
		} else {
			veTau_Page.getComboBox_LoaiKH().setSelectedItem((String) hangKH);
		}

		if (ngaySinh.equals("dd/mm/yyyy")) {
			veTau_Page.getTxt_NgaySinh().setText(ngaySinh);
		} else {
			veTau_Page.getTxt_NgaySinh().setText(formatterNgay.format(LocalDate.parse(ngaySinh)));
		}

	}

	// Cập nhật trạng thái ghế tàu
	private void capNhatTrangThaiGheTau(String maGheTau) {
		for (GheTau gheTau : GheTau_DAO.getInstance().getAll()) {
			if (gheTau.getMaGheTau().equals(maGheTau)) {
				gheTau.setTrangThai("DANG_GIU_CHO");
				break;
			}
		}
	}

	// Xử lý hủy danh sách vé tàu tạm thời
	private boolean xuLyHuyBoVeTam() {
		JTable jTable = veTau_Page.getDanhSachVeTau();
		int count = jTable.getRowCount();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				String maVeTau = (String) jTable.getValueAt(i, 3);
				GheTau_DAO.getInstance().updateTrangThaiGheTau(maVeTau, "TRONG");
			}
			return true;
		} else {
			return false;
		}
	}

	public void resetHuyBoVe() {
		stopTimers();
		isThemVeTau = false;

		// Reset giao diện và biến liên quan
		veTau_Page.getLbl_TongSoVe().setText("0");
		chonGhe_View.getLbl_TongSoVeTamThoi().setText("Số vé tàu tạm thời: 0");
		veTau_Page.getDanhSachVeTauModel().setRowCount(0);
		veTau_Page.getLblTienTamTinh().setText("0");
		tongSoVeTamThoi = 0;
		sttVeTau = 0;
		maHoaDon = null;

		lamMoi_ChuyenTau_Tau_ToaTau_GheTau();
		lamMoiToaTau_GheTau();

		chonGhe_View.getLbl_SoVeChieuDi().setText(0 + "");
		chonGhe_View.getLbl_SoVeChieuVe().setText(0 + "");

		// Xóa thông tin hiển thị thời gian
		veTau_Page.getLbl_ThoiGianGiuVe().setText("");
		xoaTrangInputVeTauTam();
		khoaNhapDuLieuVeTauTam();

		// Cập nhật trạng thái ghế tàu
		if (toaTauChon != null) {
			capNhatGiaoDienGheTau(toaTauChon.getMaToaTau());
		}
	}

	// Bôi đen chọn toàn bộ dự liệu trên ô text field
	private void boiDenDuLieu() {
		veTau_Page.getTxt_NgaySinh().selectAll();
		veTau_Page.getTxt_SDT().selectAll();
		veTau_Page.getTxt_CCCD().selectAll();
		veTau_Page.getTxt_HoTen().selectAll();
		veTau_Page.getTxt_Email().selectAll();
	}

	// Tìm kiếm khách hàng
	private KhachHang timKiemKhachHang(String s, boolean b) { // true: sdt, false: cccd
		for (KhachHang khachHang : KhachHang_DAO.getInstance().getAll()) {
			if (b) {
				if (khachHang.getSoDienThoai().equals(s)) {
					return khachHang;
				}
			} else {
				if (khachHang.getCCCD().equals(s)) {
					return khachHang;
				}
			}
		}
		return null;
	}

	// Thêm thông tin khách hàng tìm thấy lên ô input
	private void themThongTinKHInput(KhachHang khachHang) {
		veTau_Page.getTxt_MaKH().setText(khachHang.getMaKhachHang());
		veTau_Page.getTxt_SDT().setText(khachHang.getSoDienThoai());
		veTau_Page.getTxt_CCCD().setText(khachHang.getCCCD());
		veTau_Page.getTxt_HoTen().setText(khachHang.getHoTen());
		veTau_Page.getTxt_Email().setText(khachHang.getEmail());
		veTau_Page.getTxt_NgaySinh().setText(formatterNgay.format(khachHang.getNgaySinh()));
		veTau_Page.getComboBox_GioiTinh().setSelectedIndex(khachHang.isGioiTinh() ? 1 : 2);
		veTau_Page.getComboBox_LoaiKH()
				.setSelectedItem(KhachHang.LoaiKhachHang.chuyenDoiTuEnumSangChuoi(khachHang.getLoaiKH()));
	}

	// Mở khóa nhập dữ liệu input
	private void moNhapDuLieuVeTauTam() {
		veTau_Page.getTxt_SDT().setEnabled(true);
		veTau_Page.getTxt_CCCD().setEnabled(true);
		veTau_Page.getTxt_HoTen().setEnabled(true);
		veTau_Page.getTxt_Email().setEnabled(true);
		veTau_Page.getTxt_NgaySinh().setEnabled(true);
		veTau_Page.getComboBox_GioiTinh().setEnabled(true);
		veTau_Page.getComboBox_LoaiKH().setEnabled(true);
	}

	private void startCountdownTimer() {
		// Dừng tất cả các Timer cũ trước khi tạo mới
		stopTimers();

		timeLeft = new int[] { 1500 };

		// Timer đếm ngược
		countdownTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeLeft[0] > 0) {
					int minutes = timeLeft[0] / 60;
					int seconds = timeLeft[0] % 60;
					String formattedTime = String.format("%02d:%02d", minutes, seconds);
					veTau_Page.getLbl_ThoiGianGiuVe().setText(formattedTime);
					timeLeft[0]--;
				} else {
					((Timer) e.getSource()).stop();
					veTau_Page.getLbl_ThoiGianGiuVe().setText("Thời gian đã hết!");
					xuLyHuyBoVeTam();
					JOptionPane.showMessageDialog(null, "Hủy bỏ danh sách vé tàu tạm thời thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					resetHuyBoVe();
				}
			}
		});

		countdownTimer.start();
	}

	private void stopTimers() {
		if (countdownTimer != null) {
			countdownTimer.stop();
			countdownTimer = null;
		}
	}

	private void khoaNhapDuLieuVeTauTam() {
		veTau_Page.getTxt_SDT().setEnabled(false);
		veTau_Page.getTxt_CCCD().setEnabled(false);
		veTau_Page.getTxt_HoTen().setEnabled(false);
		veTau_Page.getTxt_Email().setEnabled(false);
		veTau_Page.getTxt_NgaySinh().setEnabled(false);
		veTau_Page.getComboBox_GioiTinh().setEnabled(false);
		veTau_Page.getComboBox_LoaiKH().setEnabled(false);
	}

	private void xoaTrangInputVeTauTam() {
		veTau_Page.getTxt_SDT().setText("");
		veTau_Page.getTxt_CCCD().setText("");
		veTau_Page.getTxt_HoTen().setText("");
		veTau_Page.getTxt_Email().setText("");
		veTau_Page.getTxt_NgaySinh().setText("");
		veTau_Page.getComboBox_GioiTinh().setSelectedIndex(0);
		veTau_Page.getComboBox_LoaiKH().setSelectedIndex(0);
		veTau_Page.getTxt_MaKH().setText("");
		veTau_Page.getTxt_maVT().setText("");
	}

	private void xoaTrangInputVeTauTam1() {
		veTau_Page.getTxt_SDT().setText("{trống}");
		veTau_Page.getTxt_MaKH().setText("{trống}");
		veTau_Page.getTxt_CCCD().setText("{trống}");
		veTau_Page.getTxt_HoTen().setText("{trống}");
		veTau_Page.getTxt_Email().setText("{trống}");
		veTau_Page.getTxt_NgaySinh().setText("dd/mm/yyyy");
		veTau_Page.getComboBox_GioiTinh().setSelectedIndex(0);
		veTau_Page.getComboBox_LoaiKH().setSelectedIndex(0);
	}

	public double tinhGiaVeTamTinh() {
		double giaVeTamTinh = 0.0;
		JTable jTable = veTau_Page.getDanhSachVeTau();
		for (int i = 0; i < veTau_Page.getDanhSachVeTauModel().getRowCount(); i++) {
			double giaVeHienTai = Double.parseDouble(((String) jTable.getValueAt(i, 13)).replace(",", ""));
			giaVeTamTinh += giaVeHienTai;
		}
		return giaVeTamTinh;
	}

	private void capNhatTTVeTau() {
		KhachHang kh = getThongTinKHInput();

		JTable jTable = veTau_Page.getDanhSachVeTau();
		DefaultTableModel model = (DefaultTableModel) jTable.getModel();
		int selectedRow = jTable.getSelectedRow();

		model.setValueAt(kh.getMaKhachHang(), selectedRow, 4);
		model.setValueAt(kh.getHoTen(), selectedRow, 5);
		model.setValueAt(kh.getSoDienThoai(), selectedRow, 6);
		model.setValueAt(kh.getEmail(), selectedRow, 7);
		model.setValueAt(kh.isGioiTinh() ? "Nam" : "Nữ", selectedRow, 8);
		model.setValueAt(kh.getCCCD(), selectedRow, 9);
		model.setValueAt(kh.getNgaySinh() != null ? kh.getNgaySinh().toString() : "", selectedRow, 10);
		model.setValueAt(KhachHang.LoaiKhachHang.chuyenDoiTuEnumSangChuoi(kh.getLoaiKH()), selectedRow, 11);

		themThongTinInputTable(selectedRow);

		JOptionPane.showMessageDialog(null, "Cập nhật thông tin vé thành công!", "Thông báo",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// Kiểm tra điều kiện trước khi đưa vào table
	private boolean checkDieuKienInput() {
		if ((veTau_Page.getTxt_MaKH().getText().trim().equals("{trống}") || veTau_Page.getTxt_MaKH().getText() == null)
				&& (veTau_Page.getTxt_SDT().getText().trim().equals("{trống}")
						|| veTau_Page.getTxt_SDT().getText() == null)
				&& (veTau_Page.getTxt_CCCD().getText().trim().equals("{trống}")
						|| veTau_Page.getTxt_CCCD().getText() == null)
				&& (veTau_Page.getTxt_HoTen().getText().trim().equals("{trống}")
						|| veTau_Page.getTxt_HoTen().getText() == null)
				&& (veTau_Page.getTxt_Email().getText().trim().equals("{trống}")
						|| veTau_Page.getTxt_Email().getText() == null)
				&& (veTau_Page.getTxt_NgaySinh().getText().trim().equals("dd/mm/yyyy")
						|| veTau_Page.getTxt_NgaySinh().getText() == null)
				&& veTau_Page.getComboBox_GioiTinh().getSelectedIndex() == 0) {
			return true;
		} else {
			return false;
		}
	}

	private KhachHang getThongTinKHInput() {
		KhachHang khachHang = new KhachHang();
		maKhachHang = veTau_Page.getTxt_MaKH().getText();

		if (maKhachHang == null || maKhachHang.equals("{trống}")) {
			if (sttKH == 0) {
				maKhachHang = taoMaKhachHang(KhachHang_DAO.getInstance().getMaKHMax());
				sttKH++;
			} else {
				maKhachHang = taoMaVeTau(maKhachHang);
			}
		}

		khachHang.setMaKhachHang(maKhachHang);
		khachHang.setSoDienThoai(veTau_Page.getTxt_SDT().getText());
		khachHang.setCCCD(veTau_Page.getTxt_CCCD().getText());
		khachHang.setHoTen(veTau_Page.getTxt_HoTen().getText());
		khachHang.setEmail(veTau_Page.getTxt_Email().getText());

		String ngaySinhStr = veTau_Page.getTxt_NgaySinh().getText().trim();
		if (!ngaySinhStr.isEmpty() && !ngaySinhStr.equals("dd/mm/yyyy")) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				khachHang.setNgaySinh(LocalDate.parse(ngaySinhStr, formatter));
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ! Vui lòng nhập theo định dạng dd/MM/yyyy.",
						"Lỗi", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		} else {
			khachHang.setNgaySinh(null);
		}

		String gioiTinh = (String) veTau_Page.getComboBox_GioiTinh().getSelectedItem();
		khachHang.setGioiTinh(gioiTinh.equals("Nam"));

		String loaiKhachHang = (String) veTau_Page.getComboBox_LoaiKH().getSelectedItem();
		KhachHang.LoaiKhachHang loaiKH = KhachHang.LoaiKhachHang.chuyenDoiLoaiKH(loaiKhachHang);
		khachHang.setLoaiKH(loaiKH);
		return khachHang;
	}

	private void xoaVeTau() {
		DefaultTableModel model = (DefaultTableModel) veTau_Page.getDanhSachVeTau().getModel();
		JTable jTable = veTau_Page.getDanhSachVeTau();
		int selectedRow = jTable.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn vé tàu để xóa!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Xác nhận xóa
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa vé này?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {

			String maGheTau = (String) model.getValueAt(selectedRow, 3);
			GheTau_DAO.getInstance().updateTrangThaiGheTau(maGheTau, "TRONG");
			this.maVeTau = (String) model.getValueAt(selectedRow, 1);
			model.removeRow(selectedRow);
			capNhatGiaoDienGheTau(toaTauChon.getMaToaTau());

			capNhatMaVeTauSauKhiXoa(selectedRow);

			if (veTau_Page.getDanhSachVeTau().getRowCount() == 0) {
				resetHuyBoVe();
			} else {
				int tongSoVe = model.getRowCount();
				veTau_Page.getLbl_TongSoVe().setText(String.valueOf(tongSoVe));
				double tongTienTamTinh = tinhGiaVeTamTinh();
				veTau_Page.getLblTienTamTinh().setText(String.format("%,.0f ₫", tongTienTamTinh));
			}
			--sttVeTau;
			JOptionPane.showMessageDialog(null, "Xóa vé thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void capNhatMaVeTauSauKhiXoa(int selectedRow) {
		DefaultTableModel model = (DefaultTableModel) veTau_Page.getDanhSachVeTau().getModel();
		JTable jTable = veTau_Page.getDanhSachVeTau();
		int soLuong = model.getRowCount();
		for (int i = selectedRow; i < soLuong; i++) {
			jTable.setValueAt(i + 1, i, 0);
			String maVe = (String) model.getValueAt(i, 1);
			String maVeNew = taoMaVeTauGiam(maVe);
			model.setValueAt(maVeNew, i, 1);
		}
	}

	// Xử lý hoàn vé
	private boolean timKiemHoaDon() {
		String soDienThoaiOrCCCD = hoanTien_view.getSoDienThoaiRadioButton().isSelected()
				? hoanTien_view.getSoDienThoaiField().getText()
				: hoanTien_view.getCccdField().getText();

		ArrayList<HoaDon> hoaDons = HoaDon_DAO.getInstance().getHoaDonBySoDienThoaiOrCCCD(soDienThoaiOrCCCD);
		DefaultTableModel tableModel = (DefaultTableModel) hoanTien_view.getDanhSachVeTable().getModel();
		tableModel.setRowCount(0);

		int stt = 1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		for (HoaDon hoaDon : hoaDons) {
			String formattedNgayLap = hoaDon.getNgayLapHoaDon().format(formatter);
			if (!kiemTraVeNhom(hoaDon.getMaHoaDon())) {
				tableModel.addRow(new Object[] { stt++, hoaDon.getMaHoaDon(), hoaDon.getKhachHang().getMaKhachHang(),
						hoaDon.getKhachHang().getHoTen(), formattedNgayLap,
						String.format("%,.0f ₫", tinhTienHoaDon(hoaDon.getMaHoaDon())) });
			}
		}

		return !hoaDons.isEmpty();
	}

	// mới thêm vào
	private static double tinhTienHoaDon(String maHD) {
		double tongTien = 0;
		List<ChiTiet_HoaDon> dsChiTiet = ChiTiet_HoaDon_DAO.getInstance().getByMaHoaDon(maHD);
		for (ChiTiet_HoaDon chiTiet_HoaDon : dsChiTiet) {
			double giaVe = chiTiet_HoaDon.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getGiaVe()
					.getGiaVeHienTai(chiTiet_HoaDon.getVeTau().getGheTau().getTenLoaiGheTau());
			double giamGia = chiTiet_HoaDon.getKhuyenMai() == null ? 0 : chiTiet_HoaDon.getKhuyenMai().getGiamGia();
			double thanhTien = chiTiet_HoaDon.tinhThanhTien(giaVe, giamGia / 100);
			tongTien += thanhTien;

		}

		return tongTien;
	}

	private static boolean kiemTraThoiGianMuaVe(LocalDateTime thoiGian) {

		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(thoiGian, now);

		return duration.toHours() > 24;
	}

	private static double tinhTiLeHoanTien(String maHD) {
		HoaDon hoaDon = HoaDon_DAO.getInstance().getByMaHoaDon(maHD);
		return kiemTraThoiGianMuaVe(hoaDon.getNgayLapHoaDon()) ? 0.8 : 0;
	}

	private static double tinhTienHoanVe(String maHD) {
		double tiLeHoanTien = tinhTiLeHoanTien(maHD);
		return tinhTienHoaDon(maHD) * tiLeHoanTien;
	}

	public String taoMaPhieuHoanTien(String maPhieuHoanTien) {
		String preFix = "PHT";
		int newNumber = 1;
		if (maPhieuHoanTien != null && maPhieuHoanTien.startsWith(preFix)) {
			String currentNumber = maPhieuHoanTien.substring(3);
			newNumber = Integer.parseInt(currentNumber) + 1;
		}

		String maPhieu = preFix + String.format("%05d", newNumber);
		return maPhieu;
	}

	private PhieuHoanTien taoPhieuHoanTien() {
		String maPhieuHoanTienTemp = PhieuHoanTien_DAO.getInstance().getMaPhieuHoanTienMax();
		String maPhieuHoanTien = taoMaPhieuHoanTien(maPhieuHoanTienTemp);
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime ngayHoanTien = LocalDateTime.parse(thongTinVe.getNgayHoanTienLabel().getValue(), inputFormatter);
		String formattedNgayHoanTien = ngayHoanTien.format(outputFormatter);
		LocalDateTime finalNgayHoanTien = LocalDateTime.parse(formattedNgayHoanTien, outputFormatter);
//		    String lyDoChinh =  thongTinVe. .getSelectedItem().toString();
		String lyDoHoanTien = thongTinVe.getLyDoNgoaiLeTextArea().getText();
		String ghiChu = thongTinVe.getGhiChuLabel().getValue();
		float tiLeHoanTien = Float.parseFloat(thongTinVe.getTiLeHoanTienField().getText().replace("%", ""));
		KhachHang khachHang = new KhachHang(thongTinVe.getMaKhachHangLabel().getValue());

		return new PhieuHoanTien(maPhieuHoanTien, finalNgayHoanTien, (tiLeHoanTien / 100), lyDoHoanTien, ghiChu,
				khachHang);
	}

	private boolean themPhieuHoanTienMoi(PhieuHoanTien phieuHoanTien) {
		phieuHoanTien = taoPhieuHoanTien();
		boolean daThem = PhieuHoanTien_DAO.getInstance().themPhieuHoanTien(phieuHoanTien);
		if (daThem) {
			thayDoiTrangThaiDaHuy(thongTinVe.getMaKhachHangLabel().getValue(),
					thongTinVe.getMaHoaDonLabel().getValue());
		}
		return daThem;
	}

	private void lamMoiDoiTraVe() {
		hoanTien_view.getTenKhachHangField().setText("");
		hoanTien_view.getSoDienThoaiField().setText("");
		hoanTien_view.getSoDienThoaiRadioButton().setSelected(false);
		hoanTien_view.getCccdRadioButton().setSelected(false);
		hoanTien_view.getCccdField().setText("");
		((DefaultTableModel) hoanTien_view.getDanhSachVeTable().getModel()).setRowCount(0);
		hoanTien_view.getTenKhachHangField().requestFocus();
		hoanTien_view.getXacNhanButton().setVisible(false);
	}

	private boolean thayDoiTrangThaiDaHuy(String maKH, String maHoaDon) {
		return VeTau_DAO.getInstance().capNhatTrangThaiVeTau(maKH, maHoaDon);
	}

	private boolean kiemTraVeNhom(String maHoaDon) {
		return HoaDon_DAO.getInstance().laySoLuongHoaDon(maHoaDon) > 1 ? true : false;
	}

	public static void inPhieuHoanTien(String maPhieuHoanTien) {
		try {
			PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
			if (printServices.length > 0) {
				PrintService printer = printServices[1];

				PhieuHoanTien phieuHoanTien = PhieuHoanTien_DAO.getInstance().layPhieuHoanTienBangMa(maPhieuHoanTien);
				if (phieuHoanTien == null) {
					System.out.println("Không tìm thấy phiếu hoàn tiền nào!");
					return;
				}

				StringBuilder hoanTienContent = new StringBuilder();
				String maHoaDon = PhieuHoanTien_DAO.getInstance().getMaHoaDonByMaPhieuHoanTien(maPhieuHoanTien);
				double tienHoan = tinhTienHoanVe(maHoaDon);

				hoanTienContent.append("PHIEU HOAN TIEN\n\n");
				hoanTienContent.append(String.format("Ma phieu hoan tien: %s\n", phieuHoanTien.getMaPhieuHoanTien()));
				hoanTienContent.append(String.format("Ten khach hang: %s\n", phieuHoanTien.getKhachHang().getHoTen()));
				hoanTienContent
						.append(String.format("Ngay hoan tien: %s\n", phieuHoanTien.getNgayHoanTien().toString()));
				hoanTienContent.append("---------------------------------\n");
				hoanTienContent.append(String.format("Ly do hoan tra: %s\n", phieuHoanTien.getLyDoHoanTien()));
				hoanTienContent.append(String.format("Ghi chu: %s\n", phieuHoanTien.getGhiChu()));
				hoanTienContent.append(String.format("So tien hoan tra: %.2f\n", tienHoan));
				hoanTienContent.append("\n\n");
				hoanTienContent.append("Cam on quy khach da su dung dich vu cua chung toi!\n");
				hoanTienContent.append("\n");
				hoanTienContent.append("\n");
				hoanTienContent.append("\n");
				hoanTienContent.append("\n");
				hoanTienContent.append("\n");

				DocPrintJob job = printer.createPrintJob();
				InputStream stream = new ByteArrayInputStream(hoanTienContent.toString().getBytes());
				Doc doc = new SimpleDoc(stream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
				job.print(doc, null);

				System.out.println("In phiếu hoàn tiền thành công!");
			} else {
				System.out.println("Không tìm thấy máy in nào!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String formatPhieuHoanTien() {
		StringBuilder hoanTienContent = new StringBuilder();

		hoanTienContent.append("PHIEU HOAN TIEN\n\n");

		hoanTienContent.append(String.format("Ten khach hang: %s\n", thongTinVe.getTenKhachHangLabel().getValue()));
		hoanTienContent.append(String.format("Ngay hoan tien: %s\n", thongTinVe.getNgayHoanTienLabel().getValue()));
		hoanTienContent.append("---------------------------------\n");
		hoanTienContent.append(String.format("Ly do hoan tra: %s\n", thongTinVe.getLyDoNgoaiLeTextArea().getText()));
		hoanTienContent.append(String.format("Ti le hoan tien: %s\n", thongTinVe.getTiLeHoanTienField().getText()));
		hoanTienContent.append(String.format("So tien hoan tra: %s\n", thongTinVe.getTienHoanField().getText()));
		hoanTienContent.append(String.format("Ghi chu: %s\n", thongTinVe.getGhiChuLabel().getValue()));
		hoanTienContent.append("\n\n");
		hoanTienContent.append("Cam on quy khach da su dung dich vu cua chung toi!\n");
		hoanTienContent.append("\n");
		hoanTienContent.append("\n");
		hoanTienContent.append("\n");
		hoanTienContent.append("\n");
		hoanTienContent.append("\n");

		return hoanTienContent.toString();
	}

	public void showPhieuHoanTienPreview() {
		String formattedPhieuHoanTien = formatPhieuHoanTien();
		JOptionPane.showMessageDialog(null, formattedPhieuHoanTien, "Xem trước biên lai",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// Xử lý thanh toán
	private void themDataThanhToan() {
		themDataThanhToanInput();
		themDataThanhToantable();
		taoVeTau();
	}

	private void taoVeTau() {

	}

	private void themDataThanhToantable() {
		JTable veTauTable = veTau_Page.getDanhSachVeTau();
		JTable hoaDonTable = thanhToan_Page.getDanhSachHoaDonJTable();
		dsTienChiTietVe = new ArrayList<Double>();
		thanhToan_Page.getDanhSachHoaDonModel().setRowCount(0);
		double giamGiaKhac = 0.0;
		double thueSuat = 10.0;
		int soLuong = 1;
		HoaDon hoaDon = new HoaDon();
		ChiTiet_HoaDon chiTiet_HoaDon = new ChiTiet_HoaDon();
		KhachHang kh = new KhachHang();

		for (int i = 0; i < veTauTable.getRowCount(); i++) {
			String maVe = (String) veTauTable.getValueAt(i, 1);
			String loaiVe = (String) veTauTable.getValueAt(i, 2);
			String loaiKH = (String) veTauTable.getValueAt(i, 11);
			String giaVeString = (String) veTauTable.getValueAt(i, 13);
			Double giaVe = convertStringToDouble(giaVeString);
			Double giamGia = kh.getLoaiKH().chuyenDoiLoaiKH(loaiKH).getDiscount();

			Double thanhTienChiTiet = chiTiet_HoaDon.tinhThanhTien(giaVe, giamGia + giamGiaKhac);
			dsTienChiTietVe.add(thanhTienChiTiet);

			DefaultTableModel model = (DefaultTableModel) hoaDonTable.getModel();
			model.addRow(new Object[] { i + 1, // STT
					maVe, loaiVe, soLuong, giaVeString, kh.loaiKH.chuyenDoiDiscountToString(giamGia), giamGiaKhac,
					String.format("%,.0f ₫", roundMoney(thanhTienChiTiet)) });
		}
		double tongTienHoaDon = roundMoney(hoaDon.tinhTongTien(dsTienChiTietVe));
		tongTienThanhToan = roundMoney(tongTienHoaDon + tongTienHoaDon * 0.1);

		thanhToan_Page.getLbl_TongTien().setText(String.format("%,.0f VND", tongTienHoaDon));
		thanhToan_Page.getLbl_SauThue().setText(String.format("%,.0f VND", tongTienHoaDon * 0.1));
		thanhToan_Page.getLbl_TongTienThanhToan().setText(String.format("%,.0f VND", tongTienThanhToan));

		thanhToan_Page.getPanel_GoiYTien().removeAll();
		thanhToan_Page.getPanel_GoiYTien().revalidate();
		thanhToan_Page.getPanel_GoiYTien().repaint();
		thanhToan_Page.getPanel_GoiYTien().add(createPaymentSuggestionPanel(tongTienThanhToan));

	}

	private void themDataThanhToanInput() {
		JTable jTable = veTau_Page.getDanhSachVeTau();
		thanhToan_Page.getLbl_MaNV().setText("NV00001");
		thanhToan_Page.getLbl_HoTenNV().setText("Nguyễn Anh Tùng");
		thanhToan_Page.getLbl_MaKH().setText((String) jTable.getValueAt(0, 4));
		thanhToan_Page.getLbl_HoTenKH().setText((String) jTable.getValueAt(0, 5));
		thanhToan_Page.getLbl_SoDT().setText((String) jTable.getValueAt(0, 6));
		thanhToan_Page.getLbl_HinhThucThanhToan().setText(phuongThucThanhToan);
		;
		thanhToan_Page.getPanel_Tien().add(createListMoneyButton());
	}

	// Hàm gợi ý các số tiền khách có khả năng đưa
	public static List<Double> suggestPayment(double totalAmount) {
		List<Double> suggestions = new ArrayList<>();

		suggestions.add(totalAmount);

		double firstSuggestion = Math.ceil(totalAmount / 5000) * 5000;
		if (!suggestions.contains(firstSuggestion)) {
			suggestions.add(firstSuggestion);
		}

		double secondSuggestion = Math.ceil(totalAmount / 10_000) * 10_000;
		if (!suggestions.contains(secondSuggestion)) {
			suggestions.add(secondSuggestion);
		}

		double thirdSuggestion = Math.ceil(totalAmount / 50_000) * 50_000;
		if (!suggestions.contains(thirdSuggestion)) {
			suggestions.add(thirdSuggestion);
		}

		double[] largeDenominations = { 200_000, 500_000, 1_000_000, 2_000_000, 5_000_000 };
		for (double denom : largeDenominations) {
			if (denom > totalAmount && !suggestions.contains(denom)) {
				suggestions.add(denom);
			}
		}

		return suggestions;
	}

	public JPanel createListMoneyButton() {
		JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
		panel.setBackground(Color.WHITE);
		String[] moneyValues = { "500.000", "200.000", "100.000", "50.000", "20.000", "10.000", "5.000", "2.000",
				"1.000" };
		for (String value : moneyValues) {
			JButton button = new JButton(value);
			button.setFont(new Font("Arial", Font.BOLD, 20));
			button.setBackground(ColorConstants.SECONDARY_COLOR);
			button.setForeground(Color.WHITE);
			button.setFocusable(false);

			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					double amount = Double.parseDouble(button.getText().replace(".", "").trim());
					tienKhachDua += amount;
					thanhToan_Page.getTxt_TienKhachDua().setText(formatCurrency(tienKhachDua));
					if (tienKhachDua >= tongTienThanhToan) {
						thanhToan_Page.getTxt_TienTraLai().setText(formatCurrency(tienKhachDua - tongTienThanhToan));
					}
				}
			});

			panel.add(button);
		}
		return panel;
	}

	public static String formatCurrency(double amount) {
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return currencyFormatter.format(amount).replace("₫", "").trim();
	}

	public JPanel createPaymentSuggestionPanel(double totalAmount) {
		List<Double> suggestions = suggestPayment(totalAmount);
		List<Double> limitedSuggestions = suggestions.subList(0, Math.min(suggestions.size(), 6));

		JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
		panel.setBackground(Color.WHITE);

		for (double suggestion : limitedSuggestions) {
			JButton button = new JButton(formatCurrency(suggestion));
			button.setForeground(Color.WHITE);
			button.setBackground(ColorConstants.SECONDARY_COLOR);
			button.setFont(new Font("Arial", Font.BOLD, 20));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						thanhToan_Page.getTxt_TienTraLai().setText("");
						String sanitizedText = button.getText().replace("\u00A0", "").replaceAll("[^\\d]", "");
						tienKhachDua = Double.parseDouble(sanitizedText);
						thanhToan_Page.getTxt_TienKhachDua().setText(formatCurrency(tienKhachDua));
						if (tienKhachDua >= tongTienThanhToan) {
							thanhToan_Page.getTxt_TienTraLai()
									.setText(formatCurrency(tienKhachDua - tongTienThanhToan));
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Lỗi định dạng số tiền: " + button.getText(),
								"Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			panel.add(button);
		}

		return panel;
	}

	public boolean xuLyThuTien() {
		themDanhSachKhachHangDB();

		if (themDataHoaDon()) {
			return true;
		}

		return false;
	}

	private void resetSauThanhToan() {

		veTau_Page.getDanhSachVeTauModel().setRowCount(0);

		resetThongTinKhachHang();

		thanhToan_Page.getDanhSachHoaDonModel().setRowCount(0);
		thanhToan_Page.getLbl_TongTien().setText("0");
		thanhToan_Page.getLbl_SauThue().setText("0");
		thanhToan_Page.getLbl_TongTienThanhToan().setText("0");
		thanhToan_Page.getTxt_TienKhachDua().setText("");
		thanhToan_Page.getTxt_TienTraLai().setText("");
		tongSoVeTamThoi = 0;
		tongTienThanhToan = 0;
		sttVeTau = 0;
		tienKhachDua = 0;
		ghiChu = "trống";
		maHoaDon = null;

		resetHuyBoVe();
	}

	private void resetThongTinKhachHang() {
		veTau_Page.getTxt_MaKH().setText("");
		veTau_Page.getTxt_SDT().setText("");
		veTau_Page.getTxt_CCCD().setText("");
		veTau_Page.getTxt_HoTen().setText("");
		veTau_Page.getTxt_Email().setText("");
		veTau_Page.getTxt_NgaySinh().setText("dd/mm/yyyy");
		veTau_Page.getComboBox_GioiTinh().setSelectedIndex(0);
		veTau_Page.getComboBox_LoaiKH().setSelectedIndex(0);
	}

	public boolean themDataHoaDon() {
		ThongTinTram thongTinTram = new ThongTinTram("NG0002");
		JTable jTable_Ds = veTau_Page.getDanhSachVeTau();
		NhanVien nhanVien = new NhanVien("NV00001");
		HoaDon hoaDon = new HoaDon();
		KhachHang khachHang = new KhachHang((String) jTable_Ds.getValueAt(0, 4));

		boolean temp = false;

		hoaDon.setMaHoaDon(taoMaHoaDon(HoaDon_DAO.getInstance().getMaHoaDonMaxTrongThang()));
		hoaDon.setNgayLapHoaDon(LocalDateTime.now());
		hoaDon.setGhiChu(ghiChu == null || ghiChu.equals("") ? "trống" : ghiChu);
		hoaDon.setThueVAT(10);
		hoaDon.setPhuongThucThanhToan(phuongThucThanhToan);
		hoaDon.setLoaiHoaDon(thanhToan_Page.getComboBox_LoaiHD().getSelectedIndex() == 0 ? "ThanhToan" : "GiuCho");
		hoaDon.setKhachHang(khachHang);
		hoaDon.setThongTinTram(thongTinTram);
		hoaDon.setNhanVien(nhanVien);
		hoaDon.setThongTinGiuCho(null);
		this.maHoaDon = hoaDon.getMaHoaDon();

		if (HoaDon_DAO.getInstance().addHoaDon(hoaDon)) {

			for (int i = 0; i < jTable_Ds.getRowCount(); i++) {
				VeTau veTau = new VeTau();
				veTau.setMaVeTau((String) jTable_Ds.getValueAt(i, 1));
				veTau.setLoaiVe(((String) jTable_Ds.getValueAt(i, 2)).equals("VIP") ? true : false);
				veTau.setDaHuy(false);
				veTau.setNgayHetHan(chuyenTauChon.getThoiGianKhoiHanh());
				veTau.setGheTau(new GheTau((String) jTable_Ds.getValueAt(i, 3)));
				veTau.setKhachHang(new KhachHang((String) jTable_Ds.getValueAt(i, 4)));

				GheTau_DAO.getInstance().updateTrangThaiGheTau((String) jTable_Ds.getValueAt(i, 3), "DA_THANH_TOAN");

				if (VeTau_DAO.getInstance().themVeTau(veTau)) {
					ChiTiet_HoaDon chiTiet_HoaDon = new ChiTiet_HoaDon();
					chiTiet_HoaDon.setHoaDon(hoaDon);
					chiTiet_HoaDon.setSoLuong(1);
					chiTiet_HoaDon.setKhuyenMai(null);
					chiTiet_HoaDon.setVeTau(veTau);
					if (ChiTiet_HoaDon_DAO.getInstance().add(chiTiet_HoaDon)) {
						temp = true;
					}
				}
			}
		}
		return temp;
	}

	public String taoMaHoaDon(String maHoaDonMax) {
		String prefix = "HD";
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
		String yearMonth = today.format(formatter);
		int newNumber = 1;
		if (maHoaDonMax != null && maHoaDonMax.startsWith(prefix + yearMonth)) {
			String currentNumberStr = maHoaDonMax.substring(6);
			newNumber = Integer.parseInt(currentNumberStr) + 1;
		}

		String maHoaDon = prefix + yearMonth + String.format("%05d", newNumber);
		return maHoaDon;
	}

	public boolean themDanhSachKhachHangDB() {
		KhachHang khachHang = new KhachHang();
		JTable jTable = veTau_Page.getDanhSachVeTau();
		boolean isSuccess = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		for (int i = 0; i < jTable.getRowCount(); i++) {
			khachHang.setMaKhachHang((String) jTable.getValueAt(i, 4));
			khachHang.setHoTen((String) jTable.getValueAt(i, 5));
			khachHang.setSoDienThoai((String) jTable.getValueAt(i, 6));
			khachHang.setEmail((String) jTable.getValueAt(i, 7));
			khachHang.setGioiTinh(((String) jTable.getValueAt(i, 8)).equals("Nam") ? true : false);
			khachHang.setCCCD((String) jTable.getValueAt(i, 9));
			khachHang.setNgaySinh(LocalDate.parse((String) jTable.getValueAt(i, 10), formatter));
			khachHang.setLoaiKH(LoaiKhachHang.chuyenDoiLoaiKH(((String) jTable.getValueAt(i, 11)).trim()));
			// Kiểm tra sự tồn tại của khách hàng
			KhachHang existingCustomer = KhachHang_DAO.getInstance().findKhachHangByCCCDOrSDT(khachHang.getCCCD(),
					khachHang.getSoDienThoai());
			if (existingCustomer == null) {
				if (KhachHang_DAO.getInstance().insertKhachHang(khachHang)) {
					isSuccess = true;
				}
			}
		}
		return isSuccess;
	}

	// Hàm làm tròn tiền
	public static double roundMoney(double amount) {
		return Math.round(amount / 1000) * 1000;
	}

	public void inVeTau(String maHoaDon) {
		try {
			PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

			if (printServices.length > 0) {
				PrintService printer = printServices[1];

				List<Map<String, Object>> ticketInfoList = HoaDon_DAO.getInstance().getThongTinHoaDon(maHoaDon);

				if (ticketInfoList.isEmpty()) {
					System.out.println("Không có chi tiết hóa đơn nào để in.");
					return;
				}

				StringBuilder billsContent = new StringBuilder();

				for (Map<String, Object> ticketInfo : ticketInfoList) {
					ticketInfo.put("tenNhaGa", removeAccent((String) ticketInfo.get("tenNhaGa")));
					ticketInfo.put("diaChiNhaGa", removeAccent((String) ticketInfo.get("diaChiNhaGa")));
					ticketInfo.put("dienThoaiNhaGa", ticketInfo.get("dienThoaiNhaGa"));
					ticketInfo.put("hoTenKhachHang", removeAccent((String) ticketInfo.get("hoTenKhachHang")));
					ticketInfo.put("soDienThoaiKH", ticketInfo.get("soDienThoaiKH"));
					ticketInfo.put("cccdKH", ticketInfo.get("cccdKH"));
					ticketInfo.put("maVeTau", ticketInfo.get("maVeTau"));
					ticketInfo.put("loaiVe", removeAccent((String) ticketInfo.get("loaiVe")));
					ticketInfo.put("gaKhoiHanh", removeAccent((String) ticketInfo.get("gaKhoiHanh")));
					ticketInfo.put("gaDen", removeAccent((String) ticketInfo.get("gaDen")));
					ticketInfo.put("thoiGianKhoiHanh", ticketInfo.get("thoiGianKhoiHanh"));
					ticketInfo.put("maTau", ticketInfo.get("maTau"));
					ticketInfo.put("soThuTuToa", ticketInfo.get("soThuTuToa"));
					ticketInfo.put("soThuTuGhe", ticketInfo.get("soThuTuGhe"));

					billsContent.append(generateTicket(ticketInfo));
				}

				InputStream is = new ByteArrayInputStream(billsContent.toString().getBytes());
				DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
				Doc doc = new SimpleDoc(is, flavor, null);

				DocPrintJob printJob = printer.createPrintJob();
				printJob.print(doc, null);
				System.out.println("Printed bills successfully!");
			} else {
				System.out.println("No printers available.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String removeAccent(String text) {
		text = text.replace("Đ", "D").replace("đ", "d");
		String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
		String noAccent = normalized.replaceAll("\\p{M}", "");
		noAccent = noAccent.replaceAll("[^\\p{ASCII}]", "");

		return noAccent;
	}

	public String generateTicket(Map<String, Object> ticketInfo) {
		StringBuilder ticket = new StringBuilder();

		ticket.append("             VE TAU\n\n");
		ticket.append(String.format("Nha ga: %s\n", ticketInfo.get("tenNhaGa")));
		ticket.append(String.format("Dia chi: %s\n", ticketInfo.get("diaChiNhaGa")));
		ticket.append(String.format("Dien thoai: %s\n", ticketInfo.get("dienThoaiNhaGa")));
		ticket.append("-------------------------------\n");
		ticket.append(String.format("Hanh khach: %s\n", ticketInfo.get("hoTenKhachHang")));

		String soDienThoaiMasked = maskString((String) ticketInfo.get("soDienThoaiKH"), 3, 2);
		ticket.append(String.format("So dien thoai: %s\n", soDienThoaiMasked));

		String cccdMasked = maskString((String) ticketInfo.get("cccdKH"), 4, 3);
		ticket.append(String.format("CCCD: %s\n", cccdMasked));

		ticket.append("-------------------------------\n");
		ticket.append(String.format("Ma Tau: %s\n", ticketInfo.get("maTau")));
		ticket.append(String.format("So thu tu toa: %s\n", ticketInfo.get("soThuTuToa")));
		ticket.append(String.format("Vi tri ghe: %s\n", ticketInfo.get("soThuTuGhe")));
		ticket.append(String.format("Ma ve tau: %s\n", ticketInfo.get("maVeTau")));
		ticket.append(String.format("Loai ve: %s\n", ticketInfo.get("loaiVe")));
		ticket.append(String.format("Ga khoi hanh: %s\n", ticketInfo.get("gaKhoiHanh")));
		ticket.append(String.format("Ga den: %s\n", ticketInfo.get("gaDen")));

		String thoiGianKhoiHanh = (String) ticketInfo.get("thoiGianKhoiHanh");
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm 'ngay' dd/MM/yyyy");

		LocalDateTime dateTime = LocalDateTime.parse(thoiGianKhoiHanh, inputFormatter);
		String formattedTime = dateTime.format(outputFormatter);
		ticket.append(String.format("Thoi gian khoi hanh: %s\n", formattedTime));

		ticket.append("\n");
		ticket.append("Cam on ban da su dung dich vu cua chung toi!\n");
		ticket.append("\n");
		ticket.append("\n");
		ticket.append("\n");

		return ticket.toString();
	}

	public static String maskString(String input, int unmaskedStart, int unmaskedEnd) {
		if (input == null || input.length() <= (unmaskedStart + unmaskedEnd)) {
			return input;
		}

		int maskLength = input.length() - unmaskedStart - unmaskedEnd;
		StringBuilder masked = new StringBuilder(input.substring(0, unmaskedStart));
		for (int i = 0; i < maskLength; i++) {
			masked.append("*");
		}
		masked.append(input.substring(input.length() - unmaskedEnd));
		return masked.toString();
	}

	public String capitalizeName(String name) {
		if (name == null || name.isEmpty()) {
			return name;
		}

		String[] words = name.split(" ");
		StringBuilder capitalizedName = new StringBuilder();

		for (String word : words) {
			if (!word.isEmpty()) {
				capitalizedName.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase())
						.append(" ");
			}
		}

		return capitalizedName.toString().trim();
	}

	// Xử lý sự kiện
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj.equals(chonGhe_View.getJcombobox_gadi())) {
			selectedTinhThanhDi = (TinhThanh) chonGhe_View.getJcombobox_gadi().getSelectedItem();
		} else if (obj.equals(chonGhe_View.getJcombobox_gaden())) {
			selectedTinhThanhDen = (TinhThanh) chonGhe_View.getJcombobox_gaden().getSelectedItem();
		} else if (obj.equals(chonGhe_View.getBtnTimKiemChuyenTau())) {
			if (!kiemTraNgay())
				return;
			if (!kiemTraTinhThanh())
				return;
			lamMoiToaTau_GheTau();
			timKiemChuyenTauDi(ngayDi);
			isBtnClicked = true;
		} else if (obj.equals(chonGhe_View.getBtn_TiepTheo())) {
			if (!kiemTraNgay())
				return;

			if (!kiemTraTinhThanh())
				return;

			if (checkSoLuongVeChieuDi()) {
				lamMoiToaTau_GheTau();
				timKiemChuyenTauDiVe(ngayVe);
				isBtnTiepTheoClick = true;
				lamMoi_ChuyenTau_Tau_ToaTau_GheTau();
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn ghế chiều đi!");
			}
		} else if (obj.equals(chonGhe_View.getBtn_QuayLai())) {
			if (!kiemTraNgay())
				return;
			timKiemChuyenTauDi(ngayDi);
			isBtnTiepTheoClick = false;
			lamMoi_ChuyenTau_Tau_ToaTau_GheTau();
			lamMoiToaTau_GheTau();
		} else if (obj.equals(chonGhe_View.getRdbtn_MotChieu())) {
			chonGhe_View.getBtn_QuayLai().setVisible(false);
			chonGhe_View.getBtn_TiepTheo().setVisible(false);
			chonGhe_View.getLbl_VeChieuDi().setVisible(false);
			chonGhe_View.getLbl_VeChieuVe().setVisible(false);
			chonGhe_View.getLbl_SoVeChieuDi().setVisible(false);
			chonGhe_View.getLbl_SoVeChieuVe().setVisible(false);
			chonGhe_View.getLbl_TongSoVeTamThoi().setVisible(true);
			chonGhe_View.getDateChooser_NgayVe().setEnabled(false);
		} else if (obj.equals(chonGhe_View.getRdbtn_KhuHoi())) {
			chonGhe_View.getBtn_QuayLai().setVisible(true);
			chonGhe_View.getBtn_TiepTheo().setVisible(true);
			chonGhe_View.getLbl_VeChieuDi().setVisible(true);
			chonGhe_View.getLbl_VeChieuVe().setVisible(true);
			chonGhe_View.getLbl_SoVeChieuDi().setVisible(true);
			chonGhe_View.getLbl_SoVeChieuVe().setVisible(true);
			chonGhe_View.getLbl_TongSoVeTamThoi().setVisible(false);
			chonGhe_View.getDateChooser_NgayVe().setEnabled(true);
		} else if (obj.equals(chonGhe_View.getBtn_LamMoi())) {
			lamMoi();
		} else if (obj.equals(chonGhe_View.getCombobox_KhungGio())) {
			if (isBtnClicked) {
				locChuyenTau(danhSachChuyenTau);
			} else {
				danhSachChuyenTau = ChuyenTau_DAO.getInstance().getAll();
				locChuyenTau(danhSachChuyenTau);
			}
		} else if (obj.equals(chonGhe_View.getBtn_ChonNhanh())) {
			if (chuyenTauChon != null) {
				if (chonNhanhGheTauNgauNhien()) {
					if (!isThemVeTau) {
						if (chonGhe_View.getRdbtn_KhuHoi().isSelected()) {
							if (isBtnTiepTheoClick) {
								isThemVeTau = true;
							}
							isThemVeTau = false;
						} else {
							isThemVeTau = true;
						}
						startCountdownTimer();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyến tàu!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		// Xử lý sự kiện vé tàu
		else if (obj.equals(veTau_Page.getBtn_ThanhToan())) {
			if (veTau_Page.getDanhSachVeTau().getRowCount() > 0) {
				thanhToan_Page.setVisible(true);
				thanhToan_Page.setResizable(false);
				themDataThanhToan();
			} else {
				JOptionPane.showMessageDialog(null, "Danh sách vé tàu tạm thời trống!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (obj.equals(veTau_Page.getBtn_XoaVeTau())) {
			if (veTau_Page.getDanhSachVeTauModel().getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Danh sách vé tàu tạm thời trống!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			} else {
				xoaVeTau();
			}
		} else if (obj.equals(veTau_Page.getBtn_CapNhatTT())) {
			if (veTau_Page.getDanhSachVeTauModel().getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Danh sách vé tàu tạm thời trống!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			} else if (veTau_Page.getDanhSachVeTau().getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một vé để cập nhật!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			} else if (checkDieuKienInput()) {
				JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				capNhatTTVeTau();
			}
		}
		// Xử lý sự kiện hủy bỏ vé tạm thời
		else if (obj.equals(veTau_Page.getBtn_HuyBo())) {
			int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				if (xuLyHuyBoVeTam()) {
					JOptionPane.showMessageDialog(null, "Hủy bỏ danh sách vé tàu tạm thời thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					resetHuyBoVe();
				} else {
					JOptionPane.showMessageDialog(null, "Hủy bỏ danh sách vé tàu tạm thời không thành công!",
							"Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (obj.equals(veTau_Page.getBtn_XoaTrang())) {
			if (veTau_Page.getDanhSachVeTau().getRowCount() > 0) {
				xoaTrangInputVeTauTam1();
			}
		}

		// Xử lý sự kiện hoàn vé
		else if (obj.equals(hoanTien_view.getTimKiemButton())) {
			if (!timKiemHoaDon()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn nào!", "Thông báo",
						javax.swing.JOptionPane.INFORMATION_MESSAGE);
			} else {
				hoanTien_view.getXacNhanButton().setVisible(true);
			}

		} else if (obj.equals(thongTinVe.getHoanVeButton())) {
			PhieuHoanTien phieuHoanTien = taoPhieuHoanTien();
			if (themPhieuHoanTienMoi(phieuHoanTien)) {
				JOptionPane.showMessageDialog(null, "Thêm phiếu hoàn tiền thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				timKiemHoaDon();
				thongTinVe.setVisible(false);
				
			} else {
				JOptionPane.showMessageDialog(null, "Thêm phiếu hoàn tiền thất bại!", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (obj.equals(hoanTien_view.getLamMoiButton())) {
			lamMoiDoiTraVe();
		} else if (obj.equals(hoanTien_view.getXacNhanButton())) {
			int selectedRow = hoanTien_view.getDanhSachVeTable().getSelectedRow();
			if (selectedRow != -1) {

				// Hiển thị cửa sổ thông tin vé tàu
				thongTinVe.getMaHoaDonLabel()
						.setValue(hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 1).toString());
				thongTinVe.getMaKhachHangLabel()
						.setValue(hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 2).toString());
				thongTinVe.getTenKhachHangLabel()
						.setValue(hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 3).toString());
				thongTinVe.getNgayMuaLabel()
						.setValue(hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 4).toString());
				thongTinVe.getNgayHoanTienLabel().setValue(LocalDateTime.now().format(formatterNgayGio));
				thongTinVe.getSoTienDaThanhToanLabel()
						.setValue(hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 5).toString());
				thongTinVe.getSoDienThoaiLabel().setValue(hoanTien_view.getSoDienThoaiField().getText());
				String maHD = hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 1).toString();
				double tienHoan = tinhTienHoanVe(maHD);
				double tiLeHoanTien = tinhTiLeHoanTien(maHD);
				if (tiLeHoanTien == 0) {
					thongTinVe.getLyDoNgoaiLeTextArea().setText("Thời gian trả vé đã hết hạn");
				}
				thongTinVe.getTiLeHoanTienField().setText(tiLeHoanTien * 100 + "%");
				thongTinVe.getTienHoanField().setText(String.format("%,.0f ₫", tienHoan));
				thongTinVe.setVisible(true);

			}
		} else if (obj.equals(thongTinVe.getInPDFButton())) {
			String maPhieuHoanTien = PhieuHoanTien_DAO.getInstance()
					.getMaPhieuHoanTienByMaHoaDon(thongTinVe.getMaHoaDonLabel().getValue());
			try {
				inPhieuHoanTien(maPhieuHoanTien);
				thongTinVe.setVisible(false);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Không thể in phiếu hoàn tiền!", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj.equals(thongTinVe.getHoanVeButton_1())) {
			showPhieuHoanTienPreview();
		}

		// Xử lý sự kiện thanh toán
		else if (obj.equals(thanhToan_Page.getBtn_QuayLai())) {
			thanhToan_Page.setVisible(false);
		} else if (obj.equals(thanhToan_Page.getBtn_ThuTien())) {

			if (xuLyThuTien()) {
				int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Thu thanh toán thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					inVeTau(maHoaDon);
					resetSauThanhToan();
					thanhToan_Page.setVisible(false);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thu thanh toán không thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (obj.equals(thanhToan_Page.getBtn_ThemGhiChu())) {
			String note = thanhToan_Page.showNoteInputDialog(null, "Nhập ghi chú", "Vui lòng nhập ghi chú:", ghiChu);

			if (note != null && !note.isEmpty()) {
				ghiChu = note;
			} else {
				JOptionPane.showMessageDialog(null, "Ghi chú không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj.equals(veTau_Page.getDanhSachVeTau())) {
			int selectRow = veTau_Page.getDanhSachVeTau().getSelectedRow();
			moNhapDuLieuVeTauTam();
			themThongTinInputTable(selectRow);
		} else if (obj.equals(veTau_Page.getTxt_NgaySinh())) {
			SwingUtilities.invokeLater(() -> {
				veTau_Page.getTxt_NgaySinh().setSelectionStart(0);
				veTau_Page.getTxt_NgaySinh().setSelectionEnd(2);
			});
		} else if (obj.equals(hoanTien_view.getDanhSachVeTable())) {
			int selectedRow = hoanTien_view.getDanhSachVeTable().getSelectedRow();
			if (selectedRow != -1) {
				String maHoaDon = hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 1).toString();
				if (kiemTraVeNhom(maHoaDon)) {
					JOptionPane.showMessageDialog(null, "Vé nhóm không được hoàn tiền!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {
		boiDenDuLieu();
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object obj = e.getSource();
		if (obj.equals(veTau_Page.getTxt_NgaySinh())) {
			JTextField txtNgaySinh = veTau_Page.getTxt_NgaySinh();
			String text = txtNgaySinh.getText().trim();
			if (!text.matches("\\d{2}/\\d{2}/\\d{4}")) {
				JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ! Vui lòng nhập theo định dạng dd/MM/yyyy.",
						"Lỗi", JOptionPane.ERROR_MESSAGE);
				txtNgaySinh.requestFocus();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object obj = e.getSource();

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			manager.focusNextComponent();
			Object source = e.getSource();

			if (source.equals(veTau_Page.getTxt_SDT())) {
				String soDienThoai = veTau_Page.getTxt_SDT().getText();
				if (!soDienThoai.matches("^\\d{10}$")) {
					JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ! Vui lòng nhập đúng 10 chữ số.",
							"Lỗi", JOptionPane.ERROR_MESSAGE);
					veTau_Page.getTxt_SDT().requestFocus();
				}
			} else if (source.equals(veTau_Page.getTxt_CCCD())) {
				String cccd = veTau_Page.getTxt_CCCD().getText();
				if (!cccd.matches("^\\d{12}$")) {
					JOptionPane.showMessageDialog(null, "CCCD không hợp lệ! Vui lòng nhập đúng 12 chữ số.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					veTau_Page.getTxt_CCCD().requestFocus();
				}
			} else if (source.equals(veTau_Page.getTxt_HoTen())) {
				String hoTen = capitalizeName(veTau_Page.getTxt_HoTen().getText());
				veTau_Page.getTxt_HoTen().setText(hoTen);
				if (!hoTen.matches("^[a-zA-Z\\p{L} ]+$")) {
					JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ! Chỉ chứa chữ cái và khoảng trắng.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					veTau_Page.getTxt_HoTen().requestFocus();
				}
			} else if (source.equals(veTau_Page.getTxt_Email())) {
				String email = veTau_Page.getTxt_Email().getText();
				if (!email.matches("^[a-zA-Z0-9._-]+@gmail\\.com$")) {
					JOptionPane.showMessageDialog(null, "Email không hợp lệ! Vui lòng nhập email có đuôi '@gmail.com'.",
							"Lỗi", JOptionPane.ERROR_MESSAGE);
					veTau_Page.getTxt_Email().requestFocus();
				}
			} else if (source.equals(veTau_Page.getTxt_NgaySinh())) {
				String ngaySinh = veTau_Page.getTxt_NgaySinh().getText();
				if (!ngaySinh.matches("\\d{2}/\\d{2}/\\d{4}")) {
					JOptionPane.showMessageDialog(null,
							"Ngày sinh không hợp lệ! Vui lòng nhập theo định dạng dd/MM/yyyy.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					veTau_Page.getTxt_NgaySinh().requestFocus();
				}
			}
		}
		if (obj.equals(veTau_Page.getTxt_SDT()) || obj.equals(veTau_Page.getTxt_CCCD())) {
			KhachHang khachHang = null;
			if (obj.equals(veTau_Page.getTxt_SDT())) {
				khachHang = timKiemKhachHang(veTau_Page.getTxt_SDT().getText(), true);
			} else if (obj.equals(veTau_Page.getTxt_CCCD())) {
				khachHang = timKiemKhachHang(veTau_Page.getTxt_CCCD().getText(), false);
			}

			if (khachHang != null) {
				themThongTinKHInput(khachHang);
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
				manager.focusNextComponent();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			tienKhachDua = 0;
			thanhToan_Page.getTxt_TienTraLai().setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Object obj = e.getSource();
		if (obj.equals(veTau_Page.getTxt_NgaySinh())) {
			JTextField txtNgaySinh = veTau_Page.getTxt_NgaySinh();
			String text = txtNgaySinh.getText().replaceAll("[^0-9/]", "");
			int length = text.length();

			if (length == 2 || length == 5) {
				if (length < txtNgaySinh.getText().length()) {
					txtNgaySinh.setText(text);
				} else {
					txtNgaySinh.setText(text + "/");
				}
			} else if (length > 10) {
				txtNgaySinh.setText(text.substring(0, 10));
			}
		}
	}
}
