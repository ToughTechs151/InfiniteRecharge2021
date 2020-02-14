
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;
/**
 * The command to automatically set the launcher speed based on distance to target
 */
public class AdjustLauncherCommand extends CommandBase {
    private boolean fin=false;
    private double setspeed;
    private LauncherSubsystem mLauncher;
    private LimeLightSubsystem mLime;
    /**
     * The command to automatically set the launcher speed based on distance to target
     * @param launcher
     * @param lime
     */
    public AdjustLauncherCommand(LauncherSubsystem launcher,LimeLightSubsystem lime) {
        mLauncher=launcher;
        mLime=lime;
        addRequirements(mLauncher);
        addRequirements(mLime);
    }
    public void initialize(){

    }
    /**
     * changes the input based off of the best fit trendline of data collected
     * @param speed the distance
     */
    public void changeSpeed(double speed){
        setspeed =335*Math.pow(speed,0.404);
    }
    /**
     * called when the command is scheduled
     */
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