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
    private static final int PWM_SLOT_ONE = 1;
    private static final int PWM_SLOT_TWO = 2;
    private static final int PWM_SLOT_THREE = 3;
    private static final int PWM_SLOT_FOUR = 4;
    private static final int VERTICAL_AXIS = 2;
    
    
    
    Talon leftMotorFront = new Talon(PWM_SLOT_ONE);
    Talon leftMotorBack = new Talon(PWM_SLOT_THREE);
    Talon rightMotorFront = new Talon(PWM_SLOT_TWO);
    Talon rightMotorBack = new Talon(PWM_SLOT_FOUR);
    
    public void drive()
    {
        leftMotorFront.set(leftStick.getRawAxis(VERTICAL_AXIS));
        leftMotorBack.set(leftStick.getRawAxis(VERTICAL_AXIS));
        rightMotorFront.set(-1*rightStick.getRawAxis(VERTICAL_AXIS));
        rightMotorBack.set(-1*rightStick.getRawAxis(VERTICAL_AXIS));
    }
    public void getDashboard()
    {
        SmartDashboard.putNumber("Left Front Motor", leftMotorFront.get());
        SmartDashboard.putNumber("Left Back Motor", leftMotorBack.get());
        SmartDashboard.putNumber("Right Front Motor", rightMotorFront.get());
        SmartDashboard.putNumber("Right Back Motor", rightMotorBack.get());
    }
}
