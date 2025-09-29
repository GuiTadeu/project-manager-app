package com.xpto.app.dto;

import com.xpto.app.model.TeamMember;
import com.xpto.app.model.TeamPosition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TeamMemberCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Position is required")
    private TeamPosition position;

    public TeamMember toEntity() {
        return new TeamMember(name, position);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(TeamPosition position) {
        this.position = position;
    }
}
