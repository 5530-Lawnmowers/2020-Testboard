/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.helpers.ShuffleboardHelpers;
import frc.robot.subsystems.Shooter;

public class ShooterMotorTest extends CommandBase {
  private double setSpeed = 0.4;
  private Shooter shooter;
  
  /**
   * Creates a new ShooterMotorTest.
   */
  public ShooterMotorTest(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setSpeed = (double) ShuffleboardHelpers.getWidgetValue("Test", "Shooter Set Speed");
    shooter.testShooterSet(setSpeed);
    ShuffleboardHelpers.setWidgetValue("Test", "Shooter Test Status", "Running");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.testShooterStop();
    ShuffleboardHelpers.setWidgetValue("Test", "Shooter Test Status", "Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
