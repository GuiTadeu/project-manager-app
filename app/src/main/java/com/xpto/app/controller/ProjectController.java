package com.xpto.app.controller;

import com.xpto.app.dto.ProjectDTO;
import com.xpto.app.dto.TeamMemberDTO;
import com.xpto.app.model.Project;
import com.xpto.app.model.TeamMember;
import com.xpto.app.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectDTO projectDTO) {
        Project project = projectDTO.toEntity();
        projectRepository.save(project);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(project.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<ProjectDTO> search(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent()) {
            return ResponseEntity.ok(new ProjectDTO(project.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProjectDTO> update(@PathVariable Long id, @RequestBody @Valid ProjectDTO projectDTO) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Project projectToUpdate = project.get();
        Project updatedProject = projectDTO.update(projectToUpdate);
        projectRepository.save(updatedProject);

        return ResponseEntity.ok(new ProjectDTO(updatedProject));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        projectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
