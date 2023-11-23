import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import javax.swing.JPanel;

public class MainPanel extends MyPanel
{
    private Vec2 m_vFirstPos = new Vec2();
    private Vec2 m_vCurPos = new Vec2();
    private Vector<InputPort> m_vecInputPorts = new Vector<InputPort>();
    private Vector<OutputPort> m_vecOutputPorts = new Vector<OutputPort>();
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
        boolean bChecked=false;
        switch(btnType)
        {
            case BUFFER:
                if(m_eMouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new BUFFER(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
                break;
            case INVERTER:
                if(m_eMouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new INVERTER(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
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
                if(m_eMouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new NANDGate(m_vCurPos.x, m_vCurPos.y);
                    PushGate(gate);
                    bChecked=true;
                }
                break;
            case NOR:
                if(m_eMouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new NORGate(m_vCurPos.x, m_vCurPos.y);
                    PushGate(gate);
                    bChecked=true;
                }
                break;
            case XOR:
                if(m_eMouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new XORGate(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
                break;
            case XNOR:
                if(m_eMouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new XNORGate(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
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
            case CALCULATE:
                Calculate();
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
    public void PushInputPort(InputPort inPort){m_vecInputPorts.add(inPort);}
    public void PushOutputPort(OutputPort inPort){m_vecOutputPorts.add(inPort);}
    public void PushGate(Gate inGate){m_vecGates.add(inGate);}
    public void PushLine(Line inLine){m_vecLines.add(inLine);}
    public void Calculate()
    {
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=0;i<m_vecInputPorts.size();++i)
        {
            Gate startGate = m_vecInputPorts.get(i).GetStartGate();
            startGate.AddInput(m_vecInputPorts.get(i).GetInput());
            if(startGate.Ready())
                q.add(startGate.GetNum());
        }

        while(!q.isEmpty())
        {
            int idx = q.poll();
            Gate gate = m_vecGates.get(idx);
            if(!gate.Ready())
            {
                q.add(idx);
                continue;
            }         
            int result = gate.Calculate();
            OutputPort outputPort=m_vecGates.get(idx).GetOutputPort();
            if(outputPort!=null)
            {
                outputPort.SetResult(result);
            }
            else
            {
                for(int i=0;i<gate.GetIdxVec().size();++i)
                {
                    Gate adjGate = m_vecGates.get(gate.GetIdxVec().get(i));
                    adjGate.AddInput(result);
                    q.add(adjGate.GetNum());
                }
            }  
        }
        for(OutputPort outputPort:m_vecOutputPorts)
        {
            // 여따가 아웃풋 보이게 하는 기능
            //System.out.println(outputPort.GetResult());
        }
        for(int i=0;i<m_vecGates.size();++i)
        {
            m_vecGates.get(i).Clear();
        }
    }
}