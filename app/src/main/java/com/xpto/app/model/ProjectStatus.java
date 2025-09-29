package com.xpto.app.model;

public enum ProjectStatus {

    IN_ANALYSIS(0),
    FINISHED_ANALYSIS(1),
    APPROVED_ANALYSIS(2),
    STARTED_PROJECT(3),
    PLANNED_PROJECT(4),
    IN_PROGRESS(5),
    FINISHED(6),
    CANCELLED(-1);

    private Integer order;

    ProjectStatus(Integer order) {
        this.order = order;
    }

}
