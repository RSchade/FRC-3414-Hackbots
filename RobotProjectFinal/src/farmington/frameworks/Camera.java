package farmington.frameworks;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import farmington.ultimateascent.IRobot;

/**
 * Camera processing.
 * @author 3414
 */
public class Camera implements IRobot {
    
    AxisCamera myCamera;
    CriteriaCollection crit = new CriteriaCollection();
    private ColorImage image;
    private BinaryImage dataImage;
    private boolean loopControl = true;

    /**
     * Main constructor for Camera.
     */
    public Camera() {
        if (CAMERA_ENABLED) {
            myCamera = AxisCamera.getInstance();
            crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 400, false);
            crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 400, false);
        }
    }
    
    /**
     * Calls findParticles() once each time this is true.
     * @param trigger button to be pressed for picture taking
     */
    public void takePicture(boolean trigger) {
        if (trigger && loopControl) {
            findParticles();
            loopControl = false;
        }
        if (!trigger && !loopControl) {
            loopControl = true;
        }
    }
    
    /**
     * Looks for rectangular reflective tape and processes.
     * @return the targeted rectangular image
     */
    public ParticleAnalysisReport findParticles() {
        try {
            image = myCamera.getImage();
            dataImage = image.thresholdRGB(RED_MIN, RED_MAX, BLU_MIN, BLU_MAX, GRN_MIN, GRN_MAX);
            dataImage = dataImage.removeSmallObjects(false, 2);
            dataImage = dataImage.convexHull(true);
            dataImage = dataImage.particleFilter(crit);
            ParticleAnalysisReport[] reports = dataImage.getOrderedParticleAnalysisReports();
            for(int i=0; i < reports.length; i++) {
                System.out.println("Rectangle number " + i + " found! Output: " + reports[i].particleArea);
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
