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

import java.sql.*;
import java.util.*;
import com.mysql.*;

/**
 * A mySQL helper for interacting with the debug database
 */
public class SQLHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "5530";
    private static final String PASS = "larry";

    private static Connection conn;
    private static ResultSet row;

    static {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the mySQL connection to the debug server
     * @throws SQLException
     */
    public static void openConnection() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * Closes the mySQL connection to the debug server
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        conn.close();
    }

    /**
     * Checks the mySQL connection to the debug server
     * @return <b>true</b> if open, <b>false</b> if closed
     * @throws SQLException
     */
    public static boolean isOpen() throws SQLException {
        return !conn.isClosed();
    }

    /**
     * Initializes a new <b>NETWORK_TABLES</b> under the <b>DEBUG_PLATFORM</b> database
     * <br>DELETES ALL EXISTING DATA. BACKUP DATA USING THE {@link #backupTable()} METHOD
     * @throws SQLException
     */
    public static void initTable() throws SQLException {
        Statement stmnt = conn.createStatement();
        stmnt.execute("SHOW TABLES LIKE 'NETWORK_TABLES'");
        ResultSet set = stmnt.getResultSet();
        if (set.next()) {
            stmnt.execute("DROP TABLE 'NETWORK_TABLES'");
        }
        stmnt.execute("CREATE TABLE 'NETWORK_TABLES'('Time' int)");
        stmnt.execute("SELECT * FROM 'NETWORK_TABLES'");
        if (row != null) row.getStatement().close();
        row = stmnt.getResultSet();
        row.moveToInsertRow();
    }

    /**
     * Adds a new column to the <b>NETWORK_TABLES</b> table
     * @param title Title of the new column
     * @param type Data type of the new column
     * @throws SQLException
     */
    public static void addColumn(String title, Class<?> type) throws SQLException {
        Statement stmnt = conn.createStatement();
        String colType;
        if (type == String.class) {
            colType = "varchar(255)";
        } else if (type == int.class) {
            colType = "int";
        } else if (type == double.class) {
            colType = "double";
        } else if (type == boolean.class) {
            colType = "boolean";
        } else {
            throw new ClassCastException();
        }
        stmnt.execute("ALTER NETWORK_TABLES ADD `" + title + "` " + colType);
        stmnt.execute("SELECT * FROM 'NETWORK_TABLES'");
        row.getStatement().close();
        row = stmnt.getResultSet();
        row.moveToInsertRow();
    }

    /**
     * Deletes a column from the <b>NETWORK_TABLES</b> table
     * @param title Title of the column to be deleted
     * @throws SQLException
     */
    public static void delColumn(String title) throws SQLException {
        Statement stmnt = conn.createStatement();
        stmnt.execute("ALTER NETWORK_TABLES DROP `" + title + "`");
        stmnt.execute("SELECT * FROM 'NETWORK_TABLES'");
        row.getStatement().close();
        row = stmnt.getResultSet();
        row.moveToInsertRow();
    }

    /**
     * Gets the data type of a column from the <b>NETWORK_TABLES</b> table
     * @param title Title of the column to get
     * @return The data type of the column
     * @throws SQLException
     */
    public static Class<?> getType(String title) throws SQLException {
        Statement stmnt = conn.createStatement();
        stmnt.execute("SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'NETWORK_TABLES' AND COLUMN_NAME = '" + title + "';");
        ResultSet result = stmnt.getResultSet();
        String type = result.getString(0);
        result.close();
        stmnt.close();
        switch (type) {
            case "varchar":
                return String.class;
            case "int":
                return int.class;
            case "double":
                return double.class;
            case "boolean":
                return boolean.class;
        }
        throw new ClassCastException();
    }

    /**
     * Get the a column from the <b>NETWORK_TABLES</b> table
     * @param title Title of the column to get
     * @return The result set of the column
     * @throws SQLException
     */
    public static ResultSet getColumn(String title) throws SQLException {
        Statement stmnt = conn.createStatement();
        stmnt.execute("SELECT '" + title + "' FROM 'NETWORK_TABLES'");
        return stmnt.getResultSet();
    }

    /**
     * Get a generic object array of a column's values
     * @param title Title of the column to get
     * @return A generic object array list of column values
     * @throws SQLException
     */
    public static ArrayList<Object> getGenericCol(String title) throws SQLException {
        ResultSet set = getColumn(title);
        ArrayList<Object> arr = new ArrayList<Object>();
        while (set.next()) {
            arr.add(set.getObject(1));
        }
        set.getStatement().close();
        return arr;
    }

    /**
     * Get a String array of a column's values
     * @param title Title of the column to get
     * @return A String array list of column values
     * @throws SQLException
     * @throws ClassCastException if the data type is not of type <b>String</b>
     */
    public static ArrayList<String> getStringCol(String title) throws SQLException {
        ResultSet set = getColumn(title);
        Class<?> type = getType(title);
        if (type != String.class) {
            throw new ClassCastException();
        }
        ArrayList<String> arr = new ArrayList<String>();
        while (set.next()) {
            arr.add(set.getString(1));
        }
        set.getStatement().close();
        return arr;
    }

    /**
     * Get an Integer array of a column's values
     * @param title Title of the column to get
     * @return An Integer array list of column values
     * @throws SQLException
     * @throws ClassCastException if the data type is not of type <b>int</b>
     */
    public static ArrayList<Integer> getIntCol(String title) throws SQLException {
        ResultSet set = getColumn(title);
        Class<?> type = getType(title);
        if (type != Integer.class) {
            throw new ClassCastException();
        }
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while (set.next()) {
            arr.add(set.getInt(1));
        }
        set.getStatement().close();
        return arr;
    }

    /**
     * Get a Double array of a column's values
     * @param title Title of the column to get
     * @return A Double array list of column values
     * @throws SQLException
     * @throws ClassCastException if the data type is not of type <b>double</b>
     */
    public static ArrayList<Double> getDoubleCol(String title) throws SQLException {
        ResultSet set = getColumn(title);
        Class<?> type = getType(title);
        if (type != Double.class) {
            throw new ClassCastException();
        }
        ArrayList<Double> arr = new ArrayList<Double>();
        while (set.next()) {
            arr.add(set.getDouble(1));
        }
        set.getStatement().close();
        return arr;
    }

    /**
     * Get a Boolean array of a column's values
     * @param title Title of the column to get
     * @return A Boolean array list of column values
     * @throws SQLException
     * @throws ClassCastException if the data type is not of type <b>boolean</b>
     */
    public static ArrayList<Boolean> getBooleanCol(String title) throws SQLException {
        ResultSet set = getColumn(title);
        Class<?> type = getType(title);
        if (type != boolean.class) {
            throw new ClassCastException();
        }
        ArrayList<Boolean> arr = new ArrayList<Boolean>();
        while (set.next()) {
            arr.add(set.getBoolean(1));
        }
        set.getStatement().close();
        return arr;
    }

    /**
     * Update a column's value in the insert row
     * @param column Title of the column to get
     * @param value The value to update to
     * @throws SQLException
     */
    public static void updateValue(String column, Object value) throws SQLException {
        row.moveToInsertRow();
        row.updateObject(column, value);
    }

    /**
     * Push the insert row to the table
     * @throws SQLException
     */
    public static void pushRow() throws SQLException {
        row.moveToInsertRow();
        row.insertRow();
        row.moveToInsertRow();
    }

    /**
     * Backup the <b>NETWORK_TABLES</b> table on the debug server
     * @return The ID of the new table
     * @throws SQLException
     */
    public static String backupTable() throws SQLException {
        Statement stmnt = conn.createStatement();
        String time = new java.util.Date().toString();
        stmnt.execute("CREATE TABLE IF NOT EXISTS '" + time + "' LIKE 'NETWORK_TABLES';");
        stmnt.execute("INSERT '" + time + "' SELECT * FROM 'NETWORK_TABLES'");
        stmnt.close();
        return time;
    }
}
