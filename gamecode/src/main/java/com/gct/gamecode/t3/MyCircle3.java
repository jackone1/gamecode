package com.gct.gamecode.t3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 画圆
 * @author Administrator
 *
 */
public class MyCircle3 {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 圆心
	 */
	private Point ptCenter;
	/**
	 * 圆直径
	 */
	private Integer intDiameter;
	/**
	 * 圆颜色
	 */
	private Color circleColor;

	/**
	 * 构造
	 * @param ptCenter 圆心
	 * @param intDiameter 圆直径（直径转化为偶数）
	 * @param circleColor 圆颜色
	 */
	public MyCircle3(Point ptCenter, Integer intDiameter, Color circleColor) {
		super();
		if (ptCenter == null || intDiameter == null || circleColor == null) {
			throw new NullPointerException("圆心位置/直径或颜色为空");
		}
		
		this.ptCenter = ptCenter;
		//直径转化为偶数
		this.intDiameter = intDiameter/2 * 2;
		this.circleColor = circleColor;
	}

	public void paint(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(circleColor);
		
		int x = ptCenter.x - intDiameter/2;
		int y = ptCenter.y - intDiameter/2;
		int circleWidth = intDiameter;
		int circleHeight = intDiameter;
		g.fillOval(x, y, circleWidth, circleHeight);

		g.setColor(oldColor);
	}

	public Point getPtCenter() {
		return ptCenter;
	}

	public void setPtCenter(Point ptCenter) {
		this.ptCenter = ptCenter;
	}

	public Integer getIntDiameter() {
		return intDiameter;
	}

	public void setIntDiameter(Integer intDiameter) {
		this.intDiameter = intDiameter;
	}

	public Color getCircleColor() {
		return circleColor;
	}

	public void setCircleColor(Color circleColor) {
		this.circleColor = circleColor;
	}

}
