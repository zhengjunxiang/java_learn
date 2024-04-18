package com.itheima.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.itheima.utils.AliOSSUtils;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UpdateController {

    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传：{}, {}, {}", username, age, image);
        String originalFilename = image.getOriginalFilename();

        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;

//        image.transferTo(new File("E:\\images\\"+newFileName));
        image.transferTo(new File("E:\\images\\"+newFileName));
        return Result.success(newFileName);
    }

//    上传到阿里云
//    @PostMapping("/upload")
//    public Result upload(MultipartFile image) throws IOException {
//        log.info("文件上传, 文件名：{}", image.getOriginalFilename());
//        UpdateController ailOSSUtils;
//        String url = aliOSSUtils.upload(image);
//
//        log.info("文件上传的完成，文件访问url:{}", url);
//
//        return Result.success(url);
//    }
}
