/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.HopperSubsystem;

public class HopperCommand extends CommandBase {
  private HopperSubsystem m_hopperSubsystem;
  /**
   * Creates a new HopperCommand.
   */
  private Timer timer;

  private double speed;
  public HopperCommand(HopperSubsystem subsystem, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    m_hopperSubsystem = subsystem;
    addRequirements(subsystem);
  }

  private boolean initialState;
  private int increment;
  private boolean lastState;
  private boolean thisState;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        SmartDashboard.putString("Hopper status", "***Hopper Jammed***");
      }
    }, 750L);
    initialState = m_hopperSubsystem.getHopperSwitchState();
    lastState = m_hopperSubsystem.getHopperSwitchState();
    increment = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    thisState = m_hopperSubsystem.getHopperSwitchState();
    if(thisState != lastState) {
      increment++;
      lastState = thisState;
    }
    m_hopperSubsystem.start(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hopperSubsystem.stop();
    timer.cancel();
    if(initialState)
      increment = 1;
    else
      increment = 2;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (initialState && increment == 1) || (!initialState && increment == 2);
  }
}
