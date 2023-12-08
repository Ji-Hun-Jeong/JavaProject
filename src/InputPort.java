import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class InputPort extends Port
{
    private Vector<Gate> m_vecStartGates = new Vector<Gate>();
    public InputPort(int x,int y)
    {
        super(x,y);
        m_bSetInput = true;
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Red");
        m_image = m_imageIcon.getImage();
        m_iWidth = m_imageIcon.getIconWidth();
        m_iHeight = m_imageIcon.getIconHeight();
    }

    public void ChangeNum()
    {
        if(0==m_iNumOfInput)
            m_iNumOfInput = 1;
        else
            m_iNumOfInput = 0;
    }

    public boolean SetStartGate(Gate startGate,Port onMousePort) 
    {
        if(!startGate.IsFullInputPort())
        {
            startGate.LinkInputPort(onMousePort);
            m_vecStartGates.add(startGate);
            return true;
        }
        return false;
    }

    public Vector<Gate> GetStartGateVec() 
    {
        return m_vecStartGates;
    }

    public void Render(Graphics g)
    {
        if(m_vecStartGates.isEmpty())
        {
            if(m_iNumOfInput==0)
                m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Red");
            else
                m_imageIcon=ResourceMgr.GetInst().GetImageIcon("Result1_Red");
        }
        else
        {
            if(m_iNumOfInput==0)
                m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Blue");
            else
                m_imageIcon=ResourceMgr.GetInst().GetImageIcon("Result1_Blue");
        }
        m_image = m_imageIcon.getImage();
        g.drawImage(m_image, m_vFianlPos.x-m_iWidth / 2, m_vFianlPos.y-m_iHeight / 2, null);
    }
}
