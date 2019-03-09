package assignmentGraphic2;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;

public class drawGrid extends JPanel implements ActionListener{

	Timer tm=new Timer(150,this);
	int pos=0;
	
	JLabel txtInfo;
	
	public drawGrid() {
		txtInfo=new JLabel("");
		txtInfo.setForeground(Color.YELLOW);
		txtInfo.setFont(new Font(txtInfo.getName(), Font.PLAIN, 17));
		add(txtInfo,BorderLayout.NORTH);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int centerx=(shape.screenX-((int)(280*1.06)))/2;
		int centery=(shape.screenY-80)/2;
		g.setColor(Color.red);
		g.drawLine(centerx, 0, centerx, centery*2);
		g.drawLine(0, centery, centerx*2, centery);
		
		
		if(shape.flagOriginalShape){
			ArrayList<Point> pts=shape.pts;
			
			g.setColor(Color.white);
			g.drawLine((int)(pts.get(pts.size()-1).getX()), (int)(pts.get(pts.size()-1).getY()), (int)(pts.get(0).getX()), (int)(pts.get(0).getY()));
			for(int i=0;i<pts.size()-1;i++){
				g.drawLine((int)(pts.get(i).getX()), (int)(pts.get(i).getY()), (int)(pts.get(i+1).getX()), (int)(pts.get(i+1).getY()));
			}
		}
		
		
		/*if(shape.flagTranslateShape){
			ArrayList<Integer> arrX=shape.arrTranslateX;
			ArrayList<Integer> arrY=shape.arrTranslateY;

			g.setColor(Color.yellow);
			
			for(int i=0;i<arrX.size();i++){
				g.drawLine(arrX.get(i), arrY.get(i), arrX.get(i), arrY.get(i));
			}	
		}
		
		
		if(shape.flagRotateShape){
			for(int j=0; j<shape.arrRotateX2.size();j++){
				
				if(j==0) g.setColor(Color.green);
				if(j==1) g.setColor(Color.blue);
				if(j==2) g.setColor(Color.magenta);
				
				ArrayList<Integer> arrX=shape.arrRotateX2.get(j);
				ArrayList<Integer> arrY=shape.arrRotateY2.get(j);
				
				for(int i=0;i<arrX.size();i++){
					g.drawLine(arrX.get(i), arrY.get(i), arrX.get(i), arrY.get(i));
				}
			}
		}
		
		
		if(shape.flagScaleShape){
			for(int j=0; j<shape.arrScaleX2.size();j++){
				
				if(j==0) g.setColor(Color.green);
				if(j==1) g.setColor(Color.blue);
				if(j==2) g.setColor(Color.magenta);
				
				ArrayList<Integer> arrX=shape.arrScaleX2.get(j);
				ArrayList<Integer> arrY=shape.arrScaleY2.get(j);
				
				for(int i=0;i<arrX.size();i++){
					g.drawLine(arrX.get(i), arrY.get(i), arrX.get(i), arrY.get(i));
				}
			}
		}*/
		
		if(shape.flagDrawShape){
			
			if (pos>=0 && pos<shape.translatePOS){
				g.setColor(Color.cyan);
				txtInfo.setText("Translation");
			}
			
			if (pos>=shape.translatePOS && pos<shape.rotatePOS){
				g.setColor(Color.yellow);
				txtInfo.setText("Rotation");
			}
			
			if (pos>=shape.rotatePOS && pos<shape.scalePOS){
				g.setColor(Color.pink);
				txtInfo.setText("Scaling");
			}
			
			if (pos>=shape.scalePOS && pos<shape.reflectPOS){
				g.setColor(Color.green);
				txtInfo.setText("Reflection");
			}
			
			if (pos>=shape.reflectPOS && pos<shape.shearPOS){
				g.setColor(Color.magenta);
				txtInfo.setText("Shear");
			}
			
			
			for(int j=0;j<shape.TwoD_arrPtsX.get(pos).size();j++){
				ArrayList<Integer> arrX=shape.TwoD_arrPtsX.get(pos);
				ArrayList<Integer> arrY=shape.TwoD_arrPtsY.get(pos);
				
				for(int i=0;i<arrX.size();i++){
					g.drawLine(arrX.get(i), arrY.get(i), arrX.get(i), arrY.get(i));
				}
			}
			tm.start();
		}
		
		if(shape.flagBoundaryFill){
			Color limegreen=new Color(66, 240, 74);
			g.setColor(limegreen);
			
			ArrayList<Integer> arrX=shape.arrBoundaryFillX;
			ArrayList<Integer> arrY=shape.arrBoundaryFillY;
			
			for(int i=0;i<shape.arrBoundaryFillX.size();i++){
				int x=arrX.get(i);
				int y=arrY.get(i);
				
				g.drawLine(x, y, x, y);
			}
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(pos<shape.TwoD_arrPtsX.size()-1){
			pos=pos+1;
			repaint();
		}
		else{
			pos=0;
			repaint();
		}
	}
}
