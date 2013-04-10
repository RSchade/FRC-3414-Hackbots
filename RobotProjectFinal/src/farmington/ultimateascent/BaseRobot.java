/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Joystick;
import farmington.frameworks.AutoShooter;
import farmington.frameworks.Camera;
import farmington.frameworks.DriveTrain;
import farmington.frameworks.LED;
import farmington.frameworks.PyramidLifter;
import farmington.frameworks.ShooterLoader;
import farmington.frameworks.ShooterPiston;
import farmington.frameworks.ShooterScrew;
import farmington.frameworks.ShooterWheel;
import farmington.frameworks.ShooterWheelsCombined;

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
    private ShooterWheel shooterWheelOne;
    private ShooterWheel shooterWheelTwo;
    protected ShooterWheelsCombined myShooterWheels;
    protected ShooterLoader myShooterLoader;
    protected PyramidLifter myPyramidLifter;
    protected AutoShooter myAutoShooter;
    protected LED myLed;
    
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
        shooterWheelOne = new ShooterWheel(DIO_FOUR, DIO_FIVE, PWM_SIX);
        shooterWheelTwo = new ShooterWheel(DIO_TWO, DIO_THREE, PWM_SEVEN);
        myShooterWheels = new ShooterWheelsCombined(shooterWheelOne, shooterWheelTwo);
        myShooterLoader = new ShooterLoader(RELAY_ONE, DIO_ONE, DIO_SEVEN);
        myPyramidLifter = new PyramidLifter(SOLENOID_ONE, SOLENOID_TWO);
        myAutoShooter = new AutoShooter();
        myLed = new LED(SOLENOID_EIGHT, SOLENOID_SIX, SOLENOID_SEVEN);
    }
}
