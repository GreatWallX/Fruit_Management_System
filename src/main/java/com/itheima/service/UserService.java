package com.itheima.service;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    //1.使用工具类获取SqlSessionFactory
    SqlSessionFactory factory  = SqlSessionFactoryUtils.getSqlSessionFactory();
    public User login(String username,String password){
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4. 调用方法
        User user = mapper.select(username, password);
        //5. 释放资源
        sqlSession.close();
        return user;
    }
    public boolean register(User user){
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4. 调用方法
        User u = mapper.selectByUsername(user.getUsername());
        if(u==null){
            mapper.add(user);
            sqlSession.commit();
        }
        //5. 释放资源
        sqlSession.close();
        return u==null;
    }
}
