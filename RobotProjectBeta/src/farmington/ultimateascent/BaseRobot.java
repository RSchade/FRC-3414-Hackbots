/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import farmington.frameworks.ShooterPiston;
import farmington.frameworks.ShooterWheel;
import farmington.frameworks.ShooterScrew;
import farmington.frameworks.AutoShooter;
import farmington.frameworks.Camera;
import farmington.frameworks.ShooterLoader;
import farmington.frameworks.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Robotics
 */
public class BaseRobot implements IRobot {
    
    protected Camera myCamera;
    protected Joystick leftStick, rightStick;
    protected DriveTrain myDrive;
    protected ShooterScrew myShooterScrew;
    protected ShooterPiston myShooterPiston;
    protected ShooterWheel myShooterWheelOne;
    protected ShooterWheel myShooterWheelTwo;
    protected AutoShooter myAutoShooter;
    protected ShooterLoader myShooterLoader;
    
    public BaseRobot() {
        myCamera = new Camera();
        leftStick = new Joystick(USB_ONE);
        rightStick = new Joystick(USB_TWO);
        myDrive = new DriveTrain(PWM_SLOT_ONE, PWM_SLOT_THREE, PWM_SLOT_TWO, PWM_SLOT_FOUR);
        myShooterScrew = new ShooterScrew(PWM_SLOT_FIVE, DIO_SIX, DIO_SEVEN, DIO_EIGHT, DIO_NINE);
        myShooterPiston = new ShooterPiston(SOLENOID_ONE);
        myShooterWheelOne = new ShooterWheel(DIO_TWO, DIO_THREE, PWM_SLOT_SIX, 0.3, 0, 0);
        myShooterWheelOne = new ShooterWheel(DIO_FOUR, DIO_FIVE, PWM_SLOT_SEVEN, 0.3, 0, 0);
        myAutoShooter = new AutoShooter();
        myShooterLoader = new ShooterLoader(RELAY_ONE, DIO_ONE);
    }
    
    public void turnOnShooterWheels() {
        myShooterWheelOne.setRate(3000);
        myShooterWheelTwo.setRate(3000);
    }
}
