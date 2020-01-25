/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;
import frc.robot.helpers.ShuffleboardHelpers;

public class TurretMotorTest extends CommandBase {
    private Turret turret;
    private boolean clockwise;
    private double setSpeed = 0.1;

    /**
     * Creates a new TurretMotorTest.
     */
    public TurretMotorTest(Turret turret, boolean clockwise) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.turret = turret;
        this.clockwise = clockwise;
        addRequirements(turret);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        setSpeed = (double) ShuffleboardHelpers.getWidgetValue("Test", "Turret Set Speed");
        setSpeed *= clockwise ? 1 : -1;
        turret.testTurretSet(setSpeed);
        ShuffleboardHelpers.setWidgetValue("Test", "Turret Test Status", "Running");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        turret.testTurretStop();
        ShuffleboardHelpers.setWidgetValue("Test", "Turret Test Status", "Ended");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return turret.testLimit();
    }
}
