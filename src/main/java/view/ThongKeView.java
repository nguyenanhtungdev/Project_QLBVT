package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.List;

public class ThongKeView extends View {
	private static final long serialVersionUID = -6982248081540597767L;

	private CardLayout cardLayout = new CardLayout();

	public ThongKeView(String name, String imagePath) {
		super(name, imagePath);
		setLayout(cardLayout);

		setBackground(Color.WHITE);
	}

	public void addPages(List<Page> pages) {
		for (Page page : pages) {
			add(page, page.getName());
		}
	}

	public void setVisiblePage(String name) {
		cardLayout.show(this, name);
	}

}
