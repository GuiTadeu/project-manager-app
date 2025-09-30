package com.xpto.app.dto;

import com.xpto.app.model.TeamMember;
import com.xpto.app.model.TeamPosition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TeamMemberDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Position is required")
    private TeamPosition position;

    public TeamMemberDTO(String name, TeamPosition position) {
        this.name = name;
        this.position = position;
    }

    public TeamMemberDTO(TeamMember teamMember) {
        this.name = teamMember.getName();
        this.position = teamMember.getPosition();
    }

    public TeamMember toEntity() {
        return new TeamMember(name, position);
    }
}
