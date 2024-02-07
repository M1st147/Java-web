package com.mayikt.serivce;

import com.mayikt.dao.UserDao;
import com.mayikt.entity.UserEntity;

import java.util.List;
public class UserSerivce {
    private UserDao userDao = new UserDao();
    public int registerUser(UserEntity userEntity){
        //1.根据手机号码查询该用户是否已经注册过 如果已经注册过 就无法注册
        String phone = userEntity.getPhone();
        UserEntity dbUserEntity = userDao.getByPhoneUser(phone);
        if (dbUserEntity != null){
            System.out.println("该用户手机号码已经在数据库中无法重复注册");
            return 0;
        }
        //2.该用户不存在 可以直接注册
        int result = userDao.registerUser(userEntity);
        return result;
    }
    public UserEntity login(UserEntity userEntity) {
        return userDao.login(userEntity);
    }
}

