package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import model.ThongKeNgay;
import model.ThongKeTuan;
import other.BieuDo;
import other.PrimaryButton;
import other.RoundedButton;
import other.TheThongKe;
import other.TieuDe;

public class TongQuanPage extends Page implements Printable {
	private static final long serialVersionUID = 4640477356217922276L;

	private TieuDe tieuDeNgay;
	private TieuDe tieuDeTuan;
	private TheThongKe theDoanhThu;
	private TheThongKe theSoLuongHoaDon;
	private TheThongKe theSoLuongVeBan;
	private TheThongKe theSoLuongVeHuy;
	private TheThongKe theSoLuongKhuyenMaiDaDung;
	private Box pNorth;
	private Box pCenter;
	private Box pSouth;
	private RoundedButton btnIn;
	private ChartPanel chart;

	private DefaultCategoryDataset doanhThuDataset = new DefaultCategoryDataset();

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

	public TongQuanPage(String name, String imagePath) {
		super(name, imagePath);

		setLayout(new BorderLayout());

		// NORTH
		pNorth = Box.createVerticalBox();

		tieuDeNgay = new TieuDe("Tổng quan trong ngày (dd/MM/yyyy)");
		tieuDeNgay.setAlignmentX(LEFT_ALIGNMENT);
		pNorth.add(tieuDeNgay);

		JPanel pThe = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pThe.setOpaque(false);
		pThe.setAlignmentX(LEFT_ALIGNMENT);

		pThe.add(theDoanhThu = new TheThongKe("Doanh thu", ""));
		pThe.add(theSoLuongHoaDon = new TheThongKe("Số lượng hóa đơn", ""));
		pThe.add(theSoLuongVeBan = new TheThongKe("Vé bán được", ""));
		pThe.add(theSoLuongVeHuy = new TheThongKe("Vé đã hủy", ""));
		pThe.add(theSoLuongKhuyenMaiDaDung = new TheThongKe("Khuyến mãi đã dùng", ""));
		pThe.setMaximumSize(pThe.getPreferredSize());
		pNorth.add(pThe);

		add(pNorth, BorderLayout.NORTH);

		// CENTER
		pCenter = Box.createVerticalBox();
		pCenter.add(tieuDeTuan = new TieuDe("Tổng quan doanh thu trong 7 ngày (dd/MM/yyyy - dd/MM/yyyy)", TieuDe.H3));
		pCenter.add(chart = BieuDo.taoBieuDoDuong(doanhThuDataset, "", "Ngày", "Doanh thu (VNĐ)"));
		add(pCenter, BorderLayout.CENTER);

		// SOUTH
		pSouth = Box.createHorizontalBox();
		pSouth.add(btnIn = new PrimaryButton("In báo cáo"));
		add(pSouth, BorderLayout.SOUTH);
	}

	public void loadThongKe(ThongKeNgay thongKeNgay, ThongKeTuan thongKeTuan) {
		if (thongKeNgay == null || thongKeTuan == null) {
			JOptionPane.showMessageDialog(this, "Không thể tạo thống kê!", "Lỗi truy xuất", JOptionPane.ERROR_MESSAGE);
			return;
		}

		tieuDeNgay.setText("Tổng quan trong ngày (" + dateFormatter.format(thongKeNgay.getNgayTaoThongKe()) + ")");
		theDoanhThu.setValueText(currencyFormatter.format(thongKeNgay.getDoanhThu()));
		theSoLuongHoaDon.setValueText(Integer.toString(thongKeNgay.getSoLuongHoaDon()));
		theSoLuongVeBan.setValueText(Integer.toString(thongKeNgay.getSoLuongVeBanRa()));
		theSoLuongVeHuy.setValueText(Integer.toString(thongKeNgay.getSoLuongVeDaHuy()));
		theSoLuongKhuyenMaiDaDung.setValueText(Integer.toString(thongKeNgay.getSoLuongKhuyenMaiDaDung()));

		tieuDeTuan.setText("Tổng quan doanh thu trong 7 ngày ("
				+ dateFormatter.format(thongKeTuan.getNgayTaoThongKe().minusDays(6)) + " - "
				+ dateFormatter.format(thongKeTuan.getNgayTaoThongKe()) + ")");
		doanhThuDataset.clear();
		for (int i = 0; i < 7; i++) {
			doanhThuDataset.addValue(thongKeTuan.getDoanhThu()[i], "Doanh thu",
					dateFormatter.format(thongKeTuan.getNgayTaoThongKe().minusDays(6 - i)));
		}
	}

	public void addInActionListener(ActionListener actionListener) {
		btnIn.addActionListener(actionListener);
	}

	@Override
	public Insets getInsets() {
		return new Insets(16, 16, 16, 16);
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		pSouth.setVisible(false);
		if (pageIndex > 0) {
			pSouth.setVisible(true);
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		double pageWidth = pageFormat.getImageableWidth();
		double pageHeight = pageFormat.getImageableHeight();
		double panelWidth = this.getWidth();
		double panelHeight = this.getHeight();
		double scaleX = pageWidth / panelWidth;
		double scaleY = pageHeight / panelHeight;
		double scale = Math.min(scaleX, scaleY);
		g2d.scale(scale, scale);

		this.printAll(g2d);

		return PAGE_EXISTS;
	}

}
