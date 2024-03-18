package org.vivek.placementportal.service;

import org.vivek.placementportal.dto.SkillRegisterRequest;
import org.vivek.placementportal.models.Skill;

import java.util.List;

public interface SkillService {
    public Skill register(SkillRegisterRequest request);
    public List<Skill> getAll();
    public Skill findByName(String skillName);
}
