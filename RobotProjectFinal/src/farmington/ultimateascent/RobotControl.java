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
    double targetVoltage;
    double manualTargetVoltage;
    double offset;

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
        manualTargetVoltage = 0.0;
        offset = 0.0;
    }
    
    /**
     * Puts all information we need to see in the Driver Station on the SmartDashboard.
     */
    private void updateDashboard() {
        SmartDashboard.putNumber("Shooter Wheel One RPM", myShooterWheelOne.getRate());
        SmartDashboard.putNumber("Shooter Wheel Two RPM", myShooterWheelTwo.getRate());
        SmartDashboard.putNumber("Potentiometer", myShooterScrew.getVoltage());
        SmartDashboard.putNumber("AveragePotentiometer", myShooterScrew.getAverageVoltage());
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
//        double automode = (rightStick.getRawAxis(SWITCH_AXIS)>0 ? 1 : 0);
//        if (automode == 0) {
//            targetVoltage = 3.5; //Insert angle A here
//        } else if (automode == 1) {
//            targetVoltage = 2.005; //Insert angle B here
//        } else {
//            targetVoltage = 0.0;
//        }
        targetVoltage = 2.005;
        myShooterWheelOne.setTrueSpeed(-1.0);
        myShooterWheelTwo.setTrueSpeed(1.0);
        while(true) {
            int position = myShooterScrew.isOnTarget(targetVoltage, 0.025);
            SmartDashboard.putNumber("DEBUG autonomous position check:", position);
            SmartDashboard.putNumber("Potentiometer", myShooterScrew.getVoltage());
            SmartDashboard.putNumber("AveragePotentiometer", myShooterScrew.getAverageVoltage());
            if (position == -1) {
                myShooterScrew.setMovement(true, false);
            } else if (position == 1) {
                myShooterScrew.setMovement(false, true);
            } else {
                myShooterScrew.setMovement(false, false);
                break;
            }
            Timer.delay(0.020);
        }
        int i = 1;
        Timer.delay(0.100);
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
        //Drive speed scaling
        if (rightStick.getRawButton(TRIGGER)) {
            driveScaling = 1.0;
        } else {
            driveScaling = 0.5;
        }
        
        //Drive Train
        if (rightStick.getRawButton(BUTTON_TEN)) {
            myDrive.setSpeed(driveScaling * rightStick.getRawAxis(VERTICAL_AXIS));
        } else {
            myDrive.setSpeed(driveScaling * leftStick.getRawAxis(VERTICAL_AXIS), driveScaling * rightStick.getRawAxis(VERTICAL_AXIS));
        }
        
        //Screw with manual positioning
        if (!(gamepad.getRawButton(BUTTON_FOUR) || gamepad.getRawButton(BUTTON_TWO))) {
            if (gamepad.getRawButton(BUTTON_THREE)) {
                manualTargetVoltage = 3.450;
                offset = 0.025;
            } else if (gamepad.getRawButton(BUTTON_ONE)) {
                manualTargetVoltage = 1.850;
                offset = 0.1;
            }
            if (manualTargetVoltage != 0.0) {
                int position = myShooterScrew.isOnTarget(manualTargetVoltage, offset);
                if (position == -1) {
                    myShooterScrew.setMovement(true, false);
                } else if (position == 1) {
                    myShooterScrew.setMovement(false, true);
                } else {
                    myShooterScrew.setMovement(false, false);
                }
            } else {
                myShooterScrew.setMovement(false, false);
            }
        } else {
            manualTargetVoltage = 0.0;
            myShooterScrew.setMovement(gamepad.getRawButton(BUTTON_FOUR), gamepad.getRawButton(BUTTON_TWO));
        }
        
        //Piston
        if (gamepad.getRawButton(BUTTON_EIGHT)) {
            myShooterPiston.set(true);      //DEBUG: revert to setWithMinTime(); once that is fixed
        } else {
            myShooterPiston.set(false);
        }
        
        //Automatic loader update
        myShooterLoader.updateLoader(gamepad.getRawButton(BUTTON_SIX));
        
        //Shooter Wheel Speed
//        if (gamepad.getRawButton(BUTTON_SEVEN) && !gamepad.getRawButton(BUTTON_FIVE) && !gamepad.getRawButton(BUTTON_THREE)) {
//            SmartDashboard.putBoolean("Shooter Has no target", false);
//            myShooterWheelOne.updateSpeed(-0.017);
//            myShooterWheelTwo.updateSpeed(-0.011);
//        } else if (!gamepad.getRawButton(BUTTON_SEVEN) && gamepad.getRawButton(BUTTON_FIVE) && !gamepad.getRawButton(BUTTON_THREE)) {
//            SmartDashboard.putBoolean("Shooter Has no target", false);
//            myShooterWheelOne.updateSpeed(-0.006);
//            myShooterWheelTwo.updateSpeed(-0.004);
//        } else if (!gamepad.getRawButton(BUTTON_SEVEN) && !gamepad.getRawButton(BUTTON_FIVE) && gamepad.getRawButton(BUTTON_THREE)) {
//            SmartDashboard.putBoolean("Shooter Has no target", false);
//            myShooterWheelOne.setTrueSpeed(-1.0);
//            myShooterWheelTwo.setTrueSpeed(1.0);
//        } else {
//            SmartDashboard.putBoolean("Shooter Has no target", true);
//            myShooterWheelOne.updateSpeed(0.0);
//            myShooterWheelTwo.updateSpeed(0.0);
//        }
        
        //Shooter Wheel Speed
        if (gamepad.getRawButton(BUTTON_SEVEN) && !gamepad.getRawButton(BUTTON_FIVE)) {
            myShooterWheelOne.setTrueSpeed(-1.0);
            myShooterWheelTwo.setTrueSpeed(1.0);
        } else if (!gamepad.getRawButton(BUTTON_SEVEN) && gamepad.getRawButton(BUTTON_FIVE)) {
            myShooterWheelOne.setTrueSpeed(-0.75);
            myShooterWheelTwo.setTrueSpeed(0.75);
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
        
        this.updateDashboard();
    }
    
    /**
     * Called every 100 milliseconds in Main.java.
     */
    public void hundredMSLoop() {
        //AutoAim logic goes here because it involves camera logic.
//        if (gamepad.getRawButton(BUTTON_NINE) && CAMERA_ENABLED) {
//            autoAim();
//            if (onTargetX && onTargetY) {
//                SmartDashboard.putBoolean("Ready for shooting!?", true);
//            } else {
//                SmartDashboard.putBoolean("Ready for shooting!?", false);
//            }
//        }
    }
    
    /**
     * Called every second in Main.java.
     */
    public void thousandMSLoop() {
    }
}
