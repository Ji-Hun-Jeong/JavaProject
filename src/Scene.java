import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Scene extends JFrame
{
    Container m_container = null;
    BackgroundPanel BGpanel = new BackgroundPanel();

    public Scene(String str)
    {
        super(str);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        setResizable(false);
        m_container = getContentPane();   
        m_container.add(BGpanel);
    }
    public void Update()
    {

    }
    public void Render()
    {
        getContentPane().repaint();
    }

    class BackgroundPanel extends JPanel
    {
        protected ImageIcon m_imageIcon = null;
        protected Image m_image = null;
        protected int imageX;
        protected int imageY;

        BackgroundPanel()
        {
            m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Background");
            imageX = m_imageIcon.getIconWidth();
            imageY = m_imageIcon.getIconHeight();
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(m_image, 0, 0, imageX, imageY, null);
        }
    }
}