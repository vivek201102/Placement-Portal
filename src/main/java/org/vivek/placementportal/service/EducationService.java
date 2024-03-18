package org.vivek.placementportal.service;


import org.vivek.placementportal.dto.EducationRegisterRequest;
import org.vivek.placementportal.models.Education;

import java.util.List;

public interface EducationService {
    public Education register(EducationRegisterRequest request);
    public Education update(EducationRegisterRequest request, int id);
    public Education delete(int id);
    public List<Education> get(String studentId);
}
