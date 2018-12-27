package ru.ifmo.web_lab3.Beans;

import javax.persistence.*;

@Table(name = "WEB_LAB3_HISTORY")
@Entity
public class Points {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private  Long ID;
    @Column(name = "X")
    private Double X;
    @Column(name = "Y")
    private Double Y;
    @Column(name = "R")
    private Double R;
    @Column(name = "RES")
    private Boolean RES;



    public Points(){

    }
    public Points(Double x, Double y, Double r, Boolean res){
        this.X = x;
        this.Y = y;
        this.R = r;
        this.RES = res;
    }

    public void setID(Long ID){
        this.ID = ID;
    }

    public void setX(Double x) {
        this.X = x;
    }

    public void setY(Double y) {
        this.Y = y;
    }

    public void setR(Double r) {
        this.R = r;
    }

    public void setRES(Boolean RES) {
        this.RES = RES;
    }

    public Long getID() {
        return ID;
    }

    public Double getX() {
        return X;
    }

    public Double getY() {
        return Y;
    }

    public Double getR() {
        return R;
    }

    public Boolean getRES() {
        return RES;
    }

    public String getStrX() {
        return String.format("%.3g%n", X);
    }

    public String getStrY() {
        return String.format("%.3g%n", Y);
    }

    public boolean getResult() {
        return getRES();
    }

    public String getResultStr() {
        return getRES() ? "Попадает" : "Не попадает";
    }


    @Override
    public int hashCode(){
        int hash = ID.hashCode();
        hash = 13*hash + X.hashCode();
        hash = 13*hash + Y.hashCode();
        hash = 13*hash + R.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Points))
            return false;
        Points point = (Points)o;
        if(!this.X.equals(point.X))
            return false;
        if(!this.Y.equals(point.Y))
            return false;
        if(!this.R.equals(point.R))
            return false;
        return true;
    }
}

