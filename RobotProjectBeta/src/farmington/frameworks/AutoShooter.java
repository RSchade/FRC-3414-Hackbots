
package farmington.frameworks;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import farmington.ultimateascent.IRobot;


/**
 * Takes control of the bot and automagically aims at a target.
 * 
 * @author Cooper Riehl
 * @version 1.0
 */
public class AutoShooter implements IRobot {

    ParticleAnalysisReport target;
    boolean onTargetX;
    boolean onTargetY;
    boolean hasTarget;
    Waiter wheelControl;
    Waiter pistonControl;
    Camera autoCamera;
    DriveTrain autoDrive;
    ShooterScrew autoShooterScrew;
    ShooterPiston autoShooterPiston;
    ShooterWheel autoShooterWheelOne;
    ShooterWheel autoShooterWheelTwo;
    
    
    public AutoShooter(Camera setCamera, DriveTrain setDrive, ShooterScrew setScrew, ShooterPiston setPiston, ShooterWheel setWheelOne, ShooterWheel setWheelTwo) {
        super();
        onTargetX = false;
        onTargetY = false;
        hasTarget = false;
        wheelControl = new Waiter();
        pistonControl = new Waiter();
        autoCamera = setCamera;
        autoDrive = setDrive;
        autoShooterScrew = setScrew;
        autoShooterPiston = setPiston;
        autoShooterWheelOne = setWheelOne;
        autoShooterWheelTwo = setWheelTwo;
    }
    
    public void aim() {
        target = autoCamera.findParticles();
        
        if (target.center_mass_x_normalized > 0.05) {
            onTargetX = false;
            autoDrive.setSpeed(-0.1, 0.1);
        } else if (target.center_mass_x_normalized < -0.05) {
            onTargetX = false;
            autoDrive.setSpeed(0.1, -0.1);
        } else {
            onTargetX = true;
        }
        
        if (target.center_mass_y_normalized > 0.05) {
            onTargetY = false;
            autoShooterScrew.setSpeed(-0.1);
        } else if (target.center_mass_y_normalized < -0.05) {
            onTargetY = false;
            autoShooterScrew.setSpeed(0.1);
        } else {
            onTargetY = true;
        }
        
        if (onTargetX && onTargetY && !hasTarget) {
            hasTarget = true;
            autoShooterWheelOne.turnOn(true);
            autoShooterWheelTwo.turnOn(true);
            wheelControl.waitXLoops(100);
        }
        
        if (hasTarget) {
            autoShooterWheelOne.turnOn(true);
            autoShooterWheelTwo.turnOn(true);
            if (wheelControl.timeUp()) {
                autoShooterPiston.setPosition(true);
                pistonControl.waitXLoops(50);
            }
            if (pistonControl.timeUp()) {
                autoShooterPiston.setPosition(false);
                hasTarget = false;
            }
        }
    }
}
