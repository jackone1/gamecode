package com.gct.gamecode.t2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.gct.gamecode.t2.constant.MyConstant2;
import com.gct.gamecode.util.MathUtil3;

/**
 * 画圆
 * @author Administrator
 *
 */
public class JPanelGame extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<MyCircle2> circleAry;
	
	/**
	 * 上一次的frame宽度
	 */
	private Integer oldClientWidth = null;
	/**
	 * 上一次的frame高度
	 */
	private Integer oldClientHeight = null;
	
	private Double zoomRateX = 1d;
	private Double zoomRateY = 1d;
	
	/**
	 * Create the panel.
	 */
	public JPanelGame() {
		circleAry = new ArrayList<MyCircle2>();
		this.setLayout(new BorderLayout(0, 0));

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		init();
		
		Color oldColor = g.getColor();
		
		g.setColor(MyConstant2.DEF_LINE_COLOR);
		drawHorizonalLine(g);
		drawVerticalLine(g);
		drawMyCircle2(g);
		
//		g.fill3DRect(0, 0, 100, 100, false);
		
		g.setColor(oldColor);
	}

	/**
	 * 初始化位置（图形居中）
	 */
	private void init() {
		Integer clientWidth = this.getWidth();
		Integer clientHeight = this.getHeight();
		
		oldClientWidth = oldClientWidth == null ? clientWidth : oldClientWidth;
		oldClientHeight = oldClientHeight == null ? clientHeight : oldClientHeight;
//		zoomRateX = Double.valueOf(MathUtil.div(clientWidth.toString(), this.oldClientWidth.toString()));
//		zoomRateY = Double.valueOf(MathUtil.div(clientHeight.toString(), this.oldClientHeight.toString()));
		zoomRateX = Double.valueOf(MathUtil3.div(clientWidth.toString(), String.valueOf(MyConstant2.DEF_FRAME_WIDTH)));
		zoomRateY = Double.valueOf(MathUtil3.div(clientHeight.toString(), String.valueOf(MyConstant2.DEF_FRAME_HEIGHT)));
		
		if (this.getWidth() != this.oldClientWidth) {
			this.oldClientWidth = this.getWidth();
		}
		
		if (this.getHeight() != this.oldClientHeight) {
			this.oldClientHeight = this.getHeight();
		}
	}

	/**
	 * 画按钮
	 * @param g
	 */
	public void drawMyCircle2(Graphics g) {
		for (int i = 0; i < circleAry.size(); i++) {
			MyCircle2 myCircle2 = circleAry.get(i);
			myCircle2.paint(g);
		}
	}
	
	/**
	 * 画横线
	 * @param g
	 */
	public void drawHorizonalLine(Graphics g) {
		int height2 = this.getHeight();
		int width2 = this.getWidth();
		int horizonalLineDistance = (height2 - MyConstant2.DEF_AROUND_DISTANCE * 2) / (MyConstant2.DEF_HORIZONAL_NUM - 1);
		int verticalLineDistance = (width2 - MyConstant2.DEF_AROUND_DISTANCE * 2) / (MyConstant2.DEF_VERTICAL_NUM - 1);
		
		int headPointX = MyConstant2.DEF_AROUND_DISTANCE;
		int nextHeadPointY = MyConstant2.DEF_AROUND_DISTANCE;
		int tailPointX = MyConstant2.DEF_AROUND_DISTANCE + (MyConstant2.DEF_VERTICAL_NUM - 1) * verticalLineDistance;
		int nextTailPointY = MyConstant2.DEF_AROUND_DISTANCE;
		for (int i = 0; i < MyConstant2.DEF_HORIZONAL_NUM; i++) {
			g.drawLine(headPointX, nextHeadPointY, tailPointX ,nextTailPointY);

			nextHeadPointY = nextHeadPointY + horizonalLineDistance;
			nextTailPointY = nextTailPointY + horizonalLineDistance;
		}
		
	}
	
	/**
	 * 画竖线
	 * @param g
	 */
	public void drawVerticalLine(Graphics g) {
		int height2 = this.getHeight();
		int width2 = this.getWidth();
		int horizonalLineDistance = (height2 - MyConstant2.DEF_AROUND_DISTANCE * 2) / (MyConstant2.DEF_HORIZONAL_NUM - 1);
		int verticalLineDistance = (width2 - MyConstant2.DEF_AROUND_DISTANCE * 2) / (MyConstant2.DEF_VERTICAL_NUM - 1);
		
		int headPointY = MyConstant2.DEF_AROUND_DISTANCE;
		int nextHeadPointX = MyConstant2.DEF_AROUND_DISTANCE;
		int tailPointY = MyConstant2.DEF_AROUND_DISTANCE + (MyConstant2.DEF_VERTICAL_NUM - 1) * horizonalLineDistance;
		int nextTailPointX = MyConstant2.DEF_AROUND_DISTANCE;
		for (int i = 0; i < MyConstant2.DEF_VERTICAL_NUM; i++) {
			g.drawLine(nextHeadPointX, headPointY, nextTailPointX ,tailPointY);

			nextHeadPointX = nextHeadPointX + verticalLineDistance;
			nextTailPointX = nextTailPointX + verticalLineDistance;
		}
	}
	
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		int x = (int) (point.x - this.zoomRateX * MyConstant2.DEF_CIRCLE_WIDTH / 2);
		int y = (int) (point.y - this.zoomRateY * MyConstant2.DEF_CIRCLE_HEIGHT / 2);
		int circleWidth = 0;
		int circleHeight = 0;
		
		MyCircle2 circle = new MyCircle2(this, x, y, circleWidth, circleHeight);
		
		circleAry.add(circle);
		this.repaint();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public List<MyCircle2> getCircleAry() {
		return circleAry;
	}

	public void setCircleAry(List<MyCircle2> circleAry) {
		this.circleAry = circleAry;
	}

	public Integer getOldClientWidth() {
		return oldClientWidth;
	}

	public void setOldClientWidth(Integer oldClientWidth) {
		this.oldClientWidth = oldClientWidth;
	}

	public Integer getOldClientHeight() {
		return oldClientHeight;
	}

	public void setOldClientHeight(Integer oldClientHeight) {
		this.oldClientHeight = oldClientHeight;
	}

	public Double getZoomRateX() {
		return zoomRateX;
	}

	public void setZoomRateX(Double zoomRateX) {
		this.zoomRateX = zoomRateX;
	}

	public Double getZoomRateY() {
		return zoomRateY;
	}

	public void setZoomRateY(Double zoomRateY) {
		this.zoomRateY = zoomRateY;
	}

}
