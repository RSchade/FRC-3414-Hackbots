/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Cooper Riehl
 */

import edu.wpi.first.wpilibj.Joystick;

public interface IRobot {
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
}
