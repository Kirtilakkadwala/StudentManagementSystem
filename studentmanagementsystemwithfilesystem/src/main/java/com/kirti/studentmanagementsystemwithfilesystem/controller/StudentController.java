package com.kirti.studentmanagementsystemwithfilesystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirti.studentmanagementsystemwithfilesystem.entity.StudentFileEntity;
import com.kirti.studentmanagementsystemwithfilesystem.service.StudentFileService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
    private StudentFileService studentFileService;

    @GetMapping
    public List<StudentFileEntity> getAllStudents() throws IOException {
        return studentFileService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentFileEntity getStudentById(@PathVariable Long id) throws IOException {
        return studentFileService.getStudentById(id);
    }

    @PostMapping
    public StudentFileEntity addStudent(@RequestBody StudentFileEntity student) throws IOException {
        return studentFileService.addStudent(student);
    }

    @PutMapping("/{id}")
    public StudentFileEntity updateStudent(@PathVariable Long id, @RequestBody StudentFileEntity student) throws IOException {
        return studentFileService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) throws IOException {
        studentFileService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
