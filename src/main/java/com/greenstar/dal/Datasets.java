package com.greenstar.dal;

import java.util.List;

public class Datasets {
    String label;
    List<Double> data;
    String backgroundColor;
    String borderColor;
    String pointBackgroundColor;
    String pointBorderColor;
    String pointHoverBackgroundColor;
    String pointHoverBorderColor;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getPointBackgroundColor() {
        return pointBackgroundColor;
    }

    public void setPointBackgroundColor(String pointBackgroundColor) {
        this.pointBackgroundColor = pointBackgroundColor;
    }

    public String getPointBorderColor() {
        return pointBorderColor;
    }

    public void setPointBorderColor(String pointBorderColor) {
        this.pointBorderColor = pointBorderColor;
    }

    public String getPointHoverBackgroundColor() {
        return pointHoverBackgroundColor;
    }

    public void setPointHoverBackgroundColor(String pointHoverBackgroundColor) {
        this.pointHoverBackgroundColor = pointHoverBackgroundColor;
    }

    public String getPointHoverBorderColor() {
        return pointHoverBorderColor;
    }

    public void setPointHoverBorderColor(String pointHoverBorderColor) {
        this.pointHoverBorderColor = pointHoverBorderColor;
    }
}
