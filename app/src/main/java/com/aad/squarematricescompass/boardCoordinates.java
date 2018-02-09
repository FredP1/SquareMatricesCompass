package com.aad.squarematricescompass;

/**
 * Created by Fred on 09/02/2018.
 */

public class boardCoordinates {
    private int viewID;
    private int xValue;
    private int yValue;
    private String firstDirection;
    private String secondDirection;

    public void setViewID(int ID){
        this.viewID = ID;
    }
    public void setX(int x){
        setFirstDirection1();
        this.xValue = x;
    }
    public void setY(int y){
        setSecondDirection1();
        this.yValue = y;
    }
    public void setFirstDirection(String x){
        this.firstDirection = x;
    }
    public void setSecondDirection(String x){
        this.secondDirection = x;
    }
    public int getViewID(){
        return this.viewID;
    }
    public int getXValue(){
        return this.xValue;
    }
    public int getYValue(){
        return this.yValue;
    }
    public String getFirstDirection(){
        return this.firstDirection;
    }
    public String getSecondDirection(){
        return this.secondDirection;
    }

    private void setFirstDirection1(){
        if (this.getXValue() == 310){
            this.setFirstDirection("SE");
        }
        else if (this.getXValue() == 620){
            this.setFirstDirection("NE");
        }
        else if (this.getXValue() == 930){
            this.setFirstDirection("SW");
        }
        else if (this.getXValue() == 1240){
            this.setFirstDirection("E");
        }
    }
    private void setSecondDirection1(){
        if (this.getYValue() == 486){
            this.setSecondDirection("W");
        }
        else if (this.getYValue() == 796){
            this.setSecondDirection("NW");
        }
        else if (this.getYValue() == 1106){
            this.setSecondDirection("N");
        }
        else if (this.getYValue() == 1416){
            this.setSecondDirection("S");
        }
    }
}
