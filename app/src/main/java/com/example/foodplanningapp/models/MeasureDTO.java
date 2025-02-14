package com.example.foodplanningapp.models;

public class MeasureDTO {
    String title;
    String measure;

    public MeasureDTO(String title, String measure) {
        this.title = title;
        this.measure = measure;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
