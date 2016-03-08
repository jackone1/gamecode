package com.gct.gamecode.t3.listener;

import java.awt.Point;

import com.gct.gamecode.t3.JPanelGame3;

public interface Drawerable {

	/**
	 * 绘制
	 * @param source
	 * @param mousePoint
	 * @return TODO
	 */
	public boolean draw(JPanelGame3 source, Point mousePoint);
	
	/**
	 * 随mouse移动绘制
	 * @param source
	 * @param mousePoint
	 * @return TODO
	 */
	public boolean drawFollowMouse(JPanelGame3 source, Point mousePoint);
}
