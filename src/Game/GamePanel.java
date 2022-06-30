package Game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{


	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = 800;
	static final int GAME_HEIGHT = 540;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	FrameEvents frameEvents= new FrameEvents();
	static SpaceShip spaceShip = new SpaceShip();	
	static Sound sound = new Sound();
	
	GamePanel(){


		//score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new ActionListner());
		this.setPreferredSize(SCREEN_SIZE);
		gameThread = new Thread(this);
		gameThread.start();
	}
	


	 public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		frameEvents.draw(graphics);
		g.drawImage(image,0,0,this);
	}


	@Override
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				frameEvents.game();
				repaint();
				delta--;
			}
		}
		
	}
	
	public class ActionListner extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			frameEvents.spaceShip.keyPressed(e);
			frameEvents.score.keyPressed(e);
			sound.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			frameEvents.spaceShip.keyReleased(e);
		}
	}	

}
