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

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private FruitService fruitService = new FruitServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String jsonString = br.readLine();
        //转为Fruit对象
        Fruit fruit = JSON.parseObject(jsonString, Fruit.class);
        //2. 调用service添加
        fruitService.add(fruit);
        //3.响应标记
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
