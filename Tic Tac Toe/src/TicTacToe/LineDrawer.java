package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class LineDrawer extends JComponent {
    
	private static final long serialVersionUID = 1L;
	private int x1=0;
	private int x2=0;
	private int y1=0;
	private int y2=0;
	private Color c=Color.RED;
	private boolean draw=true;
	@Override
    
    public void paint(Graphics g) {
        // Draw a simple line using the Graphics2D draw() method.
        Graphics2D g2 = (Graphics2D) g;
	       if(draw==true) {
	    	   g2.setStroke(new BasicStroke(5));
	           g2.setColor(c);
	           g2.draw(new Line2D.Double(x1,y1,x2,y2));
	       }
       }
	
	
	public boolean isDraw() {
		return draw;
	}


	public void setDraw(boolean draw) {
		this.draw = draw;
	}


	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}

   
}
