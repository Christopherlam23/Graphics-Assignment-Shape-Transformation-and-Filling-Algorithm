package assignmentGraphic2;

import java.awt.event.*;
import java.util.*;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;

import othertesting.FloodFill;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class fillingAlgo extends JPanel implements MouseListener{
	
	private BufferedImage image;
	private Graphics2D g2;
	private JLabel mouseClickLabel;
	
	public static boolean flagOriginalShape=false;

	public static int screenX=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int screenY=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public static ArrayList<Integer> arrPtsX=new ArrayList<Integer>();
	public static ArrayList<Integer> arrPtsY=new ArrayList<Integer>();
	public static ArrayList<Integer> arrTempX=new ArrayList<Integer>();
	public static ArrayList<Integer> arrTempY=new ArrayList<Integer>();
	
	//public static ArrayList<Point> activeEdgeList=new ArrayList<>();
	
	public static ArrayList<Point> pts=new ArrayList<>();
	public static Point previousPoint;
	public static int counterNumSides=0;
	
	public static JComboBox jcb_numSides;
	public static JRadioButton jrbtn_floodFill;
	public static JRadioButton jrbtn_boundaryFill;
	public static JComboBox jcb_boundaryColor;
	public static JRadioButton jrbtn_scanLine;
	public static JButton jbtn_apply;
	public static JButton jbtn_reset;
	public static JButton jbtn_close;
	public static JPanel optionPanel;
	
	public fillingAlgo(){
		
		image = new BufferedImage(screenX, screenY-80, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		setMinimumSize(getPreferredSize());
		
		g2 = image.createGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		/*int maxX=(screenX-50)/3;
		int maxY=(screenY-100)/3;
		
		for(int i=0;i<12;i++){
			if(i==0)	g2.setColor(new Color(255,0,0));
			if(i==1)	g2.setColor(new Color(0,255,0));
			if(i==2)	g2.setColor(new Color(0,0,255));
			if(i==3)	g2.setColor(new Color(255,0,0));
			if(i==4)	g2.setColor(new Color(0,255,0));
			if(i==5)	g2.setColor(new Color(0,0,255));
			if(i==6)	g2.setColor(new Color(255,0,0));
			if(i==7)	g2.setColor(new Color(0,255,0));
			if(i==8)	g2.setColor(new Color(0,0,255));
			if(i==9)	g2.setColor(new Color(255,0,0));
			if(i==10)	g2.setColor(new Color(0,255,0));
			if(i==11)	g2.setColor(new Color(0,0,255));
			
			g2.drawRect(randInt(maxX)+200, randInt(maxY), randInt(maxX), randInt(maxY));
			g2.drawOval(randInt(maxX)+200, randInt(maxY), randInt(maxX), randInt(maxY));
			g2.drawRoundRect(randInt(maxX)+200, randInt(maxY), randInt(maxX), randInt(maxY), 10, 10);
			
			g2.fillRect(randInt(maxX/3), randInt(maxY/2), randInt(maxX/2), randInt(maxY/2));
			g2.fillOval(randInt(maxX/3), randInt(maxY/2), randInt(maxX/2), randInt(maxY/2));
			g2.fillRoundRect(randInt(maxX/3), randInt(maxY/3), randInt(maxX/2), randInt(maxY/2), 10, 10);
		}*/
		
		g2.setColor(Color.red);
		g2.fillOval(30, 30, 140, 140);
		
		g2.setColor(Color.green);
		g2.fillOval(50, 50, 100, 100);
		
		g2.setColor(Color.blue);
		g2.fillOval(75, 75, 100, 100);
		
		g2.setColor(Color.red);
		g2.fillOval(155, 105, 100, 100);
		
		g2.setColor(Color.blue);
		g2.fillOval(125, 155, 50, 50);
		
		g2.setColor(Color.green);
		g2.fillOval(55, 150, 60, 60);
		g2.fillOval(125, 30, 60, 60);
		
		g2.setColor(Color.black);
		g2.drawString("Flood Fill", 100, 250);
		
		
		
		g2.setColor(Color.red);
		g2.drawOval(430, 130, 160, 160);
		
		g2.setColor(Color.green);
		g2.drawOval(450, 150, 100, 100);
		
		g2.setColor(Color.blue);
		g2.drawOval(475, 175, 100, 100);

		
		
		
		
		g2.setColor(Color.green);
		g2.drawOval(730, 130, 160, 160);
		
		g2.setColor(Color.blue);
		g2.drawOval(750, 150, 100, 100);
		
		g2.setColor(Color.red);
		g2.drawOval(775, 175, 100, 100);
		
		
		
		
		g2.setColor(Color.blue);
		g2.drawOval(1030, 130, 160, 160);
		
		g2.setColor(Color.red);
		g2.drawOval(1050, 150, 100, 100);
		
		g2.setColor(Color.green);
		g2.drawOval(1075, 175, 100, 100);
		
		g2.setColor(Color.black);
		g2.drawString("Boundary Fill", 775, 350);
		
		
		optionPanel=new JPanel();
		optionPanel.setBackground(Color.red);
		String[] sides={"3","4","5","6","7","8","9"};
		jcb_numSides=new JComboBox<>(sides);
		jcb_numSides.setSelectedIndex(5);
		jrbtn_floodFill=new JRadioButton("Flood Fill");
		jrbtn_floodFill.setOpaque(false);
		jrbtn_boundaryFill=new JRadioButton("Boundary Fill");
		jrbtn_boundaryFill.setOpaque(false);
		String[] colorList={"Black","Red","Green","Blue"};
		jcb_boundaryColor=new JComboBox<>(colorList);
		jrbtn_scanLine=new JRadioButton("Scan Line");
		jrbtn_scanLine.setOpaque(false);
		
		
		jbtn_apply=new JButton("Apply");
		jbtn_apply.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  bresenhamLine(pts.get(pts.size()-1), pts.get(0));
			  for(int i=0;i<pts.size()-1;i++){
				  bresenhamLine(pts.get(i), pts.get(i+1));
			  }
			  flagOriginalShape=true;
			  repaint();
		  }
		});
		
		jbtn_reset=new JButton("Reset");
		jbtn_reset.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  arrPtsX=new ArrayList<Integer>();
			  arrPtsY=new ArrayList<Integer>();
			  arrTempX=new ArrayList<Integer>();
			  arrTempY=new ArrayList<Integer>();
				
			  pts=new ArrayList<>();
			  counterNumSides=0;
			  flagOriginalShape=false;
			  
			  mouseClickLabel.setText("");
			  repaint();
		  }
		});
		jbtn_close=new JButton("Close");
		jbtn_close.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  shape f=new shape();
			  f.setVisible(true);
			  f.setSize(screenX,screenY-50);
			  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  }
		});
		
		optionPanel.add(new JLabel("Num Sides: "));
		optionPanel.add(jcb_numSides);
		optionPanel.add(new JLabel("     |    "));
		optionPanel.add(jrbtn_floodFill);
		optionPanel.add(new JLabel("    |    "));
		optionPanel.add(jrbtn_boundaryFill);
		optionPanel.add(jcb_boundaryColor);
		optionPanel.add(new JLabel("     |    "));
		optionPanel.add(jrbtn_scanLine);
		optionPanel.add(new JLabel("    |    "));
		optionPanel.add(jbtn_apply);
		optionPanel.add(jbtn_reset);
		optionPanel.add(jbtn_close);
		
		mouseClickLabel=new JLabel("", JLabel.CENTER);
		optionPanel.add(mouseClickLabel);
		
		add(optionPanel, BorderLayout.NORTH);
		addMouseListener(this);
		
		
		
		ButtonGroup fillingAlgoGroup=new ButtonGroup();
		fillingAlgoGroup.add(jrbtn_floodFill);
		fillingAlgoGroup.add(jrbtn_boundaryFill);
		fillingAlgoGroup.add(jrbtn_scanLine);
		jrbtn_floodFill.setSelected(true);
	}
	
	public void paintComponent(Graphics g) {
		
		if(image!=null){
			g.drawImage(image, 0, 0, null);
		}
		
		if(flagOriginalShape){
			g2 = (Graphics2D) image.getGraphics();
			g2.setColor(Color.black);
			

			for(int i=0;i<arrPtsX.size();i++){
				int x=arrPtsX.get(i);
				int y=arrPtsY.get(i);
				
				g2.drawLine(x, y, x, y);
			}
			
			/*g2.drawLine((int)(pts.get(pts.size()-1).getX()), (int)(pts.get(pts.size()-1).getY()), (int)(pts.get(0).getX()), (int)(pts.get(0).getY()));
			for(int i=0;i<pts.size()-1;i++){
				g2.drawLine((int)(pts.get(i).getX()), (int)(pts.get(i).getY()), (int)(pts.get(i+1).getX()), (int)(pts.get(i+1).getY()));
			}*/
			repaint();
		}
		

		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==3){
			
			if(jrbtn_floodFill.isSelected()){
				optionPanel.setVisible(false);
				floodFill(e.getX(), e.getY(), image.getRGB(e.getX(), e.getY()));
				optionPanel.setVisible(true);
				repaint();
			}
			
			if(jrbtn_boundaryFill.isSelected()){
				int x=0;
				
				if(jcb_boundaryColor.getSelectedItem().equals("Black"))
					x=getIntFromColor(0,0,0);
				
				else if(jcb_boundaryColor.getSelectedItem().equals("Red"))
					x=getIntFromColor(255,0,0);
				
				else if(jcb_boundaryColor.getSelectedItem().equals("Green"))
					x=getIntFromColor(0,255,0);
				
				else if(jcb_boundaryColor.getSelectedItem().equals("Blue"))
					x=getIntFromColor(0,0,255);
				
				optionPanel.setVisible(false);
				boundaryFill(e.getX(), e.getY(), x);
				optionPanel.setVisible(true);
				repaint();
			}
			
			if(jrbtn_scanLine.isSelected()){
				scanLine();
				repaint();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount()==1)
			previousPoint = e.getPoint();
		System.out.println("MOUSE pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String temp=(String)(jcb_numSides.getSelectedItem());
		
		if(counterNumSides<(Integer.parseInt(temp))){
			if(e.getClickCount()==1)
				pts.add(previousPoint);
			System.out.println("MOUSE released "+pts);
			counterNumSides++;
		}
		
		if(counterNumSides==(Integer.parseInt(temp)))
			mouseClickLabel.setText(temp + " pts already entered. CLICK ON APPLY BUTTON!");
	}
	
	
	
	
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Filling Algorithm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fillingAlgo panel = new fillingAlgo();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static int randInt(int max) {
	    Random rand =new Random();
	    int min=10;
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public int getIntFromColor(int Red, int Green, int Blue){
	    Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
	    Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
	    Blue = Blue & 0x000000FF; //Mask out anything not blue.

	    return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
	}
	
	
	public static void bresenhamLine(Point start, Point end){
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		int x=(int)(start.getX());
		int x2=(int)(end.getX());
		int y=(int)(start.getY());
		int y2=(int)(end.getY());
		
	    int w = x2 - x ;
	    int h = y2 - y ;
	    
	    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
	    
	    if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
	    if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
	    if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
	    
	    int longest = Math.abs(w) ;
	    int shortest = Math.abs(h) ;
	    
	    if (!(longest>shortest)) {
	        longest = Math.abs(h) ;
	        shortest = Math.abs(w) ;
	        if (h<0) 
	        	dy2 = -1 ; 
	        else if (h>0) 
	        	dy2 = 1 ;
	        dx2 = 0 ;            
	    }
	    
	    int numerator = longest >> 1 ;
	    
	    for (int i=0;i<=longest;i++) {
	    	arrTempX.add(x);
	    	arrTempY.add(y);
	        numerator += shortest ;
	        
	        if (!(numerator<longest)) {
	            numerator -= longest ;
	            x += dx1 ;
	            y += dy1 ;
	        } else {
	            x += dx2 ;
	            y += dy2 ;
	        }
	    }
	    arrPtsX.addAll(arrTempX);
		arrPtsY.addAll(arrTempY);
	}
	
	
	public void floodFill(int initialX, int initialY, int interiorColor) {   
		Queue<Point> queuePointsFloodFill = new LinkedList<Point>();
			   
		queuePointsFloodFill.add(new Point(initialX,initialY));
			   
		while(!queuePointsFloodFill.isEmpty()){
			Point currentPoint=queuePointsFloodFill.remove();
			int x=currentPoint.x;
			int y=currentPoint.y;
				   
			if (x < image.getWidth() && /* more */ image.getRGB(x, y) == interiorColor) {
				image.setRGB(x, y, 0);
				update(getGraphics());
	
				if(!(queuePointsFloodFill.contains(new Point(x+1, y))) && ( image.getRGB(x+1, y) == interiorColor))
					queuePointsFloodFill.add(new Point(x+1, y));
	
				if(!(queuePointsFloodFill.contains(new Point(x-1, y))) && ( image.getRGB(x-1, y) == interiorColor))
					queuePointsFloodFill.add(new Point(x-1, y));
			        	
				if(!(queuePointsFloodFill.contains(new Point(x, y+1))) && ( image.getRGB(x, y+1) == interiorColor))
					queuePointsFloodFill.add(new Point(x, y+1));
			        	
				if(!(queuePointsFloodFill.contains(new Point(x, y-1))) && ( image.getRGB(x, y-1) == interiorColor))
					queuePointsFloodFill.add(new Point(x, y-1));
	
			}
		}
	}
	
	
	public void boundaryFill(int initialX, int initialY, int borderColor){
		Queue<Point> queuePointsBoundaryFill = new LinkedList<Point>();
		
		Queue<Point> queuePointsBoundaryFill_CHECKING = new LinkedList<Point>();
		
		queuePointsBoundaryFill.add(new Point(initialX,initialY));
		queuePointsBoundaryFill_CHECKING.add(new Point(initialX,initialY));
		
		while(!queuePointsBoundaryFill.isEmpty()){
			System.out.println(queuePointsBoundaryFill);
			Point currentPoint=queuePointsBoundaryFill.remove();
			int x=currentPoint.x;
			int y=currentPoint.y;
			
			if (x < image.getWidth() && /* more */ image.getRGB(x, y) != borderColor) {
				image.setRGB(x, y, 0);
				update(getGraphics());
				
				/*if(!(queuePointsBoundaryFill_CHECKING.contains(new Point(x+1, y-1))) && ( image.getRGB(x+1, y-1) != borderColor)){
					queuePointsBoundaryFill.add(new Point(x+1, y-1));
					queuePointsBoundaryFill_CHECKING.add(new Point(x+1, y-1));
					
				}
				
				if(!(queuePointsBoundaryFill_CHECKING.contains(new Point(x+1, y+1))) && ( image.getRGB(x+1, y+1) != borderColor)){
	        		queuePointsBoundaryFill.add(new Point(x+1, y+1));
	        		queuePointsBoundaryFill_CHECKING.add(new Point(x+1, y+1));
				}
				
				if(!(queuePointsBoundaryFill_CHECKING.contains(new Point(x-1, y-1))) && ( image.getRGB(x-1, y-1) != borderColor)){
	        		queuePointsBoundaryFill.add(new Point(x-1, y-1));
	        		queuePointsBoundaryFill_CHECKING.add(new Point(x-1, y-1));
				}
				
	        	if(!(queuePointsBoundaryFill_CHECKING.contains(new Point(x-1, y+1))) && ( image.getRGB(x-1, y+1) != borderColor)){
	        		queuePointsBoundaryFill.add(new Point(x-1, y+1));
	        		queuePointsBoundaryFill_CHECKING.add(new Point(x-1, y+1));
				}*/
				
	        	
	        	
	        	
	        	if(!(queuePointsBoundaryFill_CHECKING.contains(new Point(x, y-1))) && ( image.getRGB(x, y-1) != borderColor)){
					queuePointsBoundaryFill.add(new Point(x, y-1));
					queuePointsBoundaryFill_CHECKING.add(new Point(x, y-1));
				}
				
				if(!(queuePointsBoundaryFill_CHECKING.contains(new Point(x, y+1))) && ( image.getRGB(x, y+1) != borderColor)){
	        		queuePointsBoundaryFill.add(new Point(x, y+1));
	        		queuePointsBoundaryFill_CHECKING.add(new Point(x, y+1));
				}
				
				if(!(queuePointsBoundaryFill_CHECKING.contains(new Point(x-1, y))) && ( image.getRGB(x-1, y) != borderColor)){
	        		queuePointsBoundaryFill.add(new Point(x-1, y));
	        		queuePointsBoundaryFill_CHECKING.add(new Point(x-1, y));
				}
				
	        	if(!(queuePointsBoundaryFill_CHECKING.contains(new Point(x+1, y))) && ( image.getRGB(x+1, y) != borderColor)){
	        		queuePointsBoundaryFill.add(new Point(x+1, y));
	        		queuePointsBoundaryFill_CHECKING.add(new Point(x+1, y));
	        	}
			}
		}
	}
	
	
	
	
	
	
	
	//== START ======================================================================== ALL CODE FOR SCAN LINE ALGO ===========================================================
	
	public int findStartPT(ArrayList<Point> pts){
		int start=0;
		
		for(int i=0;i<pts.size();i++){
			int y=(int) pts.get(i).getY();
			
			if(y<start)
				start=y;
		}
		return start;
	}
	
	public int findEndPT(ArrayList<Point> pts){
		int end=0;
		
		for(int i=0;i<pts.size();i++){
			int y=(int) pts.get(i).getY();
			
			if(y>end)
				end=y;
		}
		return end;
	}
	
	
	public void scanLine(){
		scanLineEdge[] activeEdgeList=new scanLineEdge[pts.size()];
		
		//adding edges into activeEdgeList
		activeEdgeList[pts.size()-1]=new scanLineEdge(pts.get(0), pts.get(pts.size()-1));
		for(int i=0;i<pts.size()-1;i++){
			activeEdgeList[i]=new scanLineEdge(pts.get(i), pts.get(i+1));
		}
		
		
		int startPT=findStartPT(pts);
		int endPT=findEndPT(pts);
		
		for(int i=startPT;i<=endPT;i++){			
			
			ArrayList<Integer> listPT=new ArrayList<>();
			
			//checking if current scan line i is touching the shape line, if yes, update currentX, then add to the list.
			for(int j=0;j<activeEdgeList.length;j++){
				double currentY1=activeEdgeList[j].getP1().getY();
				double currentY2=activeEdgeList[j].getP2().getY();
				
				
				if(i>currentY1 && i<=currentY2){
					activeEdgeList[j].updateCurrentX();
					listPT.add((int)activeEdgeList[j].getCurrentX());
				}
			}
			
			
			//sorting the list, starting with the smallest currentX
			for(int j=0;j<listPT.size();j++){
				for(int k=0;k<listPT.size()-1;k++){
					if(listPT.get(k)>listPT.get(k+1)){
						int swaptmp = listPT.get(k);
						listPT.set(k, listPT.get(k+1));
						listPT.set(k+1, swaptmp);
					}
				}
			}
			
			
			for (int j = 0; j < listPT.size(); j+=2){
				g2 = (Graphics2D) image.getGraphics();
				g2.setColor(Color.magenta);
				g2.drawLine(listPT.get(j), i,listPT.get(j+1), i);
			}
		}
	}
	//== END ======================================================================== ALL CODE FOR SCAN LINE ALGO ===========================================================
}
