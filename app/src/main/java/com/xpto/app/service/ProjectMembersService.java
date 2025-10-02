package com.xpto.app.service;

import com.xpto.app.exception.BadRequestException;
import com.xpto.app.exception.NotFoundException;
import com.xpto.app.model.Project;
import com.xpto.app.model.TeamMember;
import com.xpto.app.repository.ProjectRepository;
import com.xpto.app.repository.TeamMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProjectMembersService {

    private final ProjectRepository projectRepository;
    private final TeamMemberRepository teamMemberRepository;

    public ProjectMembersService(ProjectRepository projectRepository, TeamMemberRepository teamMemberRepository) {
        this.projectRepository = projectRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @Transactional
    public Project associate(Long projectId, Long teamMemberId) {

        // Only employee members
        TeamMember teamMember = teamMemberRepository.findById(teamMemberId).orElseThrow(NotFoundException::new);
        if (teamMember.isNotEmployee()) {
            throw new BadRequestException();
        }

        // Each project min 1, max 10 members
        Project project = projectRepository.findById(projectId).orElseThrow(NotFoundException::new);
        int projectMemberSize = project.getMembers().size();
        if (projectMemberSize == 10) {
            throw new BadRequestException();
        }

        // A member cannot be allocated to +3 projects
        // with a status other than closed or canceled
        if (teamMember.isOveremployed()) {
            throw new BadRequestException();
        }

        project.addMember(teamMember);
        projectRepository.save(project);

        return project;
    }
}
