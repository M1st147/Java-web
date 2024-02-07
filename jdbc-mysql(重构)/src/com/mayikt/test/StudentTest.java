package com.mayikt.test;

import java.sql.ResultSet;
import com.mayikt.entity.StudentEntity;
import com.mayikt.serivce.StudentService;
import com.mayikt.test.StudentTest;
import java.util.ArrayList;

public class StudentTest {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
//        ①查询全部学生id
        ArrayList<StudentEntity> studentEntities = studentService.allStudent();
        for (StudentEntity stu : studentEntities) {
            System.out.println(stu);
        }
    }

        //②查询指定id学生信息
        //StudentEntity student = studentService.getByIdStudent(8l);
        //System.out.println(student);

        //③插入信息
//        StudentEntity studentEntity = new StudentEntity("mayikt66",22,"湖北省武汉市");
//        int result = studentService.insertStudent(studentEntity);
//        if (result > 0) {
//            System.out.println("数据插入成功");
//        }else{
//            System.out.println("数据插入失败");
//        }


        //④修改数据库指定id内容
//        updateTestStudent();
//    }
//
//    public static void updateTestStudent() {
//        //1.查询原来该学生信息
//        StudentService studentService = new StudentService();
//        Long stuId = 1l;
//        StudentEntity studentEntity = studentService.getByIdStudent(stuId);
//        if(studentEntity==null){
//            return;//不会继续 往下面执行
//        }
//        //2.修改学生信息
//        studentEntity.setName("余生辉66111");
//        studentEntity.setAge(23);
//        studentEntity.setAddress("山东1省");
//        studentService.updateStudent(studentEntity);
//        int result = studentService.updateStudent(studentEntity);
//        if (result > 0){
//            System.out.println("修改成功");
//        }else {
//            System.out.println("修改失败");
//        }
//    }

//        deleteStudentTest();
//    }
//    public static void deleteStudentTest(){
//        //1.删除学生信息
//        Long id = 2l;
//        //2.在删除之前查询db中是否存在该条数据 存在就删除 不存在就不删除
//        StudentService studentService = new StudentService();
//        StudentEntity student = studentService.getByIdStudent(id);
//        if (student == null) {
//            System.out.println("数据库中没有该数据 无法删除");
//            return;
//        }
//        int result = studentService.delStudent(id);
//        if(result>0){
//            System.out.println("删除成功...");
//        } else {
//            System.out.println("删除失败...");
//        }
//    }
}
