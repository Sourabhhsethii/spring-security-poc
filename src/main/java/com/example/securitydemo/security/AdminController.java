package com.example.securitydemo.security;

import com.example.securitydemo.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class AdminController {

    private final List<Student> STUDENTS = Arrays.asList(
            Student.builder().studentId(1).studentName("Harish").build(),
            Student.builder().studentId(2).studentName("Manish").build()
    );

    @GetMapping("/managements/students/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','NEW_ADMIN')")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Integer studentId){
        return ResponseEntity.ok(STUDENTS
                .stream()
                .filter(s->studentId.equals(s.getStudentId()))
                .findFirst().orElseThrow(()-> new IllegalStateException()));
    }

    @PostMapping("/management/students")
    @PreAuthorize("hasAuthority('ADMIN','NEW_ADMIN')")
    public ResponseEntity<Student> getStudents(Student student){
        return ResponseEntity.ok().build();
    }
}
