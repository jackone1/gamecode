package com.gct.gamecode.t2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.gct.gamecode.t2.constant.MyConstant2;

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
	
	
	
	/**
	 * Create the panel.
	 */
	public JPanelGame() {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		init();
		
		Color oldColor = g.getColor();
		
		g.setColor(MyConstant2.DEF_LINE_COLOR);
		drawHorizonalLine(g);
		drawVerticalLine(g);
		
		g.setColor(oldColor);
	}

	/**
	 * 初始化位置（图形居中）
	 */
	private void init() {}

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

}
