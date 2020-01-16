/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.helpers.ShuffleboardHelpers;
import frc.robot.subsystems.Climber;

public class ClimberMotorTest extends CommandBase {
private double setSpeed = 0.4;
private Climber climber;

  /**
   * Creates a new ClimberMotorTest.
   */
  public ClimberMotorTest(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setSpeed = (double) ShuffleboardHelpers.getWidgetValue("Test", "Climber Set Speed");
    climber.testClimbSet(setSpeed);
    ShuffleboardHelpers.setWidgetValue("Test", "Climber Test Status", "Running");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.testClimbStop();
    ShuffleboardHelpers.setWidgetValue("Test", "Climber Test Status", "Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
