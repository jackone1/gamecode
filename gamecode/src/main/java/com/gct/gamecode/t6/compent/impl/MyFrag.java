package com.gct.gamecode.t6.compent.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.gct.gamecode.t5.util.ImgUtils;
import com.gct.gamecode.t6.compent.MyCompent;

/**
 * 手雷
 * @author Administrator
 *
 */
public class MyFrag implements MyCompent {

	/**
	 * 图片
	 */
	private Image img;
	/**
	 * 距形长和宽
	 */
	private Integer intDiameter;
	/**
	 * 距形中心点
	 */
	private Point ptCenter;
	
	private EnumMyFrag myFrag;
	
	public enum EnumMyFrag {
		MY_FRAG("FRAG", "frag"),
		MY_FRAG_FLAG("FLAG", "frag_flag"),
		MY_FRAG_UNSURE("UNSURE", "frag_unsure")
		;
		
		String name;
		String imgName;
		EnumMyFrag(String name, String imgName) {
			this.name = name;
			this.imgName = imgName;
		}
	}
	
	/**
	 * @param intDiameter
	 * @param ptCenter
	 */
	public MyFrag(EnumMyFrag myFrag, Point ptCenter, Integer intDiameter) {
		super();
		
		this.ptCenter = ptCenter;
		this.intDiameter = intDiameter;
		this.myFrag = myFrag;
		
		this.img = ImgUtils.getImgByFileName(myFrag.imgName);
	}

	/**
	 * 画图
	 * @param g
	 */
	public void paint(Graphics g) {
		Color oldColor = g.getColor();

		int sx = ptCenter.x - intDiameter * 3/6;
		int sy = ptCenter.y - intDiameter * 4/6;
		
		if (img != null) {
			int width = img.getWidth(null);
			int height = img.getHeight(null);
			g.drawImage(img, sx, sy, width, height, null);
		} else {
			int x = ptCenter.x - intDiameter/2;
			int y = ptCenter.y - intDiameter/2;
			g.drawRect(x, y, intDiameter, intDiameter);
		}
		
		g.setColor(oldColor);
	}
	
	public Point getMyPosition() {
		return this.ptCenter;
	}

	public void changeMyFrag(EnumMyFrag newMyFrag) {
		this.myFrag = newMyFrag;
		this.img = ImgUtils.getImgByFileName(myFrag.imgName);
	}
	
	public EnumMyFrag getMyFrag() {
		return myFrag;
	}

	public void setMyFrag(EnumMyFrag myFrag) {
		this.myFrag = myFrag;
	}
}
