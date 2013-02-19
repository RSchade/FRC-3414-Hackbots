/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Relay;

/**
 * Global variables used throughout the project are initialized here.
 * @author 3414
 */
public interface IRobot {
    
    //Miscellanious values
     double TIME_DELAY = 0.020; // 20 millisecond loop
     boolean CAMERA_ENABLED = true; // Set to false for testing without camera
     int SHOOTER_SPEED = -3000;
     double JOYSTICK_DEADZONE = 0.1;
     
     //PID tuning values
     double PID_TOLERANCE = 0.02; //2% tolerance
     double KP = 0.1;
     double KI = 0.001;
     double KD = 0.0;
    
     //Motor speed values
     double SPEED_FORWARD_FULL = 1.0;
     double SPEED_FORWARD_HALF = 0.5;
     double SPEED_STOP = 0.0;
     double SPEED_REVERSE_HALF = -0.5;
     double SPEED_REVERSE_FULL = -1.0;
     double SPEED_FORWARD_TESTING = 0.05;
     double SPEED_REVERSE_TESTING = -0.05;
    
     //PWM slots
     int PWM_ONE = 1;
     int PWM_TWO = 2;
     int PWM_THREE = 3;
     int PWM_FOUR = 4;
     int PWM_FIVE = 5;
     int PWM_SIX = 6;
     int PWM_SEVEN = 7;
     int PWM_EIGHT = 8;
     int PWM_NINE = 9;
     int PWM_TEN = 10;
    
     //Solenoid slots
     int SOLENOID_ZERO = 0;
     int SOLENOID_ONE = 1;
     int SOLENOID_TWO = 2;
     int SOLENOID_THREE = 3;
     int SOLENOID_FOUR = 4;
     int SOLENOID_FIVE = 5;
     
     int MODULE_ONE = 1;
     int MODULE_THREE = 3;
    
     //USB ports
     int USB_ONE = 1;
     int USB_TWO = 2;
     int USB_THREE = 3;
     
     //Joystick axis
     int HORIZ_AXIS = 1;
     int VERTICAL_AXIS = 2;
     
     //Joystick buttons
     int TRIGGER = 1;
     int BUTTON_ONE = 1;
     int BUTTON_TWO = 2;
     int BUTTON_THREE = 3;
     int BUTTON_FOUR = 4;
     int BUTTON_FIVE = 5;
     int BUTTON_SIX = 6;
     int BUTTON_SEVEN = 7;
     int BUTTON_EIGHT = 8;
     int BUTTON_NINE = 9;
     int BUTTON_TEN = 10;
     
     //DIO slots
     int DIO_ONE = 1;
     int DIO_TWO = 2;
     int DIO_THREE = 3;
     int DIO_FOUR = 4;
     int DIO_FIVE = 5;
     int DIO_SIX = 6;
     int DIO_SEVEN = 7;
     int DIO_EIGHT = 8;
     int DIO_NINE = 9;
     int DIO_TWELVE = 12;
     int DIO_THIRTEEN = 13;
     int DIO_FOURTEEN = 14;
      
     //Relay channels
     int RELAY_ONE = 1;
     int RELAY_TWO = 2;
    
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
     Relay.Value RELAY_FORWARD = Relay.Value.kForward;
     Relay.Value RELAY_REVERSE = Relay.Value.kReverse;
     Relay.Value RELAY_OFF = Relay.Value.kOff;
}
