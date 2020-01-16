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

public class IntakeMotorTest extends CommandBase {
  private final double speedSet = 0.4;
  private Intake intake;
  private boolean spark;
  /**
   * Creates a new SimpleMotorTest.
   */
  public IntakeMotorTest(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    spark = ((double) ShuffleboardHelpers.getWidgetValue("Test", "Intake Test Spark") == 1);
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.testIntakeSet(speedSet, spark);
    ShuffleboardHelpers.setWidgetValue("Test", "Intake Test Status", "Running");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.testIntakeStop();
    ShuffleboardHelpers.setWidgetValue("Test", "Intake Test Status", "Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
