package com.gct.gamecode.t3.listener.impl;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gct.gamecode.t3.JPanelGame3;
import com.gct.gamecode.t3.MyCircle3;
import com.gct.gamecode.t3.constant.MyConstant3;
import com.gct.gamecode.t3.listener.Drawerable;
import com.gct.gamecode.util.MathUtil3;

public abstract class AbsMyDrawerImpl implements Drawerable {

	public boolean draw(JPanelGame3 source, Point mousePoint) {
		if (isPointOverBound(source, mousePoint)) {
			return false;
		}

		//计算圆心坐标
//		int ptX = (mousePoint.x - source.getAroundDistance()) / source.getvLineDistance() * source.getvLineDistance() + source.getAroundDistance();
//		int ptY = (mousePoint.y - source.getAroundDistance()) / source.gethLineDistance() * source.gethLineDistance() + source.getAroundDistance();
		Integer aroundDistance = source.getAroundDistance();
		int ptX = Integer.valueOf(MathUtil3.mul(MathUtil3.div(String.valueOf(mousePoint.x - aroundDistance), String.valueOf(source.getvLineDistance()), 0), String.valueOf(source.getvLineDistance()))) + aroundDistance;
		int ptY = Integer.valueOf(MathUtil3.mul(MathUtil3.div(String.valueOf(mousePoint.y - aroundDistance), String.valueOf(source.gethLineDistance()), 0), String.valueOf(source.gethLineDistance()))) + aroundDistance;
		System.out.println(String.format("ptX:%s/%s*%s", mousePoint.x, source.getvLineDistance(), source.getvLineDistance()));
		System.out.println(String.format("ptY:%s/%s*%s", mousePoint.y, source.gethLineDistance(), source.gethLineDistance()));
		System.out.println("add new circle(ptX:" + ptX + ", ptY:" + ptY + ")");
		
		Point point = new Point(ptX, ptY);
		boolean hasCircle = hasCircle(source, point);
		if (hasCircle) {
			return false;
		}
		
		//计算直径
		int shortDistance = source.getvLineDistance() > source.gethLineDistance() ? source.gethLineDistance() : source.getvLineDistance();
		int intDiameter = Integer.valueOf(MathUtil3.div(String.valueOf(shortDistance), String.valueOf(1.618), 0));
		
		MyCircle3 myCircle3 = new MyCircle3(point, intDiameter, this.getMyColor());
		source.addCircle((ptX + "+" + ptY), myCircle3);
		System.out.println(source.getCircleMap().keySet().toString());
		
		drawIntenal(source, myCircle3);
		
		eatCircle(source, myCircle3);
		source.repaint();
		return true;
	}
	
	public boolean drawFollowMouse(JPanelGame3 source, Point mousePoint) {
		//计算圆心坐标
		int ptX = mousePoint.x;
		int ptY = mousePoint.y;
		
		//计算直径
		int shortDistance = source.getvLineDistance() > source.gethLineDistance() ? source.gethLineDistance() : source.getvLineDistance();
		int intDiameter = Integer.valueOf(MathUtil3.div(String.valueOf(shortDistance), String.valueOf(1.618), 0));
		Point point = new Point(ptX, ptY);
		MyCircle3 myCircle3 = source.getMouseCircle3();
		if (myCircle3 == null) {
			myCircle3 = new MyCircle3(point, intDiameter, this.getMyColor());
			source.setMouseCircle3(myCircle3);
		}
		
		myCircle3.setPtCenter(point);
		myCircle3.setCircleColor(getMyColor());

		source.repaint();
		return false;
	}
	
	private void eatCircle(JPanelGame3 source, MyCircle3 myCircle3) {
		List<MyCircle3> needEatedList = new ArrayList<MyCircle3>();
		needEatedList.addAll(findHeadAndTailWithDrUp(source, myCircle3));
		needEatedList.addAll(findHeadAndTailWithDrDown(source, myCircle3));
		needEatedList.addAll(findHeadAndTailWithDrLeft(source, myCircle3));
		needEatedList.addAll(findHeadAndTailWithDrRight(source, myCircle3));
		needEatedList.addAll(findHeadAndTailWithDrUpLeft(source, myCircle3));
		needEatedList.addAll(findHeadAndTailWithDrUpRight(source, myCircle3));
		needEatedList.addAll(findHeadAndTailWithDrDownLeft(source, myCircle3));
		needEatedList.addAll(findHeadAndTailWithDrDownRight(source, myCircle3));
		
		change2MyColor(needEatedList);
	}
	
	private void change2MyColor(List<MyCircle3> needEatedList) {
		if (needEatedList == null || needEatedList.isEmpty()) {
			return;
		}
		
		for (MyCircle3 myCircle3 : needEatedList) {
			myCircle3.setCircleColor(getMyColor());
		}
	}
	
	/**
	 * 向上
	 */
	private List<MyCircle3> findHeadAndTailWithDrUp(JPanelGame3 source, MyCircle3 myCircle3) {
		int upBound = source.getAroundDistance(); //上边界
		Map<String, MyCircle3> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle3.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle3> circleList = new ArrayList<MyCircle3>();
		while(y >= upBound) {
			y = y - source.gethLineDistance();
			
			MyCircle3 myCircle32 = circleMap.get(x + "+" + y);
			if (myCircle32 == null) {
				break;
			}
			if (myCircle32.isSameColor(myCircle3.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle32);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle3>();
			System.out.println("向上未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 向下
	 * @param source
	 * @param myCircle3
	 * @return
	 */
	private List<MyCircle3> findHeadAndTailWithDrDown(JPanelGame3 source, MyCircle3 myCircle3) {
		int downBound = source.getAroundDistance() + (source.getHorizonalNum() - 1) * source.gethLineDistance(); //下边界
		Map<String, MyCircle3> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle3.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle3> circleList = new ArrayList<MyCircle3>();
		while(y <= downBound) {
			y = y + source.gethLineDistance();
			
			MyCircle3 myCircle32 = circleMap.get(x + "+" + y);
			if (myCircle32 == null) {
				break;
			}
			if (myCircle32.isSameColor(myCircle3.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle32);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle3>();
			System.out.println("向下未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 向左
	 * @param source
	 * @param myCircle3
	 * @return
	 */
	private List<MyCircle3> findHeadAndTailWithDrLeft(JPanelGame3 source, MyCircle3 myCircle3) {
		int leftBound = source.getAroundDistance(); //左边界
		Map<String, MyCircle3> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle3.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle3> circleList = new ArrayList<MyCircle3>();
		while(x >= leftBound) {
			x = x - source.getvLineDistance();
			
			MyCircle3 myCircle32 = circleMap.get(x + "+" + y);
			if (myCircle32 == null) {
				break;
			}
			if (myCircle32.isSameColor(myCircle3.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle32);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle3>();
			System.out.println("向左未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 向右
	 * @param source
	 * @param myCircle3
	 * @return
	 */
	private List<MyCircle3> findHeadAndTailWithDrRight(JPanelGame3 source, MyCircle3 myCircle3) {
		int rightBound = source.getAroundDistance() + ((source.getVerticalNum() - 1) * source.getvLineDistance()); //右边界
		Map<String, MyCircle3> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle3.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle3> circleList = new ArrayList<MyCircle3>();
		while(x <= rightBound) {
			x = x + source.getvLineDistance();
			
			MyCircle3 myCircle32 = circleMap.get(x + "+" + y);
			if (myCircle32 == null) {
				break;
			}
			if (myCircle32.isSameColor(myCircle3.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle32);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle3>();
			System.out.println("向右未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 左上角
	 * @param source
	 * @param myCircle3
	 * @return
	 */
	private List<MyCircle3> findHeadAndTailWithDrUpLeft(JPanelGame3 source, MyCircle3 myCircle3) {
		int leftBound = source.getAroundDistance(); //左边界
		int upBound = source.getAroundDistance(); //上边界
		Map<String, MyCircle3> circleMap = source.getCircleMap();
		
		Point ptCenter = myCircle3.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle3> circleList = new ArrayList<MyCircle3>();
		while(x >= leftBound && y >= upBound) {
			x = x - source.getvLineDistance(); y = y - source.gethLineDistance();
			
			MyCircle3 myCircle32 = circleMap.get(x + "+" + y);
			if (myCircle32 == null) {
				break;
			}
			if (myCircle32.isSameColor(myCircle3.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle32);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle3>();
			System.out.println("向左上未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 右上角
	 * @param source
	 * @param myCircle3
	 * @return
	 */
	private List<MyCircle3> findHeadAndTailWithDrUpRight(JPanelGame3 source, MyCircle3 myCircle3) {
		int upBound = source.getAroundDistance(); //上边界
		int rightBound = source.getAroundDistance() + ((source.getVerticalNum() - 1) * source.getvLineDistance()); //右边界
		Map<String, MyCircle3> circleMap = source.getCircleMap();

		Point ptCenter = myCircle3.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle3> circleList = new ArrayList<MyCircle3>();
		while(x <= rightBound && y >= upBound) {
			x = x + source.getvLineDistance(); y = y - source.gethLineDistance();
			
			MyCircle3 myCircle32 = circleMap.get(x + "+" + y);
			if (myCircle32 == null) {
				break;
			}
			if (myCircle32.isSameColor(myCircle3.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle32);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle3>();
			System.out.println("向右上未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 左下角
	 * @param source
	 * @param myCircle3
	 * @return
	 */
	private List<MyCircle3> findHeadAndTailWithDrDownLeft(JPanelGame3 source, MyCircle3 myCircle3) {
		int downBound = source.getAroundDistance() + (source.getHorizonalNum() - 1) * source.gethLineDistance(); //下边界
		int leftBound = source.getAroundDistance(); //左边界
		Map<String, MyCircle3> circleMap = source.getCircleMap();

		Point ptCenter = myCircle3.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle3> circleList = new ArrayList<MyCircle3>();
		while(x >= leftBound && y <= downBound) {
			x = x - source.getvLineDistance(); y = y + source.gethLineDistance();
			
			MyCircle3 myCircle32 = circleMap.get(x + "+" + y);
			if (myCircle32 == null) {
				break;
			}
			if (myCircle32.isSameColor(myCircle3.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle32);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle3>();
			System.out.println("向左下未找到");
		}
		
		return circleList;
	}
	
	/**
	 * 右下角
	 * @param source
	 * @param myCircle3
	 * @return
	 */
	private List<MyCircle3> findHeadAndTailWithDrDownRight(JPanelGame3 source, MyCircle3 myCircle3) {
		int downBound = source.getAroundDistance() + (source.getHorizonalNum() - 1) * source.gethLineDistance(); //下边界
		int rightBound = source.getAroundDistance() + ((source.getVerticalNum() - 1) * source.getvLineDistance()); //右边界
		Map<String, MyCircle3> circleMap = source.getCircleMap();

		Point ptCenter = myCircle3.getPtCenter();
		int x = ptCenter.x;
		int y = ptCenter.y;
		
		boolean findFlag = false;
		List<MyCircle3> circleList = new ArrayList<MyCircle3>();
		while(x <= rightBound && y <= downBound) {
			x = x + source.getvLineDistance(); y = y + source.gethLineDistance();
			
			MyCircle3 myCircle32 = circleMap.get(x + "+" + y);
			if (myCircle32 == null) {
				break;
			}
			if (myCircle32.isSameColor(myCircle3.getCircleColor())) {
				findFlag = true;
				break;
			}
			circleList.add(myCircle32);
		}
		
		if (!findFlag) {
			circleList = new ArrayList<MyCircle3>();
			System.out.println("向左下未找到");
		}
		
		return circleList;
	}

	/**
	 * 是否超过边界线
	 * @param point
	 * @return
	 */
	private boolean isPointOverBound(JPanelGame3 source, Point point) {
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
	private boolean hasCircle(JPanelGame3 source, Point ptCenter) {
		String key = ptCenter.x + "+" + ptCenter.y;

		Map<String, MyCircle3> circleMap = source.getCircleMap();
		MyCircle3 myCircle3 = circleMap.get(key);
		if (myCircle3 != null) {
			System.out.println("棋盘中含有相同位置的圆");
			return true;
		}
		
		return false;
	}
	
	public Color getMyColor() {
		return MyConstant3.DEF_CIRCLE_COLOR;
	}
	
	protected abstract void drawIntenal(JPanelGame3 source, MyCircle3 myCircle3);

}
