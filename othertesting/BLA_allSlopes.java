package othertesting;

import java.awt.Point;
import java.util.ArrayList;

public class BLA_allSlopes {

	public static ArrayList<Integer> arrPtsX=new ArrayList<Integer>();
	public static ArrayList<Integer> arrPtsY=new ArrayList<Integer>();
	public static ArrayList<Integer> arrTempX=new ArrayList<Integer>();
	public static ArrayList<Integer> arrTempY=new ArrayList<Integer>();
	
	public static void main(String[] args){
		bresenhamLine(new Point(50,50),new Point(25,25));
		System.out.println(arrPtsX);
		System.out.println(arrPtsY);
	}
	
	public static void bresenhamLine(Point start, Point end) {
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
}
