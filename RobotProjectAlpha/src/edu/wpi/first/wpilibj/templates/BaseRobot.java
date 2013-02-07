/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Robotics
 */
public class BaseRobot implements IRobot {
    protected Camera myCamera;
    protected Joystick leftStick, rightStick;
    protected DriveTrain myDrive;
    protected Photosensor myPhotosensor;
    protected ShooterWheels myShooterWheels;
    protected ShooterScrew myShooterScrew;
    protected LEDController myLEDController;
    
    public BaseRobot() {
        myCamera = new Camera();
        leftStick = new Joystick(USB_ONE);
        rightStick = new Joystick(USB_TWO);
        myDrive = new DriveTrain(PWM_SLOT_ONE, PWM_SLOT_THREE, PWM_SLOT_TWO, PWM_SLOT_FOUR);
        myPhotosensor = new Photosensor(DIO_ONE, PWM_SLOT_FIVE);
        myShooterWheels = new ShooterWheels(PWM_SLOT_SEVEN, PWM_SLOT_EIGHT, SOLENOID_ONE);
        myShooterScrew = new ShooterScrew(PWM_SLOT_SIX);
        myLEDController = new LEDController(RELAY_ONE);
    }
}
