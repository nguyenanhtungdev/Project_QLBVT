package other; 

import javax.swing.*;

import constant.ColorConstants;

import java.awt.*;

public class CustomTitleLable extends JLabel {
    public CustomTitleLable(String text) {
        super(text); 
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Arial", Font.BOLD, 18));
        setForeground(Color.WHITE);
        setBackground(ColorConstants.PRIMARY_COLOR);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}
