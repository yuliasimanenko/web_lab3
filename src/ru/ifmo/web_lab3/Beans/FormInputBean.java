package ru.ifmo.web_lab3.Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FormInputBean implements Serializable {
    private HistoryBean historyBean;

    public void setHistoryBean(HistoryBean historyBean) {
        this.historyBean = historyBean;
    }

    private double x = -2;

    private double y = 0;
    private double r = 3;

    private String resultStr = "";


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String str) {
        resultStr = str;
    }


    public void performCheck() {
        boolean result = AreaCheckBean.isInArea(getX(), y, r);
        resultStr = result ? "Попадает в область" : "Не попадает в область";
        historyBean.addResult(getX(), y, r, result);
    }
}
