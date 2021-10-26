package com.spring.nginx_server1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class Controller {

    @GetMapping("/index")
    String index(){
        return "index";
    }

    @PostMapping("/upload")
    String upload(MultipartFile file){
        try {
            imageUpload(file);
            return "success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    void imageUpload(MultipartFile imageFile) throws IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String current_date = simpleDateFormat.format(new Date());

        String basePath = new File("").getAbsolutePath()+"/nginx_server1/src/main/resources/static/";

        String[] imageFileFlags = imageFile.getOriginalFilename().split("\\.");
        String imageExt=imageFileFlags[imageFileFlags.length-1];

        String imagePath = basePath + "image" + current_date + "." + imageExt;

        File dest = new File(imagePath);
        imageFile.transferTo(dest);
    }
}
