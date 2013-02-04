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
public class LED {
    Relay lightController;
    private Relay.Value state = Relay.Value.kOff;
    
    public LED (int relaySlot) {
        lightController = new Relay(relaySlot);
        lightController.setDirection(Relay.Direction.kForward);
    }
    
    public void setLEDs(boolean setState) {
        if (setState==true) {
            state = Relay.Value.kOn;
        } else {
            state = Relay.Value.kOff;
        }
    }
    
    public void update() {
        lightController.set(state);
    }
}
