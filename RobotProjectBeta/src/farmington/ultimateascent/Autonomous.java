
package farmington.ultimateascent;

import farmington.frameworks.AutoShooter;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import farmington.frameworks.Camera;
import farmington.frameworks.DriveTrain;
import farmington.frameworks.ShooterPiston;
import farmington.frameworks.ShooterScrew;
import farmington.frameworks.ShooterWheel;
import farmington.frameworks.Waiter;


/**
 * Handles all autonomous control systems.
 * @author Cooper Riehl
 */
public class Autonomous implements IRobot {

    boolean onTargetX;
    boolean onTargetY;
    boolean control;
    boolean isSpun;
    Waiter timing;
    Waiter pistonControl;
    Camera autoCamera;
    DriveTrain autoDrive;
    ShooterScrew autoShooterScrew;
    ShooterPiston autoShooterPiston;
    ShooterWheel autoShooterWheelOne;
    ShooterWheel autoShooterWheelTwo;
    AutoShooter autoAutoShooter;
    
    /**
     * Main constructor for Autonomous.
     * @param setCamera     pre-initialized camera
     * @param setDrive      pre-initialized drive train
     * @param setScrew      pre-initialized shooter screw
     * @param setPiston     pre-initialized shooter piston
     * @param setWheelOne   pre-initialized first shooter wheel
     * @param setWheelTwo   pre-initialized second shooter wheel
     * @param setAutoShooter pre-initialized AutoShooter
     */
    public Autonomous(Camera setCamera, DriveTrain setDrive, ShooterScrew setScrew, ShooterPiston setPiston, ShooterWheel setWheelOne, ShooterWheel setWheelTwo, AutoShooter setAutoShooter) {
        onTargetX = false;
        onTargetY = false;
        control = false;
        isSpun = false;
        timing = new Waiter();
        pistonControl = new Waiter();
        autoCamera = setCamera;
        autoDrive = setDrive;
        autoShooterScrew = setScrew;
        autoShooterPiston = setPiston;
        autoShooterWheelOne = setWheelOne;
        autoShooterWheelTwo = setWheelTwo;
        autoAutoShooter = setAutoShooter;
    }
    
    /**
     * Main control loop, called in Main.java.
     */
    public void mainControl() {
        autoAim();
        if (onTargetX && onTargetY) {
            //doStuff
        }
    }
    
    /**
     * Auto aiming system.
     */
    public void autoAim() {
        onTargetX = false;
        onTargetY = false;
        ParticleAnalysisReport target = autoCamera.findParticles();
            
        if (autoAutoShooter.aimX(target) == 1) {
            autoDrive.setSpeed(-0.1, 0.1);
        } else if (autoAutoShooter.aimX(target) == -1) {
            autoDrive.setSpeed(0.1, -0.1);
        } else {
            onTargetX = true;
            autoDrive.setSpeed(0.0, 0.0);
        }
        
        if (autoAutoShooter.aimY(target) == 1) {
            autoShooterScrew.setSpeed(-0.1);
        } else if (autoAutoShooter.aimY(target) == -1) {
            autoShooterScrew.setSpeed(0.1);
        } else {
            onTargetY = true;
            autoShooterScrew.setSpeed(0.0);
        }
    }
}
