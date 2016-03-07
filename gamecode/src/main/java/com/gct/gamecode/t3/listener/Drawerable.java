package com.gct.gamecode.t3.listener;

import java.awt.Point;

import com.gct.gamecode.t3.JPanelGame3;

public interface Drawerable {

	/**
	 * 绘制
	 * @param source
	 * @param mousePoint
	 */
	public void draw(JPanelGame3 source, Point mousePoint);
}
