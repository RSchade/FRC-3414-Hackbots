
package farmington.ultimateascent;

<<<<<<< HEAD
public class AutonomousTry extends BaseRobot implements IRobot {

=======
import farmington.frameworks.AutoShooter;

public class AutonomousTry implements IRobot {

    AutoShooter autoAutoShooter;
    
>>>>>>> c2e8f448ae257a0a85c3bf20038efed21209320e
    public AutonomousTry() {
        autoAutoShooter = new AutoShooter();
    }

    public void doStuff() {
<<<<<<< HEAD
        
=======
        autoAutoShooter.aim();
>>>>>>> c2e8f448ae257a0a85c3bf20038efed21209320e
    }
}