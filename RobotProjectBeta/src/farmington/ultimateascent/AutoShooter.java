
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * Takes control of the bot and automagically aims at a target.
 * 
 * @author Cooper Riehl
 * @version 1.0
 */
public class AutoShooter implements IRobot {

    public int aimX(ParticleAnalysisReport target) {
        if (target.center_mass_x_normalized > 0.05) {
            return 1;
        } else if (target.center_mass_x_normalized < -0.05) {
            return -1;
        } else {
            return 0;
        }
    }
    
    public int aimY(ParticleAnalysisReport target) {
        if (target.center_mass_y_normalized > 0.05) {
            return 1;
        } else if (target.center_mass_y_normalized < -0.05) {
            return -1;
        } else {
            return 0;
        }
    }
}
