package Game;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Sound {
	
	Clip clip1, clip2, clip3;
	long startTimeShoot;
	long startTimeCollison;
	int playing;
	int shoot;
	int collison;
	int mute;
	
	Sound(){
		try {
			music();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shoot=0;
		collison=0;
	}
	
	public void music() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
		
		File file= new File("PewPewGame\\soundTrack.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		clip1 = AudioSystem.getClip();
		clip1.open(audioStream);
		clip1.start();
		clip1.loop(clip1.LOOP_CONTINUOUSLY);
		playing=1;
		
		file=new File("PewPewGame\\shoot.wav");
		audioStream = AudioSystem.getAudioInputStream(file);
		clip2 = AudioSystem.getClip();
		clip2.open(audioStream);
		
		file=new File("PewPewGame\\collison.wav");
		audioStream = AudioSystem.getAudioInputStream(file);
		clip3 = AudioSystem.getClip();
		clip3.open(audioStream);		
		
	}
	
	public void keyPressed(KeyEvent e) {

		if( e.getKeyCode()==KeyEvent.VK_P && playing==1) {
				clip1.stop();
				playing=0;
			}	
			else if( e.getKeyCode()==KeyEvent.VK_P && playing==0){
				clip1.start();
				playing=1;
			}	
				
		if(e.getKeyCode()==KeyEvent.VK_M && mute==0)
			mute=1;
		else if(e.getKeyCode()==KeyEvent.VK_M && mute==0)
			mute=0;
		else if(e.getKeyCode()==KeyEvent.VK_M)
			mute=0;
		
	}

	public void shoot() {
		//clip1.stop();
		clip2.setMicrosecondPosition(0);
		if(mute==0)clip2.start();
		startTimeShoot=FrameEvents.timer;
		shoot=1;
	}	
	
	public void sound() {
		if((60+startTimeShoot)<=FrameEvents.timer && shoot==1) {
		clip2.stop();
		if(playing==1)
			clip1.start();
		shoot=0;
		}
		else if((150+startTimeCollison)<=FrameEvents.timer && collison==1) {
		clip3.stop(); 
		if(playing==1)
			clip1.start();
		collison=0;
		}			
	}

	public void collison() {
		//clip1.stop();
		clip2.stop();
		clip3.setMicrosecondPosition(0);
		if(mute==0)clip3.start();
		startTimeCollison = FrameEvents.timer;
		collison=1;
		
	}

	public void portal() {		
		
	}

}
