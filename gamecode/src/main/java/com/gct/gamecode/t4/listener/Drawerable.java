package com.gct.gamecode.t4.listener;

import java.awt.Point;

import com.gct.gamecode.t4.JPanelGame4;

public interface Drawerable {

	/**
	 * 绘制
	 * @param source
	 * @param mousePoint
	 * @return TODO
	 */
	public boolean draw(JPanelGame4 source, Point mousePoint);
	
	/**
	 * 随mouse移动绘制
	 * @param source
	 * @param mousePoint
	 * @return TODO
	 */
	public boolean drawFollowMouse(JPanelGame4 source, Point mousePoint);
}
