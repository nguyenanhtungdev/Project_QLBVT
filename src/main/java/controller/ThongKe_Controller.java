package controller;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import org.jfree.data.category.DefaultCategoryDataset;

import component.thongke.ThongKeListSelectorDialog;
import component.thongke.ThongKeTableSelectorDialog;
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
import model.KhuyenMai;
import model.KhuyenMai_DAO;
import model.NhanVien;
import model.NhanVien_DAO;
import model.StatisticData;
import model.Tau;
import model.Tau_DAO;
import model.ThongKeFilters;
import model.ToaTau;
import model.ToaTau_DAO;
import model.KhachHang.LoaiKhachHang;
import model.Tau.TrangThaiTau;
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
		tongQuanView = new ThongKeTongQuan_View("Tổng quan", "/Image/tabler-icon-report-analytics.png");
		taoMoiView = new ThongKeTaoMoi_View("Tạo mới", "/Image/tabler-icon-report-medical.png");
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
		taoMoiView.addBtnVeTauCategoryCallback(this::onBtnVeTauCategory);
		taoMoiView.addBtnVeTauStatusCallback(this::onBtnVeTauStatus);
		taoMoiView.addBtnXoaRongCallback(this::onBtnXoaRong);

		taoMoiView.addBtnXemBaoCaoCallback(() -> {
			Date fromDate = taoMoiView.getThoiGianDateSelector().getTextFieldTuNgay().getDate();
			Date toDate = taoMoiView.getThoiGianDateSelector().getTextFieldfDenNgay().getDate();

			int fromHour = taoMoiView.getThoiGianTimeSelector().getChooserTuLuc().getHour();
			int fromMinute = taoMoiView.getThoiGianTimeSelector().getChooserTuLuc().getMinute();
			int fromSecond = taoMoiView.getThoiGianTimeSelector().getChooserTuLuc().getSecond();

			int toHour = taoMoiView.getThoiGianTimeSelector().getChooserDenLuc().getHour();
			int toMinute = taoMoiView.getThoiGianTimeSelector().getChooserDenLuc().getMinute();
			int toSecond = taoMoiView.getThoiGianTimeSelector().getChooserDenLuc().getSecond();

			if (fromDate != null) {
				filter.setTuLuc(LocalDateTime.of(fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						LocalTime.of(0, 0, 0)));
			}
			if (toDate != null) {
				filter.setDenLuc(LocalDateTime.of(toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						LocalTime.of(23, 59, 59)));
			}

			if (!(fromHour == -1 && fromMinute == -1 && fromSecond == -1)) {
				if (fromHour == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn giờ bắt đầu thống kê", "Lỗi nhập thời gian",
							JOptionPane.ERROR_MESSAGE);
				}
				if (fromMinute == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn phút bắt đầu thống kê", "Lỗi nhập thời gian",
							JOptionPane.ERROR_MESSAGE);
				}
				if (fromSecond == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn giây bắt đầu thống kê", "Lỗi nhập thời gian",
							JOptionPane.ERROR_MESSAGE);
				}
				filter.getTuLuc().withHour(fromHour).withMinute(fromMinute).withSecond(fromSecond);
			}

			if (!(toHour == -1 && toMinute == -1 && toSecond == -1)) {
				if (toHour == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn giờ kết thúc thống kê", "Lỗi nhập thời gian",
							JOptionPane.ERROR_MESSAGE);
				}
				if (toMinute == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn phút kết thúc thống kê", "Lỗi nhập thời gian",
							JOptionPane.ERROR_MESSAGE);
				}
				if (toSecond == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn giây kết thúc thống kê", "Lỗi nhập thời gian",
							JOptionPane.ERROR_MESSAGE);
				}
				filter.getDenLuc().withHour(toHour).withMinute(toMinute).withSecond(toSecond);
			}

			LocalDateTime from = filter.getTuLuc();
			LocalDateTime to = filter.getDenLuc();
			List<HoaDon> hoaDons = HoaDon_DAO.getInstance().getByFilters(from, to, filter.getKhachHang(),
					filter.getNhanVien());
			if (filter.getKhachHangCategory() != null && filter.getKhachHangCategory().length > 0) {
				hoaDons = hoaDons.stream().filter(
						p -> Stream.of(filter.getKhachHangCategory()).anyMatch(f -> f == p.getKhachHang().getLoaiKH()))
						.toList();
			}
			if (filter.getNhanVien() != null && filter.getNhanVien().length > 0) {
				hoaDons = hoaDons.stream().filter(p -> Stream.of(filter.getNhanVien())
						.anyMatch(f -> f.getMaNV().equals(p.getNhanVien().getMaNV()))).toList();
			}

			if (filter.getCaLam() != null && filter.getCaLam().length > 0) {
				List<HoaDon> temp = new ArrayList<HoaDon>();
				for (HoaDon hoaDon : hoaDons) {
					LocalTime time = hoaDon.getNgayLapHoaDon().toLocalTime();
					for (CaLam caLam : filter.getCaLam()) {
						if (caLam.getThoiGianBatDau().minusSeconds(1).isBefore(time)
								&& caLam.getThoiGianKetThuc().plusSeconds(1).isAfter(time)) {
							temp.add(hoaDon);
							break;
						}
					}
				}
				hoaDons = temp;
			}

			List<String> maHoaDons = hoaDons.stream().map(HoaDon::getMaHoaDon).toList();
			if (maHoaDons.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp với tiêu chí đã chọn", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			ThongKeKetQua_View ketQuaView = new ThongKeKetQua_View("Báo cáo", filter,
					HienThi_Controller.getInstance().getTaiKhoan().getNhanVien());

			if (filter.getKhachHang() != null && filter.getKhachHang().length > 0 && filter.getKhachHang().length < 6) {
				addChartsToTaoMoiView("Khách hàng", maHoaDons, ketQuaView);
			}

			if (filter.getNhanVien() != null && filter.getNhanVien().length > 0 && filter.getNhanVien().length < 6) {
				addChartsToTaoMoiView("Nhân viên", maHoaDons, ketQuaView);
			}

			addChartsToTaoMoiView("Giờ", maHoaDons, ketQuaView);
			addChartsToTaoMoiView("Ngày", maHoaDons, ketQuaView);

			ketQuaView.setVisible(true);
		});
	}

	private String truncateTo(ChiTiet_HoaDon ct, String groupBy) {
		if (groupBy.equals("Khách hàng")) {
			return ct.getHoaDon().getKhachHang().getMaKhachHang();
		} else if (groupBy.equals("Nhân viên")) {
			return ct.getHoaDon().getNhanVien().getHoTenNV();
		}

		return "";
	}

	private LocalDateTime truncateTo(LocalDateTime dateTime, String groupBy) {
		ChronoUnit unit = null;
		if (groupBy.equals("Giờ")) {
			unit = ChronoUnit.HOURS;
		} else if (groupBy.equals("Ngày")) {
			unit = ChronoUnit.DAYS;
		}

		return dateTime.truncatedTo(unit);
	}

	private void addChartsToTaoMoiView(String groupBy, List<String> maHoaDons, ThongKeKetQua_View ketQuaView) {
		List<ChiTiet_HoaDon> chiTiets = ChiTiet_HoaDon_DAO.getInstance().getByMaHoaDon(maHoaDons);
		if (filter.getKhuyenMai() != null && filter.getKhuyenMai().length > 0) {
			if (filter.getKhuyenMai()[0] == null) {
				chiTiets = chiTiets.stream().filter(p -> p.getKhuyenMai() == null).toList();
			} else {
				chiTiets = chiTiets.stream().filter(p -> p.getKhuyenMai() != null).toList();
				chiTiets = chiTiets.stream().filter(p -> Stream.of(filter.getKhuyenMai())
						.anyMatch(f -> f.getMaKhuyenMai().equals(p.getKhuyenMai().getMaKhuyenMai()))).toList();
			}

		}
		if (filter.getChuyenTau() != null && filter.getChuyenTau().length > 0) {
			chiTiets = chiTiets.stream()
					.filter(p -> Stream.of(filter.getChuyenTau())
							.anyMatch(f -> f.getMaChuyenTau().equals(
									p.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getMaChuyenTau())))
					.toList();
		}
		if (filter.getTau() != null && filter.getTau().length > 0) {
			chiTiets = chiTiets.stream()
					.filter(p -> Stream.of(filter.getTau()).anyMatch(
							f -> f.getMaTau().equals(p.getVeTau().getGheTau().getToaTau().getTau().getMaTau())))
					.toList();
		}
		if (filter.getTauStatus() != null && filter.getTauStatus().length > 0) {
			chiTiets = chiTiets.stream().filter(p -> Stream.of(filter.getTauStatus())
					.anyMatch(f -> f == p.getVeTau().getGheTau().getToaTau().getTau().getTrangThai())).toList();
		}
		if (filter.getToaTau() != null && filter.getToaTau().length > 0) {
			chiTiets = chiTiets.stream()
					.filter(p -> Stream.of(filter.getToaTau())
							.anyMatch(f -> f.getMaToaTau().equals(p.getVeTau().getGheTau().getToaTau().getMaToaTau())))
					.toList();
		}
		if (filter.getToaTauStatus() != null && filter.getToaTauStatus().length == 1) {
			chiTiets = chiTiets.stream().filter(p -> p.getVeTau().getGheTau().getToaTau()
					.isTrangThai() == filter.getToaTauStatus()[0].booleanValue()).toList();
		}
		if (filter.getGheTau() != null && filter.getGheTau().length > 0) {
			chiTiets = chiTiets.stream().filter(p -> Stream.of(filter.getGheTau())
					.anyMatch(f -> f.getMaGheTau().equals(p.getVeTau().getGheTau().getMaGheTau()))).toList();
		}
		if (filter.getGheTauStatus() != null && filter.getGheTauStatus().length > 0) {
			chiTiets = chiTiets.stream().filter(p -> Stream.of(filter.getGheTauStatus())
					.anyMatch(f -> f.equals(p.getVeTau().getGheTau().getTrangThai()))).toList();
		}
		if (filter.getLoaiVe() != null && filter.getLoaiVe().length == 1) {
			chiTiets = chiTiets.stream().filter(p -> p.getVeTau().isLoaiVe() == filter.getLoaiVe()[0].booleanValue())
					.toList();
		}
		if (filter.getTrangThaiVe() != null && filter.getTrangThaiVe().length > 0) {
			chiTiets = chiTiets.stream()
					.filter(p -> p.getVeTau().isDaHuy() == filter.getTrangThaiVe()[0].booleanValue()).toList();
		}

		if (chiTiets.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp với tiêu chí đã chọn", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Map<Object, List<ChiTiet_HoaDon>> map = null;
		if (groupBy.equals("Giờ")) {
			map = chiTiets.stream()
					.collect(Collectors.groupingBy(
							m -> truncateTo(m.getHoaDon().getNgayLapHoaDon(), groupBy).toLocalTime(), TreeMap::new,
							Collectors.toList()));
		} else if (groupBy.equals("Ngày")) {
			map = chiTiets.stream()
					.collect(Collectors.groupingBy(
							m -> truncateTo(m.getHoaDon().getNgayLapHoaDon(), groupBy).toLocalDate(), TreeMap::new,
							Collectors.toList()));
		} else {
			map = chiTiets.stream()
					.collect(Collectors.groupingBy(m -> truncateTo(m, groupBy), TreeMap::new, Collectors.toList()));
		}

		List<StatisticData> data = map.entrySet().stream().map(pair -> {
			List<ChiTiet_HoaDon> list = pair.getValue();

			double doanhThu = list.stream()
					.map(ct -> ct.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getGiaVe().getGiaVe())
					.collect(Collectors.summingDouble(Double::doubleValue));
			long soLuongHoaDon = list.stream().map(m1 -> m1.getHoaDon()).distinct().count();
			long soLuongVeBan = list.size();
			long soLuongVeHuy = list.stream().filter(p -> p.getVeTau().isDaHuy()).count();
			long soLuongKhuyenMai = list.stream().map(ct -> ct.getKhuyenMai()).count();

			return new StatisticData(pair.getKey(), doanhThu, soLuongHoaDon, soLuongVeBan, soLuongVeHuy,
					soLuongKhuyenMai);
		}).toList();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();

		data.forEach(d -> dataset.addValue(d.getDoanhThu(), "Doanh thu (VNĐ)", d.getTarget().toString()));
		data.forEach(d -> dataset2.addValue(d.getSoLuongHoaDon(), "Số hóa đơn", d.getTarget().toString()));
		data.forEach(d -> dataset3.addValue(d.getSoLuongVeBan(), "Số vé bán", d.getTarget().toString()));
		data.forEach(d -> dataset4.addValue(d.getSoLuongVeHuy(), "Số vé hủy", d.getTarget().toString()));

		String type = "bar";
		if (groupBy.equals("Giờ") || groupBy.equals("Ngày")) {
			type = "line";
		}

		ketQuaView.addChart(dataset, "Doanh thu theo " + groupBy.toLowerCase(), groupBy, "Doanh thu (VNĐ)", type);
		ketQuaView.addChart(dataset2, "Số lượng hóa đơn theo " + groupBy.toLowerCase(), groupBy, "Số hóa đơn", type);
		ketQuaView.addChart(dataset3, "Số lượng vé bán ra theo " + groupBy.toLowerCase(), groupBy, "Số vé bán", type);
		ketQuaView.addChart(dataset4, "Số lượng vé đã hủy theo " + groupBy.toLowerCase(), groupBy, "Số vé hủy", type);
	}

	public void refreshData() {
		ThongKeFilters f = new ThongKeFilters();

		if (HienThi_Controller.getInstance().getTaiKhoan().getNhanVien().getTenChucVu().trim().equals("NVQL")) {
			f.setTuLuc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(7));
			f.setDenLuc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(1));

			LocalDate from = f.getTuLuc().toLocalDate();
			LocalDate to = f.getDenLuc().toLocalDate();
			tongQuanView.loadWeekStatistic(createStatisticDataGroupByDate(f), from, to);
			tongQuanView.loadSummary(createSummaryStatisticData(null));
		} else {
			List<CaLam> caLams = CaLam_DAO.getInstance().getAll();
			CaLam caLam = caLams.stream().filter(p -> {
				LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.HOURS);
				return p.getThoiGianBatDau().equals(now)
						|| (p.getThoiGianBatDau().isBefore(now) && p.getThoiGianKetThuc().isAfter(now));
			}).findFirst().orElse(null);

			if (caLam == null) {
				JOptionPane.showMessageDialog(tongQuanView, "Không tìm thấy ca làm hiện tại của người dùng",
						"Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
				return;
			}

			f.setTuLuc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).with(caLam.getThoiGianBatDau()));
			f.setDenLuc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).with(caLam.getThoiGianKetThuc()));
			f.setNhanVien(new NhanVien[] { HienThi_Controller.getInstance().getTaiKhoan().getNhanVien() });

			tongQuanView.loadHourStatistic(createStatisticDataGroupByHour(f), caLam.getThoiGianBatDau(),
					caLam.getThoiGianKetThuc());
			tongQuanView.loadSummary(createSummaryStatisticData(caLam));
		}
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

		List<HoaDon> hoaDons = HoaDon_DAO.getInstance().getByFilters(start, end, null, nhanViens);
		List<String> maHoaDons = hoaDons.stream().map(HoaDon::getMaHoaDon).toList();
		if (maHoaDons.isEmpty()) {
			return new StatisticData(caLam == null ? start : caLam, 0, 0, 0, 0, 0);
		}

		List<ChiTiet_HoaDon> chiTiets = ChiTiet_HoaDon_DAO.getInstance().getByMaHoaDon(maHoaDons);
		double doanhThu = chiTiets.stream()
				.map(ct -> ct.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getGiaVe().getGiaVe())
				.collect(Collectors.summingDouble(Double::doubleValue));
		long soLuongHoaDon = chiTiets.stream().map(ct -> ct.getHoaDon()).distinct().count();
		long soLuongVeBan = chiTiets.size();
		long soLuongVeHuy = chiTiets.stream().filter(ct -> ct.getVeTau().isDaHuy()).count();
		long soLuongKhuyenMai = chiTiets.stream().map(ct -> ct.getKhuyenMai()).count();

		return new StatisticData(caLam == null ? start : caLam, doanhThu, soLuongHoaDon, soLuongVeBan, soLuongVeHuy,
				soLuongKhuyenMai);
	}

	private List<StatisticData> createStatisticDataGroupByDate(ThongKeFilters f) {
		LocalDateTime from = f.getTuLuc();
		LocalDateTime to = f.getDenLuc();
		List<HoaDon> hoaDons = HoaDon_DAO.getInstance().getByFilters(from, to, f.getKhachHang(), f.getNhanVien());
		List<String> maHoaDons = hoaDons.stream().map(HoaDon::getMaHoaDon).toList();
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

			double doanhThu = list.stream()
					.map(ct -> ct.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getGiaVe().getGiaVe())
					.collect(Collectors.summingDouble(Double::doubleValue));
			long soLuongHoaDon = list.stream().map(m1 -> m1.getHoaDon()).distinct().count();
			long soLuongVeBan = list.size();
			long soLuongVeHuy = list.stream().filter(p -> p.getVeTau().isDaHuy()).count();
			long soLuongKhuyenMai = list.stream().map(ct -> ct.getKhuyenMai()).count();

			return new StatisticData(pair.getKey(), doanhThu, soLuongHoaDon, soLuongVeBan, soLuongVeHuy,
					soLuongKhuyenMai);
		}).toList();
	}

	private List<StatisticData> createStatisticDataGroupByHour(ThongKeFilters f) {
		LocalDateTime from = f.getTuLuc();
		LocalDateTime to = f.getDenLuc();
		List<HoaDon> hoaDons = HoaDon_DAO.getInstance().getByFilters(from, to, f.getKhachHang(), f.getNhanVien());
		List<String> maHoaDons = hoaDons.stream().map(HoaDon::getMaHoaDon).toList();
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
			long soLuongHoaDon = list.stream().map(m1 -> m1.getHoaDon()).distinct().count();
			long soLuongVeBan = list.size();
			long soLuongVeHuy = list.stream().filter(p -> p.getVeTau().isDaHuy()).count();
			long soLuongKhuyenMai = list.stream().map(ct -> ct.getKhuyenMai()).count();

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
		String strSelected = String.join("; ", selected.stream().map(KhachHang::getMaKhachHang).toList());
		taoMoiView.getKhachHangSelector().getTextField().setText(strSelected);

		filter.setKhachHang(selected.toArray(KhachHang[]::new));
	}

	private void onBtnKhachHangCategory() {
		LoaiKhachHang[] enums = LoaiKhachHang.values();
		String[] items = Stream.of(enums).map(LoaiKhachHang::toString).toArray(String[]::new);

		ThongKeListSelectorDialog selector = new ThongKeListSelectorDialog(taoMoiView, "Chọn loại khách hàng", items);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<LoaiKhachHang> selected = Arrays.stream(selector.getSelectedIndices()).mapToObj(LoaiKhachHang::fromInt)
				.toList();
		String strSelected = String.join("; ", selected.stream().map(LoaiKhachHang::toString).toList());
		taoMoiView.getKhachHangCategory().getTextField().setText(strSelected);

		filter.setKhachHangCategory(selected.stream().toArray(LoaiKhachHang[]::new));
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
		String strSelected = String.join("; ", selected.stream().map(NhanVien::getMaNV).toList());
		taoMoiView.getNhanVienSelector().getTextField().setText(strSelected);

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
		String strSelected = String.join("; ", selected.stream().map(CaLam::getMaCa).toList());
		taoMoiView.getCaLamSelector().getTextField().setText(strSelected);

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

		int[] selectedRows = selector.getSelectedRows();
		boolean isNotApplicable = false;
		for (int j : selectedRows) {
			if (j == 0) {
				filter.setKhuyenMai(new KhuyenMai[] { null });
				isNotApplicable = true;
				break;
			}
		}

		if (isNotApplicable) {
			taoMoiView.getKhuyenMaiSelector().getTextField().setText("Không áp dụng");
			return;
		}

		List<model.KhuyenMai> selected = Arrays.stream(selector.getSelectedRows()).mapToObj(i -> list.get(i - 1))
				.toList();
		String strSelected = String.join("; ", selected.stream().map(KhuyenMai::getMaKhuyenMai).toList());
		taoMoiView.getKhuyenMaiSelector().getTextField().setText(strSelected);

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
		String strSelected = String.join("; ", selected.stream().map(x -> x.getMaChuyenTau()).toList());
		taoMoiView.getChuyenTauSelector().getTextField().setText(strSelected);

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
		String strSelected = String.join("; ", selected.stream().map(Tau::getMaTau).toList());
		taoMoiView.getTauSelector().getTextField().setText(strSelected);

		filter.setTau(selected.toArray(Tau[]::new));
	}

	private void onBtnTauStatus() {
		TrangThaiTau[] enums = TrangThaiTau.values();
		String[] items = Stream.of(enums).map(TrangThaiTau::toString).toArray(String[]::new);
		ThongKeListSelectorDialog selector = new ThongKeListSelectorDialog(taoMoiView, "Chọn trạng thái tàu", items);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<String> selected = Arrays.stream(selector.getSelectedIndices()).mapToObj(i -> items[i]).toList();
		String strSelected = String.join("; ", selected.stream().toList());
		taoMoiView.getTauStatus().getTextField().setText(strSelected);

		filter.setTauStatus(selected.stream().map(TrangThaiTau::toEnum).toArray(TrangThaiTau[]::new));
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
		String strSelected = String.join("; ", selected.stream().map(ToaTau::getMaToaTau).toList());
		taoMoiView.getToaTauSelector().getTextField().setText(strSelected);

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
		String strSelected = String.join("; ", selected.stream().toList());
		taoMoiView.getToaTauStatus().getTextField().setText(strSelected);

		filter.setToaTauStatus(selected.stream().map(s -> s.equals("Còn chỗ")).toArray(Boolean[]::new));
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
		String strSelected = String.join("; ", selected.stream().map(x -> x.getMaGheTau()).toList());
		taoMoiView.getGheTauSelector().getTextField().setText(strSelected);

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
		String strSelected = String.join("; ", selected.stream().toList());
		taoMoiView.getGheTauStatus().getTextField().setText(strSelected);

		filter.setGheTauStatus(selected.toArray(String[]::new));
	}

	private void onBtnVeTauCategory() {
		String[] items = { "Vé VIP", "Vé thường" };
		ThongKeListSelectorDialog selector = new ThongKeListSelectorDialog(taoMoiView, "Chọn loại vé tàu", items);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<String> selected = Arrays.stream(selector.getSelectedIndices()).mapToObj(i -> items[i]).toList();
		String strSelected = String.join("; ", selected.stream().toList());
		taoMoiView.getpVeTauCategory().getTextField().setText(strSelected);

		filter.setLoaiVe(selected.stream().map(s -> s.equals("Vé VIP")).toArray(Boolean[]::new));
	}

	private void onBtnVeTauStatus() {
		String[] items = { "Đã mua", "Đã hủy" };
		ThongKeListSelectorDialog selector = new ThongKeListSelectorDialog(taoMoiView, "Chọn trạng thái vé tàu", items);
		selector.setVisible(true);

		if (selector.isCancelled())
			return;

		List<String> selected = Arrays.stream(selector.getSelectedIndices()).mapToObj(i -> items[i]).toList();
		String strSelected = String.join("; ", selected.stream().toList());
		taoMoiView.getVeTauStatus().getTextField().setText(strSelected);

		filter.setTrangThaiVe(selected.stream().map(s -> s.equals("Đã mua")).toArray(Boolean[]::new));
	}

	private void onBtnXoaRong() {
		taoMoiView.getKhachHangSelector().getTextField().setText("");
		taoMoiView.getKhachHangCategory().getTextField().setText("");
		taoMoiView.getNhanVienSelector().getTextField().setText("");
		taoMoiView.getCaLamSelector().getTextField().setText("");
		taoMoiView.getKhuyenMaiSelector().getTextField().setText("");
		taoMoiView.getChuyenTauSelector().getTextField().setText("");
		taoMoiView.getTauSelector().getTextField().setText("");
		taoMoiView.getTauStatus().getTextField().setText("");
		taoMoiView.getToaTauSelector().getTextField().setText("");
		taoMoiView.getToaTauStatus().getTextField().setText("");
		taoMoiView.getGheTauSelector().getTextField().setText("");
		taoMoiView.getGheTauStatus().getTextField().setText("");
		taoMoiView.getpVeTauCategory().getTextField().setText("");
		taoMoiView.getVeTauStatus().getTextField().setText("");
		taoMoiView.getThoiGianDateSelector().getTextFieldTuNgay().setDate(null);
		taoMoiView.getThoiGianDateSelector().getTextFieldfDenNgay().setDate(null);
		taoMoiView.getThoiGianTimeSelector().getChooserTuLuc().setTime(null);
		taoMoiView.getThoiGianTimeSelector().getChooserDenLuc().setTime(null);

		filter = new ThongKeFilters();
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
		viewList = new ArrayList<>();
		viewList.add(tongQuanView);
		if (HienThi_Controller.getInstance().getTaiKhoan().getNhanVien().getTenChucVu().trim().equals("NVQL")) {
			viewList.add(taoMoiView);
		}
		return viewList;
	}

}
