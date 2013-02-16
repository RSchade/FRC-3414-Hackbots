
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
 * 
 * @author Cooper Riehl
 * @version 1.0
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
    
    
    public Autonomous(Camera setCamera, DriveTrain setDrive, ShooterScrew setScrew, ShooterPiston setPiston, ShooterWheel setWheelOne, ShooterWheel setWheelTwo, AutoShooter setAutoShooter) {
        super();
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
    
    public void mainControl() {
        autoAim();
        if (onTargetX && onTargetY) {
            //doStuff
        }
    }
    
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
