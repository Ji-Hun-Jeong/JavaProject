import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;
import javax.swing.ImageIcon;

public class InputPort extends Port
{
    private Vector<Gate> m_vecStartGates = new Vector<Gate>();
    private Vector<PORT_TYPE> m_vecPortTypes = new Vector<PORT_TYPE>();
    private boolean m_bPlayAudio = false;
    static char ch = 'A';
    protected char m_cCalculateSymbol = ch++;

    public char GetCalculateSymbol_Char()
    {
        return m_cCalculateSymbol;
    }
    public String GetCalculateSymbol()
    {
        String str=new String();
        str+=m_cCalculateSymbol;
        return str;
    }
    public static void ClearCalculateSymbol()
    {
        ch='A';
    }
    public InputPort(int x,int y)
    {
        super(x,y);
        AudioMgr.GetInst().Play("Sound\\InputPort.wav");
        m_bSetInput = true;
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Red");
        m_image = m_imageIcon.getImage();
        m_iWidth = m_imageIcon.getIconWidth();
        m_iHeight = m_imageIcon.getIconHeight();
    }

    public void ChangeNum()
    {
        AudioMgr.GetInst().Play("Sound\\Beep.wav");
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
            m_vecPortTypes.add(onMousePort.GetPortType());
            return true;
        }
        return false;
    }
    public Vector<Gate> GetStartGateVec() 
    {
        return m_vecStartGates;
    }
    public Vector<PORT_TYPE> GetPortTypeVec()
    {
        return m_vecPortTypes;
    }

    public void Render(Graphics g)
    {
        final Vec2 mousePos = MyMouseListener.GetInst().GetMousePos();
        boolean mouseon = IsMouseOn(mousePos.x, mousePos.y)!=null?true:false;
        if(m_vecStartGates.isEmpty())
        {
            if(m_iNumOfInput==0)
            {
                if(mouseon)
                    m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Red_Pushed");
                else
                    m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Red");
            }
            else
            {
                if(mouseon)
                    m_imageIcon=ResourceMgr.GetInst().GetImageIcon("Result1_Red_Pushed");
                else
                    m_imageIcon=ResourceMgr.GetInst().GetImageIcon("Result1_Red");
            }
                
        }
        else
        {
            if(m_iNumOfInput==0)
            {
                if(mouseon)
                    m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Blue_Pushed");
                else
                    m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Blue");
            }
                
            else
            {
                if(mouseon)
                    m_imageIcon=ResourceMgr.GetInst().GetImageIcon("Result1_Blue_Pushed");
                else
                    m_imageIcon=ResourceMgr.GetInst().GetImageIcon("Result1_Blue");
            }    
        }
        m_image = m_imageIcon.getImage();
        g.drawImage(m_image, m_vFinalPos.x-m_iWidth / 2, m_vFinalPos.y-m_iHeight / 2, null);
        
    }
}
