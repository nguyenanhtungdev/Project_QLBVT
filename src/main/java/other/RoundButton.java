package other;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RoundButton extends JButton implements MouseListener{
    
    private static final long serialVersionUID = 1L;
    private Color color_1, color_2;
    private int heSoBoGoc;
    private int khoangCachIcon;
    private int iconWidth, iconHeight;
    private Font font;
    private String url_Icon;

    public RoundButton(String label, Color color_1, Color color_2 , String url_Icon) {
        super(label);
        this.color_1 = color_1;
        this.color_2 = color_2;
        this.url_Icon = url_Icon;
        
        init();
        
        ImageIcon icon = new ImageIcon(getClass().getResource(url_Icon));
        Image scaledImage = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage)); 
        setIconTextGap(khoangCachIcon);
    }
    
    

    public RoundButton(String label, Color color_1, Color color_2) {
        super(label);
		this.color_1 = color_1;
		this.color_2 = color_2;
		init();
	}



	public void init() {
    	//Khởi tạo
        heSoBoGoc = 20;
        khoangCachIcon = 15;
        iconWidth = 32;
        iconHeight = 32;
        font = new Font("Arial", Font.BOLD, 16);
        
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(font);
        setForeground(Color.WHITE);
        setBackground(this.color_1);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(null);
        addMouseListener(this);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), heSoBoGoc, heSoBoGoc); 
        super.paintComponent(g);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		setBackground(color_2);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setBackground(color_1);
	}

	public Color getColor_1() {
		return color_1;
	}

	public void setColor_1(Color color_1) {
		this.color_1 = color_1;
	}

	public Color getColor_2() {
		return color_2;
	}

	public void setColor_2(Color color_2) {
		this.color_2 = color_2;
	}

	public int getHeSoBoGoc() {
		return heSoBoGoc;
	}

	public void setHeSoBoGoc(int heSoBoGoc) {
		this.heSoBoGoc = heSoBoGoc;
	}

	public int getKhoangCachIcon() {
		return khoangCachIcon;
	}

	public void setKhoangCachIcon(int khoangCachIcon) {
		this.khoangCachIcon = khoangCachIcon;
	}

	public int getIconWidth() {
		return iconWidth;
	}

	public void setIconWidth(int iconWidth) {
		this.iconWidth = iconWidth;
	}

	public int getIconHeight() {
		return iconHeight;
	}

	public void setIconHeight(int iconHeight) {
		this.iconHeight = iconHeight;
	}

	public Font getFont() {
		return font;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}

	public String getUrl_Icon() {
		return url_Icon;
	}
	
	public void setUrl_Icon(String url_Icon) {
		this.url_Icon = url_Icon;
	}
}
