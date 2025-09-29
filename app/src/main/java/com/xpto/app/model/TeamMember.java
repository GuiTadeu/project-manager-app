package com.xpto.app.model;

import jakarta.persistence.Entity;

@Entity
public class TeamMember {

    private String name;
    private TeamPosition position;

    public TeamMember(String name, TeamPosition position) {
        this.name = name;
        this.position = position;
    }
}
