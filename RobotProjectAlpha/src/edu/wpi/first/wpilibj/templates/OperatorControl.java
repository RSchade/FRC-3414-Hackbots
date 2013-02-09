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
    
    CustomAccelerometer accel;
    boolean bayIsFull = false;
    int targetIteration;
    int currentIteration;

    public OperatorControl() {
        super();
        accel = new CustomAccelerometer();
    }
    
    private void updateDashboard() {
        SmartDashboard.putNumber("Left Front Motor", myDrive.getLeftFrontMotor());
        SmartDashboard.putNumber("Left Back Motor", myDrive.getLeftBackMotor());
        SmartDashboard.putNumber("Right Front Motor", myDrive.getRightFrontMotor());
        SmartDashboard.putNumber("Right Back Motor", myDrive.getRightBackMotor());
        SmartDashboard.putBoolean("Photosensor", myPhotosensor.get());
        SmartDashboard.putNumber("Screw Encoder", myShooterScrew.getEncoder());
        SmartDashboard.putNumber("Screw Motor", myShooterScrew.getScrewMotor());
        SmartDashboard.putNumber("Accelerometer", accel.getAcceleration());
    }
    
    public void updateLoader() {
        if (myPhotosensor.get() && !bayIsFull) {
            targetIteration = currentIteration + 10;
        }
        //
        if (bayIsFull) {
            bayIsFull = !myShooterPiston.getPosition();
        } else {
            bayIsFull = myPhotosensor.get();
        }
        //
        if (currentIteration <= targetIteration) {
            myLoaderWheel.set(SPEED_FORWARD_HALF);
        } else {
            myLoaderWheel.set(SPEED_STOP);
        }
    }
    
    public void tenMSLoop(int loopCount) {
        
        currentIteration = loopCount;
    
        //Take a picture with the camera for processing
//        myCamera.takePicture(leftStick.getRawButton(LEFT_TRIGGER));
        
        //Update the loader wheel based on whether a frisbee is loaded
        updateLoader();
        
        //Update systems
        myDrive.setSpeed(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
        myShooterScrew.setMovement(leftStick.getRawButton(LEFT_BUTTON_THREE), leftStick.getRawButton(LEFT_BUTTON_TWO));
        myLEDController.set(rightStick.getRawButton(RIGHT_TRIGGER));
        myShooterPiston.setPosition(leftStick.getRawButton(LEFT_TRIGGER));
        
        updateDashboard();
    }
    
    public void hundredMSLoop(int loopCount) {
        
    }
    
    public void thousandMSLoop(int loopCount) {
        myCamera.findParticles();
    }
}
