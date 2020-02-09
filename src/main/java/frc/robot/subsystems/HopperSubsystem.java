/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.hal.CANAPIJNI;
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
  private final TalonSRX hopperTurn;
  private DigitalInput hopperSwitch;
  private DigitalInput hopperSwitch2;
  public HopperSubsystem() {
    hopperTurn = new TalonSRX(Constants.HOPPER);
    hopperSwitch = new DigitalInput(Constants.HSWITCH);
    hopperSwitch2=new DigitalInput(Constants.HSWITCH2);
  }
  public void start(double speed) {
    hopperTurn.set(ControlMode.PercentOutput,speed);
  }
  public void stop(){
    hopperTurn.set(ControlMode.PercentOutput,0);
  }
  public boolean getHopperSwitchState() {
    return hopperSwitch.get();
  }
  public boolean getHopperSwitch2(){
    return hopperSwitch2.get();
  }
}
