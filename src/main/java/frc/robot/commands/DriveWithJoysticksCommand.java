package frc.robot.commands;

import frc.robot.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.Timer;
import java.util.TimerTask;
/**
 * command to drive the drivetrain using joysticks
 */
public class DriveWithJoysticksCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drive;
  private final LimeLightSubsystem m_light;
  private final Joystick m_driver;
  private Joystick coDriver;
  private Timer timer;
  private TimerTask task;
  private boolean prevState=false;
  /**
   * The command for driving the robot during teleop
   * @param drive the drivetrain
   * @param driver the driver input
   * @param light the limelight
   */
  public DriveWithJoysticksCommand(DriveSubsystem drive,Joystick driver,LimeLightSubsystem light,Joystick coDriver) {
    m_drive=drive;
    m_driver=driver;   
    m_light=light;
    this.coDriver=coDriver;
    addRequirements(drive);
    addRequirements(light);
    timer=new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    if(m_driver.getRawButton(Constants.LEFT_BUMPER)||coDriver.getRawButton(Constants.A)){
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
      prevState=true;
    }
    else{
      if(prevState){
        timer.schedule(new TimerTask(){
          public void run(){
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
          }
        }, 1000);
        prevState=false;
      }
    }
    
    m_drive.driveTank(m_driver);
    m_light.dashBoard();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  
}