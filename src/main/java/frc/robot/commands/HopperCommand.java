/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;


public class HopperCommand extends CommandBase {
  private double m_timeout;
  /**
   * Creates a new HopperCommand.
   */
  public HopperCommand(HopperSubsystem subsystem, double timeout) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_timeout = timeout;
    m_hopperSubsystem = subsystem;
    addRequirements(m_hopperSubSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setTimeout(m_timeout);
    m_hopperSubsystem.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hopperSubSystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isTimedOut();
  }
}
