package com.central.game.components;

import com.central.engine.GameContainer;
import com.central.engine.Renderer;
import com.central.game.GameManger;
import com.central.game.objects.GameObject;

public abstract class Component 
{
	protected String tag;
	
	public abstract void update(GameContainer gc, GameManger gm, float dt);
	public abstract void render(GameContainer gc, Renderer r);
	
	
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
