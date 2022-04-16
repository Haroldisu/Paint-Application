import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class PaintFrame extends JFrame{

    private JPanel northToolPanel;
    private NorthToolBar northToolBar;
    private JPanel westToolPanel;
    private WestToolBar westToolBar;
    private PaintPanel paintPanel;

    public PaintFrame(String title){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1300, 800));
        
        //northToolPanel, northToolBar
        northToolPanel = new JPanel();
        northToolPanel.setPreferredSize(new Dimension(1300, 60));
        northToolPanel.setMaximumSize(northToolPanel.getPreferredSize());
        northToolPanel.setOpaque(true);
        northToolBar = new NorthToolBar(this);
        northToolPanel.add(northToolBar);

        //westToolPanel, westToolBar
        westToolPanel = new JPanel();
        westToolPanel.setPreferredSize(new Dimension(100, 800));
        westToolPanel.setMaximumSize(westToolPanel.getPreferredSize());
        westToolPanel.setOpaque(true);
        westToolBar = new WestToolBar(this);
        westToolPanel.add(westToolBar);
        // westToolPanel.setBackground(Color.RED);
        

        //PaintPanel
        paintPanel = new PaintPanel(this);
        paintPanel.setMaximumSize(paintPanel.getPreferredSize());
        paintPanel.setBackground(Color.WHITE);

        //add all panel
        add(northToolPanel, BorderLayout.NORTH);
        add(westToolPanel, BorderLayout.WEST);
        add(paintPanel, BorderLayout.CENTER);

        pack();
        // setSize(1300, 900);
        setVisible(true);
    }

    public PaintPanel getPaintPanel(){
        return paintPanel;
    }
    
}