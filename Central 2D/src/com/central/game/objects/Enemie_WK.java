package com.central.game.objects;

import java.awt.event.KeyEvent;

import com.central.engine.GameContainer;
import com.central.engine.Renderer;
import com.central.engine.gfx.ImageTile;
import com.central.game.GameManger;
import com.central.game.components.AABBComponent;

public class Enemie_WK extends GameObject
{
public ImageTile playerImage = new ImageTile("/WALKER.png", 16, 16);

	
	private int direction = 0;
	private float ani = 0;
	private int tileX, tileY;
	private float offX, offY;
	public int Frames = 4;
	private float RND;
	private boolean Walk;
	private float TIMER;
	private boolean delay;
	
	
	private float speed = 50;
	private float velocity = 10;
	private float jump = -4;
	private boolean Ground = false;
	private boolean groundlast = false;
	
	private float fallDistance = 0;
	
	public Enemie_WK(int posX, int posY)
	{
		this.tag = "Walker";
		this.tileX = posX;
		this.tileY = posY;
		this.offX = 0;
		this.offY = 0;
		this.posX = posX * GameManger.TS;
		this.posY = posY * GameManger.TS;
		this.width = GameManger.TS;
		this.height = GameManger.TS;
		padding = 5;
		paddingTop = 1;
		
		this.addComponent(new AABBComponent(this));
	}
	
	@Override
	public void update(GameContainer gc, GameManger gm, float dt) 
	{
		
		
		System.out.print(TIMER);
		if(delay == true)
		{
		RND = (float) Math.random();
		Walk = RND >= 0.5;
		TIMER = 1;
		} 
		TIMER = (float) Math.random();
		
		delay = TIMER <= 0.02;
		
		
		
		//Left and Right
		if(Walk == false)
		{
	
			if(gm.getCollision(tileX + 1, tileY) || gm.getCollision(tileX + 1, tileY + (int)Math.signum((int)offY)))
			{
					offX += dt * speed;
					
					if(offX > padding)
					{
						offX = padding;
					}
			}else
			{
				offX += dt * speed;
			}
		}
		
		if(Walk == true)
		{
		
			if(gm.getCollision(tileX - 1, tileY) || gm.getCollision(tileX - 1, tileY + (int)Math.signum((int)offY)))
			{
					offX -= dt * speed;
					
					if(offX < -padding)
					{
						offX = -padding;
					}
			
				}else
			{
				offX -= dt * speed;
			}
		}
		//End of Left and Right
		
		//Beginning of Jump and Gravity
		fallDistance += dt * velocity;
		
		
		offY += fallDistance;
		
		
		if(fallDistance < 0)
		{
			if((gm.getCollision(tileX, tileY - 1) || gm.getCollision(tileX + (int)Math.signum((int)Math.abs(offX) > padding ? offX : 0), tileY - 1)) && offY < -paddingTop)
			{
				fallDistance = 0;
				offY = -paddingTop;
			}
		}
		
		if(fallDistance > 0)
				{
			
		
		
		if((gm.getCollision(tileX, tileY + 1) || gm.getCollision(tileX + (int)Math.signum((int)Math.abs(offX) > padding ? offX : 0), tileY + 1)) && offY > 0)
		{
			fallDistance = 0;
			offY = 0;
			Ground = true;
		}
				}
		
		// End of Jump and Gravity
		
		//Final position
		
		if(offY > GameManger.TS / 2) 
		{
			tileY++;
			offY -= GameManger.TS;
		}
		
		if(offY < -GameManger.TS / 2) 
		{
			tileY--;
			offY += GameManger.TS;
		}
		
		if(offX > GameManger.TS / 2) 
		{
			tileX++;
			offX -= GameManger.TS;
		}
		
		if(offX < -GameManger.TS / 2) 
		{
			tileX--;
			offX  += GameManger.TS;
		}
		
		posX = tileX * GameManger.TS + offX; 
		posY = tileY * GameManger.TS + offY; 
		
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_W))
		{
			if(direction == 1)
			{
			gm.addObject(new BulletEM(tileX, tileY, offX + width  / 2, offY + height / 2, 3));
			}
			else if(direction == 0)
			{
				gm.addObject(new BulletEM(tileX, tileY, offX + width / 2, offY + height / 2, 1));
			}}

	if(Walk == false)
	{
		direction = 0;
		
		// animation
		ani +=  dt * 10;
		if (ani >= Frames)
			ani = 0;
	}
	else if(Walk == true)
	{
		direction = 1;
		
		ani +=  dt * 10;
		if (ani >= Frames)
			ani = 0;
	}
	else
	{
		ani = 0;
	}
	
	if(!Ground)
	{
		ani  = 1;
	}
	
	if(Ground && !groundlast)
	{
		ani = 2;
	}
	
	groundlast = Ground;
	this.updateComponent(gc, gm, dt);
	
	
}
	
	
	
	


	@Override
	public void render(GameContainer gc, Renderer r) 
	{
		r.drawImageTile(playerImage, (int)posX, (int)posY, (int)ani, direction);
		this.renderComponent(gc, r);
	}



	@Override
	public void collsion(GameObject other) 
	{
		// TODO Auto-generated method stub
		
	}
	
}
