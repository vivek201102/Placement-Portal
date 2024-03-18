package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterApplicationRequest {
    private String studentId;
    private int driveId;
    private String studentStatus;
}
