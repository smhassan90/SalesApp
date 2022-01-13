package com.greenstar.dal;


import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 27th October, 2021
 */

public class BarChart {
    List<String> labels;
    List<Datasets> datasets;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Datasets> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<Datasets> datasets) {
        this.datasets = datasets;
    }
}
