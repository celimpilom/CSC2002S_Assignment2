/**
*word panel
*
* @version 1.0
*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WordPanel extends JPanel implements Runnable {
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
		private Timer timer;
		
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,maxY-10,width,height);
			Random rand = new Random();
			float r = rand.nextFloat();
			float awe = rand.nextFloat();
			float b = rand.nextFloat();
			Color color = new Color(r,awe,b);
		    g.setColor(color);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		   //draw the words
		   //animation must be added 
		    for (int i=0;i<noWords;i++){	    	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
		    }
		   
		  }
		
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;		
		}
	/**
     * run methed to run threads
     *
     */
		public void run() {
			//add in code to animate this
			timer = new Timer(1000, new ActionListener(){
				public void actionPerformed(ActionEvent e){

					for (int i = 0; i < words.length; i++){
						words[i].drop(words[i].getSpeed());
						if (WordApp.score.getTotal()== WordApp.totalWords){
							timer.stop();
							words[i].resetWord();
							JOptionPane.showMessageDialog(null,"<html> GAME OVER <br> Score="+WordApp.sko+"<br>press End before start</html>");
						}
						else if (words[i].dropped()){
							words[i].resetWord();
							WordApp.score.missedWord();
							WordApp.updateMissed();
							repaint();
						}
					}
					repaint();
				}
			 });
			 timer.start();
		}
	/**
     * method to stop the threads
     *
     */
		public void stop() {
			timer.stop();
		}

	}


