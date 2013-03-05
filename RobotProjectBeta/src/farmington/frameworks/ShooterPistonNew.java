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
    private Waiter reloadControl;
    private Waiter shooterControl;
    private Waiter autoBayControl;
//    private boolean sensor;
    private boolean isBayFull;
    private boolean loaderOverRide;
    
    public ShooterPistonNew(int loaderWheelSlot, int shootChannel, int loaderSensorSlot) {
        loaderWheel = new Talon(loaderWheelSlot);
        shooterPiston = new Solenoid(shootChannel);
        sensor = new DigitalInput(loaderSensorSlot);
        autoReloadControl = new Waiter();
        reloadControl = new Waiter();
        shooterControl = new Waiter();
        autoBayControl = new Waiter();
//        sensor = false;
        isBayFull = false;
        loaderOverRide = false;
    }

    public void reload(boolean forceReload) {

        //Force Reload
        if (forceReload == true && isBayFull == false) {
            loaderWheel.set(SPEED_FORWARD_FULL);
            loaderOverRide = true;
            reloadControl.waitXLoops(12);

            if (reloadControl.timeUp()) {
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
                isBayFull = true;
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

    public void autoRun(boolean wantAuto) {

        //Automatically Run
        if (wantAuto = true) {

            //Automatically Reload
            if (sensor.get() == true && isBayFull == false && loaderOverRide == false) {
                loaderWheel.set(SPEED_FORWARD_FULL);
                autoReloadControl.waitXLoops(12);

                if (autoReloadControl.timeUp()) {
                    loaderWheel.set(SPEED_STOP);
                    autoBayControl.waitXLoops(5);
                    
                    if (autoBayControl.timeUp()) {
                        isBayFull = true;
                    }
                }
            }

            //Automatically Shoot
            if (isBayFull == true) {
                shooterPiston.set(true);
                shooterControl.waitXLoops(5);

                if (shooterControl.timeUp()) {
                    shooterPiston.set(false);
                    isBayFull = false;
                }
            }

        }
    }
}
