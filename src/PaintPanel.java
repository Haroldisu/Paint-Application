import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import org.w3c.dom.events.MouseEvent;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class PaintPanel extends JPanel {
    
    Point start, end;
    int shape;
    int bold = 10;
    Color color;
    Graphics2D g2d;
    PaintPanel pp;
    ArrayList<Shape> shape_list = new ArrayList<Shape>();
    ArrayList<Shape> empty = new ArrayList<Shape>();
    ArrayList<Integer> ax = new ArrayList<Integer>();
    ArrayList<Integer> ay = new ArrayList<Integer>();
    ArrayList<Color> colorArr = new ArrayList<Color>();
    ArrayList<Integer> thicknessArr = new ArrayList<Integer>();
    int arrayBreakPoint, arrayStartPoint = 0;

    Boolean clear = false;
    static StrokeState ss = StrokeState.PENCIL;

    static enum StrokeState {

        PENCIL,
        RECTANGLE,
        CIRCLE,
        ERASER

    }

    public PaintPanel(PaintFrame frame){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(900, 800));
        addMouseListener(pencilHandleAdapter);
        addMouseMotionListener(pencilHandleAdapter);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // g2 = (Graphics2D)g;
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(clear == true){
            g2d.drawRect(0, 0, 1200, 700);
            clear = false;
        }
        else if(ss == StrokeState.PENCIL) {
            if(shape_list != empty){
                for(Shape shape : shape_list)
                {
                    int rect_width = Math.abs(shape.getEndPoint().x - shape.getStartPoint().x), 
                        rect_height = Math.abs(shape.getEndPoint().y - shape.getStartPoint().y);
                    int rect_x = Math.min(shape.getStartPoint().x, shape.getEndPoint().x), 
                        rect_y = Math.min(shape.getStartPoint().y, shape.getEndPoint().y);  
                    if(shape.getShape() == ss.RECTANGLE){
                        g2d.setStroke(new BasicStroke(shape.getBold()));
                        g2d.setColor(shape.getColor());
                        g2d.drawRect(rect_x, rect_y, rect_width, rect_height);
                    }
                    else if(shape.getShape() == ss.CIRCLE){
                        g2d.setStroke(new BasicStroke(shape.getBold()));
                        g2d.setColor(shape.getColor());
                        g2d.drawOval(rect_x, rect_y, rect_width, rect_height);
                    }
                    else if (shape.getShape() == ss.PENCIL){
                        for(int i = 0; i < arrayBreakPoint - 1; i++) {
                            g2d.setStroke(new BasicStroke(5));
                            // g2d.setColor(color);
                            g2d.drawLine(ax.get(i), ay.get(i), ax.get(i+1), ay.get(i+1));       
                        }
                    }
                }
            }

            for(int i = 0 ; i < ax.size() - 1; i++) {
                g2d.setStroke(new BasicStroke(5));
                g2d.setColor(color);
                g2d.drawLine(ax.get(i), ay.get(i), ax.get(i+1), ay.get(i+1));                        
            }
                
        }
        else if(ss == StrokeState.RECTANGLE && start != null && end != null) {
            //Using abs() is cuz when the shape is drawn from right toward left, then the width and height can be negative.  
            int width = Math.abs(end.x - start.x), height = Math.abs(end.y - start.y);
            //It's essential to use min() funciton, cuz drawRect()'s parameters only allow the coordinate from left of Shape.
            int x = Math.min(start.x, end.x), y = Math.min(start.y, end.y);  
            System.out.println("Start: [" + start.x + ", " + start.y +"]");
            System.out.println("End: [" + end.x + ", " + end.y +"]");

            if(shape_list != empty){
                for(Shape shape : shape_list)
                    {
                        int rect_width = Math.abs(shape.getEndPoint().x - shape.getStartPoint().x), 
                            rect_height = Math.abs(shape.getEndPoint().y - shape.getStartPoint().y);
                        int rect_x = Math.min(shape.getStartPoint().x, shape.getEndPoint().x), 
                            rect_y = Math.min(shape.getStartPoint().y, shape.getEndPoint().y);  
                        if(shape.getShape() == ss.RECTANGLE){
                            g2d.setStroke(new BasicStroke(shape.getBold()));
                            g2d.setColor(shape.getColor());
                            g2d.drawRect(rect_x, rect_y, rect_width, rect_height);
                        }
                        else if(shape.getShape() == ss.CIRCLE){
                            g2d.setStroke(new BasicStroke(shape.getBold()));
                            g2d.setColor(shape.getColor());
                            g2d.drawOval(rect_x, rect_y, rect_width, rect_height);
                        }
                        else{
                            for(int i = 0; i < arrayBreakPoint - 1; i++) {
                                g2d.setStroke(new BasicStroke(5));
                                // g2d.setColor(colorArr.get(i));
                                g2d.drawLine(ax.get(i), ay.get(i), ax.get(i+1), ay.get(i+1));       
                            }
                        }
                    }
             }

            g2d.setStroke(new BasicStroke(bold));
            g2d.setColor(color);
            g2d.drawRect(x, y, width, height);
        }

        else if(ss == StrokeState.CIRCLE && start != null && end != null) {
            //Using abs() is cuz when the shape is drawn from right toward left, then the width and height can be negative.  
            int width = Math.abs(end.x - start.x), height = Math.abs(end.y - start.y);
            //It's essential to use min() funciton, cuz drawRect()'s parameters only allow the coordinate from left of Shape.
            int x = Math.min(start.x, end.x), y = Math.min(start.y, end.y);  
            System.out.println("Start: [" + start.x + ", " + start.y +"]");
            System.out.println("End: [" + end.x + ", " + end.y +"]");

            for(Shape shape : shape_list)
            {
                int oval_width = Math.abs(shape.getEndPoint().x - shape.getStartPoint().x), 
                    oval_height = Math.abs(shape.getEndPoint().y - shape.getStartPoint().y);
                int oval_x = Math.min(shape.getStartPoint().x, shape.getEndPoint().x), 
                    oval_y = Math.min(shape.getStartPoint().y, shape.getEndPoint().y);  
                if(shape.getShape() == ss.RECTANGLE){
                    g2d.setStroke(new BasicStroke(shape.getBold()));
                    g2d.setColor(shape.getColor());
                    g2d.drawRect(oval_x, oval_y, oval_width, oval_height);
                }
                else if(shape.getShape() == ss.CIRCLE){
                    g2d.setStroke(new BasicStroke(shape.getBold()));
                    g2d.setColor(shape.getColor());
                    g2d.drawOval(oval_x, oval_y, oval_width, oval_height);
                }
                else{
                    for(int i = 0; i < arrayBreakPoint - 1; i++) {
                        g2d.setStroke(new BasicStroke(5));
                        // g2d.setColor(colorArr.get(i));
                        g2d.drawLine(ax.get(i), ay.get(i), ax.get(i+1), ay.get(i+1));       
                    }
                }
            }

            g2d.setStroke(new BasicStroke(bold));
            g2d.setColor(color);
            g2d.drawOval(x, y, width, height);
        }

    }

    public void strokeStateChange(StrokeState ss){
        switch(ss) {
            case PENCIL :
                break;
            case RECTANGLE :
                break;
            case CIRCLE :
                break;
        }
    }

    MouseInputAdapter pencilHandleAdapter = new MouseInputAdapter() {

        @Override
        public void mousePressed(java.awt.event.MouseEvent e){
            super.mousePressed(e);
            if(ss == StrokeState.PENCIL){
                // ax.clear();
                // ay.clear();
                ax.add(e.getX());
                ay.add(e.getY());
                arrayStartPoint = ax.size();
                colorArr.add(color);
                thicknessArr.add(bold);
            }
            System.out.println("Pressed");
            start = e.getPoint();
            // repaint();
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            super.mouseReleased(e);
            if(ss == StrokeState.PENCIL){
                // ax.add(-1);
                // ay.add(-1);
                ax.add(e.getX());
                ay.add(e.getY());
                arrayBreakPoint = ax.size();
                shape_list.add(new Shape(start, end, color, ss, bold, ax, ay));
                System.out.println("Circle added");
                // System.out.println(ax);
                // System.out.println(ay);
            }
            else{
                shape_list.add(new Shape(start, end, color, ss, bold));
            }
            System.out.println("Released");
            end = e.getPoint();
            // repaint();
        }

        @Override
        public void mouseDragged(java.awt.event.MouseEvent e){
            super.mouseDragged(e);
            System.out.println("Dragging");
            if(ss == StrokeState.PENCIL){
                ax.add(e.getX());
                ay.add(e.getY());
                colorArr.add(color);
                thicknessArr.add(bold);
            }
            end = e.getPoint();
            // g2.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
            repaint();
        }   
    };

    public void setStatePENCIL(){
        ss = StrokeState.PENCIL;
    }

    public void setStateRECTANGLE(){
        ss = StrokeState.RECTANGLE;
    }

    public void setStateCIRCLE(){
        ss = StrokeState.CIRCLE;
    }

    public void clear(){
        shape_list.clear();
        ax.clear();
        ay.clear();
        thicknessArr.clear();
        colorArr.clear();
        start = null;
        end = null;
        clear = true;
        repaint();
    }
    public void setThickness(int t){
        bold = t;
        g2d.setStroke(new BasicStroke(t));
    }
    public void red(){
        color = Color.RED;
        g2d.setColor(color);
    }
    public void green(){
        color = Color.GREEN;
        g2d.setColor(color);
    }
    public void blue(){
        color = Color.BLUE;
        g2d.setColor(color);
    }
    public void gray(){
        color = Color.GRAY;
        g2d.setColor(color);
    }
    public void black(){
        color = Color.BLACK;
        g2d.setColor(color);
    }
    public void darkGray(){
        color = Color.DARK_GRAY;
        g2d.setColor(color);
    }
    public void yellow(){
        color = Color.YELLOW;
        g2d.setColor(color);
    }
    public void orange(){
        color = Color.ORANGE;
        g2d.setColor(color);
    }
    public void pink(){
        color = Color.PINK;
        g2d.setColor(color);
    }
    public void setPencil(){
        ss = StrokeState.PENCIL;
    }
    public void setRectangle(){
        ss = StrokeState.RECTANGLE;
    }
    public void setCircle(){
        ss = StrokeState.CIRCLE;
    }
    public void setEraser(){
        ss = StrokeState.ERASER;
    }
}
