package com.gct.gamecode.t4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 画圆
 * @author Administrator
 *
 */
public class MyCircle4 {

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
	public MyCircle4(Point ptCenter, Integer intDiameter, Color circleColor) {
		super();
		if (ptCenter == null || intDiameter == null || circleColor == null) {
			throw new NullPointerException("圆心位置/直径或颜色为空");
		}
		
		this.ptCenter = ptCenter;
		//直径转化为偶数
		this.intDiameter = intDiameter/2 * 2;
		this.circleColor = circleColor;
	}

	/**
	 * 是否为相同的圆心
	 * @param ptCenter 圆心
	 * @return
	 */
	public boolean isSamePointCenter(Point ptCenter) {
		return this.ptCenter.equals(ptCenter);
	}
	
	/**
	 * 是否相同的颜色
	 * @param otherColor
	 * @return
	 */
	public boolean isSameColor(Color otherColor) {
		return this.getCircleColor().equals(otherColor);
	}
	
	/**
	 * 实现渐变
	 * @param targetColor
	 * @param g
	 */
	public void changeColor(Color targetColor, Graphics g) {
		//
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
