import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
class Vec2
{
    public int x;
    public int y;
    public Vec2(){x=0;y=0;}
}
public class MyMouseListener implements MouseListener, MouseMotionListener
{
    private static MyMouseListener m_inst = new MyMouseListener();
    private MyMouseListener(){}
    public static MyMouseListener GetInst(){return m_inst;}

    private Vec2 m_vFirstPos = new Vec2();
    private Vec2 m_vCurPos = new Vec2();
    private MainPanel m_mainPanel = null;
    private boolean m_bClicked = false;
    public boolean GetCheckClicked(){return m_bClicked;}
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
        m_bClicked=true;
    }
    public void mouseReleased(MouseEvent e)
    {
        BUTTON_TYPE btnType = MyBtnListener.GetInst().GetBtnType();
        if(btnType.equals(BUTTON_TYPE.END))
            return;

        MainPanel mPanel = ((MainPanel)((Working_Scene)SceneMgr.GetInst().GetCurScene()).GetPanels(PANEL_TYPE.MAIN));
        Gate gate=null;
        m_vCurPos.x=e.getX();
        m_vCurPos.y=e.getY();
        switch(btnType)
        {
            case BUFFER:
                break;
            case INVERTER:
                break;
            case AND:
                gate = new ANDGate();
                gate.SetPos(m_vCurPos.x,m_vCurPos.y);
                mPanel.PushGate(gate);   
                break;
            case OR:
                gate = new ORGate();
                gate.SetPos(m_vCurPos.x,m_vCurPos.y);
                mPanel.PushGate(gate);   
                break;
            case NAND:
                break;
            case XOR:
                break;
            case NOR:
                break;
            case XNOR:
                break;
            case LINE:
                Line line = new Line();
                line.leftX=m_vFirstPos.x;
                line.leftY=m_vFirstPos.y;
                line.rightX=m_vCurPos.x;
                line.rightY=m_vCurPos.y;
                mPanel.PushLine(line);
                break;
            case TABLE:
                break;
            default:
                break;
        }
        m_bClicked=false;
    }
    public void mouseEntered(MouseEvent e)
    {
        
    }
    public void mouseExited(MouseEvent e)
    {
        
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseDragged(MouseEvent e)
    {
        m_vCurPos.x = e.getX();
        m_vCurPos.y = e.getY();
    }
    public void mouseMoved(MouseEvent e)
    {
        
    }
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        
    }
}