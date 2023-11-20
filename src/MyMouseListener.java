import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
class Vec2
{
    public int x;
    public int y;
}
public class MyMouseListener implements MouseListener, MouseMotionListener
{
    private Vec2 m_vFirstPos = new Vec2();
    private boolean m_bClicked = false;
    public void mousePressed(MouseEvent e)
    {
        if(!m_bClicked)
        {
            m_vFirstPos.x = e.getX();
            m_vFirstPos.y = e.getY();
            m_bClicked=true;
        }
    }
    public void mouseReleased(MouseEvent e)
    {
        if(m_bClicked)
        {
            m_bClicked=false;
        }
    }
    public void mouseEntered(MouseEvent e)
    {
        
    }
    public void mouseExited(MouseEvent e)
    {
        
    }
    public void mouseClicked(MouseEvent e)
    {
        
    }
    public void mouseDragged(MouseEvent e)
    {
        Vec2 curPos = new Vec2();
        curPos.x = e.getX();
        curPos.y = e.getY();
        MainPanel mPanel = (MainPanel)((Working_Scene)SceneMgr.GetInst().GetCurScene()).GetPanels(PANEL_TYPE.MAIN);
        mPanel.SetMousePos(m_vFirstPos, curPos);
        //mPanel.setVisible(true);
    }
    public void mouseMoved(MouseEvent e)
    {
        
    }
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        
    }
}