package com.example.demo.controller;


import cn.hutool.core.io.FileUtil;
import com.example.demo.entity.File;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2022-06-29
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PatchMapping
    public String upload(@RequestParam MultipartFile file){

        File upload = new File();
        upload.setFileName(file.getOriginalFilename());
        upload.setType(FileUtil.extName(upload.getFileName()));
        upload.setSize(file.getSize());
        //存储到本地
        //
    }
}
