package com.central.game;

import java.util.ArrayList;

import com.central.game.components.AABBComponent;

public class Physics 
{
	private static ArrayList<AABBComponent> aabbList = new ArrayList<AABBComponent>();
	
	public static void addAABBComponent(AABBComponent aabb)
	{
		aabbList.add(aabb);
	}
	public static void update() 
	{
		for(int i = 0; i < aabbList.size(); i++)
		{
			for(int j = i + 1; j < aabbList.size(); j++)
			{
				AABBComponent c0 = aabbList.get(i);
				AABBComponent c1 = aabbList.get(j);
				
				if(Math.abs(c0.getCenterX() - c1.getCenterX()) < c0.getHalfWidth() + c1.getHalfWidth())
				{
					if(Math.abs(c0.getCenterY() - c1.getCenterY()) < c0.getHalfHight() + c1.getHalfHight())
					{
						c0.getParent().collsion(c1.getParent());
						c1.getParent().collsion(c0.getParent());
					}
				}
			}
		}
		
		aabbList.clear();
	}
}
