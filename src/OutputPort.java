import java.awt.Color;
import java.awt.Graphics;

public class OutputPort extends Port
{
    private boolean m_bConnected = false;
    private boolean m_bReceiveResult=false;
    private int m_iResult = -1;
    public OutputPort(int x,int y)
    {
        super(x,y);
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Red");
        m_image = m_imageIcon.getImage();
        m_iWidth = m_imageIcon.getIconWidth();
        m_iHeight = m_imageIcon.getIconHeight();
    }
    public void SetResult(int result) 
    {
        m_iResult = result;
        m_bReceiveResult = true;
    }

    public int GetResult() 
    {
        return m_iResult;
    }

    public void ShowResult() 
    {
        System.out.println(m_iResult);
    }

    public void Connect(){m_bConnected=true;}

    public boolean IsConnected(){return m_bConnected;}

    public void InitResult(){m_bReceiveResult = false;}

    public void Render(Graphics g)
    {
        if(!m_bConnected)
        {
            m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Red");
        }     
        else
        {
            if(m_iResult==0)
                m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Blue");
            else
                m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result1_Blue");
        }
        m_image = m_imageIcon.getImage();
        g.drawImage(m_image, m_vFianlPos.x - m_iWidth/2, m_vFianlPos.y - m_iHeight/2, null);
    }
}
