package org.vivek.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.models.User;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Student findByUser(User user);
    public Student findByUserId(String id);
}
