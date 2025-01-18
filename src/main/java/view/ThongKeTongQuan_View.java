package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.Box;
import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;

import component.thongke.ThongKeSummaryCard;
import constant.FormatterConstants;
import model.StatisticData;
import other.TrainChart;
import other.TrainPanel;
import other.TrainScrollPane;
import other.PrimaryButton;
import other.RoundedButton;
import other.TrainTitle;
import util.PrinterUtils;

public class ThongKeTongQuan_View extends View implements Printable {

	private static final long serialVersionUID = 4640477356217922276L;

	private final DefaultCategoryDataset datasetDoanhThu = new DefaultCategoryDataset();
	private final DefaultCategoryDataset datasetHoaDon = new DefaultCategoryDataset();
	private final DefaultCategoryDataset datasetVeBan = new DefaultCategoryDataset();
	private final DefaultCategoryDataset datasetVeHuy = new DefaultCategoryDataset();

	private Box pnNorth;
	private Box pnSouth;
	private RoundedButton btnIn;

	private TrainTitle lbToday;
	private JPanel pnCards;
	private ThongKeSummaryCard cdDoanhThu;
	private ThongKeSummaryCard cdSLHoaDon;
	private ThongKeSummaryCard cdSLVeBan;
	private ThongKeSummaryCard cdSLVeHuy;
	private ThongKeSummaryCard cdSLKhuyenMai;

	private TrainPanel pnCenter;
	private TrainChart chDoanhThu;
	private TrainChart chHoaDon;
	private TrainChart chVeBan;
	private TrainChart chVeHuy;

	public ThongKeTongQuan_View(String name, String imagePath) {
		super(name, imagePath);

		setContentPane(new TrainPanel(new BorderLayout(16, 16), new Insets(16, 16, 16, 16)));

		// NORTH
		add(pnNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		pnNorth.add(lbToday = new TrainTitle("Tổng quan hôm nay (dd/MM/yyyy)"));
		pnNorth.add(Box.createVerticalStrut(8));
		pnNorth.add(new TrainScrollPane(pnCards = new TrainPanel(new GridLayout(1, 0, 8, 8))));

		pnCards.add(cdDoanhThu = new ThongKeSummaryCard("Doanh thu", "0"));
		pnCards.add(cdSLHoaDon = new ThongKeSummaryCard("Số lượng hóa đơn", "0"));
		pnCards.add(cdSLVeBan = new ThongKeSummaryCard("Vé bán được", "0"));
		pnCards.add(cdSLVeHuy = new ThongKeSummaryCard("Vé đã hủy", "0"));
		pnCards.add(cdSLKhuyenMai = new ThongKeSummaryCard("Khuyến mãi đã dùng", "0"));

		// CENTER
		add(pnCenter = new TrainPanel(new GridLayout(2, 2, 16, 16)));
		chDoanhThu = TrainChart.createBarChart(datasetDoanhThu, "Doanh thu theo ngày", "", "Doanh thu (VNĐ)");
		chHoaDon = TrainChart.createBarChart(datasetHoaDon, "Số lượng hóa đơn theo ngày", "", "Số lượng hóa đơn");
		chVeBan = TrainChart.createBarChart(datasetVeBan, "Số lượng vé bán theo ngày", "", "Số lượng vé bán ra");
		chVeHuy = TrainChart.createBarChart(datasetVeHuy, "Số lượng vé hủy theo ngày", "", "Số lượng vé đã hủy");

		pnCenter.add(chDoanhThu.getPanel());
		pnCenter.add(chHoaDon.getPanel());
		pnCenter.add(chVeBan.getPanel());
		pnCenter.add(chVeHuy.getPanel());

		// SOUTH
		add(pnSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pnSouth.add(btnIn = new PrimaryButton("In thống kê"));
		btnIn.addActionListener(e -> PrinterUtils.print(this, "In thống kê tổng quan"));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1800, 1100);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
	}

	public void setManagerSummaryData(StatisticData data) {
		String today = FormatterConstants.DATE.format(LocalDate.now());
		lbToday.setText("Tổng quan hôm nay (" + today + ")");
		if (data != null) {
			cdDoanhThu.setValueText(FormatterConstants.MONEY.format(data.getDoanhThu()));
			cdSLHoaDon.setValueText(Long.toString(data.getSoLuongHoaDon()));
			cdSLVeBan.setValueText(Long.toString(data.getSoLuongVeBan()));
			cdSLVeHuy.setValueText(Long.toString(data.getSoLuongVeHuy()));
			cdSLKhuyenMai.setValueText(Long.toString(data.getSoLuongKhuyenMai()));
		}
	}

	public void setSaleStaffSummaryData(StatisticData data, LocalTime fromTime, LocalTime toTime) {
		String strFrom = FormatterConstants.TIME.format(fromTime);
		String strTo = FormatterConstants.TIME.format(toTime);
		String today = FormatterConstants.DATE.format(LocalDate.now());

		lbToday.setText("Tổng quan trong ca làm việc (từ " + strFrom + " đến " + strTo + " ngày " + today + ")");
		if (data != null) {
			cdDoanhThu.setValueText(FormatterConstants.MONEY.format(data.getDoanhThu()));
			cdSLHoaDon.setValueText(Long.toString(data.getSoLuongHoaDon()));
			cdSLVeBan.setValueText(Long.toString(data.getSoLuongVeBan()));
			cdSLVeHuy.setValueText(Long.toString(data.getSoLuongVeHuy()));
			cdSLKhuyenMai.setValueText(Long.toString(data.getSoLuongKhuyenMai()));
		}
	}

	public void setHourData(List<StatisticData> data, LocalTime fromTime, LocalTime toTime) {
		resetCharts();

		chDoanhThu.setChartTitle("Doanh thu trong ca làm việc");
		chHoaDon.setChartTitle("Số lượng hóa đơn trong ca làm việc");
		chVeBan.setChartTitle("Số lượng vé bán ra trong ca làm việc");
		chVeHuy.setChartTitle("Số lượng vé được hủy trong ca làm việc");

		String strFrom = fromTime.format(FormatterConstants.TIME);
		String strTo = toTime.format(FormatterConstants.TIME);

		String subtitle = "Từ lúc " + strFrom + " đến lúc " + strTo;
		chDoanhThu.addChartSubtitle(subtitle);
		chHoaDon.addChartSubtitle(subtitle);
		chVeBan.addChartSubtitle(subtitle);
		chVeHuy.addChartSubtitle(subtitle);

		// Đổi label trục x thành giờ
		chDoanhThu.setCategoryAxisLabel("Giờ");
		chHoaDon.setCategoryAxisLabel("Giờ");
		chVeBan.setCategoryAxisLabel("Giờ");
		chVeHuy.setCategoryAxisLabel("Giờ");

		if (data != null) {
			data.forEach(d -> {
				LocalTime time = (LocalTime) d.getTarget();
				datasetDoanhThu.addValue(d.getDoanhThu(), "Doanh thu", FormatterConstants.TIME.format(time));
				datasetHoaDon.addValue(d.getSoLuongHoaDon(), "Số lượng hóa đơn", FormatterConstants.TIME.format(time));
				datasetVeBan.addValue(d.getSoLuongVeBan(), "Số lượng vé bán", FormatterConstants.TIME.format(time));
				datasetVeHuy.addValue(d.getSoLuongVeHuy(), "Số lượng vé hủy", FormatterConstants.TIME.format(time));
			});
		}
	}

	public void setWeekData(List<StatisticData> data, LocalDate fromDate, LocalDate toDate) {
		resetCharts();

		chDoanhThu.setChartTitle("Doanh thu theo ngày");
		chHoaDon.setChartTitle("Số lượng hóa đơn theo ngày");
		chVeBan.setChartTitle("Số lượng vé bán ra theo ngày");
		chVeHuy.setChartTitle("Số lượng vé được hủy theo ngày");

		String strFrom = FormatterConstants.DATE.format(fromDate);
		String strTo = FormatterConstants.DATE.format(toDate);

		String subtitle = "Từ ngày " + strFrom + " đến ngày " + strTo;
		chDoanhThu.addChartSubtitle(subtitle);
		chHoaDon.addChartSubtitle(subtitle);
		chVeBan.addChartSubtitle(subtitle);
		chVeHuy.addChartSubtitle(subtitle);

		// Đổi label trục x thành ngày
		chDoanhThu.setCategoryAxisLabel("Ngày");
		chHoaDon.setCategoryAxisLabel("Ngày");
		chVeBan.setCategoryAxisLabel("Ngày");
		chVeHuy.setCategoryAxisLabel("Ngày");

		if (data != null) {
			data.forEach(d -> {
				LocalDate date = (LocalDate) d.getTarget();
				datasetDoanhThu.addValue(d.getDoanhThu(), "Doanh thu", FormatterConstants.DATE.format(date));
				datasetHoaDon.addValue(d.getSoLuongHoaDon(), "Số lượng hóa đơn", FormatterConstants.DATE.format(date));
				datasetVeBan.addValue(d.getSoLuongVeBan(), "Số lượng vé bán", FormatterConstants.DATE.format(date));
				datasetVeHuy.addValue(d.getSoLuongVeHuy(), "Số lượng vé hủy", FormatterConstants.DATE.format(date));
			});
		}
	}

	private void resetCharts() {
		datasetDoanhThu.clear();
		datasetHoaDon.clear();
		datasetVeBan.clear();
		datasetVeHuy.clear();

		chDoanhThu.clearSubtitles();
		chHoaDon.clearSubtitles();
		chVeBan.clearSubtitles();
		chVeHuy.clearSubtitles();
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		double pageWidth = pageFormat.getImageableWidth();
		double pageHeight = pageFormat.getImageableHeight();
		double scaleX = pageWidth / getContentPane().getWidth();
		g2d.scale(scaleX, scaleX);

		double scaledPageHeight = pageHeight / scaleX;
		int totalPages = (int) Math.ceil(getContentPane().getHeight() / scaledPageHeight);

		if (pageIndex >= totalPages) {
			return NO_SUCH_PAGE;
		}

		g2d.translate(pageFormat.getImageableX(), -pageIndex * scaledPageHeight);

		pnSouth.setVisible(false);
		getContentPane().printAll(g2d);
		pnSouth.setVisible(true);

		return PAGE_EXISTS;
	}

}
