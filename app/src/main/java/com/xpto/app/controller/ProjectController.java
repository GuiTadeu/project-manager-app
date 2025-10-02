package com.xpto.app.controller;

import com.xpto.app.dto.ProjectDTO;
import com.xpto.app.dto.UpdateWorkflowDTO;
import com.xpto.app.exception.NotFoundException;
import com.xpto.app.model.Project;
import com.xpto.app.model.ProjectStatus;
import com.xpto.app.repository.ProjectRepository;
import com.xpto.app.service.ProjectMembersService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectMembersService projectMembersService;

    public ProjectController(ProjectRepository projectRepository, ProjectMembersService projectMembersService) {
        this.projectRepository = projectRepository;
        this.projectMembersService = projectMembersService;
    }

    @PatchMapping("/{id}/workflow")
    public ResponseEntity<ProjectDTO> updateWorkflow(@PathVariable Long id, @RequestBody UpdateWorkflowDTO updateDTO) {

        Project project = projectRepository.findById(id).orElseThrow(NotFoundException::new);
        ProjectStatus currentStatus = project.getProjectStatus();

        for (ProjectStatus possibleNextStep : currentStatus.getNextSteps()) {
            if (updateDTO.getNewStatus() == possibleNextStep) {
                project.setProjectStatus(updateDTO.getNewStatus());
                projectRepository.save(project);
                return ResponseEntity.ok(new ProjectDTO(project));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{projectId}/associate/{teamMemberId}")
    public ResponseEntity<ProjectDTO> associateTeamMember(@PathVariable Long projectId, @PathVariable Long teamMemberId) {
        Project project = projectMembersService.associate(projectId, teamMemberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectDTO projectDTO) {
        Project project = projectDTO.toEntity();
        projectRepository.save(project);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(project.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> search(@PathVariable Long id) {
        Project project = projectRepository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(new ProjectDTO(project));
    }

    @PutMapping
    public ResponseEntity<ProjectDTO> update(@PathVariable Long id, @RequestBody @Valid ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id).orElseThrow(NotFoundException::new);
        Project updatedProject = projectDTO.update(project);
        projectRepository.save(updatedProject);
        return ResponseEntity.ok(new ProjectDTO(updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Project project = projectRepository.findById(id).orElseThrow(NotFoundException::new);

        if (project.canBeDeleted()) {
            projectRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
