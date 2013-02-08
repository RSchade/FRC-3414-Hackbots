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
public class OperatorControl extends BaseRobot implements IRobot {
    
    Talon loaderWheel;

    public OperatorControl() {
        super();
        loaderWheel = new Talon(PWM_SLOT_NINE);
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
    
    public void loop() {
    
        //Take a picture with the camera for processing
        myCamera.takePicture(leftStick.getRawButton(LEFT_TRIGGER));
        
        //Update systems
        myDrive.setSpeed(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
        myShooterScrew.updateManual(leftStick.getRawButton(LEFT_BUTTON_TWO), leftStick.getRawButton(LEFT_BUTTON_THREE));
        myLEDController.update(rightStick.getRawButton(RIGHT_TRIGGER));
        
        if (myPhotosensor.get()) {
            loaderWheel.set(SPEED_FORWARD_HALF);
        } else {
            loaderWheel.set(SPEED_STOP);
        }
        
        updateDashboard();
    }
}
