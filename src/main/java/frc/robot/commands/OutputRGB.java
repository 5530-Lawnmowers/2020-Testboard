/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Spinner;
import frc.robot.helpers.ShuffleboardHelpers;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class OutputRGB extends InstantCommand {
  private Spinner spinner;

  public OutputRGB(Spinner spinner) {
    this.spinner = spinner;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(); //Doesn't actually require any part of the subsystem.
  }

  // Called when the command is initially scheduled. Sets widgets to detected value.
  @Override
  public void initialize() {
    ShuffleboardHelpers.setWidgetValue("Color", "Red", spinner.getColor().red);
    ShuffleboardHelpers.setWidgetValue("Color", "Green", spinner.getColor().green);
    ShuffleboardHelpers.setWidgetValue("Color", "Blue", spinner.getColor().blue);
  }
}
