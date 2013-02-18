package farmington.frameworks;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import farmington.ultimateascent.BaseRobot;
import farmington.ultimateascent.IRobot;

public class Winning extends BaseRobot implements IRobot {

    DriveTrain winningDriveTrain = new DriveTrain(PWM_ONE, PWM_THREE, PWM_TWO, PWM_FOUR);
    int STILL = 1;
    Talon screwLift = new Talon(PWM_FIVE);
    WinningMoving moving = new WinningMoving();
    Strobe light = new Strobe();

    public void Spinning() {
        moving.Movement();
        light.lights();
        while (true) {
            if (leftStick.getRawButton(BUTTON_ELEVEN) == true) {
                STILL = 0;
            }

            while (STILL == 0) {
                winningDriveTrain.setSpeed(SPEED_FORWARD_THREE_QUARTERS, SPEED_REVERSE_QUARTER);     //left, right
                Timer.delay(0.010);
                if (leftStick.getRawButton(BUTTON_ELEVEN) == true) {
                    STILL = 1;
                }
            }

        }
    }
}
