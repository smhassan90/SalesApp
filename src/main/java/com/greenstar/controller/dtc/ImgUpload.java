package com.greenstar.controller.dtc;

import com.greenstar.entity.dtc.UploadImg;
import com.greenstar.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Syed Muhammad Hassan
 * 10/24/2019
 */

@Controller
public class ImgUpload {
    public void saveDummyImage(String img){
        File imagePath = new File("D:\\birthday_img.jpg"); //here we given fully specified image path.

        byte[] imageInBytes = new byte[(int)imagePath.length()]; //image convert in byte form
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(imagePath);  //input stream object create to read the file
            inputStream.read(imageInBytes); // here inputstream object read the file
            inputStream.close();

            UploadImg uploadImg = new UploadImg();
            uploadImg.setId(2);
//            uploadImg.setImg(img);

            HibernateUtil.saveOrUpdate(uploadImg);
        }catch(Exception e){

        }
    }
    public void fetchDummyImage(){
        ArrayList<UploadImg> uploadImg = (ArrayList<UploadImg>) HibernateUtil.getDBObjects("FROM UploadImg");

        byte[] getImageInBytes = uploadImg.get(0).getImg();
        File imageFile = new File("D:\\myImage.png");
        try{
            FileOutputStream outputStream = new FileOutputStream(imageFile); // it will create new file (same location of class)
            outputStream.write(getImageInBytes); // image write in "myImage.jpg" file
            outputStream.close(); // close the output stream

            System.out.println("Retrieved Image from Database using Hibernate.");
        }catch(Exception e){

        }
    }
    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public String index(@RequestParam("file") MultipartFile file){

        /*
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D://banner.png");
            fos.write(img);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UploadImg uploadImg = new UploadImg();
        uploadImg.setId(2);
        uploadImg.setImg(img);
        HibernateUtil.saveOrUpdate(uploadImg);*/

        saveDummyImage("");

/*
        FileInputStream inputStream = null;
        try {

            UploadImg uploadImg = new UploadImg();
            uploadImg.setId(2);
            uploadImg.setImg(img);

            HibernateUtil.saveOrUpdate(uploadImg);
        }catch(Exception e){

        }
        */
        fetchDummyImage();
        return "done";
    }
}
