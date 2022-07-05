package com.example.jpanplus1.service;

import com.example.jpanplus1.domain.Student;
import com.example.jpanplus1.domain.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager em;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void findAllSchoolNames(){
        List<Student> students = studentRepository.findAll();
        getSchoolName(students);
    }

    private List<String> getSchoolName(List<Student> students){
        return students.stream()
                .map(a -> a.getSchool().getName())
                .collect(Collectors.toList());
    }
}
