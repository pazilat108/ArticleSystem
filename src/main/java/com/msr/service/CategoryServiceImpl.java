package com.msr.service;

import com.msr.mapper.CategoryMapper;
import com.msr.pojo.Category;
import com.msr.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author acer
 * @create 2025-01-27 10:57
 * @desc
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;

   //添加分类
    @Override
    public void addCategory(Category category) {
        //用户id
        Map<String,Object> claims =ThreadLocalUtil.get();
        Integer userId =(Integer)claims.get("id");
        category.setCreateUser(userId);
        categoryMapper.addCategory(category);
    }

    //查询分类
    @Override
    public List<Category> list() {
        Map<String, Object> claims =ThreadLocalUtil.get();
        Integer id =(Integer) claims.get("id");
        return categoryMapper.list(id);
    }

    //根据id查询详情
    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    //更新分类
    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    //删除分类
    @Override
    public void delCategory(Integer id) {
        categoryMapper.delCategory(id);
    }
}
