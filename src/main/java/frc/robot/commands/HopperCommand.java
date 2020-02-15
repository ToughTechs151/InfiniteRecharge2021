/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.HopperSubsystem;
/**
 * Command to feed Launcher
 */
public class HopperCommand extends CommandBase {
  private HopperSubsystem m_hopperSubsystem;
  private Joystick coDrive = new Joystick(9);
  private Timer time;
  private TimerTask task;
  private boolean prevState=true;
  private double speed;
  private boolean auto=false;
  /**
   * hopper command constructor
   * @param subsystem the hopper subsystem
   * @param speed the speed of the hopper(%)
   * @param coDrive the codrivers input
   */
  public HopperCommand(HopperSubsystem subsystem, double speed, Joystick coDrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    this.coDrive=coDrive;
    m_hopperSubsystem = subsystem;
    addRequirements(subsystem);
    time=new Timer();
    auto=false;
    
  }


  public HopperCommand(HopperSubsystem hopperSubsystem, double hopperSpeed) {
    speed=hopperSpeed;
    m_hopperSubsystem=hopperSubsystem;
    addRequirements(hopperSubsystem);
    time=new Timer();
    auto=true;
}


// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  /**
   * called when the command is scheduled
   */
  @Override
  public void execute() {
    if(!auto&&coDrive.getRawButton(Constants.LEFT_BUMPER)){
      if(!m_hopperSubsystem.getHopperSwitch2()){
        m_hopperSubsystem.start(0-speed);
      }
      else 
        m_hopperSubsystem.stop();
    }
    else{
      //check for ready ball and command to launch
      if(auto||(coDrive.getRawButton(Constants.RIGHT_BUMPER)&&!m_hopperSubsystem.getHopperSwitch2())){
        if(prevState){
          prevState=false;
          m_hopperSubsystem.stop();
          time.schedule(task=new TimerTask(){
            @Override
            public void run() {
              m_hopperSubsystem.start(speed);
              time.schedule(task=new TimerTask(){
                @Override
                public void run() {
                  
                  prevState=true;
                }
              },200);
            }
          },500);
        }
      }
      //check to see if the hopper should stop
      else if((!coDrive.getRawButton(Constants.RIGHT_BUMPER)&&m_hopperSubsystem.getHopperSwitchState())||!m_hopperSubsystem.getHopperSwitch2())
        m_hopperSubsystem.stop();
        if(!m_hopperSubsystem.getHopperSwitch2()){

        }
      
      //check for intent to launch or if there is a ball to intake without forcing into the launcher
      else if((coDrive.getRawButton(Constants.RIGHT_BUMPER))||(!m_hopperSubsystem.getHopperSwitchState()&&m_hopperSubsystem.getHopperSwitch2())){
        m_hopperSubsystem.start(speed);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hopperSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
