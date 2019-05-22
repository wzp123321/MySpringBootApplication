package com.austshop.austshop.entity;

import lombok.Data;

@Data
public class PictureFile {
    private Integer pictureId;
    private String url;
    private String md5;
    private String filename;
    private Long length;
    private Integer uploader;
    private Integer type;
    private long carId;
    private String uploaddate;

    public PictureFile() {
    }

    public PictureFile(Integer pictureId, String url, String md5, String filename, Long length,
                       Integer uploader, Integer type, long carId, String uploaddate) {
        this.pictureId = pictureId;
        this.url = url;
        this.md5 = md5;
        this.filename = filename;
        this.length = length;
        this.uploader = uploader;
        this.type = type;
        this.carId = carId;
        this.uploaddate = uploaddate;
    }

}
