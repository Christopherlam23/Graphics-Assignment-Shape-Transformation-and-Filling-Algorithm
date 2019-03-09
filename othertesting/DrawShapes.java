package othertesting;

import java.awt.*; 
import java.awt.event.*; 
import java.util.Random; 

import javax.swing.*; 

public class DrawShapes extends JFrame 
{ 
private final int CIRCLE = 0; 
private final int SQUARE = 1; 
private final int OVAL = 2; 
private final int RECTANGLE = 3; 
private int shape; 
private JComboBox comboBox; 
private String [ ] names = { "Circle", "Square", "Oval", "Rectangle" }; 

public DrawShapes ( ){ 
	super ( "Drawing Random Shapes" ); 
	
	comboBox = new JComboBox ( names ); 
	getContentPane ( ).add ( comboBox, BorderLayout.SOUTH ); 
	
	comboBox.addItemListener ( 
		new ItemListener ( ){ 
			public void itemStateChanged ( ItemEvent e ){ 
				shape = comboBox.getSelectedIndex ( ); 
				repaint ( ); 
			} 
		} 
		); 
		
		setSize ( 400, 400 ); 
		setVisible ( true ); 
} 

	public void paint ( Graphics g ) 
	{ 
		super.paint ( g ); 
		Random r = new Random ( ); 
		
		for ( int k = 1; k<20; k++) { 
		int x = r.nextInt ( 390 ); 
		int y = r.nextInt ( 370 ) + 25; 
		int w = r.nextInt ( 400 - x ); 
		int h = r.nextInt ( 400 - y ); 
	
		switch ( shape ) 
		{ 
			case CIRCLE: 
				g.drawOval ( x, y, w, w ); 
				break;
				
			case SQUARE: 
				g.drawRect ( x, y, w, w ); 
				break;
				
			case OVAL: 
				g.drawOval ( x, y, w, h ); 
				break;
				
			case RECTANGLE: 
				g.drawRect ( x, y, w, h ); 
				break; 
		} 
	} 
} 

	public static void main ( String args [ ] ){ 
		DrawShapes application = new DrawShapes ( ); 
		application.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE ); 
	} 
} 
