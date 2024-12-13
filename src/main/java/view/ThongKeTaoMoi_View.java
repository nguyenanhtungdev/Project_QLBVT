package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;

import component.thongke.ThongKeDatePanel;
import component.thongke.ThongKeSelectorPanel;
import component.thongke.ThongKeTimePanel;
import component.thongke.ThongKeTitledBox;
import other.PrimaryButton;
import other.SecondaryButton;
import other.TrainPanel;

public class ThongKeTaoMoi_View extends View {

	private static final long serialVersionUID = -3683814467745193485L;

	private TrainPanel pCenter;
	private Box pSouth;

	private ThongKeTitledBox pKhachHang;
	private ThongKeTitledBox pNhanVien;
	private ThongKeTitledBox pCaLam;
	private ThongKeTitledBox pKhuyenMai;
	private ThongKeTitledBox pChuyenTau;
	private ThongKeTitledBox pTau;
	private ThongKeTitledBox pToaTau;
	private ThongKeTitledBox pGheTau;
	private ThongKeTitledBox pVeTau;
	private ThongKeTitledBox pThoiGian;

	private ThongKeSelectorPanel pKhachHangSelector;
	private ThongKeSelectorPanel pKhachHangCategory;
	private ThongKeSelectorPanel pNhanVienSelector;
	private ThongKeSelectorPanel pCaLamSelector;
	private ThongKeSelectorPanel pKhuyenMaiSelector;
	private ThongKeSelectorPanel pChuyenTauSelector;
	private ThongKeSelectorPanel pTauSelector;
	private ThongKeSelectorPanel pTauStatus;
	private ThongKeSelectorPanel pToaTauSelector;
	private ThongKeSelectorPanel pToaTauStatus;
	private ThongKeSelectorPanel pGheTauSelector;
	private ThongKeSelectorPanel pGheTauStatus;
	private ThongKeSelectorPanel pVeTauCategory;
	private ThongKeSelectorPanel pVeTauStatus;

	private ThongKeDatePanel pThoiGianDate;
	private ThongKeTimePanel pThoiGianTime;

	private SecondaryButton btnXoaRong;
	private PrimaryButton btnXemBaoCao;

	public ThongKeTaoMoi_View(String name, String imagePath) {
		super(name, imagePath);

		setContentPane(new TrainPanel(new BorderLayout(16, 16), new Insets(16, 16, 16, 16)));

		add(pCenter = new TrainPanel(new GridLayout(5, 2, 8, 8)));
		pCenter.add(pKhachHang = new ThongKeTitledBox("Bộ lọc khách hàng", BoxLayout.Y_AXIS));
		pKhachHang.add(pKhachHangSelector = new ThongKeSelectorPanel("Khách hàng"));
		pKhachHang.add(pKhachHangCategory = new ThongKeSelectorPanel("Loại khách hàng"));

		pCenter.add(pNhanVien = new ThongKeTitledBox("Bộ lọc nhân viên", BoxLayout.Y_AXIS));
		pNhanVien.add(pNhanVienSelector = new ThongKeSelectorPanel("Nhân viên"));

		pCenter.add(pCaLam = new ThongKeTitledBox("Bộ lọc ca làm", BoxLayout.Y_AXIS));
		pCaLam.add(pCaLamSelector = new ThongKeSelectorPanel("Ca làm"));

		pCenter.add(pKhuyenMai = new ThongKeTitledBox("Bộ lọc khuyến mãi", BoxLayout.Y_AXIS));
		pKhuyenMai.add(pKhuyenMaiSelector = new ThongKeSelectorPanel("Khuyến mãi"));

		pCenter.add(pChuyenTau = new ThongKeTitledBox("Bộ lọc chuyến tàu", BoxLayout.Y_AXIS));
		pChuyenTau.add(pChuyenTauSelector = new ThongKeSelectorPanel("Chuyến tàu"));

		pCenter.add(pTau = new ThongKeTitledBox("Bộ lọc tàu", BoxLayout.Y_AXIS));
		pTau.add(pTauSelector = new ThongKeSelectorPanel("Tàu"));
		pTau.add(pTauStatus = new ThongKeSelectorPanel("Trạng thái hoạt động"));

		pCenter.add(pToaTau = new ThongKeTitledBox("Bộ lọc toa tàu", BoxLayout.Y_AXIS));
		pToaTau.add(pToaTauSelector = new ThongKeSelectorPanel("Toa tàu"));
		pToaTau.add(pToaTauStatus = new ThongKeSelectorPanel("Trạng thái toa tàu"));

		pCenter.add(pGheTau = new ThongKeTitledBox("Bộ lọc ghế tàu", BoxLayout.Y_AXIS));
		pGheTau.add(pGheTauSelector = new ThongKeSelectorPanel("Ghế tàu"));
		pGheTau.add(pGheTauStatus = new ThongKeSelectorPanel("Trạng thái ghế"));

		pCenter.add(pVeTau = new ThongKeTitledBox("Bộ lọc vé", BoxLayout.Y_AXIS));
		pVeTau.add(pVeTauCategory = new ThongKeSelectorPanel("Loại vé tàu"));
		pVeTau.add(pVeTauStatus = new ThongKeSelectorPanel("Trạng thái vé"));

		pCenter.add(pThoiGian = new ThongKeTitledBox("Bộ lọc thời gian", BoxLayout.Y_AXIS));
		pThoiGian.add(pThoiGianDate = new ThongKeDatePanel());
		pThoiGian.add(pThoiGianTime = new ThongKeTimePanel());

		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(btnXoaRong = new SecondaryButton("Xóa rỗng"));
		pSouth.add(Box.createGlue());
		pSouth.add(btnXemBaoCao = new PrimaryButton("Xem báo cáo"));

		resizeComponents();

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}

	private void resizeComponents() {
		pKhachHangSelector.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pKhachHangCategory.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pNhanVienSelector.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pCaLamSelector.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pKhuyenMaiSelector.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pChuyenTauSelector.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pToaTauSelector.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pToaTauStatus.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pTauSelector.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pGheTauSelector.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pGheTauStatus.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pVeTauCategory.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pVeTauStatus.getLabel().setPreferredSize(pTauStatus.getLabel().getPreferredSize());

		pThoiGianDate.getLabelTuNgay().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pThoiGianDate.getLabelDenNgay().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pThoiGianDate.getTextFieldTuNgay().setMaximumSize(
				new Dimension(Integer.MAX_VALUE, pThoiGianDate.getTextFieldTuNgay().getPreferredSize().height));
		pThoiGianDate.getTextFieldfDenNgay().setMaximumSize(
				new Dimension(Integer.MAX_VALUE, pThoiGianDate.getTextFieldfDenNgay().getPreferredSize().height));

		Dimension d = pThoiGianDate.getTextFieldTuNgay().getPreferredSize();
		Dimension square = new Dimension(d.height, d.height);
		pThoiGianDate.getTextFieldTuNgay().getCalendarButton().setPreferredSize(square);
		pThoiGianDate.getTextFieldfDenNgay().getCalendarButton().setPreferredSize(square);

		pThoiGianTime.getLabelTuLuc().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pThoiGianTime.getLabelDenLuc().setPreferredSize(pTauStatus.getLabel().getPreferredSize());
		pThoiGianTime.getChooserTuLuc().setMaximumSize(
				new Dimension(Integer.MAX_VALUE, pThoiGianTime.getChooserTuLuc().getPreferredSize().height));
		pThoiGianTime.getChooserDenLuc().setMaximumSize(
				new Dimension(Integer.MAX_VALUE, pThoiGianTime.getChooserDenLuc().getPreferredSize().height));
	}

	public void addBtnKhachHangSelectorCallback(Runnable callback) {
		pKhachHangSelector.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnKhachHangCategoryCallback(Runnable callback) {
		pKhachHangCategory.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnNhanVienSelectorCallback(Runnable callback) {
		pNhanVienSelector.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnCaLamSelectorCallback(Runnable callback) {
		pCaLamSelector.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnKhuyenMaiSelectorCallback(Runnable callback) {
		pKhuyenMaiSelector.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnChuyenTauSelectorCallback(Runnable callback) {
		pChuyenTauSelector.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnTauSelectorCallback(Runnable callback) {
		pTauSelector.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnTauStatusCallback(Runnable callback) {
		pTauStatus.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnToaTauSelectorCallback(Runnable callback) {
		pToaTauSelector.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnToaTauStatusCallback(Runnable callback) {
		pToaTauStatus.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnGheTauSelectorCallback(Runnable callback) {
		pGheTauSelector.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnGheTauStatusCallback(Runnable callback) {
		pGheTauStatus.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnVeTauCategoryCallback(Runnable callback) {
		pVeTauCategory.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnVeTauStatusCallback(Runnable callback) {
		pVeTauStatus.getButton().addActionListener(e -> callback.run());
	}

	public void addBtnXoaRongCallback(Runnable callback) {
		btnXoaRong.addActionListener(e -> callback.run());
	}

	public void addBtnXemBaoCaoCallback(Runnable callback) {
		btnXemBaoCao.addActionListener(e -> callback.run());
	}

	public ThongKeSelectorPanel getKhachHangSelector() {
		return pKhachHangSelector;
	}

	public ThongKeSelectorPanel getKhachHangCategory() {
		return pKhachHangCategory;
	}

	public ThongKeSelectorPanel getNhanVienSelector() {
		return pNhanVienSelector;
	}

	public ThongKeSelectorPanel getCaLamSelector() {
		return pCaLamSelector;
	}

	public ThongKeSelectorPanel getKhuyenMaiSelector() {
		return pKhuyenMaiSelector;
	}

	public ThongKeSelectorPanel getChuyenTauSelector() {
		return pChuyenTauSelector;
	}

	public ThongKeSelectorPanel getTauSelector() {
		return pTauSelector;
	}

	public ThongKeSelectorPanel getTauStatus() {
		return pTauStatus;
	}

	public ThongKeSelectorPanel getToaTauSelector() {
		return pToaTauSelector;
	}

	public ThongKeSelectorPanel getToaTauStatus() {
		return pToaTauStatus;
	}

	public ThongKeSelectorPanel getGheTauSelector() {
		return pGheTauSelector;
	}

	public ThongKeSelectorPanel getGheTauStatus() {
		return pGheTauStatus;
	}

	public ThongKeSelectorPanel getpVeTauCategory() {
		return pVeTauCategory;
	}

	public ThongKeSelectorPanel getVeTauStatus() {
		return pVeTauStatus;
	}

	public ThongKeDatePanel getThoiGianDateSelector() {
		return pThoiGianDate;
	}

	public ThongKeTimePanel getThoiGianTimeSelector() {
		return pThoiGianTime;
	}

}
