package com.central.game.objects;

import com.central.engine.GameContainer;
import com.central.engine.Renderer;
import com.central.game.GameManger;
import com.central.game.components.AABBComponent;

public class platform extends GameObject
{
	private int colour = (int) (Math.random() * Integer.MAX_VALUE); 

	public platform()
	{
		this.tag = "platform";
		this.width = 32;
		this.height = 16;
		this.padding = 0;
		this.paddingTop = 0;
		this.posX = 276;
		this.posY = 176;
		
		this.addComponent(new AABBComponent(this));
	}

	@Override
	public void update(GameContainer gc, GameManger gm, float dt)
	{
		this.updateComponent(gc, gm, dt);
		
	}

	@Override
	public void render(GameContainer gc, Renderer r)
	{
		r.drawfillrect((int)posX, (int)posY, width, height, colour);
		this.renderComponent(gc, r);
		
	}

	@Override
	public void collsion(GameObject other) 
	{
		colour = (int) (Math.random() * Integer.MAX_VALUE);
		
	}

}
