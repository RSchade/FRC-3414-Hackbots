package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import farmington.ultimateascent.BaseRobot;
import farmington.ultimateascent.IRobot;

public class WinningMoving extends BaseRobot implements IRobot {

    Talon screwLift = new Talon(PWM_FIVE);
    int STILL = 1;
    DigitalInput sensorLow = new DigitalInput(DIO_EIGHT);
    DigitalInput sensorHigh = new DigitalInput(DIO_NINE);

    public void Movement() {
        while (true) {
            if (leftStick.getRawButton(BUTTON_ELEVEN) == true) {
                STILL = 0;
            }
            while (STILL == 0) {
                screwLift.set(SPEED_FORWARD_FULL);
                if (sensorHigh.get() == true) {
                    screwLift.set(SPEED_REVERSE_FULL);
                } else if (sensorLow.get() == true) {
                    screwLift.set(SPEED_FORWARD_FULL);
                }
                if (leftStick.getRawButton(BUTTON_ELEVEN) == true) {
                    STILL = 1;
                }
                Timer.delay(0.010);
            }
        }
    }
}
