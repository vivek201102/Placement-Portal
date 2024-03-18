package org.vivek.placementportal.service;

import org.vivek.placementportal.dto.ExperienceRegisterRequest;
import org.vivek.placementportal.models.Experience;

import java.util.List;

public interface ExperienceService {
    public Experience register(ExperienceRegisterRequest request);
    public Experience update(ExperienceRegisterRequest request, int id);
    public List<Experience> get(String studentId);
    public Experience delete(int id);
}
