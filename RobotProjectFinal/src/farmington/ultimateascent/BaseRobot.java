/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Joystick;
import farmington.frameworks.AutoShooter;
import farmington.frameworks.Camera;
import farmington.frameworks.DriveTrain;
import farmington.frameworks.PyramidLifter;
import farmington.frameworks.ShooterLoader;
import farmington.frameworks.ShooterPiston;
import farmington.frameworks.ShooterScrew;
import farmington.frameworks.ShooterWheel;

/**
 * Main class which initializes all necessary systems.
 * @author 3414
 */
public class BaseRobot implements IRobot {
    
    protected Camera myCamera;
    protected Joystick leftStick, rightStick, gamepad;
    protected DriveTrain myDrive;
    protected ShooterScrew myShooterScrew;
    protected ShooterPiston myShooterPiston;
    protected ShooterWheel myShooterWheelOne;
    protected ShooterWheel myShooterWheelTwo;
    protected ShooterLoader myShooterLoader;
    protected PyramidLifter myPyramidLifter;
    protected AutoShooter myAutoShooter;
    
    /**
     * Main constructor for BaseRobot.
     */
    public BaseRobot() {
        myCamera = new Camera();
        leftStick = new Joystick(USB_ONE);
        rightStick = new Joystick(USB_TWO);
        gamepad = new Joystick(USB_THREE);
        myDrive = new DriveTrain(PWM_ONE, PWM_THREE, PWM_TWO, PWM_FOUR);
        myShooterScrew = new ShooterScrew(PWM_FIVE, ANALOG_ONE, DIO_EIGHT, DIO_NINE);
        myShooterPiston = new ShooterPiston(SOLENOID_FOUR, SOLENOID_THREE);
        myShooterWheelOne = new ShooterWheel(DIO_TWO, DIO_THREE, PWM_SIX, 100, 0, 0);
        myShooterWheelTwo = new ShooterWheel(DIO_FOUR, DIO_FIVE, PWM_SEVEN, 100, 0, 0);
        myShooterLoader = new ShooterLoader(RELAY_ONE, DIO_ONE, DIO_SEVEN);
        myPyramidLifter = new PyramidLifter(SOLENOID_ONE, SOLENOID_TWO);
        myAutoShooter = new AutoShooter();
    }
}
