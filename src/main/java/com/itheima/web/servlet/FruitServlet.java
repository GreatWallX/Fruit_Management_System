package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Fruit;
import com.itheima.pojo.PageBean;
import com.itheima.service.FruitService;
import com.itheima.service.impl.FruitServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/fruit/*")
public class FruitServlet extends BaseServlet {
   private FruitService fruitService= new FruitServiceImpl();
   //用户实现分页查询
   public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
      //1. 调用service查询
      List<Fruit> fruits = fruitService.selectAll();
      //2. 转为JSON
      String jsonString = JSON.toJSONString(fruits);
      //3. 写数据
      //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
      response.setContentType("text/json;charset=utf-8");
      response.getWriter().write(jsonString);
   }
   //添加水果信息
   public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
      //1. 接收品牌数据
      request.setCharacterEncoding("utf-8");
      BufferedReader br = request.getReader();
      String jsonString = br.readLine();
      //转为Fruit对象
      Fruit fruit = JSON.parseObject(jsonString, Fruit.class);
      //2. 调用service添加
      fruitService.add(fruit);
      //3.响应标记

      response.getWriter().write("success");
   }
   //修改水果信息
   public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
   //删除水果信息
   public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String sid = request.getParameter("id");
      int id = Integer.parseInt(sid);
      //调用service
      fruitService.delete(id);
      //响应标识
      response.getWriter().write("success");
   }
   //批量删除水果信息
   public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
      //接收数据
      BufferedReader br = request.getReader();
      String params = br.readLine();
      //转为int[]
      int[] ids = JSON.parseObject(params, int[].class);
      //调用service
      fruitService.deleteByIds(ids);
      //响应标识
      response.getWriter().write("success");
   }
   public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
      //1. 接收 当前页码 和 每页展示条数 url?currentPage=1&pageSize=5
      String _currentPage = request.getParameter("currentPage");
      String _pageSize = request.getParameter("pageSize");
      int currentPage = Integer.parseInt(_currentPage);
      int pageSize = Integer.parseInt(_pageSize);
      //2. 调用service查询
      PageBean<Fruit> PageBean = fruitService.selectByPage(currentPage,pageSize);
      //2. 转为JSON
      String jsonString = JSON.toJSONString(PageBean);
      //3. 写数据
      response.setContentType("text/json;charset=utf-8");
      response.getWriter().write(jsonString);
   }
   public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
      //1. 接收 当前页码 和 每页展示条数 url?currentPage=1&pageSize=5
      //处理POST请求乱码问题
      request.setCharacterEncoding("utf-8");
      String _currentPage = request.getParameter("currentPage");
      String _pageSize = request.getParameter("pageSize");
      int currentPage = Integer.parseInt(_currentPage);
      int pageSize = Integer.parseInt(_pageSize);
      // 获取查询条件对象
      BufferedReader br = request.getReader();
      String params = br.readLine();
      //转为 Fruit
      Fruit fruit = JSON.parseObject(params, Fruit.class);
      //2. 调用service查询
      PageBean<Fruit> pageBean = fruitService.selectByPageAndCondition(currentPage,pageSize,fruit);
      //转为JSON
      String jsonString = JSON.toJSONString(pageBean);
      //3. 写数据
      response.setContentType("text/json;charset=utf-8");
      response.getWriter().write(jsonString);
   }
}
