package assignmentGraphic2;

import java.awt.event.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class shape extends JFrame implements MouseListener{
	
	public static boolean flagDrawShape=false;
	public static boolean flagOriginalShape=false;
	public static boolean flagTranslateShape=false;
	public static boolean flagRotateShape=false;
	public static boolean flagScaleShape=false;
	public static boolean flagReflectShape=false;
	public static boolean flagShearShape=false;
	
	public static boolean flagFloodFill=false;
	public static boolean flagBoundaryFill=false;
	public static boolean flagScanLine=false;
	
	
	public static int translatePOS=0;
	public static int rotatePOS=0;
	public static int scalePOS=0;
	public static int reflectPOS=0;
	public static int shearPOS=0;

	
	public static ArrayList<Point> pts=new ArrayList<>();
	public static Point previousPoint;
	//public static Point nextPoint;
	public static int counterNumSides=0;
	
	public static ArrayList<ArrayList<Integer>> TwoD_arrPtsX=new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> TwoD_arrPtsY=new ArrayList<ArrayList<Integer>>();
	
	public static ArrayList<Integer> arrPtsX=new ArrayList<Integer>();
	public static ArrayList<Integer> arrPtsY=new ArrayList<Integer>();
	public static ArrayList<Integer> arrTempX=new ArrayList<Integer>();
	public static ArrayList<Integer> arrTempY=new ArrayList<Integer>();
	
	public static ArrayList<ArrayList<Integer>> arrTranslateX=new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> arrTranslateY=new ArrayList<ArrayList<Integer>>();
	
	public static ArrayList<ArrayList<Integer>> arrRotateX1=new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> arrRotateY1=new ArrayList<ArrayList<Integer>>();
	
	public static ArrayList<ArrayList<Integer>> arrRotateX2=new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> arrRotateY2=new ArrayList<ArrayList<Integer>>();
	
	public static ArrayList<Integer> arrScaleX1=new ArrayList<Integer>();
	public static ArrayList<Integer> arrScaleY1=new ArrayList<Integer>();
	
	public static ArrayList<ArrayList<Integer>> arrScaleX2=new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> arrScaleY2=new ArrayList<ArrayList<Integer>>();
	
	public static ArrayList<Integer> arrReflectionX=new ArrayList<Integer>();
	public static ArrayList<Integer> arrReflectionY=new ArrayList<Integer>();
	
	public static ArrayList<Integer> arrShearX=new ArrayList<Integer>();
	public static ArrayList<Integer> arrShearY=new ArrayList<Integer>();
	
	//public static Stack<Point> stackPoints = new Stack<>();
	public static Queue<Point> queuePoints = new LinkedList<Point>();
	public static ArrayList<Integer> arrBoundaryFillX=new ArrayList<Integer>();
	public static ArrayList<Integer> arrBoundaryFillY=new ArrayList<Integer>();
	
	public static int screenX=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int screenY=(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int centerx=(screenX-((int)(280*1.06)))/2;
	public static int centery=(screenY-80)/2;
	
	public static JPanel drawPanel;
	public static JPanel optionsPanel;
	
	private JComboBox jcb_numSides;
	public static JRadioButton transformation;
	public static JRadioButton fillingAlgo;
	
	public static JRadioButton rotation1_rbtn;
	public static JRadioButton rotation2_rbtn;
	public static JRadioButton rotationCW1_rbtn;
	public static JRadioButton rotationACW1_rbtn;
	public static JRadioButton rotationCW2_rbtn;
	public static JRadioButton rotationACW2_rbtn;
	public static JRadioButton scaling1_rbtn;
	public static JRadioButton scaling2_rbtn;
	
	public static JTextField translateX;
	public static JTextField translateY;
	
	public static JTextField degreeRotation1;
	
	public static JTextField fixedRotationPtX;
	public static JTextField fixedRotationPtY;
	public static JTextField degreeRotation2;
	
	public static JComboBox scalingTimesX1;
	public static JComboBox scalingTimesY1;
	
	public static JTextField fixedScalingPtX;
	public static JTextField fixedScalingPtY;
	public static JComboBox scalingTimesX2;
	public static JComboBox scalingTimesY2;
	
	public static JCheckBox chkbx_reflection;
	public static JRadioButton reflec_xaxis;
	public static JRadioButton reflec_yaxis;
	public static JRadioButton reflec_xyaxis;
	public static JRadioButton reflec_yEqualx;
	public static JRadioButton reflec_yEqualMinusx;
	
	public static JCheckBox chkbx_shear;
	public static JRadioButton shearXaxis1;
	public static JRadioButton shearYaxis1;
	public static JRadioButton shearXaxis2;
	public static JRadioButton shearYaxis2;
	public static JComboBox shearValueX1;
	public static JComboBox shearValueX2;
	public static JTextField shearYcoordinate;
	public static JComboBox shearValueY1;
	public static JComboBox shearValueY2;
	public static JTextField shearXcoordinate;
	
	private JButton apply_btn;
	private JButton clear_btn;
	private JButton shapeFilling_btn;
	
	
	public shape(){
		drawPanel=new drawGrid();
		optionsPanel=new JPanel();
		
		drawPanel.setPreferredSize(new Dimension(screenX-((int)(280*1.06)),screenY-50));
		drawPanel.setBackground(new Color(0,0,0));
		drawPanel.addMouseListener(this);
		
		optionsPanel.setPreferredSize(new Dimension(280,screenY-50));
		optionsPanel.setBackground(Color.red);
		
		getContentPane().add(drawPanel,BorderLayout.WEST);
		getContentPane().add(optionsPanel,BorderLayout.EAST);
		
		String[] numSide={"2","3","4","5","6","7","8","9"};
		jcb_numSides=new JComboBox(numSide);
		jcb_numSides.setSelectedIndex(1);
		optionsPanel.add(new JLabel("             Num Sides         "));
		optionsPanel.add(jcb_numSides);
		
		JPanel translationPanel=new JPanel();
		translationPanel.setPreferredSize(new Dimension(280,60));
		translationPanel.setLayout(new GridLayout(3,2));
		translationPanel.setBackground(Color.gray);
		translateX=new JTextField("0");
		translateY=new JTextField("0");
		
		translationPanel.add(new JLabel("Translation",SwingConstants.CENTER));
		translationPanel.add(new JLabel(""));
		translationPanel.add(new JLabel("x",SwingConstants.CENTER));
		translationPanel.add(translateX);
		translationPanel.add(new JLabel("y",SwingConstants.CENTER));
		translationPanel.add(translateY);
		optionsPanel.add(translationPanel);
		
		
		JPanel rotationPanel1=new JPanel();
		rotationPanel1.setPreferredSize(new Dimension(280,60));
		rotationPanel1.setLayout(new GridLayout(3,2));
		rotationPanel1.setBackground(Color.lightGray);
		rotation1_rbtn=new JRadioButton("       Rotation");
		rotation1_rbtn.setOpaque(false);
		degreeRotation1=new JTextField("0");
		degreeRotation1.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  int degree=Integer.parseInt(degreeRotation1.getText());
			  if(degree<-180)
				  degreeRotation1.setText("-180");
			  if(degree>180)
				  degreeRotation1.setText("180");
		  }
		});
		rotationCW1_rbtn=new JRadioButton("Clockwise");
		rotationCW1_rbtn.setOpaque(false);
		rotationACW1_rbtn=new JRadioButton("Anti-Clockwise");
		rotationACW1_rbtn.setOpaque(false);
		
		rotationPanel1.add(rotation1_rbtn);
		rotationPanel1.add(new JLabel(""));
		rotationPanel1.add(new JLabel("Degree",SwingConstants.CENTER));
		rotationPanel1.add(degreeRotation1);
		rotationPanel1.add(rotationCW1_rbtn);
		rotationPanel1.add(rotationACW1_rbtn);
		optionsPanel.add(rotationPanel1);
		
		
		JPanel rotationPanel2=new JPanel();
		rotationPanel2.setPreferredSize(new Dimension(280,100));
		rotationPanel2.setLayout(new GridLayout(5,2));
		rotationPanel2.setBackground(Color.gray);
		rotation2_rbtn=new JRadioButton("       Rotation about a");
		rotation2_rbtn.setOpaque(false);
		degreeRotation2=new JTextField("0");
		degreeRotation2.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  int degree=Integer.parseInt(degreeRotation2.getText());
			  if(degree<-180)
				  degreeRotation2.setText("-180");
			  if(degree>180)
				  degreeRotation2.setText("180");
		  }
		});
		fixedRotationPtX=new JTextField();
		fixedRotationPtY=new JTextField();
		rotationCW2_rbtn=new JRadioButton("Clockwise");
		rotationCW2_rbtn.setOpaque(false);
		rotationACW2_rbtn=new JRadioButton("Anti-Clockwise");
		rotationACW2_rbtn.setOpaque(false);
		
		rotationPanel2.add(rotation2_rbtn);
		rotationPanel2.add(new JLabel("Fixed Point"));
		rotationPanel2.add(new JLabel("x",SwingConstants.CENTER));
		rotationPanel2.add(fixedRotationPtX);
		rotationPanel2.add(new JLabel("y",SwingConstants.CENTER));
		rotationPanel2.add(fixedRotationPtY);
		rotationPanel2.add(new JLabel("Degree",SwingConstants.CENTER));
		rotationPanel2.add(degreeRotation2);
		rotationPanel2.add(rotationCW2_rbtn);
		rotationPanel2.add(rotationACW2_rbtn);
		optionsPanel.add(rotationPanel2);
		
		
		JPanel scalingPanel1=new JPanel();
		scalingPanel1.setPreferredSize(new Dimension(280,50));
		scalingPanel1.setLayout(new GridLayout(3,2));
		scalingPanel1.setBackground(Color.lightGray);
		scaling1_rbtn=new JRadioButton("        Scaling");
		scaling1_rbtn.setOpaque(false);
		String[] scaleValues={"-1","-0.5","0.5","1","1.5","2","2.5"};
		scalingTimesX1=new JComboBox<>(scaleValues);
		scalingTimesX1.setSelectedIndex(3);
		scalingTimesY1=new JComboBox<>(scaleValues);
		scalingTimesY1.setSelectedIndex(3);
		
		scalingPanel1.add(scaling1_rbtn);
		scalingPanel1.add(new JLabel(""));
		scalingPanel1.add(new JLabel("Sx",SwingConstants.CENTER));
		scalingPanel1.add(scalingTimesX1);
		scalingPanel1.add(new JLabel("Sy",SwingConstants.CENTER));
		scalingPanel1.add(scalingTimesY1);
		optionsPanel.add(scalingPanel1);
		
		
		JPanel scalingPanel2=new JPanel();
		scalingPanel2.setPreferredSize(new Dimension(280,90));
		scalingPanel2.setLayout(new GridLayout(5,2));
		scalingPanel2.setBackground(Color.gray);
		scaling2_rbtn=new JRadioButton("        Scaling about a");
		scaling2_rbtn.setOpaque(false);
		scalingTimesX2=new JComboBox<>(scaleValues);
		scalingTimesX2.setSelectedIndex(3);
		scalingTimesY2=new JComboBox<>(scaleValues);
		scalingTimesY2.setSelectedIndex(3);
		fixedScalingPtX=new JTextField();
		fixedScalingPtY=new JTextField();
		
		scalingPanel2.add(scaling2_rbtn);
		scalingPanel2.add(new JLabel("Fixed Point"));
		scalingPanel2.add(new JLabel("Sx",SwingConstants.CENTER));
		scalingPanel2.add(scalingTimesX2);
		scalingPanel2.add(new JLabel("Sy",SwingConstants.CENTER));
		scalingPanel2.add(scalingTimesY2);
		scalingPanel2.add(new JLabel("x",SwingConstants.CENTER));
		scalingPanel2.add(fixedScalingPtX);
		scalingPanel2.add(new JLabel("y",SwingConstants.CENTER));
		scalingPanel2.add(fixedScalingPtY);
		optionsPanel.add(scalingPanel2);
		
		
		JPanel reflectionPanel=new JPanel();
		reflectionPanel.setPreferredSize(new Dimension(280,100));
		reflectionPanel.setLayout(new GridLayout(6,2));
		reflectionPanel.setBackground(Color.lightGray);
		chkbx_reflection=new JCheckBox("Reflection");
		reflec_xaxis=new JRadioButton("About x-axis");
		reflec_xaxis.setOpaque(false);
		reflec_yaxis=new JRadioButton("About y-axis");
		reflec_yaxis.setOpaque(false);
		reflec_xyaxis=new JRadioButton("About x and y-axis");
		reflec_xyaxis.setOpaque(false);
		reflec_yEqualx=new JRadioButton("About line y=x");
		reflec_yEqualx.setOpaque(false);
		reflec_yEqualMinusx=new JRadioButton("About line y=-x");
		reflec_yEqualMinusx.setOpaque(false);
		reflectionPanel.add(chkbx_reflection);
		reflectionPanel.add(new JLabel(""));
		reflectionPanel.add(new JLabel(""));
		reflectionPanel.add(reflec_xaxis);
		reflectionPanel.add(new JLabel(""));
		reflectionPanel.add(reflec_yaxis);
		reflectionPanel.add(new JLabel(""));
		reflectionPanel.add(reflec_xyaxis);
		reflectionPanel.add(new JLabel(""));
		reflectionPanel.add(reflec_yEqualx);
		reflectionPanel.add(new JLabel(""));
		reflectionPanel.add(reflec_yEqualMinusx);
		optionsPanel.add(reflectionPanel);
		
		
		JPanel shearPanel=new JPanel();
		shearPanel.setPreferredSize(new Dimension(280,200));
		shearPanel.setLayout(new GridLayout(11,2));
		shearPanel.setBackground(Color.gray);
		chkbx_shear=new JCheckBox("Shear");
		shearXaxis1=new JRadioButton("          x-axis");
		shearXaxis1.setOpaque(false);
		shearYaxis1=new JRadioButton("          y-axis");
		shearYaxis1.setOpaque(false);
		shearXaxis2=new JRadioButton("          Line parallel to");
		shearXaxis2.setOpaque(false);
		shearYaxis2=new JRadioButton("          Line parallel to");
		shearYaxis2.setOpaque(false);
		String shearValues[]={"0.25","0.5","0.75","1.0","1.5","2.0","2.5"};
		shearValueX1=new JComboBox<>(shearValues);
		shearValueX1.setSelectedIndex(3);
		shearValueX2=new JComboBox<>(shearValues);
		shearValueX2.setSelectedIndex(3);
		shearYcoordinate=new JTextField();
		shearValueY1=new JComboBox<>(shearValues);
		shearValueY1.setSelectedIndex(3);
		shearValueY2=new JComboBox<>(shearValues);
		shearValueY2.setSelectedIndex(3);
		shearXcoordinate=new JTextField();
		shearPanel.add(chkbx_shear);
		shearPanel.add(new JLabel(""));
		shearPanel.add(shearXaxis1);
		shearPanel.add(new JLabel(""));
		shearPanel.add(new JLabel("Shear Value (Shx)",SwingConstants.CENTER));
		shearPanel.add(shearValueX1);
		shearPanel.add(shearYaxis1);
		shearPanel.add(new JLabel(""));
		shearPanel.add(new JLabel("Shear Value (Shy)",SwingConstants.CENTER));
		shearPanel.add(shearValueY1);
		shearPanel.add(shearXaxis2);
		shearPanel.add(new JLabel("x-axis"));
		shearPanel.add(new JLabel("Shear Value",SwingConstants.CENTER));
		shearPanel.add(shearValueX2);
		shearPanel.add(new JLabel("y-coordinate",SwingConstants.CENTER));
		shearPanel.add(shearYcoordinate);
		shearPanel.add(shearYaxis2);
		shearPanel.add(new JLabel("y-axis"));
		shearPanel.add(new JLabel("Shear Value",SwingConstants.CENTER));
		shearPanel.add(shearValueY2);
		shearPanel.add(new JLabel("x-coordinate",SwingConstants.CENTER));
		shearPanel.add(shearXcoordinate);
		optionsPanel.add(shearPanel);
		
		
		apply_btn=new JButton("Apply");
		apply_btn.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  bresenhamLine(pts.get(pts.size()-1), pts.get(0));
			  for(int i=0;i<pts.size()-1;i++){
				  bresenhamLine(pts.get(i), pts.get(i+1));
			  }
			  
			  
			  matrixTranslation(arrPtsX,arrPtsY);
				  
			  if(rotation1_rbtn.isSelected()){
				  matrixRotation1(arrTranslateX.get(arrTranslateX.size()-1),arrTranslateY.get(arrTranslateY.size()-1));
				  
				  if(scaling1_rbtn.isSelected()){
					  matrixScaling1(arrRotateX1.get(arrRotateX1.size()-1), arrRotateY1.get(arrRotateY1.size()-1));
						  
					  if(chkbx_reflection.isSelected()){
						  matrixReflection(arrScaleX1, arrScaleY1);
						  
						  if(chkbx_shear.isSelected()){
							  matrixShear(arrReflectionX, arrReflectionY);
						  }
					  }
					  else{
						  if(chkbx_shear.isSelected()){
							  matrixShear(arrScaleX1, arrScaleY1);
						  }
					  }
				  }
				  else if(scaling2_rbtn.isSelected()){
					  matrixScaling2(arrRotateX1.get(arrRotateX1.size()-1), arrRotateY1.get(arrRotateY1.size()-1));
					  
					  if(chkbx_reflection.isSelected()){
						  matrixReflection(arrScaleX2.get(arrScaleX2.size()-1), arrScaleY2.get(arrScaleY2.size()-1));
							  
						  if(chkbx_shear.isSelected()){
							  matrixShear(arrReflectionX, arrReflectionY);
						  }
					  }
					  else{
						  if(chkbx_shear.isSelected()){
							  matrixShear(arrScaleX2.get(arrScaleX2.size()-1), arrScaleY2.get(arrScaleY2.size()-1));
						  }
					  }
				  }
			  }
			  else if(rotation2_rbtn.isSelected()){
				  matrixRotation2(arrTranslateX.get(arrTranslateX.size()-1),arrTranslateY.get(arrTranslateY.size()-1));
					  
				  if(scaling1_rbtn.isSelected()){
					  matrixScaling1(arrRotateX2.get(arrRotateX2.size()-1), arrRotateY2.get(arrRotateY2.size()-1));
						  
					  if(chkbx_reflection.isSelected()){
						  matrixReflection(arrScaleX1, arrScaleY1);
							  
						  if(chkbx_shear.isSelected()){
							  matrixShear(arrReflectionX, arrReflectionY);
						  }
					  }
					  else{
						  if(chkbx_shear.isSelected()){
							  matrixShear(arrScaleX1, arrScaleY1);
						  }
					  }
				  }
				  else if(scaling2_rbtn.isSelected()){
					  matrixScaling2(arrRotateX2.get(arrRotateX2.size()-1), arrRotateY2.get(arrRotateY2.size()-1));
						  
					  if(chkbx_reflection.isSelected()){
						  matrixReflection(arrScaleX2.get(arrScaleX2.size()-1), arrScaleY2.get(arrScaleY2.size()-1));
							  
						  if(chkbx_shear.isSelected()){
							  matrixShear(arrReflectionX, arrReflectionY);
						  }
					  }
					  else{
						  if(chkbx_shear.isSelected()){
							  matrixShear(arrScaleX2.get(arrScaleX2.size()-1), arrScaleY2.get(arrScaleY2.size()-1));
						  }
					  }
				  }
			  }


			  
			  
			  //==================================================================adding the different arrayLists into the ARRAYLIST!==========================================
				  TwoD_arrPtsX.addAll(arrTranslateX);
				  TwoD_arrPtsY.addAll(arrTranslateY);
				  translatePOS=TwoD_arrPtsY.size();
					
				  if(rotation1_rbtn.isSelected()){
					  TwoD_arrPtsX.addAll(arrRotateX1);
					  TwoD_arrPtsY.addAll(arrRotateY1);
					  rotatePOS=TwoD_arrPtsY.size();
				  }
					
				  if(rotation2_rbtn.isSelected()){
					  for(int i=0;i<arrRotateX2.size();i++){
						  TwoD_arrPtsX.add(arrRotateX2.get(i));
						  TwoD_arrPtsY.add(arrRotateY2.get(i));
					  }
					  rotatePOS=TwoD_arrPtsY.size();
				  }
					
				  if(scaling1_rbtn.isSelected()){
					  TwoD_arrPtsX.add(arrScaleX1);
					  TwoD_arrPtsY.add(arrScaleY1);/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					  
					  TwoD_arrPtsX.add(arrScaleX1);
					  TwoD_arrPtsY.add(arrScaleY1);
					  
					  TwoD_arrPtsX.add(arrScaleX1);
					  TwoD_arrPtsY.add(arrScaleY1);
					  
					  TwoD_arrPtsX.add(arrScaleX1);
					  TwoD_arrPtsY.add(arrScaleY1);
					  
					  scalePOS=TwoD_arrPtsY.size();
				  }
					
				  if(scaling2_rbtn.isSelected()){
					  for(int i=0;i<arrScaleX2.size();i++){
						  ////duplicate values, to create the illusion that the animation is slower
						  
						  TwoD_arrPtsX.add(arrScaleX2.get(i));
						  TwoD_arrPtsX.add(arrScaleX2.get(i));
						  TwoD_arrPtsX.add(arrScaleX2.get(i));
						  
						  TwoD_arrPtsY.add(arrScaleY2.get(i));
						  TwoD_arrPtsY.add(arrScaleY2.get(i));
						  TwoD_arrPtsY.add(arrScaleY2.get(i));
					  }  
					  scalePOS=TwoD_arrPtsY.size();
				  }
					
				  if(chkbx_reflection.isSelected()){
					  TwoD_arrPtsX.add(arrReflectionX);
					  TwoD_arrPtsY.add(arrReflectionY);
					  
					  TwoD_arrPtsX.add(arrReflectionX);
					  TwoD_arrPtsY.add(arrReflectionY);
					  
					  TwoD_arrPtsX.add(arrReflectionX);
					  TwoD_arrPtsY.add(arrReflectionY);
					  
					  TwoD_arrPtsX.add(arrReflectionX);
					  TwoD_arrPtsY.add(arrReflectionY);
					  
					  reflectPOS=TwoD_arrPtsY.size();
				  }
					
				  if(chkbx_shear.isSelected()){
					  TwoD_arrPtsX.add(arrShearX);
					  TwoD_arrPtsY.add(arrShearY);
					  
					  TwoD_arrPtsX.add(arrShearX);
					  TwoD_arrPtsY.add(arrShearY);
					  
					  TwoD_arrPtsX.add(arrShearX);
					  TwoD_arrPtsY.add(arrShearY);
					  
					  TwoD_arrPtsX.add(arrShearX);
					  TwoD_arrPtsY.add(arrShearY);
					  
					  shearPOS=TwoD_arrPtsY.size();
				  }
				  flagDrawShape=true;
				  repaint();
		  	}
		});
		shapeFilling_btn=new JButton("Shape Filling");
		shapeFilling_btn.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  dispose();
			  JFrame frame = new JFrame("Filling Algorithm");
			  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  fillingAlgo panel = new fillingAlgo();
			  frame.add(panel);
			  frame.pack();
			  frame.setVisible(true);
		  }
		});
		
		clear_btn=new JButton("Clear Graph");
		clear_btn.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  counterNumSides=0;
			  flagOriginalShape=false;
			  flagDrawShape=false;
			  TwoD_arrPtsX=new ArrayList<ArrayList<Integer>>();
			  TwoD_arrPtsY=new ArrayList<ArrayList<Integer>>();
			  pts=new ArrayList<>();
			  arrPtsX=new ArrayList<Integer>();
			  arrPtsY=new ArrayList<Integer>();
			  arrTranslateX=new ArrayList<ArrayList<Integer>>();
			  arrTranslateY=new ArrayList<ArrayList<Integer>>();
				
			  arrRotateX1=new ArrayList<ArrayList<Integer>>();
			  arrRotateY1=new ArrayList<ArrayList<Integer>>();
				
			  arrRotateX2=new ArrayList<ArrayList<Integer>>();
			  arrRotateY2=new ArrayList<ArrayList<Integer>>();
				
			  arrScaleX1=new ArrayList<Integer>();
			  arrScaleY1=new ArrayList<Integer>();
				
			  arrScaleX2=new ArrayList<ArrayList<Integer>>();
			  arrScaleY2=new ArrayList<ArrayList<Integer>>();
				
			  arrReflectionX=new ArrayList<Integer>();
			  arrReflectionY=new ArrayList<Integer>();
				
			  arrShearX=new ArrayList<Integer>();
			  arrShearY=new ArrayList<Integer>();
			  repaint();
		  }
		});
		optionsPanel.add(apply_btn);
		optionsPanel.add(clear_btn);
		optionsPanel.add(shapeFilling_btn);
		
		
		//======================================================================BUTTON GROUP========================================================		
		ButtonGroup rotationGroup=new ButtonGroup();
		rotationGroup.add(rotation1_rbtn);
		rotationGroup.add(rotation2_rbtn);
		rotation1_rbtn.setSelected(true);
		
		ButtonGroup rotationDirectionGroup1=new ButtonGroup();
		rotationDirectionGroup1.add(rotationCW1_rbtn);
		rotationDirectionGroup1.add(rotationACW1_rbtn);
		rotationCW1_rbtn.setSelected(true);
		
		ButtonGroup rotationDirectionGroup2=new ButtonGroup();
		rotationDirectionGroup2.add(rotationCW2_rbtn);
		rotationDirectionGroup2.add(rotationACW2_rbtn);
		rotationCW2_rbtn.setSelected(true);
		
		
		ButtonGroup scalingGroup=new ButtonGroup();
		scalingGroup.add(scaling1_rbtn);
		scalingGroup.add(scaling2_rbtn);
		scaling1_rbtn.setSelected(true);
		
		ButtonGroup reflectionGroup=new ButtonGroup();
		reflectionGroup.add(reflec_xaxis);
		reflectionGroup.add(reflec_yaxis);
		reflectionGroup.add(reflec_xyaxis);
		reflectionGroup.add(reflec_yEqualx);
		reflectionGroup.add(reflec_yEqualMinusx);
		reflec_xaxis.setSelected(true);
		
		ButtonGroup shearGroup=new ButtonGroup();
		shearGroup.add(shearXaxis1);
		shearGroup.add(shearYaxis1);
		shearGroup.add(shearXaxis2);
		shearGroup.add(shearYaxis2);
		shearXaxis1.setSelected(true);
	}
	
	public static void main(String[] args){
		shape f=new shape();
		f.setVisible(true);
		f.setSize(screenX,screenY-50);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		previousPoint = e.getPoint();
		System.out.println("mouse pressed");		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String temp=(String)(jcb_numSides.getSelectedItem());
		
		if(counterNumSides<(Integer.parseInt(temp))){
			pts.add(previousPoint);
			System.out.println("mouse released");
			System.out.println(pts);
			counterNumSides++;
		}
		
		if(counterNumSides==pts.size()){
			flagOriginalShape=true;
			repaint();
		}
	}
	
	
	
	
	
	
	
	//============================================================================ bresenhamLine Function ===================================================================================
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
	
	public static void matrixTranslation(ArrayList<Integer> x, ArrayList<Integer> y){
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		int tX=Integer.parseInt(translateX.getText());
		int tY=Integer.parseInt(translateY.getText());
		
		boolean flagx=false;
		boolean flagy=false;
		
		if(tX<0)
			flagx=true;
		if(tY<0)
			flagy=true;
		
		
		if(tX!=0 && tY!=0){
			
			int p=0;
			int q=0;
			
			while(p<Math.abs(tX) || q<Math.abs(tY)){
				int a=p;
				int b=q;
				
				if(flagx)
					a=a*-1;
				
				if(flagy)
					b=b*-1;
				
					double[][] translateMatrix={{1, 0, a},{0, 1, b},{0 ,0 ,1}};
					
					for(int i=0;i<x.size();i++){
							
						ArrayList<Integer> ptfinal=new ArrayList<>();
							
						int ptx=x.get(i)-centerx;
						int pty=y.get(i)-centery;
							
						int ptMatrix[][]={{ptx},{pty},{1}};
							
						double sum=0;
							
						for (int k = 0; k < ptMatrix[0].length; k++) {
						       for (int e = 0; e < translateMatrix.length; e++) {
						    	   sum = 0;
						           for (int f = 0; f < ptMatrix.length; f++) {
						               sum = sum + translateMatrix[e][f] * ptMatrix[f][k];
						           }
						           ptfinal.add((int)(sum));
						       }
							}
							arrTempX.add(ptfinal.get(0)+centerx);
							arrTempY.add(ptfinal.get(1)+centery);
						}
						
					arrTranslateX.add(arrTempX);
					arrTranslateY.add(arrTempY);
					
					arrTempX=new ArrayList<Integer>();
					arrTempY=new ArrayList<Integer>();
					
					if(p<Math.abs(tX)) p+=5;
					if(q<Math.abs(tY)) q+=5;
			}
		}
		else{
			arrTranslateX.add(x);
			arrTranslateY.add(y);
		}
	}
	
	//============================================================================ matrixRotation1 Function ===================================================================================
	public static void matrixRotation1(ArrayList<Integer> x, ArrayList<Integer> y){
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		int degree=Integer.parseInt(degreeRotation1.getText());
		
		if(degree!=0){
			
			for(int degreeTemp=1; degreeTemp<=degree; degreeTemp+=2){
	
				double radian=Math.toRadians(degreeTemp);
				
					if(rotationCW1_rbtn.isSelected()){
						radian=radian*-1;
					}
					
					double[][] rotationMatrix={{Math.cos(radian), Math.sin(-radian), 0},{Math.sin(radian), Math.cos(radian), 0},{0 ,0 ,1}};
					
					for(int i=0;i<x.size();i++){
						
						ArrayList<Integer> ptfinal=new ArrayList<>();
						
						int ptx=x.get(i)-centerx;
						int pty=y.get(i)-centery;
						
						int ptMatrix[][]={{ptx},{pty},{1}};
						
						double sum=0;
						
						for (int k = 0; k < ptMatrix[0].length; k++) {
					        for (int e = 0; e < rotationMatrix.length; e++) {
					            sum = 0;
					            for (int f = 0; f < ptMatrix.length; f++) {
					                sum = sum + rotationMatrix[e][f] * ptMatrix[f][k];
					            }
					            ptfinal.add((int)(sum));
					        }
					    }
						arrTempX.add(ptfinal.get(0)+centerx);
						arrTempY.add(ptfinal.get(1)+centery);
					}
					arrRotateX1.add(arrTempX);
					arrRotateY1.add(arrTempY);
					
					arrTempX=new ArrayList<Integer>();
					arrTempY=new ArrayList<Integer>();
			}
		}
		else{
			arrRotateX1.add(x);
			arrRotateY1.add(y);
		}
		
		System.out.println(arrRotateX1);
		System.out.println(arrRotateY1);
	}
	
	//============================================================================ matrixRotation2 Function ===================================================================================
	public static void matrixRotation2(ArrayList<Integer> x, ArrayList<Integer> y){
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		int tX=Integer.parseInt(fixedRotationPtX.getText());
		int tY=Integer.parseInt(fixedRotationPtY.getText());

		
		
		//================================= translate inverse =================================================				boolean flagx=false;
		boolean flagy=false;
		
		if(tX<0)
			flagx=true;
		if(tY<0)
			flagy=true;
		
			
			int p=0;
			int q=0;
			
			while(p<Math.abs(tX) || q<Math.abs(tY)){
				int a=p;
				int b=q;
				
				if(flagx)
					a=a*-1;
				
				if(flagy)
					b=b*-1;
				
					double[][] translateInverseMatrix={{1, 0, -a},{0, 1, -b},{0 ,0 ,1}};
					for(int i=0;i<x.size();i++){
							
						ArrayList<Integer> ptfinal=new ArrayList<>();
							
						int ptx=x.get(i)-centerx;
						int pty=y.get(i)-centery;
							
						int ptMatrix[][]={{ptx},{pty},{1}};
							
						double sum=0;
							
						for (int k = 0; k < ptMatrix[0].length; k++) {
						       for (int e = 0; e < translateInverseMatrix.length; e++) {
						    	   sum = 0;
						           for (int f = 0; f < ptMatrix.length; f++) {
						               sum = sum + translateInverseMatrix[e][f] * ptMatrix[f][k];
						           }
						           ptfinal.add((int)(sum));
						       }
							}
							arrTempX.add(ptfinal.get(0)+centerx);
							arrTempY.add(ptfinal.get(1)+centery);
						}
						
					arrRotateX2.add(arrTempX);
					arrRotateY2.add(arrTempY);
					
					arrTempX=new ArrayList<Integer>();
					arrTempY=new ArrayList<Integer>();
					
					if(p<Math.abs(tX)) p+=5;
					if(q<Math.abs(tY)) q+=5;
		}
			

		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		
		//================================= rotate =================================================
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		int degree=Integer.parseInt(degreeRotation2.getText());
			
			for(int degreeTemp=1; degreeTemp<=degree; degreeTemp+=15){
	
				double radian=Math.toRadians(degreeTemp);
				
					if(rotationCW2_rbtn.isSelected()){
						radian=radian*-1;
					}
					
					double[][] rotationMatrix={{Math.cos(radian), Math.sin(-radian), 0},{Math.sin(radian), Math.cos(radian), 0},{0 ,0 ,1}};
					
					for(int i=0;i<arrRotateX2.get(arrRotateX2.size()-1).size();i++){
						
						ArrayList<Integer> ptfinal=new ArrayList<>();
						
						int ptx=arrRotateX2.get(arrRotateX2.size()-1).get(i)-centerx;
						int pty=arrRotateY2.get(arrRotateY2.size()-1).get(i)-centery;
						
						int ptMatrix[][]={{ptx},{pty},{1}};
						
						double sum=0;
						
						for (int k = 0; k < ptMatrix[0].length; k++) {
					        for (int e = 0; e < rotationMatrix.length; e++) {
					            sum = 0;
					            for (int f = 0; f < ptMatrix.length; f++) {
					                sum = sum + rotationMatrix[e][f] * ptMatrix[f][k];
					            }
					            ptfinal.add((int)(sum));
					        }
					    }
						arrTempX.add(ptfinal.get(0)+centerx);
						arrTempY.add(ptfinal.get(1)+centery);
					}
					arrRotateX2.add(arrTempX);
					arrRotateY2.add(arrTempY);
					
					arrTempX=new ArrayList<Integer>();
					arrTempY=new ArrayList<Integer>();
			
		}
		
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		
		//================================= translate back to normal =================================================
		flagy=false;
		flagx=false;
		
		if(tX<0)
			flagx=true;
		if(tY<0)
			flagy=true;
		
			
			p=0;
			q=0;
			
			while(p<Math.abs(tX) || q<Math.abs(tY)){
				int a=p;
				int b=q;
				
				if(flagx)
					a=a*-1;
				
				if(flagy)
					b=b*-1;
				
					double[][] translateMatrix={{1, 0, a},{0, 1, b},{0 ,0 ,1}};
					for(int i=0;i<arrRotateX2.get(arrRotateX2.size()-1).size();i++){
							
						ArrayList<Integer> ptfinal=new ArrayList<>();
							
						int ptx=arrRotateX2.get(arrRotateX2.size()-1).get(i)-centerx;
						int pty=arrRotateY2.get(arrRotateY2.size()-1).get(i)-centery;
							
						int ptMatrix[][]={{ptx},{pty},{1}};
							
						double sum=0;
							
						for (int k = 0; k < ptMatrix[0].length; k++) {
						       for (int e = 0; e < translateMatrix.length; e++) {
						    	   sum = 0;
						           for (int f = 0; f < ptMatrix.length; f++) {
						               sum = sum + translateMatrix[e][f] * ptMatrix[f][k];
						           }
						           ptfinal.add((int)(sum));
						       }
							}
							arrTempX.add(ptfinal.get(0)+centerx);
							arrTempY.add(ptfinal.get(1)+centery);
						}
						
					arrRotateX2.add(arrTempX);
					arrRotateY2.add(arrTempY);
					
					arrTempX=new ArrayList<Integer>();
					arrTempY=new ArrayList<Integer>();
					
					if(p<Math.abs(tX)) p+=15;
					if(q<Math.abs(tY)) q+=15;
		}
			

		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		
		
	}
	
	//============================================================================ matrixScaling1 Function ===================================================================================
	public static void matrixScaling1(ArrayList<Integer> x, ArrayList<Integer> y){
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		double Sx=Double.parseDouble((String)(scalingTimesX1.getSelectedItem()));
		double Sy=Double.parseDouble((String)(scalingTimesY1.getSelectedItem()));
		
		if(Sx!=1 && Sy!=1){
			double[][] scalingMatrix={{Sx, 0, 0},{0, Sy, 0},{0 ,0 ,1}};
			
			for(int i=0;i<x.size();i++){
				
				ArrayList<Integer> ptfinal=new ArrayList<>();
				
				int ptx=x.get(i)-centerx;
				int pty=y.get(i)-centery;
				
				int ptMatrix[][]={{ptx},{pty},{1}};
				
				double sum=0;
				
				for (int k = 0; k < ptMatrix[0].length; k++) {
			        for (int e = 0; e < scalingMatrix.length; e++) {
			            sum = 0;
			            for (int f = 0; f < ptMatrix.length; f++) {
			                sum = sum + scalingMatrix[e][f] * ptMatrix[f][k];
			            }
			            ptfinal.add((int)(sum));
			        }
			    }
				arrTempX.add(ptfinal.get(0)+centerx);
				arrTempY.add(ptfinal.get(1)+centery);
			}
			arrScaleX1.addAll(arrTempX);
			arrScaleY1.addAll(arrTempY);
		}
		else{
			arrScaleX1.addAll(x);
			arrScaleY1.addAll(y);
		}
		
	}
	
	//============================================================================ matrixScaling2 Function ===================================================================================
	public static void matrixScaling2(ArrayList<Integer> x, ArrayList<Integer> y){
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		int tX=Integer.parseInt(fixedScalingPtX.getText());
		int tY=Integer.parseInt(fixedScalingPtY.getText());
		
		//================================= translate inverse =================================================
		double[][] translateInverseMatrix={{1, 0, -tX},{0, 1, -tY},{0 ,0 ,1}};
				
		for(int i=0;i<x.size();i++){
					
			ArrayList<Integer> ptfinal=new ArrayList<>();
			
			int ptx=x.get(i)-centerx;
			int pty=y.get(i)-centery;
					
			int ptMatrix[][]={{ptx},{pty},{1}};
		
			double sum=0;
					
			for (int k = 0; k < ptMatrix[0].length; k++) {
				for (int e = 0; e < translateInverseMatrix.length; e++) {
		            sum = 0;
		            for (int f = 0; f < ptMatrix.length; f++) {
		            	sum = sum + translateInverseMatrix[e][f] * ptMatrix[f][k];
		            }
		            ptfinal.add((int)(sum));
				}
			}
			arrTempX.add(ptfinal.get(0)+centerx);
			arrTempY.add(ptfinal.get(1)+centery);
		}
		arrScaleX2.add(arrTempX);
		arrScaleY2.add(arrTempY);
				
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
				
				
		//================================= scaling =================================================	
		double Sx=Double.parseDouble((String)(scalingTimesX2.getSelectedItem()));
		double Sy=Double.parseDouble((String)(scalingTimesY2.getSelectedItem()));
		
		double[][] scalingMatrix={{Sx, 0, 0},{0, Sy, 0},{0 ,0 ,1}};
		
		for(int i=0;i<arrScaleX2.get(0).size();i++){
			
			ArrayList<Integer> ptfinal=new ArrayList<>();
			
			int ptx=arrScaleX2.get(0).get(i)-centerx;
			int pty=arrScaleY2.get(0).get(i)-centery;
			
			int ptMatrix[][]={{ptx},{pty},{1}};
			
			double sum=0;
			
			for (int k = 0; k < ptMatrix[0].length; k++) {
		        for (int e = 0; e < scalingMatrix.length; e++) {
		            sum = 0;
		            for (int f = 0; f < ptMatrix.length; f++) {
		                sum = sum + scalingMatrix[e][f] * ptMatrix[f][k];
		            }
		            ptfinal.add((int)(sum));
		        }
		    }
			arrTempX.add(ptfinal.get(0)+centerx);
			arrTempY.add(ptfinal.get(1)+centery);
		}
		arrScaleX2.add(arrTempX);
		arrScaleY2.add(arrTempY);
		
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		//================================= translate back to normal =================================================
		double[][] translateMatrix={{1, 0, tX},{0, 1, tY},{0 ,0 ,1}};
				
		for(int i=0;i<arrScaleX2.get(1).size();i++){
					
			ArrayList<Integer> ptfinal=new ArrayList<>();
					
			int ptx=arrScaleX2.get(1).get(i)-centerx;
			int pty=arrScaleY2.get(1).get(i)-centery;
					
			int ptMatrix[][]={{ptx},{pty},{1}};
					
			double sum=0;
					
			for (int k = 0; k < ptMatrix[0].length; k++) {
				for (int e = 0; e < translateMatrix.length; e++) {
					sum = 0;
					for (int f = 0; f < ptMatrix.length; f++) {
						sum = sum + translateMatrix[e][f] * ptMatrix[f][k];
					}
					ptfinal.add((int)(sum));
				}
			}
			arrTempX.add(ptfinal.get(0)+centerx);
			arrTempY.add(ptfinal.get(1)+centery);
		}
		arrScaleX2.add(arrTempX);
		arrScaleY2.add(arrTempY);
		
		System.out.println(arrScaleX2);
		System.out.println(arrScaleY2);
	}
	
	//============================================================================ matrixReflection Function ===================================================================================
	public static void matrixReflection(ArrayList<Integer> x, ArrayList<Integer> y){
		double[][] reflectMatrix = {{0, 0, 0},{0, 0, 0},{0 ,0, 0}};
		
		if(reflec_xaxis.isSelected()){
			reflectMatrix[0][0]=1;
			reflectMatrix[0][1]=0;
			reflectMatrix[0][2]=0;
			reflectMatrix[1][0]=0;
			reflectMatrix[1][1]=-1;
			reflectMatrix[1][2]=0;
			reflectMatrix[2][0]=0;
			reflectMatrix[2][1]=0;
			reflectMatrix[2][2]=1;
		}
		else if(reflec_yaxis.isSelected()){
			reflectMatrix[0][0]=-1;
			reflectMatrix[0][1]=0;
			reflectMatrix[0][2]=0;
			reflectMatrix[1][0]=0;
			reflectMatrix[1][1]=1;
			reflectMatrix[1][2]=0;
			reflectMatrix[2][0]=0;
			reflectMatrix[2][1]=0;
			reflectMatrix[2][2]=1;
		}
		else if(reflec_xyaxis.isSelected()){
			reflectMatrix[0][0]=-1;
			reflectMatrix[0][1]=0;
			reflectMatrix[0][2]=0;
			reflectMatrix[1][0]=0;
			reflectMatrix[1][1]=-1;
			reflectMatrix[1][2]=0;
			reflectMatrix[2][0]=0;
			reflectMatrix[2][1]=0;
			reflectMatrix[2][2]=1;		
		}
		else if(reflec_yEqualx.isSelected()){
			reflectMatrix[0][0]=0;
			reflectMatrix[0][1]=1;
			reflectMatrix[0][2]=0;
			reflectMatrix[1][0]=1;
			reflectMatrix[1][1]=0;
			reflectMatrix[1][2]=0;
			reflectMatrix[2][0]=0;
			reflectMatrix[2][1]=0;
			reflectMatrix[2][2]=1;
		}
		else if(reflec_yEqualMinusx.isSelected()){
			reflectMatrix[0][0]=0;
			reflectMatrix[0][1]=-1;
			reflectMatrix[0][2]=0;
			reflectMatrix[1][0]=-1;
			reflectMatrix[1][1]=0;
			reflectMatrix[1][2]=0;
			reflectMatrix[2][0]=0;
			reflectMatrix[2][1]=0;
			reflectMatrix[2][2]=1;
		}
		
		
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		for(int i=0;i<x.size();i++){
			
			ArrayList<Integer> ptfinal=new ArrayList<>();
			
			int ptx=x.get(i)-centerx;
			int pty=y.get(i)-centery;
			
			int ptMatrix[][]={{ptx},{pty},{1}};
			
			double sum=0;
			
			for (int k = 0; k < ptMatrix[0].length; k++) {
		        for (int e = 0; e < reflectMatrix.length; e++) {
		            sum = 0;
		            for (int f = 0; f < ptMatrix.length; f++) {
		                sum = sum + reflectMatrix[e][f] * ptMatrix[f][k];
		            }
		            ptfinal.add((int)(sum));
		        }
		    }
			arrTempX.add(ptfinal.get(0)+centerx);
			arrTempY.add(ptfinal.get(1)+centery);
		}
		arrReflectionX.addAll(arrTempX);
		arrReflectionY.addAll(arrTempY);
		
		System.out.println(arrReflectionX);
		System.out.println(arrReflectionY);
	}
	
	//============================================================================ matrixShear Function ===================================================================================
	public static void matrixShear(ArrayList<Integer> x, ArrayList<Integer> y){
		double[][] shearMatrix = {{0, 0, 0},{0, 0, 0},{0 ,0, 0}};
		
		if(shearXaxis1.isSelected()){
			double shearValue=Double.parseDouble((String)(shearValueX1.getSelectedItem()));
			shearMatrix[0][0]=1;
			shearMatrix[0][1]=shearValue;
			shearMatrix[0][2]=0;
			shearMatrix[1][0]=0;
			shearMatrix[1][1]=1;
			shearMatrix[1][2]=0;
			shearMatrix[2][0]=0;
			shearMatrix[2][1]=0;
			shearMatrix[2][2]=1;
		}
		else if(shearYaxis1.isSelected()){
			double shearValue=Double.parseDouble((String)(shearValueY1.getSelectedItem()));
			shearMatrix[0][0]=1;
			shearMatrix[0][1]=0;
			shearMatrix[0][2]=0;
			shearMatrix[1][0]=shearValue;
			shearMatrix[1][1]=1;
			shearMatrix[1][2]=0;
			shearMatrix[2][0]=0;
			shearMatrix[2][1]=0;
			shearMatrix[2][2]=1;
		}
		else if(shearXaxis2.isSelected()){
			double shearValue=Double.parseDouble((String)(shearValueX2.getSelectedItem()));
			int coordinate=Integer.parseInt(shearYcoordinate.getText());
			shearMatrix[0][0]=1;
			shearMatrix[0][1]=shearValue;
			shearMatrix[0][2]=(-shearValue*coordinate);
			shearMatrix[1][0]=0;
			shearMatrix[1][1]=1;
			shearMatrix[1][2]=0;
			shearMatrix[2][0]=0;
			shearMatrix[2][1]=0;
			shearMatrix[2][2]=1;
		}
		else if(shearYaxis1.isSelected()){
			double shearValue=Double.parseDouble((String)(shearValueY2.getSelectedItem()));
			int coordinate=Integer.parseInt(shearXcoordinate.getText());
			shearMatrix[0][0]=1;
			shearMatrix[0][1]=0;
			shearMatrix[0][2]=0;
			shearMatrix[1][0]=shearValue;
			shearMatrix[1][1]=1;
			shearMatrix[1][2]=(-shearValue*coordinate);
			shearMatrix[2][0]=0;
			shearMatrix[2][1]=0;
			shearMatrix[2][2]=1;
		}
		
		arrTempX=new ArrayList<Integer>();
		arrTempY=new ArrayList<Integer>();
		
		for(int i=0;i<x.size();i++){
			
			ArrayList<Integer> ptfinal=new ArrayList<>();
			
			int ptx=x.get(i)-centerx;
			int pty=y.get(i)-centery;
			
			int ptMatrix[][]={{ptx},{pty},{1}};
			
			double sum=0;
			
			for (int k = 0; k < ptMatrix[0].length; k++) {
		        for (int e = 0; e < shearMatrix.length; e++) {
		            sum = 0;
		            for (int f = 0; f < ptMatrix.length; f++) {
		                sum = sum + shearMatrix[e][f] * ptMatrix[f][k];
		            }
		            ptfinal.add((int)(sum));
		        }
		    }
			arrTempX.add(ptfinal.get(0)+centerx);
			arrTempY.add(ptfinal.get(1)+centery);
		}
		arrShearX.addAll(arrTempX);
		arrShearY.addAll(arrTempY);
		
		System.out.println(arrShearX);
		System.out.println(arrShearY);
	}
}
