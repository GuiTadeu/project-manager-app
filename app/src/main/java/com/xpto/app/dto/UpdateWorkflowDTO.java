package com.xpto.app.dto;

import com.xpto.app.model.ProjectStatus;
import jakarta.validation.constraints.NotBlank;

public class UpdateWorkflowDTO {

    @NotBlank(message = "NewStatus is required")
    ProjectStatus newStatus;

    public ProjectStatus getNewStatus() {
        return newStatus;
    }
}
