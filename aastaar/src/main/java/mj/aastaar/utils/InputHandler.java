/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.utils;

import java.util.Map;
import javafx.scene.input.KeyCode;

/**
 *
 * @author MJ
 */
public class InputHandler {
    
    private Map<KeyCode, Boolean> buttonsDown;

    /**
     *
     * @param buttonsDown
     */
    public InputHandler(Map<KeyCode, Boolean> buttonsDown) {
        this.buttonsDown = buttonsDown;
    }

    /**
     *
     * @return map of keys pressed & released
     */
    public Map<KeyCode, Boolean> getButtonsDown() {
        return buttonsDown;
    }
    
    /**
     *
     * @return true if key pressed, else false
     */
    public boolean moveStartUp() {
        return genericInput(KeyCode.W);
    }

    private boolean genericInput(KeyCode kc) {
        return (buttonsDown.getOrDefault(kc, false));
    }
}
