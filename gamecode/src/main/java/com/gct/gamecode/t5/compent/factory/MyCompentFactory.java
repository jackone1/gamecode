package com.gct.gamecode.t5.compent.factory;

import java.awt.Point;

import com.gct.gamecode.t5.compent.impl.MyFrag;
import com.gct.gamecode.t5.compent.impl.MyFrag.EnumMyFrag;
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
	
	/**
	 * 获取手雷
	 * @param ptCenter
	 * @param intDiameter
	 * @return
	 */
	public MyFrag getMyFrag(EnumMyFrag myFrag, Point ptCenter, Integer intDiameter);
}
