package org.vivek.placementportal.repository;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.placementportal.models.PlacedStudent;
import org.vivek.placementportal.models.Student;

import java.util.List;

public interface PlacedStudentRepository extends JpaRepository<PlacedStudent, Integer> {
    public List<PlacedStudent> findAllByStudent(Student student);
    public List<PlacedStudent> findAllByPlacementDriveId(int id);
    public PlacedStudent findTopByOrderByOfferAmountDesc();
}
