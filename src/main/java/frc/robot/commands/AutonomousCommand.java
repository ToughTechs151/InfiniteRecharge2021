package frc.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class AutonomousCommand extends CommandGroupBase {
   private DriveSubsystem drive;
   private LauncherSubsystem launcherSubsystem;
   private LimeLightSubsystem lime;
   private HopperSubsystem hopperSubsystem;
   private DriveSoloCommand solo;
   private CommandGroupBase auto;
   private boolean done=false;
   public AutonomousCommand(DriveSubsystem drive, LauncherSubsystem launcherSubsystem,LimeLightSubsystem lime, HopperSubsystem hopperSubsystem) {
      this.drive = drive;
      this.launcherSubsystem = launcherSubsystem;
      this.lime=lime;
      this.hopperSubsystem=hopperSubsystem;
      addRequirements(drive);
      addRequirements(launcherSubsystem);
      addRequirements(lime);
      addRequirements(hopperSubsystem);
      solo=new DriveSoloCommand(drive, lime, 0.5, 0.5, 140);
      addCommands(new ChangeLauncherSpeedCommand(3000,launcherSubsystem),solo,new AdjustLauncherCommand(launcherSubsystem, lime),new HopperCommand(hopperSubsystem, Constants.HOPPER_SPEED));
   }

   public void inititialize(){
   }

   @Override
   public void addCommands(Command... commands) {
      auto=sequence(commands);
   }
   public void execute(){
      if(!done)
      auto.schedule();
      done=true;
   }
   public boolean isFinished(){
      return done;
   }
   public void end(boolean interrupted){
      
   }

 }