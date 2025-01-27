package com.msr.mapper;

import com.msr.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //添加分类
    @Insert("insert into category values (null, #{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void addCategory(Category category);

    //根据用户查看分类
    @Select("select * from category where create_user=#{id}")
    List<Category> list(Integer id);

    //根据id查看分类
    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    //更新分类
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=now() where id=#{id}")
    void updateCategory(Category category);

    //根据id删除分类
    @Delete("delete from category where id=#{id}")
    void delCategory(Integer id);
}
