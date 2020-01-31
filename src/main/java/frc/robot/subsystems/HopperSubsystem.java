/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class HopperSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final Talon hopperTurn;
  private DigitalInput hopperSwitch;
  public HopperSubsystem() {
    hopperTurn = new Talon(Constants.hopper);
    hopperSwitch = new DigitalInput(Constants.hSwitch);
  }
  public void start(double speed) {
    hopperTurn.set(-speed);
  }
  public void stop(){
    hopperTurn.set(0);
  }
  public boolean getHopperSwitchState() {
    return hopperSwitch.get();
  }
}
