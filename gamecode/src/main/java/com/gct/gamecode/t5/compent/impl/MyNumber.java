package com.gct.gamecode.t5.compent.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.gct.gamecode.t5.compent.MyCompent;

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
		MY_NUMBER_1(1, new Color(1,1,1)), MY_NUMBER_2(2, new Color(2,2,2)),
		MY_NUMBER_3(3, new Color(3,3,3)), MY_NUMBER_4(4, new Color(4,4,4)),
		MY_NUMBER_5(5, new Color(5,5,5)), MY_NUMBER_6(6, new Color(6,6,6)),
		MY_NUMBER_7(7, new Color(7,7,7)), MY_NUMBER_8(8, new Color(255,0,0))
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
		
		int x = ptCenter.x - intDiameter/2;
		int y = ptCenter.y - intDiameter/2;
		int rectangleWidth = intDiameter;
		int rectangleHeight = intDiameter;

		g.drawString(String.valueOf(myNumber.num), x, y);
		g.fillRect(x, y, rectangleWidth, rectangleHeight);

		g.setColor(oldColor);
	}
	
	public static void main(String[] args) {
		EnumMyNumber valueOf = EnumMyNumber.valueOf("MY_NUMBER_7");
		System.out.println("valueOf: " + valueOf);
	}

	public Point getMyPosition() {
		return this.ptCenter;
	}
}
