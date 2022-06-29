package com.example.demo.service.impl;

import com.example.demo.entity.File;
import com.example.demo.mapper.FileMapper;
import com.example.demo.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anonymous
 * @since 2022-06-29
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
