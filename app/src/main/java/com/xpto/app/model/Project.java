package com.xpto.app.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime expectedEndDate;
    private LocalDateTime endDate;
    private BigDecimal totalBudget;
    private String description;

    @ManyToOne
    private TeamMember manager;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
}
