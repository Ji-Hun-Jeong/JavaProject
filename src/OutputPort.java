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
        g.setColor(Color.BLUE);
        if(!m_bConnected)
            g.setColor(Color.RED);
        super.Render(g);
    }
}
