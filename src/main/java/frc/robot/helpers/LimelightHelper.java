/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.helpers;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * Add your docs here.
 */
public class LimelightHelper {
    public void getRawY(){
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry ty = table.getEntry("ty");
        double y = ty.getDouble(0.0);
        System.out.println(y);
      }public class limelightHelper {
lic void getRawY()
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry t = table.getEntry("y");
        double  = t.getDouble(0.0);
        System.out.println();

      public static void getRawX(){
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("xy");
        double x = tx.getDouble(0.0);
        System.out.println(x);
      }
      public static double getDistance() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry ty = table.getEntry("ty");
        double y = ty.getDouble(0.0);
        double distance = 23.2576688011 + Math.abs(y);
        distance = Math.toRadians(distance);
        distance = Math.tan(distance);
        distance = 41 / distance;
        return distance;
    }

    public static double getTargetX(double angle) {
        return getDistance() * Math.cos(angle);
