import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
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
    private MOUSE_TYPE m_eMouseType = MOUSE_TYPE.END;
    public void SetMousePos(Vec2 inFirstPos,Vec2 inCurPos){m_vFirstPos=inFirstPos;m_vCurPos=inCurPos;}
    public MainPanel()
    {
        setLayout(null);
        setBackground(Color.WHITE);
    }
    public void Update()
    {
        BUTTON_TYPE btnType = MyBtnListener.GetInst().GetBtnType();
        m_eMouseType = MyMouseListener.GetInst().GetCheckMouseType();
        if(btnType.equals(BUTTON_TYPE.END))
            return;
        if(m_eMouseType.equals(MOUSE_TYPE.END))
            return;

        Gate gate = null;
        //System.out.println(m_eMouseTYPE);
        boolean bChecked=false;
        switch(btnType)
        {
            case BUFFER:
                break;
            case INVERTER:
                break;
            case AND:
                if(m_eMouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new ANDGate(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
                break;
            case OR:
                if(m_eMouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new ORGate(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);   
                    bChecked=true;
                }
                break;
            case NAND:
                break;
            case XOR:
                break;
            case NOR:
                break;
            case XNOR:
                break;
            case LINE:
                if(m_eMouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    Line line = new Line();
                    line.leftX=m_vFirstPos.x;
                    line.leftY=m_vFirstPos.y;
                    for(Gate vecGate : m_vecGates)
                    {
                        Port port = vecGate.IsMouseOn(m_vCurPos.x, m_vCurPos.y);
                        if(port!=null)
                        {
                            Vec2 portPos = port.GetPos();
                            m_vCurPos.x = portPos.x;
                            m_vCurPos.y = portPos.y;
                            break;
                        }
                    }
                    line.rightX=m_vCurPos.x;
                    line.rightY=m_vCurPos.y;
                    PushLine(line);
                    bChecked=true;
                }
                else if(m_eMouseType.equals(MOUSE_TYPE.PRESSED))
                {
                    for(Gate vecGate : m_vecGates)
                    {
                        Port port = vecGate.IsMouseOn(m_vCurPos.x, m_vCurPos.y);
                        if(port!=null)
                        {
                            Vec2 portPos = port.GetPos();
                            m_vFirstPos.x = portPos.x;
                            m_vFirstPos.y = portPos.y;
                            break;
                        }
                    }
                }     
                break;
            case TABLE:
                break;
            default:
                break;
        }
        if(bChecked)
        {
            MyMouseListener.GetInst().InitMouse();
            bChecked=false;
        }
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
        
        if(m_eMouseType.equals(MOUSE_TYPE.DRAGGED)&&MyBtnListener.GetInst().GetBtnType().equals(BUTTON_TYPE.LINE))
        {
            g.drawLine(m_vFirstPos.x,m_vFirstPos.y,m_vCurPos.x,m_vCurPos.y);
        }
    }
    public void PushInputGate(INPUTGate inGate){m_vecInputGates.add(inGate);}
    public void PushOutputGate(OUTPUTGate inGate){m_vecOutputGates.add(inGate);}
    public void PushGate(Gate inGate){m_vecGates.add(inGate);}
    public void PushLine(Line inLine){m_vecLines.add(inLine);}
}