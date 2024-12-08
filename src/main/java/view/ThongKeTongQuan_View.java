package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JPanel;

import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.formdev.flatlaf.FlatLightLaf;

import constant.FontConstants;
import model.StatisticData;
import model.TieuChiThongKe;
import model.TrainLineChart;
import other.TrainChart;
import other.TrainPanel;
import other.PrimaryButton;
import other.RoundedButton;
import other.TrainStatisticCard;
import other.TrainTitle;

public class ThongKeTongQuan_View extends View implements Printable {

	private static final long serialVersionUID = 4640477356217922276L;

	private static final DateTimeFormatter FMT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter FMT_DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private static final NumberFormat FMT_MONEY = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

	private final DefaultCategoryDataset datasetDoanhThu = new DefaultCategoryDataset();
	private final DefaultCategoryDataset datasetHoaDon = new DefaultCategoryDataset();
	private final DefaultCategoryDataset datasetVeBan = new DefaultCategoryDataset();
	private final DefaultCategoryDataset datasetVeHuy = new DefaultCategoryDataset();

	private Box pNorth;
	private Box pSouth;
	private RoundedButton btnIn;

	private TrainTitle tToday;
	private JPanel pCards;
	private TrainStatisticCard cDoanhThu;
	private TrainStatisticCard cSLHoaDon;
	private TrainStatisticCard cSLVeBan;
	private TrainStatisticCard cSLVeHuy;
	private TrainStatisticCard cSLKhuyenMai;

	private TrainPanel pCenter;
	private TrainLineChart lcDoanhThu;
	private TrainLineChart lcHoaDon;
	private TrainLineChart lcVeBan;
	private TrainLineChart lcVeHuy;

	public ThongKeTongQuan_View(String name, String imagePath) {
		super(name, imagePath);
		FlatLightLaf.setup();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1800, 1100);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		setContentPane(new TrainPanel(new BorderLayout(16, 16)));

		// NORTH
		add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);

		pNorth.add(tToday = new TrainTitle("Tổng quan hôm nay (dd/MM/yyyy)"));
		tToday.setAlignmentX(LEFT_ALIGNMENT);

		pNorth.add(pCards = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 8)));
		pCards.setOpaque(false);
		pCards.setAlignmentX(LEFT_ALIGNMENT);

		pCards.add(cDoanhThu = new TrainStatisticCard("Doanh thu"));
		pCards.add(cSLHoaDon = new TrainStatisticCard("Số lượng hóa đơn"));
		pCards.add(cSLVeBan = new TrainStatisticCard("Vé bán được"));
		pCards.add(cSLVeHuy = new TrainStatisticCard("Vé đã hủy"));
		pCards.add(cSLKhuyenMai = new TrainStatisticCard("Khuyến mãi đã dùng"));

		// CENTER
		add(pCenter = new TrainPanel(new GridLayout(2, 2, 16, 16)));

		lcDoanhThu = TrainChart.createLineChart(datasetDoanhThu, "Doanh thu theo ngày", "Ngày", "Doanh thu (VNĐ)");
		lcHoaDon = TrainChart.createLineChart(datasetHoaDon, "Số lượng hóa đơn theo ngày", "Ngày", "Số lượng hóa đơn");
		lcVeBan = TrainChart.createLineChart(datasetVeBan, "Số lượng vé bán theo ngày", "Ngày", "Số lượng vé bán ra");
		lcVeHuy = TrainChart.createLineChart(datasetVeHuy, "Số lượng vé hủy theo ngày", "Ngày", "Số lượng vé đã hủy");

		pCenter.add(lcDoanhThu.getPanel());
		pCenter.add(lcHoaDon.getPanel());
		pCenter.add(lcVeBan.getPanel());
		pCenter.add(lcVeHuy.getPanel());

		// SOUTH
		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(btnIn = new PrimaryButton("In thống kê"));

	}

	public void loadTodayStatistic(List<StatisticData> data) {
		String today = data.get(0).getNgayThongKe().format(FMT_DATE);
		tToday.setText("Tổng quan hôm nay (" + today + ")");

		cDoanhThu.setValueText(FMT_MONEY.format(data.get(0).getDoanhThu()));
		cSLHoaDon.setValueText(Integer.toString(data.get(0).getSoLuongHoaDon()));
		cSLVeBan.setValueText(Integer.toString(data.get(0).getSoLuongVeBanRa()));
		cSLVeHuy.setValueText(Integer.toString(data.get(0).getSoLuongVeDaHuy()));
		cSLKhuyenMai.setValueText(Integer.toString(data.get(0).getSoLuongKhuyenMaiDaDung()));
	}

	public void loadWeekStatistic(List<StatisticData> data, TieuChiThongKe tieuChi) {
		String from = tieuChi.getTuLuc().format(FMT_DATE);
		String to = tieuChi.getDenLuc().format(FMT_DATE);

		lcDoanhThu.getChart().addSubtitle(new TextTitle("Từ ngày " + from + " đến ngày " + to, FontConstants.CAPTION));
		lcHoaDon.getChart().addSubtitle(new TextTitle("Từ ngày " + from + " đến ngày " + to, FontConstants.CAPTION));
		lcVeBan.getChart().addSubtitle(new TextTitle("Từ ngày " + from + " đến ngày " + to, FontConstants.CAPTION));
		lcVeHuy.getChart().addSubtitle(new TextTitle("Từ ngày " + from + " đến ngày " + to, FontConstants.CAPTION));

		datasetDoanhThu.clear();
		datasetHoaDon.clear();
		datasetVeBan.clear();
		datasetVeHuy.clear();

		int index = 0;
		LocalDateTime current = tieuChi.getTuLuc();
		while (current.isBefore(tieuChi.getDenLuc().plusDays(1))) {
			if (index < data.size() && data.get(index).getNgayThongKe().equals(current)) {
				datasetDoanhThu.addValue(data.get(index).getDoanhThu(), "Doanh thu", current.format(FMT_DATE));
				datasetHoaDon.addValue(data.get(index).getSoLuongHoaDon(), "Hóa đơn", current.format(FMT_DATE));
				datasetVeBan.addValue(data.get(index).getSoLuongVeBanRa(), "Vé bán", current.format(FMT_DATE));
				datasetVeHuy.addValue(data.get(index).getSoLuongVeDaHuy(), "Vé hủy", current.format(FMT_DATE));
				index++;
			} else {
				datasetDoanhThu.addValue(0, "Doanh thu", current.format(FMT_DATE));
				datasetHoaDon.addValue(0, "Hóa đơn", current.format(FMT_DATE));
				datasetVeBan.addValue(0, "Vé bán", current.format(FMT_DATE));
				datasetVeHuy.addValue(0, "Vé hủy", current.format(FMT_DATE));
			}
			current = current.plusDays(1);

		}

	}

	public void addInActionListener(ActionListener actionListener) {
		btnIn.addActionListener(actionListener);
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

		getContentPane().printAll(g2d);

		return PAGE_EXISTS;
	}

}
