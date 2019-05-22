package com.austshop.austshop.controller;

import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *文件上传
 * zpwan
 * 2019/3/25
 */
@RestController
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileService;


//    Todo
//    预上传

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping(value="/filemodule/file/upload")
    public ResultResponse handleUpload(
            @RequestParam(value = "file", required = true) MultipartFile file,
            HttpServletRequest request
    ) {
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);
        if (file.isEmpty()) {
            return new ResultResponse(501, "文件为空，请重新上传");
        } else {
            Map<String, Object> fileMaps = fileService.insertfile(file,userId);
            return new ResultResponse(200, "上传成功", fileMaps);
        }
    }

}
