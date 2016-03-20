package com.gct.gamecode.t4.listener.impl;

import java.awt.Color;

import com.gct.gamecode.t4.JPanelGame4;
import com.gct.gamecode.t4.MyCircle4;
import com.gct.gamecode.t4.listener.Drawerable;

/**
 * 黑
 * @author Administrator
 *
 */
public class BlackDrawer extends AbsMyDrawerImpl implements Drawerable {

	private Color myColor = new Color(0, 0, 0);
	
	@Override
	protected void drawIntenal(JPanelGame4 source, MyCircle4 myCircle4) {
		
	}

	public Color getMyColor() {
		return myColor;
	}
}
