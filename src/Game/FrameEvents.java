package Game;

import java.awt.Graphics;

public class FrameEvents{
	
	static int astroids=0;
	static int timer=0; 
	static int gameOver=0;
	static Portal portal = new Portal();
	static Astroid[] astroid= {new Astroid(), new Astroid(), new Astroid(), new Astroid(),
			new Astroid(), new Astroid(), new Astroid(), new Astroid(), new Astroid(),
			new Astroid(), new Astroid(), new Astroid(), new Astroid(), new Astroid(),
			new Astroid(), new Astroid(), new Astroid(), new Astroid(), new Astroid(),
			new Astroid(),};

	static SpaceShip spaceShip = new SpaceShip();	
	Score score= new Score();
	
	public void game() {
		if(gameOver==0) {
			move();
			createAstroid();
			if(SpaceShip.invincible==0) {
				if(checkCollisions()==1) {
					SpaceShip.lives--;
					SpaceShip.invincible=1;
					spaceShip.invicibilityTimer=0;
					if(SpaceShip.lives==0)
					gameOver=1;
				}
			}	
			else {
				spaceShip.invicibilityTimer++;
				if(spaceShip.invicibilityTimer>=600)
					SpaceShip.invincible=0;
			}
			timer++;
			GamePanel.sound.sound();				
		}
	}
	
	private void createAstroid() {
		
		for(int i=0; i<20; i++) {		
			if(timer==500)
				astroid[i].speed++;	
			if(timer==1000)
				astroid[i].speed++;					
			}	

		if(timer%100==0 && astroids<20) {

			for(int i=0; i<20; i++)	
				if(astroid[i].usable==-1) {
					astroid[i].setSpawn();
					astroid[i].usable=1;
					break;
				}
			astroids++;
		}	
	}

	private int checkCollisions() {
		for(int i=0; i<20; i++) {
			if(astroid[i].usable==1 && astroid[i].invincibility==0) {
				if( Math.abs(SpaceShip.x-astroid[i].x) <=(Astroid.width/2+SpaceShip.width/2) &&
					Math.abs(SpaceShip.y-astroid[i].y) <=(Astroid.height/2+SpaceShip.height/2) )
					{ GamePanel.sound.collison();FrameEvents.destroy(i); return 1;  }
			}
		}
		return 0;
	}

	public void move() {
		if(gameOver==0)
			spaceShip.move();
		for(int i=0; i<20; i++) 
			astroid[i].move();
		portal.move();
	}

	public void draw(Graphics graphics) {
		
		for(int i=0; i<20; i++) 
			astroid[i].draw(graphics);
		portal.draw(graphics);
		if(gameOver==0)
			spaceShip.draw(graphics);
		score.draw(graphics);
		
	}

	public static void destroy(int index) {
			astroid[index].usable=-1;
			astroids--;
	}

	public static void restart() {
			spaceShip = new SpaceShip();
			portal=new Portal();
			for(int i=0; i<20; i++) {
				astroid[i].usable=-1;
			}
			gameOver=0;
			timer=0;
	}
}