package org.vivek.placementportal.service;

import org.vivek.placementportal.dto.PlacedStudentRequest;
import org.vivek.placementportal.models.PlacedStudent;

import java.util.List;

public interface PlacedStudentService {
    public void register(PlacedStudentRequest request);
    public List<PlacedStudent> getStudentOffer(String studentId);
}
