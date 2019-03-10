package com.zql.verifycode;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;

/**
 * Created by 张启磊 on 2019-3-11.
 */
public class ImageUtils {

    /**
     * 图片降噪
     * @param imgUrl
     * @param resUrl
     */
    public static void removeBackground(String imgUrl, String resUrl){
        //定义一个临界阈值
        int threshold = 450;
        try{
            BufferedImage img = ImageIO.read(new File(imgUrl));
            int width = img.getWidth();
            int height = img.getHeight();
            for(int i = 1;i < width;i++){
                for (int x = 0; x < width; x++){
                    for (int y = 0; y < height; y++){
                        Color color = new Color(img.getRGB(x, y));
                        int num = color.getRed()+color.getGreen()+color.getBlue();
                        System.out.println(num);
                        if(num >= threshold){
                            img.setRGB(x, y, Color.WHITE.getRGB());
                        }
                    }
                }
            }
            for(int i = 1;i<width;i++){
                Color color1 = new Color(img.getRGB(i, 1));
                int num1 = color1.getRed()+color1.getGreen()+color1.getBlue();
                for (int x = 0; x < width; x++)
                {
                    for (int y = 0; y < height; y++)
                    {
                        Color color = new Color(img.getRGB(x, y));

                        int num = color.getRed()+color.getGreen()+color.getBlue();
                        if(num==num1){
                            img.setRGB(x, y, Color.BLACK.getRGB());
                        }else{
                            img.setRGB(x, y, Color.WHITE.getRGB());
                        }
                    }
                }
            }
            File file = new File(resUrl);
            if (!file.exists())
            {
                File dir = file.getParentFile();
                if (!dir.exists())
                {
                    dir.mkdirs();
                }
                try
                {
                    file.createNewFile();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            ImageIO.write(img, "jpg", file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 图片裁剪
     * 防止边角受到降噪的影响
     * @param imgUrl
     */
    public static void cuttingImg(String imgUrl){
        try{
            File newfile=new File(imgUrl);
            BufferedImage bufferedimage=ImageIO.read(newfile);
            int width = bufferedimage.getWidth();
            int height = bufferedimage.getHeight();
            if (width > 110) {
                bufferedimage=cut(bufferedimage,((width - 110) / 2),0,(int)110,(int) (height),false);
                if (height >45 ) {
                    bufferedimage=cut(bufferedimage,0,(int)((height - 45) / 2),110,45,false);
                }
            }
            ImageIO.write(bufferedimage, "jpg", new File(imgUrl));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 切割图像，返回指定范围的图像
     *
     * @param inputImage
     * @param x          起点横坐标
     * @param y          起点纵坐标
     * @param width      切割图片宽度:如果宽度超出图片，将改为图片自x剩余宽度
     * @param height     切割图片高度：如果高度超出图片，将改为图片自y剩余高度
     * @param fill       指定目标图像大小超出时是否补白，如果true，则表示补白；false表示不补白，此时将重置目标图像大小
     * @return
     */
    public static BufferedImage cut(BufferedImage inputImage, int x, int y,
                                    int width, int height, boolean fill) {
// 获取原始图像透明度类型
        int type = inputImage.getColorModel().getTransparency();
        int w = inputImage.getWidth();
        int h = inputImage.getHeight();
        int endx = x + width;
        int endy = y + height;
        if (x > w)
            throw new ImagingOpException("起点横坐标超出源图像范围");
        if (y > h)
            throw new ImagingOpException("起点纵坐标超出源图像范围");
        BufferedImage img;
// 补白
        if (fill) {
            img = new BufferedImage(width, height, type);
// 宽度超出限制
            if ((w - x) < width) {
                width = w - x;
                endx = w;
            }
// 高度超出限制
            if ((h - y) < height) {
                height = h - y;
                endy = h;
            }
// 不补
        } else {
// 宽度超出限制
            if ((w - x) < width) {
                width = w - x;
                endx = w;
            }
// 高度超出限制
            if ((h - y) < height) {
                height = h - y;
                endy = h;
            }
            img = new BufferedImage(width, height, type);
        }
// 开启抗锯齿
        RenderingHints renderingHints = new RenderingHints(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_ANTIALIAS_ON);
//// 使用高质量压缩
//        renderingHints.put(RenderingHints.KEY_INTERPOLATION,
//                RenderingHints.VALUE_RENDER_QUALITY);
//        Graphics2D graphics2d = img.createGraphics();
//        graphics2d.setRenderingHints(renderingHints);
//        graphics2d.drawImage(inputImage, 0, 0, width, height, x, y, endx, endy,
//                null);
//        graphics2d.dispose();
        return img;
    }
}
