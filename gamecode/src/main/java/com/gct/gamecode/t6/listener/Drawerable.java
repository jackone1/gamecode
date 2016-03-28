package com.gct.gamecode.t6.listener;

import java.awt.Point;

import com.gct.gamecode.t6.JPanelGame6;

public interface Drawerable {

	/**
	 * 绘制
	 * @param source
	 * @param mousePoint
	 * @return TODO
	 */
	public boolean draw(JPanelGame6 source, Point mousePoint);
	
	/**
	 * 右击
	 * @param source
	 * @param mousePoint
	 * @return
	 */
	public boolean drawByRightClick(JPanelGame6 source, Point mousePoint);
	
	/**
	 * 随mouse移动绘制
	 * @param source
	 * @param mousePoint
	 * @return TODO
	 */
	public boolean drawFollowMouse(JPanelGame6 source, Point mousePoint);
	
	/**
	 * 生成手雷游戏
	 */
	public void generateGameFrag(JPanelGame6 source);
}
