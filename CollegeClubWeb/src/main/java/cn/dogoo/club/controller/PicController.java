package cn.dogoo.club.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.dogoo.common.tip.UriTip;
import cn.dogoo.common.util.ObjectUtil;
import cn.dogoo.common.util.UUIDUtil;
import cn.dogoo.common.util.UploadUtil;

@Controller
public class PicController {
	
	@RequestMapping("/pic/imgadd")
	@ResponseBody
	private String saveImageFile(@RequestParam("file") MultipartFile imageFile, HttpServletRequest request) throws JsonProcessingException {
        //获取文件上传到服务器的路径
 
        String oldFileName=imageFile.getOriginalFilename();
        //创建文件夹
        String dir1=UploadUtil.getUploadPath
				(oldFileName, "/upload");	
		String uploadUrl=UriTip.Image_Path+dir1+"/";
         
        System.out.println("文件路径为："+uploadUrl);
        
        //获取从客户端传过来的文件名
        String extName=oldFileName.
				substring(oldFileName.lastIndexOf("."));
        //判断文件上传的路径是否存在，若不存在，则需要创建它
        
        String [] paths=uploadUrl.split("/");  
	    StringBuffer fullPath=new StringBuffer();  
	    for (int i = 0; i < paths.length; i++) {  
	        fullPath.append(paths[i]).append("/");  
	        File file=new File(fullPath.toString());  
	        if(!file.exists()){  
	            file.mkdir();  
	        }  
	    }  		
        
        //targetFile最终上传的文件，先判断文件是否存在
        String imgname=UUIDUtil.getUUID()+extName;
        File targetFile=new File(uploadUrl+imgname);
        System.out.println(uploadUrl+imgname);
        if(!targetFile.exists()){
            //如果文件不存在，我们尝试创建它
            try {
                targetFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //使用MultipartFile的transferTo方法保存文件

        try {
            imageFile.transferTo(targetFile);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        
        String over=UriTip.Image_URI+dir1+"/"+imgname;
        return ObjectUtil.mapper.writeValueAsString(over);
    }
}
