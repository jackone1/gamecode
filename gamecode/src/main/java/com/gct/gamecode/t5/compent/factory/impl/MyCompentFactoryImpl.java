package com.gct.gamecode.t5.compent.factory.impl;

import java.awt.Point;

import com.gct.gamecode.t5.compent.factory.MyCompentFactory;
import com.gct.gamecode.t5.compent.impl.MyNumber;
import com.gct.gamecode.t5.compent.impl.MyNumber.EnumMyNumber;

public class MyCompentFactoryImpl implements MyCompentFactory {

	public MyNumber getMyNumber(int myNo, Point ptCenter, Integer intDiameter) {
		return new MyNumber(EnumMyNumber.getMyNumberByNum(myNo), ptCenter, intDiameter);
	}

}
