package com.xpto.app.model;

import com.xpto.app.util.ProjectRiskLevel;
import com.xpto.app.util.RiskCalculator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime expectedEndDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private BigDecimal totalBudget;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private TeamMember manager;

    @ManyToMany
    @Size(min=1, max = 10)
    private List<TeamMember> members = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @Enumerated(EnumType.STRING)
    private ProjectRiskLevel projectRiskLevel;

    public Project(
        String name, LocalDateTime startDate, LocalDateTime expectedEndDate,
        LocalDateTime endDate, BigDecimal totalBudget, String description,
        TeamMember manager, ProjectStatus projectStatus
    ) {
        this.name = name;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.endDate = endDate;
        this.totalBudget = totalBudget;
        this.description = description;
        this.manager = manager;
        this.projectStatus = projectStatus;
    }

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getExpectedEndDate() {
        return expectedEndDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public String getDescription() {
        return description;
    }

    public TeamMember getManager() {
        return manager;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setExpectedEndDate(LocalDateTime expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManager(TeamMember manager) {
        this.manager = manager;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Boolean isUnfinished() {
        return projectStatus != ProjectStatus.CANCELLED && projectStatus != ProjectStatus.FINISHED;
    }

    public Boolean isFinished() {
        return projectStatus == ProjectStatus.FINISHED && endDate != null;
    }

    public Boolean addMember(TeamMember member) {
        return members.add(member);
    }

    public Boolean canBeDeleted() {
        return projectStatus != ProjectStatus.STARTED_PROJECT
                && projectStatus != ProjectStatus.IN_PROGRESS
                && projectStatus != ProjectStatus.FINISHED;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }
}
