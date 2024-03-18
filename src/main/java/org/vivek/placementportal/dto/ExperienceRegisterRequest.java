package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceRegisterRequest {
    private String title;
    private String organization;
    private String fromDate;
    private String toDate;
    private String description;
    private String studentId;
}
