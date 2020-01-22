/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class HopperSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final WPI_TalonSRX hopperTurn;
  
  public HopperSubsystem() {
    hopperTurn = new WPI_TalonSRX(Constants.hopper);
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void start() {
    hopperTurn.set(1.0);
  }
  public void stop(){
    hopperTurn.set(0);
  }
}
