package com.central.game.objects;

import com.central.engine.GameContainer;
import com.central.engine.Renderer;
import com.central.game.GameManger;

public class BulletEM extends GameObject
{
	private int tileX, tileY;
	private float offX, offY;
	
	private float Speed = 200;

	private int direction;
	public boolean DEATH = false; 
	
	public BulletEM(int tileX, int tileY, float offX, float offY, int direction)
	{
		this.direction = direction;
		this.tileX = tileX;
		this.tileY = tileY;
		
		this.offX = offX;
		this.offY = offY;
		posX = tileX * GameManger.TS + offX; 
		posY = tileY * GameManger.TS + offY; 
		this.padding = 0;
		this.paddingTop = 0;
		this.width = 4;
		this.height = 4;
		this.setTag("Bullet2");
	}
	

	@Override
	public void update(GameContainer gc, GameManger gm, float dt) 
	{
		if(DEATH == true)
		{
			gm.getObject("Billet2").dead = true;
		}
		
		
		
		
		switch(direction)
		{
		case 0: offY += Speed * dt;  break;
		case 1: offX += Speed * dt;	 break;
		case 2: offY -= Speed * dt;	 break;
		case 3: offX -= Speed * dt;	 break;
		}
		
		if(offY > GameManger.TS) 
		{
			tileY++;
			offY -= GameManger.TS;
		}
		
		if(offY < 0) 
		{
			tileY--;
			offY += GameManger.TS;
		}
		
		if(offX > GameManger.TS) 
		{
			tileX++;
			offX -= GameManger.TS;
		}
		
		if(offX < 0) 
		{
			tileX--;
			offX  += GameManger.TS;
		}
		
		if(gm.getCollision(tileX, tileY))
		{
			this.dead =true;
		}
		
		posX = tileX * GameManger.TS + offX; 
		posY = tileY * GameManger.TS + offY; 
	}

	@Override
	public void render(GameContainer gc, Renderer r) 
	{
		r.drawfillrect((int)posX, (int)posY, width, height, 0xff00ff00 );
		this.renderComponent(gc, r);
	}




	@Override
	public void collsion(GameObject other) {
		// TODO Auto-generated method stub
		
	}
	
}
