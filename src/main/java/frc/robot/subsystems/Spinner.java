/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.OutputRGB;
import frc.robot.helpers.ShuffleboardHelpers;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;

public class Spinner extends SubsystemBase {
  private final WPI_TalonSRX spinner = new WPI_TalonSRX(Constants.SPIN);
  private final ColorSensorV3 colorSensor = new ColorSensorV3(Constants.I2C_PORT);
  private final ColorMatch colorMatch = new ColorMatch();

  /**
   * Creates a new Spinner.
   */
  public Spinner() {
    colorMatch.addColorMatch(Constants.blueTarget);
    colorMatch.addColorMatch(Constants.greenTarget);
    colorMatch.addColorMatch(Constants.redTarget);
    colorMatch.addColorMatch(Constants.yellowTarget);
    spinner.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    spinner.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // In theory, this should schedule the command to output the RGB values.
    CommandScheduler.getInstance().schedule(new OutputRGB(this));
    ShuffleboardHelpers.setWidgetValue("Encoders", "Spinner", spinner.getSelectedSensorPosition());
  }

  /**
   * Get the color detected by the sensor
   * @return detected Color
   */
  public Color getColor() {
    return colorSensor.getColor();
  }

  /**
   * Get the result of the color match
   * @return matched ColorMathResult
   */
  public ColorMatchResult getColorMatch() {
    return colorMatch.matchClosestColor(colorSensor.getColor());
  }

  public void testSpinnerSet(double speed) {
    spinner.set(speed);
  }

  public void testSpinnerStop() {
    spinner.stopMotor();
  }
}
