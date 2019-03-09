package othertesting;

/* Boundary fill algorithm (project for WeGa - on http://www.sccg.sk/~wega/ )
---------------------------------
- AUTHOR:
-         Martin Pullmann - Alpha
-          .: Cmiter Studios :.
-      http://www.cmiter-studios.net
-      "...when design is essential"
---------------------------------
-
-
--- Implemented with: Queue
-
-
--- About:
- algorithm is designed as real boundary fill alg., but for visibility, it is drawing not only single pixels,
- but 10x10px squares. By clicking on the canvas, you specify starting point where algorithm is started...
- That´s why white spaces are creating near borders of other colors...
- 
- thanks a lot for reading ;)
*/

import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Bfill extends Applet
implements MouseListener, MouseMotionListener, Runnable {

	// variables ...
ListItem first = null;
ListItem last = null;

Graphics bgr;
Image backbuf;
int w, h, mx, my, poc, c_old, count = 0;
int[] main_pixels = new int[w*h];
int[] sub_pixels = new int[w*h];
Thread t;
boolean algStarted = false, algFin = false;
	// var end ...

  public class ListItem {
	  public int x, y;
	  public ListItem previous;
	  public ListItem next;
	  
	  public ListItem(int xx, int yy) { //xx, yy - position of rect.
	  	this.x = xx;
	  	this.y = yy;
	  	this.previous = this.next = null;
	  }
  }
  
  public void putQueue(int x, int y) {       // push -----------------!!!
	  
      ListItem newItem = new ListItem(x, y);
      
	  if (isEmpty()) {
	      first = last = newItem;
	  } else {
	      last.next = newItem;
	      newItem.previous = last;
	      last = newItem;
	    }
	    count++;
  }

  public void getQueue() {                  // pop ------------------!!!
	    
	  ListItem firstItem = first;

	  if (isEmpty()) {
	      // empty yet !
	  } else {
		  mx = firstItem.x;
		  my = firstItem.y;
	      this.first = firstItem.next;
	      if (first == null) {
	        last = null;
	      } else {
	        first.previous = null;
	      }
	      count--;
	    }
  }

  public boolean isEmpty() {
      return (count == 0);
  }

// -------------------------------------------------------------------
  public void init() { // ------------------------------- init -------
	resize(300, 300);
	w = 300;
	h = w;
	mx = 150;
	my = mx;
    
    backbuf = createImage(w, h);
    bgr = backbuf.getGraphics();
    bgr.setColor(Color.gray);
    bgr.fillRect(0, 0, w, h);
    bgr.setColor(Color.white);
    bgr.fillRect(20, 20, w-40, h-40);
    bgr.setColor(Color.black);
    bgr.fillRect(mx-10, my-40, 20, 80);
    bgr.fillRect(mx-40, my-10, 80, 20);
    bgr.setColor(Color.red);
    bgr.fillRect(50, 50, 30, 80);
    bgr.setColor(Color.yellow);
    bgr.fillOval(200,180,40,40);
    bgr.setColor(Color.blue);
    
    

    addMouseListener(this);
    addMouseMotionListener(this);

  }
  
//-------------------- mouse events -----------------------------------------start--

  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  
  public void mousePressed(MouseEvent e) {
    algStarted = true;
    mx = e.getX();
    my = e.getY();
    
    int[] pixels = new int[w*h];
    PixelGrabber pg = new PixelGrabber(backbuf, 0, 0, w, h, pixels, 0, w);
    try {
      pg.grabPixels();
    }
    catch (InterruptedException ie)  { }
    c_old = pixels[my * w + mx];
    main_pixels = pixels;
    sub_pixels = pixels;
    e.consume();
  }

  public void mouseReleased(MouseEvent e) {
	if (algStarted) {  // starting position was sent, start algorithm
	    algorithm(mx, my);
	    algStarted = false;
	    algFin = true;
	 }
  }
  
  public void mouseMoved(MouseEvent e) { }
  public void mouseDragged(MouseEvent e) { }
//-------------------- mouse events ------------------------------------------end--    


// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------
// _____________________________________________________algorithm__________________
  public void algorithm(int x, int y) {
	  
	  if (check_c(x, y)) {
		  main_pixels[(y * w) + x] = 0x000000ff;
		  putQueue(x, y);
	  }
	  if (check_c(x, y-11)) {
		  main_pixels[((y-11) * w) + x] = 0x000000ff;
		  putQueue(x, y-11);
	  }
      if (check_c(x+11, y)) {
    	  main_pixels[(y * w) + x+11] = 0x000000ff;
		  putQueue(x+11, y); 
      }
      if (check_c(x, y+11)) {
    	  main_pixels[((y+11) * w) + x] = 0x000000ff;
		  putQueue(x, y+11); 
      }
      if (check_c(x-11, y)) {
    	  main_pixels[(y * w) + x-11] = 0x000000ff;
		  putQueue(x-11, y); 
      }
  }
  
  public boolean check_c (int x, int y) { // can I draw a square on (x,y) ?
	  boolean ok = true;
	  // check corners...
      for (int i = 1; i<2; i++) {  // looping here is used only because of -breaking- (saving the time)
    	  if (((y) < h) && ((x) < w)) {
		      if (c_old != main_pixels[(y * w) + x]) {
		    	  ok = false;
		    	  break;  // if corner is in area with other color than we are filling up, skip other checking, send false
		      }
		  }
    	  if (((y-6) >= 0) && ((x-6) >= 0)) {
		      if (c_old != main_pixels[((y-5) * w) + x-5]) {
		    	  ok = false;
		          break;
		      }
		  }
		  if (((y+6) < h) && ((x+6) < w)) {
		      if (c_old != main_pixels[((y+5) * w) + x+5]) {
		    	  ok = false;
		    	  break;
		      }
		  }
		  if (((y+6) < h) && ((x-6) < w)) {
		      if (c_old != main_pixels[((y+5) * w) + x-5]) {
		    	  ok = false;
		    	  break;
		      }
		  }
		  if (((y-6) < h) && ((x+6) < w)) {
		      if (c_old != main_pixels[((y-5) * w) + x+5])
		    	  ok = false;
		  }
      }
	  if (ok) {
		  main_pixels[((y-5) * w) + x-5] = 0x000000ff;
		  return true;     // (right color in all corners)
	  }
	  else return false;
  }
  
// __________________________________________________________algorithm end_________
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------

// ============================== start ===============================
  public void start() {
       t = new Thread(this);
       t.setPriority(Thread.MAX_PRIORITY);
       t.start();
  }

  public void run() {
      try {
          while (true) {
        	
        	if (algFin) {
        		putQueue(-1,-1); // ends one wave
        		getQueue();
              while (mx != -1) {
            	    bgr.fillRect(mx-5, my-5, 10, 10);
        		    algorithm(mx, my);
        		    getQueue();
              }
            if (isEmpty())
            	algFin = false;
        	}
           repaint();
           t.sleep( 300 );
         }
      }
      catch (InterruptedException e) { }
  }

  public void paint(Graphics g) {		  
      g.drawImage(backbuf, 0, 0, this);
  }
}