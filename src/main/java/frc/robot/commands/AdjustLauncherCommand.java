
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class AdjustLauncherCommand extends CommandBase {
    private boolean fin=false;
    private double setspeed;
    private LauncherSubsystem mLauncher;
    private LimeLightSubsystem mLime;
    public AdjustLauncherCommand(LauncherSubsystem launcher,LimeLightSubsystem lime) {
        mLauncher=launcher;
        mLime=lime;
        addRequirements(mLauncher);
        addRequirements(mLime);
    }
    public void initialize(){

    }
    public void changeSpeed(double speed){
        setspeed = speed;
    }
    public void execute(){
        changeSpeed(mLime.returnD());
        mLauncher.setSetpoint(setspeed);
        fin=true;
    }
    @Override
    public boolean isFinished() {
        return fin;
    }
    @Override
    public void end(boolean interrupted) {
    }
}