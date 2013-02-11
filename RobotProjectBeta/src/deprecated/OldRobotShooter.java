//
//package deprecated;
//
//import edu.wpi.first.wpilibj.Jaguar;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.templates.IRobot;
//
///**
// *
// * @author Rahul Yalamanchili and Shrihari Baskaramurthi
// */
//public class OldRobotShooter implements IRobot {
//    
//    Joystick n = new Joystick(1);
//    Jaguar k = new Jaguar(1);
//    Jaguar m = new Jaguar(2);
//    
//    public void axis()
//    {
//       if(n.getRawAxis(2)>=.05)
//       {
//           k.set(1);
//           m.set(1);
//}
//    }
//    
//    public void negativeAxis()
//    {
//        if(n.getRawAxis(2)<=.05)
//        {
//            k.set(-1);
//            m.set(-1);
//        }
//    }
//}
//
