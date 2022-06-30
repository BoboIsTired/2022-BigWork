package com.example.demo.controller;



import cn.hutool.core.io.FileUtil;
//import cn.hutool.core.net.URLEncoder;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Material;
//import lombok.Value;
import com.example.demo.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

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

    //上传
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
        //获取文件的md5
        upload.setMd5(SecureUtil.md5(uploadFile));
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",upload.getMd5());
//            Material material = MaterialMapper.selectOne(queryWrapper);

        //把文件存储到磁盘目录
        file.transferTo(uploadFile);
        //存储数据库
        upload.setCreateDate(LocalDateTime.now());
//        String url = "http://localhost:3000/material/"+uuid;
        String url = uuid + StrUtil.DOT + upload.getType();
        upload.setUrl(url);
        materialMapper.insert(upload);

        return upload.getUrl();
    }

    //下载
    @GetMapping("/{url}")
    public void download(@PathVariable String url, HttpServletResponse response) throws IOException{
        //根据文件标识获取文件
        File uploadFile = new File(fileUploadPath + url);
        //设置输出流格式
        ServletOutputStream os = response.getOutputStream();
//        String ss = URLEncoder.encode(url, "utf-8");
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(url,"UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

}
