package com.itheima.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Fruit;
import com.itheima.service.FruitService;
import com.itheima.service.impl.FruitServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    FruitService fruitService = new FruitServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理POST请求的乱码问题
        request.setCharacterEncoding("utf-8");
        //1. 接收数据
        BufferedReader reader = request.getReader();
        String params =reader.readLine();
        Fruit fruit = JSON.parseObject(params, Fruit.class);
        //2.调用service完成修改
        fruitService.update(fruit);
        //3.响应标记
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
