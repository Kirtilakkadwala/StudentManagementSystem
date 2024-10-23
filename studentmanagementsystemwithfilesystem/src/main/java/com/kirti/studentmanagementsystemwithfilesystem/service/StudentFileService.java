package com.kirti.studentmanagementsystemwithfilesystem.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirti.studentmanagementsystemwithfilesystem.entity.StudentFileEntity;

@Service
public class StudentFileService {
	//private static final String FILE_PATH = "src/main/resources/students.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    @Value("${student.filepath}")
    private String filePath;


    public List<StudentFileEntity> getAllStudents() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
        	file.createNewFile();
            Files.write(file.toPath(), "[]".getBytes());
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, StudentFileEntity.class));
    }

    public StudentFileEntity getStudentById(Long id) throws IOException {
        return getAllStudents().stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public StudentFileEntity addStudent(StudentFileEntity student) throws IOException {
        List<StudentFileEntity> students = getAllStudents();
        student.setId((long) (students.size() + 1));
        students.add(student);
        objectMapper.writeValue(new File(filePath), students);
        return student;
    }

    public StudentFileEntity updateStudent(Long id, StudentFileEntity updatedStudent) throws IOException {
        List<StudentFileEntity> students = getAllStudents();
        StudentFileEntity student = getStudentById(id);
        student.setName(updatedStudent.getName());
        student.setCourse(updatedStudent.getCourse());
        student.setEmail(updatedStudent.getEmail());
        objectMapper.writeValue(new File(filePath), students);
        return student;
    }

    public void deleteStudent(Long id) throws IOException {
        List<StudentFileEntity> students = getAllStudents();
        students.removeIf(student -> student.getId().equals(id));
        objectMapper.writeValue(new File(filePath), students);
    }
}
