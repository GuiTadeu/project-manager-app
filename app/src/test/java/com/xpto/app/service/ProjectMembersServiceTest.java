package com.xpto.app.service;

import com.xpto.app.model.Project;
import com.xpto.app.model.TeamMember;
import com.xpto.app.model.TeamPosition;
import com.xpto.app.repository.ProjectRepository;
import com.xpto.app.repository.TeamMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectMembersServiceTest {

    private ProjectRepository projectRepository;
    private TeamMemberRepository teamMemberRepository;
    private ProjectMembersService service;

    @BeforeEach
    void setup() {
        projectRepository = mock(ProjectRepository.class);
        teamMemberRepository = mock(TeamMemberRepository.class);
        service = new ProjectMembersService(projectRepository, teamMemberRepository);
    }

    @Test
    void associateSuccess() {
        TeamMember member = new TeamMember();
        member.setPosition(TeamPosition.EMPLOYEE);
        member.setProjects(new ArrayList<>());

        Project project = new Project();
        project.setMembers(new ArrayList<>());

        when(teamMemberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Project result = service.associate(1L, 1L);

        assertTrue(result.getMembers().contains(member));
        verify(projectRepository).save(project);
    }
}