import java.awt.Color;
import java.awt.Graphics;

public class OutputPort extends Port
{
    private boolean m_bConnected = false;
    private boolean m_bReceiveResult=false;
    private String m_strCalculateResultFormula = "F = ";
    private int m_iResult = -1;
    public void Clear()
    {
        m_strCalculateResultFormula="F = ";
    }
    public OutputPort(int x,int y)
    {
        super(x,y);
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon("Result0_Red");
        m_image = m_imageIcon.getImage();
        m_iWidth = m_imageIcon.getIconWidth();
        m_iHeight = m_imageIcon.getIconHeight();
    }
    public String GetResultFormula()
    {
        return m_strCalculateResultFormula;
    }
    public void SetResult(int result,String inStrCalculateResultFormula) 
    {
        m_iResult = result;
        m_bReceiveResult = true;
        m_strCalculateResultFormula+=inStrCalculateResultFormula;
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
        if(0<=m_vFinalPos.x&&m_vFinalPos.x<=678&&0<=m_vFinalPos.y&&m_vFinalPos.y<=600)
            g.drawImage(m_image, m_vFinalPos.x - m_iWidth/2, m_vFinalPos.y - m_iHeight/2, null);
        else if(0<=m_vFinalPos.y&&m_vFinalPos.y<=600)
            g.drawImage(m_image, 660-m_imageIcon.getIconWidth(), m_vFinalPos.y - m_iHeight/2, null);
        else if(m_vFinalPos.y<0)
            g.drawImage(m_image, 660-m_imageIcon.getIconWidth(), 0, null);
        else
            g.drawImage(m_image, 660-m_imageIcon.getIconWidth(), 565-m_imageIcon.getIconHeight(), null);
    }
}
