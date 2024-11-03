package other;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.util.function.Function;

public class CustomButton extends JButton implements ActionListener{
	
	public CustomButtonFunction func;
	
	public CustomButton(String title, final ButtonStyle style, CustomButtonFunction func) 
	{		
		
		super(title); 
		this.func = func;
		
		setPreferredSize(new Dimension(style.getPrefWidth(), style.getPrefHeight()));	
		setMaximumSize(new Dimension(style.getPrefWidth(), style.getPrefHeight()));	
		setMinimumSize(new Dimension(style.getPrefWidth(), style.getPrefHeight()));	
		setFont(new Font(getFont().getName(), getFont().getStyle(), style.getFontSize()));		
		setBackground(style.getBasicBackgroundColor());			   
        setBorder(BorderFactory.createEmptyBorder());
        setFocusPainted(false);
        setForeground(style.getTitleColor());     
        
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                	setContentAreaFilled(false);
                	setOpaque(true);
                    setBackground(style.getPressedBackgroundColor());
                    
                } else if (getModel().isRollover()) {
                    setBackground(style.getHoverBackgroundColor());
                } else {
                	setContentAreaFilled(true);
                    setBackground(style.getBasicBackgroundColor());
                }
            }
        });
        addActionListener(this);
	}
	
	public CustomButton(String title, final ButtonStyle style, ImageIcon icon, CustomButtonIconSide iconSide, CustomButtonFunction func) {
		this(title, style, func);
		setIcon(icon);
		if(iconSide == CustomButtonIconSide.RIGHT) 
			setHorizontalTextPosition(SwingConstants.LEFT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(func!=null)func.pressed();		
	}
	
	public static class ButtonStyle{
		private int prefWidth;
		private int prefHeight;
		private int fontSize; 
		private Color titleColor;
		private Color basicBackgroundColor; 
		private Color hoverBackgroundColor;
		private Color pressedBackgroundColor;
		
		public ButtonStyle(int prefWidth, int prefHeight, int fontSize,	
				Color titleColor,
				final Color basicBackgroundColor, 
				final Color hoverBackgroundColor,
				final Color pressedBackgroundColor) 
		{
			this.prefWidth = prefWidth;
			this.prefHeight = prefHeight;
			this.fontSize = fontSize;
			this.titleColor = titleColor;
			this.basicBackgroundColor = basicBackgroundColor;
			this.hoverBackgroundColor = hoverBackgroundColor;
			this.pressedBackgroundColor = pressedBackgroundColor;		
		}

		public int getPrefWidth() {
			return prefWidth;
		}

		public int getPrefHeight() {
			return prefHeight;
		}

		public int getFontSize() {
			return fontSize;
		}

		public Color getTitleColor() {
			return titleColor;
		}

		public Color getBasicBackgroundColor() {
			return basicBackgroundColor;
		}

		public Color getHoverBackgroundColor() {
			return hoverBackgroundColor;
		}

		public Color getPressedBackgroundColor() {
			return pressedBackgroundColor;
		}
	}
	@FunctionalInterface
	public interface CustomButtonFunction{		
		public void pressed();
	}
	
	public static enum CustomButtonIconSide{
		LEFT, RIGHT;
	}
}
