package com.msr.controller;

import com.msr.pojo.entity.Result;
import com.msr.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author acer
 * @create 2025-01-28 17:50
 * @desc
 */

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @PostMapping
    public Result<String> upload(MultipartFile file) throws Exception {
        //获取上传的图片名称
        String fileName = file.getOriginalFilename();
        //重新生成上传图片的名称：UUID
        String imgName =UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf(".")); //.jpg
        //实现上传
        String imgUrlpath = AliOssUtil.uploadFile(imgName,file.getInputStream());
        return Result.success(imgUrlpath);//
    }
}
