/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Robotics
 */

// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.Talon;

public class DriveTrain implements IRobot
{
    Talon leftMotorFront = new Talon(1);
    Talon leftMotorBack = new Talon(3);
    Talon rightMotorFront = new Talon(2);
    Talon rightMotorBack = new Talon(4);
    
    public void drive()
    {
        leftMotorFront.set(leftStick.getRawAxis(2));
        leftMotorBack.set(leftStick.getRawAxis(2));
        rightMotorFront.set(-1*rightStick.getRawAxis(2));
        rightMotorBack.set(-1*rightStick.getRawAxis(2));
    }
    public void dashboard()
    {
        SmartDashboard.putNumber("Left Front Motor", leftMotorFront.get());
        SmartDashboard.putNumber("Left Back Motor", leftMotorBack.get());
        SmartDashboard.putNumber("Right Front Motor", rightMotorFront.get());
        SmartDashboard.putNumber("Right Back Motor", rightMotorBack.get());
    }
}
