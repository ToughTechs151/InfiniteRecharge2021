/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

/**
 * 
 */
public class LauncherSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Talon launcher1 = new Talon(Constants.Launcher1);
  private static Talon launcher2 = new Talon(Constants.Launcher2);
  private static double setpoint = 0;
  private static SpeedControllerGroup launcher = new SpeedControllerGroup(launcher1, launcher2);
  private PIDSubsystem pid = new PIDSubsystem(RobotContainer.getPidController()) {

    @Override
    protected void useOutput(double output, double setpoint) {
      LauncherSubsystem.run(output);
      RobotContainer.getPidController().calculate(output, setpoint);

    }

    @Override
    protected double getMeasurement() {
      // TODO Auto-generated method stub
      return 0;
    }
  };

  public LauncherSubsystem() {

  }
  public void setSetpoint(double set){
    setpoint=set;
  }

  public void periodic() {
    pid.getController().calculate(launcher.get(), setpoint);
  }

  protected static void run(double output) {
    launcher.set(output);

  }
}
