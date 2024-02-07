package com.mayikt.test;

import com.mayikt.entity.StudentEntity;
import com.mayikt.serivce.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

public class IndexTest {
    private static StudentService studentService = new StudentService();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("欢迎来到我们的学生管理系统");
            System.out.println("1.查询所有学生信息");
            System.out.println("2.根据学生id查询学生信息");
            System.out.println("3.新增学生信息");
            System.out.println("4.根据学生id修改学生信息");
            System.out.println("5.根据学生id删除学生信息");
            System.out.println("0.退出系统");
            System.out.println("请选择对应序号:");

            Scanner scanner = new Scanner(System.in);
            int result = scanner.nextInt();

            switch (result) {
                case 1:
                    showAllStudent();
                    break;
                case 2:
                    findByIdStudent();
                    break;
                case 3:
                    insertStudent();
                    break;
                case 4:
                    updateIdStudent();
                    break;
                case 5:
                    delIdStudent();
                    break;
                case 0:
                    System.out.println("退出系统，谢谢使用！");
                    System.exit(0);
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }

    public static void showAllStudent() {
        System.out.println("查询到的所有学生信息");
        ArrayList<StudentEntity> studentEntities = studentService.allStudent();
        if (studentEntities.isEmpty()) {
            System.out.println("暂无学生信息");
        } else {
            for (StudentEntity stu : studentEntities) {
                System.out.println(stu);
            }
        }
    }

    public static void findByIdStudent() {
        System.out.println("请输入学生的id：");
        Scanner scanner = new Scanner(System.in);
        Long stuId = scanner.nextLong();
        StudentEntity student = studentService.getByIdStudent(stuId);
        if (student == null) {
            System.out.println("该学生id" + stuId + "不存在");
        } else {
            System.out.println("学生信息：" + student);
        }
    }

    public static void delIdStudent() {
        System.out.println("请输入学生的id：");
        Scanner scanner = new Scanner(System.in);
        Long stuId = scanner.nextLong();
        int result = studentService.delStudent(stuId);
        if (result > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    public static void insertStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生的名称：");
        String name = scanner.nextLine();
        System.out.println("请输入学生的年龄：");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("请输入学生的地址：");
        String address = scanner.nextLine();
        StudentEntity studentEntity = new StudentEntity(null, name, age, address);

        int result = studentService.insertStudent(studentEntity);
        if (result > 0) {
            System.out.println("插入学生信息成功");
        } else {
            System.out.println("插入学生信息失败");
        }
    }

    public static void updateIdStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生的id：");
        Long stuId = scanner.nextLong();
        StudentEntity student = studentService.getByIdStudent(stuId);
        if (student == null) {
            System.out.println("没有查询到该学生的id对应学生信息");
            return;
        }
        scanner.nextLine(); // Consume the newline character
        System.out.println("请输入学生的名称：");
        String name = scanner.nextLine();
        System.out.println("请输入学生的年龄：");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("请输入学生的地址：");
        String address = scanner.nextLine();
        StudentEntity studentEntity = new StudentEntity(stuId, name, age, address);
        int result = studentService.updateStudent(studentEntity);
        if (result > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }
}
