package component.thongke;

import javax.swing.Box;
import javax.swing.border.TitledBorder;

import constant.ColorConstants;
import constant.FontConstants;

public class ThongKeTitledBox extends Box {

	private static final long serialVersionUID = -3549638793036108772L;

	public ThongKeTitledBox(String title, int orientation) {
		super(orientation);

		TitledBorder border = new TitledBorder(null, title, TitledBorder.LEFT, TitledBorder.TOP,
				FontConstants.HEADING_6);
		border.setTitleColor(ColorConstants.PRIMARY_COLOR);
		setBorder(border);
	}

}
