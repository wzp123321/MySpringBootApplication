package com.austshop.austshop.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface FileMapper {

    @Insert("insert into priview_pictures_info(url,md5,filename,length,uploader,type,carId,uploaddate)" +
            " values(#{url},#{md5},#{filename},#{length},#{uploader},#{type},#{carId},#{uploaddate})")
    public Integer insertFile(@Param("url") String url,@Param("md5") String md5,@Param("filename") String filename,
                              @Param("length") Long length,@Param("uploader") long uploader,
                              @Param("type") Integer type,@Param("carId") long carId,
                              @Param("uploaddate") String uploaddate);
}
