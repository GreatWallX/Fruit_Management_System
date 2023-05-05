package com.itheima.mapper;

import com.itheima.pojo.Fruit;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FruitMapper {
    /**
     * 显示所有
     * @return
     */
    @Select("select * from fruit_data")
    @ResultMap("fruitResultMap")
    List<Fruit> selectAll();

    /**
     * 增加
     * @param fruit
     */
    @Insert("insert into fruit_data values (null,#{fruitName},#{fruitQuantity},#{originalPlace},#{supplier})")
    void add(Fruit fruit);

    /**
     * 修改
     * @param fruit
     */
    @Update("update fruit_data set fruit_name=#{fruitName},fruit_quantity=#{fruitQuantity},original_place=#{originalPlace},supplier=#{supplier} where id = #{id}")
    void update(Fruit fruit);

    /**
     * 删除
     * @param id
     */
    @Delete("delete from fruit_data where id=#{id}")
    void deleteById(int id);
    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);
    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from fruit_data limit #{begin} , #{size}")
    @ResultMap("fruitResultMap")
    List<Fruit> selectByPage(@Param("begin") int begin,@Param("size") int size);
    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from fruit_data")
    int selectCount();
    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Fruit> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("fruit") Fruit fruit);
    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Fruit fruit);
}
