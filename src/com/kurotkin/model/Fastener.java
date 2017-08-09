package com.kurotkin.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vitaly Kurotkin on 27.06.2017.
 */
public class Fastener {
    public int id;
    public Date dat;
    public double torque;
    public boolean result;
    public int serno;
    public double nom_torque;
    public double tol_lower;
    public double tol_upper;
    public String name;
    public String tagName;

    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public int getId() {
        return id;
    }

    public String getDat() {
        return dateFormat.format(dat);
    }

    public double getTorque() {
        return torque;
    }

    public String isResult() {
        return result ? "Норма" : "Не норма";
    }

    public int getSerno() {
        return serno;
    }

    public double getNom_torque() {
        return nom_torque;
    }

    public double getTol_lower() {
        return tol_lower;
    }

    public double getTol_upper() {
        return tol_upper;
    }

    public String getName() {
        return name;
    }

    public String getTagName() {
        return tagName;
    }
}

