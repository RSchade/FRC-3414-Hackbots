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
    protected ShooterLoader myShooterLoader;
    protected PyramidLifter myPyramidLifter;
    protected AutoShooter myAutoShooter;
    
    public BaseRobot() {
        myCamera = new Camera();
        leftStick = new Joystick(USB_ONE);
        rightStick = new Joystick(USB_TWO);
        myDrive = new DriveTrain(PWM_SLOT_ONE, PWM_SLOT_THREE, PWM_SLOT_TWO, PWM_SLOT_FOUR);
        myShooterScrew = new ShooterScrew(PWM_SLOT_FIVE, DIO_SIX, DIO_SEVEN, DIO_EIGHT, DIO_NINE);
        myShooterPiston = new ShooterPiston(SOLENOID_ONE);
        myShooterWheelOne = new ShooterWheel(DIO_TWO, DIO_THREE, PWM_SLOT_SIX, 0.3, 0, 0);
        myShooterWheelTwo = new ShooterWheel(DIO_FOUR, DIO_FIVE, PWM_SLOT_SEVEN, 0.3, 0, 0);
        myShooterLoader = new ShooterLoader(RELAY_ONE, DIO_ONE);
        myPyramidLifter = new PyramidLifter(SOLENOID_TWO, SOLENOID_THREE, SOLENOID_FOUR, SOLENOID_FIVE);
        myAutoShooter = new AutoShooter(myCamera, myDrive, myShooterScrew, myShooterPiston, myShooterWheelOne, myShooterWheelTwo);
    }
    
    public void free() {
        myCamera = null;
        leftStick = null;
        rightStick = null;
        myDrive = null;
        myShooterScrew = null;
        myShooterPiston = null;
        myShooterWheelOne = null;
        myShooterWheelTwo = null;
        myShooterLoader = null;
    }
}
