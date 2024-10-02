package orther;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class RoundButton extends JButton {
    
    private static final long serialVersionUID = 1L;

    public RoundButton(String label) {
        super(label);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setBackground(Color.decode("#4682A9"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bo góc 20px
        super.paintComponent(g);
    }
}
