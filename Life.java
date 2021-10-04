import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Life extends JPanel 
               implements MouseListener {

  int valX = 100;
  int valY = 100;
  int tX = 1200;
  int tY = 600;
  int time = 150;
  int reg[][] = new int[valX][valY];
  boolean play = false;
  JFrame frame;

  public Life() {
    frame = new JFrame();
    frame.addMouseListener(this);
	  frame.getContentPane().add(this);
	  frame.setUndecorated(true);
	  frame.setLocation(100,100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(tX,tY);
	  frame.setBackground(Color.BLACK);
    frame.setVisible(true);
  }

  public void play() {
    while(true) {
		  try {
			  frame.repaint();
			  Thread.sleep(time);
		  } catch(Exception ex) {}
	  }
  }

  public void loadRandomData() {
    for(int j = 0; j < valY; j++) {
      for(int i = 0; i < valX; i++) {
        int n = (int)(Math.random()*100);
        reg[i][j] = (n>50)?1:0;
      }
    }
  }

  public void loadExperiment1() {
    reg[50][50] = 1;
    reg[50][51] = 1;
    reg[50][52] = 1;
    reg[50][53] = 1;
    reg[50][54] = 1;
    reg[50][55] = 1;
    
    reg[51][50] = 1;
    reg[51][51] = 1;
    reg[51][52] = 1;
    reg[51][53] = 1;
    reg[51][54] = 1;
    reg[51][55] = 1;
    
    reg[53][50] = 1;
    reg[54][50] = 1;
    reg[55][50] = 1;
    reg[56][50] = 1;
    reg[57][50] = 1;
    reg[58][50] = 1;
    
    reg[53][51] = 1;
    reg[54][51] = 1;
    reg[55][51] = 1;
    reg[56][51] = 1;
    reg[57][51] = 1;
    reg[58][51] = 1;
    
    reg[57][53] = 1;
    reg[57][54] = 1;
    reg[57][55] = 1;
    reg[57][56] = 1;
    reg[57][57] = 1;
    reg[57][58] = 1;
    
    reg[58][53] = 1;
    reg[58][54] = 1;
    reg[58][55] = 1;
    reg[58][56] = 1;
    reg[58][57] = 1;
    reg[58][58] = 1;
    
    reg[50][57] = 1;
    reg[51][57] = 1;
    reg[52][57] = 1;
    reg[53][57] = 1;
    reg[54][57] = 1;
    reg[55][57] = 1;
    
    reg[50][58] = 1;
    reg[51][58] = 1;
    reg[52][58] = 1;
    reg[53][58] = 1;
    reg[54][58] = 1;
    reg[55][58] = 1;
    
    
    reg[10][10] = 1;
    reg[10][11] = 1;
    reg[10][12] = 1;
    
    reg[20][20] = 1;
    reg[19][19] = 1;
    reg[21][21] = 1;
    reg[21][19] = 1;
    reg[19][21] = 1;
    
    
  } 

  public void paint(Graphics g) {
	  int incX = (int)(tX/valX);
	  int incY = (int)(tY/valY);
	
	  if(play) {
		  reg = validateNeighbor();
	  }
	
	  for(int j = 0; j < valY; j++) {
		  for(int i = 0; i < valX; i++) {
			  if(reg[i][j] == 0) {
				  g.setColor(Color.BLACK);
			  } else {
				  g.setColor(Color.WHITE);
			  }
			  g.fillRect(i*incX,j*incY,incX,incY);
			  if(!play || true) {
			    g.setColor(Color.GRAY);
			    g.drawRect(i*incX,j*incY,incX,incY);
			  }
		  }
	  }
  }
  
  public int[][] validateNeighbor() {
	  int[][] copy = new int[valX][valY];
	  int v, x1, y1, x2, y2;
	  for(int j = 0; j < valY; j++) {
		for(int i = 0; i < valX; i++) {
			v = 0;
			
			x1 = (i==0?valX-1:i-1);
			x2 = (i==valX-1?0:i+1);
			y1 = (j==0?valY-1:j-1);
			y2 = (j==valY-1?0:j+1);
			
			v += reg[x1][y1];
			v += reg[x1][j];
			v += reg[x1][y2];
			v += reg[i][y1];
			v += reg[i][y2];
			v += reg[x2][y1];
			v += reg[x2][j];
			v += reg[x2][y2];
			
			if(reg[i][j] == 0 && v==3) {
				copy[i][j] = 1;
			} else if(reg[i][j] == 1 && v<=3 && v>=2) {
				copy[i][j] = 1;
			} else {
				copy[i][j] = 0;
			}
		}
	}
	return copy;
  }
  
  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
	}

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseClicked(MouseEvent e) {
    int xM = (int)(e.getX()*valX/tX);
    int yM = (int)(e.getY()*valY/tY);
	  if(e.getButton() == 1 && !play) { // Click izq.
		  reg[xM][yM] = 1 - reg[xM][yM];
		  e.getComponent().repaint();
	  } else if(e.getButton() == 3) { //Click der.
		  play = !play;
	  } else if(e.getButton() == 1 && play) { //Click izq.
		  time += 10;
		  if(time >= 200) {
			  time = 10;
		  }
	  }
  }
}           
     