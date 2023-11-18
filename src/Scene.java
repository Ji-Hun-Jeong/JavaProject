
import java.awt.*;
import javax.swing.*;
public class Scene extends JFrame
{
    boolean m_bChange=false;
    JButton b=null;
    public Scene()
    {
        setTitle("몰라");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(new FlowLayout());
        b = new JButton("Action");
        b.setSize(100,200);
        b.addActionListener(new MyMouseListener());
        add(b);

        setVisible(true);
    }
    public void Update()
    {      
        if(!m_bChange)
        {
            b.setText("액션");
        }
        else
        {
            b.setText("Action");
        }
        m_bChange=!m_bChange;
    }
    public void Render()
    {
        getContentPane().repaint();
    }
}