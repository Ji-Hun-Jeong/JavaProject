import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
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
    private JPanel m_arrPanel[] = new JPanel[PANEL_TYPE.END.ordinal()];

    public Working_Scene()
    {
        super("논리회로 그리기");
        m_container.setLayout(new BorderLayout());

        m_arrPanel[PANEL_TYPE.BUTTON.ordinal()] = new ButPanel();
        m_arrPanel[PANEL_TYPE.IO.ordinal()] = new IOPanel();
        m_arrPanel[PANEL_TYPE.MAIN.ordinal()] = new MainPanel();

        MyMouseListener singleMouseListener = MyMouseListener.GetInst();
        singleMouseListener.SetMainPanel(m_arrPanel[PANEL_TYPE.MAIN.ordinal()]);
        m_arrPanel[PANEL_TYPE.MAIN.ordinal()].addMouseListener(singleMouseListener);
        m_arrPanel[PANEL_TYPE.MAIN.ordinal()].addMouseMotionListener(singleMouseListener);

        m_container.add(m_arrPanel[PANEL_TYPE.BUTTON.ordinal()], BorderLayout.EAST);
        m_container.add(m_arrPanel[PANEL_TYPE.IO.ordinal()], BorderLayout.SOUTH);
        m_container.add(m_arrPanel[PANEL_TYPE.MAIN.ordinal()], BorderLayout.CENTER);

        setSize(1000, 600);
        setVisible(true);
    }
    
    public JPanel GetPanels(PANEL_TYPE type){return m_arrPanel[type.ordinal()];}

    public void Update()
    {
        ((MainPanel)m_arrPanel[PANEL_TYPE.MAIN.ordinal()]).Update();
    }
}