package com.itheima.service.impl;

import com.itheima.mapper.FruitMapper;
import com.itheima.pojo.Fruit;
import com.itheima.pojo.PageBean;
import com.itheima.service.FruitService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class FruitServiceImpl implements FruitService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Fruit> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        //4. 调用方法
        List<Fruit> fruits = mapper.selectAll();
        //5. 释放资源
        sqlSession.close();
        return fruits;

    }

    @Override
    public void add(Fruit fruit) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        //4.添加
        mapper.add(fruit);
        //5.提交事务
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void update(Fruit fruit) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        mapper.update(fruit);
        //5.提交事务
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void delete(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        //4.删除
        mapper.deleteById(id);
        //5.提交事务
        sqlSession.commit();

        sqlSession.close();
    }
    public void deleteByIds(int[] ids){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        //4.删除
        mapper.deleteByIds(ids);
        //5.提交事务
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public PageBean<Fruit> selectByPage(int currentPage, int pageSize) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        //4. 计算开始索引
        int begin = (currentPage-1)*pageSize;
        int size = pageSize;
        //4. 查询当前页数据
        List<Fruit> fruits = mapper.selectByPage(begin, size);
        int totalCount = mapper.selectCount();
        ///5. 查询当前页数据和总数
        PageBean<Fruit> pageBean = new PageBean<>();
        pageBean.setRows(fruits);
        pageBean.setTotalCount(totalCount);
        //8. 释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Fruit> selectByPageAndCondition(int currentPage, int pageSize, Fruit fruit) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        //计算开始索引
        int begin = (currentPage-1)*pageSize;
        int size = pageSize;
        // 处理fruit条件，模糊表达式
        String fruitName = fruit.getFruitName();
        if (fruitName != null && fruitName.length() > 0) {
            fruit.setFruitName("%" + fruitName + "%");
        }
        String supplier = fruit.getSupplier();
        if (supplier != null && supplier.length() > 0) {
            fruit.setSupplier("%" + fruitName + "%");
        }
        //查询当前页数据和总数
        List<Fruit>  rows = mapper.selectByPageAndCondition(begin,size,fruit);
        int totalCount = mapper.selectTotalCountByCondition(fruit);

        PageBean<Fruit> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //8. 释放资源
        sqlSession.close();
        return pageBean;
    }
}
