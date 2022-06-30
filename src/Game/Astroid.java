package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;

public class Astroid{
	
	static Image image;
	static int width=60;
	static int height=60;
	Random random ;
	int Vx;
	int Vy;
	int x,y;
	int speed;
	int usable;
	int invincibility=0;
	long invincibilityTimer;
	
	Astroid(){

		random =new Random();
		speed=Math.abs(random.nextInt(6)) +3;
		usable=-1;		
	}
	
	public void setSpawn(){

		x=(random.nextInt(GamePanel.GAME_WIDTH) + SpaceShip.x) % GamePanel.GAME_WIDTH;
		y=(random.nextInt(GamePanel.GAME_HEIGHT) + SpaceShip.y) % GamePanel.GAME_HEIGHT;
		
		if(SpaceShip.x-x>=0)
			Vx=(SpaceShip.x-x)%speed;
		else
			Vx=(-1)*((SpaceShip.x-x)%speed);
		
		if(SpaceShip.y-y>=0)		
			Vy=(SpaceShip.y-y)%speed;
		else
			Vy=(-1)*((SpaceShip.y-y)%speed);
		
		if(Vx==0 || Vx==1 || Vx==-1)
			Vx=1;
		if(Vy==0 || Vy==1 || Vy==-1)
			Vy=2;
		
		if(x<=0)
			x=10;
		if(y<=0)
			y=10;
		if(x>=GamePanel.GAME_WIDTH-width)
			x=GamePanel.GAME_WIDTH-width-10;
		if(y>=(GamePanel.GAME_HEIGHT-height) )
			y=GamePanel.GAME_HEIGHT-height-10;
				
		
		invincibility=1;
		invincibilityTimer=FrameEvents.timer;
		
	}

	public void move() {
		if(usable==1) {	
			if(FrameEvents.timer>=(invincibilityTimer+50))
				invincibility=0;
			setVelocity();
			y= (y + Vy);		
			x= (x + Vx);
		}
	}
	
	public void setVelocity() {
		
		if(y>=(GamePanel.GAME_HEIGHT-height) || y<=0) {
			Vy=Vy*(-1);
		}

		if(x>=(GamePanel.GAME_WIDTH-width) || x<=0) {
			Vx=Vx*(-1);
		}
		
	}
	
	public void draw(Graphics g) {
		if(usable==1) {
			image= new ImageIcon("PewPewGame\\astroid.png").getImage();
				if(invincibility==0)
					g.drawImage(image, x, y, width, height, null);
				else if(invincibility==1){	
					g.drawImage(image, x, y, width, height, null);		
					g.setColor(Color.magenta);
					g.drawOval(x,y,width,height);
				}
		}
	}
	
}
