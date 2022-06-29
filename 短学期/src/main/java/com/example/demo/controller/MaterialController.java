package com.example.demo.controller;



import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.entity.Material;
//import lombok.Value;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2022-06-29
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Resource
    MaterialMapper materialMapper;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @PostMapping
    public String upload(@RequestParam MultipartFile file) throws IOException {

        Material upload = new Material();
        upload.setFileName(file.getOriginalFilename());
        upload.setType(FileUtil.extName(upload.getFileName()));
        upload.setSize(file.getSize());
        //存储到本地
        File uploadParentFile = new File(fileUploadPath);
            //判断配置文件是否存在，若不存在，新建文件目录
        if(!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }
            //定义文件唯一的标识
        String uuid = IdUtil.fastSimpleUUID();
        File uploadFile = new File(fileUploadPath+uuid + StrUtil.DOT + upload.getType());
            //把文件存储到磁盘目录
        file.transferTo(uploadFile);
        //存储数据库
//        materialMapper
        return "";
    }
}
