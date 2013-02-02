/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Main extends SimpleRobot implements IRobot {

    Joystick leftStick = new Joystick(USB_ONE);
    Joystick rightStick = new Joystick(USB_TWO);
    
    DriveTrain myDrive = new DriveTrain(PWM_SLOT_ONE, PWM_SLOT_THREE, PWM_SLOT_TWO, PWM_SLOT_FOUR);
    Camera myCamera = new Camera();
    LightSensor photosensor = new LightSensor(DIO_ONE, PWM_SLOT_FIVE);
    Shooter myShooter = new Shooter(PWM_SLOT_SEVEN, PWM_SLOT_EIGHT, SOLENOID_ONE);
    Screw myScrew = new Screw(PWM_SLOT_SIX);
    LED myLEDControl = new LED(RELAY_ONE);

    
    public void dashboardUpdate() {
        photosensor.getDashboard();
        myDrive.getDashboard();
    }
    
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        /**
         * This loops every 10 milliseconds
         */
        while(isOperatorControl() && isEnabled()) {
            //Set the screw motor
            myScrew.setMotor(leftStick.getRawButton(LEFT_BUTTON_TWO), leftStick.getRawButton(LEFT_BUTTON_THREE));
            
            //Take a picture with the camera for processing
            myCamera.update(leftStick.getRawButton(LEFT_TRIGGER));
            
            //Update systems
            myDrive.update(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
            myScrew.update();
            dashboardUpdate();
            
            //Loop delay
            Timer.delay(TIME_DELAY);
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {

    }
}
