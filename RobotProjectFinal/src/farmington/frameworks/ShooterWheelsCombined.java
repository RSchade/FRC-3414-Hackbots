/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

/**
 *
 * @author Robotics
 */
public class ShooterWheelsCombined {
    private ShooterWheel wheelOne;
    private ShooterWheel wheelTwo;
    
    public ShooterWheelsCombined(ShooterWheel wheelA, ShooterWheel wheelB) {
        wheelOne = wheelA;
        wheelTwo = wheelB;
    }
    
    public void setWheelSpeeds(double wheelOneSpeed, double wheelTwoSpeed) {
        wheelOne.setTrueSpeed(-wheelOneSpeed);
        wheelTwo.setTrueSpeed(wheelTwoSpeed);
    }
    
    public double getWheelOneSpeed() {
        return wheelOne.getTrueSpeed();
    }
    
    public double getWheelTwoSpeed() {
        return wheelTwo.getTrueSpeed();
    }
}
