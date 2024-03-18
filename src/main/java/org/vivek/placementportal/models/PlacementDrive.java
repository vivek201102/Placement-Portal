package org.vivek.placementportal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class PlacementDrive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String companyName;
    private int CTC;
    private String description;
    private String jobRole;
    private String criteria;
    private String qualification;
    private Date createdAt;
    private Date deadlineForApplication;
    private String status;
    @OneToMany(mappedBy = "placementDrive", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DriveApplication> driveApplications;
    @OneToMany(mappedBy = "placementDrive", cascade = CascadeType.ALL)
    private Set<PlacedStudent> placedStudents;
}
