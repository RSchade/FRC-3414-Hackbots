package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author RSchade and CRiehl
 */


public class Camera implements IRobot {
    
    AxisCamera myCamera = AxisCamera.getInstance();
    CriteriaCollection crit = new CriteriaCollection();
    private ColorImage image;
    private BinaryImage dataImage;
    private boolean loopControl = true;

    public Camera() {
        crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 400, false);
        crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 400, false);
    }
    
    public void update(boolean trigger) {
        if (trigger && loopControl) {
//            myCamera.centerCalculate();
            loopControl = false;
        }
        if (!trigger && !loopControl) {
            loopControl = true;
        }
    }
    
    public void centerCalculate() {
        ParticleAnalysisReport[] particle;
        int trgCnt;
        
        try {
            image = myCamera.getImage();
            dataImage = image.thresholdRGB(RED_MIN, RED_MAX, GRN_MIN, GRN_MAX, BLU_MIN, BLU_MAX);
            dataImage = dataImage.removeSmallObjects(false, 2);
            dataImage = dataImage.convexHull(true);
            dataImage = dataImage.particleFilter(crit);
            ParticleAnalysisReport[] reports = dataImage.getOrderedParticleAnalysisReports();
            for(int i=0; i < reports.length +1; i++) {
                System.out.println("Rectangle found! Output: " + reports[i].center_mass_x);
                SmartDashboard.putNumber("Camera Output", reports[i].center_mass_x);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            image.free();
            dataImage.free();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
//    public void getCamera() {
//        AxisCamera.getInstance().writeCompression(0);
//        AxisCamera.getInstance().writeResolution(AxisCamera.ResolutionT.k320x240);
//        AxisCamera.getInstance().writeBrightness(10);
//        DriverStationLCD.getInstance().updateLCD();
//    }
}
