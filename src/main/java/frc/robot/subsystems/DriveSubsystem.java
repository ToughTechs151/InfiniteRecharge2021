package frc.robot.subsystems;

import frc.robot.*;
import frc.robot.commands.DriveWithJoysticksCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
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
  DifferentialDrive driveTrain = null;
  double speedMultiplier=1.0;
  double normal=1.0;
  double crawl=.5;
  public void drive(double x,double y){
      driveTrain.tankDrive(x,y);
  }

  @Override
  
  public void driveTank(OI oi) {
    speedMultiplier = oi.getJoystick().getRawButton(Constants.RIGHT_BUMPER) ? crawl : normal;

    double throttle = deadzone(oi.getJoystick().getRawAxis(Constants.LEFT_JOYSTICK_Y));
    double turn = deadzone(oi.getJoystick().getRawAxis(Constants.RIGHT_JOYSTICK_X));
    
    drive(throttle * speedMultiplier, turn * speedMultiplier );
}
private double deadzone(double val) {
  return Math.abs(val) > softwareDeadband ? val : 0;
}
}
