import java.awt.Color;
import java.awt.Graphics;

public class InputPort extends Port
{
    private int m_iNum = 0;
    private Gate m_startGate = null;
    public InputPort(int x,int y)
    {
        super(x,y);
    }

    public void SetNum(int num) 
    {
        m_iNum = num;
    }

    public void SetStartGate(Gate startGate) 
    {
        m_startGate = startGate;
    }

    public Gate GetStartGate() 
    {
        return m_startGate;
    }

    public void Render(Graphics g)
    {
        if(m_startGate==null)
        {
            g.setColor(Color.RED);
        }
        super.Render(g);
        g.setColor(Color.BLUE);
    }
}
