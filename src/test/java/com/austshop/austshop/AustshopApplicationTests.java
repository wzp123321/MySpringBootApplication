package com.austshop.austshop;

import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AustshopApplicationTests {


    @Autowired
    DataSource dataSource ;
    @Test
    public void contextLoads() {

        System.out.println(dataSource.getClass());

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            System.out.println(conn);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test(){
        long id = 2015001;
        try {
            String token = JsonWebTokenUtils.createToken(id);
            System.out.println("生成的token："+token);
            System.out.println("解析的:"+JsonWebTokenUtils.verifyToken(token));
            System.out.println("解析的id:"+JsonWebTokenUtils.getAppUID(token));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tt1(){
        Admin admin = new Admin();
        admin.setUserName("1111");
        System.out.println(admin.toString());
    }


}

