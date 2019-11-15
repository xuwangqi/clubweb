package cn.dogoo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.dogoo.club.manager.mapper")
public class StarterManager{
	
	public static void main(String[] args) {
		SpringApplication.run(StarterManager.class, args);
	}
}