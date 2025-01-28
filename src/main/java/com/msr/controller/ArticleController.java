package com.msr.controller;

import com.msr.pojo.Article;
import com.msr.pojo.entity.PageBean;
import com.msr.pojo.entity.Result;
import com.msr.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author acer
 * @create 2025-01-23 14:02
 * @desc
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public Result list(){
        return Result.success("文章列表数据");
    }

    @PostMapping
    public Result addArticle(@RequestBody Article article){
        articleService.addArticle(article);
         return Result.success();
    }
    //文章分页
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String state){
        PageBean<Article> pageBean = articleService.listPage(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);
    }
}
