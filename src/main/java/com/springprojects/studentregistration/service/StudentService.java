package com.springprojects.studentregistration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springprojects.studentregistration.domain.StudentDomain;
import com.springprojects.studentregistration.dto.StudentDTO;
import com.springprojects.studentregistration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDomain> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(StudentDTO studentDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        StudentDomain studentDomain = objectMapper.convertValue(studentDTO, StudentDomain.class);
        Optional<StudentDomain> studentOptional = studentRepository.findStudentByEmail(studentDomain.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");

        }
        studentRepository.save(studentDomain);
        System.out.println("student added");
    }

    public void deleteStudent(Long id) {
        Boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("id does not exists");

        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        StudentDomain studentDomain = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(studentDomain.getName(), name)) {
            studentDomain.setName(name);

        }
        if (email != null && email.length() > 0 && !Objects.equals(studentDomain.getEmail(), email)) {

            Optional<StudentDomain> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
            studentDomain.setEmail(email);

        }
    }
}
