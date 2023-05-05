package com.itheima.service;

import com.itheima.mapper.FruitMapper;
import com.itheima.pojo.Fruit;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

public interface FruitService{
    List<Fruit> selectAll();
    void add(Fruit fruit);
    void update(Fruit fruit);
    void delete(int id);
    void deleteByIds(int[] ids);
    PageBean<Fruit> selectByPage(int currentPage , int pageSize);
    PageBean<Fruit> selectByPageAndCondition(int currentPage,int pageSize,Fruit fruit);
}
