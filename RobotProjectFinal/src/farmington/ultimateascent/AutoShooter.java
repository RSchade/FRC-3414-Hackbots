
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * Takes control of the bot and automagically aims at a target.
 * @author 3414
 */
public class AutoShooter implements IRobot {

    /**
     * Moves the drive train to center on the target x position.
     * @param target the rectangular camera target
     * @return 1 for right-offset, -1 for left-offset, 0 for on target
     */
    public int aimX(ParticleAnalysisReport target) {
        if (target.center_mass_x_normalized > 0.05) {
            return 1;
        } else if (target.center_mass_x_normalized < -0.05) {
            return -1;
        } else {
            return 0;
        }
    }
    
    /**
     * Moves the shooter to center on the target y position.
     * @param target the rectangular camera target
     * @return 1 for up-offset, -1 for down-offset, 0 for on target
     */
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
