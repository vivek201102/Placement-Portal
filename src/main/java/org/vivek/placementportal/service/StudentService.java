package org.vivek.placementportal.service;

import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.models.User;

import java.util.List;

public interface StudentService {
    public Student register(User user);
    public List<User> getAll();
    public User delete(String id);
    public User getStudent(String studentId);
    public Student getStudentData(String studentId);
}
