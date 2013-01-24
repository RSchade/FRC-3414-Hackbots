/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Robotics
 */

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public interface IRobot {
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    Talon leftMotorFront = new Talon(1);
    Talon leftMotorBack = new Talon(3);
    Talon rightMotorFront = new Talon(2);
    Talon rightMotorBack = new Talon(4);
}
