package com.example.jpanplus1.service;

import com.example.jpanplus1.domain.School;
import com.example.jpanplus1.domain.SchoolRepository;
import com.example.jpanplus1.domain.Student;
import com.example.jpanplus1.domain.StudentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentServiceTest {
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @After
    public void cleanAll() {
       schoolRepository.deleteAll();
       studentRepository.deleteAll();
    }
    @Before
    public void setup() {
        List<School> schools = new ArrayList<>();

        for(int i=1; i<=5; i++){
            School school = new School("school"+i);

            school.addStudent(new Student("Lee"+i));
            school.addStudent(new Student("Kim"+i));

            schools.add(school);
        }
        schoolRepository.saveAll(schools);
    }

    @Test
    @DisplayName("N+1 예제 - 1")
    public void test1() throws Exception {
        System.out.println("== start ==");
        List<Student> studentList = studentRepository.findAll();
        System.out.println("== end ==");
    }

    @Test
    @DisplayName("N+1 예제 - 2")
    @Transactional
    public void test2() throws Exception {
        System.out.println("== start ==");
        List<Student> studentList = studentRepository.findAll();
        System.out.println("== end ==");
    }

    @Test
    @DisplayName("N+1 예제 - 3")
    public void test3() throws Exception {
        System.out.println("== start ==");
        studentService.findAllSchoolNames();
        System.out.println("== end ==");
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("N+1 예제 - 4")
    public void test4() throws Exception {
        System.out.println("== start ==");
        studentService.findAllSchoolNames();
        System.out.println("== end ==");
    }
}
