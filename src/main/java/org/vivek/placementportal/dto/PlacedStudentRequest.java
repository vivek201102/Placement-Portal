package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacedStudentRequest {
    private List<StudentOfferRequest> offers;
    private int placementDriveId;
}
