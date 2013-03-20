package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import farmington.ultimateascent.IRobot;

public class ShooterPistonNew implements IRobot {

    private Talon loaderWheel;
    private Solenoid shooterPiston;
    private Solenoid shooterPistonReverse;
    private DigitalInput sensor;
    
    private Waiter autoReloadControl;
    private Waiter forceReloadControl;
    private Waiter autoShooterControl;
    private Waiter autoBayControl;
    
    private boolean isBayFull;
    private boolean loaderOverRide;
    private boolean wantAuto;
    private boolean control = false;
    private int runCounter;

    public ShooterPistonNew(int loaderWheelSlot, int shootChannel, int loaderSensorSlot, int shootChannelTwo) {
        loaderWheel = new Talon(loaderWheelSlot);
        shooterPiston = new Solenoid(shootChannel);
        shooterPistonReverse = new Solenoid(shootChannelTwo);
        sensor = new DigitalInput(loaderSensorSlot);

        autoReloadControl = new Waiter();
        forceReloadControl = new Waiter();
        autoShooterControl = new Waiter();
        autoBayControl = new Waiter();

        isBayFull = false;
        loaderOverRide = false;
        wantAuto = false;
    }

    public void reload(boolean forceReload) {

        //Force Reload
        if (forceReloadControl.timeUp()) {
            loaderWheel.set(SPEED_STOP);
            isBayFull = true;
            loaderOverRide = false;
        }
        if (forceReload == true && isBayFull == false && !forceReloadControl.isWaiting()) {
            loaderWheel.set(SPEED_FORWARD_FULL);
            loaderOverRide = true;
            forceReloadControl.waitXLoops(38);
        }

        //Automatically Reload
        if (autoBayControl.timeUp()) {
            isBayFull = true;
        }
        if (autoReloadControl.timeUp() && !autoBayControl.isWaiting()) {
            loaderWheel.set(SPEED_STOP);
            autoBayControl.waitXLoops(20);
        }
        if (sensor.get() == true && isBayFull == false && loaderOverRide == false && !autoReloadControl.isWaiting()) {
            loaderWheel.set(SPEED_FORWARD_FULL);
            autoReloadControl.waitXLoops(38);
        }
    }

    public void manualShoot(boolean wantToShoot) {

        //Manually Shoot
        if (wantToShoot == true) {
            loaderOverRide = true;
            shooterPiston.set(true);
            shooterPistonReverse.set(false);
            isBayFull = false;
        } else {
            loaderOverRide = false;
            shooterPiston.set(false);
            shooterPistonReverse.set(true);
        }
    }

    public void autoRun(boolean workAuto) {

        //Sets a single-button counter

        int counter = 0;
        if (workAuto == true && !control) {         // inverse method!!!
            counter++;
            control = true;
        } else if (!workAuto) {
            control = false;
        }
        if (counter % 2 == 0) {
            wantAuto = true;
            runCounter = 0;
        } else {
            wantAuto = false;
        }
        if (counter == 50) {
            counter = 2;
        }

        //Automatically Run
        if (wantAuto = true) {

            //Auto Run counter
            if (runCounter < 4) {

                //Automatically Reload
                if (autoBayControl.timeUp()) {
                    isBayFull = true;
                }
                if (autoReloadControl.timeUp() && !autoBayControl.isWaiting()) {
                    loaderWheel.set(SPEED_STOP);
                    autoBayControl.waitXLoops(20);
                }
                if (sensor.get() == true && isBayFull == false && loaderOverRide == false && !autoReloadControl.isWaiting()) {
                    loaderWheel.set(SPEED_FORWARD_FULL);
                    autoReloadControl.waitXLoops(38);
                }

                //Automatically Shoot
                if (autoShooterControl.timeUp()) {
                    shooterPiston.set(false);
                    shooterPistonReverse.set(true);
                    isBayFull = false;
                    runCounter++;
                }
                if (isBayFull == true && !autoShooterControl.isWaiting()) {
                    shooterPiston.set(true);
                    shooterPistonReverse.set(false);
                    autoShooterControl.waitXLoops(25);      //half a second
                }
                counter++;      //reset because when %2 , runCounter = 0
            }
        }
    }
}
