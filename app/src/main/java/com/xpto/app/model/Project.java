package com.xpto.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Project {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime expectedEndDate;
    private LocalDateTime endDate;
    private BigDecimal totalBudget;
    private String description;
    private TeamMember manager;
    private ProjectStatus projectStatus;
}
