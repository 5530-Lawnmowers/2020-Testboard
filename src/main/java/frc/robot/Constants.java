/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Climber motors
    public static final int CLIMB_L = 0;
    public static final int CLIMB_R = 1;

    //Drivetrain motors
    public static final int DT_R1 = 2;
    public static final int DT_R2 = 3;
    public static final int DT_R3 = 4;
    public static final int DT_L1 = 5;
    public static final int DT_L2 = 6;
    public static final int DT_L3 = 7;

    //Intake motors
    public static final int INTAKE = 8;
    public static final int DELIVERY = 9;

    //Intake sensors
    public static final int DELIVERY_S1 = 0;
    public static final int DELIVERY_S2 = 1;

    //Shooter motors
    public static final int SHOOTER_1 = 10;
    public static final int SHOOTER_2 = 11;

    //Spinner motors
    public static final int SPIN = 12;

    //Spinner color sensor
    public static final I2C.Port I2C_PORT = I2C.Port.kOnboard;

}
