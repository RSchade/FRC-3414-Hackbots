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
    int redMin = 0, redMax = 45, grnMin = 25, grnMax = 255, bluMin = 0, bluMax = 47;

    public Camera() {
        crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 400, false);
        crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 400, false);
    }
    
    public void takePicture(boolean trigger) {
        if (trigger && loopControl) {
            centerCalculate();
            loopControl = false;
        }
        if (!trigger && !loopControl) {
            loopControl = true;
        }
    }
    
    public void setValues() {
        redMin = (int)SmartDashboard.getNumber("RedMinVal");
        redMax = (int)SmartDashboard.getNumber("RedMaxVal");
        grnMin = (int)SmartDashboard.getNumber("GrnMinVal");
        grnMax = (int)SmartDashboard.getNumber("GrnMaxVal");
        bluMin = (int)SmartDashboard.getNumber("BluMinVal");
        bluMax = (int)SmartDashboard.getNumber("BluMaxVal");
    }
    
    public ParticleAnalysisReport centerCalculate() {
        ParticleAnalysisReport[] particle;
        int trgCnt;
        
        try {
            image = myCamera.getImage();
            dataImage = image.thresholdRGB(redMin, redMax, grnMin, grnMax, bluMin, bluMax);
            dataImage = dataImage.removeSmallObjects(false, 2);
            dataImage = dataImage.convexHull(true);
            dataImage = dataImage.particleFilter(crit);
            ParticleAnalysisReport[] reports = dataImage.getOrderedParticleAnalysisReports();
            for(int i=0; i < reports.length; i++) {
                System.out.println("Rectangle number " + i + " found! Output: " + reports[i].particleArea);
                SmartDashboard.putNumber("Camera Output", reports[i].particleArea);
                if (reports[i].particleArea >= PARTICLE_AREA_THRESHOLD) {
                    return reports[i];
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            image = null;
            dataImage = null;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
