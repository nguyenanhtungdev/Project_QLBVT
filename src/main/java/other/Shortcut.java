package other;
import java.awt.Checkbox;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.KeyStroke;

public class Shortcut {
    
    private static KeyStroke keyStroke;

	public static void addShowPasswordShortcut(JComponent passwordField, JCheckBox checkbox ,String showPasswordAction) {
        // Tạo phím tắt Ctrl + S
        keyStroke = KeyStroke.getKeyStroke("control S");
       
        InputMap inputMap = passwordField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = passwordField.getActionMap();
       
        inputMap.put(keyStroke, showPasswordAction);
        actionMap.put(showPasswordAction, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField instanceof RoundPassField) {
                    RoundPassField passwordFieldTyped = (RoundPassField) passwordField;
                    passwordFieldTyped.setEchoChar((char) 0);
                    checkbox.setSelected(true);
                }
            }
        });
    }
}

