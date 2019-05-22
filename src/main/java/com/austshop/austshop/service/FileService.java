package com.austshop.austshop.service;

import com.austshop.austshop.Utils.FileUtils;
import com.austshop.austshop.Utils.MD5Utils;
import com.austshop.austshop.entity.PictureFile;
import com.austshop.austshop.exception.OperationFailException;
import com.austshop.austshop.mapper.FileMapper;
import com.austshop.austshop.service.impl.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(value = "fileSerice")
public class FileService implements IFileService {

    @Autowired
    private FileMapper fileMapper;

    //    当前时间戳
    SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式


    @Override
    public Map<String,Object> insertfile(MultipartFile file,long uploader) {
//        文件本地存储位置
        String filepath = "D:\\workspace\\imgs";

//        文件后缀名
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.') + 1);
//        文件前缀名
        String suffix = MD5Utils.md5(file.getOriginalFilename());
//        拼接成新的文件名
        String fileName = suffix + "." + extension;

//         文件存储到数据库的地址
        String url = "http://127.0.0.1:9898/" + fileName;

        File dest = new File(filepath + "/" + fileName);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String md5 = FileUtils.getFileMD5(dest);

        Map<String, Object> filemaps = new HashMap<>();

        filemaps.put("filename",file.getOriginalFilename());
        filemaps.put("length",dest.length());
        filemaps.put("md5",md5);
        filemaps.put("url",url);
        filemaps.put("uploader",uploader);
        filemaps.put("uploadDate", dformat.format(new Date()));

        Integer num = fileMapper.insertFile(url, md5, file.getOriginalFilename(), dest.length(), uploader,0,uploader, dformat.format(new Date()));
        if (num != 1) {
            throw new OperationFailException();
        }
        return filemaps;
    }
}
