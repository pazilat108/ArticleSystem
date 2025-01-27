package com.msr.controller;

import com.msr.pojo.Category;
import com.msr.pojo.entity.Result;
import com.msr.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author acer
 * @create 2025-01-27 10:59
 * @desc
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //分类的添加
    @PostMapping
    public Result addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return Result.success();
    }

    //更新文章分类
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> list = categoryService.list();
        return Result.success(list);
    }
    @GetMapping("/detail")
    //根据id获取详情
    public Result<Category> detail(Integer id){
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    //更新文章分类
    @PutMapping
    public Result updateCategory(@RequestBody @Validated Category category){
        categoryService.updateCategory(category);
        return Result.success();
    }

    //删除文章分类
    @DeleteMapping
    public Result delCategory(Integer id){
         categoryService.delCategory(id);
         return Result.success();
    }
}
