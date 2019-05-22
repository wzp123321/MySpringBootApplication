package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.PictureFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IFileService {

    /**
     * 新增上传文件
     * @param file
     * @return
     */
    public Map<String,Object> insertfile(MultipartFile file,long uploader);
}
