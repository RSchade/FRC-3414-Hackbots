package farmington.frameworks;

import farmington.ultimateascent.IRobot;
import farmington.ultimateascent.BaseRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

public class Strobe extends BaseRobot implements IRobot {

    DigitalInput sensor = new DigitalInput(1);
    Relay output = new Relay(1);
    int STILL = 1;

    public void lights() {
        while (true) {
            if (leftStick.getRawButton(BUTTON_ELEVEN) == true) {
                STILL = 0;
            }
            while (STILL == 0) {
                output.set(Relay.Value.kOn);
                Timer.delay(0.1);
                output.set(Relay.Value.kOff);
                Timer.delay(0.1);
            }
            if (leftStick.getRawButton(BUTTON_ELEVEN) == true) {
                STILL = 1;
            }
            Timer.delay(0.010);
        }
    }
}

