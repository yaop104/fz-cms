package com.fangzhi.yao.fzcms.im4java;

import com.fangzhi.yao.fzcms.log.Log;
import com.fangzhi.yao.fzcms.log.LogFactory;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;


/**
 * @author yaoping
 * @date 2019-07-17 17:09
 */
public class ImageUtils {
    private static Log logger = LogFactory.getLog(ImageUtils.class);
    public static String IMAGE_SRC = "/Users/yaoping/Documents/study/image/1.png";
    public static String IMAGE_TARGET = "/Users/yaoping/Documents/study/image/2.png";

    public static void main(String[] a) {


        createStringMark(IMAGE_SRC, "测试文字",IMAGE_TARGET);
    }


    /**
     * @param filePath 源图片路径
     * @param markContent 图片中添加内容
     * @param outPath  输出图片路径
     * 字体颜色等在函数内部实现的
     */
    //给jpg添加文字
    public static boolean createStringMark(String filePath,String markContent,String outPath)
    {
        ImageIcon imgIcon=new ImageIcon(filePath);
        Image theImg =imgIcon.getImage();
        int width=theImg.getWidth(null)==-1?200:theImg.getWidth(null);
        int height= theImg.getHeight(null)==-1?200:theImg.getHeight(null);
        BufferedImage bimage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g=bimage.createGraphics();

        Color mycolor = Color.red;
        g.setColor(mycolor);
        g.setBackground(Color.red);
        g.drawImage(theImg, 0, 0, null );
        g.setFont(new Font("宋体",Font.PLAIN,50)); //字体、字型、字号
        g.drawString(markContent,width/2,height/2); //画文字
        g.dispose();
        try
        {
            FileOutputStream out=new FileOutputStream(outPath); //先用一个特定的输出文件名
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
            param.setQuality(100, true);  //
            encoder.encode(bimage, param);
            out.close();
        }
        catch(Exception e)
        { return false; }
        return true;
    }

}
