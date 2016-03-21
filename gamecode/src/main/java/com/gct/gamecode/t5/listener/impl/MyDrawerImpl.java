package com.gct.gamecode.t5.listener.impl;

import java.awt.Point;

import com.gct.gamecode.t5.JPanelGame5;
import com.gct.gamecode.t5.compent.factory.MyCompentFactory;
import com.gct.gamecode.t5.compent.factory.impl.MyCompentFactoryImpl;
import com.gct.gamecode.t5.compent.impl.MyNumber;
import com.gct.gamecode.t5.listener.Drawerable;
import com.gct.gamecode.util.MathUtil3;

public class MyDrawerImpl implements Drawerable {

	private MyCompentFactory compentFactory;
	
	public MyDrawerImpl() {
		compentFactory = new MyCompentFactoryImpl();
	}
	
	public boolean draw(JPanelGame5 source, Point mousePoint) {
		if (isPointOverBound(source, mousePoint)) {
			return false;
		}

		//计算圆心坐标
		Integer aroundDistance = source.getAroundDistance();
		int vHalfLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf(source.getvLineDistance()), String.valueOf(2), 0));
		int hHalfLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf(source.gethLineDistance()), String.valueOf(2), 0));
		int ptX = Integer.valueOf(MathUtil3.mul(String.valueOf(MathUtil3.divRoundUp((mousePoint.x - aroundDistance), source.getvLineDistance())), String.valueOf(source.getvLineDistance()))) - vHalfLineDistance + aroundDistance;
		int ptY = Integer.valueOf(MathUtil3.mul(String.valueOf(MathUtil3.divRoundUp((mousePoint.y - aroundDistance), source.gethLineDistance())), String.valueOf(source.gethLineDistance()))) - hHalfLineDistance + aroundDistance; 
		System.out.println(String.format("ptX:%s, point.x:%s, aroundDistance:%s, vLineDistance:%s, vHalfLineDistance:%s", ptX, mousePoint.x, aroundDistance, source.getvLineDistance(), vHalfLineDistance));
		System.out.println(String.format("ptY:%s, point.x:%s, aroundDistance:%s, hLineDistance:%s, hHalfLineDistance:%s", ptY, mousePoint.y, aroundDistance, source.gethLineDistance(), hHalfLineDistance));
		
		Point point = new Point(ptX, ptY);
		boolean hasCircle = hasCircle(source, point);
		if (hasCircle) {
			return false;
		}
		
		//计算直径
		int shortDistance = source.getvLineDistance() > source.gethLineDistance() ? source.gethLineDistance() : source.getvLineDistance();
		int intDiameter = Integer.valueOf(MathUtil3.div(String.valueOf(shortDistance), String.valueOf(1.618), 0));
		
		MyNumber myNumber = compentFactory.getMyNumber(8, point, intDiameter);
		source.addMyCompent(myNumber);
		
		source.repaint();
		return true;
	}
	
	public boolean drawFollowMouse(JPanelGame5 source, Point mousePoint) {
		//计算圆心坐标
		int ptX = mousePoint.x;
		int ptY = mousePoint.y;
		
		//计算直径
//		int shortDistance = source.getvLineDistance() > source.gethLineDistance() ? source.gethLineDistance() : source.getvLineDistance();
//		int intDiameter = Integer.valueOf(MathUtil3.div(String.valueOf(shortDistance), String.valueOf(1.618), 0));
//		Point point = new Point(ptX, ptY);
//		MyCircle4 myCircle4 = source.getMouseCircle4();
//		if (myCircle4 == null) {
//			myCircle4 = new MyCircle4(point, intDiameter, this.getMyColor());
//			source.setMouseCircle4(myCircle4);
//		}
//		
//		myCircle4.setPtCenter(point);
//		myCircle4.setCircleColor(getMyColor());

		source.repaint();
		return false;
	}
	
	/**
	 * 是否超过边界线
	 * @param point
	 * @return
	 */
	private boolean isPointOverBound(JPanelGame5 source, Point point) {
		if (point.x < source.getAroundDistance() || point.x > ((source.getVerticalNum() - 1) * source.getvLineDistance() + source.getAroundDistance())) {
			System.out.println("x 超过边界线");
			return true;
		}
		
		if (point.y < source.getAroundDistance() || point.y > ((source.getHorizonalNum() - 1) * source.gethLineDistance() + source.getAroundDistance())) {
			System.out.println("y 超过边界线");
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param ptCenter
	 * @return
	 */
	private boolean hasCircle(JPanelGame5 source, Point ptCenter) {
		String key = ptCenter.x + "+" + ptCenter.y;

//		Map<String, MyCircle4> circleMap = source.getCircleMap();
//		MyCircle4 myCircle4 = circleMap.get(key);
//		if (myCircle4 != null) {
//			System.out.println("棋盘中含有相同位置的圆");
//			return true;
//		}
		
		return false;
	}
	
}
