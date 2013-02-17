package farmington.frameworks;

import edu.wpi.first.wpilibj.Talon;
import farmington.ultimateascent.IRobot;

/**
 * This class controls all four Talons on the drive train.
 * @author 3414
 */
public class DriveTrain implements IRobot {

    Talon leftFrontMotor;
    Talon rightFrontMotor;
    Talon leftBackMotor;
    Talon rightBackMotor;
    
    /**
     * Main constructor for DriveTrain.
     * @param leftFrontSlot     PWM slot for the front-left Talon.
     * @param leftBackSlot      PWM slot for the back-left Talon.
     * @param rightFrontSlot    PWM slot for the front-right Talon.
     * @param rightBackSlot     PWM slot for the back-right Talon.
     */
    public DriveTrain(int leftFrontSlot, int leftBackSlot, int rightFrontSlot, int rightBackSlot) {
        leftFrontMotor = new Talon(leftFrontSlot);
        leftBackMotor = new Talon(leftBackSlot);
        rightFrontMotor = new Talon(rightFrontSlot);
        rightBackMotor = new Talon(rightBackSlot);
    }
    
    /**
     * Sets the speed of the left and right sides relative to battery voltage.
     * @param leftSpeed     
     * @param rightSpeed    
     */
    public void setSpeed(double leftSpeed, double rightSpeed) {
        
        /*
         * Since the left side Talons are backwards, we need to invert the
         * input value.
         */
        leftSpeed = -leftSpeed;
        
        if (leftSpeed < -JOYSTICK_DEADZONE || leftSpeed > JOYSTICK_DEADZONE) {
            leftFrontMotor.set(leftSpeed);
            leftBackMotor.set(leftSpeed);
        } else {
            leftFrontMotor.set(0);
            leftBackMotor.set(0);
        }
        
        if (rightSpeed < -JOYSTICK_DEADZONE || rightSpeed > JOYSTICK_DEADZONE) {
            rightFrontMotor.set(rightSpeed);
            rightBackMotor.set(rightSpeed);
        } else {
            rightFrontMotor.set(0);
            rightBackMotor.set(0);
        }
    }
    
    /**
     * 
     * @return the speed of the front-left motor relative to battery voltage
     */
    public double getLeftFrontMotor() {
        return leftFrontMotor.get();
    }
    
    /**
     * 
     * @return the speed of the back-left motor relative to battery voltage
     */
    public double getLeftBackMotor() {
        return leftBackMotor.get();
    }
    
    /**
     * 
     * @return the speed of the front-right motor relative to battery voltage
     */
    public double getRightFrontMotor() {
        return rightFrontMotor.get();
    }
    
    /**
     * 
     * @return the speed of the back-right motor relative to battery voltage
     */
    public double getRightBackMotor() {
        return rightBackMotor.get();
    }
}
