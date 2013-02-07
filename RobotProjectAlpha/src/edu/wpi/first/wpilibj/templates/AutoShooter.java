/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Robotics
 */
public class AutoShooter {
    
    int targetLoopControl = 0;
    
    public void shoot() {
        if (targetLoopControl==0) {
            target = myCamera.centerCalculate();
        }
        if (target != null) {
            findVisionTarget(target);
        }
        targetLoopControl++;
        if (targetLoopControl==20) {
            targetLoopControl = 0;
        }
                
        boolean areWeReady = SmartDashboard.getBoolean("Ready for shooting?");
        if (areWeReady) {
            //SHOOT A FRISBEE
        }
}
