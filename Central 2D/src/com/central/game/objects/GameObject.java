package com.central.game.objects;

import java.util.ArrayList;

import com.central.engine.GameContainer;
import com.central.engine.Renderer;
import com.central.game.GameManger;
import com.central.game.components.Component;

public abstract class GameObject 
{
	protected String tag;
	protected float posX, posY;
	protected int width, height;
	protected boolean dead = false;
	protected int padding, paddingTop;
	
	protected ArrayList<Component> components = new ArrayList<Component>();
	
	public abstract void update(GameContainer gc, GameManger gm, float dt);
	public abstract void render(GameContainer gc, Renderer r);
	public abstract void collsion(GameObject other);
	public void updateComponent(GameContainer gc, GameManger gm, float dt)
	{
		for(Component c : components)
		{
			c.update(gc, gm, dt);
		}
	}
	
	public void renderComponent(GameContainer gc, Renderer r)
	{
		for(Component c : components)
		{
			c.render(gc, r);
		}
	}
	
	public void addComponent(Component c)
	{
		components.add(c);
	}
	
	public void removeComponent(String tag)
	{
		for(int i = 0; i < components.size(); i++)
		{
			if(components.get(i).getTag().equalsIgnoreCase(tag))
			{
				components.remove(i);
			}
		}
	}
	
	public Component findComponent(String tag)
	{
		for(int i = 0; i < components.size(); i++)
		{
			if(components.get(i).getTag().equalsIgnoreCase(tag))
			{
				return components.get(i);
			}
		}
		
		return null;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public int getPadding() {
		return padding;
	}
	public void setPadding(int padding) {
		this.padding = padding;
	}
	public int getPaddingTop() {
		return paddingTop;
	}
	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}
	public ArrayList<Component> getComponents() {
		return components;
	}
	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	}
	
	
}
