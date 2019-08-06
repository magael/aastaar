package mj.aastaar.utils;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/**
 *
 * @author MJ
 */
public class InputListener {

    private Map<KeyCode, Boolean> buttonsDown;
    
    /**
     * Constructor for the InputListener class.
     */
    public InputListener() {
        buttonsDown = new HashMap<>();
    }
    
    /**
     * Log's the user's keyboard presses and releases into a hash map.
     * 
     * @param scene where the event is being listened to
     * @return map (of key code, boolean) indicating key press and release
     */
    public Map<KeyCode, Boolean> initInput(Scene scene) {
        scene.setOnKeyPressed(event -> {
            buttonsDown.put(event.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(event -> {
            buttonsDown.put(event.getCode(), Boolean.FALSE);
        });
        return buttonsDown;
    }
}
