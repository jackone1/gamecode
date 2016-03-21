package com.gct.gamecode.t5.listener;

import java.awt.Point;

import com.gct.gamecode.t5.JPanelGame5;

public interface Drawerable {

	/**
	 * 绘制
	 * @param source
	 * @param mousePoint
	 * @return TODO
	 */
	public boolean draw(JPanelGame5 source, Point mousePoint);
	
	/**
	 * 随mouse移动绘制
	 * @param source
	 * @param mousePoint
	 * @return TODO
	 */
	public boolean drawFollowMouse(JPanelGame5 source, Point mousePoint);
}
