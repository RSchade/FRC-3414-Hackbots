/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Robotics
 */
public class BaseRobot implements IRobot {
    
    protected Camera myCamera;
    protected Joystick leftStick, rightStick;
    protected DriveTrain myDrive;
    protected Photosensor myPhotosensor;
    protected ShooterWheel myShooterWheels;
    protected ShooterScrew myShooterScrew;
    protected ShooterPiston myShooterPiston;
    protected ShooterWheel myShooterWheelOne;
    protected ShooterWheel myShooterWheelTwo;
    protected Relay myLoaderWheel;
    protected AutoShooterBut myAutoShooter;
    
    public BaseRobot() {
        myCamera = new Camera();
        leftStick = new Joystick(USB_ONE);
        rightStick = new Joystick(USB_TWO);
        myDrive = new DriveTrain(PWM_SLOT_ONE, PWM_SLOT_THREE, PWM_SLOT_TWO, PWM_SLOT_FOUR);
        myLoaderWheel = new Relay(RELAY_ONE);
        myPhotosensor = new Photosensor(DIO_ONE);
        myShooterScrew = new ShooterScrew(PWM_SLOT_FIVE, DIO_SIX, DIO_SEVEN);
        myShooterPiston = new ShooterPiston(SOLENOID_ONE);
        myShooterWheelOne = new ShooterWheel(WHEEL_ONE, DIO_TWO, DIO_THREE, PWM_SLOT_SIX, 0.3, 0, 0);
        myShooterWheelOne = new ShooterWheel(WHEEL_TWO, DIO_FOUR, DIO_FIVE, PWM_SLOT_SEVEN, 0.3, 0, 0);
        myAutoShooter = new AutoShooterBut();
    }
    
    public void turnOnShooterWheels() {
        myShooterWheelOne.turnOn();
        myShooterWheelTwo.turnOn();
    }
}
