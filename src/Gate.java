import java.awt.Graphics;
import java.util.*;

public class Gate 
{
    // 계산이 한번 끝나면 모든 input들 -1로 만들기
    static int num = 0;
    protected int m_iNum = num++;

    protected int m_arrInput[] = new int[2];
    protected int m_iIdx = 0;

    protected Vec2 m_vPos = new Vec2();
    protected int m_iWidth = 60;
    protected int m_iHeight = 40;

    protected Port m_ioPort[] = new Port[3];

    protected Vector<Integer> m_vecVertices = new Vector<Integer>();
    private OutputPort m_outputPort = null;
    protected int Calculate() 
    {
        return 0;
    }

    protected Gate(int x, int y) 
    {
        m_vPos.x = x;
        m_vPos.y = y;
        m_ioPort[0] = new Port(x, y, m_iWidth, 0);
        m_ioPort[1] = new Port(x, y, m_iWidth, 1);
        m_ioPort[2] = new Port(x, y, m_iWidth, 2);
    }
    public Port IsMouseOn(int x,int y)
    {
        for(Port port:m_ioPort)
        {
            if(port.IsMouseOn(x, y)!=null)
                return port;
        }
        return null;
    }
    public void Render(Graphics g) 
    {
        g.drawRect(m_vPos.x - m_iWidth / 2, m_vPos.y - m_iHeight / 2, m_iWidth, m_iHeight);
        for (int i = 0; i < m_ioPort.length; ++i)
            m_ioPort[i].Render(g);
    }

    public boolean Ready() 
    {
        return 2 == m_iIdx;
    }

    public int GetNum() 
    {
        return m_iNum;
    }

    public void ShowInput() 
    {
        System.out.println(m_arrInput[0] + " " + m_arrInput[1]);
    }

    public void Link(int vertexNum) 
    {
        m_vecVertices.add(vertexNum);
    }

    public OutputPort GetOutputPort() 
    {
        return m_outputPort;
    }

    public void LinkOutPort(OutputPort outputPort) 
    {
        m_outputPort = outputPort;
    }

    public void AddInput(int input) 
    {
        if (m_iIdx >= 2)
            return;
        m_arrInput[m_iIdx++] = input;
    }

    public void Clear() 
    {
        m_iIdx = 0;
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
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate() 
    {
        return m_arrInput[0] & m_arrInput[1];
        // 입력값이 같으면 1, 다르면 0 리턴
    }
}

class ORGate extends Gate 
{
    public ORGate(int x, int y) 
    {
        super(x, y);
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate() 
    {
        return m_arrInput[0] | m_arrInput[1];
        // 입력값 둘 중 하나 이상 1이면 1 리턴
    }
}

class NANDGate extends Gate
{
    public NANDGate(int x, int y)
    {
        super(x, y);
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch(m_arrInput[0] & m_arrInput[1])
        {
            case 0:
                return 1;
            case 1:
                return 0;
            default:
                return 2;
            // AND 게이트의 결과값을 뒤집어서 리턴
        }
    }
}

class NORGate extends Gate
{
    public NORGate(int x, int y)
    {
        super(x, y);
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch(m_arrInput[0] | m_arrInput[1])
        {
            case 0:
                return 1;
            case 1:
                return 0;
            default:
                return 2;
            // OR 게이트의 결과값을 뒤집어서 리턴
        }
    }
}

class XORGate extends Gate
{
    public XORGate(int x, int y)
    {
        super(x, y);
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch (m_arrInput[0] + m_arrInput[1]) 
        {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 0;
            default:
                return 2;
            // 1이 한개일 때만 1 리턴
        }
    }
}

class XNORGate extends Gate
{
    public XNORGate(int x, int y)
    {
        super(x, y);
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch (m_arrInput[0] + m_arrInput[1]) 
        {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                return 2;
            // 1이 짝수개일 때만 1 리턴
        }
    }
}

class BUFFER extends Gate
{
    public BUFFER(int x, int y)
    {
        super(x, y);
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch (m_arrInput[0]) 
        {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return 2;
            // 입력값 그대로 리턴
        }
    }
}

class INVERTER extends Gate
{
    public INVERTER(int x, int y)
    {
        super(x, y);
    }
    // 입력값이 없으면 생성되지 않음

    public int Calculate()
    {
        switch (m_arrInput[0]) 
        {
            case 0:
                return 1;
            case 1:
                return 0;
            default:
                return 2;
            // 입력값을 뒤집어서 리턴
        }
    }
}

class Line 
{
    public int leftX;
    public int leftY;
    public int rightX;
    public int rightY;

    public void Render(Graphics g) 
    {
        g.drawLine(leftX, leftY, (leftX + rightX) / 2, leftY);
        g.drawLine((leftX + rightX) / 2, leftY, (leftX + rightX) / 2, rightY);
        g.drawLine((leftX + rightX) / 2, rightY, rightX, rightY);
    }
}