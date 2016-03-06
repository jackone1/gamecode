package com.gct.gamecode.t3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.gct.gamecode.t3.constant.MyConstant3;
import com.gct.gamecode.util.MathUtil3;

/**
 * 棋板
 * @author Administrator
 *
 */
public class JPanelGame3 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/** 线的颜色 */
	private Color lineColor = MyConstant3.DEF_LINE_COLOR;
	/** 周围边界距离 */
	private int aroundDistance = MyConstant3.DEF_AROUND_DISTANCE;
	/** 竖线数 */
	private int verticalNum = MyConstant3.DEF_VERTICAL_NUM;
	/** 横线数 */
	private int horizonalNum = MyConstant3.DEF_HORIZONAL_NUM ;
	
	/** 横线间距 */
	private int hLineDistance;
	/** 竖线间距 */
	private int vLineDistance;
	
	
	private List<MyCircle3> circleAry;
	
	/**
	 * Create the panel.
	 */
	public JPanelGame3() {
		circleAry = new ArrayList<MyCircle3>();
		this.setLayout(new BorderLayout(0, 0));

		this.addMouseListener(new MyMouseAdapter());
		this.addMouseMotionListener(new MyMouseAdapter());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		init();
		
		Color oldColor = g.getColor();
		
		g.setColor(lineColor);
		drawHorizonalLine(g);
		drawVerticalLine(g);
		drawMyCircle(g);
		
		g.setColor(oldColor);
	}

	private void init() {
		if (hLineDistance == 0) {
			int height2 = this.getHeight();
			hLineDistance = (height2 - this.aroundDistance * 2) / (this.horizonalNum - 1);
		}
		
		if (vLineDistance == 0) {
			int width2 = this.getWidth();
			vLineDistance = (width2 - this.aroundDistance * 2) / (this.verticalNum - 1);
		}
		
	}

	/**
	 * 画按钮
	 * @param g
	 */
	public void drawMyCircle(Graphics g) {
		for (int i = 0; i < circleAry.size(); i++) {
			MyCircle3 myCircle3 = circleAry.get(i);
			myCircle3.paint(g);
		}
	}
	
	/**
	 * 画横线
	 * @param g
	 */
	public void drawHorizonalLine(Graphics g) {
		int headPointX = this.aroundDistance;
		int nextHeadPointY = this.aroundDistance;
		int tailPointX = this.aroundDistance + (this.horizonalNum - 1) * vLineDistance;
		int nextTailPointY = this.aroundDistance;
		for (int i = 0; i < this.horizonalNum; i++) {
			g.drawLine(headPointX, (nextHeadPointY + i * hLineDistance), tailPointX, (nextTailPointY + i * hLineDistance));
		}
	}
	
	/**
	 * 画竖线
	 * @param g
	 */
	public void drawVerticalLine(Graphics g) {
		int headPointY = this.aroundDistance;
		int nextHeadPointX = this.aroundDistance;
		int tailPointY = this.aroundDistance + (this.verticalNum - 1) * hLineDistance;
		int nextTailPointX = this.aroundDistance;
		for (int i = 0; i < this.verticalNum; i++) {
			g.drawLine((nextHeadPointX + i * vLineDistance), headPointY, (nextTailPointX + i * vLineDistance), tailPointY);
		}
	}

	class MyMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			super.mouseMoved(e);

			Point mousePoint = e.getPoint();
			System.out.println("mouse point(" + mousePoint.x + "," + mousePoint.y + ")");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			
			Point mousePoint = e.getPoint();
			if (isPointOverBound(mousePoint)) {
				return;
			}
			
			//计算圆心坐标
			int ptX = mousePoint.x / vLineDistance * vLineDistance + aroundDistance;
			int ptY = mousePoint.y / hLineDistance * hLineDistance + aroundDistance;
			System.out.println("add new circle(ptX:" + ptX + ", ptY:" + ptY + ")");
			
			//计算直径
			int shortDistance = vLineDistance > hLineDistance ? hLineDistance : vLineDistance;
			int intDiameter = Integer.valueOf(MathUtil3.div(String.valueOf(shortDistance), String.valueOf(1.618), 0));
			Color color = MyConstant3.DEF_CIRCLE_COLOR;
			
			JPanelGame3.this.circleAry.add(new MyCircle3(new Point(ptX, ptY), intDiameter, color));
			JPanelGame3.this.repaint();
		}
		
		/**
		 * 是否超过边界线
		 * @param point
		 * @return
		 */
		private boolean isPointOverBound(Point point) {
			if (point.x < aroundDistance || point.x > ((verticalNum - 1) * vLineDistance + aroundDistance)) {
				System.out.println("x 超过边界线");
				return true;
			}
			
			if (point.y < aroundDistance || point.y > ((horizonalNum - 1) * hLineDistance + aroundDistance)) {
				System.out.println("y 超过边界线");
				return true;
			}
			
			return false;
		}
	}
}
