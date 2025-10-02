package com.xpto.app.controller;

import com.xpto.app.dto.ProjectDTO;
import com.xpto.app.dto.ReportDTO;
import com.xpto.app.model.Project;
import com.xpto.app.model.ProjectStatus;
import com.xpto.app.model.TeamMember;
import com.xpto.app.repository.ProjectRepository;
import com.xpto.app.repository.TeamMemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@RestController("/report")
public class ReportController {

    private final ProjectRepository projectRepository;
    private final TeamMemberRepository teamMemberRepository;

    public ReportController(ProjectRepository projectRepository, TeamMemberRepository teamMemberRepository) {
        this.projectRepository = projectRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @GetMapping
    public ResponseEntity<ReportDTO> generate() {

        ReportDTO report = new ReportDTO();

        // Projects by Status
        for (ProjectStatus status : ProjectStatus.values()) {
            Integer count = projectRepository.countByProjectStatus(status);
            report.putCountProjectsByStatus(status, count);
        }

        // Total Budget by Status
        for (ProjectStatus status : ProjectStatus.values()) {
            BigDecimal totalBudget = projectRepository.sumOfProjectsByStatus(status);
            report.putTotalBudgetByProjectStatus(status, totalBudget);
        }

        // Project Duration Average
        List<Project> allProjects = projectRepository.findAll();
        Double averageDurationOfCompletedProjects = allProjects.stream()
                .filter(Project::isFinished)
                .mapToDouble(p -> Duration.between(p.getStartDate(), p.getEndDate()).toDays())
                .average()
                .orElse(0.0);
        report.setAverageDurationOfCompletedProjects(averageDurationOfCompletedProjects);

        // Total allocated unique members
        List<TeamMember> allMembers = teamMemberRepository.findAll();
        Long totalAllocatedUniqueMembers = allMembers.stream()
                .filter(TeamMember::isOnAProject)
                .count();
        report.setTotalAllocatedUniqueMembers(totalAllocatedUniqueMembers);

        return ResponseEntity.ok(report);
    }
}
