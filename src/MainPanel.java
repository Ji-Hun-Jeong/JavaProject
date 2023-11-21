import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;
public class MainPanel extends JPanel
{
    private Vec2 m_vFirstPos = new Vec2();
    private Vec2 m_vCurPos = new Vec2();
    private Vector<INPUTGate> m_vecInputGates = new Vector<INPUTGate>();
    private Vector<OUTPUTGate> m_vecOutputGates = new Vector<OUTPUTGate>();
    private Vector<Gate> m_vecGates = new Vector<Gate>();
    private Vector<Line> m_vecLines = new Vector<Line>();
    public void SetMousePos(Vec2 inFirstPos,Vec2 inCurPos){m_vFirstPos=inFirstPos;m_vCurPos=inCurPos;}
    public MainPanel()
    {
        setLayout(null);
        setBackground(Color.WHITE);
    }
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLUE);
        for(Gate gate : m_vecGates)
        {
            gate.Render(g);
        }
        for(Line line : m_vecLines)
        {
            line.Render(g);
        }
        if(MyMouseListener.GetInst().GetCheckClicked())
        {
            g.drawLine(m_vFirstPos.x,m_vFirstPos.y,m_vCurPos.x,m_vCurPos.y);
        }
    }
    public void PushInputGate(INPUTGate inGate){m_vecInputGates.add(inGate);}
    public void PushOutputGate(OUTPUTGate inGate){m_vecOutputGates.add(inGate);}
    public void PushGate(Gate inGate){m_vecGates.add(inGate);}
    public void PushLine(Line inLine){m_vecLines.add(inLine);}
}