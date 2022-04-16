import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.*;

public class NorthToolBar extends JToolBar {

    Dimension dimension = new Dimension(40, 40);
    JButton red;
    JButton green;
    JButton blue;
    JButton gray;
    JButton black;
    JButton darkGray;
    JButton yellow;
    JButton orange;
    JButton pink;

    
    // JToolBar toolBar;

    public NorthToolBar(PaintFrame pf){
        setFloatable(false);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(1300, 60));
        setBackground(Color.lightGray);


        // circle.setPreferredSize(new Dimension(120, 40));
        red = new JButton("");
        green = new JButton("");
        blue = new JButton("");
        gray = new JButton("");
        black = new JButton("");
        darkGray = new JButton();
        yellow = new JButton();
        orange = new JButton();
        pink = new JButton();

        JButton[] colorButton = new JButton[] {red, pink, orange, yellow, green, blue, gray, black, darkGray};
        Color[] color = new Color[] {Color.RED, Color.pink, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.GRAY, Color.BLACK, Color.DARK_GRAY};

        //Initiate other button

        //Initiate color button
        int i  = 0;
        for(JButton object : colorButton){
            // object.setPreferredSize(new Dimension(60, 60));
            object.setBackground(color[i]);
            object.setOpaque(true);
            object.setPreferredSize(dimension);
            i++;
            this.add(object);
        }

        ActionListener listener = new ActionListener() {

            public void actionPerformed(ActionEvent e){
                if(e.getSource() == red){
                    pf.getPaintPanel().red();
                }
                else if(e.getSource() == orange){
                    pf.getPaintPanel().orange();
                }
                else if(e.getSource() == green){
                    pf.getPaintPanel().green();
                }
                else if(e.getSource() == blue){
                    pf.getPaintPanel().blue();
                }
                else if(e.getSource() == gray){
                    pf.getPaintPanel().gray();
                }
                else if(e.getSource() == black){
                    pf.getPaintPanel().black();
                }
                else if(e.getSource() == darkGray){
                    pf.getPaintPanel().darkGray();
                }
                else if(e.getSource() == yellow){
                    pf.getPaintPanel().yellow();
                }
                else if(e.getSource() == pink){
                    pf.getPaintPanel().pink();
                }
            }
        };
        red.addActionListener(listener);
        green.addActionListener(listener);
        blue.addActionListener(listener);
        gray.addActionListener(listener);
        black.addActionListener(listener);
        darkGray.addActionListener(listener);
        yellow.addActionListener(listener);
        orange.addActionListener(listener);
        pink.addActionListener(listener);
    }
}