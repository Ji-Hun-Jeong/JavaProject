import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Scene extends JFrame
{
    Container m_container = null;
    public Scene(String str)
    {
        super(str);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        m_container = getContentPane();   
    }
    public void Update()
    {

    }
    public void Render()
    {
        getContentPane().repaint();
    }
}

