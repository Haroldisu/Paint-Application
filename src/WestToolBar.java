import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.awt.*;

public class WestToolBar extends JToolBar {

    static final int BOLD_MIN = 0;
    static final int BOLD_MAX = 30;
    static final int BOLD_INIT = 5;    
    JSlider boldSlider;
    JLabel boldSliderLable;
    JSlider opaqueSlider;
    JLabel opaqueSliderLable;
    JButton redoButton;
    JButton undoButton;
    JButton clearButton;
    JButton eraseButton;
    JButton rectangleButton;
    JButton circleButton;
    JButton pencilButton;
    PaintPanel pp;

    public WestToolBar(PaintFrame pf){
        setFloatable(false);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(100, 800));
        setBackground(Color.lightGray);

        redoButton = new JButton("Redo", new ImageIcon(getClass().getResource("/icons/Redo-24.png")));
        undoButton = new JButton("Undo", new ImageIcon(getClass().getResource("/icons/Undo-24.png")));
        eraseButton = new JButton("erase", new ImageIcon(getClass().getResource("/icons/Eraser-24.png")));
        pencilButton = new JButton("Pencil   " , new ImageIcon(this.getClass().getResource("/icons/Create New-24.png")));
        rectangleButton = new JButton("Rectange", new ImageIcon(getClass().getResource("/icons/Rectangle-24.png")));
        circleButton = new JButton("Circle", new ImageIcon(getClass().getResource("/icons/Circled.png")));
        clearButton = new JButton("Clear", new ImageIcon(getClass().getResource("/icons/Trash-24.png")));

        redoButton.setPreferredSize( new Dimension(100, 45));
        undoButton.setPreferredSize( new Dimension(100, 45));
        eraseButton.setPreferredSize( new Dimension(100, 45));
        pencilButton.setPreferredSize( new Dimension(100, 45));
        rectangleButton.setPreferredSize( new Dimension(100, 45));
        circleButton.setPreferredSize( new Dimension(100, 45));
        clearButton.setPreferredSize( new Dimension(100, 45));

        boldSlider = new JSlider(JSlider.HORIZONTAL, BOLD_MIN, BOLD_MAX, BOLD_INIT);
        boldSliderLable = new JLabel("BOLD");
        opaqueSlider = new JSlider(JSlider.HORIZONTAL, BOLD_MIN, BOLD_MAX, BOLD_INIT);
        opaqueSliderLable = new JLabel("OPAQUE");

        boldSlider.setPreferredSize(new Dimension(100, 35));
        boldSlider.setPaintTrack(true);
        boldSlider.setPaintTicks(true);
        boldSlider.setPaintLabels(true);

        opaqueSlider.setPreferredSize(new Dimension(100, 35));
        opaqueSlider.setPaintTrack(true);
        opaqueSlider.setPaintTicks(true);
        opaqueSlider.setPaintLabels(true);
        

        add(boldSliderLable);
        add(boldSlider);
        // add(opaqueSliderLable);
        // add(opaqueSlider);
        addSeparator(new Dimension(40, 40));
        add(pencilButton);
        add(rectangleButton);
        add(circleButton);
        addSeparator(new Dimension(40, 40));
        add(undoButton);
        add(redoButton);
        add(eraseButton);
        add(clearButton);

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == clearButton){
                    System.out.println("Clear");
                    pf.getPaintPanel().clear();
                }
                else if (e.getSource() == pencilButton){
                    pf.getPaintPanel().setStatePENCIL();
                }
                else if (e.getSource() == rectangleButton){
                    pf.getPaintPanel().setStateRECTANGLE();
                }
                else if (e.getSource() == circleButton){
                    pf.getPaintPanel().setStateCIRCLE();
                }
            }
        };

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                if (boldSlider.getValueIsAdjusting()) {
                    pf.getPaintPanel().setThickness(boldSlider.getValue());
                }
            }
        };

        pencilButton.addActionListener(listener);
        rectangleButton.addActionListener(listener);
        circleButton.addActionListener(listener);
        clearButton.addActionListener(listener);
        boldSlider.addChangeListener(changeListener);
    }

}
