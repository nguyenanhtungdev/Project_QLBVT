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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.ChuyenTau;
import model.ChuyenTau_DAO;
import model.GheTau;
import model.GheTau_DAO;
import model.GiaVe;
import model.GiaVe_DAO;
import model.HoaDon;
import model.HoaDon_DAO;
import model.KhachHang;
import model.KhachHang_DAO;
import model.Tau;
import model.Tau_DAO;
import model.TinhThanh;
import model.ToaTau;
import model.ToaTau_DAO;
import model.VeTau_DAO;
import other.ColorConstants;
import other.RoundedPanel;
import view.BaoMat_view;
import view.DoiTraVe_View;
import view.ThanhToan_View;
import view.ThongTinVe;
import view.ThongTin;
import view.TimKiem_View;
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

	private static final int ITEMS_PER_PAGE = 5;
	private int currentIndex = 0, soChuyenTau, soTrang = 1;
	private TimKiem_View timKiem_Page;
	private VeTau_View veTau_Page;
	private ThanhToan_View thanhToan_Page;
	private List<ChuyenTau> danhSachChuyenTau;
	private JPanel selectedPanel_ChuyenTau = null, selectedPanel_ChuyenTau_1 = null;
	private JLabel selectedLabel_ToaTau = null, selectedLabel_IconToaTau = null;
	private JButton selectedButon_GheTau = null;
	private TinhThanh selectedTinhThanhDi, selectedTinhThanhDen;
	private String ngayDi, ngayVe, gaDi, gaDen;
	private boolean isMotChieu;
	private boolean isKhuHoi;
	private boolean isToaVIP;
	private boolean isToaThuong;
	private boolean temp;
	private ArrayList<ChuyenTau> chuyenTauList;
	private ImageIcon icon;
	private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd"); // Định dạng ngày đầu vào
	private SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày đầu ra
	private boolean isBtnExampleClicked = false;
	private ChuyenTau chuyenTauChon;
	private ToaTau toaTauChon;
	private GheTau gheTauChon;
	private String maGheTauDuocChon = null;
	private DateTimeFormatter formatterNgayGio = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private DateTimeFormatter formatterNgay = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private int sttVeTau = 0;
	private String maVeTau = null;
	private int tongSoVeTamThoi = 0;
	private double tongTienVeTauTamThoi = 0;
	private NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private ThongTin thongTinView;
	private BaoMat_view baoMat_view;
	// hoàn tiền view
	private DoiTraVe_View hoanTien_view;

	private ArrayList<View> pageList = new ArrayList<>();

	public ArrayList<View> getViewList() {
		return pageList;
	}

	// Hàm khởi tạo
	public BanVeTau_Controller() throws SQLException {
		this.danhSachChuyenTau = ChuyenTau_DAO.getInstance().getAll();
		this.soChuyenTau = danhSachChuyenTau.size();
		
		pageList.add(timKiem_Page = new TimKiem_View("Tìm kiếm", "/Image/search.png"));
		pageList.add(veTau_Page = new VeTau_View("Vé tàu", "/Image/tabler-icon-ticket.png"));
		pageList.add(hoanTien_view = new DoiTraVe_View("Đổi trả vé", "/Image/icon_ThanhToan.png"));
		

		hienThiThongTinVe();
		initController();
	}

	public void hienThiThongTinVe() {
		hoanTien_view.getDanhSachVeTable().getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = hoanTien_view.getDanhSachVeTable().getSelectedRow();
				if (selectedRow != -1) {
					hoanTien_view.getXacNhanButton().setEnabled(true); // Cho phép nhấn nút xác nhận khi có dòng được
																		// chọn
				} else {
					hoanTien_view.getXacNhanButton().setEnabled(false); // Không cho phép nếu không có dòng nào được
																		// chọn
				}
			}
		});

		hoanTien_view.getXacNhanButton().addActionListener(e -> {
			int selectedRow = hoanTien_view.getDanhSachVeTable().getSelectedRow();
			if (selectedRow != -1 && selectedRow < hoanTien_view.getDanhSachVeTable().getRowCount()) {
				// Lấy thông tin khách hàng từ bảng
				String maHoaDon = hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 1).toString();
				String tenKhachHang = hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 2).toString();
				String soDienThoai = hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 3).toString();
				String soTien = hoanTien_view.getDanhSachVeTable().getValueAt(selectedRow, 4).toString();

				// Hiển thị cửa sổ thông tin vé tàu
				ThongTinVe thongTinVe = new ThongTinVe();
				thongTinVe.setMaHoaDon(maHoaDon);
				thongTinVe.setTenKhachHang(tenKhachHang);
				thongTinVe.setSoDienThoai(soDienThoai);
				thongTinVe.setSoTien(soTien);

				thongTinVe.setVisible(true);

				// Thêm sự kiện cho các nút trong cửa sổ thông tin vé tàu
				thongTinVe.getHoanVeButton().addActionListener(ev -> {
					// Xử lý hoàn vé
					JOptionPane.showMessageDialog(thongTinVe, "Hoàn vé thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				});

				thongTinVe.getTraVeButton().addActionListener(ev -> {
					// Xử lý trả vé
					JOptionPane.showMessageDialog(thongTinVe, "Trả vé thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				});

				thongTinVe.getInPDFButton().addActionListener(ev -> {
					// Xử lý in PDF
					JOptionPane.showMessageDialog(thongTinVe, "In PDF thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				});
			}
		});
	}

	private void initController() throws SQLException {
		themSuKien();
		updateTrainPanel(timKiem_Page.panel_dsChuyenTau, danhSachChuyenTau);
		timKiem_Page.getLblSoTrang().setText("trang: " + soTrang);
		timKiem_Page.getLblSoChuyenTau().setText("Tổng số chuyến tàu: " + soChuyenTau);

	}

	private void themSuKien() {
		timKiem_Page.addNextButtonListener(e -> nextPage());
		timKiem_Page.addPrevButtonListener(e -> prevPage());
		timKiem_Page.addSuKien(this);
		hoanTien_view.addSuKien(this);
		veTau_Page.addSuKien(this, this, this); // Gọi hàm thêm sự kiện cho lớp Vé tàu tạm thời
		veTau_Page.addSuKienTable(this);
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
			updateTrainPanel(timKiem_Page.getPanel_dsChuyenTau(), dsChuyenTaus);
			timKiem_Page.getLblSoTrang().setText("trang: " + soTrang);
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

	// Tạo phần tử tàu
	private JPanel createTau(ChuyenTau chuyenTau) {
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

		// Sử dụng dữ liệu từ đối tượng ChuyenTau
		JLabel lblNewLabel_9 = new JLabel(chuyenTau.getMaChuyenTau()); // Ví dụ hiển thị mã tàu
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

		JLabel lblNewLabel_14 = new JLabel("10/270");
		lblNewLabel_14.setForeground(ColorConstants.PRIMARY_COLOR);
		lblNewLabel_14.setFont(new Font("Arial", Font.BOLD, 20));
		panel_26.add(lblNewLabel_14);

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

				ArrayList<ToaTau> dsToaTau = ToaTau_DAO.getInstance().getDsToaTau(chuyenTau.getTau().getMaTau());

				if (dsToaTau != null && !dsToaTau.isEmpty()) {
					JPanel panelToaTau = themDsToaTau(dsToaTau);
					toaTauChon = dsToaTau.get(0);
					// Cập nhật giao diện
					timKiem_Page.panel_DsToaTau.removeAll(); // Xóa các phần tử cũ trong panel
					timKiem_Page.panel_DsToaTau.add(panelToaTau); // Thêm panel mới
					timKiem_Page.panel_DsToaTau.revalidate(); // Làm mới layout
					timKiem_Page.panel_DsToaTau.repaint(); // Vẽ lại giao diện
				} else {
					JOptionPane.showMessageDialog(panel_chyentau, "Không có toa tàu nào!", "Thông báo",
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
			timKiem_Page.panel_DsGheTau.removeAll();
			timKiem_Page.panel_DsGheTau.add(panelGheTau);
			timKiem_Page.panel_DsGheTau.revalidate();
			timKiem_Page.panel_DsGheTau.repaint();

			timKiem_Page.getLbl_TenToaTau().setText("Toa tàu số 1: Toa tàu VIP");
			timKiem_Page.getLbl_SoGheTau()
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

	// Phương thức tạo panel cho từng toa tàu
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
				// Đặt lại màu nền của tàu trước đó nếu có
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
					timKiem_Page.panel_DsGheTau.removeAll(); // Xóa các phần tử cũ trong panel
					timKiem_Page.panel_DsGheTau.add(panelGheTau); // Thêm panel mới
					timKiem_Page.panel_DsGheTau.revalidate(); // Làm mới layout
					timKiem_Page.panel_DsGheTau.repaint(); // Vẽ lại giao diện
					timKiem_Page.getLbl_SoGheTau()
							.setText(tinhSoGheTauConLai(toaTauChon.getMaToaTau()) + "/" + dsGheTau.size());

					if (count < 4) {
						timKiem_Page.getLbl_TenToaTau().setText("Toa tàu số " + count + ":" + " Toa tàu VIP");
					} else {
						timKiem_Page.getLbl_TenToaTau().setText("Toa tàu số " + count + ":" + " Toa tàu thường");
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
	public void timKiemChuyenTau() {
		isMotChieu = timKiem_Page.getRdbtn_MotChieu().isSelected();
		isKhuHoi = timKiem_Page.getRdbtn_KhuHoi().isSelected();
		isToaThuong = timKiem_Page.getRdbtn_ToaThuong().isSelected();
		isToaVIP = timKiem_Page.getRdbtn_ToaVIP().isSelected();

		if (!kiemTraTinhThanh())
			return;
		if (!kiemTraNgay())
			return;

		try {
			chuyenTauList = ChuyenTau_DAO.getInstance().timKiemChuyenTau(gaDi, gaDen, ngayDi);

			soChuyenTau = chuyenTauList.size();

			Date date_1 = inputFormat.parse(ngayDi);
//	        Date date_2 = inputFormat.parse(ngayVe);

			if (soChuyenTau > 0) {
				JOptionPane.showMessageDialog(null, "Tìm thấy " + chuyenTauList.size() + " chuyến tàu!");
				timKiem_Page.getLblSoChuyenTau().setText("Tổng số chuyến tàu: " + soChuyenTau);

				if (isMotChieu) {
					timKiem_Page.getLblTTChuyenTauTimKiem().setText(
							"Chiều đi: " + "ngày " + outputFormat.format(date_1) + " từ " + gaDi + " đến " + gaDen);
				} else {
					timKiem_Page.getLblTTChuyenTauTimKiem().setText(
							"Chiều đi: " + "ngày " + outputFormat.format(date_1) + " từ " + gaDi + " đến " + gaDen);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy chuyến tàu!");
				timKiem_Page.getLblSoChuyenTau().setText("Tổng số chuyến tàu: " + 0);
			}

			// Reset lại chỉ số trang và hiển thị giao diện
			danhSachChuyenTau = chuyenTauList;
			this.currentIndex = 0;
			this.soTrang = 1;
			this.soChuyenTau = danhSachChuyenTau.size();
			updateDisplay(danhSachChuyenTau);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// Lọc chuyến tàu
	private void locChuyenTau(List<ChuyenTau> listChuyenTaus) {
		ArrayList<ChuyenTau> dsTimThay = new ArrayList<ChuyenTau>();
		String khungGio = (String) timKiem_Page.getCombobox_KhungGio().getSelectedItem();
		String choNgoi = (String) timKiem_Page.getComboBox_ChoNgoi().getSelectedItem();

		for (ChuyenTau chuyenTau : listChuyenTaus) {
			boolean thoaManKhungGio = false;
			boolean thoaManChoNgoi = false;
			// Lấy giờ khởi hành từ thời gian khởi hành của chuyến tàu
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
			timKiem_Page.getLblSoChuyenTau().setText("Tổng số chuyến tàu: " + soChuyenTau);
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
			gaDi = "Sài Gòn";
		if (gaDen.equals("TP Hồ Chí Minh"))
			gaDen = "Sài Gòn";

		return true;
	}

	// Phương thức kiểm tra ngày đi và ngày về
	private boolean kiemTraNgay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date ngayDiDate = timKiem_Page.getDateChooser_NgayDi().getDate();
		Date ngayVeDate = timKiem_Page.getDateChooser_NgayVe().getDate();

		if (isMotChieu && ngayDiDate == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đi cho vé một chiều!");
			return false;
		}

		if (isKhuHoi) {
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

	// Làm mới chuyến tàu lại như ban đầu và ô nhập dữ liệu
	public void lamMoi() {
		timKiem_Page.getJcombobox_gadi().setSelectedIndex(-1);
		timKiem_Page.getJcombobox_gaden().setSelectedIndex(-1);
		timKiem_Page.getDateChooser_NgayDi().setDate(null);
		timKiem_Page.getDateChooser_NgayVe().setDate(null);
		timKiem_Page.getRdbtn_MotChieu().setSelected(true);
		timKiem_Page.getRdbtn_ToaThuong().setSelected(true);
		timKiem_Page.getLblTTChuyenTauTimKiem().setText("Danh sách chuyến tàu đề xuất");
		timKiem_Page.getCombobox_KhungGio().setSelectedIndex(0);
		isBtnExampleClicked = false;
		// Làm mới giao diện ghế tàu
		timKiem_Page.panel_DsGheTau.removeAll(); // Xóa các phần tử cũ trong panel
		timKiem_Page.panel_DsGheTau.revalidate(); // Làm mới layout
		timKiem_Page.panel_DsGheTau.repaint(); // Vẽ lại giao diện
		timKiem_Page.getLbl_TenToaTau().setText("");
		timKiem_Page.getLbl_SoGheTau().setText("");
		// Làm mới giao diện toa tàu
		timKiem_Page.panel_DsToaTau.removeAll(); // Xóa các phần tử cũ trong panel
		timKiem_Page.panel_DsToaTau.revalidate(); // Làm mới layout
		timKiem_Page.panel_DsToaTau.repaint(); // Vẽ lại giao diện

		try {
			danhSachChuyenTau = ChuyenTau_DAO.getInstance().getAll();
			soChuyenTau = danhSachChuyenTau.size();
			timKiem_Page.getLblSoChuyenTau().setText("Tổng số chuyến tàu: " + soChuyenTau);
			// Reset lại chỉ số trang và hiển thị giao diện
			this.currentIndex = 0;
			this.soTrang = 1;
			updateDisplay(danhSachChuyenTau);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void capNhatGiaoDienGheTau(String maToaTau) {
		// Xóa các phần tử cũ trong panel và làm mới layout trước khi thêm ghế mới
		JPanel panelGheTau = themDsGheTau(GheTau_DAO.getInstance().getDsGheTau(maToaTau));
		// Cập nhật giao diện cho `panel_DsGheTau`
		timKiem_Page.panel_DsGheTau.removeAll();
		timKiem_Page.panel_DsGheTau.add(panelGheTau);
		timKiem_Page.panel_DsGheTau.revalidate();
		timKiem_Page.panel_DsGheTau.repaint();

	}

	// Xử lý lớp vé tàu tạm thời
	// Them ve tau
	public boolean themVeTau() {
		if (chuyenTauChon != null && toaTauChon != null && gheTauChon != null) {
			if (!gheTauChon.getTrangThai().equals("TRONG")) {

				if (gheTauChon.getTrangThai().equals("DANG_BAO_TRI")) {
					JOptionPane.showMessageDialog(null, "Ghế tàu đang bảo trì!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					return false;
				} else {
					JOptionPane.showMessageDialog(null, "Ghế tàu đã được mua!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
			themDataTableVeTau();
//			tinhTongTienVeTauTamThoi();
			GheTau_DAO.getInstance().updateTrangThaiGheTau(maGheTauDuocChon, "DANG_GIU_CHO");
			return true;
		} else {
			return false;
		}
	}

	// Tạo mã vé tàu
	public String taoMaVeTau(String maVeTauMax) {
		String currentMaxCode = maVeTauMax;
		// Phần cố định của mã vé
		String prefix = "VT";
		String year = String.valueOf(Year.now().getValue()).substring(2);

		int newNumber = 1;
		if (currentMaxCode != null) {
			String currentNumberStr = currentMaxCode.substring(4); // Lấy phần dãy số XXXX từ VTYYXXXX
			newNumber = Integer.parseInt(currentNumberStr) + 1;
		}

		// Định dạng dãy số với 4 chữ số, thêm vào mã vé mới
		String ticketCode = prefix + year + String.format("%04d", newNumber);
		return ticketCode;
	}

	// Xử lý vé tàu tạm thời
	public void themDataTableVeTau() {
		JTable jTable = veTau_Page.getDanhSachVeTau();
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

		// Them data vao table
		veTau_Page.getDanhSachVeTauModel()
				.addRow(new Object[] { ++sttVeTau, maVeTau, loaiVe ? "VIP" : "Thường", gheTauChon.getMaGheTau(),
						"{trống}", "{trống}", "{trống}", "{trống}", "{trống}", "{trống}", "{trống}", "{trống}",
						chiTietChuyenTau });
		veTau_Page.getLbl_TongSoVe().setText(sttVeTau + "");
		veTau_Page.getLblTienTamTinh().setText(vndFormat.format(tinhGiaVeTamTinh()) + "");
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

		if (gioiTinh.equals("{trống}")) {
			veTau_Page.getComboBox_LoaiKH().setSelectedIndex(0);
		} else {
			veTau_Page.getComboBox_LoaiKH().setSelectedItem((String) hangKH);
		}

		veTau_Page.getTxt_NgaySinh().setText(ngaySinh);

	}

	// Cập nhật trạng thái ghế tàu
	private void capNhatTrangThaiGheTau(String maGheTau) {
		for (GheTau gheTau : GheTau_DAO.getInstance().getalltbGheTau()) {
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
			veTau_Page.getLbl_TongSoVe().setText(0 + "");
			timKiem_Page.getLbl_TongSoVeTamThoi().setText("Số vé tàu tạm thời: 0");
			veTau_Page.getDanhSachVeTauModel().setRowCount(0);
			veTau_Page.getLblTienTamTinh().setText("0");
			capNhatGiaoDienGheTau(toaTauChon.getMaToaTau());
			tongSoVeTamThoi = 0;
			sttVeTau = 0;
			veTau_Page.getLbl_ThoiGianGiuVe().setText("");
			xoaTrangInputVeTauTam();
			khoaNhapDuLieuVeTauTam();
			return true;
		} else {
			return false;
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
		for (KhachHang khachHang : KhachHang_DAO.getInstance().getalltbKH()) {
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
		veTau_Page.getComboBox_LoaiKH().setSelectedItem(khachHang.getLoaiKH());
	}

	//
	private void moNhapDuLieuVeTauTam() {
		veTau_Page.getTxt_SDT().setEnabled(true);
		veTau_Page.getTxt_CCCD().setEnabled(true);
		veTau_Page.getTxt_HoTen().setEnabled(true);
		veTau_Page.getTxt_Email().setEnabled(true);
		veTau_Page.getTxt_NgaySinh().setEnabled(true);
		veTau_Page.getComboBox_GioiTinh().setEnabled(true);
		veTau_Page.getComboBox_LoaiKH().setEnabled(true);
	}

	// Lớp hoặc phương thức của bạn
	private void getCurrentTime() {
		// Cập nhật thời gian hiện tại mỗi giây
		Timer currentTimeTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cập nhật thời gian hiện tại
				String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
				veTau_Page.getLbl_ThoiGianGiuVe().setText(currentTime);
			}
		});

		// Khởi tạo thời gian đếm ngược 15 phút (900 giây)
		int[] timeLeft = { 900 }; // Sử dụng mảng để có thể sửa đổi trong ActionListener
		Timer countdownTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeLeft[0] > 0) {
					// Cập nhật thời gian đếm ngược
					int minutes = timeLeft[0] / 60;
					int seconds = timeLeft[0] % 60;
					String formattedTime = String.format("%02d:%02d", minutes, seconds);
					veTau_Page.getLbl_ThoiGianGiuVe().setText(formattedTime);
					timeLeft[0]--; // Giảm thời gian còn lại
				} else {
					((Timer) e.getSource()).stop(); // Dừng bộ đếm khi hết thời gian
					veTau_Page.getLbl_ThoiGianGiuVe().setText("Thời gian đã hết!");
					xuLyHuyBoVeTam();
					JOptionPane.showMessageDialog(null, "Hủy bỏ danh sách vé tàu tạm thời thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// Bắt đầu cả hai bộ đếm
		currentTimeTimer.start();
		countdownTimer.start();
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

	// Tính giá vé tạm tính
	public double tinhGiaVeTamTinh() {
		double giaVeTamTinh = 0.0;
		JTable jTable = veTau_Page.getDanhSachVeTau();

		for (int i = 0; i < veTau_Page.getDanhSachVeTauModel().getRowCount(); i++) {
			String maGhe = (String) jTable.getValueAt(i, 3);
			String loaiVe = (String) jTable.getValueAt(i, 2);
			GiaVe gv = GiaVe_DAO.getInstance().getGiaVeTheoChuyenTau(maGhe, chuyenTauChon.getMaChuyenTau());
			if (loaiVe.equals("VIP")) {
				giaVeTamTinh += gv.getGiaVeHienTai(loaiVe);
			} else {
				giaVeTamTinh += gv.getGiaVeHienTai(loaiVe);
			}
		}
		return giaVeTamTinh;
	}

	// Xử lý hoàn vé

	// Xử lý sự kiện
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		// Chọn chỗ ngồi
		if (obj.equals(timKiem_Page.getJcombobox_gadi())) {
			selectedTinhThanhDi = (TinhThanh) timKiem_Page.getJcombobox_gadi().getSelectedItem();
		} else if (obj.equals(timKiem_Page.getJcombobox_gaden())) {
			selectedTinhThanhDen = (TinhThanh) timKiem_Page.getJcombobox_gaden().getSelectedItem();
		} else if (obj.equals(timKiem_Page.getBtnTimKiemChuyenTau())) {
			timKiemChuyenTau();
			isBtnExampleClicked = true;
		} else if (obj.equals(timKiem_Page.getRdbtn_MotChieu())) {
			timKiem_Page.getDateChooser_NgayVe().setEnabled(false);
		} else if (obj.equals(timKiem_Page.getRdbtn_KhuHoi())) {
			timKiem_Page.getDateChooser_NgayVe().setEnabled(true);
		} else if (obj.equals(timKiem_Page.getBtn_LamMoi())) {
			lamMoi();
		} else if (obj.equals(timKiem_Page.getCombobox_KhungGio())) {
			if (isBtnExampleClicked) {
				locChuyenTau(danhSachChuyenTau);
			} else {
				try {
					danhSachChuyenTau = ChuyenTau_DAO.getInstance().getAll();
					locChuyenTau(danhSachChuyenTau);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (obj.equals(timKiem_Page.getBtn_ThemVeTau())) {
			if (themVeTau()) {
				capNhatTrangThaiGheTau(maGheTauDuocChon);
				capNhatGiaoDienGheTau(toaTauChon.getMaToaTau());
				timKiem_Page.getLbl_TongSoVeTamThoi().setText("Số vé tạm thời: " + ++tongSoVeTamThoi);
				getCurrentTime();
				JOptionPane.showMessageDialog(null, "Thêm vé tàu thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Thêm vé tàu không thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (obj.equals(timKiem_Page.getBtn_HuyBo())) {

		}

		// Xử lý sự kiện vé tàu
		else if (obj.equals(veTau_Page.getBtn_ThanhToan())) {
			thanhToan_Page.setVisible(true);
			thanhToan_Page.setResizable(false);
		}
		// Xử lý sự kiện hủy bỏ vé tạm thời
		else if (obj.equals(veTau_Page.getBtn_HuyBo())) {
			int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				if (xuLyHuyBoVeTam()) {
					JOptionPane.showMessageDialog(null, "Hủy bỏ danh sách vé tàu tạm thời thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Hủy bỏ danh sách vé tàu tạm thời không thành công!",
							"Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

		// Xử lý sự kiện hoàn vé
		else if (obj.equals(hoanTien_view.getTimKiemButton())) {
			timKiemHoaDon(); // Gọi phương thức tìm kiếm hóa đơn

		}
	}

	private void timKiemHoaDon() {
		String soDienThoaiOrCCCD = hoanTien_view.getSoDienThoaiRadioButton().isSelected()
				? hoanTien_view.getSoDienThoaiField().getText()
				: hoanTien_view.getCccdField().getText();

		ArrayList<HoaDon> hoaDons = HoaDon_DAO.getInstance().getHoaDonBySoDienThoaiOrCCCD(soDienThoaiOrCCCD);
		DefaultTableModel tableModel = (DefaultTableModel) hoanTien_view.getDanhSachVeTable().getModel();
		tableModel.setRowCount(0);

		int stt = 1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		for (HoaDon hoaDon : hoaDons) {
			// Định dạng ngày lập hóa đơn trước khi hiển thị
			String formattedNgayLap = hoaDon.getNgayLapHoaDon().format(formatter);

			tableModel.addRow(
					new Object[] { stt++, hoaDon.getMaHoaDon(), hoaDon.getKhachHang().getHoTen(), formattedNgayLap, // Ngày
																													// lập
																													// hóa
																													// đơn
																													// sau
																													// khi
																													// định
																													// dạng
							hoaDon.getThueVAT() // Hiển thị thành tiền thay vì thuế VAT
					});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj.equals(veTau_Page.getDanhSachVeTau())) {
			int selectRow = veTau_Page.getDanhSachVeTau().getSelectedRow();

			moNhapDuLieuVeTauTam();

			themThongTinInputTable(selectRow);
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			manager.focusNextComponent();
		}

		Object obj = e.getSource();
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
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
