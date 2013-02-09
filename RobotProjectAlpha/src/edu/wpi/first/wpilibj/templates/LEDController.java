/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Robotics
 */
public class LEDController {
    Relay lightController;
    
    public LEDController (int relaySlot) {
        lightController = new Relay(relaySlot);
        lightController.setDirection(Relay.Direction.kForward);
    }
    
    public void set(boolean setState) {
        Relay.Value state;
        if (setState==true) {
            state = Relay.Value.kOn;
        } else {
            state = Relay.Value.kOff;
        }
        lightController.set(state);
    }
}
