/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DefaultDriveTest extends CommandBase {
    private Drivetrain drivetrain;

    private DoubleSupplier throttle;
    private DoubleSupplier turn;

    /**
     * Creates a new DefaultDriveTest.
     */
    public DefaultDriveTest(Drivetrain drivetrain, DoubleSupplier throttle, DoubleSupplier turn) {
        this.drivetrain = drivetrain;
        this.throttle = throttle;
        this.turn = turn;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrain.testDrive(throttle.getAsDouble(), turn.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.testDriveStop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
