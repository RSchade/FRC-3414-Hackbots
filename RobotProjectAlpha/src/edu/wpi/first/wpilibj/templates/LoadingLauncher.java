package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;

public class LoadingLauncher implements IRobot {

    Talon testMotor = new Talon(1);
    boolean loaderState = false;
    int loaderPulse = 1;
    private final int JOYSTICK_BUTTON_ONE = 1;
    private final int THREE_SECONDS = 300;

    public void Loader() {
        if (leftStick.getRawButton(JOYSTICK_BUTTON_ONE) == true || loaderState == true)        // if the button is pressed    -or-   "loaderState" = true :                                
        {
            testMotor.set(1.0);                                               // motor is turned on
            loaderPulse++;                                                    // "loaderPulse" increases by 1
            loaderState = true;                                               // "loaderState" = true  , so it can continue to loop even if the button is not pushed
            if (loaderPulse == THREE_SECONDS) {                               // if "loaderPulse" = 300:                
                testMotor.set(0.0);                                           //      reset: motor is turned off
                loaderState = false;                                          //      reset: "loaderState" = false
                loaderPulse = 1;                                              //      reset: "loaderPulse" restarts back to 1
            }
        }
    }
}
