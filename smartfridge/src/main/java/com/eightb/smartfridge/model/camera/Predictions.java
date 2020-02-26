package com.eightb.smartfridge.model.camera;

import java.util.List;

public class Predictions {
    private List<String> labels;
    private double similarity;

    public Predictions(List<String> labels, double similarity) {
        this.labels = labels;
        this.similarity = similarity;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
}
