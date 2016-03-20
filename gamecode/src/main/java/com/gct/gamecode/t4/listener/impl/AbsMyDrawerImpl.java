package com.gct.gamecode.t4.listener.impl;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gct.gamecode.t4.JPanelGame4;
import com.gct.gamecode.t4.MyCircle4;
import com.gct.gamecode.t4.constant.MyConstant4;
import com.gct.gamecode.t4.listener.Drawerable;
import com.gct.gamecode.util.MathUtil3;

public abstract class AbsMyDrawerImpl implements Drawerable {

	public boolean draw(JPanelGame4 source, Point mousePoint) {
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
		
		MyCircle4 myCircle4 = new MyCircle4(point, intDiameter, this.getMyColor());
		source.addCircle((ptX + "+" + ptY), myCircle4);
		System.out.println(source.getCircleMap().keySet().toString());
		
		drawIntenal(source, myCircle4);
		
		eatCircle(source, myCircle4);
		source.repaint();
		return true;
	}
	
	public boolean drawFollowMouse(JPanelGame4 source, Point mousePoint) {
		//计算圆心坐标
		int ptX = mousePoint.x;
		int ptY = mousePoint.y;
		
		//计算直径
		int shortDistance = source.getvLineDistance() > source.gethLineDistance() ? source.gethLineDistance() : source.getvLineDistance();
		int intDiameter = Integer.valueOf(MathUtil3.div(String.valueOf(shortDistance), String.valueOf(1.618), 0));
		Point point = new Point(ptX, ptY);
		MyCircle4 myCircle4 = source.getMouseCircle4();
		if (myCircle4 == null) {
			myCircle4 = new MyCircle4(point, intDiameter, this.getMyColor());
			source.setMouseCircle4(myCircle4);
		}
		
		myCircle4.setPtCenter(point);
		myCircle4.setCircleColor(getMyColor());

		source.repaint();
		return false;
	}
	
	private void eatCircle(JPanelGame4 source, MyCircle4 myCircle4) {
		List<MyCircle4> needEatedList = new ArrayList<MyCircle4>();
		needEatedList.addAll(findHeadAndTailWithDrUp(source, myCircle4));
		needEatedList.addAll(findHeadAndTailWithDrDown(source, myCircle4));
		needEatedList.addAll(findHeadAndTailWithDrLeft(source, myCircle4));
		needEatedList.addAll(findHeadAndTailWithDrRight(source, myCircle4));
		needEatedList.addAll(findHeadAndTailWithDrUpLeft(source, myCircle4));
		needEatedList.addAll(findHeadAndTailWithDrUpRight(source, myCircle4));
		needEatedList.addAll(findHeadAndTailWithDrDownLeft(source, myCircle4));
		needEatedList.addAll(findHeadAndTailWithDrDownRight(source, myCircle4));
		
		change2MyColor(needEatedList);
	}
	
	private void change2MyColor(List<MyCircle4> needEatedList) {
		if (needEatedList == null || needEatedList.isEmpty()) {
			return;
		}
		
		for (MyCircle4 myCircle4 : needEatedList) {
			myCircle4.setCircleColor(getMyColor());
		}
	}
	
	/**
	 * 向上
	 */
	private List<MyCircle4> findHeadAndTailWithDrUp(JPanelGame4 source, MyCircle4 myCircle4) {
		int upBound = source.getAroundDistance(); //上边界
		Map<String, MyCircle4> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle4.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle4> circleList = new ArrayList<MyCircle4>();
		while(y >= upBound) {
			y = y - source.gethLineDistance();
			
			MyCircle4 myCircle42 = circleMap.get(x + "+" + y);
			if (myCircle42 == null) {
				break;
			}
			if (myCircle42.isSameColor(myCircle4.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle42);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle4>();
			System.out.println("向上未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 向下
	 * @param source
	 * @param myCircle4
	 * @return
	 */
	private List<MyCircle4> findHeadAndTailWithDrDown(JPanelGame4 source, MyCircle4 myCircle4) {
		int downBound = source.getAroundDistance() + (source.getHorizonalNum() - 1) * source.gethLineDistance(); //下边界
		Map<String, MyCircle4> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle4.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle4> circleList = new ArrayList<MyCircle4>();
		while(y <= downBound) {
			y = y + source.gethLineDistance();
			
			MyCircle4 myCircle42 = circleMap.get(x + "+" + y);
			if (myCircle42 == null) {
				break;
			}
			if (myCircle42.isSameColor(myCircle4.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle42);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle4>();
			System.out.println("向下未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 向左
	 * @param source
	 * @param myCircle4
	 * @return
	 */
	private List<MyCircle4> findHeadAndTailWithDrLeft(JPanelGame4 source, MyCircle4 myCircle4) {
		int leftBound = source.getAroundDistance(); //左边界
		Map<String, MyCircle4> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle4.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle4> circleList = new ArrayList<MyCircle4>();
		while(x >= leftBound) {
			x = x - source.getvLineDistance();
			
			MyCircle4 myCircle42 = circleMap.get(x + "+" + y);
			if (myCircle42 == null) {
				break;
			}
			if (myCircle42.isSameColor(myCircle4.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle42);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle4>();
			System.out.println("向左未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 向右
	 * @param source
	 * @param myCircle4
	 * @return
	 */
	private List<MyCircle4> findHeadAndTailWithDrRight(JPanelGame4 source, MyCircle4 myCircle4) {
		int rightBound = source.getAroundDistance() + ((source.getVerticalNum() - 1) * source.getvLineDistance()); //右边界
		Map<String, MyCircle4> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle4.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle4> circleList = new ArrayList<MyCircle4>();
		while(x <= rightBound) {
			x = x + source.getvLineDistance();
			
			MyCircle4 myCircle42 = circleMap.get(x + "+" + y);
			if (myCircle42 == null) {
				break;
			}
			if (myCircle42.isSameColor(myCircle4.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle42);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle4>();
			System.out.println("向右未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 左上角
	 * @param source
	 * @param myCircle4
	 * @return
	 */
	private List<MyCircle4> findHeadAndTailWithDrUpLeft(JPanelGame4 source, MyCircle4 myCircle4) {
		int leftBound = source.getAroundDistance(); //左边界
		int upBound = source.getAroundDistance(); //上边界
		Map<String, MyCircle4> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle4.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle4> circleList = new ArrayList<MyCircle4>();
		while(x >= leftBound && y >= upBound) {
			x = x - source.getvLineDistance(); y = y - source.gethLineDistance();
			
			MyCircle4 myCircle42 = circleMap.get(x + "+" + y);
			if (myCircle42 == null) {
				break;
			}
			if (myCircle42.isSameColor(myCircle4.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle42);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle4>();
			System.out.println("向左上未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 右上角
	 * @param source
	 * @param myCircle4
	 * @return
	 */
	private List<MyCircle4> findHeadAndTailWithDrUpRight(JPanelGame4 source, MyCircle4 myCircle4) {
		int upBound = source.getAroundDistance(); //上边界
		int rightBound = source.getAroundDistance() + ((source.getVerticalNum() - 1) * source.getvLineDistance()); //右边界
		Map<String, MyCircle4> circleMap = source.getCircleMap();

		Point ptCenter = myCircle4.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle4> circleList = new ArrayList<MyCircle4>();
		while(x <= rightBound && y >= upBound) {
			x = x + source.getvLineDistance(); y = y - source.gethLineDistance();
			
			MyCircle4 myCircle42 = circleMap.get(x + "+" + y);
			if (myCircle42 == null) {
				break;
			}
			if (myCircle42.isSameColor(myCircle4.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle42);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle4>();
			System.out.println("向右上未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 左下角
	 * @param source
	 * @param myCircle4
	 * @return
	 */
	private List<MyCircle4> findHeadAndTailWithDrDownLeft(JPanelGame4 source, MyCircle4 myCircle4) {
		int downBound = source.getAroundDistance() + (source.getHorizonalNum() - 1) * source.gethLineDistance(); //下边界
		int leftBound = source.getAroundDistance(); //左边界
		Map<String, MyCircle4> circleMap = source.getCircleMap();

		Point ptCenter = myCircle4.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle4> circleList = new ArrayList<MyCircle4>();
		while(x >= leftBound && y <= downBound) {
			x = x - source.getvLineDistance(); y = y + source.gethLineDistance();
			
			MyCircle4 myCircle42 = circleMap.get(x + "+" + y);
			if (myCircle42 == null) {
				break;
			}
			if (myCircle42.isSameColor(myCircle4.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle42);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle4>();
			System.out.println("向左下未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 右下角
	 * @param source
	 * @param myCircle4
	 * @return
	 */
	private List<MyCircle4> findHeadAndTailWithDrDownRight(JPanelGame4 source, MyCircle4 myCircle4) {
		int downBound = source.getAroundDistance() + (source.getHorizonalNum() - 1) * source.gethLineDistance(); //下边界
		int rightBound = source.getAroundDistance() + ((source.getVerticalNum() - 1) * source.getvLineDistance()); //右边界
		Map<String, MyCircle4> circleMap = source.getCircleMap();

		Point ptCenter = myCircle4.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle4> circleList = new ArrayList<MyCircle4>();
		while(x <= rightBound && y <= downBound) {
			x = x + source.getvLineDistance(); y = y + source.gethLineDistance();
			
			MyCircle4 myCircle42 = circleMap.get(x + "+" + y);
			if (myCircle42 == null) {
				break;
			}
			if (myCircle42.isSameColor(myCircle4.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle42);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle4>();
			System.out.println("向左下未找到");
		}
		
		return circleList;
	}

	/**
	 * 是否超过边界线
	 * @param point
	 * @return
	 */
	private boolean isPointOverBound(JPanelGame4 source, Point point) {
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
	private boolean hasCircle(JPanelGame4 source, Point ptCenter) {
		String key = ptCenter.x + "+" + ptCenter.y;

		Map<String, MyCircle4> circleMap = source.getCircleMap();
		MyCircle4 myCircle4 = circleMap.get(key);
		if (myCircle4 != null) {
			System.out.println("棋盘中含有相同位置的圆");
			return true;
		}
		
		return false;
	}
	
	public Color getMyColor() {
		return MyConstant4.DEF_CIRCLE_COLOR;
	}
	
	protected abstract void drawIntenal(JPanelGame4 source, MyCircle4 myCircle4);

}
