package com.gct.gamecode.t5.compent.factory;

import java.awt.Point;

import com.gct.gamecode.t5.compent.impl.MyNumber;

public interface MyCompentFactory {

	/**
	 * 根据号码，获取数字
	 * @param myNo
	 * @param ptCenter
	 * @param intDiameter
	 * @return
	 */
	public MyNumber getMyNumber(int myNo, Point ptCenter, Integer intDiameter);
}
