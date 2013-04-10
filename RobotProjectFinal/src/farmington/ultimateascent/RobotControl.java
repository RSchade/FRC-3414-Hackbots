/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The main control class for our robot.
 *
 * @author 3414
 */
public class RobotControl extends BaseRobot implements IRobot {

    double WHEEL_ONE_SPEED;
    double WHEEL_TWO_SPEED;
    boolean onTargetX;
    boolean onTargetY;
    boolean liftControl;
    double driveScaling;
    double targetVoltage;
    double manualTargetVoltage;
    double offset;
    Waiter LEDCycleControl;

    /**
     * Main constructor for RobotControl.
     */
    public RobotControl() {
        super();
        WHEEL_ONE_SPEED = 0.70;
        WHEEL_TWO_SPEED = 1.0;
        onTargetX = false;
        onTargetY = false;
        liftControl = false;
        driveScaling = 1.0;
        manualTargetVoltage = 0.0;
        offset = 0.0;
        LEDCycleControl = new Waiter();
    }

    /**
     * Puts all information we need to see in the Driver Station on the
     * SmartDashboard.
     */
    private void updateDashboard() {
        SmartDashboard.putBoolean("Loader Wheel Sensor", myShooterLoader.getLoaderSensor());
        SmartDashboard.putBoolean("Chamber Sensor", myShooterLoader.getChamberSensor());
        SmartDashboard.putNumber("Potentiometer", myShooterScrew.getVoltage());
        SmartDashboard.putBoolean("Shooter Piston State", myShooterPiston.get());
        SmartDashboard.putNumber("Shooter Wheel One Voltage", myShooterWheels.getWheelOneSpeed());
        SmartDashboard.putNumber("Shooter Wheel Two Voltage", myShooterWheels.getWheelTwoSpeed());
    }

    /**
     * Redirect method for autonomous control.
     */
    public void autonomous() {
        myShooterLoader.turnOff();
        targetVoltage = 2.005;
        myShooterWheels.setWheelSpeeds(WHEEL_ONE_SPEED, WHEEL_TWO_SPEED);
        boolean screwIsGood = false;
        boolean driveIsGood = false;
        double time = 0.000;
        while (!screwIsGood || !driveIsGood) {
            if (myShooterScrew.getVoltage() <= targetVoltage) {
                myShooterScrew.setMovement(false, false, SCREW_OFF);
                screwIsGood = true;
            } else {
                myShooterScrew.setMovement(false, true, SCREW_FULL);
            }

            //Drive backwards while inside the pyramid
            if (leftStick.getRawAxis(SWITCH_AXIS) < 0) {
                time += 0.010;
                if (time >= 0.750) {
                    myDrive.setSpeed(SPEED_STOP);
                    driveIsGood = true;
                } else {
                    myDrive.setSpeed(SPEED_REVERSE_HALF);
                }
            } else {
                driveIsGood = true;
            }

            Timer.delay(0.010);
        }
        int i = 1;
        while (i <= 3) {
            myShooterPiston.set(true);
            Timer.delay(0.25);  //Extended for 1/4 of a second               
            myShooterPiston.set(false);
            Timer.delay(0.75);  //Wait for 3/4 of a second
            myShooterLoader.turnOn();
            Timer.delay(0.75); //Wait for the frisbee to drop in
            myShooterLoader.turnOff();
            Timer.delay(0.50); //Wait 1/2 second for the frisbee to settle and wheels to reach speed
            i++;
        }
        myShooterWheels.setWheelSpeeds(SPEED_STOP, SPEED_STOP);
        if (myShooterScrew.getVoltage() < 3.5)
        {
            myShooterScrew.setMovement(true, false, 1.0);
        }
        else{
            myShooterScrew.setMovement(false, false, SCREW_OFF);
        }
        if (leftStick.getRawAxis(SWITCH_AXIS) > 0) {
            myDrive.setSpeed(SPEED_REVERSE_HALF);
            Timer.delay(1.0);
            myDrive.setSpeed(SPEED_STOP);
        }

    }

    public void resetSystems() {
        myShooterPiston.reset();
        myShooterLoader.reset();

        //reset autonomous
        myShooterScrew.setMovement(false, false, SCREW_OFF);
        myDrive.setSpeed(SPEED_STOP);
        myShooterPiston.set(false);
        myShooterWheels.setWheelSpeeds(SPEED_STOP, SPEED_STOP);
    }

    /**
     * Updates important systems, called every 20 milliseconds in Main.java.
     */
    public void twentyMSLoop() {
        //Drive speed scaling
        if (rightStick.getRawButton(TRIGGER)) {
            driveScaling = 1.0;
        } else {
            driveScaling = 0.5;
        }

        //Drive Train
        if (rightStick.getRawButton(BUTTON_TEN)) {
            myDrive.setSpeedWithJoysticks(driveScaling * rightStick.getRawAxis(VERTICAL_AXIS));
        } else {
            myDrive.setSpeedWithJoysticks(driveScaling * leftStick.getRawAxis(VERTICAL_AXIS), driveScaling * rightStick.getRawAxis(VERTICAL_AXIS));
        }

        //Screw with manual positioning
        if (!(gamepad.getRawButton(BUTTON_FOUR) || gamepad.getRawButton(BUTTON_TWO))) {
            if (gamepad.getRawButton(BUTTON_THREE)) {
                //Loading angle
                manualTargetVoltage = 3.50;
                offset = 0.025;
            } else if (gamepad.getRawButton(BUTTON_ONE)) {
                //Shooting angle from back of pyramid
                manualTargetVoltage = 1.950;
                offset = 0.050;
            }
            if (manualTargetVoltage != 0.0) {
                int position = myShooterScrew.isOnTarget(manualTargetVoltage, offset);
                if (position == -1) {
                    myShooterScrew.setMovement(true, false, SCREW_FULL);
                } else if (position == 1) {
                    myShooterScrew.setMovement(false, true, SCREW_FULL);
                } else {
                    myShooterScrew.setMovement(false, false, SCREW_OFF);
                    manualTargetVoltage = 0.0;
                }
            } else {
                myShooterScrew.setMovement(false, false, SCREW_OFF);
            }
        } else {
            manualTargetVoltage = 0.0;
            double speedFactor;
            if (gamepad.getRawButton(BUTTON_FIVE)) {
                speedFactor = 0.75;
            } else {
                speedFactor = 1.0;
            }
            myShooterScrew.setMovement(gamepad.getRawButton(BUTTON_FOUR), gamepad.getRawButton(BUTTON_TWO), speedFactor);
        }

        //Piston
        myShooterPiston.shootWithTimeDelay(gamepad.getRawButton(BUTTON_EIGHT));

        //Automatic loader update
        myShooterLoader.updateLoader(gamepad.getRawButton(BUTTON_SIX));

        //Shooter Wheel Speed
        if (gamepad.getRawButton(BUTTON_NINE)) {
            WHEEL_ONE_SPEED = 0.50;
            WHEEL_TWO_SPEED = 0.75;
        } else {
            WHEEL_ONE_SPEED = 0.7;
            WHEEL_TWO_SPEED = 1.0;
        }
        if (gamepad.getRawButton(BUTTON_SEVEN)) {
            myShooterWheels.setWheelSpeeds(WHEEL_ONE_SPEED, WHEEL_TWO_SPEED);
        } else {
            myShooterWheels.setWheelSpeeds(SPEED_STOP, SPEED_STOP);
        }

        //Pyramid lifter logic
        if (rightStick.getRawButton(BUTTON_EIGHT) && liftControl) {
            myPyramidLifter.alternate();
            liftControl = false;
        } else if (!rightStick.getRawButton(BUTTON_EIGHT)) {
            liftControl = true;
        }

        //LED Manual Cycling
        if (leftStick.getRawButton(TRIGGER) && LEDCycleControl.timeUp()) {
            myLed.cycleColors();
            LEDCycleControl.waitXms(500);
        }

        //LED Automatic Color changes
        if (!myShooterLoader.getChamberSensor()) {
            myLed.setGreen();
            myLed.canCycle = false;
        } else if (myShooterLoader.getChamberSensor() && myShooterLoader.getLoaderSensor()) {
            myLed.setBlue();
            myLed.canCycle = false;
        } else if (myShooterLoader.getLoaderSensor()) {
            myLed.setPurple();
            myLed.canCycle = true;
        }

        this.updateDashboard();
    }

    /**
     * Called every 100 milliseconds in Main.java.
     */
    public void hundredMSLoop() {
    }

    /**
     * Called every second in Main.java.
     */
    public void thousandMSLoop() {
    }
}
