/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.helpers.ShuffleboardHelpers;

public class SimpleMotorTest extends CommandBase {
  private final double speedSet = 0.4;
  private Intake intake;
  private boolean spark;
  /**
   * Creates a new SimpleMotorTest.
   */
  public SimpleMotorTest(Intake intake, boolean isSpark) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    spark = isSpark;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.testboardTestingMove(speedSet, spark);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ShuffleboardHelpers.setWidgetValue("Color", "Test Status", "Running");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.testboardTestingStop();
    ShuffleboardHelpers.setWidgetValue("Color", "Test Status", "Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
