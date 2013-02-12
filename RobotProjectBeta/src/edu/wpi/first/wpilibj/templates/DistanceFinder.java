/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;


public class DistanceFinder implements IRobot {
    Camera distanceCamera = new Camera();
    ParticleAnalysisReport target;
    
    public double getDistance() {
        double constant = 0;
        target = distanceCamera.findParticles();
        return (TRUE_RECT_HEIGHT*constant)/target.boundingRectHeight;
    }
}
