package com.gct.gamecode.t4.listener.impl;

import java.awt.Color;

import com.gct.gamecode.t4.JPanelGame4;
import com.gct.gamecode.t4.MyCircle4;
import com.gct.gamecode.t4.listener.Drawerable;

/**
 * 白
 * @author Administrator
 *
 */
public class WhiteDrawer extends AbsMyDrawerImpl implements Drawerable {

	private Color myColor = new Color(255, 255, 255);
	
	@Override
	protected void drawIntenal(JPanelGame4 source, MyCircle4 myCircle4) {

	}

	public Color getMyColor() {
		return myColor;
	}

}
