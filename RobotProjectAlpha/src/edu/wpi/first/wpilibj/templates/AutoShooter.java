//package edu.wpi.first.wpilibj.templates;
//
//import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
///**
// *
// * @author Robotics
// */
//
//// This program should really return a bunch of different values which are then used in motor controllers elsewhere
//
//public class AutoShooter extends BaseRobot {
//    
//    Camera autoShooterCamera = new Camera();
//    DriveTrain autoShooterDriveTrain = new DriveTrain();
//    int targetLoopControl = 0;
//    ParticleAnalysisReport target = null;
//    
//    public void shoot() {
//        if (targetLoopControl==0) {
//            target = autoShooterCamera.findParticles();
//        }
//        if (target != null) {
//            findVisionTarget(target);
//        }
//        targetLoopControl++;
//        if (targetLoopControl==20) {
//            targetLoopControl = 0;
//        }
//                
//        boolean areWeReady = SmartDashboard.getBoolean("Ready for shooting?");
//        if (areWeReady) {
//            //SHOOT A FRISBEE
//        }
//    }
//
//    private void findVisionTarget(ParticleAnalysisReport target) {
//        boolean isOnTargetX = false;
//        boolean isOnTargetY = false;
//        
//        if (target.center_mass_x_normalized < -0.9) {
//            autoShooterDriveTrain.setSpeed(0.25, -0.25);
//        } else if (target.center_mass_x_normalized > 0.9) {
//            autoShooterDriveTrain.setSpeed(-0.25, 0.25);
//        } else {
//            isOnTargetX = true;
//        }
//        
//        if (target.center_mass_y_normalized < -0.9) {
//            myScrew.updateAuto(0.25);
//        } else if (target.center_mass_y_normalized > 0.9) {
//            myScrew.updateAuto(-0.25);
//        } else {
//            isOnTargetY = true;
//        }
//        
//        if (isOnTargetX && isOnTargetY) {
//            SmartDashboard.putBoolean("Ready for shooting?", true);
//        } else {
//            SmartDashboard.putBoolean("Ready for shooting?", false);
//        }
//    }
//}
