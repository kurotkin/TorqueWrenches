package com.kurotkin;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Vitaly Kurotkin on 27.06.2017.
 */
public class SQLreq {
    public static String url_1 () {
        String sqlUrl = "SELECT id,\n" +
                "       serno,\n" +
                "       time,\n" +
                "       max_torque,\n" +
                "       max_angle,\n" +
                "       sequence_id,\n" +
                "       (SELECT name FROM joint_setting WHERE id = joint_data.settings_id) AS name,\n" +
                "       settings_id,\n" +
                "       (SELECT name FROM joint_setting WHERE id = joint_data.settings_id) AS nameF,\n" +
                "       deleted,\n" +
                "       assembly_object,\n" +
                "       slot_id,\n" +
                "       seq_position,\n" +
                "       result,\n" +
                "       memid,\n" +
                "       error,\n" +
                "       result_torque,\n" +
                "       release_angle,\n" +
                "       (SELECT torque FROM joint_setting WHERE id = joint_data.settings_id) AS nom_torque," +
                "       (SELECT tol_lower FROM joint_setting WHERE id = joint_data.settings_id) AS setting_tol_lower," +
                "       (SELECT tol_upper FROM joint_setting WHERE id = joint_data.settings_id) AS setting_tol_upper" +
                "  FROM joint_data;";
        return sqlUrl;
    }

    public static ArrayList<Fastener> req() {
        ArrayList<Fastener> fasteners = new ArrayList<Fastener>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/" + Settings.sqliteUrl);
            Statement st = conn.createStatement();
            ResultSet resSet = st.executeQuery(url_1());
            while (resSet.next()) {
                Fastener newFast = new Fastener();
                newFast.serno = resSet.getInt("serno");

                int timeInt = resSet.getInt("time");
                long timeLong = (long) timeInt * 1000L;
                newFast.dat = new Date(timeLong);

                newFast.torque = (double) resSet.getInt("max_torque") / 1000.0;
                newFast.nom_torque = resSet.getDouble("nom_torque");
                newFast.tol_lower = newFast.torque - resSet.getDouble("setting_tol_lower");
                newFast.tol_upper = newFast.torque + resSet.getDouble("setting_tol_upper");
                newFast.name = resSet.getString("name");
                newFast.id = resSet.getInt("id");

                if (resSet.getInt("result") == 1) {
                    newFast.result = true;
                } else if (resSet.getInt("result") == 3) {
                    if ((newFast.torque > newFast.tol_lower) && (newFast.torque < newFast.tol_upper)) {
                        newFast.result = true;
                    } else {
                        newFast.result = false;
                    }
                }
                fasteners.add(newFast);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fasteners;
    }
}
