package com.gct.gamecode.t3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;

import com.gct.gamecode.t3.constant.MyConstant3;
import com.gct.gamecode.t3.listener.Drawerable;
import com.gct.gamecode.t3.listener.impl.BlackDrawer;
import com.gct.gamecode.t3.listener.impl.DrawerSwitcherImpl;
import com.gct.gamecode.t3.listener.impl.WhiteDrawer;

/**
 * 棋板
 * @author Administrator
 *
 */
public class JPanelGame3 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/** 棋盘的颜色 */
	private Color panelColor = MyConstant3.DEF_PANEL_COLOR;
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
	
//	private List<MyCircle3> circleAry;
	/** key:位置坐标(x+y), value:circle*/
	private Map<String, MyCircle3> circleMap;
	private MyCircle3 mouseCircle3;
	
	private Drawerable drawer;
	
	/**
	 * Create the panel.
	 */
	public JPanelGame3() {
//		circleAry = new ArrayList<MyCircle3>();
		circleMap = new HashMap<String, MyCircle3>();
		
		BlackDrawer black = new BlackDrawer();
		WhiteDrawer white = new WhiteDrawer();
		Drawerable first = black;
		drawer = new DrawerSwitcherImpl(first, white, black);
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(panelColor);

		this.addMouseListener(new MyMouseAdapter());
		this.addMouseMotionListener(new MyMouseAdapter());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		init();
		
		drawHorizonalLine(g);
		drawVerticalLine(g);
		drawMyCircle(g);
		
		if (mouseCircle3 != null) {
			mouseCircle3.paint(g);
		}
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
		Collection<MyCircle3> values = circleMap.values();
		for (Iterator iterator = values.iterator(); iterator.hasNext();) {
			MyCircle3 myCircle3 = (MyCircle3) iterator.next();
			myCircle3.paint(g);
		}
	}
	
	/**
	 * 画横线
	 * @param g
	 */
	public void drawHorizonalLine(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(lineColor);
		
		int headPointX = this.aroundDistance;
		int nextHeadPointY = this.aroundDistance;
		int tailPointX = this.aroundDistance + (this.horizonalNum - 1) * vLineDistance;
		int nextTailPointY = this.aroundDistance;
		for (int i = 0; i < this.horizonalNum; i++) {
			g.drawLine(headPointX, (nextHeadPointY + i * hLineDistance), tailPointX, (nextTailPointY + i * hLineDistance));
		}
		
		g.setColor(oldColor);
	}
	
	/**
	 * 画竖线
	 * @param g
	 */
	public void drawVerticalLine(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(lineColor);
		
		int headPointY = this.aroundDistance;
		int nextHeadPointX = this.aroundDistance;
		int tailPointY = this.aroundDistance + (this.verticalNum - 1) * hLineDistance;
		int nextTailPointX = this.aroundDistance;
		for (int i = 0; i < this.verticalNum; i++) {
			g.drawLine((nextHeadPointX + i * vLineDistance), headPointY, (nextTailPointX + i * vLineDistance), tailPointY);
		}

		g.setColor(oldColor);
	}

	class MyMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			super.mouseMoved(e);

			Point mousePoint = e.getPoint();
//			System.out.println("current mouse point(" + mousePoint.x + "," + mousePoint.y + ")");
			
			drawer.drawFollowMouse(JPanelGame3.this, mousePoint);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			drawer.draw(JPanelGame3.this, e.getPoint());
		}
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public int getAroundDistance() {
		return aroundDistance;
	}

	public void setAroundDistance(int aroundDistance) {
		this.aroundDistance = aroundDistance;
	}

	public int getVerticalNum() {
		return verticalNum;
	}

	public void setVerticalNum(int verticalNum) {
		this.verticalNum = verticalNum;
	}

	public int getHorizonalNum() {
		return horizonalNum;
	}

	public void setHorizonalNum(int horizonalNum) {
		this.horizonalNum = horizonalNum;
	}

	public int gethLineDistance() {
		return hLineDistance;
	}

	public void sethLineDistance(int hLineDistance) {
		this.hLineDistance = hLineDistance;
	}

	public int getvLineDistance() {
		return vLineDistance;
	}

	public void setvLineDistance(int vLineDistance) {
		this.vLineDistance = vLineDistance;
	}

	public Map<String, MyCircle3> getCircleMap() {
		return circleMap;
	}

	public void addCircle(String key, MyCircle3 value) {
		this.getCircleMap().put(key, value);
	}
	
	public void setCircleMap(Map<String, MyCircle3> circleMap) {
		this.circleMap = circleMap;
	}

	public Color getPanelColor() {
		return panelColor;
	}

	public void setPanelColor(Color panelColor) {
		this.panelColor = panelColor;
	}

	public MyCircle3 getMouseCircle3() {
		return mouseCircle3;
	}

	public void setMouseCircle3(MyCircle3 mouseCircle3) {
		this.mouseCircle3 = mouseCircle3;
	}

	public Drawerable getDrawer() {
		return drawer;
	}

	public void setDrawer(Drawerable drawer) {
		this.drawer = drawer;
	}
	
}
