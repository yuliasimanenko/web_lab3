package ru.ifmo.web_lab3.Beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="formInputLimits")
@ApplicationScoped
public class FormInputLimitsBean {
    //@ManagedProperty(name="allowedX", value="")
    private double[] allowedX = new double[] { -4, -3, -2, -1, 0, 1, 2 };
    //@ManagedProperty("allowedMinY")
    private double allowedMinY = -3;
    //@ManagedProperty("allowedMaxY")
    private double allowedMaxY = 5;
    //@ManagedProperty("allowedR")
    private double[] allowedR = new double[] { 1, 1.5, 2, 2.5, 3 };

    public double[] getAllowedX() {
        return allowedX;
    }

    public double getAllowedMinY() {
        return allowedMinY;
    }

    public double getAllowedMaxY() {
        return allowedMaxY;
    }

    public double[] getAllowedR() {
        return allowedR;
    }
}
