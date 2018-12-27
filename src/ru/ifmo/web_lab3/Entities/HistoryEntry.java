package ru.ifmo.web_lab3.Entities;

public class HistoryEntry {


    private double x;
    private double y;
    private double r;
    private boolean result;

    public HistoryEntry() {

        x = 0;
        y = 0;
        r = 0;
        result = false;
    }

    public HistoryEntry(double x, double y, double r, boolean result) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }


    public double getX() {
        return x;
    }

    public String getStrX() {
        return String.format("%.3g%n", x);
    }

    public double getY() {
        return y;
    }

    public String getStrY() {
        return String.format("%.3g%n", y);
    }

    public double getR() {
        return r;
    }

    public boolean getResult() {
        return result;
    }

    public String getResultStr() {
        return result ? "Попадает" : "Не попадает";
    }


}
