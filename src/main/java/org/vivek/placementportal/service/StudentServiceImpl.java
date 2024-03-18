package org.vivek.placementportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.models.User;
import org.vivek.placementportal.repository.StudentRepository;
import org.vivek.placementportal.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public Student register(User user) {
        Student student = Student.builder().user(user).isPlaced(false).build();
        studentRepository.save(student);
        return student;
    }

    @Override
    public List<User> getAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(Student::getUser).toList();
    }

    @Override
    public User delete(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Student does not exist"));
        Student student = studentRepository.findByUser(user);
        studentRepository.delete(student);
        userRepository.delete(user);
        return user;
    }

    @Override
    public User getStudent(String studentId) {
        return studentRepository.findByUserId(studentId).getUser();
    }

    @Override
    public Student getStudentData(String studentId) {
        return studentRepository.findByUserId(studentId);
    }
}
