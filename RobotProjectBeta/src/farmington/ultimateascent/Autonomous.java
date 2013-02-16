
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import farmington.frameworks.Camera;
import farmington.frameworks.DriveTrain;
import farmington.frameworks.ShooterPiston;
import farmington.frameworks.ShooterScrew;
import farmington.frameworks.ShooterWheel;
import farmington.frameworks.Waiter;


/**
 * Takes control of the bot and automagically aims at a target.
 * 
 * @author Cooper Riehl
 * @version 1.0
 */
public class Autonomous implements IRobot {

    ParticleAnalysisReport target;
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
    
    
    public Autonomous(Camera setCamera, DriveTrain setDrive, ShooterScrew setScrew, ShooterPiston setPiston, ShooterWheel setWheelOne, ShooterWheel setWheelTwo) {
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
    }
    
    public void mainControl() {
        
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
    }
    
    public void spinUp() {
        autoShooterWheelOne.turnOn(true);
        autoShooterWheelTwo.turnOn(true);
        if (autoShooterWheelOne.isOnTarget() && autoShooterWheelTwo.isOnTarget()) {
            isSpun = true;
        }
    }
    
    public void shoot() {
        if (!timing.isWaiting() && !pistonControl.timeUp()) {
            autoShooterPiston.setPosition(true);
            timing.waitXLoops(50);
        }
        
        if (timing.timeUp()) {
            autoShooterPiston.setPosition(false);
            pistonControl.waitXLoops(50);
        }
    }
    
    public boolean load() {
        
    }
    
//    public void shoot() {
//        autoShooterWheelOne.turnOn(true);
//        autoShooterWheelTwo.turnOn(true);
//        
//        //Gives the wheels 2 seconds to spin up
//        if (!wheelControl.isWaiting()) {
//            wheelControl.waitXLoops(100);
//        }
//        
//        //Extends the piston for 1 second
//        if (wheelControl.timeUp()) {
//            autoShooterPiston.setPosition(true);
//            pistonControl.waitXLoops(50);
//        }
//        
//        //Retracts the piston and waits 1 second to fire again
//        if (pistonControl.timeUp()) {
//            autoShooterPiston.setPosition(false);
//        }
//    }
}
