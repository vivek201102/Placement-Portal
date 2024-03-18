package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDriveRequest {
    private String companyName;
    private int CTC;
    private String description;
    private String jobRole;
    private String criteria;
    private String qualification;
    private Date deadlineForApplication;
}
