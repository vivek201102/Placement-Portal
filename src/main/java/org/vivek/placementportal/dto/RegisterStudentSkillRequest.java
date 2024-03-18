package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterStudentSkillRequest {
    private String studentId;
    private int skillId;
}
