import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javax.swing.*;

enum BUTTON_TYPE
{
    BUFFER, INVERTER, AND, OR, NAND, XOR, NOR, XNOR, CALCULATE, LINE, INPUT, OUTPUT, CHANGE, CLEAR, END,
}

public class MainPanel extends MyPanel
{
    private Vec2 m_vFirstPos = new Vec2();
    private Vec2 m_vCurPos = new Vec2();
    private Vector<InputPort> m_vecInputPorts = new Vector<InputPort>();
    private Vector<OutputPort> m_vecOutputPorts = new Vector<OutputPort>();
    private Vector<Gate> m_vecGates = new Vector<Gate>();
    private Vector<Line> m_vecLines = new Vector<Line>();

    private AlphaComposite m_alphaChanel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
    private ImageIcon m_backGroundImageIcon = null;
    private Image m_backGroundImage = null;
    public void SetMousePos(Vec2 inFirstPos,Vec2 inCurPos){m_vFirstPos=inFirstPos;m_vCurPos=inCurPos;}
    public MainPanel()
    {
        setLayout(null);
        m_backGroundImageIcon = ResourceMgr.GetInst().GetImageIcon("BackGround");
        m_backGroundImage = m_backGroundImageIcon.getImage();
    }
    public void FinalUpdate()
    {
        for(Gate gate : m_vecGates)
        {
            if(gate!=null)
                gate.FinalUpdate();
        }
        for(InputPort port : m_vecInputPorts)
        {
            if(port!=null)
                port.FinalUpdate();
        }
        for(OutputPort port : m_vecOutputPorts)
        {
            if(port!=null)
                port.FinalUpdate();
        }
        for(Line line : m_vecLines)
        {
            if(line!=null)
                line.FinalUpdate();
        }
    }
    public void Update()
    {
        BUTTON_TYPE btnType = MyBtnListener.GetInst().GetBtnType();
        MOUSE_TYPE mouseType = MyMouseListener.GetInst().GetCheckMouseType();
        Gate gate = null;
        boolean bChecked=false;
        switch(btnType)
        {
            case BUFFER:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new BUFFER(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
                break;
            case INVERTER:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new INVERTER(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
                break;
            case AND:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new ANDGate(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
                break;
            case OR:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new ORGate(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);   
                    bChecked=true;
                }
                break;
            case NAND:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new NANDGate(m_vCurPos.x, m_vCurPos.y);
                    PushGate(gate);
                    bChecked=true;
                }
                break;
            case NOR:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new NORGate(m_vCurPos.x, m_vCurPos.y);
                    PushGate(gate);
                    bChecked=true;
                }
                break;
            case XOR:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new XORGate(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
                break;
            case XNOR:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    gate = new XNORGate(m_vCurPos.x,m_vCurPos.y);
                    PushGate(gate);  
                    bChecked=true;
                }
                break;
            case LINE:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    CreateLine();
                    bChecked = true;
                }           
                break;
            case CALCULATE:
                bChecked=true;
                MyBtnListener.GetInst().InitButtonType();
                break;
            case INPUT:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    InputPort inputPort = new InputPort(m_vCurPos.x,m_vCurPos.y);
                    PushInputPort(inputPort);  
                    bChecked=true;
                }
                break;
            case OUTPUT:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    OutputPort outputPort = new OutputPort(m_vCurPos.x,m_vCurPos.y);
                    PushOutputPort(outputPort);  
                    bChecked=true;
                }
                break;
            case CHANGE:
                if(mouseType.equals(MOUSE_TYPE.RELEASED))
                {
                    for(InputPort inputPort:m_vecInputPorts)
                    {
                        if(inputPort.IsMouseOn(m_vCurPos.x, m_vCurPos.y)!=null)
                        {
                            inputPort.ChangeNum();
                        }
                    }
                    bChecked=true;
                }
                break;
            //case DELETE:
                //break;
            case CLEAR:
                Clear();
                break;
            default:
                break;
        }  
        Calculate();
        if(bChecked)
        {
            MyMouseListener.GetInst().InitMouse();
            bChecked=false;
        }
        FinalUpdate();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(m_backGroundImage, 0, 0,null);
        Vec2 camera = Camera.GetInst().GetCameraPos();

        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,0));
        g2d.setColor(Color.RED);
        for(Line line : m_vecLines)
        {
            line.Render(g2d);
        }
        for(Gate gate : m_vecGates)
        {
            gate.Render(g);
        }
        for(InputPort inputPort:m_vecInputPorts)
        {
            inputPort.Render(g);
        }
        for(OutputPort outputPort:m_vecOutputPorts)
        {
            outputPort.Render(g);
        }
        if(MyMouseListener.GetInst().GetCheckMouseType().equals(MOUSE_TYPE.DRAGGED)&&MyBtnListener.GetInst().GetBtnType().equals(BUTTON_TYPE.LINE))
        {
            g.drawLine(m_vFirstPos.x-camera.x,m_vFirstPos.y-camera.y,m_vCurPos.x-camera.x,m_vCurPos.y-camera.y);
        }
        ImageIcon imageIcon = ResourceMgr.GetInst().GetImageIcon(MyBtnListener.GetInst().GetBtnType().toString());
        if(imageIcon!=null)
        {
            Image image= imageIcon.getImage();
            g2d.setComposite(m_alphaChanel);
            int posX = m_vCurPos.x - camera.x - imageIcon.getIconWidth()/2;
            int posY = m_vCurPos.y - camera.y - imageIcon.getIconHeight()/2;
            g.drawImage(image, posX, posY, imageIcon.getIconWidth(),imageIcon.getIconHeight(),null);
        }
    }
    public void PushInputPort(InputPort inPort){m_vecInputPorts.add(inPort);}
    public void PushOutputPort(OutputPort inPort){m_vecOutputPorts.add(inPort);}
    public void PushGate(Gate inGate){m_vecGates.add(inGate);}
    public void PushLine(Line inLine){m_vecLines.add(inLine);}
    public void CreateLine()
    {
        Gate firstGate=null;
        Gate secondGate=null;
        Port firstPort=null;
        Port secondPort=null;
        boolean fillBoth=false;
        Vec2 camera = Camera.GetInst().GetCameraPos();
        for (Gate gates : m_vecGates) 
        {
            Port port1 = gates.IsMouseOn(m_vFirstPos.x, m_vFirstPos.y);
            Port port2 = gates.IsMouseOn(m_vCurPos.x, m_vCurPos.y);
            if (port1 != null) 
            {
                firstGate = gates;
                firstPort = port1;
            }
            if (port2 != null) 
            {
                secondGate = gates;
                secondPort = port2;
            }
            if (firstGate != null && secondGate != null)
                break;
        }
        if(firstGate==secondGate)
            return;
        if(null == firstGate && null == secondGate)
            return;
        if (firstGate == null) 
        {
            if(secondPort.IsLinked())
                return;
            InputPort inputPort = null;
            OutputPort outputPort = null;
            for (InputPort port : m_vecInputPorts) 
            {
                if (port.IsMouseOn(m_vFirstPos.x, m_vFirstPos.y) != null) 
                {
                    inputPort = port;
                    fillBoth = true;
                    break;
                }
            }
            if (inputPort == null) 
            {
                for (OutputPort port : m_vecOutputPorts) 
                {
                    if (port.IsMouseOn(m_vFirstPos.x, m_vFirstPos.y) != null)
                    {
                        if(port.IsConnected())
                            return;
                        outputPort = port;
                        fillBoth = true;
                        break;
                    }
                }
            } 
            if (fillBoth) 
            {
                if (inputPort != null)
                {
                    if(secondPort.GetPortType().equals(PORT_TYPE.OUTPUT))
                        return;
                    if(inputPort.GetPos().x>secondPort.GetPos().x)
                        return;
                    if(!inputPort.SetStartGate(secondGate,secondPort))
                        return;
                    //secondGate.AddInput(inputPort.GetInput());
                    firstPort = inputPort;
                }
                else if(outputPort!=null)
                {
                    if(secondPort.GetPortType().equals(PORT_TYPE.OUTPUT))
                    {
                        if(outputPort.GetPos().x<secondPort.GetPos().x)
                            return;
                        if(secondGate.GetOutputPort()!=null)
                            return;
                        firstPort = outputPort;
                        secondGate.LinkOutPort(outputPort);
                    }
                    else
                        return;
                }
                else
                    return;
            }
        } 
        else if (secondGate == null) 
        {
            if(firstPort.IsLinked())
                return;
            InputPort inputPort = null;
            OutputPort outputPort = null;
            for (InputPort port : m_vecInputPorts) 
            {
                if (port.IsMouseOn(m_vCurPos.x, m_vCurPos.y) != null) 
                {
                    inputPort = port;
                    fillBoth = true;
                    break;
                }
            }
            if (inputPort == null) 
            {
                for (OutputPort port : m_vecOutputPorts) 
                {
                    if (port.IsMouseOn(m_vCurPos.x, m_vCurPos.y) != null) 
                    {
                        if(port.IsConnected())
                            return;
                        outputPort = port;
                        fillBoth = true;
                        break;
                    }
                }
            } 
            if (fillBoth) 
            {
                if (inputPort != null)
                {
                    if(firstPort.GetPortType().equals(PORT_TYPE.OUTPUT))
                        return;
                    if(inputPort.GetPos().x>firstPort.GetPos().x)
                        return;
                    if(!inputPort.SetStartGate(firstGate,firstPort))
                        return;
                    //firstGate.AddInput(inputPort.GetInput());
                    secondPort = inputPort;
                }
                else if(outputPort!=null)
                {
                    if(firstPort.GetPortType().equals(PORT_TYPE.OUTPUT))
                    {
                        if(outputPort.GetPos().x<firstPort.GetPos().x)
                            return;
                        if(firstGate.GetOutputPort()!=null)
                            return;
                        firstGate.LinkOutPort(outputPort);
                        secondPort = outputPort;
                    }
                    else
                        return;
                }
                else
                    return;
            }
        } 
        else 
        {
            if (m_vFirstPos.x < m_vCurPos.x) 
            {
                PORT_TYPE type1 = firstPort.GetPortType();
                PORT_TYPE type2 = secondPort.GetPortType();
                if(secondPort.IsLinked())
                    return;
                if(type1.equals(PORT_TYPE.OUTPUT)&&(type2.equals(PORT_TYPE.INPUT1)||type2.equals(PORT_TYPE.INPUT2)||type2.equals(PORT_TYPE.ONEINPUT)))
                {
                    firstGate.Link(secondGate,secondPort);
                }

                else
                    return;
            } 
            else
            {
                PORT_TYPE type1 = firstPort.GetPortType();
                PORT_TYPE type2 = secondPort.GetPortType();
                if(firstPort.IsLinked())
                    return;
                if(type2.equals(PORT_TYPE.OUTPUT)&&(type1.equals(PORT_TYPE.INPUT1)||type1.equals(PORT_TYPE.INPUT2)||type2.equals(PORT_TYPE.ONEINPUT)))
                    secondGate.Link(firstGate,firstPort);
                else
                    return;
            }
            fillBoth = true;
        }
        if (fillBoth) 
        {
            Line line = new Line();
            m_vFirstPos.x = firstPort.GetPos().x;
            m_vFirstPos.y = firstPort.GetPos().y;
            m_vCurPos.x = secondPort.GetPos().x;
            m_vCurPos.y = secondPort.GetPos().y;
            line.leftX = m_vFirstPos.x;
            line.leftY = m_vFirstPos.y;
            line.rightX = m_vCurPos.x;
            line.rightY = m_vCurPos.y;
            PushLine(line);
        }
    }
    public void Calculate()
    {
        for(int i=0;i<m_vecGates.size();++i)
        {
            m_vecGates.get(i).Clear();
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for(InputPort inputPort : m_vecInputPorts)
        {
            Vector<Gate> startGateVec = inputPort.GetStartGateVec();
            Vector<PORT_TYPE> portTypeVec = inputPort.GetPortTypeVec();
            for(int i=0;i<startGateVec.size();++i)
            {
                Gate gate = startGateVec.get(i);
                gate.AddInput(inputPort.GetInput(),portTypeVec.get(i),inputPort.GetCalculateSymbol());
                if(gate.Ready())
                    q.add(gate.GetNum());
            }
        }

        while(!q.isEmpty())
        {
            int num = q.poll();
            Gate curGate = SearchGateNum(m_vecGates, num);
            if(!curGate.IsFullInputPort())
                continue;
            if(!curGate.Ready())
            {
                q.add(num);
                continue;
            }         
            int result = curGate.Calculate();
            OutputPort outputPort=curGate.GetOutputPort();
            if(outputPort!=null)
            {
                outputPort.SetResult(result,curGate.GetCalculateSymbol());
                System.out.println(outputPort.GetResultFormula());
                outputPort.Clear();
            }
            else
            {
                Vector<Integer> curGateAdjVerticesVector = curGate.GetIdxVec();
                Vector<PORT_TYPE> curGatePortTypeVector = curGate.GetPortTypeVec();
                for(int i=0;i<curGateAdjVerticesVector.size();++i)
                {
                    Gate adjGate = SearchGateNum(m_vecGates,curGateAdjVerticesVector.get(i));

                    adjGate.AddInput(result,curGatePortTypeVector.get(i),curGate.GetCalculateSymbol());
                    q.add(adjGate.GetNum());
                }
            }  
        }
    }
    private void Clear()
    {
        m_vecGates.clear();
        m_vecInputPorts.clear();
        m_vecLines.clear();
        m_vecOutputPorts.clear();
        InputPort.ClearCalculateSymbol();
    }
    private Gate SearchGateNum(Vector<Gate> inGatesVec, int inNum)
    {
        for(Gate gate : inGatesVec)
        {
            if(gate.GetNum()==inNum)
                return gate;
        }
        return null;
    }
}