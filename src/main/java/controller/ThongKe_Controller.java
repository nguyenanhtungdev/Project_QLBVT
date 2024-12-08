package controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.Timer;

import model.ChiTiet_HoaDon;
import model.ChiTiet_HoaDon_DAO;
import model.HoaDon;
import model.HoaDon_DAO;
import model.StatisticData;
import model.TieuChiThongKe;
import model.TieuChiThongKe.StatisticType;
import util.PrinterUtils;
import view.View;
import view.ThongKeTaoMoi_View;
import view.ThongKeTongQuan_View;

public class ThongKe_Controller {

	private static ThongKe_Controller instance;
	private ArrayList<View> viewList;
	private ThongKeTongQuan_View tongQuanView;
	private Timer timerToday;

	public static ThongKe_Controller getInstance() {
		return instance == null ? instance = new ThongKe_Controller() : instance;
	}

	public ThongKe_Controller() {
		viewList = new ArrayList<>();
		viewList.add(tongQuanView = new ThongKeTongQuan_View("Tổng quan", "/Image/tabler-icon-report-analytics.png"));

//		timerToday = new Timer(1000, e -> refreshTodayStatistic());
		tongQuanView.addInActionListener(e -> PrinterUtils.print(tongQuanView, "In thống kê tổng quan"));
	}

	public void refreshData() {
		TieuChiThongKe tieuChiTuan = new TieuChiThongKe(StatisticType.DOANH_THU, null, null, null, false, null, null,
				null, null, LocalDateTime.of(2024, 10, 1, 0, 0, 0), LocalDateTime.of(2024, 10, 7, 0, 0, 0));
		tongQuanView.loadWeekStatistic(taoThongKeTheoTieuChi(tieuChiTuan), tieuChiTuan);

		refreshTodayStatistic();
//		timerToday.start();
	}

	private void refreshTodayStatistic() {
		// TODO: Change to today's date
		TieuChiThongKe tieuChiHomNay = new TieuChiThongKe(StatisticType.DOANH_THU, null, null, null, false, null, null,
				null, null, LocalDateTime.of(2024, 10, 6, 0, 0, 0), LocalDateTime.of(2024, 10, 6, 0, 0, 0));
		tongQuanView.loadTodayStatistic(taoThongKeTheoTieuChi(tieuChiHomNay));
	}

	public void stopRefresh() {
		timerToday.stop();
	}

	private List<StatisticData> mapEntryToValueList(Map<LocalDateTime, List<ChiTiet_HoaDon>> map) {
		return map.entrySet().stream().map(m -> {
			LocalDateTime thoiGian = m.getKey();
			List<ChiTiet_HoaDon> list = m.getValue();

			double doanhThu = list.stream()
					.map(m1 -> m1.getVeTau().getGheTau().getToaTau().getTau().getChuyenTau().getGiaVe().getGiaVe())
					.collect(Collectors.summingDouble(Double::doubleValue));
			int soLuongHoaDon = list.stream().map(m1 -> m1.getHoaDon()).distinct().toList().size();
			int soLuongVeBan = list.size();
			int soLuongVeHuy = list.stream().filter(p -> p.getVeTau().isDaHuy()).toList().size();

			return new StatisticData(thoiGian, doanhThu, soLuongHoaDon, soLuongVeBan, soLuongVeHuy, 0);
		}).toList();

	}

	public List<StatisticData> taoThongKeTheoTieuChi(TieuChiThongKe tieuChi) {
		List<HoaDon> hoaDons = HoaDon_DAO.getInstance().getByFilters(tieuChi.getTuLuc(),
				tieuChi.getDenLuc().plusDays(1), null);
		List<String> maHoaDons = hoaDons.stream().map(m -> m.getMaHoaDon()).toList();
		List<ChiTiet_HoaDon> chiTiets = ChiTiet_HoaDon_DAO.getInstance().getByMaHoaDon(maHoaDons);

		Map<LocalDateTime, List<ChiTiet_HoaDon>> map = chiTiets.stream().collect(Collectors.groupingBy(
				m -> m.getHoaDon().getNgayLapHoaDon().truncatedTo(ChronoUnit.DAYS), TreeMap::new, Collectors.toList()));

		return mapEntryToValueList(map);

	}

	public ArrayList<View> getViewList() {
		return viewList;
	}

	public static void main(String[] args) {
		ThongKe_Controller.getInstance().viewList.get(0).setVisible(true);
		ThongKe_Controller.getInstance().refreshData();
	}

}
