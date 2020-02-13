/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.AdjustLauncherCommand;
import frc.robot.commands.ChangeLauncherSpeedCommand;
import frc.robot.commands.DriveWithJoysticksCommand;
import frc.robot.commands.HopperCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // OI joysticks 
  
  public final Joystick driverOI=new Joystick(0);
  public final static Joystick coDriverOI=new Joystick(1);
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem=new DriveSubsystem();
  private final LimeLightSubsystem m_LimeLightSubsystem=new LimeLightSubsystem();
  private final DriveWithJoysticksCommand m_DriveWithJoysticksCommand=new DriveWithJoysticksCommand(m_driveSubsystem,driverOI,m_LimeLightSubsystem);
  public final HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  private final HopperCommand m_hopperCommand = new HopperCommand(m_hopperSubsystem, 1.0, coDriverOI);
  
  public final static PIDController launcherPID = new PIDController(Constants.LAUNCHERKP, Constants.LAUNCHERKI,
      Constants.LAUNCHERKD);
  private final LauncherSubsystem mLauncherSubsystem=  new LauncherSubsystem(launcherPID);
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    //Shuffleboard.addEventMarker("LauncherSpeed", EventImportance.kHigh);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    JoystickButton Ac = new JoystickButton(coDriverOI, 1);
    Ac.whenPressed(new AdjustLauncherCommand(mLauncherSubsystem, m_LimeLightSubsystem));
    JoystickButton Bc = new JoystickButton(coDriverOI, 2);
    Bc.whenPressed(new ChangeLauncherSpeedCommand(0,mLauncherSubsystem));
    JoystickButton Xc = new JoystickButton(coDriverOI, 3);
    Xc.whenPressed(new ChangeLauncherSpeedCommand(2950, mLauncherSubsystem));
    JoystickButton Yc = new JoystickButton(coDriverOI, 4);
    Yc.whenPressed(new ChangeLauncherSpeedCommand(3750, mLauncherSubsystem));//2950
    JoystickButton LEFT_BUMPERc = new JoystickButton(coDriverOI, 5);
    LEFT_BUMPERc.whenHeld(new ChangeLauncherSpeedCommand(-500, mLauncherSubsystem));
    JoystickButton RIGHT_BUMPERc = new JoystickButton(coDriverOI, 6);
    //RIGHT_BUMPERc.whenHeld(m_hopperCommand);
    JoystickButton BACK = new JoystickButton(driverOI, 7);
    JoystickButton START = new JoystickButton(driverOI, 8);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /*
   * public Command getAutonomousCommand() { // An ExampleCommand will run in
   * autonomous return m_autoCommand; }
   */
  public Command getDriveCommand() {

    return m_DriveWithJoysticksCommand;
  }

  public static PIDController getPidController() {
    return launcherPID;
  }
  public Command getHopperCommand(){
    return m_hopperCommand;
  }
}
