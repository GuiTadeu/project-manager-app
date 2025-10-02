package com.xpto.app.model;

import jakarta.persistence.*;

import java.util.List;

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

    @ManyToMany
    private List<Project> projects;

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

    public List<Project> getProjects() {
        return projects;
    }

    public Boolean isNotEmployee() {
        return position != TeamPosition.EMPLOYEE;
    }

    public Boolean isOveremployed() {
        long unfinishedProjectsCount = projects.stream().filter(Project::isUnfinished).count();
        return unfinishedProjectsCount >= 3;
    }

    public Boolean isOnAProject() {
        return projects.size() >= 1;
    }
}
