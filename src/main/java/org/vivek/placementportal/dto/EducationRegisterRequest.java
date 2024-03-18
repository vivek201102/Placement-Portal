package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationRegisterRequest {
    private String degree;
    private String fieldOfStudy;
    private String institute;
    private String fromDate;
    private String toDate;
    private String result;
    private String studentId;
}
