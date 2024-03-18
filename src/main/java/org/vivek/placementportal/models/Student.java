package org.vivek.placementportal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isPlaced;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<DriveApplication> driveApplications;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudentSkill> studentSkills;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<PlacedStudent> placedStudents;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Education> educations;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Experience> experiences;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Project> projects;
}
