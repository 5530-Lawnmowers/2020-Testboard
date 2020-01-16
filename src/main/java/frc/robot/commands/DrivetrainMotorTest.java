/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.helpers.ShuffleboardHelpers;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainMotorTest extends CommandBase {
  private final double setSpeed = 0.7;
  private Drivetrain drivetrain;

  /**
   * Creates a new DrivetrainMotorTest.
   */
  public DrivetrainMotorTest(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.testDrivetrainSet(setSpeed, "L1");
    drivetrain.testDrivetrainSet(setSpeed, "L2");
    drivetrain.testDrivetrainSet(setSpeed, "R1");
    drivetrain.testDrivetrainSet(setSpeed, "R2");
    ShuffleboardHelpers.setWidgetValue("Test", "Drivetrain Test Status", "Running");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.testDrivetrainStop();
    ShuffleboardHelpers.setWidgetValue("Test", "Drivetrain Test Status", "Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
