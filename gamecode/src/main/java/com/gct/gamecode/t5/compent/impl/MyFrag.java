package com.gct.gamecode.t5.compent.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.gct.gamecode.t5.compent.MyCompent;
import com.gct.gamecode.t5.util.ImgUtils;

/**
 * 手雷
 * @author Administrator
 *
 */
public class MyFrag implements MyCompent {

	
	private static final String IMG_NAME = "frag";
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
	
	/**
	 * @param intDiameter
	 * @param ptCenter
	 */
	public MyFrag(Point ptCenter, Integer intDiameter) {
		super();
		
		this.ptCenter = ptCenter;
		this.intDiameter = intDiameter;
		
		this.img = ImgUtils.getImgByFileName(IMG_NAME);
	}

	/**
	 * 画图
	 * @param g
	 */
	public void paint(Graphics g) {
		Color oldColor = g.getColor();

//		g.drawImage(null, 0, 0, 0, 0, 0, 0, 0, 0, null);
		g.drawImage(img, 0, 0, null);
		
		
		
		g.setColor(oldColor);
	}
	
	public Point getMyPosition() {
		return this.ptCenter;
	}
}
