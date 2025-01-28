package com.msr.service;


import com.msr.pojo.Article;
import com.msr.pojo.entity.PageBean;

public interface ArticleService {
    //添加文章
    void addArticle(Article article);
    //文章分页
    PageBean<Article> listPage(Integer pageNum, Integer pageSize, String categoryId, String state);
}
