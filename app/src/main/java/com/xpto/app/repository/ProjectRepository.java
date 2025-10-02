package com.xpto.app.repository;

import com.xpto.app.model.Project;
import com.xpto.app.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Integer countByProjectStatus(ProjectStatus status);

    @Query("SELECT SUM(p.totalBudget) FROM Project p WHERE p.projectStatus = ?1")
    BigDecimal sumOfProjectsByStatus(ProjectStatus status);
}
