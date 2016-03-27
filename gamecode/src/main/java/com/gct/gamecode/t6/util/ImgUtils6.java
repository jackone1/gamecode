package com.gct.gamecode.t6.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;


/**
 * 图片工具
 * @author Administrator
 *
 */
public class ImgUtils6 {

	private static final String IMG_PATH = "com/gct/gamecode/t5/img/";
	
	private static Map<String, Image> imgMap = new HashMap<String, Image>();
	
	static { //load img when using
		String filePath = null;
		try {
			filePath = URLDecoder.decode(ImgUtils6.class.getClassLoader().getResource("").getPath(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		File[] listFiles = new File(filePath + IMG_PATH).listFiles();
		for (File file : listFiles) {
			try {
				Image img = ImageIO.read(file);
				imgMap.put(StringUtils.substringBefore(file.getName(), "."), img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(imgMap.keySet());
	}
	
	public static Image getImgByFileName(String fileName) {
		return imgMap.get(fileName);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("1:" + Thread.currentThread().getContextClassLoader().getResource("").getPath());  
		System.out.println("2:" + ImgUtils6.class.getClassLoader().getResource("").getPath());  
		System.out.println("3:" + ClassLoader.getSystemResource("").getPath());  
		System.out.println("4:" + ImgUtils6.class.getResource("").getPath());//IdcardClient.class文件所在路径  
		System.out.println("5:" + ImgUtils6.class.getResource("/").getPath()); // Class包所在路径，得到的是URL对象，用url.getPath()获取绝对路径String  
		System.out.println("6:" + new File("/").getAbsolutePath());  
		System.out.println("7:" + System.getProperty("user.dir"));  
		System.out.println("8:" + System.getProperty("file.encoding"));//获取文件编码 
		
		//第一种结果（前边有个斜线）
				System.out.println(ImgUtils6.class.getClassLoader().getResource(".").getPath());
				System.out.println("===========华丽丽的分割线===========");
				//第二种结果（前边以file打头）
				System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
				System.out.println(ClassLoader.getSystemResource(""));
				System.out.println(ImgUtils6.class.getResource(""));
				System.out.println(ImgUtils6.class.getResource("/"));
				System.out.println("===========华丽丽的分割线===========");
				//第三种结果（定位到工程目录）
				System.out.println(new File("").getAbsolutePath());
				System.out.println(System.getProperty("user.dir"));
		
	}

}
