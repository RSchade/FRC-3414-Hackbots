/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Cooper Riehl
 */

public interface IRobot {
    
     double TIME_DELAY = 0.020; // 20 millisecond loop
     boolean CAMERA_ENABLED = false; // Change for debugging without camera
     int SHOOTER_SPEED = 3000;
    
     double SPEED_FORWARD_FULL = 1.0;  //Motor speed values
     double SPEED_FORWARD_HALF = 0.5;
     double SPEED_STOP = 0.0;
     double SPEED_REVERSE_HALF = -0.5;
     double SPEED_REVERSE_FULL = -1.0;
     double SPEED_FORWARD_TESTING = 0.05;
     double SPEED_REVERSE_TESTING = -0.05;
    
     boolean ON = true;        //Solenoid values
     boolean OFF = false;
    
     int PWM_SLOT_ONE = 1;     //PWM slots
     int PWM_SLOT_TWO = 2;
     int PWM_SLOT_THREE = 3;
     int PWM_SLOT_FOUR = 4;
     int PWM_SLOT_FIVE = 5;
     int PWM_SLOT_SIX = 6;
     int PWM_SLOT_SEVEN = 7;
     int PWM_SLOT_EIGHT = 8;
     int PWM_SLOT_NINE = 9;
     int PWM_SLOT_TEN = 10;
    
     int SOLENOID_ONE = 1;     //Solenoid ports
     int SOLENOID_TWO = 2;
     int SOLENOID_THREE = 3;
     int SOLENOID_FOUR = 4;
     int SOLENOID_FIVE = 5;
    
     int USB_ONE = 1;          //USB ports
     int USB_TWO = 2;
    
     int DIO_ONE = 1;          //DIO slots
     int DIO_TWO = 2;
     int DIO_THREE = 3;
     int DIO_FOUR = 4;
     int DIO_FIVE = 5;
     int DIO_SIX = 6;
     int DIO_SEVEN = 7;
     int DIO_EIGHT = 8;
     int DIO_NINE = 9;
     int DIO_FOURTEEN = 14;
    
     int RELAY_ONE = 1;         //Relay channels
     int RELAY_TWO = 2;
    
     int HORIZ_AXIS = 1;       //Joystick axis
     int VERTICAL_AXIS = 2;
    
     int RIGHT_TRIGGER = 1;    //Joystick buttons
     int RIGHT_BUTTON_TWO = 2;
     int RIGHT_BUTTON_THREE = 3;
     int RIGHT_BUTTON_NINE = 9;
     int LEFT_TRIGGER = 1;
     int LEFT_BUTTON_TWO = 2;
     int LEFT_BUTTON_THREE = 3;
     int LEFT_BUTTON_EIGHT = 8;
    
     double PARTICLE_AREA_THRESHOLD = 2000;
     int TRUE_RECT_HEIGHT = 11; //inches
    
     double MAX_ENCODER_VALUE = 1000.0;  //Encoder values
     double MIN_ENCODER_VALUE = 0.0;
     
     Relay.Value RELAY_ON = Relay.Value.kOn;
     Relay.Value RELAY_OFF = Relay.Value.kOff;
    
     int WHEEL_ONE = 1;         //Shooter wheel IDs
     int WHEEL_TWO = 2;
     
     int RED_MIN = 0;          //Threshold levels for Red, Green and Blue
     int RED_MAX = 45;
     int GRN_MIN = 25;
     int GRN_MAX = 255;
     int BLU_MIN = 0;
     int BLU_MAX = 47;
}
