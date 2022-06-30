package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;

public class Portal {

	static Image image;
	static int width=60;
	static int height=60;
	Random random ;
	int Vx1;
	int Vy1;
	int Vx2;
	int Vy2;
	int x1,y1;
	int coolDown;
	int x2,y2;
	int coolDownTimer;	
	int speed;
	int usable;
	
	Portal(){
		
		random = new Random();
		usable=1;
		coolDown=0;
		speed=3;
		
		spawn();
		
		if(SpaceShip.x-x1>=0)
			Vx1=(SpaceShip.x-x1)%speed;
		else
			Vx1=(-1)*((SpaceShip.x-x1)%speed);
		
		if(SpaceShip.y-y1>=0)		
			Vy1=(SpaceShip.y-y1)%speed;
		else
			Vy1=(-1)*((SpaceShip.y-y1)%speed);
		
		if(Vx1==0 || Vx1==1 || Vx1==-1)
			Vx1=2;
		if(Vy1==0 || Vy1==1 || Vy1==-1)
			Vy1=2;
		
		Vy2=-Vy1;
		Vx2=-Vx1;
		

	}
	
	public void spawn() {
		x1=(random.nextInt(GamePanel.GAME_WIDTH) +SpaceShip.x) % GamePanel.GAME_WIDTH;
		y1=(random.nextInt(GamePanel.GAME_HEIGHT) +SpaceShip.y) % GamePanel.GAME_HEIGHT;
		x2=(random.nextInt(GamePanel.GAME_WIDTH) +2*SpaceShip.x) % GamePanel.GAME_WIDTH;
		y2=(random.nextInt(GamePanel.GAME_HEIGHT) +2*SpaceShip.y) % GamePanel.GAME_HEIGHT;			
	
		if(x1<=0)
			x1=10;
		if(y1<=0)
			y1=10;
		if(x1>=GamePanel.GAME_WIDTH-width)
			x1=GamePanel.GAME_WIDTH-width-10;
		if(y1>=(GamePanel.GAME_HEIGHT-height) )
			y1=GamePanel.GAME_HEIGHT-height-10;		
		
		if(x2<=0)
			x2=10;
		if(y2<=0)
			y2=10;
		if(x2>=GamePanel.GAME_WIDTH-width)
			x2=GamePanel.GAME_WIDTH-width-10;
		if(y2>=(GamePanel.GAME_HEIGHT-height) )
			y2=GamePanel.GAME_HEIGHT-height-10;			
		
	}
	
		public void move(){
			if(usable==1) {	
				teleport();				
				setVelocity();
				y1= (y1 + Vy1);		
				x1= (x1 + Vx1);		
				y2= (y2 + Vy2);		
				x2= (x2 + Vx2);		
			}
		}

		public void setVelocity() {
			
			if(y1>(GamePanel.GAME_HEIGHT-height) || y1<0) 
				Vy1*=(-1);

			if(x1>(GamePanel.GAME_WIDTH-width) || x1<0) 
				Vx1*=(-1);

			if(y2>(GamePanel.GAME_HEIGHT-height) || y2<0) 
						Vy2*=(-1);

			if(x2>(GamePanel.GAME_WIDTH-width) || x2<0) 
						Vx2*=(-1);
		}		
		
		public void teleport() {
			
			if(coolDown==0) {
				
				// Astroids pt 1
				for(int i=0; i<20; i++) {
					if(FrameEvents.astroid[i].usable==1) {
						if( Math.abs(x1-FrameEvents.astroid[i].x) <=(width/2+SpaceShip.width/2) &&
							Math.abs(y1-FrameEvents.astroid[i].y) <=(height/2+SpaceShip.height/2) )
							{ 
								GamePanel.sound.portal(); 
//								FrameEvents.destroy(i);
//								coolDown=1;
								coolDownTimer=0;
							}
					}
				}
				
				// Astroids pt 2
				for(int i=0; i<20; i++) {
					if(FrameEvents.astroid[i].usable==1) {
						if( Math.abs(x2-FrameEvents.astroid[i].x) <=(width/2+SpaceShip.width/2) &&
							Math.abs(y2-FrameEvents.astroid[i].y) <=(height/2+SpaceShip.height/2) )
							{ 
							
								GamePanel.sound.portal(); 
//								FrameEvents.destroy(i);
//								coolDown=1;
								coolDownTimer=0;
//								GamePanel.sound.portal(); 
//								FrameEvents.astroid[i].x=x1;
//								FrameEvents.astroid[i].y=y1;
//								coolDown=1;
//								coolDownTimer=0;
							}
					}
				}		
				
				//SpaceShip pt 1
				if( Math.abs(x2-SpaceShip.x) <=(width/2+SpaceShip.width/2) &&
						Math.abs(y2-SpaceShip.y) <=(height/2+SpaceShip.height/2) )
						{ 
							GamePanel.sound.portal(); 
							SpaceShip.x=x1;
							SpaceShip.y=y1;
							coolDown=1;
							coolDownTimer=0;
						}	
				
				//SpaceShip pt 2
				else if( Math.abs(x1-SpaceShip.x) <=(width/2+SpaceShip.width/2) &&
						Math.abs(y1-SpaceShip.y) <=(height/2+SpaceShip.height/2) )
						{ 
							GamePanel.sound.portal(); 
							SpaceShip.x=x2;
							SpaceShip.y=y2;
							coolDown=1;
							coolDownTimer=0;
					} 				
			}
			
			if(coolDown==1)
				coolDownTimer++;
			if(coolDownTimer==100) {
				coolDown=0;
				coolDownTimer=0;
				spawn();
			}
			
		}	
		
		public void draw(Graphics g) {

			image= new ImageIcon("PewPewGame\\portal.png").getImage();

			if(usable==1 && coolDown==0) {
				g.drawImage(image, x1, y1, width, height, null);		
				g.drawImage(image, x2, y2, width, height, null);
				}
			}
		
}
