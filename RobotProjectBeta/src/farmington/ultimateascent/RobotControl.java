/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import farmington.frameworks.LoopHandler;

/**
 *
 * @author Robotics
 */
public class RobotControl extends BaseRobot implements IRobot {
    
    boolean onTargetX;
    boolean onTargetY;

    public RobotControl() {
        super();
        onTargetX = false;
        onTargetY = false;
    }
    
    private void updateDashboard() {
        SmartDashboard.putNumber("Left Front Motor", myDrive.getLeftFrontMotor());
        SmartDashboard.putNumber("Left Back Motor", myDrive.getLeftBackMotor());
        SmartDashboard.putNumber("Right Front Motor", myDrive.getRightFrontMotor());
        SmartDashboard.putNumber("Right Back Motor", myDrive.getRightBackMotor());
        SmartDashboard.putBoolean("Screw Sensor Sensor Low", myShooterScrew.getSensorLowValue());
        SmartDashboard.putBoolean("Screw Sensor Sensor High", myShooterScrew.getSensorHighValue());
        SmartDashboard.putBoolean("Loader Sensor", myShooterLoader.getFrisbeeSensor());
        SmartDashboard.putBoolean("Shooter Piston", myShooterPiston.getPosition());
        SmartDashboard.putNumber("Current Loop Iteration", LoopHandler.getCurrentIteration());
    }
    
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
    
    public void autonomous() {
        myAutonomous.mainControl();
    }
    
    public void twentyMSLoop() {
        //This locks us out of control if autoAim is active
        if (!leftStick.getRawButton(LEFT_BUTTON_EIGHT)) {
            
            myDrive.setSpeed(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
            myShooterScrew.setMovement(leftStick.getRawButton(LEFT_BUTTON_THREE), leftStick.getRawButton(LEFT_BUTTON_TWO));
            myShooterPiston.setPosition(leftStick.getRawButton(LEFT_TRIGGER));
            myShooterLoader.updateLoader(myShooterPiston.getPosition());
            myPyramidLifter.update(rightStick.getRawButton(RIGHT_BUTTON_TWO));
            if (rightStick.getRawButton(RIGHT_TRIGGER) && !rightStick.getRawButton(RIGHT_BUTTON_FIVE)) {
                myShooterWheelOne.setRate(-3000);
                myShooterWheelTwo.setRate(3000);
            } else if (!rightStick.getRawButton(RIGHT_TRIGGER) && rightStick.getRawButton(RIGHT_BUTTON_FIVE)) {
                myShooterWheelOne.turnOnMaxSpeed(true);
                myShooterWheelTwo.turnOnMaxSpeed(true);
            } else {
                myShooterWheelOne.setRate(0);
                myShooterWheelTwo.setRate(0);
            }
            
            updateDashboard();
        }
    }
    
    public void hundredMSLoop() {
        if (leftStick.getRawButton(LEFT_BUTTON_EIGHT) && CAMERA_ENABLED) {
            autoAim();
            if (onTargetX && onTargetY) {
                SmartDashboard.putBoolean("Ready for shooting!?", true);
            } else {
                SmartDashboard.putBoolean("Ready for shooting!?", false);
            }
        }
    }
    
    public void thousandMSLoop() {
        myCamera.findParticles();
    }
}
