package com.kirti.studentmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirti.studentmanagementsystem.entity.Student;
import com.kirti.studentmanagementsystem.repository.StudentRepository;

@Service
public class StudentService {
	 @Autowired
	    private StudentRepository studentRepository;

	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    public Student getStudentById(Long id) {
	        return studentRepository.findById(id).orElse(null);
	    }
	    
	    public Student addStudent(Student student) {
	        return studentRepository.save(student);
	    }

	    public Student updateStudent(Long id, Student studentDetails) {
	        Optional<Student> optionalStudent = studentRepository.findById(id);
	        if(optionalStudent.isPresent()) {
	        	Student exisitingStudent = optionalStudent.get();
	        	exisitingStudent.setName(studentDetails.getName());
	        	exisitingStudent.setEmail(studentDetails.getEmail());
	        	exisitingStudent.setCourse(studentDetails.getCourse());
	        	return studentRepository.save(exisitingStudent);
	        }
	        else {
	        	throw new RuntimeException("Student not found with id" + id);
	        }
	    }

	    public void deleteStudent(Long id) {
	        studentRepository.deleteById(id);
	    }
}
