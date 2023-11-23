import java.awt.Graphics;
public class Port 
{
    private int m_iNumOfInput = 0;
    private int m_iOffsetX = 10;
    private int m_iOffsetY = 8;
    private Vec2 m_vPos = new Vec2();
    private int m_iWidth = 10;
    private int m_iHeight = 10;
    public Port(int x,int y)
    {
        m_vPos.x = x;
        m_vPos.y = y;
    }
    public Port(int gateX, int gateY, int gateSizeX, int num) 
    {
        if (0 == num) 
        {
            m_vPos.x = gateX - gateSizeX / 2 + m_iOffsetX;
            m_vPos.y = gateY + m_iOffsetY;
        } 
        else if (1 == num) 
        {
            m_vPos.x = gateX - gateSizeX / 2 + m_iOffsetX;
            m_vPos.y = gateY - m_iOffsetY;
        } 
        else 
        {
            m_vPos.x = gateX + gateSizeX / 2 - m_iOffsetX;
            m_vPos.y = gateY;
        }
    }
    public Vec2 GetPos()
    {
        return m_vPos.Copy();
    }
    public void SetInput(int inInput) 
    {
        m_iNumOfInput = inInput;
    }

    public int GetInput() 
    {
        return m_iNumOfInput;
    }

    public void Render(Graphics g) 
    {
        g.drawRect(m_vPos.x - m_iWidth / 2, m_vPos.y - m_iHeight / 2, m_iWidth, m_iHeight);
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
