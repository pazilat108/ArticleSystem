package com.msr.controller;

import com.msr.pojo.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author acer
 * @create 2025-01-23 14:02
 * @desc
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result list(){
        return Result.success("文章列表数据");
    }
}
