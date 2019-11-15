package cn.dogoo.club.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

	@RequestMapping("/")
	public String goIndex(){
		return "index";
	}

	@RequestMapping("/page/{path}")
	public String goPath(@PathVariable String path){
		return path;
	}
	
	
	
	
	
}
