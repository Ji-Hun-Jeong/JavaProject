import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
enum PORT_TYPE
{
    ONEINPUT, INPUT1, INPUT2, OUTPUT, END,
}
public class Port extends JLabel
{
    protected int m_iNumOfInput = 0;
    protected boolean m_bSetInput = false;
    protected boolean m_bLinked = false;
    private PORT_TYPE m_ePortType = PORT_TYPE.END;

    protected int m_iOffsetX = 10;
    protected int m_iOffsetY = 8;
    protected Vec2 m_vPos = new Vec2();
    protected Vec2 m_vFinalPos = new Vec2();
    protected int m_iWidth = 10;
    protected int m_iHeight = 10;

    public Port(int x,int y)
    {
        m_vPos.x = x;
        m_vPos.y = y;
        m_vFinalPos.x = m_vPos.x;
        m_vFinalPos.y = m_vPos.y;
    }
    public Port(int gateX, int gateY, int gateSizeX, PORT_TYPE portType) 
    {
        m_ePortType = portType;
        if(m_ePortType.equals(PORT_TYPE.ONEINPUT))
        {
            m_vPos.x = gateX - gateSizeX / 2 + m_iOffsetX;
            m_vPos.y = gateY;
        }
        else if (m_ePortType.equals(PORT_TYPE.INPUT1)) 
        {
            m_vPos.x = gateX - gateSizeX / 2 + m_iOffsetX;
            m_vPos.y = gateY - m_iOffsetY;
        } 
        else if (m_ePortType.equals(PORT_TYPE.INPUT2)) 
        {
            m_vPos.x = gateX - gateSizeX / 2 + m_iOffsetX;
            m_vPos.y = gateY + m_iOffsetY;
        } 
        else if(m_ePortType.equals(PORT_TYPE.OUTPUT))
        {
            m_vPos.x = gateX + gateSizeX / 2 - m_iOffsetX;
            m_vPos.y = gateY;
        }
        m_vFinalPos.x = m_vPos.x;
        m_vFinalPos.y = m_vPos.y;
    }
    public void Update()
    {
        Vec2 camera = Camera.GetInst().GetCameraPos();
        m_vFinalPos.x = m_vPos.x-camera.x;
        m_vFinalPos.y = m_vPos.y-camera.y;
    }
    public void SetLinked(){m_bLinked=true;}
    public boolean IsLinked(){return m_bLinked;}
    public boolean Ready()
    {
        return m_bSetInput;
    }
    public PORT_TYPE GetPortType(){return m_ePortType;}
    public void SetPos(int x,int y)
    {
        m_vPos.x=x;
        m_vPos.y=y;
    }
    public Vec2 GetPos()
    {
        return m_vPos.Copy();
    }
    public void SetInput(int inInput) 
    {
        m_iNumOfInput = inInput;
        m_bSetInput = true;
    }
    public void Clear()
    {
        m_iNumOfInput = 0;
        m_bSetInput = false;
    }

    public int GetInput() 
    {
        return m_iNumOfInput;
    }

    public void Render(Graphics g) 
    {
        g.drawRect(m_vFinalPos.x - m_iWidth / 2, m_vFinalPos.y - m_iHeight / 2, m_iWidth, m_iHeight);
        if(m_ePortType.equals(PORT_TYPE.OUTPUT)&&true == m_bSetInput)
        {
            Integer tsrt = m_iNumOfInput;
            g.drawString(tsrt.toString(), m_vFinalPos.x, m_vFinalPos.y-m_iHeight*3);
        }
    }

    public Port IsMouseOn(int x,int y)
    {
        if(m_vPos.x - m_iWidth / 2<=x&&x<=m_vPos.x + m_iWidth / 2&&m_vPos.y - m_iHeight / 2<=y&&y<=m_vPos.y + m_iHeight / 2)
        {
            return this;
        }
        return null;
    }
}
