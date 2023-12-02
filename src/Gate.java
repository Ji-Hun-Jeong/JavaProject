import java.awt.*;
import java.util.*;
import javax.swing.*;
abstract public class Gate 
{
    // 계산이 한번 끝나면 모든 input들 -1로 만들기
    static int num = 0;
    protected int m_iNum = num++;

    protected int m_iNumOfInputPort = 0;
    protected int m_iCalculateResult = -1;

    protected String m_strName = null;
    protected Vec2 m_vPos = new Vec2();
    protected Vec2 m_vFinalPos = new Vec2();
    protected int m_iWidth = 60;
    protected int m_iHeight = 40;
    protected ImageIcon m_imageIcon = null;
    protected Image m_image = null;

    protected Port m_ioPort[] = new Port[PORT_TYPE.END.ordinal()];

    protected Vector<Integer> m_vecVertices = new Vector<Integer>();
    private OutputPort m_outputPort = null;
    abstract protected int Calculate();
    public Image GetImage(){return m_image;}

    protected Gate(int x, int y) 
    {
        m_vPos.x = x;
        m_vPos.y = y;
        m_vFinalPos.x = m_vPos.x;
        m_vFinalPos.y = m_vPos.y;
        m_ioPort[PORT_TYPE.INPUT1.ordinal()] = new Port(x, y, m_iWidth, PORT_TYPE.INPUT1);
        m_ioPort[PORT_TYPE.INPUT2.ordinal()] = new Port(x, y, m_iWidth, PORT_TYPE.INPUT2);
        m_ioPort[PORT_TYPE.OUTPUT.ordinal()] = new Port(x, y, m_iWidth, PORT_TYPE.OUTPUT);
    }
    public void Update()
    {
        Vec2 camera = Camera.GetInst().GetCameraPos();
        m_vFinalPos.x = m_vPos.x-camera.x;
        m_vFinalPos.y = m_vPos.y-camera.y;
        for(Port port : m_ioPort)
        {
            if(port!=null)
            {
                port.Update();
            }
        }
    }
    public boolean LinkInputPort(Port port)
    {
        if(!port.IsLinked())
        {
            port.SetLinked();
            return true;
        }
        return false;
    }
    public boolean IsFullInputPort()
    {
        if(!m_ioPort[PORT_TYPE.INPUT1.ordinal()].IsLinked())
            return false;
        else if(!m_ioPort[PORT_TYPE.INPUT2.ordinal()].IsLinked())
            return false;
        return true;
    }
    public Port IsMouseOn(int x,int y)
    {
        for(Port port:m_ioPort)
        {
            if(port!=null)
            {
                if(port.IsMouseOn(x, y)!=null)
                    return port;
            }
        }
        return null;
    }
    public void Render(Graphics g) 
    {
        g.setColor(Color.BLUE);
        //g.drawRect(m_vFinalPos.x - m_iWidth / 2, m_vFinalPos.y - m_iHeight / 2, m_iWidth, m_iHeight);
        g.drawImage(m_image, m_vFinalPos.x - m_imageIcon.getIconWidth()/2, m_vFinalPos.y - m_imageIcon.getIconHeight()/2, m_imageIcon.getIconWidth(), m_imageIcon.getIconHeight(), null);
        /*for(Port port : m_ioPort)
        {
            if(port!=null)
                port.Render(g);
        }
        */
    }

    public boolean Ready() 
    {
        if(!m_ioPort[PORT_TYPE.INPUT1.ordinal()].Ready())
            return false;
        if(!m_ioPort[PORT_TYPE.INPUT2.ordinal()].Ready())
            return false;
        return true;
    }

    public int GetNum() 
    {
        return m_iNum;
    }

    public void Link(Gate gate, Port port) 
    {
        m_vecVertices.add(gate.GetNum());
        gate.LinkInputPort(port);
    }

    public OutputPort GetOutputPort() 
    {
        return m_outputPort;
    }

    public void LinkOutPort(OutputPort outputPort) 
    {
        if(m_outputPort==null)
        {
            m_outputPort = outputPort;
            m_outputPort.Connect();
        }
    }

    public void AddInput(int input) 
    {
        if(Ready())
            return;
        if(!m_ioPort[PORT_TYPE.INPUT1.ordinal()].Ready())
            m_ioPort[PORT_TYPE.INPUT1.ordinal()].SetInput(input);
        else if(!m_ioPort[PORT_TYPE.INPUT2.ordinal()].Ready())
            m_ioPort[PORT_TYPE.INPUT2.ordinal()].SetInput(input);
    }

    public void Clear() 
    {
        for(Port port : m_ioPort)
        {
            if(port!=null)
                port.Clear();
        }
    }

    public Vector<Integer> GetIdxVec() 
    {
        return m_vecVertices;
    }
}

class ANDGate extends Gate 
{
    public ANDGate(int x, int y) 
    {
        super(x, y);
        m_strName=BUTTON_TYPE.AND.toString();
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon(m_strName);
        m_image = m_imageIcon.getImage();
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate() 
    {
        m_iCalculateResult = m_ioPort[PORT_TYPE.INPUT1.ordinal()].GetInput() & m_ioPort[PORT_TYPE.INPUT2.ordinal()].GetInput();
        m_ioPort[PORT_TYPE.OUTPUT.ordinal()].SetInput(m_iCalculateResult);
        return m_iCalculateResult;
        // 입력값이 같으면 1, 다르면 0 리턴
    }
}

class ORGate extends Gate 
{
    public ORGate(int x, int y) 
    {
        super(x, y);
        m_strName=BUTTON_TYPE.OR.toString();
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon(m_strName);
        m_image = m_imageIcon.getImage();
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate() 
    {
        m_iCalculateResult = m_ioPort[PORT_TYPE.INPUT1.ordinal()].GetInput() | m_ioPort[PORT_TYPE.INPUT2.ordinal()].GetInput();
        m_ioPort[PORT_TYPE.OUTPUT.ordinal()].SetInput(m_iCalculateResult);
        return m_iCalculateResult;
        // 입력값 둘 중 하나 이상 1이면 1 리턴
    }
}

class NANDGate extends Gate
{
    public NANDGate(int x, int y)
    {
        super(x, y);
        m_strName=BUTTON_TYPE.NAND.toString();
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon(m_strName);
        m_image = m_imageIcon.getImage();
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch(m_ioPort[PORT_TYPE.INPUT1.ordinal()].GetInput() & m_ioPort[PORT_TYPE.INPUT2.ordinal()].GetInput())
        {
            case 0:
                m_iCalculateResult = 1;
                break;
            case 1:
                m_iCalculateResult = 0;
                break;
            default:
                m_iCalculateResult = 2;
                break;
            // AND 게이트의 결과값을 뒤집어서 리턴
        }
        m_ioPort[PORT_TYPE.OUTPUT.ordinal()].SetInput(m_iCalculateResult);
        return m_iCalculateResult;
    }
}

class NORGate extends Gate
{
    public NORGate(int x, int y)
    {
        super(x, y);
        m_strName=BUTTON_TYPE.NOR.toString();
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon(m_strName);
        m_image = m_imageIcon.getImage();
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch(m_ioPort[PORT_TYPE.INPUT1.ordinal()].GetInput() | m_ioPort[PORT_TYPE.INPUT2.ordinal()].GetInput())
        {
            case 0:
                m_iCalculateResult = 1;
                break;
            case 1:
                m_iCalculateResult = 0;
                break;
            default:
                m_iCalculateResult = 2;
                break;
            // OR 게이트의 결과값을 뒤집어서 리턴
        }
        m_ioPort[PORT_TYPE.OUTPUT.ordinal()].SetInput(m_iCalculateResult);
        return m_iCalculateResult;
    }
}

class XORGate extends Gate
{
    public XORGate(int x, int y)
    {
        super(x, y);
        m_strName=BUTTON_TYPE.XOR.toString();
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon(m_strName);
        m_image = m_imageIcon.getImage();
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch (m_ioPort[PORT_TYPE.INPUT1.ordinal()].GetInput() + m_ioPort[PORT_TYPE.INPUT2.ordinal()].GetInput()) 
        {
            case 0:
                m_iCalculateResult=  0;
                break;
            case 1:
                m_iCalculateResult= 1;
                break;
            case 2:
                m_iCalculateResult= 0;
                break;
            default:
                m_iCalculateResult= 2;
                break;
            // 1이 한개일 때만 1 리턴
        }
        m_ioPort[PORT_TYPE.OUTPUT.ordinal()].SetInput(m_iCalculateResult);
        return m_iCalculateResult;
    }
}

class XNORGate extends Gate
{
    public XNORGate(int x, int y)
    {
        super(x, y);
        m_strName=BUTTON_TYPE.XNOR.toString();
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon(m_strName);
        m_image = m_imageIcon.getImage();
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch (m_ioPort[PORT_TYPE.INPUT1.ordinal()].GetInput() + m_ioPort[PORT_TYPE.INPUT2.ordinal()].GetInput()) 
        {
            case 0:
                m_iCalculateResult= 1;
                break;
            case 1:
                m_iCalculateResult= 0;
                break;
            case 2:
                m_iCalculateResult= 1;
                break;
            default:
                m_iCalculateResult= 2;
                break;
            // 1이 한개일 때만 1 리턴
        }
        m_ioPort[PORT_TYPE.OUTPUT.ordinal()].SetInput(m_iCalculateResult);
        return m_iCalculateResult;
    }
}

class BUFFER extends Gate
{
    public BUFFER(int x, int y)
    {
        super(x, y);
        m_ioPort[PORT_TYPE.INPUT1.ordinal()]=null;
        m_ioPort[PORT_TYPE.INPUT2.ordinal()]=null;
        m_ioPort[PORT_TYPE.ONEINPUT.ordinal()] = new Port(x,y,m_iWidth,PORT_TYPE.ONEINPUT);
        m_strName=BUTTON_TYPE.BUFFER.toString();
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon(m_strName);
        m_image = m_imageIcon.getImage();
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        m_iCalculateResult = m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].GetInput();
        m_ioPort[PORT_TYPE.OUTPUT.ordinal()].SetInput(m_iCalculateResult);
        return m_iCalculateResult;
    }
    public boolean IsFullInputPort()
    {
        if(!m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].IsLinked())
            return false;
        return true;
    }
    public boolean LinkInputPort()
    {
        if(!m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].IsLinked())
        {
            m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].SetLinked();
            return true;
        }
        return false;
    }
    public boolean Ready()
    {
        if(!m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].Ready())
            return false;
        return true;
    }
    public void AddInput(int input) 
    {
        if(Ready())
            return;
        m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].SetInput(input);
    }
}

class INVERTER extends Gate
{
    public INVERTER(int x, int y)
    {
        super(x, y);
        m_ioPort[PORT_TYPE.INPUT1.ordinal()]=null;
        m_ioPort[PORT_TYPE.INPUT2.ordinal()]=null;
        m_ioPort[PORT_TYPE.ONEINPUT.ordinal()] = new Port(x,y,m_iWidth,PORT_TYPE.ONEINPUT);
        m_strName=BUTTON_TYPE.INVERTER.toString();
        m_imageIcon = ResourceMgr.GetInst().GetImageIcon(m_strName);
        m_image = m_imageIcon.getImage();
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch (m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].GetInput()) 
        {
            case 0:
                m_iCalculateResult = 1;
                break;
            case 1:
                m_iCalculateResult = 0;
                break;
            default:
                m_iCalculateResult = 2;
                break;
            // 입력값을 뒤집어서 리턴
        }
        m_ioPort[PORT_TYPE.OUTPUT.ordinal()].SetInput(m_iCalculateResult);
        return m_iCalculateResult;
    }
    public boolean IsFullInputPort()
    {
        if(!m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].IsLinked())
            return false;
        return true;
    }
    public boolean LinkInputPort()
    {
        if(!m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].IsLinked())
        {
            m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].SetLinked();
            return true;
        }
        return false;
    }
    public boolean Ready()
    {
        if(!m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].Ready())
            return false;
        return true;
    }
    public void AddInput(int input) 
    {
        if(Ready())
            return;
        m_ioPort[PORT_TYPE.ONEINPUT.ordinal()].SetInput(input);
    }
}

class Line 
{
    public int leftX;
    public int leftY;
    public int rightX;
    public int rightY;
    public int finalLeftX;
    public int finalLeftY;
    public int finalRightX;
    public int finalRightY;
    public Color color;
    public Line()
    {
        int r = (int)(Math.random()*300)%200;
        int g = (int)(Math.random()*300)%200;
        int b = (int)(Math.random()*300)%200;
        color = new Color(r,g,b);
    }
    public void Update()
    {
        Vec2 camera = Camera.GetInst().GetCameraPos();
        finalLeftX = leftX - camera.x;
        finalLeftY = leftY - camera.y;
        finalRightX = rightX - camera.x;
        finalRightY = rightY - camera.y;
    }
    public void Render(Graphics g) 
    {
        g.setColor(color);
        g.drawLine(finalLeftX, finalLeftY, (finalLeftX + finalRightX) / 2, finalLeftY);
        g.drawLine((finalLeftX + finalRightX) / 2, finalLeftY, (finalLeftX + finalRightX) / 2, finalRightY);
        g.drawLine((finalLeftX + finalRightX) / 2, finalRightY, finalRightX, finalRightY);
    }
}