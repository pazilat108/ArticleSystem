package com.msr.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.msr.mapper.ArticleMapper;
import com.msr.pojo.Article;
import com.msr.pojo.entity.PageBean;
import com.msr.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author acer
 * @create 2025-01-28 11:27
 * @desc
 */
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;

    //添加文章
    @Override
    public void addArticle(Article article) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId =(Integer) claims.get("id");//用户id
        article.setCreateUser(userId);
        articleMapper.addArticle(article);
    }

    //文章分页
    @Override
    public PageBean<Article> listPage(Integer pageNum, Integer pageSize, String categoryId, String state) {
        //1.开启分页查询
        PageHelper.startPage(pageNum,pageSize);

        //2.调用mapper分页参数
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId =(Integer) claims.get("id"); //用户id 分类id 状态
        List<Article> articleList = articleMapper.listPage(userId,categoryId,state);

        //3.根据集合数据获取分页对象
        Page<Article> page =(Page<Article>)articleList;

        //4.把查询的数据分装到pagebean
        PageBean<Article> pageBean = new PageBean<Article>(page.getTotal(),page.getResult());
        return pageBean;
    }
}
