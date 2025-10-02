package com.xpto.app.model;

public enum ProjectStatus {

    CANCELLED(),
    FINISHED(CANCELLED),
    IN_PROGRESS(FINISHED, CANCELLED),
    PLANNED_PROJECT(IN_PROGRESS, CANCELLED),
    STARTED_PROJECT(PLANNED_PROJECT, CANCELLED),
    APPROVED_ANALYSIS(STARTED_PROJECT, CANCELLED),
    FINISHED_ANALYSIS(APPROVED_ANALYSIS, CANCELLED),
    IN_ANALYSIS(FINISHED_ANALYSIS, CANCELLED);

    private final ProjectStatus[] nextSteps;

    ProjectStatus(ProjectStatus... nextSteps) {
        this.nextSteps = nextSteps;
    }

    public ProjectStatus[] getNextSteps() {
        return nextSteps;
    }
}
