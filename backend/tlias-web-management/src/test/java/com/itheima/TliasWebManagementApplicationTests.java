package com.itheima;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.impl.EmpServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Autowired
    private EmpServiceImpl empServiceImpl;

//    测试生成UUID
    @Test
    public void testUuid() {
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }
// 生成JWT和解析jwt
    @Test
    public void testGenAndParseJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "lem");

        String secretKey = "Lem";
        byte[] secretBytes = secretKey.getBytes();

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretBytes) // 签名算法
                .setClaims(claims)  // 自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 过期时间
                .compact();
        System.out.println(jwt);

        Claims claims1 = Jwts.parser()
                .setSigningKey(secretBytes)
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(claims1);

    }

//    测试登录
    @Test
    public void testLogin() {
        Emp emp = new Emp();
        emp.setUsername("jinyong");
        emp.setPassword("123456");
        Emp e = empServiceImpl.login(emp);
        System.out.println(e);
    }

    //    测试通过id查询
    @Test
    public void testGetById() {
        Emp e = empServiceImpl.getById(3);
        System.out.println(e);
    }

    //    测试分页查询
    @Test
    public void testPage() {
        PageBean e = empServiceImpl.page(2,5, null, null, null, null);
        System.out.println(e);
    }

}
