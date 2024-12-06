package other;

import javax.swing.*;

import constant.ColorConstants;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import model.Tau.TrangThaiTau; // Import enum từ class Tau

public class CustomTrainStatusButton extends JButton {
    private String trainCode;
    private TrangThaiTau trangThaiTau;
    private Color defaultRectColor = ColorConstants.PRIMARY_COLOR;
    private Color hoverRectColor = ColorConstants.HOVER_COLOR;
    private Color clickRectColor = new Color(163, 163, 163);
    private Color currentRectColor = defaultRectColor;

    private static CustomTrainStatusButton selectedButton = null;

    public CustomTrainStatusButton(String trainCode, TrangThaiTau trangThaiTau) {
        this.trainCode = trainCode;
        this.trangThaiTau = trangThaiTau;

        this.setPreferredSize(new Dimension(145, 125));
        this.setBackground(new Color(0, 0, 0, 0));
        this.setFocusPainted(false); 
        this.setBorderPainted(false); 
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (CustomTrainStatusButton.this != selectedButton) {
                    currentRectColor = hoverRectColor;
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (CustomTrainStatusButton.this != selectedButton) {
                    currentRectColor = defaultRectColor;
                    repaint();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selectButton();
            }
        });
    }

	public void selectButton() {
        if (selectedButton != null && selectedButton != this) {
            selectedButton.deselectButton();
        }
        selectedButton = this;
        currentRectColor = clickRectColor;
        repaint();
    }

	public void deselectButton() {
	    currentRectColor = defaultRectColor; 
	    repaint();
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ hình chữ nhật bo góc
        g2d.setColor(currentRectColor);
        g2d.fillRoundRect(10, 10, 125, 105, 30, 30);

        // Vẽ mã tàu
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(35, 13, 75, 20, 15, 15);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 13));
        FontMetrics fm = g2d.getFontMetrics();
        int trainCodeWidth = fm.stringWidth(trainCode);
        g2d.drawString(trainCode, 73 - trainCodeWidth / 2, 28);

     // Vẽ trạng thái tàu
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(23, 38, 100, 62, 15, 15);
        String status = getStatusText(trangThaiTau);
        
        // Tính toán vị trí vẽ và xuống hàng nếu cần
        g2d.setColor(new Color(70, 130, 180));
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        String[] statusLines = wrapText(g2d, status, 100); // 100 là chiều rộng của ô trạng thái
        int lineHeight = fm.getHeight();
        for (int i = 0; i < statusLines.length; i++) {
            int statusWidth = fm.stringWidth(statusLines[i]);
            g2d.drawString(statusLines[i], 67 - statusWidth / 2, 60 + (i * lineHeight));
        }

        // Vẽ các vòng tròn ở dưới cùng
        g2d.setColor(Color.WHITE);
        g2d.fillOval(41, 102, 12, 12);
        g2d.fillOval(91, 102, 12, 12);

        g2d.dispose();
    }

    // Phương thức chuyển enum trạng thái thành chuỗi hiển thị
    private String getStatusText(TrangThaiTau trangThaiTau) {
        switch (trangThaiTau) {
            case HOAT_DONG: return "Hoạt Động";
            case BAO_TRI: return "Bảo Trì";
            case DUNG_HOAT_DONG: return "Dừng Hoạt Động";
            default: return "";
        }
    }
    
 // Phương thức để tự động xuống dòng
    private String[] wrapText(Graphics g, String text, int maxWidth) {
        FontMetrics fm = g.getFontMetrics();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();
        ArrayList<String> lines = new ArrayList<>();

        for (String word : words) {
            if (fm.stringWidth(currentLine + word) < maxWidth) {
                currentLine.append(word).append(" ");
            } else {
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder(word + " ");
            }
        }
        lines.add(currentLine.toString().trim());

        return lines.toArray(new String[0]);
    }
}
