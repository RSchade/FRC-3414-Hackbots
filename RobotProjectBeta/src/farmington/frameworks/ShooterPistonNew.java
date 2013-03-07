package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

import farmington.frameworks.Waiter;
import farmington.ultimateascent.IRobot;

public class ShooterPistonNew implements IRobot {

    private Talon loaderWheel;
    private Solenoid shooterPiston;
    private DigitalInput sensor;
    private Waiter autoReloadControl;
    private Waiter forceReloadControl;
    private Waiter autoShooterControl;
    private Waiter autoBayControl;
//    private boolean sensor;
    private boolean isBayFull;
    private boolean loaderOverRide;
    private boolean wantAuto;
    private int runCounter;

    public ShooterPistonNew(int loaderWheelSlot, int shootChannel, int loaderSensorSlot) {
        loaderWheel = new Talon(loaderWheelSlot);
        shooterPiston = new Solenoid(shootChannel);
        sensor = new DigitalInput(loaderSensorSlot);
        autoReloadControl = new Waiter();
        forceReloadControl = new Waiter();
        autoShooterControl = new Waiter();
        autoBayControl = new Waiter();
//        sensor = false;
        isBayFull = false;
        loaderOverRide = false;
        wantAuto = false;
        runCounter = 0;
    }

    public void reload(boolean forceReload) {

        //Force Reload
        if (forceReload == true && isBayFull == false) {
            loaderWheel.set(SPEED_FORWARD_FULL);
            loaderOverRide = true;
            forceReloadControl.waitXLoops(12);

            if (forceReloadControl.timeUp()) {
                loaderWheel.set(SPEED_STOP);
                isBayFull = true;
                loaderOverRide = false;
            }
        }
        //Automatically Reload
        if (sensor.get() == true && isBayFull == false && loaderOverRide == false) {
            loaderWheel.set(SPEED_FORWARD_FULL);
            autoReloadControl.waitXLoops(12);

            if (autoReloadControl.timeUp()) {
                loaderWheel.set(SPEED_STOP);
                autoBayControl.waitXLoops(7);

                if (autoBayControl.timeUp()) {
                    isBayFull = true;
                }
            }
        }
    }

    public void manualShoot(boolean wantToShoot) {

        //Manually Shoot
        if (wantToShoot == true) {
            shooterPiston.set(true);
            isBayFull = false;
        } else {
            shooterPiston.set(false);
        }
    }

    public void autoRun(boolean workAuto) {

        //Sets a single-button counter
        int counter = 0;
        if (workAuto == true) {
            counter++;
        }
        if (counter % 2 == 0) {
            wantAuto = true;
        } else {
            wantAuto = false;
        }
        if (counter == 50) {
            counter = 2;
        }

        //Automatically Run
        if (wantAuto = true) {

            //Auto Run counter
            for (runCounter = 0; runCounter < 5; runCounter++) {

                //Automatically Reload
                if (sensor.get() == true && isBayFull == false && loaderOverRide == false) {
                    loaderWheel.set(SPEED_FORWARD_FULL);
                    autoReloadControl.waitXLoops(12);

                    if (autoReloadControl.timeUp()) {
                        loaderWheel.set(SPEED_STOP);
                        autoBayControl.waitXLoops(7);

                        if (autoBayControl.timeUp()) {
                            isBayFull = true;
                        }
                    }
                }

                //Automatically Shoot
                if (isBayFull == true) {
                    shooterPiston.set(true);
                    autoShooterControl.waitXLoops(3);

                    if (autoShooterControl.timeUp()) {
                        shooterPiston.set(false);
                        isBayFull = false;
                    }
                }

                //Shut off loop
                if (runCounter == 4) {
                    counter++;
                }
            }

            //Resets loop
            runCounter = 0;
        }
    }
}
