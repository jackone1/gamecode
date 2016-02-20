package com.gct.gamecode.t2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.gct.gamecode.t1.constant.MyConstant1;
import com.gct.gamecode.util.MathUtil;

/**
 * 画圆
 * @author Administrator
 *
 */
public class JPanelCircle2 extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * x坐标
	 */
	private Integer x;
	/**
	 * y坐标
	 */
	private Integer y;
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
	
	/**
	 * 上一次的frame宽度
	 */
	private Integer oldClientWidth;
	/**
	 * 上一次的frame高度
	 */
	private Integer oldClientHeight;
	
	
	/**
	 * Create the panel.
	 */
	public JPanelCircle2() {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		init();
		
		Color oldColor = g.getColor();
		
		g.setColor(circleColor);
		g.fillOval(x, y, circleWidth, circleHeight);

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
		circleWidth = circleWidth == null ? MyConstant1.DEF_CIRCLE_WIDTH : circleWidth;
		circleHeight = circleHeight == null ? MyConstant1.DEF_CIRCLE_HEIGHT : circleHeight;
		x = (int) (x == null ? ((clientWidth - circleWidth) / 2) : x);
		y = (int) (y == null ? ((clientHeight - circleHeight) / 2) : y);
		stepX = stepX == null ? MyConstant1.DEF_STEP_LEN_X : stepX;
		stepY = stepY == null ? MyConstant1.DEF_STEP_LEN_Y : stepY;
		
		circleColor = MyConstant1.DEF_CIRCLE_COLOR;
		
		if (!clientWidth.equals(oldClientWidth)) {
			circleWidth = Integer.parseInt(MathUtil.div(MathUtil.mul(clientWidth.toString(), circleWidth.toString()), oldClientWidth.toString(), 0));
			x = Integer.parseInt(MathUtil.div(MathUtil.mul(clientWidth.toString(), x.toString()), oldClientWidth.toString(), 0));
			stepX = Integer.parseInt(MathUtil.div(MathUtil.mul(clientWidth.toString(), stepX.toString()), oldClientWidth.toString(), 0));
			
			oldClientWidth = clientWidth;
		}
		
		if (!clientHeight.equals(oldClientHeight)) {
			circleHeight = Integer.parseInt(MathUtil.div(MathUtil.mul(clientHeight.toString(), circleHeight.toString()), oldClientHeight.toString(), 0));
			y = Integer.parseInt(MathUtil.div(MathUtil.mul(clientHeight.toString(), y.toString()), oldClientHeight.toString(), 0));
			stepY = Integer.parseInt(MathUtil.div(MathUtil.mul(clientHeight.toString(), stepY.toString()), oldClientHeight.toString(), 0));
			
			oldClientHeight = clientHeight;
		}
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
		this.repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

}
