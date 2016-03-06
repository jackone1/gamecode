package com.gct.gamecode.t2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gct.gamecode.t2.constant.MyConstant2;
import com.gct.gamecode.util.MathUtil;

/**
 * 画圆
 * @author Administrator
 *
 */
public class MyCircle2 implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * x坐标
	 */
	private Integer x;
	private Integer initX;
	/**
	 * y坐标
	 */
	private Integer y;
	private Integer initY;
	/**
	 * 上下的移动步长
	 */
	private Integer stepX;
	/**
	 * 左右的移动步长
	 */
	private Integer stepY;
	/**
	 * 圆宽度
	 */
	private Integer circleWidth;
	/**
	 * 圆高度
	 */
	private Integer circleHeight;
	/**
	 * 圆颜色
	 */
	private Color circleColor;
	
	private JPanelGame parentPanel;
	
	/**
	 * Create the panel.
	 */
	public MyCircle2(JPanelGame parentPanel, int x, int y, int circleWidth, int circleHeight) {
		this.parentPanel = parentPanel;
//		this.x = x;
//		this.y = y;
		this.initX = x;
		this.initY = y;
		this.circleWidth = circleWidth;
		this.circleHeight = circleHeight;
//		recalc();
	}

	public void paint(Graphics g) {
		recalc();
		
		Color oldColor = g.getColor();
		
		g.setColor(circleColor);
		g.fillOval(x, y, circleWidth, circleHeight);

		g.setColor(oldColor);
	}
	
	
	/**
	 * 初始化位置（图形居中）
	 * 注：圆属性的计算可使用通知模式，这样不用每次都重新计算
	 */
	public void recalc() {
		Integer defClientWidth = MyConstant2.DEF_FRAME_WIDTH;
		Integer defClientHeight = MyConstant2.DEF_FRAME_HEIGHT;
		
		circleWidth = Integer.parseInt(MathUtil.round(MathUtil.mul(parentPanel.getZoomRateX().toString(), String.valueOf(MyConstant2.DEF_CIRCLE_WIDTH)), 0));
		x = Integer.parseInt(MathUtil.round(MathUtil.mul(parentPanel.getZoomRateX().toString(), initX.toString()), 0));
		stepX = Integer.parseInt(MathUtil.round(MathUtil.mul(parentPanel.getZoomRateX().toString(), String.valueOf(MyConstant2.DEF_STEP_LEN_X)), 0));
		
		circleHeight = Integer.parseInt(MathUtil.round(MathUtil.mul(parentPanel.getZoomRateY().toString(), String.valueOf(MyConstant2.DEF_CIRCLE_HEIGHT)), 0));
		y = Integer.parseInt(MathUtil.round(MathUtil.mul(parentPanel.getZoomRateY().toString(), initY.toString()), 0));
		stepY = Integer.parseInt(MathUtil.round(MathUtil.mul(parentPanel.getZoomRateY().toString(), String.valueOf(MyConstant2.DEF_STEP_LEN_Y)), 0));

		circleColor = MyConstant2.DEF_CIRCLE_COLOR;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			y += stepX;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			y -= stepX;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= stepX;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += stepX;
		}
		parentPanel.repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getStepX() {
		return stepX;
	}

	public void setStepX(Integer stepX) {
		this.stepX = stepX;
	}

	public Integer getStepY() {
		return stepY;
	}

	public void setStepY(Integer stepY) {
		this.stepY = stepY;
	}

	public Integer getCircleWidth() {
		return circleWidth;
	}

	public void setCircleWidth(Integer circleWidth) {
		this.circleWidth = circleWidth;
	}

	public Integer getCircleHeight() {
		return circleHeight;
	}

	public void setCircleHeight(Integer circleHeight) {
		this.circleHeight = circleHeight;
	}

	public Color getCircleColor() {
		return circleColor;
	}

	public void setCircleColor(Color circleColor) {
		this.circleColor = circleColor;
	}

	public Integer getInitX() {
		return initX;
	}

	public void setInitX(Integer initX) {
		this.initX = initX;
	}

	public Integer getInitY() {
		return initY;
	}

	public void setInitY(Integer initY) {
		this.initY = initY;
	}

}
