/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The main control class for our robot.
 * @author 3414
 */
public class RobotControl extends BaseRobot implements IRobot {
    
    boolean onTargetX;
    boolean onTargetY;
    boolean liftIsUp;
    boolean liftControl;
    double driveScaling;

    /**
     * Main constructor for RobotControl.
     */
    public RobotControl() {
        super();
        onTargetX = false;
        onTargetY = false;
        liftIsUp = false;
        liftControl = false;
        driveScaling = 1.0;
    }
    
    /**
     * Puts all information we need to see in the Driver Station on the SmartDashboard.
     */
    private void updateDashboard() {
        SmartDashboard.putBoolean("Loader Sensor", myShooterLoader.getFrisbeeSensor());
        SmartDashboard.putNumber("Shooter Wheel One Rate", myShooterWheelOne.getRate());
        SmartDashboard.putNumber("Shooter Wheel Two Rate", myShooterWheelTwo.getRate());
        SmartDashboard.putNumber("Screw Encoder", myShooterScrew.getAngle());
        SmartDashboard.putBoolean("Screw Limit High", myShooterScrew.getSensorHighValue());
        SmartDashboard.putBoolean("Screw Limit Low", myShooterScrew.getSensorLowValue());
        SmartDashboard.putNumber("Potentiometer", myPotentiometer.getVoltage());
        SmartDashboard.putNumber("Average Pot Values", myPotentiometer.getAverageVoltage());
    }
    
    /**
     * Updates systems based on the camera rectangle target.
     */
    public void autoAim() {
        onTargetX = false;
        onTargetY = false;
        ParticleAnalysisReport target = myCamera.findParticles();
            
        if (myAutoShooter.aimX(target) == 1) {
            myDrive.setSpeed(-0.1, 0.1);
        } else if (myAutoShooter.aimX(target) == -1) {
            myDrive.setSpeed(0.1, -0.1);
        } else {
            onTargetX = true;
            myDrive.setSpeed(0.0, 0.0);
        }
        
        if (myAutoShooter.aimY(target) == 1) {
            myShooterScrew.setMovement(false, true);
        } else if (myAutoShooter.aimY(target) == -1) {
            myShooterScrew.setMovement(true, false);
        } else {
            onTargetY = true;
            myShooterScrew.setMovement(false, false);
        }
    }
    
    /**
     * Redirect method for autonomous control.
     */
    public void autonomous() {
        myShooterWheelOne.setTrueSpeed(1.0);
        myShooterWheelTwo.setTrueSpeed(1.0);
        Timer.delay(3);
        int i = 1;
        while(i<=3) {
            myShooterPiston.set(true);
            Timer.delay(1.0);
            myShooterPiston.set(false);
            Timer.delay(0.5);
            myShooterLoader.turnOn();
            Timer.delay(0.8);
            myShooterLoader.turnOff();
            i++;
        }
    }
    
    public void resetSystems() {
        myShooterPiston.reset();
        myShooterLoader.reset();
    }
    
    /**
     * Updates important systems, called every 20 milliseconds in Main.java.
     */
    public void twentyMSLoop() {
        //This locks us out of control if autoAim is active
        if (!gamepad.getRawButton(BUTTON_NINE)) {
            
            //Drive speed scaling
            if (leftStick.getRawButton(BUTTON_TWO)) {
                driveScaling = 0.5;
            } else {
                driveScaling = 1.0;
            }
            
            //Drive Train
            if (rightStick.getRawButton(TRIGGER)) {
                myDrive.setSpeed(driveScaling * rightStick.getRawAxis(VERTICAL_AXIS));
            } else {
                myDrive.setSpeed(driveScaling * leftStick.getRawAxis(VERTICAL_AXIS), driveScaling * rightStick.getRawAxis(VERTICAL_AXIS));
            }
            
            //Screw
            myShooterScrew.setMovement(gamepad.getRawButton(BUTTON_FOUR), gamepad.getRawButton(BUTTON_TWO));
            
            //Piston
            if (gamepad.getRawButton(BUTTON_EIGHT)) {
                myShooterPiston.set(true);      //DEBUG: revert to setWithMinTime(); once that is fixed
            } else {
                myShooterPiston.set(false);
            }
            
            //Automatic loader update
            myShooterLoader.updateLoader(myShooterPiston.get(), gamepad.getRawButton(BUTTON_SIX));
            
            //Shooter Wheel control
//            if (gamepad.getRawButton(BUTTON_SEVEN) && !gamepad.getRawButton(BUTTON_FIVE) && !gamepad.getRawButton(BUTTON_THREE)) {
//                SmartDashboard.putBoolean("Shooter Has no target", false);
//                myShooterWheelOne.updateSpeed(-0.017);
//                myShooterWheelTwo.updateSpeed(-0.011);
//            } else if (!gamepad.getRawButton(BUTTON_SEVEN) && gamepad.getRawButton(BUTTON_FIVE) && !gamepad.getRawButton(BUTTON_THREE)) {
//                SmartDashboard.putBoolean("Shooter Has no target", false);
//                myShooterWheelOne.updateSpeed(-0.006);
//                myShooterWheelTwo.updateSpeed(-0.004);
//            } else if (!gamepad.getRawButton(BUTTON_SEVEN) && !gamepad.getRawButton(BUTTON_FIVE) && gamepad.getRawButton(BUTTON_THREE)) {
//                SmartDashboard.putBoolean("Shooter Has no target", false);
//                myShooterWheelOne.setTrueSpeed(-1.0);
//                myShooterWheelTwo.setTrueSpeed(1.0);
//            } else {
//                SmartDashboard.putBoolean("Shooter Has no target", true);
//                myShooterWheelOne.updateSpeed(0.0);
//                myShooterWheelTwo.updateSpeed(0.0);
//            }
            
            if (gamepad.getRawButton(BUTTON_SEVEN) && !gamepad.getRawButton(BUTTON_FIVE) && !gamepad.getRawButton(BUTTON_THREE)) {
                myShooterWheelOne.setTrueSpeed(-1.0);
                myShooterWheelTwo.setTrueSpeed(1.0);
            } else if (!gamepad.getRawButton(BUTTON_SEVEN) && gamepad.getRawButton(BUTTON_FIVE) && !gamepad.getRawButton(BUTTON_THREE)) {
                myShooterWheelOne.setTrueSpeed(-0.75);
                myShooterWheelTwo.setTrueSpeed(0.75);
            } else if (!gamepad.getRawButton(BUTTON_SEVEN) && !gamepad.getRawButton(BUTTON_FIVE) && gamepad.getRawButton(BUTTON_THREE)) {
                myShooterWheelOne.setTrueSpeed(-0.5);
                myShooterWheelTwo.setTrueSpeed(0.5);
            } else {
                myShooterWheelOne.setTrueSpeed(0.0);
                myShooterWheelTwo.setTrueSpeed(0.0);
            }
            
            //Pyramid lifter logic
            if (rightStick.getRawButton(BUTTON_EIGHT) && liftControl) {
                liftIsUp = !liftIsUp;
                liftControl = false;
            } else if (!rightStick.getRawButton(BUTTON_EIGHT)) {
                liftControl = true;
            }
            if (liftIsUp) {
                myPyramidLifter.goDown();
            } else {
                myPyramidLifter.goUp();
            }
            
            //DEBUG: Potentiometer testing
            
            myPotentiometer.updateAverageValues(POT_SAMPLING_RATE);
            if (myPotentiometer.getAverageVoltage() > 4.030 || myPotentiometer.getAverageVoltage() < 4.000) {
                SmartDashboard.putBoolean("Pot On Target", false);
            } else {
                SmartDashboard.putBoolean("Pot On Target", true);
            }
            
            this.updateDashboard();
        }
    }
    
    /**
     * Called every 100 milliseconds in Main.java.
     */
    public void hundredMSLoop() {
        //AutoAim logic goes here because it involves camera logic.
        if (gamepad.getRawButton(BUTTON_NINE) && CAMERA_ENABLED) {
            autoAim();
            if (onTargetX && onTargetY) {
                SmartDashboard.putBoolean("Ready for shooting!?", true);
            } else {
                SmartDashboard.putBoolean("Ready for shooting!?", false);
            }
        }
    }
    
    /**
     * Called every second in Main.java.
     */
    public void thousandMSLoop() {
    }
}
