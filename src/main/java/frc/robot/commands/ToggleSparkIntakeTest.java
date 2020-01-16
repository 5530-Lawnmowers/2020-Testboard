/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.helpers.ShuffleboardHelpers;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ToggleSparkIntakeTest extends InstantCommand {
  private double value;

  public ToggleSparkIntakeTest() {
    // Use addRequirements() here to declare subsystem dependencies.
    if ((double) ShuffleboardHelpers.getWidgetValue("Test", "Intake Test Spark") == 1) {
      value = 0;
    } else {
      value = 1;
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ShuffleboardHelpers.setWidgetValue("Test", "Intake Test Spark", value);
  }
}
