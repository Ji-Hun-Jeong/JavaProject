import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
enum BUTTON_TYPE
{
    BUFFER, INVERTER, AND, OR, NAND, XOR, NOR, XNOR, LINE, TABLE, END,
}
enum PANEL_TYPE
{
    BUTTON, IO, MAIN, END,
}
public class Working_Scene extends Scene
{
    private JButton m_arrButton[] = new JButton[BUTTON_TYPE.END.ordinal()];
    private JPanel m_arrPanel[] = new JPanel[PANEL_TYPE.END.ordinal()];

    private Vector<INPUTGate> m_vecInputGates = new Vector<INPUTGate>();
    private Vector<OUTPUTGate> m_vecOutputGates = new Vector<OUTPUTGate>();
    private Vector<Gate> m_vecGates = new Vector<Gate>();

    public Working_Scene()
    {
        super("논리회로 그리기");
        m_container.setLayout(new BorderLayout());

        m_arrPanel[PANEL_TYPE.BUTTON.ordinal()] = new ButPanel();
        m_arrPanel[PANEL_TYPE.IO.ordinal()] = new IOPanel();
        m_arrPanel[PANEL_TYPE.MAIN.ordinal()] = new MainPanel();
        MyMouseListener myMouseListener =new MyMouseListener();
        m_arrPanel[PANEL_TYPE.MAIN.ordinal()].addMouseListener(myMouseListener);
        m_arrPanel[PANEL_TYPE.MAIN.ordinal()].addMouseMotionListener(myMouseListener);

        m_container.add(m_arrPanel[PANEL_TYPE.BUTTON.ordinal()], BorderLayout.EAST);
        m_container.add(m_arrPanel[PANEL_TYPE.IO.ordinal()], BorderLayout.SOUTH);
        m_container.add(m_arrPanel[PANEL_TYPE.MAIN.ordinal()], BorderLayout.CENTER);

        m_arrButton[BUTTON_TYPE.BUFFER.ordinal()] = new JButton(BUTTON_TYPE.BUFFER.name());
        m_arrButton[BUTTON_TYPE.INVERTER.ordinal()] = new JButton(BUTTON_TYPE.INVERTER.name());
        m_arrButton[BUTTON_TYPE.AND.ordinal()] = new JButton(BUTTON_TYPE.AND.name());
        m_arrButton[BUTTON_TYPE.OR.ordinal()] = new JButton(BUTTON_TYPE.OR.name());
        m_arrButton[BUTTON_TYPE.NAND.ordinal()] = new JButton(BUTTON_TYPE.NAND.name());
        m_arrButton[BUTTON_TYPE.XOR.ordinal()] = new JButton(BUTTON_TYPE.XOR.name());
        m_arrButton[BUTTON_TYPE.NOR.ordinal()] = new JButton(BUTTON_TYPE.NOR.name());
        m_arrButton[BUTTON_TYPE.XNOR.ordinal()] = new JButton(BUTTON_TYPE.XNOR.name());
        m_arrButton[BUTTON_TYPE.LINE.ordinal()] = new JButton(BUTTON_TYPE.LINE.name());
        m_arrButton[BUTTON_TYPE.TABLE.ordinal()] = new JButton(BUTTON_TYPE.TABLE.name());
        
        for(int i=0;i<BUTTON_TYPE.END.ordinal();++i)
        {
            m_arrButton[i].addActionListener(new MyBtnListener());
            m_arrPanel[PANEL_TYPE.BUTTON.ordinal()].add(m_arrButton[i]);
        }

        setSize(1000, 600);
        setVisible(true);
    }
    
    public JButton GetButtons(BUTTON_TYPE type){return m_arrButton[type.ordinal()];}
    public JPanel GetPanels(PANEL_TYPE type){return m_arrPanel[type.ordinal()];}

    public void PushInputGate(INPUTGate inGate){m_vecInputGates.add(inGate);}
    public void PushOutputGate(OUTPUTGate inGate){m_vecOutputGates.add(inGate);}
    public void PushGate(Gate inGate){m_vecGates.add(inGate);}

    public void Update()
    {
        // Todo
    }
}