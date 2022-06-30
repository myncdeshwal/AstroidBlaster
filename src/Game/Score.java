package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Score {
	
	static long highScore=0;
	Image image;
	
	public void draw(Graphics graphics) {
		graphics.setColor(Color.white);
		graphics.setFont(new Font("Consolas",Font.PLAIN,25));
		
		if(FrameEvents.gameOver==0) {
			image= new ImageIcon("PewPewGame\\heart.png").getImage();
			graphics.drawString(String.valueOf(FrameEvents.timer/10), (GamePanel.GAME_WIDTH/2)-10, 50);
			for(int i=0; i<SpaceShip.lives; i++)	
				graphics.drawImage(image, 0+i*35, GamePanel.GAME_HEIGHT-30, 30, 30, null);
		}
		else {
			graphics.drawString("Game Over", (GamePanel.GAME_WIDTH/2)-60, 50);
			graphics.drawString("Your score "+String.valueOf(FrameEvents.timer/10), (GamePanel.GAME_WIDTH/2)-60, 100);
			graphics.drawString("High score "+String.valueOf(highScore), (GamePanel.GAME_WIDTH/2)-60, 150);
			
			if( (FrameEvents.timer/10) >= highScore) { 
				highScore=FrameEvents.timer/10;
				graphics.drawString("High score!", (GamePanel.GAME_WIDTH/2)-60, 250);
			}
			graphics.setColor(Color.orange);
			graphics.drawString("Press R to play again", (GamePanel.GAME_WIDTH/2)-100, 400);
		}	
	}
	
	public void keyPressed(KeyEvent e) {
		
		if( e.getKeyCode()==KeyEvent.VK_R && FrameEvents.gameOver==1) 
			FrameEvents.restart();
	}	
}
