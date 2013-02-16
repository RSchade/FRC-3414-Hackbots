
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import farmington.frameworks.Waiter;


/**
 * Takes control of the bot and automagically aims at a target.
 * 
 * @author Cooper Riehl
 * @version 1.0
 */
public class AutoShooter extends BaseRobot implements IRobot {

    ParticleAnalysisReport target;
    boolean onTargetX;
    boolean onTargetY;
    boolean hasTarget;
    Waiter wheelControl;
    Waiter pistonControl;
    
    public AutoShooter() {
        super();
        onTargetX = false;
        onTargetY = false;
        hasTarget = false;
        wheelControl = new Waiter();
        pistonControl = new Waiter();
    }
    
    protected void aim() {
        target = myCamera.findParticles();
        
        if (target.center_mass_x_normalized > 0.05) {
            onTargetX = false;
            myDrive.setSpeed(-0.1, 0.1);
        } else if (target.center_mass_x_normalized < -0.05) {
            onTargetX = false;
            myDrive.setSpeed(0.1, -0.1);
        } else {
            onTargetX = true;
        }
        
        if (target.center_mass_y_normalized > 0.05) {
            onTargetY = false;
            myShooterScrew.setSpeed(-0.1);
        } else if (target.center_mass_y_normalized < -0.05) {
            onTargetY = false;
            myShooterScrew.setSpeed(0.1);
        } else {
            onTargetY = true;
        }
        
        if (onTargetX && onTargetY && !hasTarget) {
            hasTarget = true;
            super.turnOnShooterWheels(true);
            wheelControl.waitXLoops(100);
        }
        
        if (hasTarget) {
            super.turnOnShooterWheels(true);
            if (wheelControl.timeUp()) {
                myShooterPiston.setPosition(true);
                pistonControl.waitXLoops(50);
            }
            if (pistonControl.timeUp()) {
                myShooterPiston.setPosition(false);
                hasTarget = false;
            }
        }
    }
}
