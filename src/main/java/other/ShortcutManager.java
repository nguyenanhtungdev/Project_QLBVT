package other;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class ShortcutManager {
    private JsonNode shortcuts;
    private String filePath = getClass().getResource("/Files/shortcuts.json").getPath();

    // Constructor: Load JSON
    public ShortcutManager() throws Exception {
    	 try {
             ObjectMapper mapper = new ObjectMapper();
             shortcuts = mapper.readTree(new File(filePath));
         } catch (Exception e) {
             System.err.println("Lỗi khi tải phím tắt: " + e.getMessage());
             shortcuts = null;
         }
    }

    public void registerShortcut(JComponent component, String actionName, Runnable action) throws Exception {
        try {
            JsonNode shortcut = shortcuts.get("shortcuts").get(actionName);
            if (shortcut == null) {
                System.out.println("Shortcut '" + actionName + "' not found in JSON.");
                return;
            }

            int keyCode = shortcut.get("keyCode").asInt();
            int modifiers = shortcut.has("modifiers") ? shortcut.get("modifiers").asInt() : 0;

            InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = component.getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(keyCode, modifiers), actionName);
            actionMap.put(actionName, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    action.run();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
