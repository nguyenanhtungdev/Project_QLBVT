package controller;

import java.awt.Insets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.UIManager;

import org.jfree.data.category.DefaultCategoryDataset;

import com.formdev.flatlaf.FlatLightLaf;

import component.thongke.ThongKeListSelectorDialog;
import component.thongke.ThongKeTableSelectorDialog;
import constant.FontConstants;
import model.CaLam;
import model.CaLam_DAO;
import model.ChiTiet_HoaDon;
import model.ChiTiet_HoaDon_DAO;
import model.ChuyenTau;
import model.ChuyenTau_DAO;
import model.GheTau;
import model.GheTau_DAO;
import model.HoaDon;
import model.HoaDon_DAO;
import model.KhachHang;
import model.KhachHang_DAO;
import model.KhuyenMai_DAO;
import model.NhanVien;
import model.NhanVien_DAO;
import model.StatisticData;
import model.TaiKhoan;
import model.Tau;
import model.Tau_DAO;
import model.ThongKeFilters;
import model.ToaTau;
import model.ToaTau_DAO;
import model.KhachHang.LoaiKhachHang;
import model.Tau.TrangThaiTau;
import util.PrinterUtils;
import view.View;
import view.ThongKeKetQua_View;
import view.ThongKeTaoMoi_View;
import view.ThongKeTongQuan_View;

public class ThongKe_Controller {

	private static ThongKe_Controller instance;

	public static ThongKe_Controller getInstance() {
		return instance == null ? instance = new ThongKe_Controller() : instance;
	}

	public static final NumberFormat FMT_MONEY = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	public static final DateTimeFormatter FMT_DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	public static final DateTimeFormatter FMT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FMT_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");

	private ArrayList<View> viewList;
	private ThongKeTongQuan_View tongQuanView;
	private ThongKeTaoMoi_View taoMoiView;
	private ThongKeFilters filter = new ThongKeFilters();

	public ThongKe_Controller() {
		viewList = new ArrayList<>();
		viewList.add(tongQuanView = new ThongKeTongQuan_View("Tổng quan", "/Image/tabler-icon-report-analytics.png"));

		viewList.add(taoMoiView = new ThongKeTaoMoi_View("Tạo mới", "/Image/tabler-icon-report-medical.png"));
		taoMoiView.addBtnKhachHangSelectorCallback(this::onBtnKhachHangSelector);
		taoMoiView.addBtnKhachHangCategoryCallback(this::onBtnKhachHangCategory);
		taoMoiView.addBtnNhanVienSelectorCallback(this::onBtnNhanVienSelector);
		taoMoiView.addBtnCaLamSelectorCallback(this::onBtnCaLamSelector);
		taoMoiView.addBtnKhuyenMaiSelectorCallback(this::onBtnKhuyenMaiSelector);
		taoMoiView.addBtnChuyenTauSelectorCallback(this::onBtnChuyenTauSelector);
		taoMoiView.addBtnTauSelectorCallback(this::onBtnTauSelector);
		taoMoiView.addBtnTauStatusCallback(this::onBtnTauStatus);
		taoMoiView.addBtnToaTauSelectorCallback(this::onBtnToaTauSelector);
		taoMoiView.addBtnToaTauStatusCallback(this::onBtnToaTauStatus);
		taoMoiView.addBtnGheTauSelectorCallback(this::onBtnGheTauSelector);
		taoMoiView.addBtnGheTauStatusCallback(this::onBtnGheTauStatus);
		taoMoiView.addBtnVeTauStatusCallback(this::onBtnVeTauStatus);

		taoMoiView.addBtnXemBaoCaoCallback(() -> {
			List<HoaDon> hoaDons = HoaDon_DAO.getInstance().getByFilters(filter.getTuLuc(), filter.getDenLuc(),
					filter.getKhachHang(), filter.getNhanVien());
			List<String> maHoaDons = hoaDons.stream().map(hd -> hd.getMaHoaDon()).toList();
			if (maHoaDons.isEmpty()) {
				return;
			}

			List<ChiTiet_HoaDon> chiTiets = ChiTiet_HoaDon_DAO.getInstance().getByMaHoaDon(maHoaDons);
			Map<Object, List<ChiTiet_HoaDon>> map = chiTiets.stream()
					.collect(Collectors.groupingBy(
							m -> m.getHoaDon().getNgayLapHoaDon().truncatedTo(ChronoUnit.DAYS).toLocalDate(),
							TreeMap::new, Collectors.toList()));

			map.entrySet().stream().map(pair -> {
				List<ChiTiet_HoaDon> list = pair.getValue();

				double sumDoanhThu = list.stream()
						.map(ct -> ct.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getGiaVe().getGiaVe())
						.collect(Collectors.summingDouble(Double::doubleValue));
				int soLuongHoaDon = list.stream().map(m1 -> m1.getHoaDon()).distinct().toList().size();
				int soLuongVeBan = list.size();
				int soLuongVeHuy = list.stream().filter(p -> p.getVeTau().isDaHuy()).toList().size();
				int soLuongKhuyenMai = list.stream().map(ct -> ct.getKhuyenMai()).toList().size();

				return new StatisticData(pair.getKey(), sumDoanhThu, soLuongHoaDon, soLuongVeBan, soLuongVeHuy,
						soLuongKhuyenMai);
			}).toList();

//			ThongKeKetQua_View dialog = new ThongKeKetQua_View(taoMoiView, "Báo cáo", filters,
//					NhanVien_DAO.getInstance().getByMaNhanVien("NV00004"));
//
//			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//			data.forEach(d -> dataset.addValue(d.getDoanhThu(), "Doanh thu", d.getTarget().toString()));
//
//			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
//			data.forEach(d -> dataset2.addValue(d.getSoLuongHoaDon(), "Số hóa đơn", d.getTarget().toString()));
//
//			dialog.addChart(dataset, "Doanh thu theo ngày", "Ngày", "Doanh thu (VNĐ)", "bar");
//			dialog.addChart(dataset2, "Số lượng hóa đơn theo ngày", "Ngày", "Số hóa đơn", "line");
//			dialog.setVisible(true);
		});
	}

	public void refreshData() {
		CaLam caLam = null;
		ThongKeFilters filter = new ThongKeFilters();

		boolean isManager = HienThi_Controller.getInstance().getTaiKhoan().getNhanVien().getTenChucVu().trim()
				.equals("NVQL");
		if (isManager) {
			filter.setTuLuc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(7));
			filter.setDenLuc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(1));
			tongQuanView.loadWeekStatistic(createStatisticDataGroupByDate(filter), filter.getTuLuc().toLocalDate(),
					filter.getDenLuc().toLocalDate());
		} else {
			List<CaLam> caLams = CaLam_DAO.getInstance().getAll();
			caLam = caLams.stream().filter(p -> {
				LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.HOURS);
				return p.getThoiGianBatDau().equals(now)
						|| (p.getThoiGianBatDau().isBefore(now) && p.getThoiGianKetThuc().isAfter(now));
			}).findFirst().orElse(null);

			filter.setTuLuc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).with(caLam.getThoiGianBatDau()));
			filter.setDenLuc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).with(caLam.getThoiGianKetThuc()));
			filter.setNhanVien(new NhanVien[] { HienThi_Controller.getInstance().getTaiKhoan().getNhanVien() });

			tongQuanView.loadHourStatistic(createStatisticDataGroupByHour(filter), caLam.getThoiGianBatDau(),
					caLam.getThoiGianKetThuc());
		}

		tongQuanView.loadSummary(createSummaryStatisticData(caLam));
	}

	private StatisticData createSummaryStatisticData(CaLam caLam) {
		LocalDateTime start = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime end = start.plusDays(1).minusSeconds(1);
		NhanVien[] nhanViens = null;

		if (caLam != null) {
			start = start.with(caLam.getThoiGianBatDau());
			end = end.with(caLam.getThoiGianKetThuc());
			nhanViens = new NhanVien[] { HienThi_Controller.getInstance().getTaiKhoan().getNhanVien() };
		}

		List<String> maHoaDons = HoaDon_DAO.getInstance().getByFilters(start, end, null, nhanViens).stream()
				.map(HoaDon::getMaHoaDon).toList();
		if (maHoaDons.isEmpty()) {
			return new StatisticData(caLam == null ? start : caLam, 0, 0, 0, 0, 0);
		}

		List<ChiTiet_HoaDon> chiTiets = ChiTiet_HoaDon_DAO.getInstance().getByMaHoaDon(maHoaDons);
		double doanhThu = chiTiets.stream()
				.map(ct -> ct.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getGiaVe().getGiaVe())
				.collect(Collectors.summingDouble(Double::doubleValue));
		long soLuongHoaDon = chiTiets.stream().map(ct -> ct.getHoaDon()).distinct().count();
		long soLuongVeBan = (long) chiTiets.size();
		long soLuongVeHuy = chiTiets.stream().filter(ct -> ct.getVeTau().isDaHuy()).count();
		long soLuongKhuyenMai = chiTiets.stream().map(ct -> ct.getKhuyenMai()).count();

		return new StatisticData(caLam == null ? start : caLam, doanhThu, soLuongHoaDon, soLuongVeBan, soLuongVeHuy,
				soLuongKhuyenMai);
	}

	private List<StatisticData> createStatisticDataGroupByDate(ThongKeFilters filters) {
		List<String> maHoaDons = HoaDon_DAO.getInstance()
				.getByFilters(filters.getTuLuc(), filters.getDenLuc(), filters.getKhachHang(), filters.getNhanVien())
				.stream().map(HoaDon::getMaHoaDon).toList();
		if (maHoaDons.isEmpty()) {
			return null;
		}

		List<ChiTiet_HoaDon> chiTiets = ChiTiet_HoaDon_DAO.getInstance().getByMaHoaDon(maHoaDons);
		Map<Object, List<ChiTiet_HoaDon>> map = chiTiets.stream()
				.collect(Collectors.groupingBy(
						m -> m.getHoaDon().getNgayLapHoaDon().truncatedTo(ChronoUnit.DAYS).toLocalDate(), TreeMap::new,
						Collectors.toList()));

		return map.entrySet().stream().map(pair -> {
			List<ChiTiet_HoaDon> list = pair.getValue();

			double sumDoanhThu = list.stream()
					.map(ct -> ct.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getGiaVe().getGiaVe())
					.collect(Collectors.summingDouble(Double::doubleValue));
			int soLuongHoaDon = list.stream().map(m1 -> m1.getHoaDon()).distinct().toList().size();
			int soLuongVeBan = list.size();
			int soLuongVeHuy = list.stream().filter(p -> p.getVeTau().isDaHuy()).toList().size();
			int soLuongKhuyenMai = list.stream().map(ct -> ct.getKhuyenMai()).toList().size();

			return new StatisticData(pair.getKey(), sumDoanhThu, soLuongHoaDon, soLuongVeBan, soLuongVeHuy,
					soLuongKhuyenMai);
		}).toList();
	}

	private List<StatisticData> createStatisticDataGroupByHour(ThongKeFilters filters) {
		List<String> maHoaDons = HoaDon_DAO.getInstance()
				.getByFilters(filters.getTuLuc(), filters.getDenLuc(), filters.getKhachHang(), filters.getNhanVien())
				.stream().map(HoaDon::getMaHoaDon).toList();
		if (maHoaDons.isEmpty()) {
			return null;
		}

		List<ChiTiet_HoaDon> chiTiets = ChiTiet_HoaDon_DAO.getInstance().getByMaHoaDon(maHoaDons);
		Map<Object, List<ChiTiet_HoaDon>> map = chiTiets.stream()
				.collect(Collectors.groupingBy(
						m -> m.getHoaDon().getNgayLapHoaDon().truncatedTo(ChronoUnit.HOURS).toLocalTime(), TreeMap::new,
						Collectors.toList()));

		return map.entrySet().stream().map(pair -> {
			List<ChiTiet_HoaDon> list = pair.getValue();

			double sumDoanhThu = list.stream()
					.map(ct -> ct.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getGiaVe().getGiaVe())
					.collect(Collectors.summingDouble(Double::doubleValue));
			int soLuongHoaDon = list.stream().map(m1 -> m1.getHoaDon()).distinct().toList().size();
			int soLuongVeBan = list.size();
			int soLuongVeHuy = list.stream().filter(p -> p.getVeTau().isDaHuy()).toList().size();
			int soLuongKhuyenMai = list.stream().map(ct -> ct.getKhuyenMai()).toList().size();

			return new StatisticData(pair.getKey(), sumDoanhThu, soLuongHoaDon, soLuongVeBan, soLuongVeHuy,
					soLuongKhuyenMai);
		}).toList();

	}

	private void onBtnKhachHangSelector() {
		List<KhachHang> list = KhachHang_DAO.getInstance().getAll();

		String[] columns = { "Mã khách hàng", "Tên khách hàng", "Giới tính", "Ngày sinh", "Loại khách hàng" };
		Object[][] rows = list.stream().map(x -> new Object[] { x.getMaKhachHang(), x.getHoTen(),
				x.isGioiTinh() ? "Nữ" : "Nam", x.getNgaySinh(), x.getLoaiKH() }).toArray(Object[][]::new);
		ThongKeTableSelectorDialog selector = new ThongKeTableSelectorDialog(taoMoiView, "Chọn khách hàng", columns,
				rows);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<KhachHang> selected = Arrays.stream(selector.getSelectedRows()).mapToObj(list::get).toList();
		taoMoiView.getKhachHangSelector().getTextField().setText(
				String.join("; ", selected.stream().map(x -> x.getMaKhachHang() + " - " + x.getHoTen()).toList()));

		filter.setKhachHang(selected.toArray(KhachHang[]::new));
	}

	private void onBtnKhachHangCategory() {
		LoaiKhachHang[] enums = LoaiKhachHang.values();
		String[] items = Stream.of(enums).map(LoaiKhachHang::toString).toArray(String[]::new);

		ThongKeListSelectorDialog selector = new ThongKeListSelectorDialog(taoMoiView, "Chọn loại khách hàng", items);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<String> selected = Arrays.stream(selector.getSelectedIndices()).mapToObj(i -> items[i]).toList();
		taoMoiView.getKhachHangCategory().getTextField()
				.setText(String.join("; ", selected.stream().map(String::toString).toList()));

		filter.setKhachHangCategory(selected.stream().map(LoaiKhachHang::valueOf).toArray(LoaiKhachHang[]::new));
	}

	private void onBtnNhanVienSelector() {
		List<NhanVien> list = NhanVien_DAO.getInstance().getAll();

		String[] columns = { "Mã nhân viên", "Họ tên nhân viên", "Giới tính", "Số điện thoại", "Ngày sinh" };
		Object[][] rows = list.stream().map(x -> new Object[] { x.getMaNV(), x.getHoTenNV(),
				x.isGioiTinh() ? "Nữ" : "Nam", x.getSoDienThoai(), x.getNgaySinh() }).toArray(Object[][]::new);
		ThongKeTableSelectorDialog selector = new ThongKeTableSelectorDialog(taoMoiView, "Chọn nhân viên", columns,
				rows);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<NhanVien> selected = Arrays.stream(selector.getSelectedRows()).mapToObj(list::get).toList();
		taoMoiView.getNhanVienSelector().getTextField()
				.setText(String.join("; ", selected.stream().map(x -> x.getMaNV() + " - " + x.getHoTenNV()).toList()));

		filter.setNhanVien(selected.toArray(NhanVien[]::new));
	}

	private void onBtnCaLamSelector() {
		List<CaLam> list = CaLam_DAO.getInstance().getAll();

		String[] columns = { "Mã ca làm", "Tên ca", "Thời gian bắt đầu", "Thời gian kết thúc", "Ghi chú" };
		Object[][] rows = list.stream().map(x -> new Object[] { x.getMaCa(), x.getTenCa(), x.getThoiGianBatDau(),
				x.getThoiGianKetThuc(), x.getGhiChu() }).toArray(Object[][]::new);
		ThongKeTableSelectorDialog selector = new ThongKeTableSelectorDialog(taoMoiView, "Chọn ca làm", columns, rows);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<CaLam> selected = Arrays.stream(selector.getSelectedRows()).mapToObj(list::get).toList();
		taoMoiView.getCaLamSelector().getTextField()
				.setText(String.join("; ", selected.stream().map(x -> x.getMaCa() + " - " + x.getTenCa()).toList()));

		filter.setCaLam(selected.toArray(CaLam[]::new));
	}

	private void onBtnKhuyenMaiSelector() {
		List<model.KhuyenMai> list = KhuyenMai_DAO.getInstance().getAll();

		String[] columns = { "Mã khuyến mãi", "Tên khuyến mãi", "Trị giá", "Nội dung khuyến mãi", "Số lượng tối đa",
				"Thời gian hiệu lực", "Hạn sử dụng", "Tình trạng khuyến mãi" };
		List<Object[]> rows = list.stream()
				.map(x -> new Object[] { x.getMaKhuyenMai(), x.getTenKhuyenMai(), x.getGiamGia(),
						x.getNoiDungKhuyenMai(), x.getSoLuongToiDa(), x.getThoiGianBatDau(), x.getHanSuDungKhuyenMai(),
						x.getTinhTrangKhuyenMai() })
				.collect(Collectors.toList());
		rows.add(0, new Object[] { "", "Không áp dụng", 0, "Không áp dụng khuyến mãi" });
		ThongKeTableSelectorDialog selector = new ThongKeTableSelectorDialog(taoMoiView, "Chọn khuyến mãi", columns,
				rows.toArray(Object[][]::new));
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<model.KhuyenMai> selected = Arrays.stream(selector.getSelectedRows()).mapToObj(i -> list.get(i + 1))
				.toList();
		taoMoiView.getKhuyenMaiSelector().getTextField().setText(String.join("; ",
				selected.stream().map(x -> x.getMaKhuyenMai() + " - " + x.getTenKhuyenMai()).toList()));

		filter.setKhuyenMai(selected.toArray(model.KhuyenMai[]::new));
	}

	private void onBtnChuyenTauSelector() {
		List<ChuyenTau> list = ChuyenTau_DAO.getInstance().getAll();

		String[] columns = { "Mã chuyến tàu", "Ga khởi hành", "Ga đến", "Thời gian khởi hành", "Thời gian đến dự kiến",
				"Ghi chú" };
		Object[][] rows = list.stream().map(x -> new Object[] { x.getMaChuyenTau(), x.getGaKhoiHanh(), x.getGaDen(),
				x.getThoiGianKhoiHanh(), x.getThoiGianDuKien(), x.getGhiChu() }).toArray(Object[][]::new);
		ThongKeTableSelectorDialog selector = new ThongKeTableSelectorDialog(taoMoiView, "Chọn chuyến tàu", columns,
				rows);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<ChuyenTau> selected = Arrays.stream(selector.getSelectedRows()).mapToObj(list::get).toList();
		taoMoiView.getChuyenTauSelector().getTextField()
				.setText(String.join("; ", selected.stream().map(x -> x.getMaChuyenTau()).toList()));

		filter.setChuyenTau(selected.toArray(ChuyenTau[]::new));
	}

	private void onBtnTauSelector() {
		List<Tau> list = Tau_DAO.getInstance().getAll();

		String[] columns = { "Mã tàu", "Tên tàu", "Số toa", "Năm sản xuất", "Trạng thái", "Ghi chú" };
		Object[][] rows = list.stream().map(x -> new Object[] { x.getMaTau(), x.getTenTau(), x.getSoToa(),
				x.getNamSanXuat(), x.getTrangThai(), x.getGhiChu() }).toArray(Object[][]::new);
		ThongKeTableSelectorDialog selector = new ThongKeTableSelectorDialog(taoMoiView, "Chọn tàu", columns, rows);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<Tau> selected = Arrays.stream(selector.getSelectedRows()).mapToObj(list::get).toList();
		taoMoiView.getTauSelector().getTextField()
				.setText(String.join("; ", selected.stream().map(x -> x.getMaTau()).toList()));

		filter.setTau(selected.toArray(Tau[]::new));
	}

	private void onBtnTauStatus() {
		String[] items = { "Hoạt động", "Bảo trì", "Dừng hoạt động" };
		ThongKeListSelectorDialog selector = new ThongKeListSelectorDialog(taoMoiView, "Chọn trạng thái tàu", items);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<String> selected = Arrays.stream(selector.getSelectedIndices()).mapToObj(i -> items[i]).toList();
		taoMoiView.getTauStatus().getTextField()
				.setText(String.join("; ", selected.stream().map(String::toString).toList()));

		filter.setTauStatus(selected.toArray(TrangThaiTau[]::new));
	}

	private void onBtnToaTauSelector() {
		List<ToaTau> list = ToaTau_DAO.getInstance().getAll();

		String[] columns = { "Mã toa tàu", "Tên toa tàu", "Số thứ tự toa", "Số lượng ghế", "Trạng thái" };
		Object[][] rows = list.stream().map(x -> new Object[] { x.getMaToaTau(), x.getTenToaTau(), x.getSoThuTuToa(),
				x.getSoLuongGhe(), x.isTrangThai() ? "Còn chỗ" : "Hết chỗ" }).toArray(Object[][]::new);
		ThongKeTableSelectorDialog selector = new ThongKeTableSelectorDialog(taoMoiView, "Chọn toa tàu", columns, rows);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<ToaTau> selected = Arrays.stream(selector.getSelectedRows()).mapToObj(list::get).toList();
		taoMoiView.getToaTauSelector().getTextField().setText(
				String.join("; ", selected.stream().map(x -> x.getMaToaTau() + " - " + x.getTenToaTau()).toList()));

		filter.setToaTau(selected.toArray(ToaTau[]::new));
	}

	private void onBtnToaTauStatus() {
		String[] items = { "Còn chỗ", "Đã đầy" };
		ThongKeListSelectorDialog selector = new ThongKeListSelectorDialog(taoMoiView, "Chọn trạng thái toa tàu",
				items);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<String> selected = Arrays.stream(selector.getSelectedIndices()).mapToObj(i -> items[i]).toList();
		taoMoiView.getToaTauStatus().getTextField()
				.setText(String.join("; ", selected.stream().map(String::toString).toList()));

//		filter.setToaTauStatus(selected.toArray(String[]::new));
	}

	private void onBtnGheTauSelector() {
		List<GheTau> list = GheTau_DAO.getInstance().getAll();

		String[] columns = { "Mã ghế tàu", "Loại ghế tàu", "Số thứ tự toa", "Trạng thái" };
		Object[][] rows = list.stream().map(x -> new Object[] { x.getMaGheTau(), x.getTenLoaiGheTau(),
				x.getsoThuTuGhe(), translateTrangThaiGheTau(x.getTrangThai()) }).toArray(Object[][]::new);
		ThongKeTableSelectorDialog selector = new ThongKeTableSelectorDialog(taoMoiView, "Chọn ghế tàu", columns, rows);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<GheTau> selected = Arrays.stream(selector.getSelectedRows()).mapToObj(list::get).toList();
		taoMoiView.getGheTauSelector().getTextField()
				.setText(String.join("; ", selected.stream().map(x -> x.getMaGheTau()).toList()));

		filter.setGheTau(selected.toArray(GheTau[]::new));
	}

	private void onBtnGheTauStatus() {
		String[] items = { "Trống", "Đã thanh toán", "Đang bảo trì", "Đang giữ chỗ" };
		ThongKeListSelectorDialog selector = new ThongKeListSelectorDialog(taoMoiView, "Chọn trạng thái ghế tàu",
				items);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<String> selected = Arrays.stream(selector.getSelectedIndices()).mapToObj(i -> items[i]).toList();
		taoMoiView.getGheTauStatus().getTextField()
				.setText(String.join("; ", selected.stream().map(String::toString).toList()));

		filter.setGheTauStatus(selected.toArray(String[]::new));
	}

	private void onBtnVeTauStatus() {
		String[] items = { "Đã mua", "Đã hủy" };
		ThongKeListSelectorDialog selector = new ThongKeListSelectorDialog(taoMoiView, "Chọn trạng thái vé tàu", items);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<String> selected = Arrays.stream(selector.getSelectedIndices()).mapToObj(i -> items[i]).toList();
		taoMoiView.getVeTauStatus().getTextField()
				.setText(String.join("; ", selected.stream().map(String::toString).toList()));

//		filter.setVeTauStatus(selected.toArray(String[]::new));
	}

	private String translateTrangThaiGheTau(String text) {
		switch (text) {
		case "TRONG":
			return "Trống";
		case "DA_THANH_TOAN":
			return "Đã thanh toán";
		case "DANG_BAO_TRI":
			return "Đang bảo trì";
		case "DANG_GIU_CHO":
			return "Đang giữ chỗ";
		default:
			return null;
		}
	}

	public ArrayList<View> getViewList() {
		return viewList;
	}

	public static void main(String[] args) {
		FlatLightLaf.setup();
		UIManager.put("TextField.margin", new Insets(8, 12, 8, 12));
		UIManager.put("ComboBox.padding", new Insets(8, 12, 8, 12));
		UIManager.put("TextComponent.arc", 8);
		UIManager.put("Label.font", FontConstants.TEXT);

		ThongKe_Controller.getInstance().viewList.get(1).setVisible(true);
		ThongKe_Controller.getInstance().refreshData();
	}

}
