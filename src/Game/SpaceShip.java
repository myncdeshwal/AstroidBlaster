package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;

public class SpaceShip {
	
	static Image image;
	static int lives;
	static int width=60;
	static int height=60;
	static int x;
	static int y;
	Random random;
	double xVelocity;
	double yVelocity;
	static double acceleration=10;
	static int invincible;
	int mode;
	double maxSpeed=10;
	double drag=.05;
	WeaponBullet[] bullet= {new WeaponBullet(0), new WeaponBullet(1), new WeaponBullet(2), new WeaponBullet(3), new WeaponBullet(4)};
	int lastShot;
	static int bullets;
	long invicibilityTimer;
	static int[] bulletId= {0,0,0,0,0};
	
	SpaceShip(){
		x=GamePanel.GAME_WIDTH/2-width/2;
		y=GamePanel.GAME_HEIGHT/2-height/2;
		xVelocity=0;
		yVelocity=0;
		mode=0;
		lives=3;
		invincible=1;
		invicibilityTimer=0;
		lastShot=-100;
		bullets=0;
	}
	

	public void move() {
		
		invicibilityTimer++;
		
		if(Math.abs(yVelocity)>=maxSpeed)
			yVelocity= (yVelocity/Math.abs(yVelocity))*maxSpeed;

		
		if(Math.abs(xVelocity)>=maxSpeed)
			xVelocity= (xVelocity/Math.abs(xVelocity))*maxSpeed;	
	
		avoidBorder();
		y= (y + (int)yVelocity);
		x= (x + (int)xVelocity);
		yVelocity=yVelocity*(1-drag);
		xVelocity=xVelocity*(1-drag);	
		
		for(int i=0; i<5; i++) {
			bullet[i].move();
		}		
		
	}
	
	private void avoidBorder() {
			
			if(y>(GamePanel.GAME_HEIGHT-65) ) {
				y=GamePanel.GAME_HEIGHT-65;
			}
			else if(y<5)
				y=5;

			if(x>(GamePanel.GAME_WIDTH-65) ) {
				x=GamePanel.GAME_WIDTH-65;
			}
			else if(x<5)
				x=5;
			
		
	}


	public void keyPressed(KeyEvent e) {
		
			if( e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP) 
				yVelocity=-maxSpeed;//yVelocity-=acceleration;
			else if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN ) 
				yVelocity=maxSpeed;//yVelocity+=acceleration;
			else if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT) 
				xVelocity=-maxSpeed;//xVelocity-=acceleration;
			else if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT) 
				xVelocity=maxSpeed;//xVelocity+=acceleration;
			else if(e.getKeyCode()==KeyEvent.VK_SPACE)
				shoot();
		}
	
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP) 
			yVelocity=yVelocity;//-(drag*yVelocity);
		else if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN) 
			yVelocity=yVelocity;//-drag*yVelocity;
		else if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT) 
			xVelocity=xVelocity;//-drag*xVelocity;
		else if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT) 
			xVelocity=xVelocity;//-drag*xVelocity;
		else if(e.getKeyCode()==KeyEvent.VK_SPACE)
				lastShot=-100;
		
	}
		
	public void shoot() {
		if(FrameEvents.timer>lastShot+100 && bullets<5) {
			for(int i=0; i<5; i++) {
				if(bulletId[i]==0){ 
					bullet[i].x=SpaceShip.x+SpaceShip.width/2;
					bullet[i].y=SpaceShip.y;
					bullet[i].usable=1;
					bullet[i].timer=0;
					bulletId[i]=1;
					break;
				}
			}	
			bullets++; 
			lastShot=FrameEvents.timer;
			GamePanel.sound.shoot();
		} 		
	}
	
	public void draw(Graphics g) {

		image= new ImageIcon("PewPewGame\\spaceShip.png").getImage();
		if(invincible==0)
			g.drawImage(image, x, y, width, height, null);
		else {	
			g.drawImage(image, x, y, width, height, null);		
			g.setColor(Color.blue);
			g.drawOval(x,y,width,height);
		}
		for(int i=0; i<5; i++) 
			bullet[i].draw(g);
	}
	
}
