import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
class Vec2
{
    public int x;
    public int y;
    public Vec2(){x=0;y=0;}
    public Vec2(int x,int y){this.x=x;this.y=y;}
    public Vec2 Copy()
    {
        return new Vec2(x,y);
    }
}
enum MOUSE_TYPE
{
    PRESSED, RELEASED , DRAGGED, END,
}
public class MyMouseListener implements MouseListener, MouseMotionListener
{
    private static MyMouseListener m_inst = new MyMouseListener();
    private MyMouseListener(){}
    public static MyMouseListener GetInst(){return m_inst;}

    private Vec2 m_vFirstPos = new Vec2();
    private Vec2 m_vCurPos = new Vec2();
    private MainPanel m_mainPanel = null;
    private MOUSE_TYPE m_eMouseTYPE = MOUSE_TYPE.END;
    public MOUSE_TYPE GetCheckMouseType(){return m_eMouseTYPE;}
    public void InitMouse(){m_eMouseTYPE = MOUSE_TYPE.END;}
    public void SetMainPanel(JPanel inPanel)
    {
        m_mainPanel = (MainPanel)inPanel;
        m_mainPanel.SetMousePos(m_vFirstPos,m_vCurPos);
    }
    public void mousePressed(MouseEvent e)
    {
        m_vFirstPos.x=e.getX();
        m_vFirstPos.y=e.getY();
        m_vCurPos.x=e.getX();
        m_vCurPos.y=e.getY();
        m_eMouseTYPE=MOUSE_TYPE.PRESSED;
    }
    public void mouseReleased(MouseEvent e)
    {
        m_eMouseTYPE=MOUSE_TYPE.RELEASED;
        m_vCurPos.x=e.getX();
        m_vCurPos.y=e.getY();
    }
    public void mouseDragged(MouseEvent e)
    {
        m_vCurPos.x = e.getX();
        m_vCurPos.y = e.getY();
        m_eMouseTYPE = MOUSE_TYPE.DRAGGED;
    }
    public void mouseEntered(MouseEvent e)
    {    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    {        }
    public void mouseMoved(MouseEvent e)
    {
        
    }
    public void mouseWheelMoved(MouseWheelEvent e)
    {
    }
}