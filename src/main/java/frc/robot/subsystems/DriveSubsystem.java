package frc.robot.subsystems;

import frc.robot.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.DriveWithJoysticksCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
/**
 * An example subsystem. 
 */

public class DriveSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX frontRight;
  private WPI_TalonSRX backRight;
  private WPI_TalonSRX frontLeft;
  private WPI_TalonSRX backLeft;
  public DriveSubsystem(){
    frontRight = new WPI_TalonSRX(Constants.RIGHT);
    //backRight = new WPI_TalonSRX(Constants.BACK_RIGHT);
    frontLeft = new WPI_TalonSRX(Constants.LEFT);
    //backLeft = new WPI_TalonSRX(Constants.BACK_LEFT);
      
    //SpeedControllerGroup right = new SpeedControllerGroup(frontRight, backRight);
    //SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, backLeft);

    driveTrain = new DifferentialDrive(frontLeft, frontRight);

    frontLeft.setInverted(true);
    frontRight.setInverted(true);

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
