package com.mayikt.test;

import com.mayikt.entity.UserEntity;
import com.mayikt.serivce.UserSerivce;

import java.util.Scanner;

/**
 * @author M1st
 * @ClassName UserTest
 */
public class UserTest {
    private UserSerivce userSerivce = new UserSerivce();

    /**
     * 该登录代码 bug ----sql注入破解登录  手机号码和密码输入错误的 但是可以登录
     *
     * @param args
     */
    public static void main(String[] args) {
        UserTest userTest = new UserTest();
        userTest.index();
    }

    public void index() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入数字1：用户注册");
        System.out.println("输入数字2：用户登录");
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                registerUser();
            case 2:
                login();
        }
    }

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户的手机号码");
        String phone = scanner.nextLine();
        System.out.println("请输入用户的密码");
        String pwd = scanner.nextLine();
        int result = userSerivce.registerUser(new UserEntity(phone, pwd));
        if (result > 0) {
            // 用户注册成功
            System.out.println("用户注册成功");
        } else {
            System.out.println("用户注册失败啦");
        }
    }

    public void login() {
        //给用户输入手机号码或者是密码错误机会 三次 直接退出程序
        for (int i = 1; i <= 3; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入用户的手机号码");
            String phone = scanner.nextLine();
            System.out.println("请输入用户的密码");
            String pwd = scanner.nextLine();
            // 调用 登录业务逻辑方法
            UserEntity dbUserEntity = userSerivce.login(new UserEntity(phone, pwd));
            if (dbUserEntity != null) {
                System.out.println("登录成功");
                return;//退出循环
            } else {
                System.out.println("用户输入的手机号码或者密码不正确! 错误的次数:" + i);
            }
        }

    }
}
