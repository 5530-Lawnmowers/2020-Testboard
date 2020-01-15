/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //These are all in flux pending final robot design

    //Climber motors
    public static final int CLIMB_L = 5;
    public static final int CLIMB_R = 2;

    //Drivetrain motors
    public static final int DT_R1 = 9;
    public static final int DT_R2 = 10;
    public static final int DT_L1 = 11;
    public static final int DT_L2 = 12;

    //Intake motors
    public static final int INTAKE = 8;
    public static final int DELIVERY = 4;

    //Intake sensors
    public static final int DELIVERY_S1 = 0;
    public static final int DELIVERY_S2 = 1;

    //Shooter motors
    public static final int SHOOTER_1 = 6;
    public static final int SHOOTER_2 = 3;

    //Spinner motors
    public static final int SPIN = 7;

    //Spinner color sensor
    public static final I2C.Port I2C_PORT = I2C.Port.kOnboard;


    //Color sensor constants
    public static final Color blueTarget = ColorMatch.makeColor(0.150, 0.460, 0.351);
    public static final Color greenTarget = ColorMatch.makeColor(0.189, 0.552, 0.246);
    public static final Color redTarget = ColorMatch.makeColor(0.462, 0.381, 0.161);
    public static final Color yellowTarget = ColorMatch.makeColor(0.323, 0.544, 0.128);

}
