package com.fc.test.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.fc.test.service.oss.QiNiuCloudService;
import com.fc.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/QiNiuCloudController")
public class QiNiuCloudController {

    @Autowired
    private QiNiuCloudService qiNiuCloudService;

    @PostMapping("/uploadToQiNiu")
    public Result upload(@RequestParam("file")MultipartFile uploadFile){
        try {
            return qiNiuCloudService.uploadToQiNiu(uploadFile);
        } catch (IOException e){
           e.printStackTrace();
           return Result.error(e.getMessage());
        }
    }
}
