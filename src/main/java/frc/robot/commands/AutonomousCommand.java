package frc.robot.commands;

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
   public AutonomousCommand(DriveSubsystem drive, LauncherSubsystem launcherSubsystem,LimeLightSubsystem lime) {
      this.drive = drive;
      this.launcherSubsystem = launcherSubsystem;
      this.lime=lime;
      addRequirements(drive);
      addRequirements(launcherSubsystem);
      addRequirements(lime);
   }

   public void inititialize(){
      addCommands(new DriveSoloCommand(drive, lime, 0.9, 0.9, 200));
      addCommands(new AdjustLauncherCommand(launcherSubsystem, lime));
      addCommands(new HopperCommand(hopperSubsystem, Constants.HOPPER_SPEED));
   }

   @Override
   public void addCommands(Command... commands) {
      sequence(commands);

   }

 }