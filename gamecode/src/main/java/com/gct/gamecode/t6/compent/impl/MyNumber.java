package com.gct.gamecode.t6.compent.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import com.gct.gamecode.t6.compent.MyCompent;

/**
 * 数字图标（距形）
 * 8个数字：1-8
 * @author Administrator
 *
 */
public class MyNumber implements MyCompent {
	
	/**
	 * 数字号：1-8
	 */
	private EnumMyNumber myNumber;
	/**
	 * 距形长和宽
	 */
	private Integer intDiameter;
	/**
	 * 距形中心点
	 */
	private Point ptCenter;
	
	public enum EnumMyNumber {
		MY_NUMBER_1(1, new Color(128,0,0)), MY_NUMBER_2(2, new Color(255,128,0)),
		MY_NUMBER_3(3, new Color(0,128,0)), MY_NUMBER_4(4, new Color(0,128,64)),
		MY_NUMBER_5(5, new Color(0,0,255)), MY_NUMBER_6(6, new Color(0,0,160)),
		MY_NUMBER_7(7, new Color(128,0,128)), MY_NUMBER_8(8, new Color(128,0,255)),
		MY_NUMBER_0(0, new Color(0,0,0))
		;
		
		int num;
		Color color;
		EnumMyNumber(int num, Color color) {
			this.num = num;
			this.color = color;
		}
		
		public static EnumMyNumber getMyNumberByNum(int num) {
			EnumMyNumber[] values = EnumMyNumber.values();
			for (EnumMyNumber enumMyNumber : values) {
				if (enumMyNumber.num == num) {
					return enumMyNumber;
				}
			}
			
			return null;
		}
	}
	/**
	 * @param myNumber
	 * @param intDiameter
	 * @param ptCenter
	 */
	public MyNumber(EnumMyNumber myNumber, Point ptCenter, Integer intDiameter) {
		super();
		
		this.myNumber = myNumber;
		this.ptCenter = ptCenter;
		this.intDiameter = intDiameter;
	}

	/**
	 * 画图
	 * @param g
	 */
	public void paint(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(myNumber.color);
		
		int rectangleWidth = intDiameter;
		int rectangleHeight = intDiameter;

		Font font = new Font(null, Font.PLAIN, intDiameter);
		g.setFont(font);
		
		int sx = ptCenter.x - intDiameter/4;
		int sy = ptCenter.y + intDiameter * 2/5;
		g.drawString(String.valueOf(myNumber.num), sx, sy);

		int x = ptCenter.x - intDiameter/2;
		int y = ptCenter.y - intDiameter/2;
		g.drawRect(x, y, rectangleWidth, rectangleHeight);

		g.setColor(oldColor);
	}
	
	public static void main(String[] args) {
		EnumMyNumber valueOf = EnumMyNumber.valueOf("MY_NUMBER_7");
		System.out.println("valueOf: " + valueOf);
	}

	public Point getMyPosition() {
		return this.ptCenter;
	}

	public EnumMyNumber getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(EnumMyNumber myNumber) {
		this.myNumber = myNumber;
	}

	public Integer getIntDiameter() {
		return intDiameter;
	}

	public void setIntDiameter(Integer intDiameter) {
		this.intDiameter = intDiameter;
	}

	public Point getPtCenter() {
		return ptCenter;
	}

	public void setPtCenter(Point ptCenter) {
		this.ptCenter = ptCenter;
	}
}
