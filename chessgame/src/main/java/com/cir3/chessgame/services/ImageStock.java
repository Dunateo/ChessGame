package com.cir3.chessgame.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageStock {

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif", "image/bmp", "image/gif");

    public ImageStock(){ }

    /**
     * Store an picture for a form with his bytes array
     * @param image
     * @param name
     * @param storagePath
     * @return
     */
    public Boolean upload(MultipartFile image, String name , String storagePath ){

        //check if it's an image
        if (!contentTypes.contains(image.getContentType())){
            return Boolean.FALSE;
        }

        //try and catch pour l'upload de l'image
        try {
            System.out.println("9a passe4" + image.getName());

            //get the file
            byte[] bytes = image.getBytes();


            Path path = Paths.get(storagePath+ name);

            System.out.println(image.getContentType());
            //System.out.println(path.toAbsolutePath());
            //System.out.println(path.getParent());

            Files.write(path, bytes);



        }catch (IOException e){

            e.printStackTrace();
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
