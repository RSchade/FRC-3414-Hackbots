/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 * @author Cooper Riehl
 */

// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.Talon;

public class DriveTrain implements IRobot {

    Talon leftFrontMotor;
    Talon rightFrontMotor;
    Talon leftBackMotor;
    Talon rightBackMotor;
    
    public DriveTrain(int leftFrontSlot, int leftBackSlot, int rightFrontSlot, int rightBackSlot) {
        leftFrontMotor = new Talon(leftFrontSlot);
        leftBackMotor = new Talon(leftBackSlot);
        rightFrontMotor = new Talon(rightFrontSlot);
        rightBackMotor = new Talon(rightBackSlot);
    }
    
    public void update(double leftSpeed, double rightSpeed) {
        leftFrontMotor.set(leftSpeed);
        leftBackMotor.set(leftSpeed);
        rightFrontMotor.set(rightSpeed);
        rightBackMotor.set(rightSpeed);
    }
    
    public void getDashboard() {
        SmartDashboard.putNumber("Left Front Motor", leftFrontMotor.get());
        SmartDashboard.putNumber("Left Back Motor", leftBackMotor.get());
        SmartDashboard.putNumber("Right Front Motor", rightFrontMotor.get());
        SmartDashboard.putNumber("Right Back Motor", rightBackMotor.get());
    }
}
