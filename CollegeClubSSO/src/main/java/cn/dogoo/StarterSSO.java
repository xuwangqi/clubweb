package cn.dogoo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.dogoo.common.util.ObjectUtil;

@SpringBootApplication
@MapperScan("cn.dogoo.club.sso.mapper")
public class StarterSSO {
	
	public static void main(String[] args) {
		SpringApplication.run(StarterSSO.class, args);
	}
}