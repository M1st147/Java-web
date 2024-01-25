package com.mayikt.test;

import com.mayikt.entity.StudentEntity;
import com.mayikt.serivce.StudentService;
import com.mayikt.test.StudentTest;
import java.util.ArrayList;

public class StudentTest {
    public static void main(String[] args){
        StudentService studentService = new StudentService();
        ArrayList<StudentEntity> studentEntities = studentService.allStudent();
        for (StudentEntity stu:studentEntities){
            System.out.println(stu);
        }
    }
}
