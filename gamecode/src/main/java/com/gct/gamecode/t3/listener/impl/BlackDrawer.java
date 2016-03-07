package com.gct.gamecode.t3.listener.impl;

import java.awt.Color;

import com.gct.gamecode.t3.JPanelGame3;
import com.gct.gamecode.t3.MyCircle3;
import com.gct.gamecode.t3.listener.Drawerable;

/**
 * 黑
 * @author Administrator
 *
 */
public class BlackDrawer extends AbsMyDrawerImpl implements Drawerable {

	private Color myColor = new Color(0, 0, 0);
	
	@Override
	protected void drawIntenal(JPanelGame3 source, MyCircle3 myCircle3) {
		
	}

	public Color getMyColor() {
		return myColor;
	}
}
