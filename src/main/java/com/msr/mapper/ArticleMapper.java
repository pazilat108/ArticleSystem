package com.msr.mapper;

import com.msr.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //添加文章
    @Insert("insert into article values (null,#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    void addArticle(Article article);

    //分页文章
    List<Article> listPage(Integer userId, String categoryId, String state);
}
