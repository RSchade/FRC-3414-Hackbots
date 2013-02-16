/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.Talon;
import farmington.ultimateascent.IRobot;



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
    
    public void setSpeed(double leftSpeedInverse, double rightSpeed) {
        double leftSpeed = -1*leftSpeedInverse;
        
        //deadzone
        if (leftSpeed < -0.1 || leftSpeed > 0.1) {
            leftFrontMotor.set(leftSpeed);
            leftBackMotor.set(leftSpeed);
        } else {
            leftFrontMotor.set(0);
            leftBackMotor.set(0);
        }
        
        if (rightSpeed < -0.1 || rightSpeed > 0.1) {
            rightFrontMotor.set(rightSpeed);
            rightBackMotor.set(rightSpeed);
        } else {
            rightFrontMotor.set(0);
            rightBackMotor.set(0);
        }
    }
    
    public double getLeftFrontMotor() {
        return leftFrontMotor.get();
    }
    
    public double getLeftBackMotor() {
        return leftBackMotor.get();
    }
    
    public double getRightFrontMotor() {
        return rightFrontMotor.get();
    }
    
    public double getRightBackMotor() {
        return rightBackMotor.get();
    }
}
