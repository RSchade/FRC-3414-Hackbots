/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import farmington.frameworks.LoopHandler;

/**
 * The main control class for our robot.
 * @author 3414
 */
public class RobotControl extends BaseRobot implements IRobot {
    
    boolean onTargetX;
    boolean onTargetY;
    boolean liftIsUp;
    boolean liftControl;

    /**
     * Main constructor for RobotControl.
     */
    public RobotControl() {
        super();
        onTargetX = false;
        onTargetY = false;
        liftIsUp = false;
        liftControl = false;
    }
    
    /**
     * Puts all information we need to see in the Driver Station on the SmartDashboard.
     */
    private void updateDashboard() {
        SmartDashboard.putBoolean("Screw Sensor Sensor Low", myShooterScrew.getSensorLowValue());
        SmartDashboard.putBoolean("Screw Sensor Sensor High", myShooterScrew.getSensorHighValue());
        SmartDashboard.putBoolean("Loader Sensor", myShooterLoader.getFrisbeeSensor());
        SmartDashboard.putBoolean("Shooter Piston", myShooterPiston.get());
        SmartDashboard.putNumber("Current Loop Iteration", LoopHandler.getCurrentIteration());
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
        double time = 0.0;
        while (!onTargetX || !onTargetY){
           autoAim();
           Timer.delay(0.020);
           time += 0.020;
           if (time >= 5.0) {
               onTargetX = true;
               onTargetY = true;
           }
        }
        myShooterWheelOne.setRate(-3000);
        myShooterWheelTwo.setRate(3000);
        while(!myShooterWheelOne.isOnTarget() && !myShooterWheelTwo.isOnTarget())
        {
            
        }
        int shootingCount = 1;
        while (shootingCount < 3){
            myShooterPiston.set(false);
            Timer.delay(0.5);
            myShooterPiston.set(true);
            Timer.delay(0.5);
            myShooterLoader.turnOn();
            Timer.delay(1.0);
            myShooterLoader.turnOff();
            //Shoot 3 times and turns off 3 times//
            shootingCount++;
        }
    }
    
    /**
     * Updates important systems, called every 20 milliseconds in Main.java.
     */
    public void twentyMSLoop() {
        //This locks us out of control if autoAim is active
        if (!gamepad.getRawButton(BUTTON_NINE)) {
            
            myDrive.setSpeed(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
            myShooterScrew.setMovement(gamepad.getRawButton(BUTTON_FOUR), gamepad.getRawButton(BUTTON_TWO));
            myShooterPiston.set(!gamepad.getRawButton(BUTTON_EIGHT));
            myShooterLoader.updateLoader(myShooterPiston.get());
            
            //Shooter Wheel control
            if (gamepad.getRawButton(BUTTON_SEVEN)) {
                myShooterWheelOne.setRate(-3000);
                myShooterWheelTwo.setRate(3000);
            } else {
                myShooterWheelOne.setRate(0);
                myShooterWheelTwo.setRate(0);
            }
            
            //Pyramid lifter logic
            if (rightStick.getRawButton(BUTTON_EIGHT) && liftControl) {
                liftIsUp = !liftIsUp;
                liftControl = false;
            }
            if (!rightStick.getRawButton(BUTTON_EIGHT)) {
                liftControl = true;
            }
            if (liftIsUp) {
                myPyramidLifter.goUp();
            } else {
                myPyramidLifter.goDown();
            }
            
            updateDashboard();
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
        //Camera updating goes here so as not to lag the robot.
        myCamera.findParticles();
    }
}
