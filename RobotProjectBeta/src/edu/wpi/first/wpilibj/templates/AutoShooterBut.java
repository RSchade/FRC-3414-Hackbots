/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;


/**
 *
 * @author Robotics
 */
public class AutoShooterBut extends BaseRobot implements IRobot {

    ParticleAnalysisReport target;
    boolean onTargetX;
    boolean onTargetY;
    boolean hasTarget;
    Waiter wheelControl;
    Waiter pistonControl;
    
    public AutoShooterBut() {
        onTargetX = false;
        onTargetY = false;
        hasTarget = false;
        wheelControl = new Waiter();
        pistonControl = new Waiter();
    }
    
    public void aim() {
        target = myCamera.findParticles();
        
        if (target.center_mass_x_normalized >= 0.95) {
            onTargetX = false;
            myDrive.setSpeed(-0.1, 0.1);
        } else if (target.center_mass_x_normalized <= 0.95) {
            onTargetX = false;
            myDrive.setSpeed(0.1, -0.1);
        } else {
            onTargetX = true;
        }
        
        if (target.center_mass_y_normalized >= 0.95) {
            onTargetY = false;
            myShooterScrew.setSpeed(-0.1);
        } else if (target.center_mass_y_normalized <= 0.95) {
            onTargetY = false;
            myShooterScrew.setSpeed(0.1);
        } else {
            onTargetY = true;
        }
        
        if (onTargetX && onTargetY && !hasTarget) {
            hasTarget = true;
            turnOnShooterWheels();
            wheelControl.waitXLoops(100);
        }
        
        if (hasTarget) {
            turnOnShooterWheels();
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
