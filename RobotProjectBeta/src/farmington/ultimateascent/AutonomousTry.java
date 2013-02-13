
package farmington.ultimateascent;

import farmington.frameworks.AutoShooter;

public class AutonomousTry implements IRobot {

    AutoShooter autoAutoShooter;
    
    public AutonomousTry() {
        autoAutoShooter = new AutoShooter();
    }

    public void doStuff() {
        autoAutoShooter.aim();
    }
}