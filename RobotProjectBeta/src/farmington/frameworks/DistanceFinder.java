/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import farmington.ultimateascent.IRobot;

/**
 * Finds the distance from the camera to its target.
 * @author 3414
 */
public class DistanceFinder implements IRobot {
    
    /**
     * Finds said distance.
     * @param target the rectangular camera target
     * @return distance from camera to its target
     */
    public double getDistance(ParticleAnalysisReport target) {
        double constant = 0;
        return (TRUE_RECT_HEIGHT*constant)/target.boundingRectHeight; //FIXME insert correct constant
    }
}
