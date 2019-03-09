package othertesting;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FloodFill extends JPanel implements MouseListener {
   
   private BufferedImage image;
   private Graphics2D g2;

   public static void main(String[] args) {
               createAndShowGUI();
   }
   
	public static void createAndShowGUI() {
      JFrame frame = new JFrame("Flooding");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      FloodFill panel = new FloodFill();
      frame.add(panel);
      frame.pack();
      frame.setVisible(true);
	}
	
   public FloodFill() {
      image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
      setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
      setMinimumSize(getPreferredSize());
      g2 = image.createGraphics();
      g2.setColor(Color.white);
      g2.fillRect(0, 0, image.getWidth(), image.getHeight());
      g2.setColor(Color.black);
      
      /*final int EYE_RADIUS = 50;
      final int EYE_GAP = 20;
      final int CENTER_X = image.getWidth() / 2;
      final int MOUTH_RADIUS = EYE_RADIUS + 25;
      g2.drawOval(CENTER_X - EYE_GAP - EYE_RADIUS, 150, EYE_RADIUS, EYE_RADIUS);
      g2.drawOval(CENTER_X + EYE_GAP, 150, EYE_RADIUS, EYE_RADIUS);
      g2.drawOval(CENTER_X - MOUTH_RADIUS / 2, 220, MOUTH_RADIUS, MOUTH_RADIUS);*/
      
      g2.drawOval(50, 50, 600, 600);
      
      
      
      
      addMouseListener(this);
   }
   
   public void paintComponent(Graphics g) {
      g.drawImage(image, 0, 0, null);
   }
   
   public void floodFill(int seedX, int seedY, int rgb) {
	   
	   Queue<Point> queuePoints = new LinkedList<Point>();
	   
	   queuePoints.add(new Point(seedX,seedY));
	   
	   while(!queuePoints.isEmpty()){
		   Point currentPoint=queuePoints.remove();
		   
		   int x=currentPoint.x;
		   int y=currentPoint.y;
		   
		   if (x < image.getWidth() && /* more */ image.getRGB(x, y) == rgb) {
	    	  	image.setRGB(x, y, 0);
	    	  	update(getGraphics());
	    	  	
	    	  //=========================================4 connected ==========================================================
	    	  	/*if(!(queuePoints.contains(new Point(x+1, y))) && ( image.getRGB(x+1, y) == rgb))
	        		queuePoints.add(new Point(x+1, y));

	        	if(!(queuePoints.contains(new Point(x-1, y))) && ( image.getRGB(x-1, y) == rgb))
	        		queuePoints.add(new Point(x-1, y));
	        	
	        	if(!(queuePoints.contains(new Point(x, y+1))) && ( image.getRGB(x, y+1) == rgb))
	        		queuePoints.add(new Point(x, y+1));
	        	
	        	if(!(queuePoints.contains(new Point(x, y-1))) && ( image.getRGB(x, y-1) == rgb))
	        		queuePoints.add(new Point(x, y-1));*/
	        	
	        	
	        	//=========================================8 connected ==========================================================
	        	if(!(queuePoints.contains(new Point(x+1, y))) && ( image.getRGB(x+1, y) == rgb))
	        		queuePoints.add(new Point(x+1, y));

	        	if(!(queuePoints.contains(new Point(x+1, y+1))) && ( image.getRGB(x+1, y+1) == rgb))
	        		queuePoints.add(new Point(x+1, y+1));

	        	if(!(queuePoints.contains(new Point(x+1, y-1))) && ( image.getRGB(x+1, y-1) == rgb))
	        		queuePoints.add(new Point(x+1, y-1));
	        	
	        	if(!(queuePoints.contains(new Point(x-1, y))) && ( image.getRGB(x-1, y) == rgb))
	        		queuePoints.add(new Point(x-1, y));
	        	
	        	if(!(queuePoints.contains(new Point(x-1, y-1))) && ( image.getRGB(x-1, y-1) == rgb))
	        		queuePoints.add(new Point(x-1, y-1));
	        	
	        	if(!(queuePoints.contains(new Point(x-1, y+1))) && ( image.getRGB(x-1, y+1) == rgb))
	        		queuePoints.add(new Point(x-1, y+1));
	        	
	        	if(!(queuePoints.contains(new Point(x, y+1))) && ( image.getRGB(x, y+1) == rgb))
	        		queuePoints.add(new Point(x, y+1));
	        	
	        	if(!(queuePoints.contains(new Point(x, y-1))) && ( image.getRGB(x, y-1) == rgb))
	        		queuePoints.add(new Point(x, y-1));
	      	}
	   }
   }

   public void mouseClicked(MouseEvent e) {
      System.out.println(e.getX() + ", " + e.getY());
      
      floodFill(e.getX(), e.getY(), image.getRGB(e.getX(), e.getY()));
      
      repaint();
   }

   public void mouseEntered(MouseEvent arg0) {
   }

   public void mouseExited(MouseEvent arg0) {
   }

   public void mousePressed(MouseEvent arg0) {
   }

   public void mouseReleased(MouseEvent arg0) {
   }
}