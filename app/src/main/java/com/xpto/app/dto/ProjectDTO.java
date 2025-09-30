package com.xpto.app.dto;

import com.xpto.app.model.Project;
import com.xpto.app.model.ProjectStatus;
import com.xpto.app.model.TeamMember;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProjectDTO {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime expectedEndDate;
    private LocalDateTime endDate;
    private BigDecimal totalBudget;
    private String description;
    private TeamMember manager;
    private ProjectStatus projectStatus;

    public ProjectDTO(Project project) {
        this.name = project.getName();
        this.startDate = project.getStartDate();
        this.expectedEndDate = project.getExpectedEndDate();
        this.endDate = project.getEndDate();
        this.totalBudget = project.getTotalBudget();
        this.description = project.getDescription();
        this.manager = project.getManager();
        this.projectStatus = project.getProjectStatus();
    }

    public Project toEntity() {
        return new Project(
            name, startDate, expectedEndDate, endDate,
            totalBudget, description, manager, projectStatus
        );
    }

    public Project update(Project project) {
        project.setName(this.name);
        project.setStartDate(this.startDate);
        project.setExpectedEndDate(this.expectedEndDate);
        project.setEndDate(this.endDate);
        project.setTotalBudget(this.totalBudget);
        project.setDescription(this.description);
        project.setManager(this.manager);
        project.setProjectStatus(this.projectStatus);
        return project;
    }
}