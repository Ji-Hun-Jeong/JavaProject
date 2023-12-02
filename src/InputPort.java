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
        g.setColor(Color.BLUE);
        if(m_vecStartGates.isEmpty())
        {
            g.setColor(Color.RED);
        }
        super.Render(g);
        Integer tstr = m_iNumOfInput;
        g.drawString(tstr.toString(),m_vFinalPos.x-m_iWidth / 2,m_vFinalPos.y+m_iHeight / 2);
    }
}
