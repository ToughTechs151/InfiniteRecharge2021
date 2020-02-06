package frc.robot.subsystems;

import frc.robot.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem. 
 */

public class DriveSubsystem extends SubsystemBase {
  // Drive motors
  private Talon frontRight;
  private Talon backRight;
  private Talon frontLeft;
  private Talon backLeft;
  public DriveSubsystem(){
    //Assign drive motors
    frontRight = new Talon(Constants.FRONT_RIGHT);
    backRight = new Talon(Constants.BACK_RIGHT);
    frontLeft = new Talon(Constants.FRONT_LEFT);
    backLeft = new Talon(Constants.BACK_LEFT);
    //Group drive motors based on location  
    SpeedControllerGroup left = new SpeedControllerGroup(frontRight, backRight);
    SpeedControllerGroup right= new SpeedControllerGroup(frontLeft, backLeft);

    driveTrain = new DifferentialDrive(left, right);
    //adjust for which side of the robot should be front.
    left.setInverted(true);
    right.setInverted(true);

  }
  private static double softwareDeadband = 0.05;
  static DifferentialDrive driveTrain = null;
  static double speedMultiplier = 1.0;
  static double normal = 1.0;
  static double crawl = .5;

  public static void drive(double x, double y) {
    driveTrain.tankDrive(x, y);
  }

  public void driveTank(Joystick oi) {
    //check to see if the robot should drive slower
    speedMultiplier = oi.getRawButton(Constants.RIGHT_BUMPER) ? crawl : normal;

    double leftDrive = deadzone(oi.getRawAxis(Constants.LEFT_JOYSTICK_Y));
    double rightDrive = deadzone(oi.getRawAxis(Constants.RIGHT_JOYSTICK_Y));
    float Kp=-0.075f;
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    //checks for input from drive to allign to target.
    if(oi.getRawButton(Constants.LEFT_BUMPER) ? true : false)
    if (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0)==1){
      double heading_error = tx;
      double steering_adjust = Kp * tx;

      leftDrive+=steering_adjust;
      rightDrive-=steering_adjust;
    }

    drive((leftDrive/Math.abs(leftDrive))*Math.pow(leftDrive,2)* speedMultiplier, Math.pow(rightDrive ,2)*(rightDrive/Math.abs(rightDrive))* speedMultiplier);
  }
  //checks value against software deadband to avoid minor variations in joystick position
  private static double deadzone(double val) {
    return Math.abs(val) > softwareDeadband ? val : 0;
}


}
