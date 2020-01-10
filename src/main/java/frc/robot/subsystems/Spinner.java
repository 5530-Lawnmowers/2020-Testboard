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

import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.ctre.phoenix.motorcontrol.can.*;

public class Spinner extends SubsystemBase {
  private final WPI_TalonSRX spinner = new WPI_TalonSRX(Constants.SPIN);
  private final ColorSensorV3 colorSensor = new ColorSensorV3(Constants.I2C_PORT);

  /**
   * Creates a new Spinner.
   */
  public Spinner() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // In theory, this should schedule the command to output the RGB values.
    CommandScheduler.getInstance().schedule(new OutputRGB(this));
  }

  public Color getColor() {
    return colorSensor.getColor();
  }
}
