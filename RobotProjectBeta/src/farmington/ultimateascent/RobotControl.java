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
    

    public RobotControl() {
        super();
    }
    
    private void updateDashboard() {
        SmartDashboard.putNumber("Left Front Motor", myDrive.getLeftFrontMotor());
        SmartDashboard.putNumber("Left Back Motor", myDrive.getLeftBackMotor());
        SmartDashboard.putNumber("Right Front Motor", myDrive.getRightFrontMotor());
        SmartDashboard.putNumber("Right Back Motor", myDrive.getRightBackMotor());
        SmartDashboard.putNumber("Screw Encoder", myShooterScrew.getEncoderValue());
        SmartDashboard.putBoolean("Screw Sensor Sensor Low", myShooterScrew.getSensorLowValue());
        SmartDashboard.putBoolean("Screw Sensor Sensor High", myShooterScrew.getSensorHighValue());
        SmartDashboard.putBoolean("Loader Sensor", myShooterLoader.getFrisbeeSensor());
        SmartDashboard.putBoolean("Shooter Piston", myShooterPiston.getPosition());
        SmartDashboard.putNumber("Current Loop Iteration", LoopHandler.getCurrentIteration());
    }
    
    public void autoAim() {
        
    }
    
    public void twentyMSLoop() {
        //This locks us out of control if autoAim is active
        if (!leftStick.getRawButton(LEFT_BUTTON_EIGHT)) {
            myDrive.setSpeed(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
            myShooterScrew.setMovement(leftStick.getRawButton(LEFT_BUTTON_THREE), leftStick.getRawButton(LEFT_BUTTON_TWO));
            myShooterPiston.setPosition(leftStick.getRawButton(LEFT_TRIGGER));
            myShooterLoader.updateLoader(myShooterPiston.getPosition());
        
            updateDashboard();
        }
    }
    
    public void hundredMSLoop() {
        boolean onTargetX = false;
        boolean onTargetY = false;
        
        if (leftStick.getRawButton(LEFT_BUTTON_EIGHT) && CAMERA_ENABLED) {
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
                myShooterScrew.setSpeed(-0.1);
            } else if (myAutoShooter.aimY(target) == -1) {
                myShooterScrew.setSpeed(0.1);
            } else {
                onTargetY = true;
                myShooterScrew.setSpeed(0.0);
            }
            
            if (onTargetX && onTargetY) {
                //doStuff
            }
        }
    }
    
    public void thousandMSLoop() {
        myCamera.findParticles();
    }
}
