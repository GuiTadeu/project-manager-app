package com.xpto.app.model;

import jakarta.persistence.*;

@Entity
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TeamPosition position;

    public TeamMember() {
    }

    public TeamMember(String name, TeamPosition position) {
        this.name = name;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TeamPosition getPosition() {
        return position;
    }
}
