/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Robotics
 */
public class OperatorControl extends BaseRobot implements IRobot {
    
    boolean wheelLoopControl = false;

    public OperatorControl() {
        super();
    }
    
    private void updateDashboard() {
        SmartDashboard.putNumber("Left Front Motor", myDrive.getLeftFrontMotor());
        SmartDashboard.putNumber("Left Back Motor", myDrive.getLeftBackMotor());
        SmartDashboard.putNumber("Right Front Motor", myDrive.getRightFrontMotor());
        SmartDashboard.putNumber("Right Back Motor", myDrive.getRightBackMotor());
        SmartDashboard.putBoolean("Photosensor", myPhotosensor.get());
        SmartDashboard.putNumber("Screw Encoder", myShooterScrew.getEncoder());
        SmartDashboard.putNumber("Screw Motor", myShooterScrew.getScrewMotor());
    }
    
    private void wheelControl() {
        if (leftStick.getRawButton(RIGHT_BUTTON_TWO)) {
            myShooterWheelOne.turnOn();
            myShooterWheelTwo.turnOn();
        }
    }
    
    public void tenMSLoop() {
    
        //Take a picture with the camera for processing
//        myCamera.takePicture(leftStick.getRawButton(LEFT_TRIGGER));
        
        //Update systems
        myDrive.setSpeed(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
        myShooterScrew.setMovement(leftStick.getRawButton(LEFT_BUTTON_THREE), leftStick.getRawButton(LEFT_BUTTON_TWO));
        myLEDController.set(rightStick.getRawButton(RIGHT_TRIGGER));
        myPhotosensor.updateMotorSpeed();
        myShooterPiston.setPosition(leftStick.getRawButton(LEFT_TRIGGER));
        wheelControl();
        
        updateDashboard();
    }
    
    public void hundredMSLoop() {
        
    }
    
    public void thousandMSLoop() {
        myCamera.findParticles();
    }
}
