package com.cydeo.dto;

import com.cydeo.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ProjectDTO {

    private Long id;
    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectDetail;
    private Status projectStatus;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int completeTaskCounts;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int unfinishedTaskCounts;
}
