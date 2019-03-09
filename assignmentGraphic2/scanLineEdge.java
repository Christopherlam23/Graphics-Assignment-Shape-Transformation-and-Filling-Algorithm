package assignmentGraphic2;

import java.awt.Point;

public class scanLineEdge {

	private Point p1;
	private Point p2;
	private double maxY;
	private double currentX;
	private double xIncr;
	
	public scanLineEdge(Point a, Point b){
		if(a.getY()>b.getY()){
			this.p1=b;
			this.p2=a;
			this.maxY=a.getY();
		}
		else{
			this.p1=a;
			this.p2=b;
			this.maxY=b.getY();
		}
		
		this.currentX=p1.getX();
		
		this.xIncr=(p2.getX()-p1.getX())/(p2.getY()-p1.getY());
	}
	
	public void updateCurrentX(){
		this.currentX=this.currentX+this.xIncr;
	}
	
	public double getCurrentX(){
		return this.currentX;
	}
	
	public Point getP1(){
		return this.p1;
	}
	
	public Point getP2(){
		return this.p2;
	}
}
