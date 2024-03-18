package org.vivek.placementportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.SkillRegisterRequest;
import org.vivek.placementportal.models.Skill;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.repository.SkillRepository;
import org.vivek.placementportal.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService{
    private final SkillRepository skillRepository;
    private final StudentRepository studentRepository;

    @Override
    public Skill register(SkillRegisterRequest request) {
        Skill skill = Skill
                .builder()
                .name(request.getSkillName())
                .build();
        skillRepository.save(skill);
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findByName(String skillName) {
        return skillRepository.findByName(skillName);
    }
}
