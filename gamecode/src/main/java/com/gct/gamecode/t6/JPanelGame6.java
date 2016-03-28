package com.gct.gamecode.t6;

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

import com.gct.gamecode.t6.compent.MyCompent;
import com.gct.gamecode.t6.constant.MyConstant6;
import com.gct.gamecode.t6.listener.Drawerable;
import com.gct.gamecode.t6.listener.impl.MyDrawerImpl;
import com.gct.gamecode.util.MathUtil3;

/**
 * 棋板
 * @author Administrator
 *
 */
public class JPanelGame6 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/** 棋盘的颜色 */
	private Color panelColor = MyConstant6.DEF_PANEL_COLOR;
	/** 线的颜色 */
	private Color lineColor = MyConstant6.DEF_LINE_COLOR;
	/** 周围边界距离 */
	private int aroundDistance = MyConstant6.DEF_AROUND_DISTANCE;
	/** 竖线数 */
	private int verticalNum = MyConstant6.DEF_VERTICAL_NUM;
	/** 横线数 */
	private int horizonalNum = MyConstant6.DEF_HORIZONAL_NUM ;
	/** 手雷数 */
	private int fragNum = MyConstant6.DEF_FRAG_NUM ;
	
	/** 横线间距 */
	private int hLineDistance;
	/** 竖线间距 */
	private int vLineDistance;
	
//	private List<MyCircle4> circleAry;
	/** key:位置坐标(x+y), value:circle*/
	private Map<String, MyCompent> myCompentMap;
//	private MyCircle4 mouseCircle4;
	
	private Drawerable drawer;
	
	/**
	 * Create the panel.
	 */
	public JPanelGame6() {
		myCompentMap = new HashMap<String, MyCompent>();
		drawer = new MyDrawerImpl();
		
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
		drawMyCompent(g);
//		
//		if (mouseCircle4 != null) {
//			mouseCircle4.paint(g);
//		}
	}

	private void init() {
		if (hLineDistance == 0) {
			int height2 = this.getHeight();
			hLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf((height2 - this.aroundDistance * 2)), String.valueOf((this.horizonalNum - 1)), 0));
		}
		
		if (vLineDistance == 0) {
			int width2 = this.getWidth();
			vLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf((width2 - this.aroundDistance * 2)),String.valueOf(this.verticalNum - 1), 0));
		}
		
	}

	/**
	 * 画按钮
	 * @param g
	 */
	public void drawMyCompent(Graphics g) {
		Collection<MyCompent> values = myCompentMap.values();
		for (Iterator<MyCompent> iterator = values.iterator(); iterator.hasNext();) {
			MyCompent myCompent = (MyCompent) iterator.next();
			myCompent.paint(g);
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
			System.out.println("current mouse point(" + mousePoint.x + "," + mousePoint.y + ")");
			
//			drawer.drawFollowMouse(JPanelGame5.this, mousePoint);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			if (e.getButton() == MouseEvent.BUTTON3) {
				drawer.drawByRightClick(JPanelGame6.this, e.getPoint());
			} else if (e.getButton() == MouseEvent.BUTTON1) {
				drawer.draw(JPanelGame6.this, e.getPoint());
			}
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

	public int getFragNum() {
		return fragNum;
	}

	public void setFragNum(int fragNum) {
		this.fragNum = fragNum;
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

//	public Map<String, MyCircle4> getCircleMap() {
//		return circleMap;
//	}
//
//	public void addCircle(String key, MyCircle4 value) {
//		this.getCircleMap().put(key, value);
//	}
//	
//	public void setCircleMap(Map<String, MyCircle4> circleMap) {
//		this.circleMap = circleMap;
//	}

	public Color getPanelColor() {
		return panelColor;
	}

	public void setPanelColor(Color panelColor) {
		this.panelColor = panelColor;
	}
	
	public Map<String, MyCompent> getMyCompentMap() {
		return myCompentMap;
	}

	public void setMyCompentMap(Map<String, MyCompent> myCompentMap) {
		this.myCompentMap = myCompentMap;
	}
}
