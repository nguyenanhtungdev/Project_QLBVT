package orther;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class RoundField extends JTextField {
    private Shape shape;
    private Color borderColor = Color.GRAY; // Màu viền mặc định

    public RoundField(int size) {
        super(size);
        setOpaque(false); // Để có thể thấy nền của JPanel
        setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font cho JTextField
        setBackground(Color.WHITE); // Đặt màu nền cho JTextField
        setForeground(Color.BLACK); // Đặt màu chữ
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create(); // Tạo bản sao Graphics
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Bật chống răng cưa
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15); // Bo góc
        super.paintComponent(g2); // Gọi phương thức paintComponent của JTextField
        g2.dispose(); // Giải phóng Graphics2D
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create(); // Tạo bản sao Graphics
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Bật chống răng cưa
        g2.setColor(borderColor); // Sử dụng màu viền
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15); // Vẽ viền bo góc
        g2.dispose(); // Giải phóng Graphics2D
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }

    // Phương thức để thay đổi màu viền
    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint(); // Vẽ lại để cập nhật màu viền
    }
}