package com.itheima.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Fruit;
import com.itheima.service.FruitService;
import com.itheima.service.impl.FruitServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private FruitService brandService= new FruitServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<Fruit> fruits = brandService.selectAll();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(fruits);
        //3. 写数据
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
