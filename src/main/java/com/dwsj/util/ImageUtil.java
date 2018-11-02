package com.dwsj.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * 图片处理工具类
 * @author YuDong
 *
 */
public class ImageUtil {
	
	
	public static void main(String[] args) {
		ImageUtil a = new ImageUtil();
		//System.out.println(a.pdfToPngAndClipping("C:\\Users\\win7 虚拟机\\Desktop\\刘少怪与王星蕾的个人借款合同.pdf"));
		
		ImageUtil.pdfToPngAndClippingReturnImgPath("C:\\crawl\\fileserver\\data\\WuYoujt\\17786506171\\4141890.pdf",100,2400,2350,3400);
		
	}
	
	
	/**
	 * pdf-->png-->截图后的png
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public static Boolean pdfToPngAndClipping(String path) {
		 File file = new File(path);
		 PDDocument doc = null;
		 PDFRenderer renderer = null;
		 BufferedImage image = null;
		 BufferedImage result = null;
		 try {
            doc = PDDocument.load(file);  
            renderer = new PDFRenderer(doc);  
            //for (int i = 0; i < pageCount; i++) {  
                // 方式1,第二个参数是设置缩放比(即像素)  
                image = renderer.renderImageWithDPI(0, 300);  
                // 方式2,第二个参数是设置缩放比(即像素)  
                // BufferedImage image = renderer.renderImage(i, 1f);  
                ImageIO.write(image, "PNG", new File(path.substring(0,path.length()-4)+".png"));  
                image = ImageUtil.cropImage(result,image, 2200, 600, 2300, 2000);
                //image = ImageUtil.cropImage(image, 5, 50, 40, 200);
                ImageIO.write(image, "PNG", new File(path.substring(0,path.length()-4)+".png"));  
                return true;
           // }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }finally {
	        	try {
					doc.close();
				} catch (Exception e2) {}
			}
		 return false;
	}

	/**
	 * pdf-->png-->截图后的png，最后返回本机的图片路径
	 * @param path
	 * @return
	 */
	public static String pdfToPngAndClippingReturnImgPath(String path) {
		return pdfToPngAndClippingReturnImgPath(path, 2200, 600, 2300, 2000);
	}
	
	/**
	 * pdf-->png-->截图后的png，最后返回本机的图片路径
	 * @param path
	 * @return
	 */
	public static String pdfToPngAndClippingReturnImgPath(String path, int startX, int startY, int endX, int endY) {
		 File file = new File(path);
		 PDDocument doc = null;
		 PDFRenderer renderer = null;
		 BufferedImage image = null;
		 BufferedImage result = null;
		 try {
            doc = PDDocument.load(file);  
            renderer = new PDFRenderer(doc);  
            //for (int i = 0; i < pageCount; i++) {  
                // 方式1,第二个参数是设置缩放比(即像素)  
                image = renderer.renderImageWithDPI(0, 300);  
                // 方式2,第二个参数是设置缩放比(即像素)  
                // BufferedImage image = renderer.renderImage(i, 1f);  
                ImageIO.write(image, "PNG", new File(path.substring(0,path.length()-4)+".png"));  
                image = ImageUtil.cropImage(result,image, startX, startY, endX, endY);
                ImageIO.write(image, "PNG", new File(path.substring(0,path.length()-4)+".png"));  
                return path.substring(0,path.length()-4)+".png";
           // }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }finally {
	        	try {
					doc.close();
				} catch (IOException e2) {}
			}
		 return "";
		
	}
	
	
	/**
     * 裁剪图片方法
     * @param bufferedImage 图像源
     * @param startX 裁剪开始x坐标
     * @param startY 裁剪开始y坐标
     * @param endX 裁剪结束x坐标
     * @param endY 裁剪结束y坐标
     * @return
     */
    public static BufferedImage cropImage(BufferedImage result,BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
       int width = bufferedImage.getWidth();
       int height = bufferedImage.getHeight();
       if (startX == -1) {
           startX = 0;
       }
       if (startY == -1) {
           startY = 0;
       }
       if (endX == -1) {
           endX = width - 1;
       }
       if (endY == -1) {
           endY = height - 1;
       }
       result = new BufferedImage(endX - startX, endY - startY, 4);
       for (int x = startX; x < endX; ++x) {
           for (int y = startY; y < endY; ++y) {
               int rgb = bufferedImage.getRGB(x, y);
               result.setRGB(x - startX, y - startY, rgb);
           }
       }
       return result;
   }
	
	
}
