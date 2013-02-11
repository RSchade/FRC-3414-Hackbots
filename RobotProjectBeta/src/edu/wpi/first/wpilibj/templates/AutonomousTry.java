
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class AutonomousTry extends BaseRobot implements IRobot {

    private Talon shooterLifter;
    private Encoder encoderB;

    public AutonomousTry(int lifterSlot, int encoderSlotA, int encoderSlotB) {
        shooterLifter = new Talon(lifterSlot);
        encoderB = new Encoder(encoderSlotA, encoderSlotB);
    }

    public void setAngle(double speed) {
        if ((speed > 0) && (MIN_ENCODER_VALUE <= encoderB.get()) && (encoderB.get() <= AUTONOMOUS_SET_VALUE)) {
            shooterLifter.set(speed);
        } else if ((speed < 0) && (MAX_ENCODER_VALUE >= encoderB.get()) && (encoderB.get() >= AUTONOMOUS_SET_VALUE)) {
            shooterLifter.set(speed);
        }







    }
}