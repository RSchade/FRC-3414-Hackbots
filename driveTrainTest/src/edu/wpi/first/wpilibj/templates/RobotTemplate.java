package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotTemplate extends SimpleRobot {
    
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    Talon leftMotorFront = new Talon(1);
    Talon leftMotorBack = new Talon(3);
    Talon rightMotorFront = new Talon(2);
    Talon rightMotorBack = new Talon(4);
    
    public void dashboardOutput() {
        SmartDashboard.putNumber("Left Front Motor", leftMotorFront.get());
        SmartDashboard.putNumber("Left Back Motor", leftMotorBack.get());
        SmartDashboard.putNumber("Right Front Motor", rightMotorFront.get());
        SmartDashboard.putNumber("Right Back Motor", rightMotorBack.get());
    }

    public void autonomous() {
        
    } 

    public void operatorControl() {
        while (isOperatorControl() && isEnabled())
        {
            leftMotorFront.set(leftStick.getRawAxis(2));
            leftMotorBack.set(leftStick.getRawAxis(2));
            rightMotorFront.set(-1*rightStick.getRawAxis(2));
            rightMotorBack.set(-1*rightStick.getRawAxis(2));
            
            dashboardOutput();
            
            Timer.delay(0.010);
        }
    }
    
    public void test() 
    {

    }
 
}
