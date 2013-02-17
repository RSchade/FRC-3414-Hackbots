/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Relay;

/**
 * Global variables used throughout the project are initialized here.
 * @author Cooper Riehl
 */
public interface IRobot {
    
     double TIME_DELAY = 0.020; // 20 millisecond loop
     boolean CAMERA_ENABLED = true; // Change for debugging without camera
     int SHOOTER_SPEED = -3000;
     double JOYSTICK_DEADZONE = 0.1;
    
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
    
     //Joystick buttons
     int TRIGGER = 1;
     int BUTTON_TWO = 2;
     int BUTTON_THREE = 3;
     int BUTTON_FIVE = 5;
     int BUTTON_EIGHT = 8;
     int BUTTON_NINE = 9;
    
     //Camera stuff
     double PARTICLE_AREA_THRESHOLD = 2000;
     int TRUE_RECT_HEIGHT = 11; //inches
     
     //Camera threshold levels for RGB
     int RED_MIN = 0;
     int RED_MAX = 45;
     int GRN_MIN = 25;
     int GRN_MAX = 255;
     int BLU_MIN = 0;
     int BLU_MAX = 47;
     
     //Relay constants
     Relay.Value RELAY_ON = Relay.Value.kOn;
     Relay.Value RELAY_OFF = Relay.Value.kOff;
}
