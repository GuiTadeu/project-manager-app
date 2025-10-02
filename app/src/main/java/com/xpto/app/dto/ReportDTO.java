package com.xpto.app.dto;

import com.xpto.app.model.ProjectStatus;

import java.math.BigDecimal;
import java.util.Map;

public class ReportDTO {

    private Map<ProjectStatus, Integer> countProjectsByStatus;
    private Map<ProjectStatus, BigDecimal> totalBudgetByProjectStatus;
    private Double averageDurationOfCompletedProjects;
    private Long totalAllocatedUniqueMembers;

    public ReportDTO(Map<ProjectStatus, Integer> countProjectsByStatus,
         Map<ProjectStatus, BigDecimal> totalBudgetByProjectStatus,
         Double averageDurationOfCompletedProjects,
         Long totalAllocatedUniqueMembers
    ) {
        this.countProjectsByStatus = countProjectsByStatus;
        this.totalBudgetByProjectStatus = totalBudgetByProjectStatus;
        this.averageDurationOfCompletedProjects = averageDurationOfCompletedProjects;
        this.totalAllocatedUniqueMembers = totalAllocatedUniqueMembers;
    }

    public ReportDTO() {
    }

    public void putCountProjectsByStatus(ProjectStatus projectStatus, Integer count) {
        this.countProjectsByStatus.put(projectStatus, count);
    }

    public void putTotalBudgetByProjectStatus(ProjectStatus projectStatus, BigDecimal totalBudget) {
        this.totalBudgetByProjectStatus.put(projectStatus, totalBudget);
    }

    public void setAverageDurationOfCompletedProjects(Double averageDurationOfCompletedProjects) {
        this.averageDurationOfCompletedProjects = averageDurationOfCompletedProjects;
    }

    public void setTotalAllocatedUniqueMembers(Long totalAllocatedUniqueMembers) {
        this.totalAllocatedUniqueMembers = totalAllocatedUniqueMembers;
    }
}
