package com.central.game.objects;

import java.awt.event.KeyEvent;

import com.central.engine.GameContainer;
import com.central.engine.Renderer;
import com.central.engine.gfx.ImageTile;
import com.central.game.GameManger;
import com.central.game.components.AABBComponent;

public class Player extends GameObject
{
	public ImageTile playerImage = new ImageTile("/Player.png", 16, 16);

	
	private int direction = 0;
	private float ani = 0;
	private int tileX, tileY;
	private float offX, offY;
	public int Frames = 4;
	
	private float speed = 100;
	private float velocity = 10;
	private float jump = -4;
	private boolean Ground = false;
	private boolean groundlast = false;
	
	private float fallDistance = 0;
	
	public Player(int posX, int posY)
	{
		this.tag = "player";
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
		//Left and Right
		if(gc.getInput().isKey(KeyEvent.VK_D))
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
		
		if(gc.getInput().isKey(KeyEvent.VK_A))
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
		
		if(gc.getInput().isKey(KeyEvent.VK_SPACE) && Ground)
		{
			fallDistance = jump;
			Ground = false;
		}
		
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
		
		//shoot bullet
		//if(gc.getInput().isKey(KeyEvent.VK_UP))
		{
			//gm.addObject(new Bullet(tileX, tileY, offX + width / 2, offY + height / 2, 2));
		}
		
		//if(gc.getInput().isKey(KeyEvent.VK_RIGHT))
		{
			//gm.addObject(new Bullet(tileX, tileY, offX + width / 2, offY + height / 2, 1));
		}
		
		//if(gc.getInput().isKey(KeyEvent.VK_DOWN))
		{
			//gm.addObject(new Bullet(tileX, tileY, offX + width / 2, offY + height / 2, 0));
		}
		
		//if(gc.getInput().isKey(KeyEvent.VK_LEFT))
		{
			//gm.addObject(new Bullet(tileX, tileY, offX + width / 2, offY + height / 2, 3));
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_W))
		{
			if(direction == 1)
			{
			gm.addObject(new Bullet(tileX, tileY, offX + width  / 2, offY + height / 2, 3));
			}
			else if(direction == 0)
			{
				gm.addObject(new Bullet(tileX, tileY, offX + width / 2, offY + height / 2, 1));
			}}

	if(gc.getInput().isKey(KeyEvent.VK_D))
	{
		direction = 0;
		
		// animation
		ani +=  dt * 10;
		if (ani >= Frames)
			ani = 0;
	}
	else if(gc.getInput().isKey(KeyEvent.VK_A))
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

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}
	
}
