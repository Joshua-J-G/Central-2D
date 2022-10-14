package com.central.game.components;

import com.central.engine.GameContainer;
import com.central.engine.Renderer;
import com.central.game.GameManger;
import com.central.game.Physics;
import com.central.game.objects.GameObject;

public class AABBComponent extends Component
{
	private GameObject parent;
	private int centerX, centerY;
	private int HalfWidth, HalfHeight;
	
	public AABBComponent(GameObject parent)
	{
		this.parent = parent;
	}

	@Override
	public void update(GameContainer gc, GameManger gm, float dt) 
	{
		centerX = (int) (parent.getPosX() + (parent.getWidth() / 2));  
		centerY = (int) (parent.getPosY() + (parent.getHeight() / 2) + (parent.getPaddingTop() / 2)); 
		
		HalfWidth = (parent.getWidth() / 2) - parent.getPadding();
		HalfHeight = (parent.getHeight() / 2) - (parent.getPaddingTop() / 2);
		
		Physics.addAABBComponent(this);
	}

	@Override
	public void render(GameContainer gc, Renderer r) 
	{
		r.drawrect(centerX - HalfWidth, centerY - HalfHeight, HalfWidth * 2, HalfHeight * 2, 0xff00ff00);
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getHalfWidth() {
		return HalfWidth;
	}

	public void setHalfWidth(int halfWidth) {
		HalfWidth = halfWidth;
	}

	public int getHalfHight() {
		return HalfHeight;
	}

	public void setHalfHight(int halfHight) {
		HalfHeight = halfHight;
	}

	public GameObject getParent() {
		return parent;
	}

	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	
	
}
