package com.springprojects.studentregistration.controller;

import com.springprojects.studentregistration.domain.StudentDomain;
import com.springprojects.studentregistration.dto.StudentDTO;
import com.springprojects.studentregistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("data")
    public List<StudentDomain> getStudents() {
        return studentService.getStudents();

    }

    @PostMapping
    public void registerStudent(@RequestBody StudentDTO studentDTO){
        studentService.addNewStudent(studentDTO);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(studentId,name,email);

    }


}
