package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRegisterRequest {
    private String title;
    private String description;
    private String technologies;
    private String sourceCodeUrl;
    private String url;
    private String studentId;
}
