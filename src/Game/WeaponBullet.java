package Game;

import java.awt.Color;
import java.awt.Graphics;

public class WeaponBullet {
	
	int x;
	int y;
	int Vx;
	int Vy;
	int width=5;
	int height=40;
	int type;
	int speed;
	int usable;
	int id;
	long timer;
	
	WeaponBullet(int id){
		Vx=0;
		speed=20;
		Vy=-speed;
		usable=0;
		this.id=id;
	}
	
	public void move() {
		
		if(usable==1) {
			x=x+Vx;
			y=y+Vy;
			if(checkCollision()!=-1 && checkCollision()!=101) {
				FrameEvents.destroy( checkCollision() );
//				usable=0;
//				SpaceShip.bulletId[id]=0;				
//				SpaceShip.bullets--;
			}
			else if(checkCollision()==101)
				usable=0;
				SpaceShip.bulletId[id]=0;				
				SpaceShip.bullets--;				
			timer++;
		}

	}

	public void draw(Graphics g) {
		if(usable==1) {
			g.setColor(Color.orange);
			g.fillRect(x, y, width, height);
		}
	}
	
	public int checkCollision() {
		for(int i=0; i<20; i++) {
			if( x>=FrameEvents.astroid[i].x && (x-60)<= FrameEvents.astroid[i].x &&
					y<=(FrameEvents.astroid[i].y+60) && y>=FrameEvents.astroid[i].y && 
						FrameEvents.astroid[i].invincibility==0)
				return i;
		}
		if(y<0)
			return 101;
		return -1;
	}
	
}
