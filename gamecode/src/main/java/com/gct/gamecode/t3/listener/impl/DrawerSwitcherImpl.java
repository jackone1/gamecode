package com.gct.gamecode.t3.listener.impl;

import java.awt.Point;

import com.gct.gamecode.t3.JPanelGame3;
import com.gct.gamecode.t3.listener.Drawerable;

/**
 * 不同绘制者的切换
 * @author Administrator
 *
 */
public class DrawerSwitcherImpl implements Drawerable {

	private WhiteDrawer white;
	private BlackDrawer black;
	
	private Drawerable current;
	
	/**
	 * 构造
	 * @param first 先手
	 * @param white 白手
	 * @param black 黑手
	 */
	public DrawerSwitcherImpl(Drawerable first, WhiteDrawer white, BlackDrawer black) {
		this.current = first;
		this.white = white;
		this.black = black;
	}
	
	/**
	 * 切换绘制者
	 */
	private void toggle() {
		if (current.equals(black)) {
			current = white;
		} else if (current.equals(white)) {
			current = black;
		} else {
			System.out.println("ERROR: toggle nothing...");
		}
	}
	
	/**
	 * 绘制之后，切换绘制者
	 */
	public boolean draw(JPanelGame3 source, Point mousePoint) {
		boolean draw = current.draw(source, mousePoint);
		if (draw) {
			toggle();
		}
		return draw;
	}

	public boolean drawFollowMouse(JPanelGame3 source, Point mousePoint) {
		current.drawFollowMouse(source, mousePoint);
		return false;
	}

}
