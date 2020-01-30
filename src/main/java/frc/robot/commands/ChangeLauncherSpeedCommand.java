
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class ChangeLauncherSpeedCommand extends CommandBase{
    private double setspeed;
    private LauncherSubsystem mLauncher;
    public ChangeLauncherSpeedCommand(double speed, LauncherSubsystem launcher) {
        setspeed=speed;
        mLauncher=launcher;
        addRequirements(mLauncher);
    }
    public void execute(){
        mLauncher.setSetpoint(setspeed);
    }
}