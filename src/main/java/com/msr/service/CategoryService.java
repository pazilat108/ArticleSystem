package com.msr.service;

import com.msr.pojo.Category;

import java.util.List;

public interface CategoryService {

    //分类的添加
    void addCategory(Category category);
    //查询分类
    List<Category> list();
    //根据id查询详情
    Category findById(Integer id);
    //更新文章分类
    void updateCategory(Category category);
    //删除文章
    void delCategory(Integer id);
}
