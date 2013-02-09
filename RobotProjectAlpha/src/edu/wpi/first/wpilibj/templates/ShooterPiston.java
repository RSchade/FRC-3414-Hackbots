/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Robotics
 */
public class ShooterPiston {
    
    Solenoid piston;
    
    public ShooterPiston(int solenoidChannel) {
        piston = new Solenoid(solenoidChannel);
    }

    public void setPosition(boolean position) {
        piston.set(position);
    }
}
