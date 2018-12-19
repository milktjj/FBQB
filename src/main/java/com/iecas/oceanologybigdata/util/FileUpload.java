package com.iecas.oceanologybigdata.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
    public static String fileUpload(MultipartFile file,String file_path){
        String fileName=file.getOriginalFilename();
        File dest=new File(file_path+'/'+fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "true";
        }catch (IllegalStateException e){
            e.printStackTrace();
            return "false";
        }catch (IOException e){
            e.printStackTrace();
            return "false";
        }
    }
}

