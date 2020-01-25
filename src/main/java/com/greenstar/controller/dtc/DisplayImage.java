package com.greenstar.controller.dtc;

import com.greenstar.entity.dtc.UploadImg;
import com.greenstar.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

/**
 * @author Syed Muhammad Hassan
 * 4th November, 2019
 */
@Controller
public class DisplayImage {
    public void downloadImg(byte[] b){
        /*byte[] b = {-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82,
                0, 0, 0, 15, 0, 0, 0, 15, 8, 6, 0, 0, 0, 59, -42, -107,
                74, 0, 0, 0, 64, 73, 68, 65, 84, 120, -38, 99, 96, -64, 14, -2,
                99, -63, 68, 1, 100, -59, -1, -79, -120, 17, -44, -8, 31, -121, 28, 81,
                26, -1, -29, 113, 13, 78, -51, 100, -125, -1, -108, 24, 64, 86, -24, -30,
                11, 101, -6, -37, 76, -106, -97, 25, 104, 17, 96, -76, 77, 97, 20, -89,
                109, -110, 114, 21, 0, -82, -127, 56, -56, 56, 76, -17, -42, 0, 0, 0,
                0, 73, 69, 78, 68, -82, 66, 96, -126};

                */
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D://banner.png");
            fos.write(b);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Image createImage(){
        byte[] b = {-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82,
                0, 0, 0, 15, 0, 0, 0, 15, 8, 6, 0, 0, 0, 59, -42, -107,
                74, 0, 0, 0, 64, 73, 68, 65, 84, 120, -38, 99, 96, -64, 14, -2,
                99, -63, 68, 1, 100, -59, -1, -79, -120, 17, -44, -8, 31, -121, 28, 81,
                26, -1, -29, 113, 13, 78, -51, 100, -125, -1, -108, 24, 64, 86, -24, -30,
                11, 101, -6, -37, 76, -106, -97, 25, 104, 17, 96, -76, 77, 97, 20, -89,
                109, -110, 114, 21, 0, -82, -127, 56, -56, 56, 76, -17, -42, 0, 0, 0,
                0, 73, 69, 78, 68, -82, 66, 96, -126};
        return new ImageIcon(b).getImage();
    }
    @RequestMapping(value = "/showImg", method = RequestMethod.GET)
    @ResponseBody
    public void showImage(){
        File imageFile=null;

        try {
            ArrayList<UploadImg> uploadImg = (ArrayList<UploadImg>) HibernateUtil.getDBObjects("FROM UploadImg");
             File file = new File("D:\\myImage.png");
            byte [] aByteArray = uploadImg.get(0).getImg();
            ByteArrayInputStream baisss = new ByteArrayInputStream(aByteArray);
            BufferedImage bImage = ImageIO.read(baisss);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos );
            byte [] data = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "png", new File("D:\\myImageTest.png") );
            System.out.println("image created");
        }catch(Exception e ){
            int i =3;
        }

    }
}
