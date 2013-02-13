/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import farmington.frameworks.LoopHandler;

/**
 *
 * @author Robotics
 */
public class OperatorControl extends BaseRobot implements IRobot {
    

    public OperatorControl() {
        super();
    }
    
    private void updateDashboard() {
        SmartDashboard.putNumber("Left Front Motor", myDrive.getLeftFrontMotor());
        SmartDashboard.putNumber("Left Back Motor", myDrive.getLeftBackMotor());
        SmartDashboard.putNumber("Right Front Motor", myDrive.getRightFrontMotor());
        SmartDashboard.putNumber("Right Back Motor", myDrive.getRightBackMotor());
        SmartDashboard.putNumber("Screw Encoder", myShooterScrew.getEncoderValue());
        SmartDashboard.putNumber("Screw Motor", myShooterScrew.getScrewMotorSpeed());
        SmartDashboard.putBoolean("Screw Sensor Low", myShooterScrew.getSensorLowValue());
        SmartDashboard.putBoolean("Screw Sensor High", myShooterScrew.getSensorHighValue());
        SmartDashboard.putBoolean("Shooter Piston", myShooterPiston.getPosition());
        SmartDashboard.putNumber("Current Loop Iteration", LoopHandler.getCurrentIteration());
        SmartDashboard.putNumber("Shooter Wheel One True Speed", myShooterWheelOne.getTrueSpeed());
        SmartDashboard.putNumber("Shooter Wheel Two True Speed", myShooterWheelTwo.getTrueSpeed());
    }
    
    public void tenMSLoop() {
    
        //Take a manual picture with the camera for processing
        myCamera.takePicture(leftStick.getRawButton(LEFT_TRIGGER));

        myDrive.setSpeed(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
        myShooterScrew.setMovement(leftStick.getRawButton(LEFT_BUTTON_THREE), leftStick.getRawButton(LEFT_BUTTON_TWO));
        myShooterPiston.setPosition(leftStick.getRawButton(LEFT_TRIGGER));
        myShooterLoader.updateLoader(myShooterPiston.getPosition());
        
        updateDashboard();
    }
    
    public void hundredMSLoop() {
        //DISABLED for testing
        if (leftStick.getRawButton(LEFT_BUTTON_EIGHT)) {
            myAutoShooter.aim();
        } else {
            if (rightStick.getRawButton(RIGHT_BUTTON_THREE)) {
                turnOnShooterWheels();
            }
        }
    }
    
    public void thousandMSLoop() {
        myCamera.findParticles();
    }
}
