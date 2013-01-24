/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Robotics
 */

// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.Talon;

public class DriveTrain implements IRobot {
    public void drive() {
        leftMotorFront.set(leftStick.getRawAxis(2));
        leftMotorBack.set(leftStick.getRawAxis(2));
        rightMotorFront.set(-1*rightStick.getRawAxis(2));
        rightMotorBack.set(-1*rightStick.getRawAxis(2));
    }
}
