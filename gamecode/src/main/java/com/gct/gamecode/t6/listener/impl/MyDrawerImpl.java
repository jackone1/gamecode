package com.gct.gamecode.t6.listener.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.gct.gamecode.t6.JPanelGame6;
import com.gct.gamecode.t6.compent.MyCompent;
import com.gct.gamecode.t6.compent.factory.MyCompentFactory;
import com.gct.gamecode.t6.compent.factory.impl.MyCompentFactoryImpl;
import com.gct.gamecode.t6.compent.impl.MyFrag;
import com.gct.gamecode.t6.compent.impl.MyFrag.EnumMyFrag;
import com.gct.gamecode.t6.listener.Drawerable;
import com.gct.gamecode.util.MathUtil3;

public class MyDrawerImpl implements Drawerable {

	/** key:位置坐标(x+y), value:circle*/
	private Map<String, MyCompent> gameFragMap;
	
	private MyCompentFactory compentFactory;
	
	public MyDrawerImpl() {
		gameFragMap = new HashMap<String, MyCompent>();
		compentFactory = new MyCompentFactoryImpl();
	}
	
	public boolean draw(JPanelGame6 source, Point mousePoint) {
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
		
		source.repaint();
		return true;
	}

	public boolean drawFollowMouse(JPanelGame6 source, Point mousePoint) {
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
	private boolean isPointOverBound(JPanelGame6 source, Point point) {
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
	private boolean hasCircle(JPanelGame6 source, Point ptCenter) {
		String key = ptCenter.x + "+" + ptCenter.y;
		
		return false;
	}
	
	/**
	 * 初始化雷区
	 */
	public void generateGameFrag(JPanelGame6 source) {
		gameFragMap.putAll(fillGameFrag(source, generateFrag(source)));
		
		Collection<MyCompent> values = gameFragMap.values();
		for (MyCompent myCompent : values) {
			source.addMyCompent(myCompent);
		}
		return ;
	}
	
	private Map<String, MyCompent> fillGameFrag(JPanelGame6 source, Map<String, MyCompent> generatedFragMap) {
		int gridVNum = source.getVerticalNum() - 1; //竖格子数 
		int gridHNum = source.getHorizonalNum() - 1; //横格子数
		Integer aroundDistance = source.getAroundDistance();
		int vHalfLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf(source.getvLineDistance()), String.valueOf(2), 0));
		int hHalfLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf(source.gethLineDistance()), String.valueOf(2), 0));
		//计算直径
		int shortDistance = source.getvLineDistance() > source.gethLineDistance() ? source.gethLineDistance() : source.getvLineDistance();
		int intDiameter = Integer.valueOf(MathUtil3.div(String.valueOf(shortDistance), String.valueOf(1.618), 0));

		for (int ix = 0; ix < gridVNum; ix++) {
			for (int jy = 0; jy < gridHNum; jy++) {
				int x = aroundDistance + vHalfLineDistance + source.getvLineDistance() * ix;
				int y = aroundDistance + hHalfLineDistance + source.gethLineDistance() * jy;
				String key = String.format("%s+%s", x, y);
				if (generatedFragMap.get(key) != null) { //已经计算或存在雷
					continue;
				}
				
				int aroundFragNum = getAroundFragNum(source, ix, jy, generatedFragMap);
				generatedFragMap.put(key, compentFactory.getMyNumber(aroundFragNum, new Point(x, y), intDiameter));
			}
		}
		
		return generatedFragMap;
	}
	
	private int getAroundFragNum(JPanelGame6 source, int ix, int jy, Map<String, MyCompent> generatedFragMap) {
		int aroundFragNum = 0;

		Integer aroundDistance = source.getAroundDistance();
		int vHalfLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf(source.getvLineDistance()), String.valueOf(2), 0));
		int hHalfLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf(source.gethLineDistance()), String.valueOf(2), 0));

		for (int i = ix - 1; i <= ix + 1; i++) {
			for (int j = jy - 1; j <= jy + 1; j++) {
				if (i == ix && j == jy) {
					continue;
				}
				int x = aroundDistance + vHalfLineDistance + source.getvLineDistance() * i;
				int y = aroundDistance + hHalfLineDistance + source.gethLineDistance() * j;
				String key = String.format("%s+%s", x, y);
				MyCompent myCompent = generatedFragMap.get(key);
				if (myCompent != null && myCompent instanceof MyFrag && EnumMyFrag.MY_FRAG.equals(((MyFrag)myCompent).getMyFrag())) {
					aroundFragNum += 1;
				}
			}
		}
		
		return aroundFragNum;
	}

	/**
	 * 初始化地雷
	 */
	private Map<String, MyCompent> generateFrag(JPanelGame6 source) {
		Map<String, MyCompent> mapFrag = new HashMap<String, MyCompent>();
		
		int verticalNum = source.getVerticalNum(); //竖线数 
		int horizonalNum = source.getHorizonalNum(); //横线数

		Integer aroundDistance = source.getAroundDistance();
		int vHalfLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf(source.getvLineDistance()), String.valueOf(2), 0));
		int hHalfLineDistance = Integer.valueOf(MathUtil3.div(String.valueOf(source.gethLineDistance()), String.valueOf(2), 0));
		//计算直径
		int shortDistance = source.getvLineDistance() > source.gethLineDistance() ? source.gethLineDistance() : source.getvLineDistance();
		int intDiameter = Integer.valueOf(MathUtil3.div(String.valueOf(shortDistance), String.valueOf(1.618), 0));

		int fragNum = source.getFragNum();
		List<String> useableXList = new ArrayList<String>();
		List<String> useableYList = new ArrayList<String>();
		for (int i = 0; i < fragNum; i++) {
			String randomFrag[] = StringUtils.split(getRandomFrag((verticalNum - 1), (horizonalNum - 1), useableXList), "+");
			int randomFragX = Integer.valueOf(randomFrag[0]);
			int randomFragY = Integer.valueOf(randomFrag[1]);
			
			int x = aroundDistance + vHalfLineDistance + source.getvLineDistance() * randomFragX;
			int y = aroundDistance + hHalfLineDistance + source.gethLineDistance() * randomFragY;

			String key = String.format("%s+%s", x, y);
			MyFrag myFrag = compentFactory.getMyFrag(EnumMyFrag.MY_FRAG, new Point(x, y), intDiameter);
			
			mapFrag.put(key, myFrag);
		}
		
		System.out.println("初始化地雷数: " + mapFrag.values().size());
		return mapFrag;
	}
	
	private String getRandomFrag(int maxXListNum, int maxYListNum, List<String> useableXYList) {
		if (useableXYList == null || useableXYList.isEmpty()) {
			for (int i = 0; i < maxXListNum; i++) {
				for (int j = 0; j < maxYListNum; j++) {
					String value = String.format("%s+%s", i, j);
					useableXYList.add(value);
				}
			}
		}
		
		int index = RandomUtils.nextInt(0, (useableXYList.size()));
		return useableXYList.remove(index);
	}
	
}
