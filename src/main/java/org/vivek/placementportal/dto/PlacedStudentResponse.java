package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vivek.placementportal.models.DriveApplication;
import org.vivek.placementportal.models.PlacedStudent;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlacedStudentResponse {
    private DriveApplication driveApplication;
    private List<PlacedStudent> placedStudents;
}
