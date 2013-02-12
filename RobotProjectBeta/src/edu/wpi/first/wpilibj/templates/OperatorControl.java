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
    Waiter loaderControl = new Waiter();

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
    
    /**
     * Updates loader wheel speed based on photosensor input
     */
    public void updateLoader() {
        if (myPhotosensor.get() && !bayIsFull) {
            loaderControl.waitXLoops(10);
        }
        //
        if (bayIsFull) {
            bayIsFull = !myShooterPiston.getPosition();
        } else {
            bayIsFull = myPhotosensor.get();
        }
        //
        if (loaderControl.timeUp()) {
            myLoaderWheel.set(RELAY_ON);
        } else {
            myLoaderWheel.set(RELAY_OFF);
        }
    }
    
    /**
     * Updates the 10 ms systems
     */
    public void updateSystems() {
        myDrive.setSpeed(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
        myShooterScrew.setMovement(leftStick.getRawButton(LEFT_BUTTON_THREE), leftStick.getRawButton(LEFT_BUTTON_TWO));
        myShooterPiston.setPosition(leftStick.getRawButton(LEFT_TRIGGER));
    }
    
    public void tenMSLoop() {
    
        //Take a manual picture with the camera for processing
        myCamera.takePicture(leftStick.getRawButton(LEFT_TRIGGER));

        updateLoader();
        updateSystems();
        updateDashboard();
    }
    
    public void hundredMSLoop() {
        //DISABLED for testing
//        if (leftStick.getRawButton(LEFT_BUTTON_EIGHT)) {
//            myAutoShooter.aim();
//        }
    }
    
    public void thousandMSLoop() {
        myCamera.findParticles();
    }
}
