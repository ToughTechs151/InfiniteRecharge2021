package frc.robot.subsystems;

import frc.robot.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
/**
 * An example subsystem. 
 */

public class DriveSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Talon frontRight;
  private Talon backRight;
  private Talon frontLeft;
  private Talon backLeft;
  public DriveSubsystem(){
    frontRight = new Talon(Constants.FRONT_RIGHT);
    backRight = new Talon(Constants.BACK_RIGHT);
    frontLeft = new Talon(Constants.FRONT_LEFT);
    backLeft = new Talon(Constants.BACK_LEFT);
      
    SpeedControllerGroup right = new SpeedControllerGroup(frontRight, backRight);
    SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, backLeft);

    driveTrain = new DifferentialDrive(left, right);

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
    speedMultiplier = oi.getRawButton(Constants.RIGHT_BUMPER) ? crawl : normal;

    double throttle = deadzone(oi.getRawAxis(Constants.LEFT_JOYSTICK_Y));
    double turn = deadzone(oi.getRawAxis(Constants.RIGHT_JOYSTICK_Y));

    drive(throttle * speedMultiplier, turn * speedMultiplier);
  }

  private static double deadzone(double val) {
    return Math.abs(val) > softwareDeadband ? val : 0;
}
}
