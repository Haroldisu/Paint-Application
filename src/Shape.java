import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Shape {
    
    private Point start, end;
    private Color color;    
    private int bold;
    private PaintPanel.StrokeState shape;
    private ArrayList<Integer> ax = new ArrayList<Integer>();
    private ArrayList<Integer> ay = new ArrayList<Integer>();

    public Shape(Point s, Point e, Color c, PaintPanel.StrokeState ss, int bold){
        this.start = s;
        this.end = e;
        this.color = c;
        this.shape = ss;
        this.bold = bold;
    }
    public Shape(Point s, Point e, Color c, PaintPanel.StrokeState ss, int bold, 
     ArrayList<Integer> x,  ArrayList<Integer> y){
        this.start = s;
        this.end = e;
        this.color = c;
        this.shape = ss;
        this.bold = bold;
        this.ax = x;
        this.ay = ay;
    }

    public ArrayList<Integer> getAx(){
        return this.ax;
    }
    public ArrayList<Integer> getAy(){
        return this.ay;
    }
    public Point getStartPoint(){
        return this.start;
    }

    public Point getEndPoint(){
        return this.end;
    }

    public Color getColor(){
        return color;
    }

    public int getBold(){
        return bold;
    }

    public PaintPanel.StrokeState getShape(){
        return shape;
    }

    public void setStartPoint(Point s){
        this.start = s;
    }

    public void setEndPoint(Point e){
        this.end = e;
    }

    public String toString(){
        return "Start : [" + start.x + ", " + start.y + "]\n" + "End : [" + end.x + ", " + end.y + "]";
    }
}
